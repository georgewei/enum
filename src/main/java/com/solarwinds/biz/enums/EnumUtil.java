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
    /*
     * Encapsulates all values of an enum and returns as a list of map
     *
     * @param enumClass The enum class which implements IStatusEnum to be casted
     * @return List contains enum values, each value was encapsulated into a map which has keys named 'code' and 'name'
     */
    public static <T extends Enum<T> & IStatusEnum> List<Map<String, Object>> getValueList(Class<T> enumClass) {
        T[] values = getEnumValues(enumClass);

        List<Map<String, Object>> result = new ArrayList<>();
        for (T value: values) {
            result.add(enumValueToMap(value.toDb(), value.getDesc()));
        }

        return result;
    }

    /*
     * Encapsulates all values of an enum and returns as a list of map
     *
     * @param enumClass The enum class which implements ITypeEnum to be casted
     * @param dummy A dummy parameter just used to distinguish with another method with the same name, can be any int value
     * @return List contains enum values, each value was encapsulated into a map which has keys named 'code' and 'name'
     */
    public static <T extends Enum<T> & ITypeEnum> List<Map<String, Object>> getValueList(Class<T> enumClass, int dummy) {
        T[] values = getEnumValues(enumClass);

        List<Map<String, Object>> result = new ArrayList<>();
        for (T value: values) {
            result.add(enumValueToMap(value.toDb(), value.getDesc()));
        }

        return result;
    }

    private static <T extends Enum<T> & ITypeEnum> Map<String, Object> enumValueToMap(Object dbValue, String desc) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", dbValue);
        map.put("name", desc);
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
