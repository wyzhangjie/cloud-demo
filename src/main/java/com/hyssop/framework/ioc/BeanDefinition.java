package com.hyssop.framework.ioc;

/**
 * @Author jie.zhang
 * @create_time 2020/5/4 18:35
 * @updater
 * @update_time
 **/
public class BeanDefinition {

    private Object bean;

    private Class beanClass;

    private String beanClassName;

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public String getBeanClassName() {
        return beanClassName;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
    }
}