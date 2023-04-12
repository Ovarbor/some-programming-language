package com.digdes.school.datatypehandlers;

import com.digdes.school.FormatDataValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IntegerHandler {

    private final FormatDataValidator formatDataValidator = new FormatDataValidator();
    
    public List<Map<String, Object>> getMapInteger(String string, String regex, List<Map<String,Object>> result) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        String[] strings = string.split(regex);
        if (formatDataValidator.isLong(strings[1])) {
            switch (regex) {
                case "=":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null
                                && (long) stringObjectMap.get(strings[0]) == Long.parseLong(strings[1])) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "!=":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNotNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if ((long) stringObjectMap.get(strings[0]) != Long.parseLong(strings[1])) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case ">":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                (long) stringObjectMap.get(strings[0]) > Long.parseLong(strings[1])) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "<":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                (long) stringObjectMap.get(strings[0]) < Long.parseLong(strings[1])) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case ">=":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                (long) stringObjectMap.get(strings[0]) >= Long.parseLong(strings[1])) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "<=":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                (long) stringObjectMap.get(strings[0]) <= Long.parseLong(strings[1])) {
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
