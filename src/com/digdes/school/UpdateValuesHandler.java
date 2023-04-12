package com.digdes.school;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class UpdateValuesHandler {

    FormatDataValidator formatDataValidator = new FormatDataValidator();

    public void updateValuesWhereParameter(String[] s, List<Map<String,Object>> result, String[] stringConditional,
                                           String conditional1, String conditional2) {
        switch (conditional1 + ", " + conditional2) {
            case "id=, age=":
            case "age=, age=":
            case "id=, id=":
            case "age=, id=":
                if (formatDataValidator.isLong(s[1]) && formatDataValidator.isLong(stringConditional[1])) {
                    for (Map<String, Object> stringObjectMap : result) {
                        if (stringObjectMap.get(stringConditional[0]) == null && Objects.equals(stringConditional[1], "null")) {
                            stringObjectMap.put(s[0], Long.parseLong(s[1]));
                        } else if (!Objects.equals(stringConditional[1], "null") && stringObjectMap.get(stringConditional[0]) != null
                                && (long) stringObjectMap.get(stringConditional[0]) == Long.parseLong(stringConditional[1])) {
                            stringObjectMap.put(s[0], Long.parseLong(s[1]));
                        }
                    }
                } else {
                    throw new NumberFormatException("invalid data update format!");
                }
                break;
            case "coast=, age=":
            case "coast=, id=":
                if (formatDataValidator.isDouble(s[1]) && formatDataValidator.isLong(stringConditional[1])) {
                    for (Map<String, Object> stringObjectMap : result) {
                        if (stringObjectMap.get(stringConditional[0]) == null && Objects.equals(stringConditional[1], "null")) {
                            stringObjectMap.put(s[0], Double.parseDouble(s[1]));
                        } else if (!Objects.equals(stringConditional[1], "null") && stringObjectMap.get(stringConditional[0]) != null
                                && (long) stringObjectMap.get(stringConditional[0]) == Long.parseLong(stringConditional[1])) {
                            stringObjectMap.put(s[0], Double.parseDouble(s[1]));
                        }
                    }
                } else {
                    throw new NumberFormatException("invalid data update format!");
                }
                break;
            case "lastname=, age=":
            case "lastname=, id=":
                if (formatDataValidator.isString(s[1]) && formatDataValidator.isLong(stringConditional[1])) {
                    for (Map<String, Object> stringObjectMap : result) {
                        if (stringObjectMap.get(stringConditional[0]) == null && Objects.equals(stringConditional[1], "null")) {
                            stringObjectMap.put("lastName", s[1]);
                        } else if (!Objects.equals(stringConditional[1], "null") && stringObjectMap.get(stringConditional[0]) != null
                                && (long) stringObjectMap.get(stringConditional[0]) == Long.parseLong(stringConditional[1])) {
                            stringObjectMap.put("lastName", s[1]);
                        }
                    }
                } else {
                    throw new NumberFormatException("invalid data update format!");
                }
                break;
            case "active=, age=":
            case "active=, id=":
                if (formatDataValidator.isBoolean(s[1]) && formatDataValidator.isLong(stringConditional[1])) {
                    for (Map<String, Object> stringObjectMap : result) {
                        if (stringObjectMap.get(stringConditional[0]) == null && Objects.equals(stringConditional[1], "null")) {
                            stringObjectMap.put(s[0], Boolean.parseBoolean(s[1]));
                        } else if (!Objects.equals(stringConditional[1], "null") && stringObjectMap.get(stringConditional[0]) != null
                                && (long) stringObjectMap.get(stringConditional[0]) == Long.parseLong(stringConditional[1])) {
                            stringObjectMap.put(s[0], Boolean.parseBoolean(s[1]));
                        }
                    }
                } else {
                    throw new NumberFormatException("invalid data update format!");
                }
                break;
            case "id=, coast=":
            case "age=, coast=":
                if (formatDataValidator.isLong(s[1]) && formatDataValidator.isDouble(stringConditional[1])) {
                    for (Map<String, Object> stringObjectMap : result) {
                        if (stringObjectMap.get(stringConditional[0]) == null && Objects.equals(stringConditional[1], "null")) {
                            stringObjectMap.put(s[0], Long.parseLong(s[1]));
                        } else if (!Objects.equals(stringConditional[1], "null") && stringObjectMap.get(stringConditional[0]) != null
                                && (double) stringObjectMap.get(stringConditional[0]) == Double.parseDouble(stringConditional[1])) {
                            stringObjectMap.put(s[0], Long.parseLong(s[1]));
                        }
                    }
                } else {
                    throw new NumberFormatException("invalid data update format!");
                }
                break;
            case "coast=, coast=":
                if (formatDataValidator.isDouble(s[1]) && formatDataValidator.isDouble(stringConditional[1])) {
                    for (Map<String, Object> stringObjectMap : result) {
                        if (stringObjectMap.get(stringConditional[0]) == null && Objects.equals(stringConditional[1], "null")) {
                            stringObjectMap.put(s[0], Double.parseDouble(s[1]));
                        } else if (!Objects.equals(stringConditional[1], "null") && stringObjectMap.get(stringConditional[0]) != null
                                && (double) stringObjectMap.get(stringConditional[0]) == Double.parseDouble(stringConditional[1])) {
                            stringObjectMap.put(s[0], Double.parseDouble(s[1]));
                        }
                    }
                } else {
                    throw new NumberFormatException("invalid data update format!");
                }
                break;
            case "lastname=, coast=":
                if (formatDataValidator.isString(s[1]) && formatDataValidator.isDouble(stringConditional[1])) {
                    for (Map<String, Object> stringObjectMap : result) {
                        if (stringObjectMap.get(stringConditional[0]) == null && Objects.equals(stringConditional[1], "null")) {
                            stringObjectMap.put("lastName", s[1]);
                        } else if (!Objects.equals(stringConditional[1], "null") && stringObjectMap.get(stringConditional[0]) != null
                                && (double) stringObjectMap.get(stringConditional[0]) == Double.parseDouble(stringConditional[1])) {
                            stringObjectMap.put("lastName", s[1]);
                        }
                    }
                } else {
                    throw new NumberFormatException("invalid data update format!");
                }
                break;
            case "active=, coast=":
                if (formatDataValidator.isBoolean(s[1]) && formatDataValidator.isDouble(stringConditional[1])) {
                    for (Map<String, Object> stringObjectMap : result) {
                        if (stringObjectMap.get(stringConditional[0]) == null && Objects.equals(stringConditional[1], "null")) {
                            stringObjectMap.put(s[0], Boolean.parseBoolean(s[1]));
                        } else if (!Objects.equals(stringConditional[1], "null") && stringObjectMap.get(stringConditional[0]) != null
                                && (double) stringObjectMap.get(stringConditional[0]) == Double.parseDouble(stringConditional[1])) {
                            stringObjectMap.put(s[0], Boolean.parseBoolean(s[1]));
                        }
                    }
                } else {
                    throw new NumberFormatException("invalid data update format!");
                }
                break;
            case "id=, lastname=":
            case "age=, lastname=":
                if (formatDataValidator.isLong(s[1]) && formatDataValidator.isString(stringConditional[1])) {
                    for (Map<String, Object> stringObjectMap : result) {
                        if (stringObjectMap.get("lastName") == null && Objects.equals(stringConditional[1], "null")) {
                            stringObjectMap.put(s[0], Long.parseLong(s[1]));
                        } else if (!Objects.equals(stringConditional[1], "null") && stringObjectMap.get("lastName") != null
                                && stringObjectMap.get("lastName").equals(stringConditional[1])) {
                            stringObjectMap.put(s[0], Long.parseLong(s[1]));
                        }
                    }
                } else {
                    throw new NumberFormatException("invalid data update format!");
                }
                break;
            case "coast=, lastname=":
                if (formatDataValidator.isDouble(s[1]) && formatDataValidator.isString(stringConditional[1])) {
                    for (Map<String, Object> stringObjectMap : result) {
                        if (stringObjectMap.get("lastName") == null && Objects.equals(stringConditional[1], "null")) {
                            stringObjectMap.put(s[0], Double.parseDouble(s[1]));
                        } else if (!Objects.equals(stringConditional[1], "null") && stringObjectMap.get("lastName") != null
                                && stringObjectMap.get("lastName").equals(stringConditional[1])) {
                            stringObjectMap.put(s[0], Double.parseDouble(s[1]));
                        }
                    }
                } else {
                    throw new NumberFormatException("invalid data update format!");
                }
                break;
            case "lastname=, lastname=":
                if (formatDataValidator.isString(s[1]) && formatDataValidator.isString(stringConditional[1])) {
                    for (Map<String, Object> stringObjectMap : result) {
                        if (stringObjectMap.get("lastName") == null && Objects.equals(stringConditional[1], "null")) {
                            stringObjectMap.put("lastName", s[1]);
                        } else if (!Objects.equals(stringConditional[1], "null") && stringObjectMap.get("lastName") != null
                                && stringObjectMap.get("lastName").equals(stringConditional[1])) {
                            stringObjectMap.put("lastName", s[1]);
                        }
                    }
                } else {
                    throw new NumberFormatException("invalid data update format!");
                }
                break;
            case "active=, lastname=":
                if (formatDataValidator.isBoolean(s[1]) && formatDataValidator.isString(stringConditional[1])) {
                    for (Map<String, Object> stringObjectMap : result) {
                        if (stringObjectMap.get("lastName") == null && Objects.equals(stringConditional[1], "null")) {
                            stringObjectMap.put(s[0], Boolean.parseBoolean(s[1]));
                        } else if (!Objects.equals(stringConditional[1], "null") && stringObjectMap.get("lastName") != null
                                && stringObjectMap.get("lastName").equals(stringConditional[1])) {
                            stringObjectMap.put(s[0], Boolean.parseBoolean(s[1]));
                        }
                    }
                } else {
                    throw new NumberFormatException("invalid data update format!");
                }
                break;
            case "id=, active=":
            case "age=, active=":
                if (formatDataValidator.isLong(s[1]) && formatDataValidator.isBoolean(stringConditional[1])) {
                    for (Map<String, Object> stringObjectMap : result) {
                        if (stringObjectMap.get(stringConditional[0]) == null && Objects.equals(stringConditional[1], "null")) {
                            stringObjectMap.put(s[0], Long.parseLong(s[1]));
                        } else if (!Objects.equals(stringConditional[1], "null") && stringObjectMap.get(stringConditional[0]) != null
                                && (boolean) stringObjectMap.get(stringConditional[0]) == Boolean.parseBoolean(stringConditional[1])) {
                            stringObjectMap.put(s[0], Long.parseLong(s[1]));
                        }
                    }
                } else {
                    throw new NumberFormatException("invalid data update format!");
                }
                break;
            case "coast=, active=":
                if (formatDataValidator.isDouble(s[1]) && formatDataValidator.isBoolean(stringConditional[1])) {
                    for (Map<String, Object> stringObjectMap : result) {
                        if (stringObjectMap.get(stringConditional[0]) == null && Objects.equals(stringConditional[1], "null")) {
                            stringObjectMap.put(s[0], Double.parseDouble(s[1]));
                        } else if (!Objects.equals(stringConditional[1], "null") && stringObjectMap.get(stringConditional[0]) != null
                                && (boolean) stringObjectMap.get(stringConditional[0]) == Boolean.parseBoolean(stringConditional[1])) {
                            stringObjectMap.put(s[0], Double.parseDouble(s[1]));
                        }
                    }
                } else {
                    throw new NumberFormatException("invalid data update format!");
                }
                break;
            case "lastname=, active=":
                if (formatDataValidator.isString(s[1]) && formatDataValidator.isBoolean(stringConditional[1])) {
                    for (Map<String, Object> stringObjectMap : result) {
                        if (stringObjectMap.get(stringConditional[0]) == null && Objects.equals(stringConditional[1], "null")) {
                            stringObjectMap.put("lastName", s[1]);
                        } else if (!Objects.equals(stringConditional[1], "null") && stringObjectMap.get(stringConditional[0]) != null
                                && (boolean) stringObjectMap.get(stringConditional[0]) == Boolean.parseBoolean(stringConditional[1])) {
                            stringObjectMap.put("lastName", s[1]);
                        }
                    }
                } else {
                    throw new NumberFormatException("invalid data update format!");
                }
                break;
            case "active=, active=":
                if (formatDataValidator.isBoolean(s[1]) && formatDataValidator.isBoolean(stringConditional[1])) {
                    for (Map<String, Object> stringObjectMap : result) {
                        if (stringObjectMap.get(stringConditional[0]) == null && Objects.equals(stringConditional[1], "null")) {
                            stringObjectMap.put(s[0], Boolean.parseBoolean(s[1]));
                        } else if (!Objects.equals(stringConditional[1], "null") && stringObjectMap.get(stringConditional[0]) != null
                                && (boolean) stringObjectMap.get(stringConditional[0]) == Boolean.parseBoolean(stringConditional[1])) {
                            stringObjectMap.put(s[0], Boolean.parseBoolean(s[1]));
                        }
                    }
                } else {
                    throw new NumberFormatException("invalid data update format!");
                }
                break;
        }
    }
}
