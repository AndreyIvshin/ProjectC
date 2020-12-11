package org.example.mapper;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.example.model.Entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Mapper
public abstract class EntityMapper {

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class EntityRef {
        Long id;
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class EntityToSave {

        @NotNull
        @Size(min = 2, max = 100)
        String name;
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class EntityLite {
        Long id;
        String name;
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class EntityFull {
        Long id;
        String name;
    }

    public abstract Entity mapRef(EntityRef entityRef);

    public abstract EntityRef mapRef(Entity entity);

    public abstract Entity mapToSave(EntityToSave entityToSave);

    public abstract EntityToSave mapToSave(Entity entity);

    public abstract Entity mapLite(EntityLite entityLite);

    public abstract EntityLite mapLite(Entity entity);

    public abstract Entity mapFull(EntityFull entityFull);

    public abstract EntityFull mapFull(Entity entity);
}
