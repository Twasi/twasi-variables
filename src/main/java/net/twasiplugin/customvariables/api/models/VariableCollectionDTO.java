package net.twasiplugin.customvariables.api.models;

import net.twasi.core.database.models.User;
import net.twasiplugin.customvariables.database.CustomVariableEntity;

import java.util.List;
import java.util.stream.Collectors;

import static net.twasiplugin.customvariables.CustomVariablePlugin.service;

public class VariableCollectionDTO {

    private User user;

    public VariableCollectionDTO(User user) {
        this.user = user;
    }

    public VariableDTO getVariableByName(String varName) {
        CustomVariableEntity variable = service.getVariableByName(user, varName);
        if (variable == null) return null;
        return new VariableDTO(variable);
    }

    public List<VariableDTO> getAllVariables() {
        return service.getVariables(user).stream().map(VariableDTO::new).collect(Collectors.toList());
    }

    public boolean addVariable(String name, String output) {
        return service.addVariable(user, name, output);
    }

    public boolean removeVariable(String id) {
        return service.removeVariable(user, id);
    }

    public boolean editVariable(String id, String name, String output) {
        CustomVariableEntity variable = service.getVariable(user, id);
        if (variable == null) return false;

        variable.setVariable(name);
        variable.setOutput(output);

        service.save(variable);
        return true;
    }

}
