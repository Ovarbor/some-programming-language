package com.digdes.school.datatypehandlers;

import com.digdes.school.FormatDataValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BooleanHandler {

    private final FormatDataValidator formatDataValidator = new FormatDataValidator();
    
    public List<Map<String, Object>> getMapBoolean(String string, String regex, List<Map<String,Object>> result) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        String[] strings = string.split(regex);
        if (formatDataValidator.isBoolean(strings[1])) {
            switch (regex) {
                case "=":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null
                                && (boolean) stringObjectMap.get(strings[0]) == Boolean.parseBoolean(strings[1])) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "!=":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNotNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if ((boolean) stringObjectMap.get(strings[0]) != Boolean.parseBoolean(strings[1])) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
            }
        } else {
            throw new NumberFormatException("invalid data select format!");
        }
        return mapList;
    }
}
