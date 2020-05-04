package com.thesis.omstravel.utils;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class CheckNumberic {
    private Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    public boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }
}
