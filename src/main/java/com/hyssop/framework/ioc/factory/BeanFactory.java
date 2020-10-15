package com.hyssop.framework.ioc.factory;

import com.hyssop.framework.ioc.BeanDefinition;

/**
 * @Author jie.zhang
 * @create_time 2020/5/4 18:34
 * @updater
 * @update_time
 **/
public interface  BeanFactory {
  Object getObject(String name);
  void registerBeanDefinition(String name, BeanDefinition b);
}