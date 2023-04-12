package com.digdes.school.datatypehandlers;

import com.digdes.school.FormatDataValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StringHandler {

    private final FormatDataValidator formatDataValidator = new FormatDataValidator();
    
    public List<Map<String, Object>> getMapString(String string, String regex, List<Map<String,Object>> result) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        String[] strings = string.split(regex);
        if (formatDataValidator.isString(strings[1])) {
            switch (regex) {
                case "=":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get("lastName") != null
                                && stringObjectMap.get("lastName").equals(strings[1])) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "!=":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNotNullParameterStrings(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (!stringObjectMap.get("lastName").equals((strings[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "like":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get("lastName") != null &&
                                 stringObjectMap.get("lastName").toString().contains(strings[1])) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "ilike":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get("lastName") != null &&
                                stringObjectMap.get("lastName").toString().toLowerCase().contains(strings[1].toLowerCase())) {
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
