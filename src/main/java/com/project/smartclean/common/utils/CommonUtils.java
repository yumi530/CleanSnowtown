package com.project.smartclean.common.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public final class CommonUtils {

    public static String getOrderUUID() {
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHMMss"));
        String uuid = UUID.randomUUID().toString().replace("-", "");

        return now + uuid;
    }


}
