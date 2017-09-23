package com.flash.cn.core;

import com.flash.cn.annotation.Repository;
import com.flash.cn.util.PropertiesUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Bean 核心容器。
 *
 * @author kay
 * @version v1.0
 */
public final class BeanContainer {

    /**
     * 根据配置获取包名
     */
    private static final String FLASH_PACKAGE_NAME = PropertiesUtils.load("/config/flash.properties", "packageName");

    /**
     * 根据配置获取容器模式
     */
    private static final String FLASH_CONTAINER = PropertiesUtils.load("/config/flash.properties", "containerModes");

    private static BeanContainer instance = new BeanContainer();

    public static synchronized BeanContainer getInstance() {
        if ("multiple".equals(FLASH_CONTAINER)) {
            return new BeanContainer();
        }
        else {
            return instance;
        }
    }

    private static Map<String, Object> container = new ConcurrentHashMap<String, Object>();

    /**
     * 默认构造器
     */
    private BeanContainer() {
        //
    }

    /**
     * 初始化容器
     */
    public void init() {
        ClassPathResource resource = new ClassPathResource();
        List<Class<?>> list = resource.getClasses(FLASH_PACKAGE_NAME);
        loadRepository(list);
    }

    /**
     * 装载进入 Bean 容器
     *
     * @param list Class 清单
     */
    private void loadRepository(List<Class<?>> list) {
        for (Class clazz : list) {
            Repository annotation = (Repository) clazz.getAnnotation(Repository.class);
            if (annotation != null) {
                Object object = ClassUtils.newInstance(clazz.getName());
                container.put(annotation.value(), object);
            }
        }
    }

    /**
     * 根据 key 获取容器中的对象
     *
     * @param key 容器关键字
     * @param <T> 弱类型转换成强类型
     * @return 返回容器中的对象
     */
    @SuppressWarnings("unchecked")
    public <T> T getValue(String key) {
        return (T) container.get(key);
    }

    public void println() {
        for (Map.Entry<String, Object> entry : container.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println("Size=" + container.size());
        }
    }
}
