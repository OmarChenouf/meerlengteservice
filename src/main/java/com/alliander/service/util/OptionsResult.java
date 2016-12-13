package com.alliander.service.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.JsonSchemaGenerator;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by OmarChenoufInfiniot on 13-12-2016.
 */
@AllArgsConstructor
public class OptionsResult {
    private static final Logger LOG = LoggerFactory.getLogger(OptionsResult.class);

    private Map<RequestMethod, Class<?>> classesPerMethod;

    @Override
    public String toString() {
        List<String> listOfMethods = classesPerMethod.keySet().stream()
                .map(RequestMethod::toString)
                .collect(Collectors.toList());
        String methods = "Methods: " + String.join(", ", listOfMethods);

        List<String> schemas = classesPerMethod.entrySet().stream()
                .filter(entry -> entry.getValue() != null)
                .map(entry -> {
                    String result = "Input schema for " + entry.getKey() + ":";
                    result += System.lineSeparator();
                    result += getSchema(entry.getValue());
                    result += System.lineSeparator();
                    result += System.lineSeparator();
                    return result;
                })
                .collect(Collectors.toList());

        String result = methods +
                System.lineSeparator() +
                System.lineSeparator() +
                String.join("", schemas);

        return result;
    }

    private String getSchema(Class<?> clazz) {
        String result = null;

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonSchemaGenerator schemaGen = new JsonSchemaGenerator(mapper);
            JsonSchema schema = schemaGen.generateSchema(clazz);
            result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(schema);
        } catch(JsonProcessingException e) {
            LOG.error(e.getMessage(), e);
        }

        return result;
    }
}
