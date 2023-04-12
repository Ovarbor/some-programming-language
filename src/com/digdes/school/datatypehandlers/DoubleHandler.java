package com.digdes.school.datatypehandlers;

import com.digdes.school.FormatDataValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DoubleHandler {
    
    private final FormatDataValidator formatDataValidator = new FormatDataValidator();

    public List<Map<String, Object>> getMapDouble(String string, String regex, List<Map<String,Object>> result) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        String[] strings = string.split(regex);
        if (formatDataValidator.isDouble(strings[1])) {
            switch (regex) {
                case "=":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null
                                && (double) stringObjectMap.get(strings[0]) == Double.parseDouble(strings[1])) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "!=":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNotNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if ((double) stringObjectMap.get(strings[0]) != Double.parseDouble(strings[1])) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case ">":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                (double) stringObjectMap.get(strings[0]) > Double.parseDouble(strings[1])) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "<":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                (double) stringObjectMap.get(strings[0]) < Double.parseDouble(strings[1])) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case ">=":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                (double) stringObjectMap.get(strings[0]) >= Double.parseDouble(strings[1])) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "<=":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                (double) stringObjectMap.get(strings[0]) <= Double.parseDouble(strings[1])) {
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
