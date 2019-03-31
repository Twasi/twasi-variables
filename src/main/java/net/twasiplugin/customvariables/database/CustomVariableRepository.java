package net.twasiplugin.customvariables.database;

import net.twasi.core.database.lib.Repository;
import net.twasi.core.database.models.User;

import java.util.List;

public class CustomVariableRepository extends Repository<CustomVariableEntity> {

    public List<CustomVariableEntity> getEntitesByUser(User user) {
        return store.createQuery(CustomVariableEntity.class).field("user").equal(user).asList();
    }

}
