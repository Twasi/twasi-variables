package net.twasiplugin.customvariables;

import net.twasi.core.database.models.permissions.PermissionGroups;
import net.twasi.core.interfaces.api.TwasiInterface;
import net.twasi.core.models.Message.TwasiMessage;
import net.twasi.core.plugin.api.TwasiUserPlugin;
import net.twasi.core.plugin.api.TwasiVariable;
import net.twasiplugin.customvariables.database.CustomVariableEntity;

import java.util.Collections;
import java.util.List;

public class TwasiCustomVariable extends TwasiVariable {

    private String name;
    private String output;

    public TwasiCustomVariable(TwasiUserPlugin owner, String name, String output) {
        super(owner);
        this.name = name;
        this.output = output;
    }

    public TwasiCustomVariable(TwasiUserPlugin owner, CustomVariableEntity entity) {
        this(owner, entity.getVariable(), entity.getOutput());
    }

    @Override
    public List<String> getNames() {
        return Collections.singletonList(this.name);
    }

    @Override
    public String process(String name, TwasiInterface inf, String[] params, TwasiMessage message) {
        return this.output;
    }
}
