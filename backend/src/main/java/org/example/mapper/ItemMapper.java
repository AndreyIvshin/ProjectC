package org.example.mapper;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.example.model.Item;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Mapper
public abstract class ItemMapper {

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class ItemRef {
        Long id;
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class ItemToSave {

        @NotNull
        @Size(min = 2, max = 100)
        String name;

        @NotNull
        UnitMapper.UnitRef unit;
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class ItemLite {
        Long id;
        String name;
        UnitMapper.UnitLite unit;
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class ItemFull {
        Long id;
        String name;
        UnitMapper.UnitLite unit;
    }

    public abstract Item mapRef(ItemRef itemRef);

    public abstract ItemRef mapRef(Item item);

    public abstract Item mapToSave(ItemToSave itemToSave);

    public abstract ItemToSave mapToSave(Item item);

    public abstract Item mapLite(ItemLite itemLite);

    public abstract ItemLite mapLite(Item item);

    public abstract Item mapFull(ItemFull itemFull);

    public abstract ItemFull mapFull(Item item);
}
