package com.solarwinds.util.enums;

import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.lang.reflect.Field;

final class TypeEnumHelper extends ClassValue<Map<Object,Object>> {
    static final TypeEnumHelper INSTANCE = new TypeEnumHelper();

    @Override 
    protected Map<Object, Object> computeValue(Class<?> enumClass) {
        Map<Object,Object> m = new HashMap<>();
        for(Field f: enumClass.getDeclaredFields()) {
            if(f.isEnumConstant())
            try {
                Object constant = f.get(null);
                IType type = f.getAnnotation(IType.class);
                m.put(type.value().toUpperCase(), constant);
                m.put(constant, type);
            }
            catch(IllegalAccessException e) {
                throw new IllegalStateException(e);
            }
        }
        return Collections.unmodifiableMap(m);
    }
}

public interface ITypeEnum {
    String name();
    Class<? extends Enum<?>> getDeclaringClass(); 
    static <T extends Enum<T>&ITypeEnum> T fromDb(Class<T> enumClass, String dbValue) {
        if (null == dbValue)
            return null;
        return enumClass.cast(TypeEnumHelper.INSTANCE.get(enumClass).get(dbValue.toUpperCase()));
    }
    default String toDb() {
        return ((IType)TypeEnumHelper.INSTANCE.get(getDeclaringClass()).get(this)).value();
    }
    default boolean equalsToDb(String dbValue) {
        return toDb().equalsIgnoreCase(dbValue);
    }
    default String getFullname() {
        return getDeclaringClass().getSimpleName() + "." + name();
    }
    default String getDesc() {
        return ((IType)TypeEnumHelper.INSTANCE.get(getDeclaringClass()).get(this)).desc();
    }
}