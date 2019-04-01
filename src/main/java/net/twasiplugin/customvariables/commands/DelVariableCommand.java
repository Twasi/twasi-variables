package net.twasiplugin.customvariables.commands;

import net.twasi.core.plugin.api.TwasiUserPlugin;
import net.twasi.core.plugin.api.customcommands.TwasiCustomCommand;
import net.twasi.core.plugin.api.customcommands.TwasiCustomCommandEvent;
import net.twasi.core.plugin.api.customcommands.TwasiPluginCommand;

public class DelVariableCommand extends TwasiPluginCommand {

    public DelVariableCommand(TwasiUserPlugin twasiUserPlugin) {
        super(twasiUserPlugin);
    }

    @Override
    public void process(TwasiCustomCommandEvent e) {
        if (e.hasArgs()) {
            String var = e.getArgs().get(0);
            // TODO delete var
        } else {
            // TODO helptext
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
