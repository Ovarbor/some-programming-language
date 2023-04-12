package com.digdes.school;

import com.digdes.school.datatypehandlers.*;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaSchoolStarter {

  private Map<String,Object> row;
  private final List<Map<String,Object>> result = new ArrayList<>();

  private final IntegerIntegerHandler integerIntegerHandler = new IntegerIntegerHandler();
  private final IntegerDoubleHandler integerDoubleHandler = new IntegerDoubleHandler();
  private final DoubleIntegerHandler doubleIntegerHandler = new DoubleIntegerHandler();
  private final DoubleBooleanHandler doubleBooleanHandler = new DoubleBooleanHandler();
  private final DoubleStringHandler doubleStringHandler = new DoubleStringHandler();
  private final IntegerBooleanHandler integerBooleanHandler = new IntegerBooleanHandler();
  private final IntegerStringHandler integerStringHandler = new IntegerStringHandler();
  private final BooleanIntegerHandler booleanIntegerHandler = new BooleanIntegerHandler();
  private final BooleanDoubleHandler booleanDoubleHandler = new BooleanDoubleHandler();
  private final BooleanStringHandler booleanStringHandler = new BooleanStringHandler();
  private final StringIntegerHandler stringIntegerHandler = new StringIntegerHandler();
  private final StringDoubleHandler stringDoubleHandler = new StringDoubleHandler();
  private final StringBooleanHandler stringBooleanHandler = new StringBooleanHandler();
  private final IntegerHandler integerHandler = new IntegerHandler();
  private final DoubleHandler doubleHandler = new DoubleHandler();
  private final StringHandler stringHandler = new StringHandler();
  private final BooleanHandler booleanHandler = new BooleanHandler();
  private final FormatDataValidator formatDataValidator = new FormatDataValidator();
  private final UpdateValuesHandler updateValuesHandler = new UpdateValuesHandler();

  public JavaSchoolStarter() {

  }

  private String processString(String request) {
      Pattern p = Pattern.compile("([A-Z])");
      Matcher m = p.matcher(request);
      StringBuilder sb = new StringBuilder();
      while ( m.find() ) {
          m.appendReplacement(sb, m.group(1).toLowerCase());
      }
      m.appendTail(sb);
      return sb.toString();
  }

  public void insert(String request) {
      String processedRequest = processString(request);
      if (processedRequest.contains("insert values ")) {
          row = new HashMap<>();
          String newString = processedRequest.replace("insert values", " ");
          nullValuesValidation(newString);
          String[] strings = newString.trim().replaceAll("[ ']", "").split(",");
          for (String string : strings) {
              if (string.contains("id")) {
                  String[] strings1 = string.split("=");
                  insertIntegerValidation(strings1, row);
              } else if (string.contains("lastname")) {
                  String[] strings1 = string.split("=");
                  insertStringValidation(strings1, row);
              } else if (string.contains("age")) {
                  String[] strings1 = string.split("=");
                  insertIntegerValidation(strings1, row);
                  row.put(strings1[0], Long.parseLong(strings1[1]));
              } else if (string.contains("coast")) {
                  String[] strings1 = string.split("=");
                  insertDoubleValidation(strings1, row);
              } else if (string.contains("active")) {
                  String[] strings1 = string.split("=");
                  insertBooleanValidation(strings1, row);
              } else {
                  throw new RuntimeException("The request contains an invalid parameter name");
              }
          }
          result.add(row);
      } else {
          throw new RuntimeException("Incorrect method request");
      }
  }

  public void delete(String request) {
      if (processString(request).trim().equals("delete")) {
          result.clear();
      } else if (processString(request).contains("delete ") && processString(request).contains("where")) {
          String newString = processString(request).replace("delete ", "").replace("where", "");
          String[] strings = newString.trim().replaceAll("[ ']", "").split("where");
          for (String string : strings) {
              if (string.contains("id=")) {
                  String[] strings1 = string.split("=");
                  if (formatDataValidator.isLong(strings1[1])) {
                      result.removeIf(stringObjectMap -> (long) stringObjectMap.get(strings1[0]) == Long.parseLong(strings1[1]));
                  } else {
                      throw  new NumberFormatException("invalid data delete format!");
                  }
              } else if (string.contains("age=")) {
                  String[] strings1 = string.split("=");
                  if (formatDataValidator.isLong(strings1[1])) {
                      result.removeIf(stringObjectMap -> (long) stringObjectMap.get(strings1[0]) == Long.parseLong(strings1[1]));
                  } else {
                      throw  new NumberFormatException("invalid data delete format!");
                  }
              } else if (string.contains("coast=")) {
                  String[] strings1 = string.split("=");
                  if (formatDataValidator.isDouble(strings1[1])) {
                      result.removeIf(stringObjectMap -> (double) stringObjectMap.get(strings1[0]) == Double.parseDouble(strings1[1]));
                  } else {
                      throw  new NumberFormatException("invalid data delete format!");
                  }
              } else if (string.contains("lastname")) {
                  String[] strings1 = string.split("=");
                  if (formatDataValidator.isString(strings1[1])) {
                      result.removeIf(stringObjectMap -> stringObjectMap.get("lastName").equals(strings1[1]));
                  } else {
                      throw  new NumberFormatException("invalid data delete format!");
                  }
              } else if (string.contains("active")) {
                  String[] strings1 = string.split("=");
                  if (formatDataValidator.isBoolean(strings1[1])) {
                      result.removeIf(stringObjectMap -> (boolean) stringObjectMap.get(strings1[0]) == Boolean.parseBoolean(strings1[1]));
                  } else {
                      throw new NumberFormatException("invalid data delete format!");
                  }
              }
          }
      }
  }

  public void update(String request) {
      if (processString(request).contains("update values ") && !processString(request).contains("and") && !processString(request).contains("or")
              && !processString(request).contains("where")) {
          String newString = processString(request).replace("update values", " ");
          String[] strings = newString.trim().replaceAll("[ ']", "").split(",");
          for (String string : strings) {
              if (string.contains("id=")) {
                  String[] strings1 = string.split("=");
                      if (formatDataValidator.isLong(strings1[1])) {
                          for (Map<String, Object> stringObjectMap : result) {
                              if (strings1[1].equals("null")) {
                                  stringObjectMap.put(strings1[0], null);
                              } else {
                                  stringObjectMap.put(strings1[0], Long.parseLong(strings1[1]));
                              }
                          }
                      } else {
                          throw  new NumberFormatException("invalid data update format!");
                      }
              } else if (string.contains("age=")) {
                  String[] strings1 = string.split("=");
                  if (formatDataValidator.isLong(strings1[1])) {
                      for (Map<String, Object> stringObjectMap : result) {
                          if (strings1[1].equals("null")) {
                              stringObjectMap.put(strings1[0], null);
                          } else {
                              stringObjectMap.put(strings1[0], Long.parseLong(strings1[1]));
                          }
                      }
                  } else {
                          throw  new NumberFormatException("invalid data update format!");
                      }
              } else if (string.contains("coast=")) {
                      String[] strings1 = string.split("=");
                        if (formatDataValidator.isDouble(strings1[1])) {
                      for (Map<String, Object> stringObjectMap : result) {
                          if (strings1[1].equals("null")) {
                              stringObjectMap.put(strings1[0], null);
                          } else {
                              stringObjectMap.put(strings1[0], Double.parseDouble(strings1[1]));
                          }
                      }
                  } else {
                      throw new NumberFormatException("invalid data update format!");
                  }
              } else if (string.contains("lastname=")) {
                  String[] strings1 = string.split("=");
                  if (formatDataValidator.isString(strings1[1])) {
                      for (Map<String, Object> stringObjectMap : result) {
                          if (strings1[1].equals("null")) {
                              stringObjectMap.put(strings1[0], null);
                          } else {
                              stringObjectMap.put("lastName", strings1[1]);
                          }
                      }
                  } else {
                      throw new NumberFormatException("invalid data update format!");
                  }
              } else if (string.contains("active=")) {
                  String[] strings1 = string.split("=");
                  if (formatDataValidator.isBoolean(strings1[1])) {
                      for (Map<String, Object> stringObjectMap : result) {
                          if (strings1[1].equals("null")) {
                              stringObjectMap.put(strings1[0], null);
                          } else {
                              stringObjectMap.put(strings1[0], Boolean.parseBoolean(strings1[1]));
                          }
                      }
                  } else {
                      throw new NumberFormatException("invalid data update format!");
                  }
              } else {
                  throw new RuntimeException("The request contains an invalid parameter name");
              }
          }
      } else if (processString(request).contains("update values ") && processString(request).contains("where")) {
          String newString = processString(request).replace("update values ", " ");
          String[] strings = newString.trim().replaceAll("[ ']", "").split("where");
          String[] stringWithChanges = strings[0].split(",");
          String[] stringConditional = strings[1].split("=");
          ArrayList<String> stringArrayList = new ArrayList<>(List.of(stringWithChanges));
          for (int i = 0; i < stringWithChanges.length; i++) {
              if (stringWithChanges[i].contains(stringConditional[0])) {
                  stringArrayList = new ArrayList<>(List.of(stringWithChanges));
                  stringArrayList.remove(stringWithChanges[i]);
                  stringArrayList.add(stringWithChanges[i]);
              }
              if (stringArrayList.get(i).contains("id=") && strings[1].contains("age=")) {
                  String[] s = stringArrayList.get(i).split("=");
                  updateValuesHandler.updateValuesWhereParameter(s, result, stringConditional, "id=", "age=");
              } else if (stringArrayList.get(i).contains("age=") && strings[1].contains("age=")) {
                  String[] s = stringArrayList.get(i).split("=");
                  updateValuesHandler.updateValuesWhereParameter(s, result, stringConditional, "age=", "age=");
              } else if (stringArrayList.get(i).contains("coast=") && strings[1].contains("age=")) {
                  String[] s = stringArrayList.get(i).split("=");
                  updateValuesHandler.updateValuesWhereParameter(s, result, stringConditional, "coast=", "age=");
              } else if (stringArrayList.get(i).contains("lastname=") && strings[1].contains("age=")) {
                  String[] s = stringArrayList.get(i).split("=");
                  updateValuesHandler.updateValuesWhereParameter(s, result, stringConditional, "lastname=", "age=");
              } else if (stringArrayList.get(i).contains("active=") && strings[1].contains("age=")) {
                  String[] s = stringArrayList.get(i).split("=");
                  updateValuesHandler.updateValuesWhereParameter(s, result, stringConditional, "active=", "age=");
              }  else if (stringArrayList.get(i).contains("id=") && strings[1].contains("id=")) {
                  String[] s = stringArrayList.get(i).split("=");
                  updateValuesHandler.updateValuesWhereParameter(s, result, stringConditional, "id=", "id=");
              } else if (stringArrayList.get(i).contains("age=") && strings[1].contains("id=")) {
                  String[] s = stringArrayList.get(i).split("=");
                  updateValuesHandler.updateValuesWhereParameter(s, result, stringConditional, "age=", "id=");
              } else if (stringArrayList.get(i).contains("coast=") && strings[1].contains("id=")) {
                  String[] s = stringArrayList.get(i).split("=");
                  updateValuesHandler.updateValuesWhereParameter(s, result, stringConditional, "coast=", "id=");
              } else if (stringArrayList.get(i).contains("lastname=") && strings[1].contains("id=")) {
                  String[] s = stringArrayList.get(i).split("=");
                  updateValuesHandler.updateValuesWhereParameter(s, result, stringConditional, "lastname=", "id=");
              } else if (stringArrayList.get(i).contains("active=") && strings[1].contains("id=")) {
                  String[] s = stringArrayList.get(i).split("=");
                  updateValuesHandler.updateValuesWhereParameter(s, result, stringConditional, "active=", "id=");
              } else if (stringArrayList.get(i).contains("id=") && strings[1].contains("coast=")) {
                  String[] s = stringArrayList.get(i).split("=");
                  updateValuesHandler.updateValuesWhereParameter(s, result, stringConditional, "id=", "coast=");
              } else if (stringArrayList.get(i).contains("age=") && strings[1].contains("coast=")) {
                  String[] s = stringArrayList.get(i).split("=");
                  updateValuesHandler.updateValuesWhereParameter(s, result, stringConditional, "age=", "coast=");
              } else if (stringArrayList.get(i).contains("coast=") && strings[1].contains("coast=")) {
                  String[] s = stringArrayList.get(i).split("=");
                  updateValuesHandler.updateValuesWhereParameter(s, result, stringConditional, "coast=", "coast=");
              } else if (stringArrayList.get(i).contains("lastname=") && strings[1].contains("coast=")) {
                  String[] s = stringArrayList.get(i).split("=");
                  updateValuesHandler.updateValuesWhereParameter(s, result, stringConditional, "lastname=", "coast=");
              } else if (stringArrayList.get(i).contains("active=") && strings[1].contains("coast=")) {
                  String[] s = stringArrayList.get(i).split("=");
                  updateValuesHandler.updateValuesWhereParameter(s, result, stringConditional, "active=", "coast=");
              } else if (stringArrayList.get(i).contains("id=") && strings[1].contains("lastname=")) {
                  String[] s = stringArrayList.get(i).split("=");
                  updateValuesHandler.updateValuesWhereParameter(s, result, stringConditional, "id=", "lastname=");
              } else if (stringArrayList.get(i).contains("age=") && strings[1].contains("lastname=")) {
                  String[] s = stringArrayList.get(i).split("=");
                  updateValuesHandler.updateValuesWhereParameter(s, result, stringConditional, "age=", "lastname=");
              } else if (stringArrayList.get(i).contains("coast=") && strings[1].contains("lastname=")) {
                  String[] s = stringArrayList.get(i).split("=");
                  updateValuesHandler.updateValuesWhereParameter(s, result, stringConditional, "coast=", "lastname=");
              } else if (stringArrayList.get(i).contains("lastname=") && strings[1].contains("lastname=")) {
                  String[] s = stringArrayList.get(i).split("=");
                  updateValuesHandler.updateValuesWhereParameter(s, result, stringConditional, "lastname=", "lastname=");
              } else if (stringArrayList.get(i).contains("active=") && strings[1].contains("lastname=")) {
                  String[] s = stringArrayList.get(i).split("=");
                  updateValuesHandler.updateValuesWhereParameter(s, result, stringConditional, "active=", "lastname=");
              } else if (stringArrayList.get(i).contains("id=") && strings[1].contains("active=")) {
                  String[] s = stringArrayList.get(i).split("=");
                  updateValuesHandler.updateValuesWhereParameter(s, result, stringConditional, "id=", "active=");
              } else if (stringArrayList.get(i).contains("age=") && strings[1].contains("active=")) {
                  String[] s = stringArrayList.get(i).split("=");
                  updateValuesHandler.updateValuesWhereParameter(s, result, stringConditional, "age=", "active=");
              } else if (stringArrayList.get(i).contains("coast=") && strings[1].contains("active=")) {
                  String[] s = stringArrayList.get(i).split("=");
                  updateValuesHandler.updateValuesWhereParameter(s, result, stringConditional, "coast=", "active=");
              } else if (stringArrayList.get(i).contains("lastname=") && strings[1].contains("active=")) {
                  String[] s = stringArrayList.get(i).split("=");
                  updateValuesHandler.updateValuesWhereParameter(s, result, stringConditional, "lastname=", "active=");
              } else if (stringArrayList.get(i).contains("active=") && strings[1].contains("active=")) {
                  String[] s = stringArrayList.get(i).split("=");
                  updateValuesHandler.updateValuesWhereParameter(s, result, stringConditional, "active=", "active=");
              } else {
                  throw new RuntimeException("The request contains an invalid parameter name");
              }
          }
      } else {
            throw new RuntimeException("Incorrect method request");
        }
  }

  public List<Map<String, Object>> getResults(String request) {
      String processedRequest = processString(request);
      String newString = processedRequest.replace("select where", " ");
      String[] strings = newString.trim().replaceAll("[ ']", "").split("and|or");
      if (processedRequest.trim().equals("select")) {
          return result;
      } else if (processedRequest.contains("select where") && !processedRequest.contains("and") &&
              !processedRequest.contains("or")) {
              for (String string : strings) {
                  if (string.contains("id=")) {
                      return integerHandler.getMapInteger(string, "=", result);
                  } else if (string.contains("id!=")) {
                      return integerHandler.getMapInteger(string, "!=", result);
                  } else if (string.contains("id>") && !string.contains("=")) {
                      return integerHandler.getMapInteger(string, ">", result);
                  } else if (string.contains("id<") && !string.contains("=")) {
                      return integerHandler.getMapInteger(string, "<", result);
                  } else if (string.contains("id>=")) {
                      return integerHandler.getMapInteger(string, ">=", result);
                  } else if (string.contains("id<=")) {
                      return integerHandler.getMapInteger(string, "<=", result);
                  } else if (string.contains("age=")) {
                      return integerHandler.getMapInteger(string, "=", result);
                  } else if (string.contains("age!=")) {
                      return integerHandler.getMapInteger(string, "!=", result);
                  } else if (string.contains("age>") && !string.contains("=")) {
                      return integerHandler.getMapInteger(string, ">", result);
                  } else if (string.contains("age<") && !string.contains("=")) {
                      return integerHandler.getMapInteger(string, "<", result);
                  } else if (string.contains("age>=")) {
                      return integerHandler.getMapInteger(string, ">=", result);
                  } else if (string.contains("age<=")) {
                      return integerHandler.getMapInteger(string, "<=", result);
                  } else if (string.contains("coast=")) {
                      return doubleHandler.getMapDouble(string, "=", result);
                  } else if (string.contains("coast!=")) {
                      return doubleHandler.getMapDouble(string, "!=", result);
                  } else if (string.contains("coast>") && !string.contains("=")) {
                      return doubleHandler.getMapDouble(string, ">", result);
                  } else if (string.contains("coast<") && !string.contains("=")) {
                      return doubleHandler.getMapDouble(string, "<", result);
                  } else if (string.contains("coast>=")) {
                      return doubleHandler.getMapDouble(string, ">=", result);
                  } else if (string.contains("coast<=")) {
                      return doubleHandler.getMapDouble(string, "<=", result);
                  } else if (string.contains("lastname=")) {
                      return stringHandler.getMapString(string, "=", result);
                  } else if (string.contains("lastname!=")) {
                      return stringHandler.getMapString(string, "!=", result);
                  } else if (string.contains("lastnamelike")) {
                      return stringHandler.getMapString(string, "like", result);
                  } else if (string.contains("lastnameilike")) {
                      return stringHandler.getMapString(string, "ilike", result);
                  }
                  else if (string.contains("active=")) {
                      return booleanHandler.getMapBoolean(string, "=", result);
                  } else if (string.contains("active!=")) {
                      return booleanHandler.getMapBoolean(string, "!=", result);
                  } else {
                      throw new RuntimeException("The request contains an invalid parameter name");
                  }
              }
          return new ArrayList<>();
      } else if (processedRequest.contains("select where") && processedRequest.contains("and") && !processedRequest.contains("or")) {
          for (int i = 0; i < strings.length; i++) {
              //block id-age-and
              if ((strings[i].contains("id=") && strings[i + 1].contains("age="))) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "=", "=", result);
              } else if ((strings[i].contains("id=") && strings[i + 1].contains("age!="))) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "=", "!=", result);
              } else if (strings[i].contains("id=") && strings[i + 1].contains("age>") &&
                      !strings[i + 1].contains("=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "=", ">", result);
              } else if (strings[i].contains("id=") && strings[i + 1].contains("age<") &&
                      !strings[i + 1].contains("=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "=", "<", result);
              } else if (strings[i].contains("id=") && strings[i + 1].contains("age>=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "=", ">=", result);
              } else if (strings[i].contains("id=") && strings[i + 1].contains("age<=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "=", "<=", result);
              } else if (strings[i].contains("id>") && !strings[i].contains("=")
                      && strings[i + 1].contains("age=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], ">", "=", result);
              } else if (strings[i].contains("id>") && !strings[i].contains("=")
                      && strings[i + 1].contains("age!=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], ">", "!=", result);
              } else if (strings[i].contains("id>") && !strings[i].contains("=")
                      && strings[i + 1].contains("age>") && !strings[i + 1].contains("=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], ">", ">", result);
              } else if (strings[i].contains("id>") && !strings[i].contains("=")
                      && strings[i + 1].contains("age<") && !strings[i + 1].contains("=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], ">", "<", result);
              } else if (strings[i].contains("id>") && !strings[i].contains("=")
                      && strings[i + 1].contains("age>=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], ">", ">=", result);
              } else if (strings[i].contains("id>") && !strings[i].contains("=")
                      && strings[i + 1].contains("age<=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], ">", "<=", result);
              } else if (strings[i].contains("id<") && !strings[i].contains("=")
                      && strings[i + 1].contains("age=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "<", "=", result);
              } else if (strings[i].contains("id<") && !strings[i].contains("=")
                      && strings[i + 1].contains("age!=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "<", "!=", result);
              } else if (strings[i].contains("id<") && !strings[i].contains("=")
                      && strings[i + 1].contains("age>") && !strings[i + 1].contains("=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "<", ">", result);
              } else if (strings[i].contains("id<") && !strings[i].contains("=")
                      && strings[i + 1].contains("age<") && !strings[i + 1].contains("=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "<", "<", result);
              } else if (strings[i].contains("id<") && !strings[i].contains("=")
                      && strings[i + 1].contains("age>=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "<", ">=", result);
              } else if (strings[i].contains("id<") && !strings[i].contains("=")
                      && strings[i + 1].contains("age<=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "<", "<=", result);
              } else if (strings[i].contains("id>=") && strings[i + 1].contains("age=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], ">=", "=", result);
              } else if (strings[i].contains("id>=") && strings[i + 1].contains("age!=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], ">=", "!=", result);
              } else if (strings[i].contains("id>=") && strings[i + 1].contains("age>") &&
                      !strings[i + 1].contains("=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], ">=", ">", result);
              } else if (strings[i].contains("id>=") && strings[i + 1].contains("age<") &&
                      !strings[i + 1].contains("=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], ">=", "<", result);
              } else if (strings[i].contains("id>=") && strings[i + 1].contains("age>=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], ">=", ">=", result);
              } else if (strings[i].contains("id>=") && strings[i + 1].contains("age<=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], ">=", "<=", result);
              } else if (strings[i].contains("id<=") && strings[i + 1].contains("age=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "<=", "=", result);
              } else if (strings[i].contains("id<=") && strings[i + 1].contains("age!=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "<=", "!=", result);
              } else if (strings[i].contains("id<=") && strings[i + 1].contains("age>") &&
                      !strings[i + 1].contains("=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "<=", ">", result);
              } else if (strings[i].contains("id<=") && strings[i + 1].contains("age<") &&
                      !strings[i + 1].contains("=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "<=", "<", result);
              } else if (strings[i].contains("id<=") && strings[i + 1].contains("age>=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "<=", ">=", result);
              } else if (strings[i].contains("id<=") && strings[i + 1].contains("age<=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "<=", "<=", result);
              } else if ((strings[i].contains("id!=") && strings[i + 1].contains("age="))) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "=", result);
              } else if ((strings[i].contains("id!=") && strings[i + 1].contains("age!="))) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "!=", result);
              } else if (strings[i].contains("id!=") && strings[i + 1].contains("age>") &&
                      !strings[i + 1].contains("=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "!=", ">", result);
              } else if (strings[i].contains("id!=") && strings[i + 1].contains("age<") &&
                      !strings[i + 1].contains("=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "<", result);
              } else if (strings[i].contains("id!=") && strings[i + 1].contains("age>=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "!=", ">=", result);
              } else if (strings[i].contains("id!=") && strings[i + 1].contains("age<=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "<=", result);
              }
              // Block id-age-or
              if ((strings[i].contains("id=") && strings[i + 1].contains("age="))) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "=", "=", result);
              } else if ((strings[i].contains("id=") && strings[i + 1].contains("age!="))) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "=", "!=", result);
              } else if (strings[i].contains("id=") && strings[i + 1].contains("age>") &&
                      !strings[i + 1].contains("=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "=", ">", result);
              } else if (strings[i].contains("id=") && strings[i + 1].contains("age<") &&
                      !strings[i + 1].contains("=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "=", "<", result);
              } else if (strings[i].contains("id=") && strings[i + 1].contains("age>=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "=", ">=", result);
              } else if (strings[i].contains("id=") && strings[i + 1].contains("age<=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "=", "<=", result);
              } else if (strings[i].contains("id>") && !strings[i].contains("=")
                      && strings[i + 1].contains("age=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], ">", "=", result);
              } else if (strings[i].contains("id>") && !strings[i].contains("=")
                      && strings[i + 1].contains("age!=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], ">", "!=", result);
              } else if (strings[i].contains("id>") && !strings[i].contains("=")
                      && strings[i + 1].contains("age>") && !strings[i + 1].contains("=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], ">", ">", result);
              } else if (strings[i].contains("id>") && !strings[i].contains("=")
                      && strings[i + 1].contains("age<") && !strings[i + 1].contains("=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], ">", "<", result);
              } else if (strings[i].contains("id>") && !strings[i].contains("=")
                      && strings[i + 1].contains("age>=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], ">", ">=", result);
              } else if (strings[i].contains("id>") && !strings[i].contains("=")
                      && strings[i + 1].contains("age<=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], ">", "<=", result);
              } else if (strings[i].contains("id<") && !strings[i].contains("=")
                      && strings[i + 1].contains("age=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "<", "=", result);
              } else if (strings[i].contains("id<") && !strings[i].contains("=")
                      && strings[i + 1].contains("age!=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "<", "!=", result);
              } else if (strings[i].contains("id<") && !strings[i].contains("=")
                      && strings[i + 1].contains("age>") && !strings[i + 1].contains("=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "<", ">", result);
              } else if (strings[i].contains("id<") && !strings[i].contains("=")
                      && strings[i + 1].contains("age<") && !strings[i + 1].contains("=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "<", "<", result);
              } else if (strings[i].contains("id<") && !strings[i].contains("=")
                      && strings[i + 1].contains("age>=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "<", ">=", result);
              } else if (strings[i].contains("id<") && !strings[i].contains("=")
                      && strings[i + 1].contains("age<=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "<", "<=", result);
              } else if (strings[i].contains("id>=") && strings[i + 1].contains("age=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], ">=", "=", result);
              } else if (strings[i].contains("id>=") && strings[i + 1].contains("age!=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], ">=", "!=", result);
              } else if (strings[i].contains("id>=") && strings[i + 1].contains("age>") &&
                      !strings[i + 1].contains("=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], ">=", ">", result);
              } else if (strings[i].contains("id>=") && strings[i + 1].contains("age<") &&
                      !strings[i + 1].contains("=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], ">=", "<", result);
              } else if (strings[i].contains("id>=") && strings[i + 1].contains("age>=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], ">=", ">=", result);
              } else if (strings[i].contains("id>=") && strings[i + 1].contains("age<=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], ">=", "<=", result);
              } else if (strings[i].contains("id<=") && strings[i + 1].contains("age=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "<=", "=", result);
              } else if (strings[i].contains("id<=") && strings[i + 1].contains("age!=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "<=", "!=", result);
              } else if (strings[i].contains("id<=") && strings[i + 1].contains("age>") &&
                      !strings[i + 1].contains("=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "<=", ">", result);
              } else if (strings[i].contains("id<=") && strings[i + 1].contains("age<") &&
                      !strings[i + 1].contains("=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "<=", "<", result);
              } else if (strings[i].contains("id<=") && strings[i + 1].contains("age>=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "<=", ">=", result);
              } else if (strings[i].contains("id<=") && strings[i + 1].contains("age<=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "<=", "<=", result);
              } else if ((strings[i].contains("id!=") && strings[i + 1].contains("age="))) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "!=", "=", result);
              } else if ((strings[i].contains("id!=") && strings[i + 1].contains("age!="))) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "!=", "!=", result);
              } else if (strings[i].contains("id!=") && strings[i + 1].contains("age>") &&
                      !strings[i + 1].contains("=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "!=", ">", result);
              } else if (strings[i].contains("id!=") && strings[i + 1].contains("age<") &&
                      !strings[i + 1].contains("=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "!=", "<", result);
              } else if (strings[i].contains("id!=") && strings[i + 1].contains("age>=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "!=", ">=", result);
              } else if (strings[i].contains("id!=") && strings[i + 1].contains("age<=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "!=", "<=", result);
              }
              // Block id-coast
              if ((strings[i].contains("id=") && strings[i + 1].contains("coast="))) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "=", "=", result);
              } else if ((strings[i].contains("id=") && strings[i + 1].contains("coast!="))) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "=", "!=", result);
              } else if (strings[i].contains("id=") && strings[i + 1].contains("coast>") &&
                      !strings[i + 1].contains("=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "=", ">", result);
              } else if (strings[i].contains("id=") && strings[i + 1].contains("coast<") &&
                      !strings[i + 1].contains("=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "=", "<", result);
              } else if (strings[i].contains("id=") && strings[i + 1].contains("coast>=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "=", ">=", result);
              } else if (strings[i].contains("id=") && strings[i + 1].contains("coast<=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "=", "<=", result);
              } else if (strings[i].contains("id>") && !strings[i].contains("=")
                      && strings[i + 1].contains("coast=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], ">", "=", result);
              } else if (strings[i].contains("id>") && !strings[i].contains("=")
                      && strings[i + 1].contains("coast!=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], ">", "!=", result);
              } else if (strings[i].contains("id>") && !strings[i].contains("=")
                      && strings[i + 1].contains("coast>") && !strings[i + 1].contains("=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], ">", ">", result);
              } else if (strings[i].contains("id>") && !strings[i].contains("=")
                      && strings[i + 1].contains("coast<") && !strings[i + 1].contains("=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], ">", "<", result);
              } else if (strings[i].contains("id>") && !strings[i].contains("=")
                      && strings[i + 1].contains("coast>=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], ">", ">=", result);
              } else if (strings[i].contains("id>") && !strings[i].contains("=")
                      && strings[i + 1].contains("coast<=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], ">", "<=", result);
              } else if (strings[i].contains("id<") && !strings[i].contains("=")
                      && strings[i + 1].contains("coast=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "<", "=", result);
              } else if (strings[i].contains("id<") && !strings[i].contains("=")
                      && strings[i + 1].contains("coast!=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "<", "!=", result);
              } else if (strings[i].contains("id<") && !strings[i].contains("=")
                      && strings[i + 1].contains("coast>") && !strings[i + 1].contains("=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "<", ">", result);
              } else if (strings[i].contains("id<") && !strings[i].contains("=")
                      && strings[i + 1].contains("coast<") && !strings[i + 1].contains("=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "<", "<", result);
              } else if (strings[i].contains("id<") && !strings[i].contains("=")
                      && strings[i + 1].contains("coast>=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "<", ">=", result);
              } else if (strings[i].contains("id<") && !strings[i].contains("=")
                      && strings[i + 1].contains("coast<=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "<", "<=", result);
              } else if (strings[i].contains("id>=") && strings[i + 1].contains("coast=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], ">=", "=", result);
              } else if (strings[i].contains("id>=") && strings[i + 1].contains("coast!=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], ">=", "!=", result);
              } else if (strings[i].contains("id>=") && strings[i + 1].contains("coast>") &&
                      !strings[i + 1].contains("=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], ">=", ">", result);
              } else if (strings[i].contains("id>=") && strings[i + 1].contains("coast<") &&
                      !strings[i + 1].contains("=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], ">=", "<", result);
              } else if (strings[i].contains("id>=") && strings[i + 1].contains("coast>=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], ">=", ">=", result);
              } else if (strings[i].contains("id>=") && strings[i + 1].contains("coast<=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], ">=", "<=", result);
              } else if (strings[i].contains("id<=") && strings[i + 1].contains("coast=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "<=", "=", result);
              } else if (strings[i].contains("id<=") && strings[i + 1].contains("coast!=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "<=", "!=", result);
              } else if (strings[i].contains("id<=") && strings[i + 1].contains("coast>") &&
                      !strings[i + 1].contains("=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "<=", ">", result);
              } else if (strings[i].contains("id<=") && strings[i + 1].contains("coast<") &&
                      !strings[i + 1].contains("=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "<=", "<", result);
              } else if (strings[i].contains("id<=") && strings[i + 1].contains("coast>=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "<=", ">=", result);
              } else if (strings[i].contains("id<=") && strings[i + 1].contains("coast<=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "<=", "<=", result);
              } else if ((strings[i].contains("id!=") && strings[i + 1].contains("coast="))) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "=", result);
              } else if ((strings[i].contains("id!=") && strings[i + 1].contains("coast!="))) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "!=", result);
              } else if (strings[i].contains("id!=") && strings[i + 1].contains("coast>") &&
                      !strings[i + 1].contains("=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "!=", ">", result);
              } else if (strings[i].contains("id!=") && strings[i + 1].contains("coast<") &&
                      !strings[i + 1].contains("=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "<", result);
              } else if (strings[i].contains("id!=") && strings[i + 1].contains("coast>=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "!=", ">=", result);
              } else if (strings[i].contains("id!=") && strings[i + 1].contains("coast<=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "<=", result);
              }
              // Block id - active
              if ((strings[i].contains("id=") && strings[i + 1].contains("active="))) {
                  return integerBooleanHandler.getMapIntegerBooleanMultipleConditionsAnd(strings[i], strings[i + 1], "=", "=", result);
              } else if ((strings[i].contains("id=") && strings[i + 1].contains("active!="))) {
                  return integerBooleanHandler.getMapIntegerBooleanMultipleConditionsAnd(strings[i], strings[i + 1], "=", "!=", result);
              } else if (strings[i].contains("id>") && !strings[i].contains("=")
                      && strings[i + 1].contains("active=")) {
                  return integerBooleanHandler.getMapIntegerBooleanMultipleConditionsAnd(strings[i], strings[i + 1], ">", "=", result);
              } else if (strings[i].contains("id>") && !strings[i].contains("=")
                      && strings[i + 1].contains("active!=")) {
                  return integerBooleanHandler.getMapIntegerBooleanMultipleConditionsAnd(strings[i], strings[i + 1], ">", "!=", result);
              } else if (strings[i].contains("id<") && !strings[i].contains("=")
                      && strings[i + 1].contains("active=")) {
                  return integerBooleanHandler.getMapIntegerBooleanMultipleConditionsAnd(strings[i], strings[i + 1], "<", "=", result);
              } else if (strings[i].contains("id<") && !strings[i].contains("=")
                      && strings[i + 1].contains("active!=")) {
                  return integerBooleanHandler.getMapIntegerBooleanMultipleConditionsAnd(strings[i], strings[i + 1], "<", "!=", result);
              } else if (strings[i].contains("id>=") && strings[i + 1].contains("active=")) {
                  return integerBooleanHandler.getMapIntegerBooleanMultipleConditionsAnd(strings[i], strings[i + 1], ">=", "=", result);
              } else if (strings[i].contains("id>=") && strings[i + 1].contains("active!=")) {
                  return integerBooleanHandler.getMapIntegerBooleanMultipleConditionsAnd(strings[i], strings[i + 1], ">=", "!=", result);
              } else if (strings[i].contains("id<=") && strings[i + 1].contains("active=")) {
                  return integerBooleanHandler.getMapIntegerBooleanMultipleConditionsAnd(strings[i], strings[i + 1], "<=", "=", result);
              } else if (strings[i].contains("id<=") && strings[i + 1].contains("active!=")) {
                  return integerBooleanHandler.getMapIntegerBooleanMultipleConditionsAnd(strings[i], strings[i + 1], "<=", "!=", result);
              } else if ((strings[i].contains("id!=") && strings[i + 1].contains("active="))) {
                  return integerBooleanHandler.getMapIntegerBooleanMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "=", result);
              } else if ((strings[i].contains("id!=") && strings[i + 1].contains("active!="))) {
                  return integerBooleanHandler.getMapIntegerBooleanMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "!=", result);
              }
              // Block id - lastName
              if ((strings[i].contains("id=") && strings[i + 1].contains("lastname="))) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsAnd(strings[i], strings[i + 1], "=", "=", result);
              } else if ((strings[i].contains("id=") && strings[i + 1].contains("lastname!="))) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsAnd(strings[i], strings[i + 1], "=", "!=", result);
              } else if (strings[i].contains("id=") && strings[i + 1].contains("lastnamelike")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsAnd(strings[i], strings[i + 1], "=", "like", result);
              } else if (strings[i].contains("id=") && strings[i + 1].contains("lastnameilike")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsAnd(strings[i], strings[i + 1], "=", "ilike", result);
              } else if (strings[i].contains("id>") && !strings[i].contains("=")
                      && strings[i + 1].contains("lastname=")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsAnd(strings[i], strings[i + 1], ">", "=", result);
              } else if (strings[i].contains("id>") && !strings[i].contains("=")
                      && strings[i + 1].contains("lastname!=")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsAnd(strings[i], strings[i + 1], ">", "!=", result);
              } else if (strings[i].contains("id>") && !strings[i].contains("=")
                      && strings[i + 1].contains("lastnamelike")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsAnd(strings[i], strings[i + 1], ">", "like", result);
              } else if (strings[i].contains("id>") && !strings[i].contains("=")
                      && strings[i + 1].contains("lastnameilike")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsAnd(strings[i], strings[i + 1], ">", "ilike", result);
              } else if (strings[i].contains("id<") && !strings[i].contains("=")
                      && strings[i + 1].contains("lastname=")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsAnd(strings[i], strings[i + 1], "<", "=", result);
              } else if (strings[i].contains("id<") && !strings[i].contains("=")
                      && strings[i + 1].contains("lastname!=")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsAnd(strings[i], strings[i + 1], "<", "!=", result);
              } else if (strings[i].contains("id<") && !strings[i].contains("=")
                      && strings[i + 1].contains("lastnamelike")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsAnd(strings[i], strings[i + 1], "<", "like", result);
              } else if (strings[i].contains("id<") && !strings[i].contains("=")
                      && strings[i + 1].contains("lastnameilike")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsAnd(strings[i], strings[i + 1], "<", "ilike", result);
              } else if (strings[i].contains("id>=") && strings[i + 1].contains("lastname=")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsAnd(strings[i], strings[i + 1], ">=", "=", result);
              } else if (strings[i].contains("id>=") && strings[i + 1].contains("lastname!=")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsAnd(strings[i], strings[i + 1], ">=", "!=", result);
              } else if (strings[i].contains("id>=") && strings[i + 1].contains("lastnamelike")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsAnd(strings[i], strings[i + 1], ">=", "like", result);
              } else if (strings[i].contains("id>=") && strings[i + 1].contains("lastnameilike")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsAnd(strings[i], strings[i + 1], ">=", "ilike", result);
              } else if (strings[i].contains("id<=") && strings[i + 1].contains("lastname=")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsAnd(strings[i], strings[i + 1], "<=", "=", result);
              } else if (strings[i].contains("id<=") && strings[i + 1].contains("lastname!=")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsAnd(strings[i], strings[i + 1], "<=", "!=", result);
              } else if (strings[i].contains("id<=") && strings[i + 1].contains("lastnamelike")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsAnd(strings[i], strings[i + 1], "<=", "like", result);
              } else if (strings[i].contains("id<=") && strings[i + 1].contains("lastnameilike")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsAnd(strings[i], strings[i + 1], "<=", "ilike", result);
              } else if ((strings[i].contains("id!=") && strings[i + 1].contains("lastname="))) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "=", result);
              } else if ((strings[i].contains("id!=") && strings[i + 1].contains("lastname!="))) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "!=", result);
              } else if (strings[i].contains("id!=") && strings[i + 1].contains("lastnamelike")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "like", result);
              } else if (strings[i].contains("id!=") && strings[i + 1].contains("lastnameilike")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "ilike", result);
              }
              // Блок age - id
              if ((strings[i].contains("age=") && strings[i + 1].contains("id="))) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "=", "=", result);
              } else if ((strings[i].contains("age=") && strings[i + 1].contains("id!="))) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "=", "!=", result);
              } else if (strings[i].contains("age=") && strings[i + 1].contains("id>") &&
                      !strings[i + 1].contains("=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "=", ">", result);
              } else if (strings[i].contains("age=") && strings[i + 1].contains("id<") &&
                      !strings[i + 1].contains("=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "=", "<", result);
              } else if (strings[i].contains("age=") && strings[i + 1].contains("id>=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "=", ">=", result);
              } else if (strings[i].contains("age=") && strings[i + 1].contains("id<=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "=", "<=", result);
              } else if (strings[i].contains("age>") && !strings[i].contains("=")
                      && strings[i + 1].contains("id=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], ">", "=", result);
              } else if (strings[i].contains("age>") && !strings[i].contains("=")
                      && strings[i + 1].contains("id!=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], ">", "!=", result);
              } else if (strings[i].contains("age>") && !strings[i].contains("=")
                      && strings[i + 1].contains("id>") && !strings[i + 1].contains("=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], ">", ">", result);
              } else if (strings[i].contains("age>") && !strings[i].contains("=")
                      && strings[i + 1].contains("id<") && !strings[i + 1].contains("=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], ">", "<", result);
              } else if (strings[i].contains("age>") && !strings[i].contains("=")
                      && strings[i + 1].contains("id>=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], ">", ">=", result);
              } else if (strings[i].contains("age>") && !strings[i].contains("=")
                      && strings[i + 1].contains("id<=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], ">", "<=", result);
              } else if (strings[i].contains("age<") && !strings[i].contains("=")
                      && strings[i + 1].contains("id=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "<", "=", result);
              } else if (strings[i].contains("age<") && !strings[i].contains("=")
                      && strings[i + 1].contains("id!=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "<", "!=", result);
              } else if (strings[i].contains("age<") && !strings[i].contains("=")
                      && strings[i + 1].contains("id>") && !strings[i + 1].contains("=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "<", ">", result);
              } else if (strings[i].contains("age<") && !strings[i].contains("=")
                      && strings[i + 1].contains("id<") && !strings[i + 1].contains("=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "<", "<", result);
              } else if (strings[i].contains("age<") && !strings[i].contains("=")
                      && strings[i + 1].contains("id>=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "<", ">=", result);
              } else if (strings[i].contains("age<") && !strings[i].contains("=")
                      && strings[i + 1].contains("id<=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "<", "<=", result);
              } else if (strings[i].contains("age>=") && strings[i + 1].contains("id=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], ">=", "=", result);
              } else if (strings[i].contains("age>=") && strings[i + 1].contains("id!=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], ">=", "!=", result);
              } else if (strings[i].contains("age>=") && strings[i + 1].contains("id>") &&
                      !strings[i + 1].contains("=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], ">=", ">", result);
              } else if (strings[i].contains("age>=") && strings[i + 1].contains("id<") &&
                      !strings[i + 1].contains("=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], ">=", "<", result);
              } else if (strings[i].contains("age>=") && strings[i + 1].contains("id>=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], ">=", ">=", result);
              } else if (strings[i].contains("age>=") && strings[i + 1].contains("id<=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], ">=", "<=", result);
              } else if (strings[i].contains("age<=") && strings[i + 1].contains("id=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "<=", "=", result);
              } else if (strings[i].contains("age<=") && strings[i + 1].contains("id!=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "<=", "!=", result);
              } else if (strings[i].contains("age<=") && strings[i + 1].contains("id>") &&
                      !strings[i + 1].contains("=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "<=", ">", result);
              } else if (strings[i].contains("age<=") && strings[i + 1].contains("id<") &&
                      !strings[i + 1].contains("=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "<=", "<", result);
              } else if (strings[i].contains("age<=") && strings[i + 1].contains("id>=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "<=", ">=", result);
              } else if (strings[i].contains("age<=") && strings[i + 1].contains("id<=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "<=", "<=", result);
              } else if ((strings[i].contains("age!=") && strings[i + 1].contains("id="))) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "=", result);
              } else if ((strings[i].contains("age!=") && strings[i + 1].contains("id!="))) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "!=", result);
              } else if (strings[i].contains("age!=") && strings[i + 1].contains("id>") &&
                      !strings[i + 1].contains("=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "!=", ">", result);
              } else if (strings[i].contains("age!=") && strings[i + 1].contains("id<") &&
                      !strings[i + 1].contains("=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "<", result);
              } else if (strings[i].contains("age!=") && strings[i + 1].contains("id>=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "!=", ">=", result);
              } else if (strings[i].contains("age!=") && strings[i + 1].contains("id<=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "<=", result);
              }
              // Block age-coast
              if ((strings[i].contains("age=") && strings[i + 1].contains("coast="))) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "=", "=", result);
              } else if ((strings[i].contains("age=") && strings[i + 1].contains("coast!="))) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "=", "!=", result);
              } else if (strings[i].contains("age=") && strings[i + 1].contains("coast>") &&
                      !strings[i + 1].contains("=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "=", ">", result);
              } else if (strings[i].contains("age=") && strings[i + 1].contains("coast<") &&
                      !strings[i + 1].contains("=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "=", "<", result);
              } else if (strings[i].contains("age=") && strings[i + 1].contains("coast>=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "=", ">=", result);
              } else if (strings[i].contains("age=") && strings[i + 1].contains("coast<=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "=", "<=", result);
              } else if (strings[i].contains("age>") && !strings[i].contains("=")
                      && strings[i + 1].contains("coast=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], ">", "=", result);
              } else if (strings[i].contains("age>") && !strings[i].contains("=")
                      && strings[i + 1].contains("coast!=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], ">", "!=", result);
              } else if (strings[i].contains("age>") && !strings[i].contains("=")
                      && strings[i + 1].contains("coast>") && !strings[i + 1].contains("=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], ">", ">", result);
              } else if (strings[i].contains("age>") && !strings[i].contains("=")
                      && strings[i + 1].contains("coast<") && !strings[i + 1].contains("=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], ">", "<", result);
              } else if (strings[i].contains("age>") && !strings[i].contains("=")
                      && strings[i + 1].contains("coast>=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], ">", ">=", result);
              } else if (strings[i].contains("age>") && !strings[i].contains("=")
                      && strings[i + 1].contains("coast<=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], ">", "<=", result);
              } else if (strings[i].contains("age<") && !strings[i].contains("=")
                      && strings[i + 1].contains("coast=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "<", "=", result);
              } else if (strings[i].contains("age<") && !strings[i].contains("=")
                      && strings[i + 1].contains("coast!=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "<", "!=", result);
              } else if (strings[i].contains("age<") && !strings[i].contains("=")
                      && strings[i + 1].contains("coast>") && !strings[i + 1].contains("=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "<", ">", result);
              } else if (strings[i].contains("age<") && !strings[i].contains("=")
                      && strings[i + 1].contains("coast<") && !strings[i + 1].contains("=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "<", "<", result);
              } else if (strings[i].contains("age<") && !strings[i].contains("=")
                      && strings[i + 1].contains("coast>=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "<", ">=", result);
              } else if (strings[i].contains("age<") && !strings[i].contains("=")
                      && strings[i + 1].contains("coast<=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "<", "<=", result);
              } else if (strings[i].contains("age>=") && strings[i + 1].contains("coast=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], ">=", "=", result);
              } else if (strings[i].contains("age>=") && strings[i + 1].contains("coast!=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], ">=", "!=", result);
              } else if (strings[i].contains("age>=") && strings[i + 1].contains("coast>") &&
                      !strings[i + 1].contains("=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], ">=", ">", result);
              } else if (strings[i].contains("age>=") && strings[i + 1].contains("coast<") &&
                      !strings[i + 1].contains("=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], ">=", "<", result);
              } else if (strings[i].contains("age>=") && strings[i + 1].contains("coast>=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], ">=", ">=", result);
              } else if (strings[i].contains("age>=") && strings[i + 1].contains("coast<=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], ">=", "<=", result);
              } else if (strings[i].contains("age<=") && strings[i + 1].contains("coast=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "<=", "=", result);
              } else if (strings[i].contains("age<=") && strings[i + 1].contains("coast!=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "<=", "!=", result);
              } else if (strings[i].contains("age<=") && strings[i + 1].contains("coast>") &&
                      !strings[i + 1].contains("=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "<=", ">", result);
              } else if (strings[i].contains("age<=") && strings[i + 1].contains("coast<") &&
                      !strings[i + 1].contains("=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "<=", "<", result);
              } else if (strings[i].contains("age<=") && strings[i + 1].contains("coast>=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "<=", ">=", result);
              } else if (strings[i].contains("age<=") && strings[i + 1].contains("coast<=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "<=", "<=", result);
              } else if ((strings[i].contains("age!=") && strings[i + 1].contains("coast="))) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "=", result);
              } else if ((strings[i].contains("age!=") && strings[i + 1].contains("coast!="))) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "!=", result);
              } else if (strings[i].contains("age!=") && strings[i + 1].contains("coast>") &&
                      !strings[i + 1].contains("=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "!=", ">", result);
              } else if (strings[i].contains("age!=") && strings[i + 1].contains("coast<") &&
                      !strings[i + 1].contains("=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "<", result);
              } else if (strings[i].contains("age!=") && strings[i + 1].contains("coast>=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "!=", ">=", result);
              } else if (strings[i].contains("age!=") && strings[i + 1].contains("coast<=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "<=", result);
              }
              // Block age - active
              if ((strings[i].contains("age=") && strings[i + 1].contains("active="))) {
                  return integerBooleanHandler.getMapIntegerBooleanMultipleConditionsAnd(strings[i], strings[i + 1], "=", "=", result);
              } else if ((strings[i].contains("age=") && strings[i + 1].contains("active!="))) {
                  return integerBooleanHandler.getMapIntegerBooleanMultipleConditionsAnd(strings[i], strings[i + 1], "=", "!=", result);
              } else if (strings[i].contains("age>") && !strings[i].contains("=")
                      && strings[i + 1].contains("active=")) {
                  return integerBooleanHandler.getMapIntegerBooleanMultipleConditionsAnd(strings[i], strings[i + 1], ">", "=", result);
              } else if (strings[i].contains("age>") && !strings[i].contains("=")
                      && strings[i + 1].contains("active!=")) {
                  return integerBooleanHandler.getMapIntegerBooleanMultipleConditionsAnd(strings[i], strings[i + 1], ">", "!=", result);
              } else if (strings[i].contains("age<") && !strings[i].contains("=")
                      && strings[i + 1].contains("active=")) {
                  return integerBooleanHandler.getMapIntegerBooleanMultipleConditionsAnd(strings[i], strings[i + 1], "<", "=", result);
              } else if (strings[i].contains("age<") && !strings[i].contains("=")
                      && strings[i + 1].contains("active!=")) {
                  return integerBooleanHandler.getMapIntegerBooleanMultipleConditionsAnd(strings[i], strings[i + 1], "<", "!=", result);
              } else if (strings[i].contains("age>=") && strings[i + 1].contains("active=")) {
                  return integerBooleanHandler.getMapIntegerBooleanMultipleConditionsAnd(strings[i], strings[i + 1], ">=", "=", result);
              } else if (strings[i].contains("age>=") && strings[i + 1].contains("active!=")) {
                  return integerBooleanHandler.getMapIntegerBooleanMultipleConditionsAnd(strings[i], strings[i + 1], ">=", "!=", result);
              } else if (strings[i].contains("age<=") && strings[i + 1].contains("active=")) {
                  return integerBooleanHandler.getMapIntegerBooleanMultipleConditionsAnd(strings[i], strings[i + 1], "<=", "=", result);
              } else if (strings[i].contains("age<=") && strings[i + 1].contains("active!=")) {
                  return integerBooleanHandler.getMapIntegerBooleanMultipleConditionsAnd(strings[i], strings[i + 1], "<=", "!=", result);
              } else if ((strings[i].contains("age!=") && strings[i + 1].contains("active="))) {
                  return integerBooleanHandler.getMapIntegerBooleanMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "=", result);
              } else if ((strings[i].contains("age!=") && strings[i + 1].contains("active!="))) {
                  return integerBooleanHandler.getMapIntegerBooleanMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "!=", result);
              }
              // Block age - lastname
              if ((strings[i].contains("age=") && strings[i + 1].contains("lastname="))) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsAnd(strings[i], strings[i + 1], "=", "=", result);
              } else if ((strings[i].contains("age=") && strings[i + 1].contains("lastname!="))) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsAnd(strings[i], strings[i + 1], "=", "!=", result);
              } else if (strings[i].contains("age=") && strings[i + 1].contains("lastnamelike")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsAnd(strings[i], strings[i + 1], "=", "like", result);
              } else if (strings[i].contains("age=") && strings[i + 1].contains("lastnameilike")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsAnd(strings[i], strings[i + 1], "=", "ilike", result);
              } else if (strings[i].contains("age>") && !strings[i].contains("=")
                      && strings[i + 1].contains("lastname=")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsAnd(strings[i], strings[i + 1], ">", "=", result);
              } else if (strings[i].contains("age>") && !strings[i].contains("=")
                      && strings[i + 1].contains("lastname!=")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsAnd(strings[i], strings[i + 1], ">", "!=", result);
              } else if (strings[i].contains("age>") && !strings[i].contains("=")
                      && strings[i + 1].contains("lastnamelike")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsAnd(strings[i], strings[i + 1], ">", "like", result);
              } else if (strings[i].contains("age>") && !strings[i].contains("=")
                      && strings[i + 1].contains("lastnameilike")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsAnd(strings[i], strings[i + 1], ">", "ilike", result);
              } else if (strings[i].contains("age<") && !strings[i].contains("=")
                      && strings[i + 1].contains("lastname=")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsAnd(strings[i], strings[i + 1], "<", "=", result);
              } else if (strings[i].contains("age<") && !strings[i].contains("=")
                      && strings[i + 1].contains("lastname!=")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsAnd(strings[i], strings[i + 1], "<", "!=", result);
              } else if (strings[i].contains("age<") && !strings[i].contains("=")
                      && strings[i + 1].contains("lastnamelike")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsAnd(strings[i], strings[i + 1], "<", "like", result);
              } else if (strings[i].contains("age<") && !strings[i].contains("=")
                      && strings[i + 1].contains("lastnameilike")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsAnd(strings[i], strings[i + 1], "<", "ilike", result);
              } else if (strings[i].contains("age>=") && strings[i + 1].contains("lastname=")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsAnd(strings[i], strings[i + 1], ">=", "=", result);
              } else if (strings[i].contains("age>=") && strings[i + 1].contains("lastname!=")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsAnd(strings[i], strings[i + 1], ">=", "!=", result);
              } else if (strings[i].contains("age>=") && strings[i + 1].contains("lastnamelike")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsAnd(strings[i], strings[i + 1], ">=", "like", result);
              } else if (strings[i].contains("age>=") && strings[i + 1].contains("lastnameilike")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsAnd(strings[i], strings[i + 1], ">=", "ilike", result);
              } else if (strings[i].contains("age<=") && strings[i + 1].contains("lastname=")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsAnd(strings[i], strings[i + 1], "<=", "=", result);
              } else if (strings[i].contains("age<=") && strings[i + 1].contains("lastname!=")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsAnd(strings[i], strings[i + 1], "<=", "!=", result);
              } else if (strings[i].contains("age<=") && strings[i + 1].contains("lastnamelike")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsAnd(strings[i], strings[i + 1], "<=", "like", result);
              } else if (strings[i].contains("age<=") && strings[i + 1].contains("lastnameilike")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsAnd(strings[i], strings[i + 1], "<=", "ilike", result);
              } else if ((strings[i].contains("age!=") && strings[i + 1].contains("lastname="))) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "=", result);
              } else if ((strings[i].contains("age!=") && strings[i + 1].contains("lastname!="))) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "!=", result);
              } else if (strings[i].contains("age!=") && strings[i + 1].contains("lastnamelike")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "like", result);
              } else if (strings[i].contains("age!=") && strings[i + 1].contains("lastnameilike")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "ilike", result);
              }
              // Block coast-id
              if ((strings[i].contains("coast=") && strings[i + 1].contains("id="))) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "=", "=", result);
              } else if ((strings[i].contains("coast=") && strings[i + 1].contains("id!="))) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "=", "!=", result);
              } else if (strings[i].contains("coast=") && strings[i + 1].contains("id>") &&
                      !strings[i + 1].contains("=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "=", ">", result);
              } else if (strings[i].contains("coast=") && strings[i + 1].contains("id<") &&
                      !strings[i + 1].contains("=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "=", "<", result);
              } else if (strings[i].contains("coast=") && strings[i + 1].contains("id>=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "=", ">=", result);
              } else if (strings[i].contains("coast=") && strings[i + 1].contains("id<=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "=", "<=", result);
              } else if (strings[i].contains("coast>") && !strings[i].contains("=")
                      && strings[i + 1].contains("id=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], ">", "=", result);
              } else if (strings[i].contains("coast>") && !strings[i].contains("=")
                      && strings[i + 1].contains("id!=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], ">", "!=", result);
              } else if (strings[i].contains("coast>") && !strings[i].contains("=")
                      && strings[i + 1].contains("id>") && !strings[i + 1].contains("=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], ">", ">", result);
              } else if (strings[i].contains("coast>") && !strings[i].contains("=")
                      && strings[i + 1].contains("id<") && !strings[i + 1].contains("=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], ">", "<", result);
              } else if (strings[i].contains("coast>") && !strings[i].contains("=")
                      && strings[i + 1].contains("id>=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], ">", ">=", result);
              } else if (strings[i].contains("coast>") && !strings[i].contains("=")
                      && strings[i + 1].contains("id<=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], ">", "<=", result);
              } else if (strings[i].contains("coast<") && !strings[i].contains("=")
                      && strings[i + 1].contains("id=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "<", "=", result);
              } else if (strings[i].contains("coast<") && !strings[i].contains("=")
                      && strings[i + 1].contains("id!=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "<", "!=", result);
              } else if (strings[i].contains("coast<") && !strings[i].contains("=")
                      && strings[i + 1].contains("id>") && !strings[i + 1].contains("=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "<", ">", result);
              } else if (strings[i].contains("coast<") && !strings[i].contains("=")
                      && strings[i + 1].contains("id<") && !strings[i + 1].contains("=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "<", "<", result);
              } else if (strings[i].contains("coast<") && !strings[i].contains("=")
                      && strings[i + 1].contains("id>=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "<", ">=", result);
              } else if (strings[i].contains("coast<") && !strings[i].contains("=")
                      && strings[i + 1].contains("id<=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "<", "<=", result);
              } else if (strings[i].contains("coast>=") && strings[i + 1].contains("id=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], ">=", "=", result);
              } else if (strings[i].contains("coast>=") && strings[i + 1].contains("id!=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], ">=", "!=", result);
              } else if (strings[i].contains("coast>=") && strings[i + 1].contains("id>") &&
                      !strings[i + 1].contains("=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], ">=", ">", result);
              } else if (strings[i].contains("coast>=") && strings[i + 1].contains("id<") &&
                      !strings[i + 1].contains("=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], ">=", "<", result);
              } else if (strings[i].contains("coast>=") && strings[i + 1].contains("id>=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], ">=", ">=", result);
              } else if (strings[i].contains("coast>=") && strings[i + 1].contains("id<=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], ">=", "<=", result);
              } else if (strings[i].contains("coast<=") && strings[i + 1].contains("id=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "<=", "=", result);
              } else if (strings[i].contains("coast<=") && strings[i + 1].contains("id!=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "<=", "!=", result);
              } else if (strings[i].contains("coast<=") && strings[i + 1].contains("id>") &&
                      !strings[i + 1].contains("=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "<=", ">", result);
              } else if (strings[i].contains("coast<=") && strings[i + 1].contains("id<") &&
                      !strings[i + 1].contains("=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "<=", "<", result);
              } else if (strings[i].contains("coast<=") && strings[i + 1].contains("id>=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "<=", ">=", result);
              } else if (strings[i].contains("coast<=") && strings[i + 1].contains("id<=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "<=", "<=", result);
              } else if ((strings[i].contains("coast!=") && strings[i + 1].contains("id="))) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "=", result);
              } else if ((strings[i].contains("coast!=") && strings[i + 1].contains("id!="))) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "!=", result);
              } else if (strings[i].contains("coast!=") && strings[i + 1].contains("id>") &&
                      !strings[i + 1].contains("=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "!=", ">", result);
              } else if (strings[i].contains("coast!=") && strings[i + 1].contains("id<") &&
                      !strings[i + 1].contains("=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "<", result);
              } else if (strings[i].contains("coast!=") && strings[i + 1].contains("id>=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "!=", ">=", result);
              } else if (strings[i].contains("coast!=") && strings[i + 1].contains("id<=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "<=", result);
              }
              // Block coast-age
              if ((strings[i].contains("coast=") && strings[i + 1].contains("age="))) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "=", "=", result);
              } else if ((strings[i].contains("coast=") && strings[i + 1].contains("age!="))) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "=", "!=", result);
              } else if (strings[i].contains("coast=") && strings[i + 1].contains("age>") &&
                      !strings[i + 1].contains("=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "=", ">", result);
              } else if (strings[i].contains("coast=") && strings[i + 1].contains("age<") &&
                      !strings[i + 1].contains("=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "=", "<", result);
              } else if (strings[i].contains("coast=") && strings[i + 1].contains("age>=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "=", ">=", result);
              } else if (strings[i].contains("coast=") && strings[i + 1].contains("age<=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "=", "<=", result);
              } else if (strings[i].contains("coast>") && !strings[i].contains("=")
                      && strings[i + 1].contains("age=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], ">", "=", result);
              } else if (strings[i].contains("coast>") && !strings[i].contains("=")
                      && strings[i + 1].contains("age!=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], ">", "!=", result);
              } else if (strings[i].contains("coast>") && !strings[i].contains("=")
                      && strings[i + 1].contains("age>") && !strings[i + 1].contains("=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], ">", ">", result);
              } else if (strings[i].contains("coast>") && !strings[i].contains("=")
                      && strings[i + 1].contains("age<") && !strings[i + 1].contains("=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], ">", "<", result);
              } else if (strings[i].contains("coast>") && !strings[i].contains("=")
                      && strings[i + 1].contains("age>=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], ">", ">=", result);
              } else if (strings[i].contains("coast>") && !strings[i].contains("=")
                      && strings[i + 1].contains("age<=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], ">", "<=", result);
              } else if (strings[i].contains("coast<") && !strings[i].contains("=")
                      && strings[i + 1].contains("age=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "<", "=", result);
              } else if (strings[i].contains("coast<") && !strings[i].contains("=")
                      && strings[i + 1].contains("age!=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "<", "!=", result);
              } else if (strings[i].contains("coast<") && !strings[i].contains("=")
                      && strings[i + 1].contains("age>") && !strings[i + 1].contains("=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "<", ">", result);
              } else if (strings[i].contains("coast<") && !strings[i].contains("=")
                      && strings[i + 1].contains("age<") && !strings[i + 1].contains("=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "<", "<", result);
              } else if (strings[i].contains("coast<") && !strings[i].contains("=")
                      && strings[i + 1].contains("age>=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "<", ">=", result);
              } else if (strings[i].contains("coast<") && !strings[i].contains("=")
                      && strings[i + 1].contains("age<=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "<", "<=", result);
              } else if (strings[i].contains("coast>=") && strings[i + 1].contains("age=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], ">=", "=", result);
              } else if (strings[i].contains("coast>=") && strings[i + 1].contains("age!=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], ">=", "!=", result);
              } else if (strings[i].contains("coast>=") && strings[i + 1].contains("age>") &&
                      !strings[i + 1].contains("=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], ">=", ">", result);
              } else if (strings[i].contains("coast>=") && strings[i + 1].contains("age<") &&
                      !strings[i + 1].contains("=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], ">=", "<", result);
              } else if (strings[i].contains("coast>=") && strings[i + 1].contains("age>=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], ">=", ">=", result);
              } else if (strings[i].contains("coast>=") && strings[i + 1].contains("age<=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], ">=", "<=", result);
              } else if (strings[i].contains("coast<=") && strings[i + 1].contains("age=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "<=", "=", result);
              } else if (strings[i].contains("coast<=") && strings[i + 1].contains("age!=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "<=", "!=", result);
              } else if (strings[i].contains("coast<=") && strings[i + 1].contains("age>") &&
                      !strings[i + 1].contains("=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "<=", ">", result);
              } else if (strings[i].contains("coast<=") && strings[i + 1].contains("age<") &&
                      !strings[i + 1].contains("=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "<=", "<", result);
              } else if (strings[i].contains("coast<=") && strings[i + 1].contains("age>=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "<=", ">=", result);
              } else if (strings[i].contains("coast<=") && strings[i + 1].contains("age<=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "<=", "<=", result);
              } else if ((strings[i].contains("coast!=") && strings[i + 1].contains("age="))) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "=", result);
              } else if ((strings[i].contains("coast!=") && strings[i + 1].contains("age!="))) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "!=", result);
              } else if (strings[i].contains("coast!=") && strings[i + 1].contains("age>") &&
                      !strings[i + 1].contains("=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "!=", ">", result);
              } else if (strings[i].contains("coast!=") && strings[i + 1].contains("age<") &&
                      !strings[i + 1].contains("=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "<", result);
              } else if (strings[i].contains("coast!=") && strings[i + 1].contains("age>=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "!=", ">=", result);
              } else if (strings[i].contains("coast!=") && strings[i + 1].contains("age<=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "<=", result);
              }
              //Block coast-active
              if ((strings[i].contains("coast=") && strings[i + 1].contains("active="))) {
                  return doubleBooleanHandler.getMapDoubleBooleanMultipleConditionsAnd(strings[i], strings[i + 1], "=", "=", result);
              } else if ((strings[i].contains("coast=") && strings[i + 1].contains("active!="))) {
                  return doubleBooleanHandler.getMapDoubleBooleanMultipleConditionsAnd(strings[i], strings[i + 1], "=", "!=", result);
              } else if (strings[i].contains("coast>") && !strings[i].contains("=")
                      && strings[i + 1].contains("active=")) {
                  return doubleBooleanHandler.getMapDoubleBooleanMultipleConditionsAnd(strings[i], strings[i + 1], ">", "=", result);
              } else if (strings[i].contains("coast>") && !strings[i].contains("=")
                      && strings[i + 1].contains("active!=")) {
                  return doubleBooleanHandler.getMapDoubleBooleanMultipleConditionsAnd(strings[i], strings[i + 1], ">", "!=", result);
              } else if (strings[i].contains("coast<") && !strings[i].contains("=")
                      && strings[i + 1].contains("active=")) {
                  return doubleBooleanHandler.getMapDoubleBooleanMultipleConditionsAnd(strings[i], strings[i + 1], "<", "=", result);
              } else if (strings[i].contains("coast<") && !strings[i].contains("=")
                      && strings[i + 1].contains("active!=")) {
                  return doubleBooleanHandler.getMapDoubleBooleanMultipleConditionsAnd(strings[i], strings[i + 1], "<", "!=", result);
              } else if (strings[i].contains("coast>=") && strings[i + 1].contains("active=")) {
                  return doubleBooleanHandler.getMapDoubleBooleanMultipleConditionsAnd(strings[i], strings[i + 1], ">=", "=", result);
              } else if (strings[i].contains("coast>=") && strings[i + 1].contains("active!=")) {
                  return doubleBooleanHandler.getMapDoubleBooleanMultipleConditionsAnd(strings[i], strings[i + 1], ">=", "!=", result);
              } else if (strings[i].contains("coast<=") && strings[i + 1].contains("active=")) {
                  return doubleBooleanHandler.getMapDoubleBooleanMultipleConditionsAnd(strings[i], strings[i + 1], "<=", "=", result);
              } else if (strings[i].contains("coast<=") && strings[i + 1].contains("active!=")) {
                  return doubleBooleanHandler.getMapDoubleBooleanMultipleConditionsAnd(strings[i], strings[i + 1], "<=", "!=", result);
              } else if ((strings[i].contains("coast!=") && strings[i + 1].contains("active="))) {
                  return doubleBooleanHandler.getMapDoubleBooleanMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "=", result);
              } else if ((strings[i].contains("coast!=") && strings[i + 1].contains("active!="))) {
                  return doubleBooleanHandler.getMapDoubleBooleanMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "!=", result);
              }
              //Block coast-lastName
              if ((strings[i].contains("coast=") && strings[i + 1].contains("lastname="))) {
                  return doubleStringHandler.getMapDoubleStringMultipleConditionsAnd(strings[i], strings[i + 1], "=", "=", result);
              } else if ((strings[i].contains("coast=") && strings[i + 1].contains("lastname!="))) {
                  return doubleStringHandler.getMapDoubleStringMultipleConditionsAnd(strings[i], strings[i + 1], "=", "!=", result);
              } else if (strings[i].contains("coast=") && strings[i + 1].contains("lastnamelike")) {
                  return doubleStringHandler.getMapDoubleStringMultipleConditionsAnd(strings[i], strings[i + 1], "=", "like", result);
              } else if (strings[i].contains("coast=") && strings[i + 1].contains("lastnameilike")) {
                  return doubleStringHandler.getMapDoubleStringMultipleConditionsAnd(strings[i], strings[i + 1], "=", "ilike", result);
              } else if (strings[i].contains("coast>") && !strings[i].contains("=")
                      && strings[i + 1].contains("lastname=")) {
                  return doubleStringHandler.getMapDoubleStringMultipleConditionsAnd(strings[i], strings[i + 1], ">", "=", result);
              } else if (strings[i].contains("coast>") && !strings[i].contains("=")
                      && strings[i + 1].contains("lastname!=")) {
                  return doubleStringHandler.getMapDoubleStringMultipleConditionsAnd(strings[i], strings[i + 1], ">", "!=", result);
              } else if (strings[i].contains("coast>") && !strings[i].contains("=")
                      && strings[i + 1].contains("lastnamelike")) {
                  return doubleStringHandler.getMapDoubleStringMultipleConditionsAnd(strings[i], strings[i + 1], ">", "like", result);
              } else if (strings[i].contains("coast>") && !strings[i].contains("=")
                      && strings[i + 1].contains("lastnameilike")) {
                  return doubleStringHandler.getMapDoubleStringMultipleConditionsAnd(strings[i], strings[i + 1], ">", "ilike", result);
              } else if (strings[i].contains("coast<") && !strings[i].contains("=")
                      && strings[i + 1].contains("lastname=")) {
                  return doubleStringHandler.getMapDoubleStringMultipleConditionsAnd(strings[i], strings[i + 1], "<", "=", result);
              } else if (strings[i].contains("coast<") && !strings[i].contains("=")
                      && strings[i + 1].contains("lastname!=")) {
                  return doubleStringHandler.getMapDoubleStringMultipleConditionsAnd(strings[i], strings[i + 1], "<", "!=", result);
              } else if (strings[i].contains("coast<") && !strings[i].contains("=")
                      && strings[i + 1].contains("lastnamelike")) {
                  return doubleStringHandler.getMapDoubleStringMultipleConditionsAnd(strings[i], strings[i + 1], "<", "like", result);
              } else if (strings[i].contains("coast<") && !strings[i].contains("=")
                      && strings[i + 1].contains("lastnameilike")) {
                  return doubleStringHandler.getMapDoubleStringMultipleConditionsAnd(strings[i], strings[i + 1], "<", "ilike", result);
              } else if (strings[i].contains("coast>=") && strings[i + 1].contains("lastname=")) {
                  return doubleStringHandler.getMapDoubleStringMultipleConditionsAnd(strings[i], strings[i + 1], ">=", "=", result);
              } else if (strings[i].contains("coast>=") && strings[i + 1].contains("lastname!=")) {
                  return doubleStringHandler.getMapDoubleStringMultipleConditionsAnd(strings[i], strings[i + 1], ">=", "!=", result);
              } else if (strings[i].contains("coast>=") && strings[i + 1].contains("lastnamelike")) {
                  return doubleStringHandler.getMapDoubleStringMultipleConditionsAnd(strings[i], strings[i + 1], ">=", "like", result);
              } else if (strings[i].contains("coast>=") && strings[i + 1].contains("lastnameilike")) {
                  return doubleStringHandler.getMapDoubleStringMultipleConditionsAnd(strings[i], strings[i + 1], ">=", "ilike", result);
              } else if (strings[i].contains("coast<=") && strings[i + 1].contains("lastname=")) {
                  return doubleStringHandler.getMapDoubleStringMultipleConditionsAnd(strings[i], strings[i + 1], "<=", "=", result);
              } else if (strings[i].contains("coast<=") && strings[i + 1].contains("lastname!=")) {
                  return doubleStringHandler.getMapDoubleStringMultipleConditionsAnd(strings[i], strings[i + 1], "<=", "!=", result);
              } else if (strings[i].contains("coast<=") && strings[i + 1].contains("lastnamelike")) {
                  return doubleStringHandler.getMapDoubleStringMultipleConditionsAnd(strings[i], strings[i + 1], "<=", "like", result);
              } else if (strings[i].contains("coast<=") && strings[i + 1].contains("lastnameilike")) {
                  return doubleStringHandler.getMapDoubleStringMultipleConditionsAnd(strings[i], strings[i + 1], "<=", "ilike", result);
              } else if ((strings[i].contains("coast!=") && strings[i + 1].contains("lastname="))) {
                  return doubleStringHandler.getMapDoubleStringMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "=", result);
              } else if ((strings[i].contains("coast!=") && strings[i + 1].contains("lastname!="))) {
                  return doubleStringHandler.getMapDoubleStringMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "!=", result);
              } else if (strings[i].contains("coast!=") && strings[i + 1].contains("lastnamelike")) {
                  return doubleStringHandler.getMapDoubleStringMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "like", result);
              } else if (strings[i].contains("coast!=") && strings[i + 1].contains("lastnameilike")) {
                  return doubleStringHandler.getMapDoubleStringMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "ilike", result);
              }
              //Block active-id
              if ((strings[i].contains("active=") && strings[i + 1].contains("id="))) {
                  return booleanIntegerHandler.getMapBooleanIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "=", "=", result);
              } else if ((strings[i].contains("active=") && strings[i + 1].contains("id!="))) {
                  return booleanIntegerHandler.getMapBooleanIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "=", "!=", result);
              } else if (strings[i].contains("active=") && strings[i + 1].contains("id>") &&
                      !strings[i + 1].contains("=")) {
                  return booleanIntegerHandler.getMapBooleanIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "=", ">", result);
              } else if (strings[i].contains("active=") && strings[i + 1].contains("id<") &&
                      !strings[i + 1].contains("=")) {
                  return booleanIntegerHandler.getMapBooleanIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "=", "<", result);
              } else if (strings[i].contains("active=") && strings[i + 1].contains("id>=")) {
                  return booleanIntegerHandler.getMapBooleanIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "=", ">=", result);
              } else if (strings[i].contains("active=") && strings[i + 1].contains("id<=")) {
                  return booleanIntegerHandler.getMapBooleanIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "=", "<=", result);
              } else if ((strings[i].contains("active!=") && strings[i + 1].contains("id="))) {
                  return booleanIntegerHandler.getMapBooleanIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "=", result);
              } else if ((strings[i].contains("active!=") && strings[i + 1].contains("id!="))) {
                  return booleanIntegerHandler.getMapBooleanIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "!=", result);
              } else if (strings[i].contains("active!=") && strings[i + 1].contains("id>") &&
                      !strings[i + 1].contains("=")) {
                  return booleanIntegerHandler.getMapBooleanIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "!=", ">", result);
              } else if (strings[i].contains("active!=") && strings[i + 1].contains("id<") &&
                      !strings[i + 1].contains("=")) {
                  return booleanIntegerHandler.getMapBooleanIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "<", result);
              } else if (strings[i].contains("active!=") && strings[i + 1].contains("id>=")) {
                  return booleanIntegerHandler.getMapBooleanIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "!=", ">=", result);
              } else if (strings[i].contains("active!=") && strings[i + 1].contains("id<=")) {
                  return booleanIntegerHandler.getMapBooleanIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "<=", result);
              }
              // Block active-age
              if ((strings[i].contains("active=") && strings[i + 1].contains("age="))) {
                  return booleanIntegerHandler.getMapBooleanIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "=", "=", result);
              } else if ((strings[i].contains("active=") && strings[i + 1].contains("age!="))) {
                  return booleanIntegerHandler.getMapBooleanIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "=", "!=", result);
              } else if (strings[i].contains("active=") && strings[i + 1].contains("age>") &&
                      !strings[i + 1].contains("=")) {
                  return booleanIntegerHandler.getMapBooleanIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "=", ">", result);
              } else if (strings[i].contains("active=") && strings[i + 1].contains("age<") &&
                      !strings[i + 1].contains("=")) {
                  return booleanIntegerHandler.getMapBooleanIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "=", "<", result);
              } else if (strings[i].contains("active=") && strings[i + 1].contains("age>=")) {
                  return booleanIntegerHandler.getMapBooleanIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "=", ">=", result);
              } else if (strings[i].contains("active=") && strings[i + 1].contains("age<=")) {
                  return booleanIntegerHandler.getMapBooleanIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "=", "<=", result);
              } else if ((strings[i].contains("active!=") && strings[i + 1].contains("age="))) {
                  return booleanIntegerHandler.getMapBooleanIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "=", result);
              } else if ((strings[i].contains("active!=") && strings[i + 1].contains("age!="))) {
                  return booleanIntegerHandler.getMapBooleanIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "!=", result);
              } else if (strings[i].contains("active!=") && strings[i + 1].contains("age>") &&
                      !strings[i + 1].contains("=")) {
                  return booleanIntegerHandler.getMapBooleanIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "!=", ">", result);
              } else if (strings[i].contains("active!=") && strings[i + 1].contains("age<") &&
                      !strings[i + 1].contains("=")) {
                  return booleanIntegerHandler.getMapBooleanIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "<", result);
              } else if (strings[i].contains("active!=") && strings[i + 1].contains("age>=")) {
                  return booleanIntegerHandler.getMapBooleanIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "!=", ">=", result);
              } else if (strings[i].contains("active!=") && strings[i + 1].contains("age<=")) {
                  return booleanIntegerHandler.getMapBooleanIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "<=", result);
              }
              //Block active-coast
              if ((strings[i].contains("active=") && strings[i + 1].contains("coast="))) {
                  return booleanDoubleHandler.getMapBooleanDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "=", "=", result);
              } else if ((strings[i].contains("active=") && strings[i + 1].contains("coast!="))) {
                  return booleanDoubleHandler.getMapBooleanDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "=", "!=", result);
              } else if (strings[i].contains("active=") && strings[i + 1].contains("coast>") &&
                      !strings[i + 1].contains("=")) {
                  return booleanDoubleHandler.getMapBooleanDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "=", ">", result);
              } else if (strings[i].contains("active=") && strings[i + 1].contains("coast<") &&
                      !strings[i + 1].contains("=")) {
                  return booleanDoubleHandler.getMapBooleanDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "=", "<", result);
              } else if (strings[i].contains("active=") && strings[i + 1].contains("coast>=")) {
                  return booleanDoubleHandler.getMapBooleanDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "=", ">=", result);
              } else if (strings[i].contains("active=") && strings[i + 1].contains("coast<=")) {
                  return booleanDoubleHandler.getMapBooleanDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "=", "<=", result);
              } else if ((strings[i].contains("active!=") && strings[i + 1].contains("coast="))) {
                  return booleanDoubleHandler.getMapBooleanDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "=", result);
              } else if ((strings[i].contains("active!=") && strings[i + 1].contains("coast!="))) {
                  return booleanDoubleHandler.getMapBooleanDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "!=", result);
              } else if (strings[i].contains("active!=") && strings[i + 1].contains("coast>") &&
                      !strings[i + 1].contains("=")) {
                  return booleanDoubleHandler.getMapBooleanDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "!=", ">", result);
              } else if (strings[i].contains("active!=") && strings[i + 1].contains("coast<") &&
                      !strings[i + 1].contains("=")) {
                  return booleanDoubleHandler.getMapBooleanDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "<", result);
              } else if (strings[i].contains("active!=") && strings[i + 1].contains("coast>=")) {
                  return booleanDoubleHandler.getMapBooleanDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "!=", ">=", result);
              } else if (strings[i].contains("active!=") && strings[i + 1].contains("coast<=")) {
                  return booleanDoubleHandler.getMapBooleanDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "<=", result);
              }
              // Block active-lastName
              if ((strings[i].contains("active=") && strings[i + 1].contains("lastname="))) {
                  return booleanStringHandler.getMapBooleanStringMultipleConditionsAnd(strings[i], strings[i + 1], "=", "=", result);
              } else if ((strings[i].contains("active=") && strings[i + 1].contains("lastname!="))) {
                  return booleanStringHandler.getMapBooleanStringMultipleConditionsAnd(strings[i], strings[i + 1], "=", "!=", result);
              } else if (strings[i].contains("active=") && strings[i + 1].contains("lastnamelike")) {
                  return booleanStringHandler.getMapBooleanStringMultipleConditionsAnd(strings[i], strings[i + 1], "=", "like", result);
              } else if (strings[i].contains("active=") && strings[i + 1].contains("lastnameilike")) {
                  return booleanStringHandler.getMapBooleanStringMultipleConditionsAnd(strings[i], strings[i + 1], "=", "ilike", result);
              } else if ((strings[i].contains("active!=") && strings[i + 1].contains("lastname="))) {
                  return booleanStringHandler.getMapBooleanStringMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "=", result);
              } else if ((strings[i].contains("active!=") && strings[i + 1].contains("lastname!="))) {
                  return booleanStringHandler.getMapBooleanStringMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "!=", result);
              } else if (strings[i].contains("active!=") && strings[i + 1].contains("lastnamelike")) {
                  return booleanStringHandler.getMapBooleanStringMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "like", result);
              } else if (strings[i].contains("active!=") && strings[i + 1].contains("lastnameilike")) {
                  return booleanStringHandler.getMapBooleanStringMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "ilike", result);
              }
              // Block lastName-id
              if ((strings[i].contains("lastname=") && strings[i + 1].contains("id="))) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "=", "=", result);
              } else if ((strings[i].contains("lastname=") && strings[i + 1].contains("id!="))) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "=", "!=", result);
              } else if (strings[i].contains("lastname=") && strings[i + 1].contains("id>") &&
                      !strings[i + 1].contains("=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "=", ">", result);
              } else if (strings[i].contains("lastname=") && strings[i + 1].contains("id<") &&
                      !strings[i + 1].contains("=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "=", "<", result);
              } else if (strings[i].contains("lastname=") && strings[i + 1].contains("id>=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "=", ">=", result);
              } else if (strings[i].contains("lastname=") && strings[i + 1].contains("id<=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "=", "<=", result);
              } else if ((strings[i].contains("lastname!=") && strings[i + 1].contains("id="))) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "=", result);
              } else if ((strings[i].contains("lastname!=") && strings[i + 1].contains("id!="))) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "!=", result);
              } else if (strings[i].contains("lastname!=") && strings[i + 1].contains("id>") &&
                      !strings[i + 1].contains("=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "!=", ">", result);
              } else if (strings[i].contains("lastname!=") && strings[i + 1].contains("id<") &&
                      !strings[i + 1].contains("=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "<", result);
              } else if (strings[i].contains("lastname!=") && strings[i + 1].contains("id>=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "!=", ">=", result);
              } else if (strings[i].contains("lastname!=") && strings[i + 1].contains("id<=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "<=", result);
              } else if ((strings[i].contains("lastnamelike") && strings[i + 1].contains("id="))) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "like", "=", result);
              } else if ((strings[i].contains("lastnamelike") && strings[i + 1].contains("id!="))) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "like", "!=", result);
              } else if (strings[i].contains("lastnamelike") && strings[i + 1].contains("id>") &&
                      !strings[i + 1].contains("=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "like", ">", result);
              } else if (strings[i].contains("lastnamelike") && strings[i + 1].contains("id<") &&
                      !strings[i + 1].contains("=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "like", "<", result);
              } else if (strings[i].contains("lastnamelike") && strings[i + 1].contains("id>=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "like", ">=", result);
              } else if (strings[i].contains("lastnamelike") && strings[i + 1].contains("id<=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "like", "<=", result);
              } else if ((strings[i].contains("lastnameilike") && strings[i + 1].contains("id="))) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "ilike", "=", result);
              } else if ((strings[i].contains("lastnameilike") && strings[i + 1].contains("id!="))) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "ilike", "!=", result);
              } else if (strings[i].contains("lastnameilike") && strings[i + 1].contains("id>") &&
                      !strings[i + 1].contains("=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "ilike", ">", result);
              } else if (strings[i].contains("lastnameilike") && strings[i + 1].contains("id<") &&
                      !strings[i + 1].contains("=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "ilike", "<", result);
              } else if (strings[i].contains("lastnameilike") && strings[i + 1].contains("id>=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "ilike", ">=", result);
              } else if (strings[i].contains("lastnameilike") && strings[i + 1].contains("id<=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "ilike", "<=", result);
              }
              //Block lastName-age
              if ((strings[i].contains("lastname=") && strings[i + 1].contains("age="))) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "=", "=", result);
              } else if ((strings[i].contains("lastname=") && strings[i + 1].contains("age!="))) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "=", "!=", result);
              } else if (strings[i].contains("lastname=") && strings[i + 1].contains("age>") &&
                      !strings[i + 1].contains("=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "=", ">", result);
              } else if (strings[i].contains("lastname=") && strings[i + 1].contains("age<") &&
                      !strings[i + 1].contains("=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "=", "<", result);
              } else if (strings[i].contains("lastname=") && strings[i + 1].contains("age>=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "=", ">=", result);
              } else if (strings[i].contains("lastname=") && strings[i + 1].contains("age<=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "=", "<=", result);
              } else if ((strings[i].contains("lastname!=") && strings[i + 1].contains("age="))) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "=", result);
              } else if ((strings[i].contains("lastname!=") && strings[i + 1].contains("age!="))) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "!=", result);
              } else if (strings[i].contains("lastname!=") && strings[i + 1].contains("age>") &&
                      !strings[i + 1].contains("=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "!=", ">", result);
              } else if (strings[i].contains("lastname!=") && strings[i + 1].contains("age<") &&
                      !strings[i + 1].contains("=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "<", result);
              } else if (strings[i].contains("lastname!=") && strings[i + 1].contains("age>=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "!=", ">=", result);
              } else if (strings[i].contains("lastname!=") && strings[i + 1].contains("age<=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "<=", result);
              } else if ((strings[i].contains("lastnamelike") && strings[i + 1].contains("age="))) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "like", "=", result);
              } else if ((strings[i].contains("lastnamelike") && strings[i + 1].contains("age!="))) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "like", "!=", result);
              } else if (strings[i].contains("lastnamelike") && strings[i + 1].contains("age>") &&
                      !strings[i + 1].contains("=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "like", ">", result);
              } else if (strings[i].contains("lastnamelike") && strings[i + 1].contains("age<") &&
                      !strings[i + 1].contains("=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "like", "<", result);
              } else if (strings[i].contains("lastnamelike") && strings[i + 1].contains("age>=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "like", ">=", result);
              } else if (strings[i].contains("lastnamelike") && strings[i + 1].contains("age<=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "like", "<=", result);
              } else if ((strings[i].contains("lastnameilike") && strings[i + 1].contains("age="))) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "ilike", "=", result);
              } else if ((strings[i].contains("lastnameilike") && strings[i + 1].contains("age!="))) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "ilike", "!=", result);
              } else if (strings[i].contains("lastnameilike") && strings[i + 1].contains("age>") &&
                      !strings[i + 1].contains("=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "ilike", ">", result);
              } else if (strings[i].contains("lastnameilike") && strings[i + 1].contains("age<") &&
                      !strings[i + 1].contains("=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "ilike", "<", result);
              } else if (strings[i].contains("lastnameilike") && strings[i + 1].contains("age>=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "ilike", ">=", result);
              } else if (strings[i].contains("lastnameilike") && strings[i + 1].contains("age<=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsAnd(strings[i], strings[i + 1], "ilike", "<=", result);
              }
              // Block lastName-coast
              if ((strings[i].contains("lastname=") && strings[i + 1].contains("coast="))) {
                  return stringDoubleHandler.getMapStringDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "=", "=", result);
              } else if ((strings[i].contains("lastname=") && strings[i + 1].contains("coast!="))) {
                  return stringDoubleHandler.getMapStringDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "=", "!=", result);
              } else if (strings[i].contains("lastname=") && strings[i + 1].contains("coast>") &&
                      !strings[i + 1].contains("=")) {
                  return stringDoubleHandler.getMapStringDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "=", ">", result);
              } else if (strings[i].contains("lastname=") && strings[i + 1].contains("coast<") &&
                      !strings[i + 1].contains("=")) {
                  return stringDoubleHandler.getMapStringDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "=", "<", result);
              } else if (strings[i].contains("lastname=") && strings[i + 1].contains("coast>=")) {
                  return stringDoubleHandler.getMapStringDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "=", ">=", result);
              } else if (strings[i].contains("lastname=") && strings[i + 1].contains("coast<=")) {
                  return stringDoubleHandler.getMapStringDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "=", "<=", result);
              } else if ((strings[i].contains("lastname!=") && strings[i + 1].contains("coast="))) {
                  return stringDoubleHandler.getMapStringDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "=", result);
              } else if ((strings[i].contains("lastname!=") && strings[i + 1].contains("coast!="))) {
                  return stringDoubleHandler.getMapStringDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "!=", result);
              } else if (strings[i].contains("lastname!=") && strings[i + 1].contains("coast>") &&
                      !strings[i + 1].contains("=")) {
                  return stringDoubleHandler.getMapStringDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "!=", ">", result);
              } else if (strings[i].contains("lastname!=") && strings[i + 1].contains("coast<") &&
                      !strings[i + 1].contains("=")) {
                  return stringDoubleHandler.getMapStringDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "<", result);
              } else if (strings[i].contains("lastname!=") && strings[i + 1].contains("coast>=")) {
                  return stringDoubleHandler.getMapStringDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "!=", ">=", result);
              } else if (strings[i].contains("lastname!=") && strings[i + 1].contains("coast<=")) {
                  return stringDoubleHandler.getMapStringDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "<=", result);
              } else if ((strings[i].contains("lastnamelike") && strings[i + 1].contains("coast="))) {
                  return stringDoubleHandler.getMapStringDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "like", "=", result);
              } else if ((strings[i].contains("lastnamelike") && strings[i + 1].contains("coast!="))) {
                  return stringDoubleHandler.getMapStringDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "like", "!=", result);
              } else if (strings[i].contains("lastnamelike") && strings[i + 1].contains("coast>") &&
                      !strings[i + 1].contains("=")) {
                  return stringDoubleHandler.getMapStringDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "like", ">", result);
              } else if (strings[i].contains("lastnamelike") && strings[i + 1].contains("coast<") &&
                      !strings[i + 1].contains("=")) {
                  return stringDoubleHandler.getMapStringDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "like", "<", result);
              } else if (strings[i].contains("lastnamelike") && strings[i + 1].contains("coast>=")) {
                  return stringDoubleHandler.getMapStringDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "like", ">=", result);
              } else if (strings[i].contains("lastnamelike") && strings[i + 1].contains("coast<=")) {
                  return stringDoubleHandler.getMapStringDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "like", "<=", result);
              } else if ((strings[i].contains("lastnameilike") && strings[i + 1].contains("coast="))) {
                  return stringDoubleHandler.getMapStringDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "ilike", "=", result);
              } else if ((strings[i].contains("lastnameilike") && strings[i + 1].contains("coast!="))) {
                  return stringDoubleHandler.getMapStringDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "ilike", "!=", result);
              } else if (strings[i].contains("lastnameilike") && strings[i + 1].contains("coast>") &&
                      !strings[i + 1].contains("=")) {
                  return stringDoubleHandler.getMapStringDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "ilike", ">", result);
              } else if (strings[i].contains("lastnameilike") && strings[i + 1].contains("coast<") &&
                      !strings[i + 1].contains("=")) {
                  return stringDoubleHandler.getMapStringDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "ilike", "<", result);
              } else if (strings[i].contains("lastnameilike") && strings[i + 1].contains("coast>=")) {
                  return stringDoubleHandler.getMapStringDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "ilike", ">=", result);
              } else if (strings[i].contains("lastnameilike") && strings[i + 1].contains("coast<=")) {
                  return stringDoubleHandler.getMapStringDoubleMultipleConditionsAnd(strings[i], strings[i + 1], "ilike", "<=", result);
              }
              // Block lastName-active
              if ((strings[i].contains("lastname=") && strings[i + 1].contains("active="))) {
                  return stringBooleanHandler.getMapStringBooleanMultipleConditionsAnd(strings[i], strings[i + 1], "=", "=", result);
              } else if ((strings[i].contains("lastname=") && strings[i + 1].contains("active!="))) {
                  return stringBooleanHandler.getMapStringBooleanMultipleConditionsAnd(strings[i], strings[i + 1], "=", "!=", result);
              } else if ((strings[i].contains("lastname!=") && strings[i + 1].contains("active="))) {
                  return stringBooleanHandler.getMapStringBooleanMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "=", result);
              } else if ((strings[i].contains("lastname!=") && strings[i + 1].contains("active!="))) {
                  return stringBooleanHandler.getMapStringBooleanMultipleConditionsAnd(strings[i], strings[i + 1], "!=", "!=", result);
              } else if ((strings[i].contains("lastnamelike") && strings[i + 1].contains("active="))) {
                  return stringBooleanHandler.getMapStringBooleanMultipleConditionsAnd(strings[i], strings[i + 1], "like", "=", result);
              } else if ((strings[i].contains("lastnamelike") && strings[i + 1].contains("active!="))) {
                  return stringBooleanHandler.getMapStringBooleanMultipleConditionsAnd(strings[i], strings[i + 1], "like", "!=", result);
              } else if ((strings[i].contains("lastnameilike") && strings[i + 1].contains("active="))) {
                  return stringBooleanHandler.getMapStringBooleanMultipleConditionsAnd(strings[i], strings[i + 1], "ilike", "=", result);
              } else if ((strings[i].contains("lastnameilike") && strings[i + 1].contains("active!="))) {
                  return stringBooleanHandler.getMapStringBooleanMultipleConditionsAnd(strings[i], strings[i + 1], "ilike", "!=", result);
              } else {
                  throw new RuntimeException("The request contains an invalid parameter name or invalid character");
              }
          }
          return new ArrayList<>();
      } else if (processedRequest.contains("select where") && !processedRequest.contains("and") && processedRequest.contains("or")) {
          for (int i = 0; i < strings.length; i++) {
              // Block id-age-or
              if ((strings[i].contains("id=") && strings[i + 1].contains("age="))) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "=", "=", result);
              } else if ((strings[i].contains("id=") && strings[i + 1].contains("age!="))) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "=", "!=", result);
              } else if (strings[i].contains("id=") && strings[i + 1].contains("age>") &&
                      !strings[i + 1].contains("=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "=", ">", result);
              } else if (strings[i].contains("id=") && strings[i + 1].contains("age<") &&
                      !strings[i + 1].contains("=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "=", "<", result);
              } else if (strings[i].contains("id=") && strings[i + 1].contains("age>=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "=", ">=", result);
              } else if (strings[i].contains("id=") && strings[i + 1].contains("age<=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "=", "<=", result);
              } else if (strings[i].contains("id>") && !strings[i].contains("=")
                      && strings[i + 1].contains("age=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], ">", "=", result);
              } else if (strings[i].contains("id>") && !strings[i].contains("=")
                      && strings[i + 1].contains("age!=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], ">", "!=", result);
              } else if (strings[i].contains("id>") && !strings[i].contains("=")
                      && strings[i + 1].contains("age>") && !strings[i + 1].contains("=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], ">", ">", result);
              } else if (strings[i].contains("id>") && !strings[i].contains("=")
                      && strings[i + 1].contains("age<") && !strings[i + 1].contains("=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], ">", "<", result);
              } else if (strings[i].contains("id>") && !strings[i].contains("=")
                      && strings[i + 1].contains("age>=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], ">", ">=", result);
              } else if (strings[i].contains("id>") && !strings[i].contains("=")
                      && strings[i + 1].contains("age<=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], ">", "<=", result);
              } else if (strings[i].contains("id<") && !strings[i].contains("=")
                      && strings[i + 1].contains("age=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "<", "=", result);
              } else if (strings[i].contains("id<") && !strings[i].contains("=")
                      && strings[i + 1].contains("age!=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "<", "!=", result);
              } else if (strings[i].contains("id<") && !strings[i].contains("=")
                      && strings[i + 1].contains("age>") && !strings[i + 1].contains("=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "<", ">", result);
              } else if (strings[i].contains("id<") && !strings[i].contains("=")
                      && strings[i + 1].contains("age<") && !strings[i + 1].contains("=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "<", "<", result);
              } else if (strings[i].contains("id<") && !strings[i].contains("=")
                      && strings[i + 1].contains("age>=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "<", ">=", result);
              } else if (strings[i].contains("id<") && !strings[i].contains("=")
                      && strings[i + 1].contains("age<=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "<", "<=", result);
              } else if (strings[i].contains("id>=") && strings[i + 1].contains("age=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], ">=", "=", result);
              } else if (strings[i].contains("id>=") && strings[i + 1].contains("age!=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], ">=", "!=", result);
              } else if (strings[i].contains("id>=") && strings[i + 1].contains("age>") &&
                      !strings[i + 1].contains("=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], ">=", ">", result);
              } else if (strings[i].contains("id>=") && strings[i + 1].contains("age<") &&
                      !strings[i + 1].contains("=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], ">=", "<", result);
              } else if (strings[i].contains("id>=") && strings[i + 1].contains("age>=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], ">=", ">=", result);
              } else if (strings[i].contains("id>=") && strings[i + 1].contains("age<=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], ">=", "<=", result);
              } else if (strings[i].contains("id<=") && strings[i + 1].contains("age=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "<=", "=", result);
              } else if (strings[i].contains("id<=") && strings[i + 1].contains("age!=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "<=", "!=", result);
              } else if (strings[i].contains("id<=") && strings[i + 1].contains("age>") &&
                      !strings[i + 1].contains("=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "<=", ">", result);
              } else if (strings[i].contains("id<=") && strings[i + 1].contains("age<") &&
                      !strings[i + 1].contains("=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "<=", "<", result);
              } else if (strings[i].contains("id<=") && strings[i + 1].contains("age>=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "<=", ">=", result);
              } else if (strings[i].contains("id<=") && strings[i + 1].contains("age<=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "<=", "<=", result);
              } else if ((strings[i].contains("id!=") && strings[i + 1].contains("age="))) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "!=", "=", result);
              } else if ((strings[i].contains("id!=") && strings[i + 1].contains("age!="))) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "!=", "!=", result);
              } else if (strings[i].contains("id!=") && strings[i + 1].contains("age>") &&
                      !strings[i + 1].contains("=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "!=", ">", result);
              } else if (strings[i].contains("id!=") && strings[i + 1].contains("age<") &&
                      !strings[i + 1].contains("=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "!=", "<", result);
              } else if (strings[i].contains("id!=") && strings[i + 1].contains("age>=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "!=", ">=", result);
              } else if (strings[i].contains("id!=") && strings[i + 1].contains("age<=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "!=", "<=", result);
              }
              //Block id-coast-or
              if ((strings[i].contains("id=") && strings[i + 1].contains("coast="))) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], "=", "=", result);
              } else if ((strings[i].contains("id=") && strings[i + 1].contains("coast!="))) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], "=", "!=", result);
              } else if (strings[i].contains("id=") && strings[i + 1].contains("coast>") &&
                      !strings[i + 1].contains("=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], "=", ">", result);
              } else if (strings[i].contains("id=") && strings[i + 1].contains("coast<") &&
                      !strings[i + 1].contains("=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], "=", "<", result);
              } else if (strings[i].contains("id=") && strings[i + 1].contains("coast>=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], "=", ">=", result);
              } else if (strings[i].contains("id=") && strings[i + 1].contains("coast<=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], "=", "<=", result);
              } else if (strings[i].contains("id>") && !strings[i].contains("=")
                      && strings[i + 1].contains("coast=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], ">", "=", result);
              } else if (strings[i].contains("id>") && !strings[i].contains("=")
                      && strings[i + 1].contains("coast!=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], ">", "!=", result);
              } else if (strings[i].contains("id>") && !strings[i].contains("=")
                      && strings[i + 1].contains("coast>") && !strings[i + 1].contains("=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], ">", ">", result);
              } else if (strings[i].contains("id>") && !strings[i].contains("=")
                      && strings[i + 1].contains("coast<") && !strings[i + 1].contains("=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], ">", "<", result);
              } else if (strings[i].contains("id>") && !strings[i].contains("=")
                      && strings[i + 1].contains("coast>=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], ">", ">=", result);
              } else if (strings[i].contains("id>") && !strings[i].contains("=")
                      && strings[i + 1].contains("coast<=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], ">", "<=", result);
              } else if (strings[i].contains("id<") && !strings[i].contains("=")
                      && strings[i + 1].contains("coast=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], "<", "=", result);
              } else if (strings[i].contains("id<") && !strings[i].contains("=")
                      && strings[i + 1].contains("coast!=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], "<", "!=", result);
              } else if (strings[i].contains("id<") && !strings[i].contains("=")
                      && strings[i + 1].contains("coast>") && !strings[i + 1].contains("=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], "<", ">", result);
              } else if (strings[i].contains("id<") && !strings[i].contains("=")
                      && strings[i + 1].contains("coast<") && !strings[i + 1].contains("=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], "<", "<", result);
              } else if (strings[i].contains("id<") && !strings[i].contains("=")
                      && strings[i + 1].contains("coast>=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], "<", ">=", result);
              } else if (strings[i].contains("id<") && !strings[i].contains("=")
                      && strings[i + 1].contains("coast<=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], "<", "<=", result);
              } else if (strings[i].contains("id>=") && strings[i + 1].contains("coast=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], ">=", "=", result);
              } else if (strings[i].contains("id>=") && strings[i + 1].contains("coast!=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], ">=", "!=", result);
              } else if (strings[i].contains("id>=") && strings[i + 1].contains("coast>") &&
                      !strings[i + 1].contains("=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], ">=", ">", result);
              } else if (strings[i].contains("id>=") && strings[i + 1].contains("coast<") &&
                      !strings[i + 1].contains("=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], ">=", "<", result);
              } else if (strings[i].contains("id>=") && strings[i + 1].contains("coast>=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], ">=", ">=", result);
              } else if (strings[i].contains("id>=") && strings[i + 1].contains("coast<=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], ">=", "<=", result);
              } else if (strings[i].contains("id<=") && strings[i + 1].contains("coast=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], "<=", "=", result);
              } else if (strings[i].contains("id<=") && strings[i + 1].contains("coast!=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], "<=", "!=", result);
              } else if (strings[i].contains("id<=") && strings[i + 1].contains("coast>") &&
                      !strings[i + 1].contains("=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], "<=", ">", result);
              } else if (strings[i].contains("id<=") && strings[i + 1].contains("coast<") &&
                      !strings[i + 1].contains("=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], "<=", "<", result);
              } else if (strings[i].contains("id<=") && strings[i + 1].contains("coast>=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], "<=", ">=", result);
              } else if (strings[i].contains("id<=") && strings[i + 1].contains("coast<=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], "<=", "<=", result);
              } else if ((strings[i].contains("id!=") && strings[i + 1].contains("coast="))) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], "!=", "=", result);
              } else if ((strings[i].contains("id!=") && strings[i + 1].contains("coast!="))) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], "!=", "!=", result);
              } else if (strings[i].contains("id!=") && strings[i + 1].contains("coast>") &&
                      !strings[i + 1].contains("=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], "!=", ">", result);
              } else if (strings[i].contains("id!=") && strings[i + 1].contains("coast<") &&
                      !strings[i + 1].contains("=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], "!=", "<", result);
              } else if (strings[i].contains("id!=") && strings[i + 1].contains("coast>=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], "!=", ">=", result);
              } else if (strings[i].contains("id!=") && strings[i + 1].contains("coast<=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], "!=", "<=", result);
              }
                  //Block id-lastName-or
              if ((strings[i].contains("id=") && strings[i + 1].contains("lastname="))) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsOr(strings[i], strings[i + 1], "=", "=", result);
              } else if ((strings[i].contains("id=") && strings[i + 1].contains("lastname!="))) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsOr(strings[i], strings[i + 1], "=", "!=", result);
              } else if (strings[i].contains("id=") && strings[i + 1].contains("lastnamelike")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsOr(strings[i], strings[i + 1], "=", "like", result);
              } else if (strings[i].contains("id=") && strings[i + 1].contains("lastnameilike")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsOr(strings[i], strings[i + 1], "=", "ilike", result);
              } else if (strings[i].contains("id>") && !strings[i].contains("=")
                      && strings[i + 1].contains("lastname=")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsOr(strings[i], strings[i + 1], ">", "=", result);
              } else if (strings[i].contains("id>") && !strings[i].contains("=")
                      && strings[i + 1].contains("lastname!=")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsOr(strings[i], strings[i + 1], ">", "!=", result);
              } else if (strings[i].contains("id>") && !strings[i].contains("=")
                      && strings[i + 1].contains("lastnamelike")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsOr(strings[i], strings[i + 1], ">", "like", result);
              } else if (strings[i].contains("id>") && !strings[i].contains("=")
                      && strings[i + 1].contains("lastnameilike")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsOr(strings[i], strings[i + 1], ">", "ilike", result);
              } else if (strings[i].contains("id<") && !strings[i].contains("=")
                      && strings[i + 1].contains("lastname=")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsOr(strings[i], strings[i + 1], "<", "=", result);
              } else if (strings[i].contains("id<") && !strings[i].contains("=")
                      && strings[i + 1].contains("lastname!=")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsOr(strings[i], strings[i + 1], "<", "!=", result);
              } else if (strings[i].contains("id<") && !strings[i].contains("=")
                      && strings[i + 1].contains("lastnamelike")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsOr(strings[i], strings[i + 1], "<", "like", result);
              } else if (strings[i].contains("id<") && !strings[i].contains("=")
                      && strings[i + 1].contains("lastnameilike")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsOr(strings[i], strings[i + 1], "<", "ilike", result);
              } else if (strings[i].contains("id>=") && strings[i + 1].contains("lastname=")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsOr(strings[i], strings[i + 1], ">=", "=", result);
              } else if (strings[i].contains("id>=") && strings[i + 1].contains("lastname!=")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsOr(strings[i], strings[i + 1], ">=", "!=", result);
              } else if (strings[i].contains("id>=") && strings[i + 1].contains("lastnamelike")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsOr(strings[i], strings[i + 1], ">=", "like", result);
              } else if (strings[i].contains("id>=") && strings[i + 1].contains("lastnameilike")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsOr(strings[i], strings[i + 1], ">=", "ilike", result);
              } else if (strings[i].contains("id<=") && strings[i + 1].contains("lastname=")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsOr(strings[i], strings[i + 1], "<=", "=", result);
              } else if (strings[i].contains("id<=") && strings[i + 1].contains("lastname!=")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsOr(strings[i], strings[i + 1], "<=", "!=", result);
              } else if (strings[i].contains("id<=") && strings[i + 1].contains("lastnamelike")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsOr(strings[i], strings[i + 1], "<=", "like", result);
              } else if (strings[i].contains("id<=") && strings[i + 1].contains("lastnameilike")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsOr(strings[i], strings[i + 1], "<=", "ilike", result);
              } else if ((strings[i].contains("id!=") && strings[i + 1].contains("lastname="))) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsOr(strings[i], strings[i + 1], "!=", "=", result);
              } else if ((strings[i].contains("id!=") && strings[i + 1].contains("lastname!="))) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsOr(strings[i], strings[i + 1], "!=", "!=", result);
              } else if (strings[i].contains("id!=") && strings[i + 1].contains("lastnamelike")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsOr(strings[i], strings[i + 1], "!=", "like", result);
              } else if (strings[i].contains("id!=") && strings[i + 1].contains("lastnameilike")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsOr(strings[i], strings[i + 1], "!=", "ilike", result);
              }
                  // Block id-active-or
              if ((strings[i].contains("id=") && strings[i + 1].contains("active="))) {
                  return integerBooleanHandler.getMapIntegerBooleanMultipleConditionsOr(strings[i], strings[i + 1], "=", "=", result);
              } else if ((strings[i].contains("id=") && strings[i + 1].contains("active!="))) {
                  return integerBooleanHandler.getMapIntegerBooleanMultipleConditionsOr(strings[i], strings[i + 1], "=", "!=", result);
              } else if (strings[i].contains("id>") && !strings[i].contains("=")
                      && strings[i + 1].contains("active=")) {
                  return integerBooleanHandler.getMapIntegerBooleanMultipleConditionsOr(strings[i], strings[i + 1], ">", "=", result);
              } else if (strings[i].contains("id>") && !strings[i].contains("=")
                      && strings[i + 1].contains("active!=")) {
                  return integerBooleanHandler.getMapIntegerBooleanMultipleConditionsOr(strings[i], strings[i + 1], ">", "!=", result);
              } else if (strings[i].contains("id<") && !strings[i].contains("=")
                      && strings[i + 1].contains("active=")) {
                  return integerBooleanHandler.getMapIntegerBooleanMultipleConditionsOr(strings[i], strings[i + 1], "<", "=", result);
              } else if (strings[i].contains("id<") && !strings[i].contains("=")
                      && strings[i + 1].contains("active!=")) {
                  return integerBooleanHandler.getMapIntegerBooleanMultipleConditionsOr(strings[i], strings[i + 1], "<", "!=", result);
              } else if (strings[i].contains("id>=") && strings[i + 1].contains("active=")) {
                  return integerBooleanHandler.getMapIntegerBooleanMultipleConditionsOr(strings[i], strings[i + 1], ">=", "=", result);
              } else if (strings[i].contains("id>=") && strings[i + 1].contains("active!=")) {
                  return integerBooleanHandler.getMapIntegerBooleanMultipleConditionsOr(strings[i], strings[i + 1], ">=", "!=", result);
              } else if (strings[i].contains("id<=") && strings[i + 1].contains("active=")) {
                  return integerBooleanHandler.getMapIntegerBooleanMultipleConditionsOr(strings[i], strings[i + 1], "<=", "=", result);
              } else if (strings[i].contains("id<=") && strings[i + 1].contains("active!=")) {
                  return integerBooleanHandler.getMapIntegerBooleanMultipleConditionsOr(strings[i], strings[i + 1], "<=", "!=", result);
              } else if ((strings[i].contains("id!=") && strings[i + 1].contains("active="))) {
                  return integerBooleanHandler.getMapIntegerBooleanMultipleConditionsOr(strings[i], strings[i + 1], "!=", "=", result);
              } else if ((strings[i].contains("id!=") && strings[i + 1].contains("active!="))) {
                  return integerBooleanHandler.getMapIntegerBooleanMultipleConditionsOr(strings[i], strings[i + 1], "!=", "!=", result);
              }
              //Block age-id-or
              if ((strings[i].contains("age=") && strings[i + 1].contains("id="))) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "=", "=", result);
              } else if ((strings[i].contains("age=") && strings[i + 1].contains("id!="))) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "=", "!=", result);
              } else if (strings[i].contains("age=") && strings[i + 1].contains("id>") &&
                      !strings[i + 1].contains("=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "=", ">", result);
              } else if (strings[i].contains("age=") && strings[i + 1].contains("id<") &&
                      !strings[i + 1].contains("=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "=", "<", result);
              } else if (strings[i].contains("age=") && strings[i + 1].contains("id>=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "=", ">=", result);
              } else if (strings[i].contains("age=") && strings[i + 1].contains("id<=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "=", "<=", result);
              } else if (strings[i].contains("age>") && !strings[i].contains("=")
                      && strings[i + 1].contains("id=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], ">", "=", result);
              } else if (strings[i].contains("age>") && !strings[i].contains("=")
                      && strings[i + 1].contains("id!=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], ">", "!=", result);
              } else if (strings[i].contains("age>") && !strings[i].contains("=")
                      && strings[i + 1].contains("id>") && !strings[i + 1].contains("=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], ">", ">", result);
              } else if (strings[i].contains("age>") && !strings[i].contains("=")
                      && strings[i + 1].contains("id<") && !strings[i + 1].contains("=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], ">", "<", result);
              } else if (strings[i].contains("age>") && !strings[i].contains("=")
                      && strings[i + 1].contains("id>=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], ">", ">=", result);
              } else if (strings[i].contains("age>") && !strings[i].contains("=")
                      && strings[i + 1].contains("id<=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], ">", "<=", result);
              } else if (strings[i].contains("age<") && !strings[i].contains("=")
                      && strings[i + 1].contains("id=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "<", "=", result);
              } else if (strings[i].contains("age<") && !strings[i].contains("=")
                      && strings[i + 1].contains("id!=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "<", "!=", result);
              } else if (strings[i].contains("age<") && !strings[i].contains("=")
                      && strings[i + 1].contains("id>") && !strings[i + 1].contains("=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "<", ">", result);
              } else if (strings[i].contains("age<") && !strings[i].contains("=")
                      && strings[i + 1].contains("id<") && !strings[i + 1].contains("=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "<", "<", result);
              } else if (strings[i].contains("age<") && !strings[i].contains("=")
                      && strings[i + 1].contains("id>=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "<", ">=", result);
              } else if (strings[i].contains("age<") && !strings[i].contains("=")
                      && strings[i + 1].contains("id<=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "<", "<=", result);
              } else if (strings[i].contains("age>=") && strings[i + 1].contains("id=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], ">=", "=", result);
              } else if (strings[i].contains("age>=") && strings[i + 1].contains("id!=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], ">=", "!=", result);
              } else if (strings[i].contains("age>=") && strings[i + 1].contains("id>") &&
                      !strings[i + 1].contains("=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], ">=", ">", result);
              } else if (strings[i].contains("age>=") && strings[i + 1].contains("id<") &&
                      !strings[i + 1].contains("=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], ">=", "<", result);
              } else if (strings[i].contains("age>=") && strings[i + 1].contains("id>=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], ">=", ">=", result);
              } else if (strings[i].contains("age>=") && strings[i + 1].contains("id<=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], ">=", "<=", result);
              } else if (strings[i].contains("age<=") && strings[i + 1].contains("id=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "<=", "=", result);
              } else if (strings[i].contains("age<=") && strings[i + 1].contains("id!=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "<=", "!=", result);
              } else if (strings[i].contains("age<=") && strings[i + 1].contains("id>") &&
                      !strings[i + 1].contains("=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "<=", ">", result);
              } else if (strings[i].contains("age<=") && strings[i + 1].contains("id<") &&
                      !strings[i + 1].contains("=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "<=", "<", result);
              } else if (strings[i].contains("age<=") && strings[i + 1].contains("id>=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "<=", ">=", result);
              } else if (strings[i].contains("age<=") && strings[i + 1].contains("id<=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "<=", "<=", result);
              } else if ((strings[i].contains("age!=") && strings[i + 1].contains("id="))) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "!=", "=", result);
              } else if ((strings[i].contains("age!=") && strings[i + 1].contains("id!="))) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "!=", "!=", result);
              } else if (strings[i].contains("age!=") && strings[i + 1].contains("id>") &&
                      !strings[i + 1].contains("=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "!=", ">", result);
              } else if (strings[i].contains("age!=") && strings[i + 1].contains("id<") &&
                      !strings[i + 1].contains("=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "!=", "<", result);
              } else if (strings[i].contains("age!=") && strings[i + 1].contains("id>=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "!=", ">=", result);
              } else if (strings[i].contains("age!=") && strings[i + 1].contains("id<=")) {
                  return integerIntegerHandler.getMapIntegerIntegerMultipleConditionsOr(strings[i], strings[i + 1], "!=", "<=", result);
              }
              //Block age-coast-or
              if ((strings[i].contains("age=") && strings[i + 1].contains("coast="))) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], "=", "=", result);
              } else if ((strings[i].contains("age=") && strings[i + 1].contains("coast!="))) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], "=", "!=", result);
              } else if (strings[i].contains("age=") && strings[i + 1].contains("coast>") &&
                      !strings[i + 1].contains("=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], "=", ">", result);
              } else if (strings[i].contains("age=") && strings[i + 1].contains("coast<") &&
                      !strings[i + 1].contains("=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], "=", "<", result);
              } else if (strings[i].contains("age=") && strings[i + 1].contains("coast>=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], "=", ">=", result);
              } else if (strings[i].contains("age=") && strings[i + 1].contains("coast<=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], "=", "<=", result);
              } else if (strings[i].contains("age>") && !strings[i].contains("=")
                      && strings[i + 1].contains("coast=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], ">", "=", result);
              } else if (strings[i].contains("age>") && !strings[i].contains("=")
                      && strings[i + 1].contains("coast!=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], ">", "!=", result);
              } else if (strings[i].contains("age>") && !strings[i].contains("=")
                      && strings[i + 1].contains("coast>") && !strings[i + 1].contains("=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], ">", ">", result);
              } else if (strings[i].contains("age>") && !strings[i].contains("=")
                      && strings[i + 1].contains("coast<") && !strings[i + 1].contains("=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], ">", "<", result);
              } else if (strings[i].contains("age>") && !strings[i].contains("=")
                      && strings[i + 1].contains("coast>=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], ">", ">=", result);
              } else if (strings[i].contains("age>") && !strings[i].contains("=")
                      && strings[i + 1].contains("coast<=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], ">", "<=", result);
              } else if (strings[i].contains("age<") && !strings[i].contains("=")
                      && strings[i + 1].contains("coast=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], "<", "=", result);
              } else if (strings[i].contains("age<") && !strings[i].contains("=")
                      && strings[i + 1].contains("coast!=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], "<", "!=", result);
              } else if (strings[i].contains("age<") && !strings[i].contains("=")
                      && strings[i + 1].contains("coast>") && !strings[i + 1].contains("=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], "<", ">", result);
              } else if (strings[i].contains("age<") && !strings[i].contains("=")
                      && strings[i + 1].contains("coast<") && !strings[i + 1].contains("=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], "<", "<", result);
              } else if (strings[i].contains("age<") && !strings[i].contains("=")
                      && strings[i + 1].contains("coast>=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], "<", ">=", result);
              } else if (strings[i].contains("age<") && !strings[i].contains("=")
                      && strings[i + 1].contains("coast<=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], "<", "<=", result);
              } else if (strings[i].contains("age>=") && strings[i + 1].contains("coast=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], ">=", "=", result);
              } else if (strings[i].contains("age>=") && strings[i + 1].contains("coast!=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], ">=", "!=", result);
              } else if (strings[i].contains("age>=") && strings[i + 1].contains("coast>") &&
                      !strings[i + 1].contains("=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], ">=", ">", result);
              } else if (strings[i].contains("age>=") && strings[i + 1].contains("coast<") &&
                      !strings[i + 1].contains("=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], ">=", "<", result);
              } else if (strings[i].contains("age>=") && strings[i + 1].contains("coast>=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], ">=", ">=", result);
              } else if (strings[i].contains("age>=") && strings[i + 1].contains("coast<=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], ">=", "<=", result);
              } else if (strings[i].contains("age<=") && strings[i + 1].contains("coast=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], "<=", "=", result);
              } else if (strings[i].contains("age<=") && strings[i + 1].contains("coast!=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], "<=", "!=", result);
              } else if (strings[i].contains("age<=") && strings[i + 1].contains("coast>") &&
                      !strings[i + 1].contains("=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], "<=", ">", result);
              } else if (strings[i].contains("age<=") && strings[i + 1].contains("coast<") &&
                      !strings[i + 1].contains("=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], "<=", "<", result);
              } else if (strings[i].contains("age<=") && strings[i + 1].contains("coast>=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], "<=", ">=", result);
              } else if (strings[i].contains("age<=") && strings[i + 1].contains("coast<=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], "<=", "<=", result);
              } else if ((strings[i].contains("age!=") && strings[i + 1].contains("coast="))) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], "!=", "=", result);
              } else if ((strings[i].contains("age!=") && strings[i + 1].contains("coast!="))) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], "!=", "!=", result);
              } else if (strings[i].contains("age!=") && strings[i + 1].contains("coast>") &&
                      !strings[i + 1].contains("=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], "!=", ">", result);
              } else if (strings[i].contains("age!=") && strings[i + 1].contains("coast<") &&
                      !strings[i + 1].contains("=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], "!=", "<", result);
              } else if (strings[i].contains("age!=") && strings[i + 1].contains("coast>=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], "!=", ">=", result);
              } else if (strings[i].contains("age!=") && strings[i + 1].contains("coast<=")) {
                  return integerDoubleHandler.getMapIntegerDoubleMultipleConditionsOr(strings[i], strings[i + 1], "!=", "<=", result);
              }
              //Block age-lastName-or
              if ((strings[i].contains("age=") && strings[i + 1].contains("lastname="))) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsOr(strings[i], strings[i + 1], "=", "=", result);
              } else if ((strings[i].contains("age=") && strings[i + 1].contains("lastname!="))) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsOr(strings[i], strings[i + 1], "=", "!=", result);
              } else if (strings[i].contains("age=") && strings[i + 1].contains("lastnamelike")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsOr(strings[i], strings[i + 1], "=", "like", result);
              } else if (strings[i].contains("age=") && strings[i + 1].contains("lastnameilike")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsOr(strings[i], strings[i + 1], "=", "ilike", result);
              } else if (strings[i].contains("age>") && !strings[i].contains("=")
                      && strings[i + 1].contains("lastname=")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsOr(strings[i], strings[i + 1], ">", "=", result);
              } else if (strings[i].contains("age>") && !strings[i].contains("=")
                      && strings[i + 1].contains("lastname!=")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsOr(strings[i], strings[i + 1], ">", "!=", result);
              } else if (strings[i].contains("age>") && !strings[i].contains("=")
                      && strings[i + 1].contains("lastnamelike")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsOr(strings[i], strings[i + 1], ">", "like", result);
              } else if (strings[i].contains("age>") && !strings[i].contains("=")
                      && strings[i + 1].contains("lastnameilike")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsOr(strings[i], strings[i + 1], ">", "ilike", result);
              } else if (strings[i].contains("age<") && !strings[i].contains("=")
                      && strings[i + 1].contains("lastname=")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsOr(strings[i], strings[i + 1], "<", "=", result);
              } else if (strings[i].contains("age<") && !strings[i].contains("=")
                      && strings[i + 1].contains("lastname!=")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsOr(strings[i], strings[i + 1], "<", "!=", result);
              } else if (strings[i].contains("age<") && !strings[i].contains("=")
                      && strings[i + 1].contains("lastnamelike")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsOr(strings[i], strings[i + 1], "<", "like", result);
              } else if (strings[i].contains("age<") && !strings[i].contains("=")
                      && strings[i + 1].contains("lastnameilike")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsOr(strings[i], strings[i + 1], "<", "ilike", result);
              } else if (strings[i].contains("age>=") && strings[i + 1].contains("lastname=")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsOr(strings[i], strings[i + 1], ">=", "=", result);
              } else if (strings[i].contains("age>=") && strings[i + 1].contains("lastname!=")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsOr(strings[i], strings[i + 1], ">=", "!=", result);
              } else if (strings[i].contains("age>=") && strings[i + 1].contains("lastnamelike")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsOr(strings[i], strings[i + 1], ">=", "like", result);
              } else if (strings[i].contains("age>=") && strings[i + 1].contains("lastnameilike")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsOr(strings[i], strings[i + 1], ">=", "ilike", result);
              } else if (strings[i].contains("age<=") && strings[i + 1].contains("lastname=")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsOr(strings[i], strings[i + 1], "<=", "=", result);
              } else if (strings[i].contains("age<=") && strings[i + 1].contains("lastname!=")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsOr(strings[i], strings[i + 1], "<=", "!=", result);
              } else if (strings[i].contains("age<=") && strings[i + 1].contains("lastnamelike")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsOr(strings[i], strings[i + 1], "<=", "like", result);
              } else if (strings[i].contains("age<=") && strings[i + 1].contains("lastnameilike")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsOr(strings[i], strings[i + 1], "<=", "ilike", result);
              } else if ((strings[i].contains("age!=") && strings[i + 1].contains("lastname="))) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsOr(strings[i], strings[i + 1], "!=", "=", result);
              } else if ((strings[i].contains("age!=") && strings[i + 1].contains("lastname!="))) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsOr(strings[i], strings[i + 1], "!=", "!=", result);
              } else if (strings[i].contains("age!=") && strings[i + 1].contains("lastnamelike")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsOr(strings[i], strings[i + 1], "!=", "like", result);
              } else if (strings[i].contains("age!=") && strings[i + 1].contains("lastnameilike")) {
                  return integerStringHandler.getMapIntegerStringMultipleConditionsOr(strings[i], strings[i + 1], "!=", "ilike", result);
              }
              // Block age-active-or
              if ((strings[i].contains("age=") && strings[i + 1].contains("active="))) {
                  return integerBooleanHandler.getMapIntegerBooleanMultipleConditionsOr(strings[i], strings[i + 1], "=", "=", result);
              } else if ((strings[i].contains("age=") && strings[i + 1].contains("active!="))) {
                  return integerBooleanHandler.getMapIntegerBooleanMultipleConditionsOr(strings[i], strings[i + 1], "=", "!=", result);
              } else if (strings[i].contains("age>") && !strings[i].contains("=")
                      && strings[i + 1].contains("active=")) {
                  return integerBooleanHandler.getMapIntegerBooleanMultipleConditionsOr(strings[i], strings[i + 1], ">", "=", result);
              } else if (strings[i].contains("age>") && !strings[i].contains("=")
                      && strings[i + 1].contains("active!=")) {
                  return integerBooleanHandler.getMapIntegerBooleanMultipleConditionsOr(strings[i], strings[i + 1], ">", "!=", result);
              } else if (strings[i].contains("age<") && !strings[i].contains("=")
                      && strings[i + 1].contains("active=")) {
                  return integerBooleanHandler.getMapIntegerBooleanMultipleConditionsOr(strings[i], strings[i + 1], "<", "=", result);
              } else if (strings[i].contains("age<") && !strings[i].contains("=")
                      && strings[i + 1].contains("active!=")) {
                  return integerBooleanHandler.getMapIntegerBooleanMultipleConditionsOr(strings[i], strings[i + 1], "<", "!=", result);
              } else if (strings[i].contains("age>=") && strings[i + 1].contains("active=")) {
                  return integerBooleanHandler.getMapIntegerBooleanMultipleConditionsOr(strings[i], strings[i + 1], ">=", "=", result);
              } else if (strings[i].contains("age>=") && strings[i + 1].contains("active!=")) {
                  return integerBooleanHandler.getMapIntegerBooleanMultipleConditionsOr(strings[i], strings[i + 1], ">=", "!=", result);
              } else if (strings[i].contains("age<=") && strings[i + 1].contains("active=")) {
                  return integerBooleanHandler.getMapIntegerBooleanMultipleConditionsOr(strings[i], strings[i + 1], "<=", "=", result);
              } else if (strings[i].contains("age<=") && strings[i + 1].contains("active!=")) {
                  return integerBooleanHandler.getMapIntegerBooleanMultipleConditionsOr(strings[i], strings[i + 1], "<=", "!=", result);
              } else if ((strings[i].contains("age!=") && strings[i + 1].contains("active="))) {
                  return integerBooleanHandler.getMapIntegerBooleanMultipleConditionsOr(strings[i], strings[i + 1], "!=", "=", result);
              } else if ((strings[i].contains("age!=") && strings[i + 1].contains("active!="))) {
                  return integerBooleanHandler.getMapIntegerBooleanMultipleConditionsOr(strings[i], strings[i + 1], "!=", "!=", result);
              }
              // Block coast-id-or
              if ((strings[i].contains("coast=") && strings[i + 1].contains("id="))) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], "=", "=", result);
              } else if ((strings[i].contains("coast=") && strings[i + 1].contains("id!="))) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], "=", "!=", result);
              } else if (strings[i].contains("coast=") && strings[i + 1].contains("id>") &&
                      !strings[i + 1].contains("=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], "=", ">", result);
              } else if (strings[i].contains("coast=") && strings[i + 1].contains("id<") &&
                      !strings[i + 1].contains("=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], "=", "<", result);
              } else if (strings[i].contains("coast=") && strings[i + 1].contains("id>=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], "=", ">=", result);
              } else if (strings[i].contains("coast=") && strings[i + 1].contains("id<=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], "=", "<=", result);
              } else if (strings[i].contains("coast>") && !strings[i].contains("=")
                      && strings[i + 1].contains("id=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], ">", "=", result);
              } else if (strings[i].contains("coast>") && !strings[i].contains("=")
                      && strings[i + 1].contains("id!=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], ">", "!=", result);
              } else if (strings[i].contains("coast>") && !strings[i].contains("=")
                      && strings[i + 1].contains("id>") && !strings[i + 1].contains("=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], ">", ">", result);
              } else if (strings[i].contains("coast>") && !strings[i].contains("=")
                      && strings[i + 1].contains("id<") && !strings[i + 1].contains("=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], ">", "<", result);
              } else if (strings[i].contains("coast>") && !strings[i].contains("=")
                      && strings[i + 1].contains("id>=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], ">", ">=", result);
              } else if (strings[i].contains("coast>") && !strings[i].contains("=")
                      && strings[i + 1].contains("id<=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], ">", "<=", result);
              } else if (strings[i].contains("coast<") && !strings[i].contains("=")
                      && strings[i + 1].contains("id=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], "<", "=", result);
              } else if (strings[i].contains("coast<") && !strings[i].contains("=")
                      && strings[i + 1].contains("id!=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], "<", "!=", result);
              } else if (strings[i].contains("coast<") && !strings[i].contains("=")
                      && strings[i + 1].contains("id>") && !strings[i + 1].contains("=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], "<", ">", result);
              } else if (strings[i].contains("coast<") && !strings[i].contains("=")
                      && strings[i + 1].contains("id<") && !strings[i + 1].contains("=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], "<", "<", result);
              } else if (strings[i].contains("coast<") && !strings[i].contains("=")
                      && strings[i + 1].contains("id>=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], "<", ">=", result);
              } else if (strings[i].contains("coast<") && !strings[i].contains("=")
                      && strings[i + 1].contains("id<=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], "<", "<=", result);
              } else if (strings[i].contains("coast>=") && strings[i + 1].contains("id=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], ">=", "=", result);
              } else if (strings[i].contains("coast>=") && strings[i + 1].contains("id!=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], ">=", "!=", result);
              } else if (strings[i].contains("coast>=") && strings[i + 1].contains("id>") &&
                      !strings[i + 1].contains("=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], ">=", ">", result);
              } else if (strings[i].contains("coast>=") && strings[i + 1].contains("id<") &&
                      !strings[i + 1].contains("=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], ">=", "<", result);
              } else if (strings[i].contains("coast>=") && strings[i + 1].contains("id>=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], ">=", ">=", result);
              } else if (strings[i].contains("coast>=") && strings[i + 1].contains("id<=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], ">=", "<=", result);
              } else if (strings[i].contains("coast<=") && strings[i + 1].contains("id=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], "<=", "=", result);
              } else if (strings[i].contains("coast<=") && strings[i + 1].contains("id!=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], "<=", "!=", result);
              } else if (strings[i].contains("coast<=") && strings[i + 1].contains("id>") &&
                      !strings[i + 1].contains("=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], "<=", ">", result);
              } else if (strings[i].contains("coast<=") && strings[i + 1].contains("id<") &&
                      !strings[i + 1].contains("=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], "<=", "<", result);
              } else if (strings[i].contains("coast<=") && strings[i + 1].contains("id>=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], "<=", ">=", result);
              } else if (strings[i].contains("coast<=") && strings[i + 1].contains("id<=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], "<=", "<=", result);
              } else if ((strings[i].contains("coast!=") && strings[i + 1].contains("id="))) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], "!=", "=", result);
              } else if ((strings[i].contains("coast!=") && strings[i + 1].contains("id!="))) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], "!=", "!=", result);
              } else if (strings[i].contains("coast!=") && strings[i + 1].contains("id>") &&
                      !strings[i + 1].contains("=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], "!=", ">", result);
              } else if (strings[i].contains("coast!=") && strings[i + 1].contains("id<") &&
                      !strings[i + 1].contains("=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], "!=", "<", result);
              } else if (strings[i].contains("coast!=") && strings[i + 1].contains("id>=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], "!=", ">=", result);
              } else if (strings[i].contains("coast!=") && strings[i + 1].contains("id<=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], "!=", "<=", result);
              }
              // Block coast-age-or
              if ((strings[i].contains("coast=") && strings[i + 1].contains("age="))) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], "=", "=", result);
              } else if ((strings[i].contains("coast=") && strings[i + 1].contains("age!="))) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], "=", "!=", result);
              } else if (strings[i].contains("coast=") && strings[i + 1].contains("age>") &&
                      !strings[i + 1].contains("=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], "=", ">", result);
              } else if (strings[i].contains("coast=") && strings[i + 1].contains("age<") &&
                      !strings[i + 1].contains("=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], "=", "<", result);
              } else if (strings[i].contains("coast=") && strings[i + 1].contains("age>=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], "=", ">=", result);
              } else if (strings[i].contains("coast=") && strings[i + 1].contains("age<=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], "=", "<=", result);
              } else if (strings[i].contains("coast>") && !strings[i].contains("=")
                      && strings[i + 1].contains("age=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], ">", "=", result);
              } else if (strings[i].contains("coast>") && !strings[i].contains("=")
                      && strings[i + 1].contains("age!=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], ">", "!=", result);
              } else if (strings[i].contains("coast>") && !strings[i].contains("=")
                      && strings[i + 1].contains("age>") && !strings[i + 1].contains("=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], ">", ">", result);
              } else if (strings[i].contains("coast>") && !strings[i].contains("=")
                      && strings[i + 1].contains("age<") && !strings[i + 1].contains("=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], ">", "<", result);
              } else if (strings[i].contains("coast>") && !strings[i].contains("=")
                      && strings[i + 1].contains("age>=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], ">", ">=", result);
              } else if (strings[i].contains("coast>") && !strings[i].contains("=")
                      && strings[i + 1].contains("age<=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], ">", "<=", result);
              } else if (strings[i].contains("coast<") && !strings[i].contains("=")
                      && strings[i + 1].contains("age=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], "<", "=", result);
              } else if (strings[i].contains("coast<") && !strings[i].contains("=")
                      && strings[i + 1].contains("age!=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], "<", "!=", result);
              } else if (strings[i].contains("coast<") && !strings[i].contains("=")
                      && strings[i + 1].contains("age>") && !strings[i + 1].contains("=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], "<", ">", result);
              } else if (strings[i].contains("coast<") && !strings[i].contains("=")
                      && strings[i + 1].contains("age<") && !strings[i + 1].contains("=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], "<", "<", result);
              } else if (strings[i].contains("coast<") && !strings[i].contains("=")
                      && strings[i + 1].contains("age>=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], "<", ">=", result);
              } else if (strings[i].contains("coast<") && !strings[i].contains("=")
                      && strings[i + 1].contains("age<=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], "<", "<=", result);
              } else if (strings[i].contains("coast>=") && strings[i + 1].contains("age=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], ">=", "=", result);
              } else if (strings[i].contains("coast>=") && strings[i + 1].contains("age!=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], ">=", "!=", result);
              } else if (strings[i].contains("coast>=") && strings[i + 1].contains("age>") &&
                      !strings[i + 1].contains("=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], ">=", ">", result);
              } else if (strings[i].contains("coast>=") && strings[i + 1].contains("age<") &&
                      !strings[i + 1].contains("=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], ">=", "<", result);
              } else if (strings[i].contains("coast>=") && strings[i + 1].contains("age>=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], ">=", ">=", result);
              } else if (strings[i].contains("coast>=") && strings[i + 1].contains("age<=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], ">=", "<=", result);
              } else if (strings[i].contains("coast<=") && strings[i + 1].contains("age=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], "<=", "=", result);
              } else if (strings[i].contains("coast<=") && strings[i + 1].contains("age!=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], "<=", "!=", result);
              } else if (strings[i].contains("coast<=") && strings[i + 1].contains("age>") &&
                      !strings[i + 1].contains("=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], "<=", ">", result);
              } else if (strings[i].contains("coast<=") && strings[i + 1].contains("age<") &&
                      !strings[i + 1].contains("=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], "<=", "<", result);
              } else if (strings[i].contains("coast<=") && strings[i + 1].contains("age>=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], "<=", ">=", result);
              } else if (strings[i].contains("coast<=") && strings[i + 1].contains("age<=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], "<=", "<=", result);
              } else if ((strings[i].contains("coast!=") && strings[i + 1].contains("age="))) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], "!=", "=", result);
              } else if ((strings[i].contains("coast!=") && strings[i + 1].contains("age!="))) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], "!=", "!=", result);
              } else if (strings[i].contains("coast!=") && strings[i + 1].contains("age>") &&
                      !strings[i + 1].contains("=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], "!=", ">", result);
              } else if (strings[i].contains("coast!=") && strings[i + 1].contains("age<") &&
                      !strings[i + 1].contains("=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], "!=", "<", result);
              } else if (strings[i].contains("coast!=") && strings[i + 1].contains("age>=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], "!=", ">=", result);
              } else if (strings[i].contains("coast!=") && strings[i + 1].contains("age<=")) {
                  return doubleIntegerHandler.getMapDoubleIntegerMultipleConditionsOr(strings[i], strings[i + 1], "!=", "<=", result);
              }
              //Block coast-active-or
              if ((strings[i].contains("coast=") && strings[i + 1].contains("active="))) {
                  return doubleBooleanHandler.getMapDoubleBooleanMultipleConditionsOr(strings[i], strings[i + 1], "=", "=", result);
              } else if ((strings[i].contains("coast=") && strings[i + 1].contains("active!="))) {
                  return doubleBooleanHandler.getMapDoubleBooleanMultipleConditionsOr(strings[i], strings[i + 1], "=", "!=", result);
              } else if (strings[i].contains("coast>") && !strings[i].contains("=")
                      && strings[i + 1].contains("active=")) {
                  return doubleBooleanHandler.getMapDoubleBooleanMultipleConditionsOr(strings[i], strings[i + 1], ">", "=", result);
              } else if (strings[i].contains("coast>") && !strings[i].contains("=")
                      && strings[i + 1].contains("active!=")) {
                  return doubleBooleanHandler.getMapDoubleBooleanMultipleConditionsOr(strings[i], strings[i + 1], ">", "!=", result);
              } else if (strings[i].contains("coast<") && !strings[i].contains("=")
                      && strings[i + 1].contains("active=")) {
                  return doubleBooleanHandler.getMapDoubleBooleanMultipleConditionsOr(strings[i], strings[i + 1], "<", "=", result);
              } else if (strings[i].contains("coast<") && !strings[i].contains("=")
                      && strings[i + 1].contains("active!=")) {
                  return doubleBooleanHandler.getMapDoubleBooleanMultipleConditionsOr(strings[i], strings[i + 1], "<", "!=", result);
              } else if (strings[i].contains("coast>=") && strings[i + 1].contains("active=")) {
                  return doubleBooleanHandler.getMapDoubleBooleanMultipleConditionsOr(strings[i], strings[i + 1], ">=", "=", result);
              } else if (strings[i].contains("coast>=") && strings[i + 1].contains("active!=")) {
                  return doubleBooleanHandler.getMapDoubleBooleanMultipleConditionsOr(strings[i], strings[i + 1], ">=", "!=", result);
              } else if (strings[i].contains("coast<=") && strings[i + 1].contains("active=")) {
                  return doubleBooleanHandler.getMapDoubleBooleanMultipleConditionsOr(strings[i], strings[i + 1], "<=", "=", result);
              } else if (strings[i].contains("coast<=") && strings[i + 1].contains("active!=")) {
                  return doubleBooleanHandler.getMapDoubleBooleanMultipleConditionsOr(strings[i], strings[i + 1], "<=", "!=", result);
              } else if ((strings[i].contains("coast!=") && strings[i + 1].contains("active="))) {
                  return doubleBooleanHandler.getMapDoubleBooleanMultipleConditionsOr(strings[i], strings[i + 1], "!=", "=", result);
              } else if ((strings[i].contains("coast!=") && strings[i + 1].contains("active!="))) {
                  return doubleBooleanHandler.getMapDoubleBooleanMultipleConditionsOr(strings[i], strings[i + 1], "!=", "!=", result);
              }
              //Block coast-lastName-or
              if ((strings[i].contains("coast=") && strings[i + 1].contains("lastname="))) {
                  return doubleStringHandler.getMapDoubleStringMultipleConditionsOr(strings[i], strings[i + 1], "=", "=", result);
              } else if ((strings[i].contains("coast=") && strings[i + 1].contains("lastname!="))) {
                  return doubleStringHandler.getMapDoubleStringMultipleConditionsOr(strings[i], strings[i + 1], "=", "!=", result);
              } else if (strings[i].contains("coast=") && strings[i + 1].contains("lastnamelike")) {
                  return doubleStringHandler.getMapDoubleStringMultipleConditionsOr(strings[i], strings[i + 1], "=", "like", result);
              } else if (strings[i].contains("coast=") && strings[i + 1].contains("lastnameilike")) {
                  return doubleStringHandler.getMapDoubleStringMultipleConditionsOr(strings[i], strings[i + 1], "=", "ilike", result);
              } else if (strings[i].contains("coast>") && !strings[i].contains("=")
                      && strings[i + 1].contains("lastname=")) {
                  return doubleStringHandler.getMapDoubleStringMultipleConditionsOr(strings[i], strings[i + 1], ">", "=", result);
              } else if (strings[i].contains("coast>") && !strings[i].contains("=")
                      && strings[i + 1].contains("lastname!=")) {
                  return doubleStringHandler.getMapDoubleStringMultipleConditionsOr(strings[i], strings[i + 1], ">", "!=", result);
              } else if (strings[i].contains("coast>") && !strings[i].contains("=")
                      && strings[i + 1].contains("lastnamelike")) {
                  return doubleStringHandler.getMapDoubleStringMultipleConditionsOr(strings[i], strings[i + 1], ">", "like", result);
              } else if (strings[i].contains("coast>") && !strings[i].contains("=")
                      && strings[i + 1].contains("lastnameilike")) {
                  return doubleStringHandler.getMapDoubleStringMultipleConditionsOr(strings[i], strings[i + 1], ">", "ilike", result);
              } else if (strings[i].contains("coast<") && !strings[i].contains("=")
                      && strings[i + 1].contains("lastname=")) {
                  return doubleStringHandler.getMapDoubleStringMultipleConditionsOr(strings[i], strings[i + 1], "<", "=", result);
              } else if (strings[i].contains("coast<") && !strings[i].contains("=")
                      && strings[i + 1].contains("lastname!=")) {
                  return doubleStringHandler.getMapDoubleStringMultipleConditionsOr(strings[i], strings[i + 1], "<", "!=", result);
              } else if (strings[i].contains("coast<") && !strings[i].contains("=")
                      && strings[i + 1].contains("lastnamelike")) {
                  return doubleStringHandler.getMapDoubleStringMultipleConditionsOr(strings[i], strings[i + 1], "<", "like", result);
              } else if (strings[i].contains("coast<") && !strings[i].contains("=")
                      && strings[i + 1].contains("lastnameilike")) {
                  return doubleStringHandler.getMapDoubleStringMultipleConditionsOr(strings[i], strings[i + 1], "<", "ilike", result);
              } else if (strings[i].contains("coast>=") && strings[i + 1].contains("lastname=")) {
                  return doubleStringHandler.getMapDoubleStringMultipleConditionsOr(strings[i], strings[i + 1], ">=", "=", result);
              } else if (strings[i].contains("coast>=") && strings[i + 1].contains("lastname!=")) {
                  return doubleStringHandler.getMapDoubleStringMultipleConditionsOr(strings[i], strings[i + 1], ">=", "!=", result);
              } else if (strings[i].contains("coast>=") && strings[i + 1].contains("lastnamelike")) {
                  return doubleStringHandler.getMapDoubleStringMultipleConditionsOr(strings[i], strings[i + 1], ">=", "like", result);
              } else if (strings[i].contains("coast>=") && strings[i + 1].contains("lastnameilike")) {
                  return doubleStringHandler.getMapDoubleStringMultipleConditionsOr(strings[i], strings[i + 1], ">=", "ilike", result);
              } else if (strings[i].contains("coast<=") && strings[i + 1].contains("lastname=")) {
                  return doubleStringHandler.getMapDoubleStringMultipleConditionsOr(strings[i], strings[i + 1], "<=", "=", result);
              } else if (strings[i].contains("coast<=") && strings[i + 1].contains("lastname!=")) {
                  return doubleStringHandler.getMapDoubleStringMultipleConditionsOr(strings[i], strings[i + 1], "<=", "!=", result);
              } else if (strings[i].contains("coast<=") && strings[i + 1].contains("lastnamelike")) {
                  return doubleStringHandler.getMapDoubleStringMultipleConditionsOr(strings[i], strings[i + 1], "<=", "like", result);
              } else if (strings[i].contains("coast<=") && strings[i + 1].contains("lastnameilike")) {
                  return doubleStringHandler.getMapDoubleStringMultipleConditionsOr(strings[i], strings[i + 1], "<=", "ilike", result);
              } else if ((strings[i].contains("coast!=") && strings[i + 1].contains("lastname="))) {
                  return doubleStringHandler.getMapDoubleStringMultipleConditionsOr(strings[i], strings[i + 1], "!=", "=", result);
              } else if ((strings[i].contains("coast!=") && strings[i + 1].contains("lastname!="))) {
                  return doubleStringHandler.getMapDoubleStringMultipleConditionsOr(strings[i], strings[i + 1], "!=", "!=", result);
              } else if (strings[i].contains("coast!=") && strings[i + 1].contains("lastnamelike")) {
                  return doubleStringHandler.getMapDoubleStringMultipleConditionsOr(strings[i], strings[i + 1], "!=", "like", result);
              } else if (strings[i].contains("coast!=") && strings[i + 1].contains("lastnameilike")) {
                  return doubleStringHandler.getMapDoubleStringMultipleConditionsOr(strings[i], strings[i + 1], "!=", "ilike", result);

              }
              //Block lastName-id-or
              if ((strings[i].contains("lastname=") && strings[i + 1].contains("id="))) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsOr(strings[i], strings[i + 1], "=", "=", result);
              } else if ((strings[i].contains("lastname=") && strings[i + 1].contains("id!="))) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsOr(strings[i], strings[i + 1], "=", "!=", result);
              } else if (strings[i].contains("lastname=") && strings[i + 1].contains("id>") &&
                      !strings[i + 1].contains("=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsOr(strings[i], strings[i + 1], "=", ">", result);
              } else if (strings[i].contains("lastname=") && strings[i + 1].contains("id<") &&
                      !strings[i + 1].contains("=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsOr(strings[i], strings[i + 1], "=", "<", result);
              } else if (strings[i].contains("lastname=") && strings[i + 1].contains("id>=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsOr(strings[i], strings[i + 1], "=", ">=", result);
              } else if (strings[i].contains("lastname=") && strings[i + 1].contains("id<=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsOr(strings[i], strings[i + 1], "=", "<=", result);
              } else if ((strings[i].contains("lastname!=") && strings[i + 1].contains("id="))) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsOr(strings[i], strings[i + 1], "!=", "=", result);
              } else if ((strings[i].contains("lastname!=") && strings[i + 1].contains("id!="))) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsOr(strings[i], strings[i + 1], "!=", "!=", result);
              } else if (strings[i].contains("lastname!=") && strings[i + 1].contains("id>") &&
                      !strings[i + 1].contains("=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsOr(strings[i], strings[i + 1], "!=", ">", result);
              } else if (strings[i].contains("lastname!=") && strings[i + 1].contains("id<") &&
                      !strings[i + 1].contains("=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsOr(strings[i], strings[i + 1], "!=", "<", result);
              } else if (strings[i].contains("lastname!=") && strings[i + 1].contains("id>=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsOr(strings[i], strings[i + 1], "!=", ">=", result);
              } else if (strings[i].contains("lastname!=") && strings[i + 1].contains("id<=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsOr(strings[i], strings[i + 1], "!=", "<=", result);
              } else if ((strings[i].contains("lastnamelike") && strings[i + 1].contains("id="))) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsOr(strings[i], strings[i + 1], "like", "=", result);
              } else if ((strings[i].contains("lastnamelike") && strings[i + 1].contains("id!="))) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsOr(strings[i], strings[i + 1], "like", "!=", result);
              } else if (strings[i].contains("lastnamelike") && strings[i + 1].contains("id>") &&
                      !strings[i + 1].contains("=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsOr(strings[i], strings[i + 1], "like", ">", result);
              } else if (strings[i].contains("lastnamelike") && strings[i + 1].contains("id<") &&
                      !strings[i + 1].contains("=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsOr(strings[i], strings[i + 1], "like", "<", result);
              } else if (strings[i].contains("lastnamelike") && strings[i + 1].contains("id>=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsOr(strings[i], strings[i + 1], "like", ">=", result);
              } else if (strings[i].contains("lastnamelike") && strings[i + 1].contains("id<=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsOr(strings[i], strings[i + 1], "like", "<=", result);
              } else if ((strings[i].contains("lastnameilike") && strings[i + 1].contains("id="))) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsOr(strings[i], strings[i + 1], "ilike", "=", result);
              } else if ((strings[i].contains("lastnameilike") && strings[i + 1].contains("id!="))) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsOr(strings[i], strings[i + 1], "ilike", "!=", result);
              } else if (strings[i].contains("lastnameilike") && strings[i + 1].contains("id>") &&
                      !strings[i + 1].contains("=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsOr(strings[i], strings[i + 1], "ilike", ">", result);
              } else if (strings[i].contains("lastnameilike") && strings[i + 1].contains("id<") &&
                      !strings[i + 1].contains("=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsOr(strings[i], strings[i + 1], "ilike", "<", result);
              } else if (strings[i].contains("lastnameilike") && strings[i + 1].contains("id>=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsOr(strings[i], strings[i + 1], "ilike", ">=", result);
              } else if (strings[i].contains("lastnameilike") && strings[i + 1].contains("id<=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsOr(strings[i], strings[i + 1], "ilike", "<=", result);
              }
              //Block lastName-age-or
              if ((strings[i].contains("lastname=") && strings[i + 1].contains("age="))) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsOr(strings[i], strings[i + 1], "=", "=", result);
              } else if ((strings[i].contains("lastname=") && strings[i + 1].contains("age!="))) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsOr(strings[i], strings[i + 1], "=", "!=", result);
              } else if (strings[i].contains("lastname=") && strings[i + 1].contains("age>") &&
                      !strings[i + 1].contains("=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsOr(strings[i], strings[i + 1], "=", ">", result);
              } else if (strings[i].contains("lastname=") && strings[i + 1].contains("age<") &&
                      !strings[i + 1].contains("=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsOr(strings[i], strings[i + 1], "=", "<", result);
              } else if (strings[i].contains("lastname=") && strings[i + 1].contains("age>=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsOr(strings[i], strings[i + 1], "=", ">=", result);
              } else if (strings[i].contains("lastname=") && strings[i + 1].contains("age<=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsOr(strings[i], strings[i + 1], "=", "<=", result);
              } else if ((strings[i].contains("lastname!=") && strings[i + 1].contains("age="))) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsOr(strings[i], strings[i + 1], "!=", "=", result);
              } else if ((strings[i].contains("lastname!=") && strings[i + 1].contains("age!="))) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsOr(strings[i], strings[i + 1], "!=", "!=", result);
              } else if (strings[i].contains("lastname!=") && strings[i + 1].contains("age>") &&
                      !strings[i + 1].contains("=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsOr(strings[i], strings[i + 1], "!=", ">", result);
              } else if (strings[i].contains("lastname!=") && strings[i + 1].contains("age<") &&
                      !strings[i + 1].contains("=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsOr(strings[i], strings[i + 1], "!=", "<", result);
              } else if (strings[i].contains("lastname!=") && strings[i + 1].contains("age>=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsOr(strings[i], strings[i + 1], "!=", ">=", result);
              } else if (strings[i].contains("lastname!=") && strings[i + 1].contains("age<=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsOr(strings[i], strings[i + 1], "!=", "<=", result);
              } else if ((strings[i].contains("lastnamelike") && strings[i + 1].contains("age="))) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsOr(strings[i], strings[i + 1], "like", "=", result);
              } else if ((strings[i].contains("lastnamelike") && strings[i + 1].contains("age!="))) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsOr(strings[i], strings[i + 1], "like", "!=", result);
              } else if (strings[i].contains("lastnamelike") && strings[i + 1].contains("age>") &&
                      !strings[i + 1].contains("=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsOr(strings[i], strings[i + 1], "like", ">", result);
              } else if (strings[i].contains("lastnamelike") && strings[i + 1].contains("age<") &&
                      !strings[i + 1].contains("=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsOr(strings[i], strings[i + 1], "like", "<", result);
              } else if (strings[i].contains("lastnamelike") && strings[i + 1].contains("age>=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsOr(strings[i], strings[i + 1], "like", ">=", result);
              } else if (strings[i].contains("lastnamelike") && strings[i + 1].contains("age<=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsOr(strings[i], strings[i + 1], "like", "<=", result);
              } else if ((strings[i].contains("lastnameilike") && strings[i + 1].contains("age="))) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsOr(strings[i], strings[i + 1], "ilike", "=", result);
              } else if ((strings[i].contains("lastnameilike") && strings[i + 1].contains("age!="))) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsOr(strings[i], strings[i + 1], "ilike", "!=", result);
              } else if (strings[i].contains("lastnameilike") && strings[i + 1].contains("age>") &&
                      !strings[i + 1].contains("=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsOr(strings[i], strings[i + 1], "ilike", ">", result);
              } else if (strings[i].contains("lastnameilike") && strings[i + 1].contains("age<") &&
                      !strings[i + 1].contains("=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsOr(strings[i], strings[i + 1], "ilike", "<", result);
              } else if (strings[i].contains("lastnameilike") && strings[i + 1].contains("age>=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsOr(strings[i], strings[i + 1], "ilike", ">=", result);
              } else if (strings[i].contains("lastnameilike") && strings[i + 1].contains("age<=")) {
                  return stringIntegerHandler.getMapStringIntegerMultipleConditionsOr(strings[i], strings[i + 1], "ilike", "<=", result);
              }
              // Block lastName-coast-or
              if ((strings[i].contains("lastname=") && strings[i + 1].contains("coast="))) {
                  return stringDoubleHandler.getMapStringDoubleMultipleConditionsOr(strings[i], strings[i + 1], "=", "=", result);
              } else if ((strings[i].contains("lastname=") && strings[i + 1].contains("coast!="))) {
                  return stringDoubleHandler.getMapStringDoubleMultipleConditionsOr(strings[i], strings[i + 1], "=", "!=", result);
              } else if (strings[i].contains("lastname=") && strings[i + 1].contains("coast>") &&
                      !strings[i + 1].contains("=")) {
                  return stringDoubleHandler.getMapStringDoubleMultipleConditionsOr(strings[i], strings[i + 1], "=", ">", result);
              } else if (strings[i].contains("lastname=") && strings[i + 1].contains("coast<") &&
                      !strings[i + 1].contains("=")) {
                  return stringDoubleHandler.getMapStringDoubleMultipleConditionsOr(strings[i], strings[i + 1], "=", "<", result);
              } else if (strings[i].contains("lastname=") && strings[i + 1].contains("coast>=")) {
                  return stringDoubleHandler.getMapStringDoubleMultipleConditionsOr(strings[i], strings[i + 1], "=", ">=", result);
              } else if (strings[i].contains("lastname=") && strings[i + 1].contains("coast<=")) {
                  return stringDoubleHandler.getMapStringDoubleMultipleConditionsOr(strings[i], strings[i + 1], "=", "<=", result);
              } else if ((strings[i].contains("lastname!=") && strings[i + 1].contains("coast="))) {
                  return stringDoubleHandler.getMapStringDoubleMultipleConditionsOr(strings[i], strings[i + 1], "!=", "=", result);
              } else if ((strings[i].contains("lastname!=") && strings[i + 1].contains("coast!="))) {
                  return stringDoubleHandler.getMapStringDoubleMultipleConditionsOr(strings[i], strings[i + 1], "!=", "!=", result);
              } else if (strings[i].contains("lastname!=") && strings[i + 1].contains("coast>") &&
                      !strings[i + 1].contains("=")) {
                  return stringDoubleHandler.getMapStringDoubleMultipleConditionsOr(strings[i], strings[i + 1], "!=", ">", result);
              } else if (strings[i].contains("lastname!=") && strings[i + 1].contains("coast<") &&
                      !strings[i + 1].contains("=")) {
                  return stringDoubleHandler.getMapStringDoubleMultipleConditionsOr(strings[i], strings[i + 1], "!=", "<", result);
              } else if (strings[i].contains("lastname!=") && strings[i + 1].contains("coast>=")) {
                  return stringDoubleHandler.getMapStringDoubleMultipleConditionsOr(strings[i], strings[i + 1], "!=", ">=", result);
              } else if (strings[i].contains("lastname!=") && strings[i + 1].contains("coast<=")) {
                  return stringDoubleHandler.getMapStringDoubleMultipleConditionsOr(strings[i], strings[i + 1], "!=", "<=", result);
              } else if ((strings[i].contains("lastnamelike") && strings[i + 1].contains("coast="))) {
                  return stringDoubleHandler.getMapStringDoubleMultipleConditionsOr(strings[i], strings[i + 1], "like", "=", result);
              } else if ((strings[i].contains("lastnamelike") && strings[i + 1].contains("coast!="))) {
                  return stringDoubleHandler.getMapStringDoubleMultipleConditionsOr(strings[i], strings[i + 1], "like", "!=", result);
              } else if (strings[i].contains("lastnamelike") && strings[i + 1].contains("coast>") &&
                      !strings[i + 1].contains("=")) {
                  return stringDoubleHandler.getMapStringDoubleMultipleConditionsOr(strings[i], strings[i + 1], "like", ">", result);
              } else if (strings[i].contains("lastnamelike") && strings[i + 1].contains("coast<") &&
                      !strings[i + 1].contains("=")) {
                  return stringDoubleHandler.getMapStringDoubleMultipleConditionsOr(strings[i], strings[i + 1], "like", "<", result);
              } else if (strings[i].contains("lastnamelike") && strings[i + 1].contains("coast>=")) {
                  return stringDoubleHandler.getMapStringDoubleMultipleConditionsOr(strings[i], strings[i + 1], "like", ">=", result);
              } else if (strings[i].contains("lastnamelike") && strings[i + 1].contains("coast<=")) {
                  return stringDoubleHandler.getMapStringDoubleMultipleConditionsOr(strings[i], strings[i + 1], "like", "<=", result);
              } else if ((strings[i].contains("lastnameilike") && strings[i + 1].contains("coast="))) {
                  return stringDoubleHandler.getMapStringDoubleMultipleConditionsOr(strings[i], strings[i + 1], "ilike", "=", result);
              } else if ((strings[i].contains("lastnameilike") && strings[i + 1].contains("coast!="))) {
                  return stringDoubleHandler.getMapStringDoubleMultipleConditionsOr(strings[i], strings[i + 1], "ilike", "!=", result);
              } else if (strings[i].contains("lastnameilike") && strings[i + 1].contains("coast>") &&
                      !strings[i + 1].contains("=")) {
                  return stringDoubleHandler.getMapStringDoubleMultipleConditionsOr(strings[i], strings[i + 1], "ilike", ">", result);
              } else if (strings[i].contains("lastnameilike") && strings[i + 1].contains("coast<") &&
                      !strings[i + 1].contains("=")) {
                  return stringDoubleHandler.getMapStringDoubleMultipleConditionsOr(strings[i], strings[i + 1], "ilike", "<", result);
              } else if (strings[i].contains("lastnameilike") && strings[i + 1].contains("coast>=")) {
                  return stringDoubleHandler.getMapStringDoubleMultipleConditionsOr(strings[i], strings[i + 1], "ilike", ">=", result);
              } else if (strings[i].contains("lastnameilike") && strings[i + 1].contains("coast<=")) {
                  return stringDoubleHandler.getMapStringDoubleMultipleConditionsOr(strings[i], strings[i + 1], "ilike", "<=", result);
              }
              // Block lastName-active-or
              if ((strings[i].contains("lastname=") && strings[i + 1].contains("active="))) {
                  return stringBooleanHandler.getMapStringBooleanMultipleConditionsOr(strings[i], strings[i + 1], "=", "=", result);
              } else if ((strings[i].contains("lastname=") && strings[i + 1].contains("active!="))) {
                  return stringBooleanHandler.getMapStringBooleanMultipleConditionsOr(strings[i], strings[i + 1], "=", "!=", result);
              } else if ((strings[i].contains("lastname!=") && strings[i + 1].contains("active="))) {
                  return stringBooleanHandler.getMapStringBooleanMultipleConditionsOr(strings[i], strings[i + 1], "!=", "=", result);
              } else if ((strings[i].contains("lastname!=") && strings[i + 1].contains("active!="))) {
                  return stringBooleanHandler.getMapStringBooleanMultipleConditionsOr(strings[i], strings[i + 1], "!=", "!=", result);
              } else if ((strings[i].contains("lastnamelike") && strings[i + 1].contains("active="))) {
                  return stringBooleanHandler.getMapStringBooleanMultipleConditionsOr(strings[i], strings[i + 1], "like", "=", result);
              } else if ((strings[i].contains("lastnamelike") && strings[i + 1].contains("active!="))) {
                  return stringBooleanHandler.getMapStringBooleanMultipleConditionsOr(strings[i], strings[i + 1], "like", "!=", result);
              } else if ((strings[i].contains("lastnameilike") && strings[i + 1].contains("active="))) {
                  return stringBooleanHandler.getMapStringBooleanMultipleConditionsOr(strings[i], strings[i + 1], "ilike", "=", result);
              } else if ((strings[i].contains("lastnameilike") && strings[i + 1].contains("active!="))) {
                  return stringBooleanHandler.getMapStringBooleanMultipleConditionsOr(strings[i], strings[i + 1], "ilike", "!=", result);
              }
                  //Block active-id-or
              if ((strings[i].contains("active=") && strings[i + 1].contains("id="))) {
                  return booleanIntegerHandler.getMapBooleanIntegerMultipleConditionsOr(strings[i], strings[i + 1], "=", "=", result);
              } else if ((strings[i].contains("active=") && strings[i + 1].contains("id!="))) {
                  return booleanIntegerHandler.getMapBooleanIntegerMultipleConditionsOr(strings[i], strings[i + 1], "=", "!=", result);
              } else if (strings[i].contains("active=") && strings[i + 1].contains("id>") &&
                      !strings[i + 1].contains("=")) {
                  return booleanIntegerHandler.getMapBooleanIntegerMultipleConditionsOr(strings[i], strings[i + 1], "=", ">", result);
              } else if (strings[i].contains("active=") && strings[i + 1].contains("id<") &&
                      !strings[i + 1].contains("=")) {
                  return booleanIntegerHandler.getMapBooleanIntegerMultipleConditionsOr(strings[i], strings[i + 1], "=", "<", result);
              } else if (strings[i].contains("active=") && strings[i + 1].contains("id>=")) {
                  return booleanIntegerHandler.getMapBooleanIntegerMultipleConditionsOr(strings[i], strings[i + 1], "=", ">=", result);
              } else if (strings[i].contains("active=") && strings[i + 1].contains("id<=")) {
                  return booleanIntegerHandler.getMapBooleanIntegerMultipleConditionsOr(strings[i], strings[i + 1], "=", "<=", result);
              } else if ((strings[i].contains("active!=") && strings[i + 1].contains("id="))) {
                  return booleanIntegerHandler.getMapBooleanIntegerMultipleConditionsOr(strings[i], strings[i + 1], "!=", "=", result);
              } else if ((strings[i].contains("active!=") && strings[i + 1].contains("id!="))) {
                  return booleanIntegerHandler.getMapBooleanIntegerMultipleConditionsOr(strings[i], strings[i + 1], "!=", "!=", result);
              } else if (strings[i].contains("active!=") && strings[i + 1].contains("id>") &&
                      !strings[i + 1].contains("=")) {
                  return booleanIntegerHandler.getMapBooleanIntegerMultipleConditionsOr(strings[i], strings[i + 1], "!=", ">", result);
              } else if (strings[i].contains("active!=") && strings[i + 1].contains("id<") &&
                      !strings[i + 1].contains("=")) {
                  return booleanIntegerHandler.getMapBooleanIntegerMultipleConditionsOr(strings[i], strings[i + 1], "!=", "<", result);
              } else if (strings[i].contains("active!=") && strings[i + 1].contains("id>=")) {
                  return booleanIntegerHandler.getMapBooleanIntegerMultipleConditionsOr(strings[i], strings[i + 1], "!=", ">=", result);
              } else if (strings[i].contains("active!=") && strings[i + 1].contains("id<=")) {
                  return booleanIntegerHandler.getMapBooleanIntegerMultipleConditionsOr(strings[i], strings[i + 1], "!=", "<=", result);
              }
                  // Block active-age-or
              if ((strings[i].contains("active=") && strings[i + 1].contains("age="))) {
                  return booleanIntegerHandler.getMapBooleanIntegerMultipleConditionsOr(strings[i], strings[i + 1], "=", "=", result);
              } else if ((strings[i].contains("active=") && strings[i + 1].contains("age!="))) {
                  return booleanIntegerHandler.getMapBooleanIntegerMultipleConditionsOr(strings[i], strings[i + 1], "=", "!=", result);
              } else if (strings[i].contains("active=") && strings[i + 1].contains("age>") &&
                      !strings[i + 1].contains("=")) {
                  return booleanIntegerHandler.getMapBooleanIntegerMultipleConditionsOr(strings[i], strings[i + 1], "=", ">", result);
              } else if (strings[i].contains("active=") && strings[i + 1].contains("age<") &&
                      !strings[i + 1].contains("=")) {
                  return booleanIntegerHandler.getMapBooleanIntegerMultipleConditionsOr(strings[i], strings[i + 1], "=", "<", result);
              } else if (strings[i].contains("active=") && strings[i + 1].contains("age>=")) {
                  return booleanIntegerHandler.getMapBooleanIntegerMultipleConditionsOr(strings[i], strings[i + 1], "=", ">=", result);
              } else if (strings[i].contains("active=") && strings[i + 1].contains("age<=")) {
                  return booleanIntegerHandler.getMapBooleanIntegerMultipleConditionsOr(strings[i], strings[i + 1], "=", "<=", result);
              } else if ((strings[i].contains("active!=") && strings[i + 1].contains("age="))) {
                  return booleanIntegerHandler.getMapBooleanIntegerMultipleConditionsOr(strings[i], strings[i + 1], "!=", "=", result);
              } else if ((strings[i].contains("active!=") && strings[i + 1].contains("age!="))) {
                  return booleanIntegerHandler.getMapBooleanIntegerMultipleConditionsOr(strings[i], strings[i + 1], "!=", "!=", result);
              } else if (strings[i].contains("active!=") && strings[i + 1].contains("age>") &&
                      !strings[i + 1].contains("=")) {
                  return booleanIntegerHandler.getMapBooleanIntegerMultipleConditionsOr(strings[i], strings[i + 1], "!=", ">", result);
              } else if (strings[i].contains("active!=") && strings[i + 1].contains("age<") &&
                      !strings[i + 1].contains("=")) {
                  return booleanIntegerHandler.getMapBooleanIntegerMultipleConditionsOr(strings[i], strings[i + 1], "!=", "<", result);
              } else if (strings[i].contains("active!=") && strings[i + 1].contains("age>=")) {
                  return booleanIntegerHandler.getMapBooleanIntegerMultipleConditionsOr(strings[i], strings[i + 1], "!=", ">=", result);
              } else if (strings[i].contains("active!=") && strings[i + 1].contains("age<=")) {
                  return booleanIntegerHandler.getMapBooleanIntegerMultipleConditionsOr(strings[i], strings[i + 1], "!=", "<=", result);
              }
                  //Block active-coast-or
              if ((strings[i].contains("active=") && strings[i + 1].contains("coast="))) {
                  return booleanDoubleHandler.getMapBooleanDoubleMultipleConditionsOr(strings[i], strings[i + 1], "=", "=", result);
              } else if ((strings[i].contains("active=") && strings[i + 1].contains("coast!="))) {
                  return booleanDoubleHandler.getMapBooleanDoubleMultipleConditionsOr(strings[i], strings[i + 1], "=", "!=", result);
              } else if (strings[i].contains("active=") && strings[i + 1].contains("coast>") &&
                      !strings[i + 1].contains("=")) {
                  return booleanDoubleHandler.getMapBooleanDoubleMultipleConditionsOr(strings[i], strings[i + 1], "=", ">", result);
              } else if (strings[i].contains("active=") && strings[i + 1].contains("coast<") &&
                      !strings[i + 1].contains("=")) {
                  return booleanDoubleHandler.getMapBooleanDoubleMultipleConditionsOr(strings[i], strings[i + 1], "=", "<", result);
              } else if (strings[i].contains("active=") && strings[i + 1].contains("coast>=")) {
                  return booleanDoubleHandler.getMapBooleanDoubleMultipleConditionsOr(strings[i], strings[i + 1], "=", ">=", result);
              } else if (strings[i].contains("active=") && strings[i + 1].contains("coast<=")) {
                  return booleanDoubleHandler.getMapBooleanDoubleMultipleConditionsOr(strings[i], strings[i + 1], "=", "<=", result);
              } else if ((strings[i].contains("active!=") && strings[i + 1].contains("coast="))) {
                  return booleanDoubleHandler.getMapBooleanDoubleMultipleConditionsOr(strings[i], strings[i + 1], "!=", "=", result);
              } else if ((strings[i].contains("active!=") && strings[i + 1].contains("coast!="))) {
                  return booleanDoubleHandler.getMapBooleanDoubleMultipleConditionsOr(strings[i], strings[i + 1], "!=", "!=", result);
              } else if (strings[i].contains("active!=") && strings[i + 1].contains("coast>") &&
                      !strings[i + 1].contains("=")) {
                  return booleanDoubleHandler.getMapBooleanDoubleMultipleConditionsOr(strings[i], strings[i + 1], "!=", ">", result);
              } else if (strings[i].contains("active!=") && strings[i + 1].contains("coast<") &&
                      !strings[i + 1].contains("=")) {
                  return booleanDoubleHandler.getMapBooleanDoubleMultipleConditionsOr(strings[i], strings[i + 1], "!=", "<", result);
              } else if (strings[i].contains("active!=") && strings[i + 1].contains("coast>=")) {
                  return booleanDoubleHandler.getMapBooleanDoubleMultipleConditionsOr(strings[i], strings[i + 1], "!=", ">=", result);
              } else if (strings[i].contains("active!=") && strings[i + 1].contains("coast<=")) {
                  return booleanDoubleHandler.getMapBooleanDoubleMultipleConditionsOr(strings[i], strings[i + 1], "!=", "<=", result);
              }
                  // Block active-lastName-or
              if ((strings[i].contains("active=") && strings[i + 1].contains("lastname="))) {
                  return booleanStringHandler.getMapBooleanStringMultipleConditionsOr(strings[i], strings[i + 1], "=", "=", result);
              } else if ((strings[i].contains("active=") && strings[i + 1].contains("lastname!="))) {
                  return booleanStringHandler.getMapBooleanStringMultipleConditionsOr(strings[i], strings[i + 1], "=", "!=", result);
              } else if (strings[i].contains("active=") && strings[i + 1].contains("lastnamelike")) {
                  return booleanStringHandler.getMapBooleanStringMultipleConditionsOr(strings[i], strings[i + 1], "=", "like", result);
              } else if (strings[i].contains("active=") && strings[i + 1].contains("lastnameilike")) {
                  return booleanStringHandler.getMapBooleanStringMultipleConditionsOr(strings[i], strings[i + 1], "=", "ilike", result);
              } else if ((strings[i].contains("active!=") && strings[i + 1].contains("lastname="))) {
                  return booleanStringHandler.getMapBooleanStringMultipleConditionsOr(strings[i], strings[i + 1], "!=", "=", result);
              } else if ((strings[i].contains("active!=") && strings[i + 1].contains("lastname!="))) {
                  return booleanStringHandler.getMapBooleanStringMultipleConditionsOr(strings[i], strings[i + 1], "!=", "!=", result);
              } else if (strings[i].contains("active!=") && strings[i + 1].contains("lastnamelike")) {
                  return booleanStringHandler.getMapBooleanStringMultipleConditionsOr(strings[i], strings[i + 1], "!=", "like", result);
              } else if (strings[i].contains("active!=") && strings[i + 1].contains("lastnameilike")) {
                  return booleanStringHandler.getMapBooleanStringMultipleConditionsOr(strings[i], strings[i + 1], "!=", "ilike", result);
              } else {
                  throw new RuntimeException("The request contains an invalid parameter name or invalid character");
              }
          }
          return new ArrayList<>();
  } else {
          System.out.println("Incorrect method request");
          return new ArrayList<>();
      }
  }

  private void nullValuesValidation(String string) {
      if (!string.contains("id")) {
          row.put("id", null);
      }
      if (!string.contains("lastname")) {
          row.put("lastName", null);
      }
      if (!string.contains("age")) {
          row.put("age", null);
      }
      if (!string.contains("coast")) {
          row.put("coast", null);
      }
      if (!string.contains("active")) {
          row.put("active", null);
      }
      if (!string.contains("id") && !string.contains("lastname") && !string.contains("age")
              && !string.contains("active") && !string.contains("coast")) {
          throw new RuntimeException("All parameters are null, plz enter some parameters");
      }
  }

  private void insertIntegerValidation(String[] strings, Map<String,Object> row) {
      if(formatDataValidator.isLong(strings[1])) {
          row.put(strings[0], Long.parseLong(strings[1]));
      } else {
          throw new NumberFormatException("Invalid data insert format!");
      }
  }

  private void insertDoubleValidation(String[] strings, Map<String,Object> row) {
      if(formatDataValidator.isDouble(strings[1])) {
          row.put(strings[0], Double.parseDouble(strings[1]));
      } else {
          throw new NumberFormatException("Invalid data insert format!");
      }
  }


  private void insertStringValidation(String[] strings, Map<String,Object> row) {
      if(formatDataValidator.isString(strings[1])) {
          row.put("lastName", strings[1]);
      } else {
          throw new NumberFormatException("Invalid data insert format!");
      }
  }

  private void insertBooleanValidation(String[] strings, Map<String,Object> row) {
      if(formatDataValidator.isBoolean(strings[1])) {
          row.put(strings[0], Boolean.parseBoolean(strings[1]));
      } else {
          throw new NumberFormatException("Invalid data insert format!");
      }
  }
}
