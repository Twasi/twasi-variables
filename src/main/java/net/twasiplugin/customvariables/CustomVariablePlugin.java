package net.twasiplugin.customvariables;

import net.twasi.core.plugin.TwasiPlugin;
import net.twasi.core.plugin.api.TwasiUserPlugin;

public class CustomVariablePlugin extends TwasiPlugin {

    @Override
    public Class<? extends TwasiUserPlugin> getUserPluginClass() {
        return CustomVariableUserPlugin.class;
    }

}
