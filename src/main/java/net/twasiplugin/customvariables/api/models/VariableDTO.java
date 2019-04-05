package net.twasiplugin.customvariables.api.models;

import net.twasiplugin.customvariables.database.CustomVariableEntity;

public class VariableDTO {

    private CustomVariableEntity entity;

    public VariableDTO(CustomVariableEntity entity) {
        this.entity = entity;
    }

    public VariableDTO(String name, String output) {
        this(new CustomVariableEntity(null, name.toLowerCase(), output));
    }

    public String getName() {
        return entity.getVariable();
    }

    public String getOutput() {
        return entity.getOutput();
    }

    public String getId() {
        return entity.getId().toString();
    }
}
