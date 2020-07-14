package com.hyssop.framework.ioc;

/**
 * @Author jie.zhang
 * @create_time 2020/5/4 18:39
 * @updater
 * @update_time
 **/
public class PropertyValue {

    private String propertyName;

    private Object propertyValue;

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Object getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(Object propertyValue) {
        this.propertyValue = propertyValue;
    }
}