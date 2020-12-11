package org.example.mapper;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.example.model.Unit;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Mapper
public abstract class UnitMapper {

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class UnitRef {
        Long id;
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class UnitToSave {

        @NotNull
        @Size(min = 2, max = 100)
        String name;

    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class UnitLite {
        Long id;
        String name;
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class UnitFull {
        Long id;
        String name;
    }
    public abstract Unit mapRef(UnitRef unitRef);

    public abstract UnitRef mapRef(Unit unit);

    public abstract Unit mapToSave(UnitToSave unitToSave);

    public abstract UnitToSave mapToSave(Unit unit);

    public abstract Unit mapLite(UnitLite unitLite);

    public abstract UnitLite mapLite(Unit unit);

    public abstract Unit mapFull(UnitFull unitFull);

    public abstract UnitFull mapFull(Unit unit);
}
