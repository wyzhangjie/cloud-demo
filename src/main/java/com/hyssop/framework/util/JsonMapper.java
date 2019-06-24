package com.hyssop.framework.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.util.Optional;

import javax.annotation.Nullable;

import lombok.extern.slf4j.Slf4j;


/**
 * @author zhjie.zhang
 */
@Slf4j
public class JsonMapper {
    private static final JsonMapper instance = new JsonMapper();

    private ObjectMapper objectMapper;

    public JsonMapper() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        this.objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public static JsonMapper getInstance() {
        return instance;
    }

    public String toJson(Object o) {
        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            log.error("exception:", e);
        }

        return "";
    }

    @Nullable
    public <T> T fromJson(String json, Class<T> clazz) {
        if (StringUtils.isBlank(json)) {
            return null;
        }

        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            log.error("exception:", e);
        }

        return null;
    }


    public <T> Optional<T> fromJsonOp(String json, Class<T> clazz) {
        try {
            return Optional.ofNullable(objectMapper.readValue(json, clazz));
        } catch (IOException e) {
            log.error("exception:", e);
        }
        return Optional.empty();
    }


    @Nullable
    public <T> T fromJson(String json, TypeReference<T> typeReference) {
        try {
            return objectMapper.readValue(json, typeReference);
        } catch (IOException e) {
            log.error("exception:", e);
        }

        return null;
    }

    public <T> Optional<T> fromJsonOp(String json, TypeReference<T> typeReference) {
        try {
            return Optional.ofNullable(objectMapper.readValue(json, typeReference));
        } catch (IOException e) {
            log.error("exception:", e);
        }

        return Optional.empty();
    }

    public ObjectNode createObjectNode() {
        return objectMapper.createObjectNode();
    }

    public ArrayNode createArrayNode() {
        return objectMapper.createArrayNode();
    }
}
