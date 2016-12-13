package com.alliander.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * Created by OmarChenoufInfiniot on 13-12-2016.
 */
@Data
@AllArgsConstructor
public class Meerlengte {
    private final double cost;
    private final int length;
    private final GeoCoordinate asset;
    private final Date time;
}
