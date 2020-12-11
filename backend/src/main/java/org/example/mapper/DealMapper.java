package org.example.mapper;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.example.model.Deal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Mapper
public abstract class DealMapper {

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class DealToSave {

        @NotNull
        Date date;

        @NotNull
        @DecimalMin(value = "0.0", inclusive = false)
        BigDecimal price;

        @NotNull
        @DecimalMin(value = "0.0", inclusive = false)
        BigDecimal quantity;

        @NotNull
        ItemMapper.ItemRef item;

        @NotNull
        EntityMapper.EntityRef seller;

        @NotNull
        EntityMapper.EntityRef buyer;

    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class DealLite {
        Long id;
        Date date;
        BigDecimal price;
        BigDecimal quantity;
        ItemMapper.ItemLite item;
        EntityMapper.EntityLite seller;
        EntityMapper.EntityLite buyer;
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class DealFull {
        Long id;
        Date date;
        BigDecimal price;
        BigDecimal quantity;
        ItemMapper.ItemLite item;
        EntityMapper.EntityLite seller;
        EntityMapper.EntityLite buyer;
    }

    public abstract Deal mapToSave(DealToSave dealToSave);

    public abstract DealToSave mapToSave(Deal deal);

    public abstract Deal mapLite(DealLite dealLite);

    public abstract DealLite mapLite(Deal deal);

    public abstract Deal mapFull(DealFull dealFull);

    public abstract DealFull mapFull(Deal deal);
}
