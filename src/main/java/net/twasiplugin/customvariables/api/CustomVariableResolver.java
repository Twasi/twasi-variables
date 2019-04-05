package net.twasiplugin.customvariables.api;

import net.twasi.core.database.models.User;
import net.twasi.core.graphql.TwasiCustomResolver;
import net.twasiplugin.customvariables.api.models.VariableCollectionDTO;

public class CustomVariableResolver extends TwasiCustomResolver {

    public VariableCollectionDTO getVariableCollection(String token) {
        User user = getUser(token);
        if (user == null) return null;
        return new VariableCollectionDTO(user);
    }

}
