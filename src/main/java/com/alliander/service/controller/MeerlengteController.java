package com.alliander.service.controller;

import com.alliander.service.model.ConnectionPoint;
import com.alliander.service.model.Meerlengte;
import com.alliander.service.util.OptionsResult;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.alliander.service.Application.API_PREFIX;

/**
 * Created by OmarChenoufInfiniot on 13-12-2016.
 */
@RefreshScope
@RestController
public class MeerlengteController {

    private static final Logger LOG = LoggerFactory.getLogger(MeerlengteController.class);

    @RequestMapping(value = API_PREFIX + "meerlengte", method = RequestMethod.POST)
    public ResponseEntity<Meerlengte> postMeerlengte(
            @ApiParam(value = "connectionPoint", required = true) @RequestBody @Valid ConnectionPoint connectionPoint) {

        // TODO hoe aan de echte meerlengte komen...

        Meerlengte result = new Meerlengte(10, 20, connectionPoint.getCoordinate(), new Date());
        return new ResponseEntity<Meerlengte>(result, HttpStatus.OK);
    }

    @RequestMapping(value = API_PREFIX + "meerlengte", method = RequestMethod.OPTIONS)
    public String optionsMeerlengte() throws Exception {
        Map<RequestMethod, Class<?>> classesPerMethod = new HashMap<>();
        classesPerMethod.put(RequestMethod.POST, ConnectionPoint.class);
        classesPerMethod.put(RequestMethod.OPTIONS, null);
        String result = new OptionsResult(classesPerMethod).toString();

        return result;
    }
}
