package com.solarwinds.util.enums;

import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.lang.reflect.Field;

final class Helper extends ClassValue<Map<Object,Object>> {
    static final Helper INSTANCE = new Helper();

    @Override 
    protected Map<Object, Object> computeValue(Class<?> type) {
        Map<Object,Object> m = new HashMap<>();
        for(Field f: type.getDeclaredFields()) {
            if(f.isEnumConstant())
            try {
                Object constant = f.get(null);
                //Integer id = f.getAnnotation(IStatus.class).value();
                IStatus status = f.getAnnotation(IStatus.class);
                m.put(status.value(), constant);
                m.put(constant, status);
            }
            catch(IllegalAccessException ex) {
                throw new IllegalStateException(ex);
            }
        }
        return Collections.unmodifiableMap(m);
    }
}

public interface IStatusEnum {
    String name();
    Class<? extends Enum<?>> getDeclaringClass(); 
    static <T extends Enum<T>&IStatusEnum> T fromDb(Class<T> type, int id) {
        return type.cast(Helper.INSTANCE.get(type).get(id));
    }
    default int toDb() {
        return ((IStatus)Helper.INSTANCE.get(getDeclaringClass()).get(this)).value();
    }
    default String getFullname() {
        return getDeclaringClass().getSimpleName() + "." + name();
    }
    default String getDesc() {
        return ((IStatus)Helper.INSTANCE.get(getDeclaringClass()).get(this)).desc();
    }
}