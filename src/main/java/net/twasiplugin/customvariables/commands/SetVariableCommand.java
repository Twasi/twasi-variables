package net.twasiplugin.customvariables.commands;

import net.twasi.core.database.models.User;
import net.twasi.core.plugin.api.TwasiUserPlugin;
import net.twasi.core.plugin.api.customcommands.TwasiCustomCommandEvent;
import net.twasi.core.plugin.api.customcommands.TwasiPluginCommand;
import net.twasi.core.services.ServiceRegistry;
import net.twasi.core.services.providers.DataService;
import net.twasiplugin.customvariables.CustomVariableUserPlugin;
import net.twasiplugin.customvariables.TwasiCustomVariable;
import net.twasiplugin.customvariables.database.CustomVariableEntity;
import net.twasiplugin.customvariables.database.CustomVariableRepository;

public class SetVariableCommand extends TwasiPluginCommand {

    private CustomVariableUserPlugin tup;
    private CustomVariableRepository repo;

    public SetVariableCommand(CustomVariableUserPlugin twasiUserPlugin) {
        super(twasiUserPlugin);
        this.tup = twasiUserPlugin;
        repo = ServiceRegistry.get(DataService.class).get(CustomVariableRepository.class);
    }

    @Override
    public void process(TwasiCustomCommandEvent e) {
        if (e.hasArgs()) {
            String name = e.getArgs().get(0), sender = e.getSender().getDisplayName();
            if (name.startsWith("$")) name = name.substring(1);

            /*
            VARIABLE NAME REGEX
                ^           -> Start of name
                [a-z]       -> Force to start with letter
                [a-z0-9]    -> Only allow numbers and letters
                *           -> Also allow variables with only one letter
                $           -> End of name
             */

            if (!name.matches("^[a-z][a-z0-9]*$")) {
                e.reply(getTranslation("twasi.variables.invalidname", sender));
                return;
            }

            User user = tup.getTwasiInterface().getStreamer().getUser();
            CustomVariableEntity entity = repo.getVariableByUserAndName(user, name);
            if (e.getArgs().size() >= 2) {
                String output = e.getArgsAsOne().replaceFirst(name + " ", "");
                if (entity != null) {
                    // Update
                    if (!e.hasPermission("net.twasi.variables.update")) return;
                    entity.setOutput(output);
                    repo.commit(entity);
                    e.reply(getTranslation("twasi.variables.edited", name, sender));
                } else {
                    // Add
                    // TODO check for varibales of other plugins
                    if (!e.hasPermission("net.twasi.variables.create")) return;
                    entity = new CustomVariableEntity(user, name, output);
                    repo.add(entity);
                    e.reply(getTranslation("twasi.variables.created", name, sender));
                }
                tup.getRegisteredVars().put(name.toLowerCase(), new TwasiCustomVariable(tup, entity));
            } else {
                if (entity != null) {
                    e.reply(getTranslation("twasi.variables.content", sender, name, entity.getOutput(), name));
                } else {
                    e.reply(getTranslation("twasi.variables.invalidvar", sender));
                }
            }
        } else {
            e.reply(getTranslation("twasi.variables.helptext.set"));
        }
    }

    @Override
    public String getCommandName() {
        return "setvar";
    }

    @Override
    public boolean allowsTimer() {
        return false;
    }

    @Override
    public boolean allowsListing() {
        return false;
    }

}
