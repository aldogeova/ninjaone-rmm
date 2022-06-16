package com.ninjaone.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public class NinjaUuiiUtils {

    public static final String UUID_PATTERN = "^[0-9A-Fa-f]{8}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{12}$";

    public static UUID identifierToUuid(String id) {
        try {
            return UUID.fromString(id);
        } catch (Exception e) {
            log.error("Invalid id: {} because {}", id, e);
            throw new IllegalArgumentException("Invalid id: " + id);
        }
    }

}
