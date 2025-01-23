package com.nhnacademy.stopwatch;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// StopWatch 어노테이션이 메서드에만 적용될 수 있다는 의미.
@Target(value = {ElementType.METHOD})
// Runtime 까지 유지되어 리플렉션을 통해 접근할 수 있음을 의미.
@Retention(RetentionPolicy.RUNTIME)

// StopWatch 는 실제로 어노테이션 이고, 내부적으로는 인터페이스 처럼 동작하게 됨.
// 어노테이션은 단지 메타데이터를 담고 있는 특수한 형태임
// 메타데이터는 데이터에 대한 데이터. ex) @Override 로 부모 클래스의 메서드를 오버라이드 / JavaDoc 주석 / 리플렉션 API
public @interface StopWatch {
}
