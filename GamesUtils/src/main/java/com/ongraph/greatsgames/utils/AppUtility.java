package com.ongraph.greatsgames.utils;



import org.springframework.format.datetime.DateFormatter;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Date;
import java.util.UUID;

public class AppUtility {

    public static String generateUUID() {
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid.toString());

        String randomUUid = StringUtils.delete(uuid.toString(), "-");
        System.out.println("random-uuid" + randomUUid);
        return randomUUid;
    }


}
