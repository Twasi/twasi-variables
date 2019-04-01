package net.twasiplugin.customvariables.commands;

import net.twasi.core.plugin.api.TwasiUserPlugin;
import net.twasi.core.plugin.api.customcommands.TwasiCustomCommand;
import net.twasi.core.plugin.api.customcommands.TwasiCustomCommandEvent;
import net.twasi.core.plugin.api.customcommands.TwasiPluginCommand;

public class SetVariableCommand extends TwasiPluginCommand {

    public SetVariableCommand(TwasiUserPlugin twasiUserPlugin) {
        super(twasiUserPlugin);
    }

    @Override
    public void process(TwasiCustomCommandEvent e) {
        if (e.hasArgs()) {
            String name = e.getArgs().get(0);
            if (e.getArgs().size() >= 2) {
                // TODO set var content
            } else {
                // TODO return var content
            }
        } else {
            // TODO helptext
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
