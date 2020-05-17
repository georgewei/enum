package com.solarwinds.biz.enums;

import com.solarwinds.util.enums.IStatusEnum;
import com.solarwinds.util.enums.ITypeEnum;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility for enum types which implements interface IStatusEnum or ITypeEnum
 */
public class EnumUtil {

    public static <T extends Enum<T> & IStatusEnum> T fromDb(Class<T> enumClass, int dbValue) {
        return IStatusEnum.fromDb(enumClass, dbValue);
    }
    public static <T extends Enum<T> & ITypeEnum> T fromDb(Class<T> enumClass, String dbValue) {
        return ITypeEnum.fromDb(enumClass, dbValue);
    }

    public static String KEY_NAME_FOR_VALUE = "valueKeyName";
    public static String KEY_NAME_FOR_DESC = "descKeyName";
    private static Map<String, String> DEFAULT_MAPPED_KEYNAMES = new HashMap<String, String>(){{
        put(KEY_NAME_FOR_VALUE, "code");
        put(KEY_NAME_FOR_DESC, "name");
        }};
    /*
     * Encapsulates all values of an enum and returns as a list of map use default mapped key names
     *
     * @param enumClass The enum class which implements IStatusEnum to be casted
     * @return List contains enum values, each value was encapsulated into a map which has keys named 'code' and 'name'
     */
    public static <T extends Enum<T> & IStatusEnum> List<Map<String, Object>> getValueList(Class<T> enumClass) {
        return getValueList(enumClass, DEFAULT_MAPPED_KEYNAMES);
    }
    /*
     * Encapsulates all values of an enum and returns as a list of map use customized mapped key names
     *
     * @param enumClass The enum class which implements IStatusEnum to be casted
     * @param mappedKeyNames Customized mapped key names of enum's value & desc properties
     * @return List contains enum values, each value was encapsulated into a map, the mapped key names are customized by parameter mappedKeyNames
     */
    public static <T extends Enum<T> & IStatusEnum> List<Map<String, Object>> getValueList(Class<T> enumClass,
                                                                                           Map<String, String> mappedKeyNames) {
        CheckMappedKeyNames(mappedKeyNames);
        T[] values = getEnumValues(enumClass);

        List<Map<String, Object>> result = new ArrayList<>();
        for (T value: values) {
            result.add(enumValueToMap(value.toDb(), value.getDesc(), mappedKeyNames));
        }

        return result;
    }

    /*
     * Encapsulates all values of an enum and returns as a list of map use default mapped key names
     *
     * @param enumClass The enum class which implements ITypeEnum to be casted
     * @param dummy A dummy parameter just used to distinguish with another method with the same name, can be any int value
     * @return List contains enum values, each value was encapsulated into a map which has keys named 'code' and 'name'
     */
    public static <T extends Enum<T> & ITypeEnum> List<Map<String, Object>> getValueList(Class<T> enumClass, int dummy) {
        return getValueList(enumClass, DEFAULT_MAPPED_KEYNAMES, dummy);
    }
    /*
     * Encapsulates all values of an enum and returns as a list of map use customized mapped key names
     *
     * @param enumClass The enum class which implements ITypeEnum to be casted
     * @param mappedKeyNames Customized mapped key names of enum's value & desc properties
     * @param dummy A dummy parameter just used to distinguish with another method with the same name, can be any int value
     * @return List contains enum values, each value was encapsulated into a map, the mapped key names are customized by parameter mappedKeyNames
     */
    public static <T extends Enum<T> & ITypeEnum> List<Map<String, Object>> getValueList(Class<T> enumClass,
                                                                                         Map<String, String> mappedKeyNames,
                                                                                         int dummy) {
        CheckMappedKeyNames(mappedKeyNames);
        T[] values = getEnumValues(enumClass);

        List<Map<String, Object>> result = new ArrayList<>();
        for (T value: values) {
            result.add(enumValueToMap(value.toDb(), value.getDesc(), mappedKeyNames));
        }

        return result;
    }

    private static void CheckMappedKeyNames(Map<String, String> mappedKeyNames) {
        if (mappedKeyNames != DEFAULT_MAPPED_KEYNAMES){
            if (!mappedKeyNames.containsKey(KEY_NAME_FOR_VALUE) ||
                    !mappedKeyNames.containsKey(KEY_NAME_FOR_DESC))
                throw new RuntimeException(String.format("Incorrect definition of mapped key names. Keys named '%s' and '%s' required.", KEY_NAME_FOR_VALUE, KEY_NAME_FOR_DESC));
        }
    }
    private static Map<String, Object> enumValueToMap(Object dbValue, String desc,
                                                      Map<String, String> mappedKeyNames) {
        Map<String, Object> map = new HashMap<>();
        map.put(mappedKeyNames.get(KEY_NAME_FOR_VALUE), dbValue);
        map.put(mappedKeyNames.get(KEY_NAME_FOR_DESC), desc);
        return map;
    }

    @SuppressWarnings("unchecked")
    private static <T extends Enum<T>> T[] getEnumValues(Class<T> enumClass) {
        if (!enumClass.isEnum())
            throw new RuntimeException("Parameter type mismatch: An enum class required");

        try {
            //Each enum class has a static method named 'values' added by the compiler
            Method getValues = enumClass.getDeclaredMethod("values");
            return (T[])getValues.invoke(null, (Object[]) null);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("That's impossible! An enum hasn't a method named \"values\"");
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException("That's impossible! Failed to invoke an enum's \"values\" method");
        }
    }
}
