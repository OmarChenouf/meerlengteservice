package com.alliander.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * This is an 'aansluitpunt'; a location from where a connection to an asset is made.
 * Created by Ramon on 7-7-2016.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConnectionPoint {
    @Valid
    @NotNull
    private GeoCoordinate coordinate;
    @NotNull
    private String cableConnectionType;
}
