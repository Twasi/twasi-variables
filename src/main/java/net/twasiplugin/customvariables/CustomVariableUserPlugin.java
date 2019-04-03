package net.twasiplugin.customvariables;

import com.sun.javafx.collections.MappingChange;
import net.twasi.core.plugin.api.TwasiUserPlugin;
import net.twasi.core.plugin.api.TwasiVariable;
import net.twasi.core.plugin.api.customcommands.TwasiCustomCommand;
import net.twasi.core.plugin.api.customcommands.TwasiPluginCommand;
import net.twasi.core.plugin.api.events.TwasiEnableEvent;
import net.twasi.core.plugin.api.events.TwasiInstallEvent;
import net.twasi.core.services.ServiceRegistry;
import net.twasi.core.services.providers.DataService;
import net.twasiplugin.customvariables.commands.DelVariableCommand;
import net.twasiplugin.customvariables.commands.SetVariableCommand;
import net.twasiplugin.customvariables.database.CustomVariableEntity;
import net.twasiplugin.customvariables.database.CustomVariableRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CustomVariableUserPlugin extends TwasiUserPlugin {

    private HashMap<String, TwasiCustomVariable> variables = new HashMap<>();
    private List<TwasiPluginCommand> commands;

    public CustomVariableUserPlugin() {
        this.commands = Arrays.asList(
                new DelVariableCommand(this),
                new SetVariableCommand(this)
        );
    }

    @Override
    public void onInstall(TwasiInstallEvent e) {
        e.getAdminGroup().addKey("net.twasi.variables.*");
        e.getModeratorsGroup().addKey("net.twasi.variables.*");
    }

    @Override
    public void onUninstall(TwasiInstallEvent e) {
        e.getAdminGroup().removeKey("net.twasi.variables.*");
        e.getModeratorsGroup().removeKey("net.twasi.variables.*");
    }

    @Override
    public void onEnable(TwasiEnableEvent e) {
        for (CustomVariableEntity var : ServiceRegistry.get(DataService.class).get(CustomVariableRepository.class).getVariablesByUser(getTwasiInterface().getStreamer().getUser())) {
            variables.put(var.getVariable().toLowerCase(), new TwasiCustomVariable(this, var));
        }
    }


    @Override
    public List<TwasiVariable> getVariables() {
        return new ArrayList<>(this.variables.values());
    }

    public HashMap<String, TwasiCustomVariable> getRegisteredVars() {
        return this.variables;
    }

    @Override
    public List<TwasiPluginCommand> getCommands() {
        return this.commands;
    }
}
