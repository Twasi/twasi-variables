package net.twasiplugin.customvariables.services;

import net.twasi.core.database.models.User;
import net.twasi.core.plugin.api.TwasiUserPlugin;
import net.twasi.core.services.IService;
import net.twasi.core.services.ServiceRegistry;
import net.twasi.core.services.providers.DataService;
import net.twasi.core.services.providers.InstanceManagerService;
import net.twasiplugin.customvariables.CustomVariableUserPlugin;
import net.twasiplugin.customvariables.TwasiCustomVariable;
import net.twasiplugin.customvariables.database.CustomVariableEntity;
import net.twasiplugin.customvariables.database.CustomVariableRepository;

import java.util.List;

public class CustomVariableService implements IService {

    private CustomVariableRepository repo = ServiceRegistry.get(DataService.class).get(CustomVariableRepository.class);

    private CustomVariableUserPlugin getUserPluginByUser(User user) {
        TwasiUserPlugin twasiUserPlugin = ServiceRegistry.get(InstanceManagerService.class).getByUser(user).getPlugins().stream()
                .filter(p -> p.getClass().equals(CustomVariableUserPlugin.class)).findFirst().orElse(null);
        if (twasiUserPlugin == null) return null;
        return (CustomVariableUserPlugin) twasiUserPlugin;
    }

    public List<CustomVariableEntity> getVariables(User user) {
        return repo.getVariablesByUser(user);
    }

    public boolean removeVariableByName(User user, String varName) {
        CustomVariableUserPlugin userPlugin = getUserPluginByUser(user);
        if (userPlugin != null) userPlugin.delVar(varName);
        CustomVariableEntity entity = repo.getVariableByUserAndName(user, varName);
        if (entity == null) return false;
        repo.remove(entity);
        return true;
    }

    public boolean removeVariable(User user, String id) {
        CustomVariableEntity variable = repo.getById(id);
        return removeVariableByName(user, variable.getVariable());
    }

    public CustomVariableEntity getVariableByName(User user, String varName) {
        return repo.getVariableByUserAndName(user, varName);
    }

    public void save(CustomVariableEntity entity) {
        repo.commit(entity);
        try {
            getUserPluginByUser(entity.getUser()).loadVars();
        } catch (Exception ignored) {
        }
    }

    public CustomVariableEntity getVariable(User user, String id) {
        return repo.getVariableByUserAndId(user, id);
    }

    public boolean addVariable(CustomVariableEntity entity) {
        if (getVariableByName(entity.getUser(), entity.getVariable()) != null) return false;
        repo.add(entity);
        CustomVariableUserPlugin userPlugin = getUserPluginByUser(entity.getUser());
        if (userPlugin != null)
            userPlugin.setVar(entity.getVariable(), new TwasiCustomVariable(userPlugin, entity));
        return true;
    }

    public boolean addVariable(User user, String name, String output) {
        return addVariable(new CustomVariableEntity(user, name.toLowerCase(), output));
    }

}
