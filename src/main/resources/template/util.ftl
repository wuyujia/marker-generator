package com.cloud.dao.sql.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.math.BigDecimal;
import org.apache.commons.beanutils.BeanUtils;

/** Create By Mr.Black
 *  The more you try, The more you get!
 */
@SuppressWarnings("all")
public class ${table_name}Util {

    private Map<String, Object> param = new HashMap<String, Object>();

    /**私有构造方法*/
    private ${table_name}Util(){}

    public static ${table_name}Util build() {
        return new ${table_name}Util();
    }

    <#list column_list as column>
    public ${table_name}Util ${column.method}(${column.data_type} ${column.var}) {
        this.param.put("${column.column}", ${column.var});
        return this;
    }

    </#list>

    public Map<String, Object> getMap(){
        return this.param;
    }

    /**吞掉异常, 如果拷贝错误就返回null值*/
    public <T> T getBean(Class<T> clazz) {
        try {
            T obj = clazz.newInstance();
            BeanUtils.copyProperties(obj, this.param);
            return obj;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**吞掉异常, 拷贝失败返回null值, 应用场景是*/
    public <T> T copyProperties(Object src, Class<T> dest) {
        try {
            T t = dest.newInstance();
            BeanUtils.copyProperties(t,src);
            return t;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <O> ${table_name}Util putParam(O obj) {
        if (obj == null)
            return null;

        Class clazz = this.getClass();
        Method[] methods = clazz.getMethods();
        Map<String, Method> methodsMap = new HashMap<>();
        for (Method method : methods) {
            String name = method.getName();
            if (name.equals("copyProperties") || name.equals("getBean") || name.equals("getMap") || name.equals("putParam") || name.equals("build")) {
                continue;
            }
            methodsMap.put(name, method);
        }
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            String name = field.getName();
            if (methodsMap.containsKey(name)) {
                Method method = methodsMap.get(name);
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length != 1) {
                    continue;
                }
                if (parameterTypes[0].getSimpleName().equals(field.getType().getSimpleName())) {
                    try {
                        if (field.get(obj) != null)
                            method.invoke(this, field.get(obj));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return this;
    }
}