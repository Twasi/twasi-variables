package net.twasiplugin.customvariables;

import net.twasi.core.plugin.api.TwasiUserPlugin;
import net.twasi.core.plugin.api.TwasiVariable;
import net.twasi.core.plugin.api.events.TwasiEnableEvent;
import net.twasi.core.plugin.api.events.TwasiInstallEvent;
import net.twasi.core.services.ServiceRegistry;
import net.twasi.core.services.providers.DataService;
import net.twasiplugin.customvariables.database.CustomVariableEntity;
import net.twasiplugin.customvariables.database.CustomVariableRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ServiceConfigurationError;

public class CustomVariableUserPlugin extends TwasiUserPlugin {

    private HashMap<String, TwasiCustomVariable> variables = new HashMap<>();

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
        for (CustomVariableEntity var : ServiceRegistry.get(DataService.class).get(CustomVariableRepository.class).getEntitesByUser(getTwasiInterface().getStreamer().getUser())) {
            variables.put(var.getVariable(), new TwasiCustomVariable(this, var.getVariable(), var.getOutput(), var.getPermissionGroup()));
        }
    }

    @Override
    public List<TwasiVariable> getVariables() {
        return new ArrayList<>(this.variables.values());
    }
}
