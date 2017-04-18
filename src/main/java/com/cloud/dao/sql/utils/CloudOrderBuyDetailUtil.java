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
public class CloudOrderBuyDetailUtil {

    private Map<String, Object> param = new HashMap<String, Object>();

    /**私有构造方法*/
    private CloudOrderBuyDetailUtil(){}

    public static CloudOrderBuyDetailUtil build() {
        return new CloudOrderBuyDetailUtil();
    }

    public CloudOrderBuyDetailUtil orderBuyDetailId(Integer orderBuyDetailId) {
        this.param.put("orderBuyDetailId", orderBuyDetailId);
        return this;
    }

    public CloudOrderBuyDetailUtil orderBuyId(Integer orderBuyId) {
        this.param.put("orderBuyId", orderBuyId);
        return this;
    }

    public CloudOrderBuyDetailUtil orderBuyCode(String orderBuyCode) {
        this.param.put("orderBuyCode", orderBuyCode);
        return this;
    }

    public CloudOrderBuyDetailUtil orderId(Integer orderId) {
        this.param.put("orderId", orderId);
        return this;
    }

    public CloudOrderBuyDetailUtil buyId(Integer buyId) {
        this.param.put("buyId", buyId);
        return this;
    }

    public CloudOrderBuyDetailUtil buyerName(String buyerName) {
        this.param.put("buyerName", buyerName);
        return this;
    }

    public CloudOrderBuyDetailUtil sellerId(Integer sellerId) {
        this.param.put("sellerId", sellerId);
        return this;
    }

    public CloudOrderBuyDetailUtil sellerName(String sellerName) {
        this.param.put("sellerName", sellerName);
        return this;
    }

    public CloudOrderBuyDetailUtil goodsId(Integer goodsId) {
        this.param.put("goodsId", goodsId);
        return this;
    }

    public CloudOrderBuyDetailUtil specId(Integer specId) {
        this.param.put("specId", specId);
        return this;
    }

    public CloudOrderBuyDetailUtil cateId1(Integer cateId1) {
        this.param.put("cateId1", cateId1);
        return this;
    }

    public CloudOrderBuyDetailUtil cateId2(Integer cateId2) {
        this.param.put("cateId2", cateId2);
        return this;
    }

    public CloudOrderBuyDetailUtil cateId3(Integer cateId3) {
        this.param.put("cateId3", cateId3);
        return this;
    }

    public CloudOrderBuyDetailUtil cateId4(Integer cateId4) {
        this.param.put("cateId4", cateId4);
        return this;
    }

    public CloudOrderBuyDetailUtil sku(String sku) {
        this.param.put("sku", sku);
        return this;
    }

    public CloudOrderBuyDetailUtil goodsName(String goodsName) {
        this.param.put("goodsName", goodsName);
        return this;
    }

    public CloudOrderBuyDetailUtil price(BigDecimal price) {
        this.param.put("price", price);
        return this;
    }

    public CloudOrderBuyDetailUtil totalPrice(BigDecimal totalPrice) {
        this.param.put("totalPrice", totalPrice);
        return this;
    }

    public CloudOrderBuyDetailUtil goodsSpec(String goodsSpec) {
        this.param.put("goodsSpec", goodsSpec);
        return this;
    }

    public CloudOrderBuyDetailUtil goodsSellUnit(String goodsSellUnit) {
        this.param.put("goodsSellUnit", goodsSellUnit);
        return this;
    }

    public CloudOrderBuyDetailUtil boxNum(Integer boxNum) {
        this.param.put("boxNum", boxNum);
        return this;
    }

    public CloudOrderBuyDetailUtil goodPara(Integer goodPara) {
        this.param.put("goodPara", goodPara);
        return this;
    }

    public CloudOrderBuyDetailUtil goodsNum(Integer goodsNum) {
        this.param.put("goodsNum", goodsNum);
        return this;
    }

    public CloudOrderBuyDetailUtil sendNum(Integer sendNum) {
        this.param.put("sendNum", sendNum);
        return this;
    }

    public CloudOrderBuyDetailUtil checkNum(Integer checkNum) {
        this.param.put("checkNum", checkNum);
        return this;
    }

    public CloudOrderBuyDetailUtil diffNum(Integer diffNum) {
        this.param.put("diffNum", diffNum);
        return this;
    }

    public CloudOrderBuyDetailUtil diffRemark(String diffRemark) {
        this.param.put("diffRemark", diffRemark);
        return this;
    }

    public CloudOrderBuyDetailUtil diffType(Integer diffType) {
        this.param.put("diffType", diffType);
        return this;
    }

    public CloudOrderBuyDetailUtil goodsImage(String goodsImage) {
        this.param.put("goodsImage", goodsImage);
        return this;
    }

    public CloudOrderBuyDetailUtil type(Integer type) {
        this.param.put("type", type);
        return this;
    }

    public CloudOrderBuyDetailUtil status(Integer status) {
        this.param.put("status", status);
        return this;
    }

    public CloudOrderBuyDetailUtil payStatus(Integer payStatus) {
        this.param.put("payStatus", payStatus);
        return this;
    }

    public CloudOrderBuyDetailUtil stockStatus(Integer stockStatus) {
        this.param.put("stockStatus", stockStatus);
        return this;
    }

    public CloudOrderBuyDetailUtil channel(Integer channel) {
        this.param.put("channel", channel);
        return this;
    }

    public CloudOrderBuyDetailUtil creater(String creater) {
        this.param.put("creater", creater);
        return this;
    }

    public CloudOrderBuyDetailUtil updater(String updater) {
        this.param.put("updater", updater);
        return this;
    }

    public CloudOrderBuyDetailUtil createTime(Integer createTime) {
        this.param.put("createTime", createTime);
        return this;
    }

    public CloudOrderBuyDetailUtil updateTime(Integer updateTime) {
        this.param.put("updateTime", updateTime);
        return this;
    }

    public CloudOrderBuyDetailUtil isDelete(Long isDelete) {
        this.param.put("isDelete", isDelete);
        return this;
    }

    public CloudOrderBuyDetailUtil productTime(Integer productTime) {
        this.param.put("productTime", productTime);
        return this;
    }

    public CloudOrderBuyDetailUtil keepTime(Integer keepTime) {
        this.param.put("keepTime", keepTime);
        return this;
    }

    public CloudOrderBuyDetailUtil keepType(Byte keepType) {
        this.param.put("keepType", keepType);
        return this;
    }

    public CloudOrderBuyDetailUtil pickNum(Integer pickNum) {
        this.param.put("pickNum", pickNum);
        return this;
    }

    public CloudOrderBuyDetailUtil actualNum(Integer actualNum) {
        this.param.put("actualNum", actualNum);
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

    public <O> CloudOrderBuyDetailUtil putParam(O obj) {
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