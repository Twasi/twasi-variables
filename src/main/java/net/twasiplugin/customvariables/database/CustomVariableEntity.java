package net.twasiplugin.customvariables.database;

import jdk.nashorn.internal.ir.annotations.Reference;
import net.twasi.core.database.models.BaseEntity;
import net.twasi.core.database.models.User;
import net.twasi.core.database.models.permissions.PermissionGroups;
import org.mongodb.morphia.annotations.Entity;

@Entity(value = "customvariables", noClassnameStored = true)
public class CustomVariableEntity extends BaseEntity {

    @Reference
    private User user;

    private String variable;
    private String output;

    public CustomVariableEntity() {
    }

    public CustomVariableEntity(User user, String variable, String output) {
        this.user = user;
        this.variable = variable.toLowerCase();
        this.output = output;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getVariable() {
        return variable.toLowerCase();
    }

    public void setVariable(String variable) {
        this.variable = variable.toLowerCase();
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
}
