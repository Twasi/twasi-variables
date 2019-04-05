package net.twasiplugin.customvariables.database;

import net.twasi.core.database.lib.Repository;
import net.twasi.core.database.models.User;
import org.bson.types.ObjectId;

import java.util.List;

public class CustomVariableRepository extends Repository<CustomVariableEntity> {

    public List<CustomVariableEntity> getVariablesByUser(User user) {
        return store.createQuery(CustomVariableEntity.class).field("user").equal(user).asList();
    }

    public CustomVariableEntity getVariableByUserAndName(User user, String name) {
        return store.createQuery(CustomVariableEntity.class).field("user").equal(user).field("variable").equalIgnoreCase(name).get();
    }

    public CustomVariableEntity getVariableByUserAndId(User user, String id) {
        return store.createQuery(CustomVariableEntity.class).field("user").equal(user).field("_id").equal(new ObjectId(id)).get();
    }

    public boolean hasVaribale(User user, String name) {
        return getVariableByUserAndName(user, name) != null;
    }
}
