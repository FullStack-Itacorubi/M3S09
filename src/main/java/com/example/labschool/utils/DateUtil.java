package com.example.labschool.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private DateUtil() {}

    public static String dataParaString(Date data) {
        return dataParaString(data, "yyyy-MM-dd");
    }

    public static String dataParaString(Date data, String padrao) {
        DateFormat dateFormat = new SimpleDateFormat(padrao);
        return dateFormat.format(data);
    }

}
