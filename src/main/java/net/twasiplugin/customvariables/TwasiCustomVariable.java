package net.twasiplugin.customvariables;

import net.twasi.core.database.models.permissions.PermissionGroups;
import net.twasi.core.interfaces.api.TwasiInterface;
import net.twasi.core.models.Message.TwasiMessage;
import net.twasi.core.plugin.api.TwasiUserPlugin;
import net.twasi.core.plugin.api.TwasiVariable;

import java.util.Collections;
import java.util.List;

public class TwasiCustomVariable extends TwasiVariable {

    private String name;
    private String output;
    private PermissionGroups permissionGroup;

    public TwasiCustomVariable(TwasiUserPlugin owner, String name, String output, PermissionGroups permissionGroup) {
        super(owner);
        this.name = name;
        this.output = output;
        this.permissionGroup = permissionGroup;
    }

    @Override
    public List<String> getNames() {
        return Collections.singletonList(this.name);
    }

    @Override
    public String process(String name, TwasiInterface inf, String[] params, TwasiMessage message) {
        if (message.getSender().getGroups().contains(this.permissionGroup))
            return this.output;
        return null;
    }
}
