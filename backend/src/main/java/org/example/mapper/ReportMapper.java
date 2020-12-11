package org.example.mapper;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public abstract class ReportMapper {

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Req {

        @NotNull
        Date first;

        @NotNull
        Date last;

        @NotNull
        EntityMapper.EntityRef entity;

    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Resp {

        Date first;
        Date last;
        EntityMapper.EntityFull entity;
        List<DealMapper.DealFull> sellDeals;
        List<DealMapper.DealFull> buyDeals;
        List<RespItem> sellItems;
        List<RespItem> buyItems;
        BigDecimal sum;
        BigDecimal allSum;
        List<RespItem> allItems;

    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class RespItem {

        BigDecimal quantity;
        ItemMapper.ItemLite item;

    }
}
