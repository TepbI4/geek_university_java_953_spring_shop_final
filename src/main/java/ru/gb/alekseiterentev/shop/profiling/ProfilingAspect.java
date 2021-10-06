package ru.gb.alekseiterentev.shop.profiling;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class ProfilingAspect {

    private Map<String, Long> profilingData;

    @PostConstruct
    private void init() {
        profilingData = new HashMap<>();
    }

    @Around("execution(public * ru.gb.alekseiterentev.shop.beans.services.*.*(..))")
    public Object methodProfiling(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        long duration = end - begin;
        String key = proceedingJoinPoint.getSignature().toShortString();
        if (profilingData.containsKey(key)) {
            Long newValue = profilingData.get(key) + duration;
            profilingData.put(key, newValue);
        } else {
            profilingData.put(key, duration);
        }
        return out;
    }

    public Map<String, Long> getProfilingData() {
        return profilingData;
    }
}
