package com.nhnacademy.student;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.lang.reflect.Method;

public class ControllerFactory {
    private final ConcurrentMap<String, Object> beanMap = new ConcurrentHashMap<>();

    public void init(Set<Class<?>> c){
        //todo beanMap 에 key = method + servletPath, value = Controller instance
        for (Class<?> clazz : c) {
            if(clazz.isAnnotationPresent(RequestMapping.class)) {
                RequestMapping requestMapping = clazz.getAnnotation(RequestMapping.class);
                String path = requestMapping.value();
                String method = requestMapping.method().name();

                Object controller = null;
                try {
                    controller = clazz.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                beanMap.put(method + path, controller);
            }
        }
    }

    public Object getBean(String method, String servletPath){
        //todo beanMap 에서 method+servletPath을 key로 이용하여 Controller instance를 반환합니다.
        return beanMap.get(method + servletPath);
    }
}