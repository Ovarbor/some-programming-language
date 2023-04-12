package com.digdes.school.datatypehandlers;
import com.digdes.school.FormatDataValidator;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StringBooleanHandler {
    
    private final FormatDataValidator formatDataValidator = new FormatDataValidator();

    public List<Map<String, Object>> getMapStringBooleanMultipleConditionsAnd(String string, String string1, String regex,
                                                                              String regex1, List<Map<String,Object>> result) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        String[] strings = string.split(regex);
        String[] strings1 = string1.split(regex1);
        if (formatDataValidator.isString(strings[1]) && formatDataValidator.isBoolean(strings1[1])) {
            switch (regex + ", " + regex1) {
                case "=, =":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get("lastName") != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                (stringObjectMap.get("lastName").equals(strings[1])) &&
                                ((boolean) stringObjectMap.get(strings1[0]) == Boolean.parseBoolean(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "=, !=":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNotNullParameter(strings1, stringObjectMap) &&
                                stringObjectMap.get("lastName") != null &&
                                (stringObjectMap.get("lastName").equals(strings[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get("lastName") != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                (stringObjectMap.get("lastName").equals(strings[1])) &&
                                ((boolean) stringObjectMap.get(strings1[0]) != Boolean.parseBoolean(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "!=, =":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNotNullParameterStrings(strings, stringObjectMap) &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((boolean) stringObjectMap.get(strings1[0]) == Boolean.parseBoolean(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get("lastName") != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                (!stringObjectMap.get("lastName").equals(strings[1])) &&
                                ((boolean) stringObjectMap.get(strings1[0]) == Boolean.parseBoolean(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "!=, !=":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNotNullParameterStrings(strings, stringObjectMap) &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((boolean) stringObjectMap.get(strings1[0]) != Boolean.parseBoolean(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNotNullParameter(strings1, stringObjectMap) &&
                                stringObjectMap.get("lastName") != null &&
                                (!stringObjectMap.get("lastName").equals(strings[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNotNullParameterStrings(strings, stringObjectMap) &&
                                formatDataValidator.checkNotNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get("lastName") != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                (!stringObjectMap.get("lastName").equals(strings[1])) &&
                                ((boolean) stringObjectMap.get(strings1[0]) != Boolean.parseBoolean(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "like, =":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get("lastName") != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                (stringObjectMap.get("lastName").toString().contains(strings[1])) &&
                                ((boolean) stringObjectMap.get(strings1[0]) == Boolean.parseBoolean(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "like, !=":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNotNullParameter(strings1, stringObjectMap) &&
                                stringObjectMap.get("lastName") != null &&
                                (stringObjectMap.get("lastName").toString().contains(strings[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get("lastName") != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                (stringObjectMap.get("lastName").toString().contains(strings[1])) &&
                                ((boolean) stringObjectMap.get(strings1[0]) != Boolean.parseBoolean(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "ilike, =":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get("lastName") != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                (stringObjectMap.get("lastName").toString().toLowerCase().contains(strings[1].toLowerCase())) &&
                                ((boolean) stringObjectMap.get(strings1[0]) == Boolean.parseBoolean(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "ilike, !=":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNotNullParameter(strings1, stringObjectMap) &&
                                stringObjectMap.get("lastName") != null &&
                                (stringObjectMap.get("lastName").toString().toLowerCase().contains(strings[1].toLowerCase()))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get("lastName") != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                (stringObjectMap.get("lastName").toString().toLowerCase().contains(strings[1].toLowerCase())) &&
                                ((boolean) stringObjectMap.get(strings1[0]) != Boolean.parseBoolean(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
            }
        } else  {
            throw new NumberFormatException("invalid data select format!");
        }
        return mapList;
    }

    public List<Map<String, Object>> getMapStringBooleanMultipleConditionsOr(String string, String string1, String regex,
                                                                             String regex1, List<Map<String,Object>> result) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        String[] strings = string.split(regex);
        String[] strings1 = string1.split(regex1);
        if (formatDataValidator.isString(strings[1]) && formatDataValidator.isBoolean(strings1[1])) {
            switch (regex + ", " + regex1) {
                case "=, =":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get("lastName") != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                (stringObjectMap.get("lastName").equals(strings[1]) ||
                                        (boolean) stringObjectMap.get(strings1[0]) == Boolean.parseBoolean(strings1[1]))){
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get("lastName") != null &&
                                stringObjectMap.get(strings1[0]) == null &&
                                (stringObjectMap.get("lastName").equals(strings[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get("lastName") == null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((boolean) stringObjectMap.get(strings1[0]) == Boolean.parseBoolean(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "=, !=":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNotNullParameter(strings1, stringObjectMap) &&
                                stringObjectMap.get("lastName") != null &&
                                (stringObjectMap.get("lastName").equals(strings[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get("lastName") != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                (stringObjectMap.get("lastName").equals(strings[1]) ||
                                        (boolean) stringObjectMap.get(strings1[0]) != Boolean.parseBoolean(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get("lastName") != null &&
                                stringObjectMap.get(strings1[0]) == null &&
                                (stringObjectMap.get("lastName").equals(strings[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get("lastName") == null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((boolean) stringObjectMap.get(strings1[0]) != Boolean.parseBoolean(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get("lastName") == null &&
                                stringObjectMap.get(strings1[0]) == null) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "like, =":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get("lastName") != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                (stringObjectMap.get("lastName").toString().contains(strings[1]) ||
                                        (boolean) stringObjectMap.get(strings1[0]) == Boolean.parseBoolean(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get("lastName") != null &&
                                stringObjectMap.get(strings1[0]) == null &&
                                (stringObjectMap.get("lastName").toString().contains(strings[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get("lastName") == null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((boolean) stringObjectMap.get(strings1[0]) == Boolean.parseBoolean(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "like, !=":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNotNullParameter(strings1, stringObjectMap) &&
                                stringObjectMap.get("lastName") != null &&
                                (stringObjectMap.get("lastName").toString().contains(strings[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get("lastName") != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                (stringObjectMap.get("lastName").toString().contains(strings[1]) ||
                                        (boolean) stringObjectMap.get(strings1[0]) != Boolean.parseBoolean(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get("lastName") != null &&
                                stringObjectMap.get(strings1[0]) == null &&
                                (stringObjectMap.get("lastName").toString().contains(strings[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get("lastName") == null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((boolean) stringObjectMap.get(strings1[0]) != Boolean.parseBoolean(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get("lastName") == null &&
                                stringObjectMap.get(strings1[0]) == null) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "ilike, =":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get("lastName") != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                (stringObjectMap.get("lastName").toString().toLowerCase().contains(strings[1].toLowerCase()) ||
                                        (boolean) stringObjectMap.get(strings1[0]) == Boolean.parseBoolean(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get("lastName") != null &&
                                stringObjectMap.get(strings1[0]) == null &&
                                (stringObjectMap.get("lastName").toString().toLowerCase().contains(strings[1].toLowerCase()))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get("lastName") == null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((boolean) stringObjectMap.get(strings1[0]) == Boolean.parseBoolean(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "ilike, !=":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNotNullParameter(strings1, stringObjectMap) &&
                                stringObjectMap.get("lastName") != null &&
                                (stringObjectMap.get("lastName").toString().toLowerCase().contains(strings[1].toLowerCase()))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get("lastName") != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                (stringObjectMap.get("lastName").toString().toLowerCase().contains(strings[1].toLowerCase()) ||
                                        (boolean) stringObjectMap.get(strings1[0]) != Boolean.parseBoolean(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get("lastName") != null &&
                                stringObjectMap.get(strings1[0]) == null &&
                                (stringObjectMap.get("lastName").toString().toLowerCase().contains(strings[1].toLowerCase()))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get("lastName") == null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((boolean) stringObjectMap.get(strings1[0]) != Boolean.parseBoolean(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get("lastName") == null &&
                                stringObjectMap.get(strings1[0]) == null) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                // БЛОК С ПАРАМЕТРАМИ !=
                case "!=, =":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNotNullParameterStrings(strings, stringObjectMap) &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((boolean) stringObjectMap.get(strings1[0]) == Boolean.parseBoolean(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get("lastName") != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                (!stringObjectMap.get("lastName").equals(strings[1]) ||
                                        (boolean) stringObjectMap.get(strings1[0]) == Boolean.parseBoolean(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get("lastName") != null &&
                                stringObjectMap.get(strings1[0]) == null &&
                                (!stringObjectMap.get("lastName").equals(strings[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get("lastName") == null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((boolean) stringObjectMap.get(strings1[0]) == Boolean.parseBoolean(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get("lastName") == null &&
                                stringObjectMap.get(strings1[0]) == null) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "!=, !=":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNotNullParameterStrings(strings, stringObjectMap) &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((boolean) stringObjectMap.get(strings1[0]) != Boolean.parseBoolean(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNotNullParameter(strings1, stringObjectMap) &&
                                stringObjectMap.get("lastName") != null &&
                                (!stringObjectMap.get("lastName").equals(strings[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNotNullParameterStrings(strings, stringObjectMap) &&
                                formatDataValidator.checkNotNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get("lastName") != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                (!stringObjectMap.get("lastName").equals(strings[1]) ||
                                        (boolean) stringObjectMap.get(strings1[0]) != Boolean.parseBoolean(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get("lastName") != null &&
                                stringObjectMap.get(strings1[0]) == null &&
                                (!stringObjectMap.get("lastName").equals(strings[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get("lastName") == null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((boolean) stringObjectMap.get(strings1[0]) != Boolean.parseBoolean(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get("lastName") == null &&
                                stringObjectMap.get(strings1[0]) == null) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
            }
        } else  {
            throw new NumberFormatException("invalid data select format!");
        }
        return mapList;
    }
}
