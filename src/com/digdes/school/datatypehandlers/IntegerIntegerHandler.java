package com.digdes.school.datatypehandlers;

import com.digdes.school.FormatDataValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IntegerIntegerHandler {
    
    private final FormatDataValidator formatDataValidator = new FormatDataValidator();

    public List<Map<String, Object>> getMapIntegerIntegerMultipleConditionsAnd(String string, String string1, String regex,
                                                                               String regex1, List<Map<String,Object>> result) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        String[] strings = string.split(regex);
        String[] strings1 = string1.split(regex1);
        if (formatDataValidator.isLong(strings[1]) && formatDataValidator.isLong(strings1[1])) {
            switch (regex + ", " + regex1) {
                case "=, =":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) == Long.parseLong(strings[1])) &&
                                ((long) stringObjectMap.get(strings1[0]) == Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "=, !=":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNotNullParameter(strings1, stringObjectMap) &&
                                stringObjectMap.get(strings[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) == Long.parseLong(strings[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) == Long.parseLong(strings[1])) &&
                                ((long) stringObjectMap.get(strings1[0]) != Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "=, >":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) == Long.parseLong(strings[1])) &&
                                ((long) stringObjectMap.get(strings1[0]) > Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "=, <":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) == Long.parseLong(strings[1])) &&
                                ((long) stringObjectMap.get(strings1[0]) < Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "=, >=":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) == Long.parseLong(strings[1])) &&
                                ((long) stringObjectMap.get(strings1[0]) >= Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "=, <=":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) == Long.parseLong(strings[1])) &&
                                ((long) stringObjectMap.get(strings1[0]) <= Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case ">, =":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) > Long.parseLong(strings[1])) &&
                                ((long) stringObjectMap.get(strings1[0]) == Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case ">, !=":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNotNullParameter(strings1, stringObjectMap) &&
                                stringObjectMap.get(strings[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) > Long.parseLong(strings[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) > Long.parseLong(strings[1])) &&
                                ((long) stringObjectMap.get(strings1[0]) != Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case ">, >":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) > Long.parseLong(strings[1])) &&
                                ((long) stringObjectMap.get(strings1[0]) > Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case ">, <":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) > Long.parseLong(strings[1])) &&
                                ((long) stringObjectMap.get(strings1[0]) < Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case ">, >=":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) > Long.parseLong(strings[1])) &&
                                ((long) stringObjectMap.get(strings1[0]) >= Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case ">, <=":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) > Long.parseLong(strings[1])) &&
                                ((long) stringObjectMap.get(strings1[0]) <= Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "<, =":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) < Long.parseLong(strings[1])) &&
                                ((long) stringObjectMap.get(strings1[0]) == Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "<, !=":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNotNullParameter(strings1, stringObjectMap) &&
                                stringObjectMap.get(strings[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) < Long.parseLong(strings[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) < Long.parseLong(strings[1])) &&
                                ((long) stringObjectMap.get(strings1[0]) != Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "<, >":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) < Long.parseLong(strings[1])) &&
                                ((long) stringObjectMap.get(strings1[0]) > Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "<, <":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) < Long.parseLong(strings[1])) &&
                                ((long) stringObjectMap.get(strings1[0]) < Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "<, >=":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) < Long.parseLong(strings[1])) &&
                                ((long) stringObjectMap.get(strings1[0]) >= Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "<, <=":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) < Long.parseLong(strings[1])) &&
                                ((long) stringObjectMap.get(strings1[0]) <= Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case ">=, =":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) >= Long.parseLong(strings[1])) &&
                                ((long) stringObjectMap.get(strings1[0]) == Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case ">=, !=":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);

                            // проверка параметра !=
                        } else if (formatDataValidator.checkNotNullParameter(strings1, stringObjectMap) &&
                                stringObjectMap.get(strings[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) >= Long.parseLong(strings[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) >= Long.parseLong(strings[1])) &&
                                ((long) stringObjectMap.get(strings1[0]) != Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case ">=, >":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) >= Long.parseLong(strings[1])) &&
                                ((long) stringObjectMap.get(strings1[0]) > Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case ">=, <":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) >= Long.parseLong(strings[1])) &&
                                ((long) stringObjectMap.get(strings1[0]) < Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case ">=, >=":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) >= Long.parseLong(strings[1])) &&
                                ((long) stringObjectMap.get(strings1[0]) >= Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case ">=, <=":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) >= Long.parseLong(strings[1])) &&
                                ((long) stringObjectMap.get(strings1[0]) <= Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "<=, =":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) <= Long.parseLong(strings[1])) &&
                                ((long) stringObjectMap.get(strings1[0]) == Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "<=, !=":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);

                            // проверка параметра !=
                        } else if (formatDataValidator.checkNotNullParameter(strings1, stringObjectMap) &&
                                stringObjectMap.get(strings[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) <= Long.parseLong(strings[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) <= Long.parseLong(strings[1])) &&
                                ((long) stringObjectMap.get(strings1[0]) != Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "<=, >":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) <= Long.parseLong(strings[1])) &&
                                ((long) stringObjectMap.get(strings1[0]) > Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "<=, <":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) <= Long.parseLong(strings[1])) &&
                                ((long) stringObjectMap.get(strings1[0]) < Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "<=, >=":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) <= Long.parseLong(strings[1])) &&
                                ((long) stringObjectMap.get(strings1[0]) >= Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "<=, <=":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) <= Long.parseLong(strings[1])) &&
                                ((long) stringObjectMap.get(strings1[0]) <= Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;

                // БЛОК С ПАРАМЕТРАМИ !=
                case "!=, =":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNotNullParameter(strings, stringObjectMap) &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings1[0]) == Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) != Long.parseLong(strings[1])) &&
                                ((long) stringObjectMap.get(strings1[0]) == Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "!=, !=":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNotNullParameter(strings, stringObjectMap) &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings1[0]) != Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNotNullParameter(strings1, stringObjectMap) &&
                                stringObjectMap.get(strings[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) != Long.parseLong(strings[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNotNullParameter(strings, stringObjectMap) &&
                                formatDataValidator.checkNotNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) != Long.parseLong(strings[1])) &&
                                ((long) stringObjectMap.get(strings1[0]) != Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "!=, >":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNotNullParameter(strings, stringObjectMap) &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings1[0]) > Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) != Long.parseLong(strings[1])) &&
                                ((long) stringObjectMap.get(strings1[0]) > Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "!=, <":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNotNullParameter(strings, stringObjectMap) &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings1[0]) < Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) != Long.parseLong(strings[1])) &&
                                ((long) stringObjectMap.get(strings1[0]) < Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "!=, >=":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNotNullParameter(strings, stringObjectMap) &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings1[0]) >= Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) != Long.parseLong(strings[1])) &&
                                ((long) stringObjectMap.get(strings1[0]) >= Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "!=, <=":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNotNullParameter(strings, stringObjectMap) &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings1[0]) <= Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) != Long.parseLong(strings[1])) &&
                                ((long) stringObjectMap.get(strings1[0]) <= Long.parseLong(strings1[1]))) {
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

    public List<Map<String, Object>> getMapIntegerIntegerMultipleConditionsOr(String string, String string1, String regex,
                                                                               String regex1, List<Map<String,Object>> result) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        String[] strings = string.split(regex);
        String[] strings1 = string1.split(regex1);
        if (formatDataValidator.isLong(strings[1]) && formatDataValidator.isLong(strings1[1])) {
            switch (regex + ", " + regex1) {
                case "=, =":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) == Long.parseLong(strings[1]) ||
                                (long) stringObjectMap.get(strings1[0]) == Long.parseLong(strings1[1]))){
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) == null &&
                                ((long) stringObjectMap.get(strings[0]) == Long.parseLong(strings[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) == null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings1[0]) == Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "=, !=":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNotNullParameter(strings1, stringObjectMap) &&
                                stringObjectMap.get(strings[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) == Long.parseLong(strings[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) == Long.parseLong(strings[1]) ||
                                (long) stringObjectMap.get(strings1[0]) != Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                        else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) == null &&
                                ((long) stringObjectMap.get(strings[0]) == Long.parseLong(strings[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) == null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings1[0]) != Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) == null &&
                                stringObjectMap.get(strings1[0]) == null) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "=, >":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) == Long.parseLong(strings[1]) ||
                                        (long) stringObjectMap.get(strings1[0]) > Long.parseLong(strings1[1]))){
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) == null &&
                                ((long) stringObjectMap.get(strings[0]) == Long.parseLong(strings[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) == null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings1[0]) > Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "=, <":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) == Long.parseLong(strings[1]) ||
                                        (long) stringObjectMap.get(strings1[0]) < Long.parseLong(strings1[1]))){
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) == null &&
                                ((long) stringObjectMap.get(strings[0]) == Long.parseLong(strings[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) == null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings1[0]) < Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "=, >=":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) == Long.parseLong(strings[1]) ||
                                        (long) stringObjectMap.get(strings1[0]) >= Long.parseLong(strings1[1]))){
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) == null &&
                                ((long) stringObjectMap.get(strings[0]) == Long.parseLong(strings[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) == null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings1[0]) >= Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "=, <=":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) == Long.parseLong(strings[1]) ||
                                        (long) stringObjectMap.get(strings1[0]) <= Long.parseLong(strings1[1]))){
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) == null &&
                                ((long) stringObjectMap.get(strings[0]) == Long.parseLong(strings[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) == null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings1[0]) <= Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case ">, =":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) > Long.parseLong(strings[1]) ||
                                (long) stringObjectMap.get(strings1[0]) == Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) == null &&
                                ((long) stringObjectMap.get(strings[0]) > Long.parseLong(strings[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) == null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings1[0]) == Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case ">, !=":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNotNullParameter(strings1, stringObjectMap) &&
                                stringObjectMap.get(strings[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) > Long.parseLong(strings[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) > Long.parseLong(strings[1]) ||
                                        (long) stringObjectMap.get(strings1[0]) != Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) == null &&
                                ((long) stringObjectMap.get(strings[0]) > Long.parseLong(strings[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) == null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings1[0]) != Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) == null &&
                                stringObjectMap.get(strings1[0]) == null) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case ">, >":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) > Long.parseLong(strings[1]) ||
                                        (long) stringObjectMap.get(strings1[0]) > Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) == null &&
                                ((long) stringObjectMap.get(strings[0]) > Long.parseLong(strings[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) == null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings1[0]) > Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case ">, <":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) > Long.parseLong(strings[1]) ||
                                        (long) stringObjectMap.get(strings1[0]) < Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) == null &&
                                ((long) stringObjectMap.get(strings[0]) > Long.parseLong(strings[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) == null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings1[0]) < Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case ">, >=":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) > Long.parseLong(strings[1]) ||
                                        (long) stringObjectMap.get(strings1[0]) >= Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) == null &&
                                ((long) stringObjectMap.get(strings[0]) > Long.parseLong(strings[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) == null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings1[0]) >= Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case ">, <=":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) > Long.parseLong(strings[1]) ||
                                        (long) stringObjectMap.get(strings1[0]) <= Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) == null &&
                                ((long) stringObjectMap.get(strings[0]) > Long.parseLong(strings[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) == null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings1[0]) <= Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "<, =":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) < Long.parseLong(strings[1]) ||
                                        (long) stringObjectMap.get(strings1[0]) == Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) == null &&
                                ((long) stringObjectMap.get(strings[0]) < Long.parseLong(strings[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) == null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings1[0]) == Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "<, !=":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNotNullParameter(strings1, stringObjectMap) &&
                                stringObjectMap.get(strings[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) < Long.parseLong(strings[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) < Long.parseLong(strings[1]) ||
                                        (long) stringObjectMap.get(strings1[0]) != Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) == null &&
                                ((long) stringObjectMap.get(strings[0]) < Long.parseLong(strings[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) == null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings1[0]) != Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) == null &&
                                stringObjectMap.get(strings1[0]) == null) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "<, >":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) < Long.parseLong(strings[1]) ||
                                        (long) stringObjectMap.get(strings1[0]) > Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) == null &&
                                ((long) stringObjectMap.get(strings[0]) < Long.parseLong(strings[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) == null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings1[0]) > Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "<, <":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) < Long.parseLong(strings[1]) ||
                                        (long) stringObjectMap.get(strings1[0]) < Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) == null &&
                                ((long) stringObjectMap.get(strings[0]) < Long.parseLong(strings[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) == null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings1[0]) < Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "<, >=":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) < Long.parseLong(strings[1]) ||
                                        (long) stringObjectMap.get(strings1[0]) >= Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) == null &&
                                ((long) stringObjectMap.get(strings[0]) < Long.parseLong(strings[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) == null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings1[0]) >= Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "<, <=":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) < Long.parseLong(strings[1]) ||
                                        (long) stringObjectMap.get(strings1[0]) <= Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) == null &&
                                ((long) stringObjectMap.get(strings[0]) < Long.parseLong(strings[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) == null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings1[0]) <= Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case ">=, =":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) >= Long.parseLong(strings[1]) ||
                                        (long) stringObjectMap.get(strings1[0]) == Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) == null &&
                                ((long) stringObjectMap.get(strings[0]) >= Long.parseLong(strings[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) == null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings1[0]) == Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case ">=, !=":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);

                            // проверка параметра !=
                        } else if (formatDataValidator.checkNotNullParameter(strings1, stringObjectMap) &&
                                stringObjectMap.get(strings[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) >= Long.parseLong(strings[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) >= Long.parseLong(strings[1]) ||
                                        (long) stringObjectMap.get(strings1[0]) != Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) == null &&
                                ((long) stringObjectMap.get(strings[0]) >= Long.parseLong(strings[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) == null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings1[0]) != Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) == null &&
                                stringObjectMap.get(strings1[0]) == null) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case ">=, >":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) >= Long.parseLong(strings[1]) ||
                                        (long) stringObjectMap.get(strings1[0]) > Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) == null &&
                                ((long) stringObjectMap.get(strings[0]) >= Long.parseLong(strings[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) == null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings1[0]) > Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case ">=, <":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) >= Long.parseLong(strings[1]) ||
                                        (long) stringObjectMap.get(strings1[0]) < Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) == null &&
                                ((long) stringObjectMap.get(strings[0]) >= Long.parseLong(strings[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) == null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings1[0]) < Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case ">=, >=":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) >= Long.parseLong(strings[1]) ||
                                        (long) stringObjectMap.get(strings1[0]) >= Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) == null &&
                                ((long) stringObjectMap.get(strings[0]) >= Long.parseLong(strings[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) == null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings1[0]) >= Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case ">=, <=":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) >= Long.parseLong(strings[1]) ||
                                        (long) stringObjectMap.get(strings1[0]) <= Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) == null &&
                                ((long) stringObjectMap.get(strings[0]) >= Long.parseLong(strings[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) == null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings1[0]) <= Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "<=, =":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) <= Long.parseLong(strings[1]) ||
                                        (long) stringObjectMap.get(strings1[0]) == Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) == null &&
                                ((long) stringObjectMap.get(strings[0]) <= Long.parseLong(strings[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) == null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings1[0]) == Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "<=, !=":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNotNullParameter(strings1, stringObjectMap) &&
                                stringObjectMap.get(strings[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) <= Long.parseLong(strings[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) <= Long.parseLong(strings[1]) ||
                                        (long) stringObjectMap.get(strings1[0]) != Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) == null &&
                                ((long) stringObjectMap.get(strings[0]) <= Long.parseLong(strings[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) == null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings1[0]) != Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) == null &&
                                stringObjectMap.get(strings1[0]) == null) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "<=, >":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) <= Long.parseLong(strings[1]) ||
                                        (long) stringObjectMap.get(strings1[0]) > Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) == null &&
                                ((long) stringObjectMap.get(strings[0]) <= Long.parseLong(strings[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) == null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings1[0]) > Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "<=, <":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) <= Long.parseLong(strings[1]) ||
                                        (long) stringObjectMap.get(strings1[0]) < Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) == null &&
                                ((long) stringObjectMap.get(strings[0]) <= Long.parseLong(strings[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) == null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings1[0]) < Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "<=, >=":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) <= Long.parseLong(strings[1]) ||
                                        (long) stringObjectMap.get(strings1[0]) >= Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) == null &&
                                ((long) stringObjectMap.get(strings[0]) <= Long.parseLong(strings[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) == null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings1[0]) >= Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "<=, <=":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNullParameter(strings, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) <= Long.parseLong(strings[1]) ||
                                        (long) stringObjectMap.get(strings1[0]) <= Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) == null &&
                                ((long) stringObjectMap.get(strings[0]) <= Long.parseLong(strings[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) == null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings1[0]) <= Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;

                // БЛОК С ПАРАМЕТРАМИ !=
                case "!=, =":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNotNullParameter(strings, stringObjectMap) &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings1[0]) == Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) != Long.parseLong(strings[1]) ||
                                        (long) stringObjectMap.get(strings1[0]) == Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) == null &&
                                ((long) stringObjectMap.get(strings[0]) != Long.parseLong(strings[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) == null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings1[0]) == Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) == null &&
                                stringObjectMap.get(strings1[0]) == null) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "!=, !=":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNotNullParameter(strings, stringObjectMap) &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings1[0]) != Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNotNullParameter(strings1, stringObjectMap) &&
                                stringObjectMap.get(strings[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) != Long.parseLong(strings[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNotNullParameter(strings, stringObjectMap) &&
                                formatDataValidator.checkNotNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) != Long.parseLong(strings[1]) ||
                                        (long) stringObjectMap.get(strings1[0]) != Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) == null &&
                                ((long) stringObjectMap.get(strings[0]) != Long.parseLong(strings[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) == null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings1[0]) != Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) == null &&
                                stringObjectMap.get(strings1[0]) == null) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "!=, >":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNotNullParameter(strings, stringObjectMap) &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings1[0]) > Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) != Long.parseLong(strings[1]) ||
                                        (long) stringObjectMap.get(strings1[0]) > Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) == null &&
                                ((long) stringObjectMap.get(strings[0]) != Long.parseLong(strings[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) == null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings1[0]) > Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) == null &&
                                stringObjectMap.get(strings1[0]) == null) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "!=, <":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNotNullParameter(strings, stringObjectMap) &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings1[0]) < Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) != Long.parseLong(strings[1]) ||
                                        (long) stringObjectMap.get(strings1[0]) < Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) == null &&
                                ((long) stringObjectMap.get(strings[0]) != Long.parseLong(strings[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) == null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings1[0]) < Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) == null &&
                                stringObjectMap.get(strings1[0]) == null) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "!=, >=":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNotNullParameter(strings, stringObjectMap) &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings1[0]) >= Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) != Long.parseLong(strings[1]) ||
                                        (long) stringObjectMap.get(strings1[0]) >= Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) == null &&
                                ((long) stringObjectMap.get(strings[0]) != Long.parseLong(strings[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) == null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings1[0]) >= Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) == null &&
                                stringObjectMap.get(strings1[0]) == null) {
                            mapList.add(stringObjectMap);
                        }
                    }
                    break;
                case "!=, <=":
                    for (Map<String, Object> stringObjectMap : result) {
                        if (formatDataValidator.checkNotNullParameter(strings, stringObjectMap) &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings1[0]) <= Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (formatDataValidator.checkNullParameter(strings1, stringObjectMap)) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings[0]) != Long.parseLong(strings[1]) ||
                                        (long) stringObjectMap.get(strings1[0]) <= Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) != null &&
                                stringObjectMap.get(strings1[0]) == null &&
                                ((long) stringObjectMap.get(strings[0]) != Long.parseLong(strings[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) == null &&
                                stringObjectMap.get(strings1[0]) != null &&
                                ((long) stringObjectMap.get(strings1[0]) <= Long.parseLong(strings1[1]))) {
                            mapList.add(stringObjectMap);
                        } else if (stringObjectMap.get(strings[0]) == null &&
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
