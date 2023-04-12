package com.digdes.school;

import java.util.Map;

public class FormatDataValidator {

    public Boolean isDouble(String word) {
        boolean isOnlyDigits = true;
        for(int i = 0; i < word.length() && isOnlyDigits; i++) {
            if(!Character.isDigit(word.charAt(i)) && !word.contains(".")) {
                isOnlyDigits = false;
            }
        }
        return isOnlyDigits;
    }

    public Boolean isLong(String word) {
        boolean isOnlyDigits = true;
        for(int i = 0; i < word.length() && isOnlyDigits; i++) {
            if(!Character.isDigit(word.charAt(i))) {
                isOnlyDigits = false;
            }
        }
        return isOnlyDigits;
    }


    public Boolean isString(String word) {
        boolean isOnlyLetter = true;
        for(int i = 0; i < word.length() && isOnlyLetter; i++) {
            if(!Character.isLetter(word.charAt(i))) {
                isOnlyLetter = false;
            }
        }
        return isOnlyLetter;
    }

    public Boolean isBoolean(String word) {
        return word.equals("true") || word.equals("false");
    }

    public Boolean checkNullParameter(String[] strings, Map<String, Object> stringObjectMap) {
        if (stringObjectMap.get(strings[0]) == null) {
            return stringObjectMap.get(strings[0]) == strings[1];
        } else if (stringObjectMap.get(strings[0]) != null) {
            return false;
        }
        return false;
    }

    public Boolean checkNotNullParameter(String[] strings, Map<String, Object> stringObjectMap) {
        if (stringObjectMap.get(strings[0]) == null) {
            return stringObjectMap.get(strings[0]) != strings[1];
        } else if(stringObjectMap.get(strings[0]) != null) {
            return false;
        }
        return false;
    }

    public Boolean checkNotNullParameterStrings(String[] strings, Map<String, Object> stringObjectMap) {
        if (stringObjectMap.get("lastName") == null) {
            return stringObjectMap.get("lastName") != strings[1];
        } else if(stringObjectMap.get("lastName") != null) {
            return false;
        }
        return false;
    }
}
