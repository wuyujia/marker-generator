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
public class CloudOrderBuyUtil {

    private Map<String, Object> param = new HashMap<String, Object>();

    /**私有构造方法*/
    private CloudOrderBuyUtil(){}

    public static CloudOrderBuyUtil build() {
        return new CloudOrderBuyUtil();
    }

    public CloudOrderBuyUtil orderBuyId(Integer orderBuyId) {
        this.param.put("orderBuyId", orderBuyId);
        return this;
    }

    public CloudOrderBuyUtil orderBuyCode(String orderBuyCode) {
        this.param.put("orderBuyCode", orderBuyCode);
        return this;
    }

    public CloudOrderBuyUtil orderId(Integer orderId) {
        this.param.put("orderId", orderId);
        return this;
    }

    public CloudOrderBuyUtil buyId(Integer buyId) {
        this.param.put("buyId", buyId);
        return this;
    }

    public CloudOrderBuyUtil buyerName(String buyerName) {
        this.param.put("buyerName", buyerName);
        return this;
    }

    public CloudOrderBuyUtil sellerId(Integer sellerId) {
        this.param.put("sellerId", sellerId);
        return this;
    }

    public CloudOrderBuyUtil sellerName(String sellerName) {
        this.param.put("sellerName", sellerName);
        return this;
    }

    public CloudOrderBuyUtil price(BigDecimal price) {
        this.param.put("price", price);
        return this;
    }

    public CloudOrderBuyUtil buyCount(Integer buyCount) {
        this.param.put("buyCount", buyCount);
        return this;
    }

    public CloudOrderBuyUtil presentCount(Integer presentCount) {
        this.param.put("presentCount", presentCount);
        return this;
    }

    public CloudOrderBuyUtil totalFee(BigDecimal totalFee) {
        this.param.put("totalFee", totalFee);
        return this;
    }

    public CloudOrderBuyUtil totalPay(BigDecimal totalPay) {
        this.param.put("totalPay", totalPay);
        return this;
    }

    public CloudOrderBuyUtil totalDiscount(BigDecimal totalDiscount) {
        this.param.put("totalDiscount", totalDiscount);
        return this;
    }

    public CloudOrderBuyUtil type(Integer type) {
        this.param.put("type", type);
        return this;
    }

    public CloudOrderBuyUtil status(Integer status) {
        this.param.put("status", status);
        return this;
    }

    public CloudOrderBuyUtil channel(Integer channel) {
        this.param.put("channel", channel);
        return this;
    }

    public CloudOrderBuyUtil warehouseId(Integer warehouseId) {
        this.param.put("warehouseId", warehouseId);
        return this;
    }

    public CloudOrderBuyUtil creater(String creater) {
        this.param.put("creater", creater);
        return this;
    }

    public CloudOrderBuyUtil updater(String updater) {
        this.param.put("updater", updater);
        return this;
    }

    public CloudOrderBuyUtil createTime(Integer createTime) {
        this.param.put("createTime", createTime);
        return this;
    }

    public CloudOrderBuyUtil updateTime(Integer updateTime) {
        this.param.put("updateTime", updateTime);
        return this;
    }

    public CloudOrderBuyUtil isDelete(Long isDelete) {
        this.param.put("isDelete", isDelete);
        return this;
    }

    public CloudOrderBuyUtil remark(String remark) {
        this.param.put("remark", remark);
        return this;
    }

    public CloudOrderBuyUtil hopeTime(Integer hopeTime) {
        this.param.put("hopeTime", hopeTime);
        return this;
    }

    public CloudOrderBuyUtil address(String address) {
        this.param.put("address", address);
        return this;
    }

    public CloudOrderBuyUtil addressId(Integer addressId) {
        this.param.put("addressId", addressId);
        return this;
    }

    public CloudOrderBuyUtil mobile(String mobile) {
        this.param.put("mobile", mobile);
        return this;
    }

    public CloudOrderBuyUtil contact(String contact) {
        this.param.put("contact", contact);
        return this;
    }

    public CloudOrderBuyUtil deliveryMan(String deliveryMan) {
        this.param.put("deliveryMan", deliveryMan);
        return this;
    }

    public CloudOrderBuyUtil deliveryTime(Integer deliveryTime) {
        this.param.put("deliveryTime", deliveryTime);
        return this;
    }

    public CloudOrderBuyUtil picker(String picker) {
        this.param.put("picker", picker);
        return this;
    }

    public CloudOrderBuyUtil pickTime(Integer pickTime) {
        this.param.put("pickTime", pickTime);
        return this;
    }

    public CloudOrderBuyUtil checkMan(String checkMan) {
        this.param.put("checkMan", checkMan);
        return this;
    }

    public CloudOrderBuyUtil checkTime(Integer checkTime) {
        this.param.put("checkTime", checkTime);
        return this;
    }

    public CloudOrderBuyUtil stockStatus(Integer stockStatus) {
        this.param.put("stockStatus", stockStatus);
        return this;
    }

    public CloudOrderBuyUtil payStatus(Integer payStatus) {
        this.param.put("payStatus", payStatus);
        return this;
    }

    public CloudOrderBuyUtil receiveTime(Integer receiveTime) {
        this.param.put("receiveTime", receiveTime);
        return this;
    }


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

    public <O> CloudOrderBuyUtil putParam(O obj) {
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