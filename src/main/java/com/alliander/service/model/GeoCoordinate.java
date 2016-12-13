package com.alliander.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Created by OmarChenoufInfiniot on 13-12-2016.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeoCoordinate {
    @NotNull
    private double x;
    @NotNull
    private double y;
}
