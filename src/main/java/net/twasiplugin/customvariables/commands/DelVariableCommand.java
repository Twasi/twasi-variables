package net.twasiplugin.customvariables.commands;

import net.twasi.core.database.models.User;
import net.twasi.core.plugin.api.TwasiUserPlugin;
import net.twasi.core.plugin.api.customcommands.TwasiCustomCommand;
import net.twasi.core.plugin.api.customcommands.TwasiCustomCommandEvent;
import net.twasi.core.plugin.api.customcommands.TwasiPluginCommand;
import net.twasi.core.services.ServiceRegistry;
import net.twasi.core.services.providers.DataService;
import net.twasiplugin.customvariables.CustomVariableUserPlugin;
import net.twasiplugin.customvariables.database.CustomVariableEntity;
import net.twasiplugin.customvariables.database.CustomVariableRepository;

public class DelVariableCommand extends TwasiPluginCommand {

    private final CustomVariableUserPlugin tup;
    private final CustomVariableRepository repo;

    public DelVariableCommand(CustomVariableUserPlugin twasiUserPlugin) {
        super(twasiUserPlugin);
        repo = ServiceRegistry.get(DataService.class).get(CustomVariableRepository.class);
        tup = twasiUserPlugin;
    }

    @Override
    public void process(TwasiCustomCommandEvent e) {
        if (!e.hasPermission("net.twasi.variables.delete")) return;
        User user = tup.getTwasiInterface().getStreamer().getUser();
        String sender = e.getSender().getDisplayName();
        if (e.hasArgs()) {
            String name = e.getArgs().get(0);
            if (name.startsWith("$")) name = name.substring(1);
            CustomVariableEntity entity = repo.getVariableByUserAndName(user, name);
            if (entity != null) {
                repo.remove(entity);
                tup.getRegisteredVars().remove(entity.getVariable().toLowerCase());
                e.reply(getTranslation("twasi.variables.deleted", sender, entity.getVariable()));
            } else {
                e.reply(getTranslation("twasi.variables.invalidvar", sender, name));
            }
        } else {
            e.reply(getTranslation("twasi.variables.helptext.delete", sender));
        }
    }

    @Override
    public String getCommandName() {
        return "delvar";
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
