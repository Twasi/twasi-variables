package net.twasiplugin.customvariables;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import net.twasi.core.plugin.TwasiPlugin;
import net.twasi.core.plugin.api.TwasiUserPlugin;
import net.twasi.core.services.ServiceRegistry;
import net.twasiplugin.customvariables.api.CustomVariableResolver;
import net.twasiplugin.customvariables.services.CustomVariableService;

public class CustomVariablePlugin extends TwasiPlugin {

    public static CustomVariableService service;

    @Override
    public void onActivate() {
        service = new CustomVariableService();
        ServiceRegistry.register(service);
    }

    @Override
    public Class<? extends TwasiUserPlugin> getUserPluginClass() {
        return CustomVariableUserPlugin.class;
    }

    @Override
    public GraphQLQueryResolver getGraphQLResolver() {
        return new CustomVariableResolver();
    }
}
