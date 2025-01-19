package code.shubham.commons.configurations;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceAspect {

    @Pointcut("execution(* org.springframework.data.repository.Repository+.*(..))")
    public void repositoryMethods() {
    }

    @Around("repositoryMethods() && @annotation(ReadOnly)")
    public Object setReplicaDataSource(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            DataSourceContextHolder.setCurrentDataSource("REPLICA");
            return joinPoint.proceed();
        } finally {
            DataSourceContextHolder.clear();
        }
    }

    @Around("repositoryMethods() && !@annotation(ReadOnly)")
    public Object setPrimaryDataSource(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            DataSourceContextHolder.setCurrentDataSource("PRIMARY");
            return joinPoint.proceed();
        } finally {
            DataSourceContextHolder.clear();
        }
    }
}
