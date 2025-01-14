package de.telran.onlineshop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;


// 140124 hm
@Aspect
@Component
@Slf4j
public class BeforeAfterAspectUsers {

    private Timestamp startTime;

    @Pointcut("execution(public * de.telran.onlineshop.controller.UsersController.*(..))")
    public void callAtUsersControllerPublic() {
    }

    @Before("callAtUsersControllerPublic()")
    public void beforeCallAtMethod(JoinPoint jp) {

        Date currentDate = new Date();
        startTime = new Timestamp(currentDate.getTime());

        String args = Arrays.stream(jp.getArgs())
                .map(a -> a.toString())
                .collect(Collectors.joining(","));

        log.info("-- Before start UsersController " + jp.toString() + " time_start-> " + startTime + ", args=[" + args + "]");

    }

    @AfterReturning(pointcut = "execution(public * de.telran.onlineshop.controller.UsersController.*(..))", returning = "result")
    public void afterCallAtMethod(Object result) {
        Date currentDate = new Date();
        Timestamp endTime = new Timestamp(currentDate.getTime());
        long deltaTime = endTime.getTime() - startTime.getTime();

        log.info("--After finish UsersController " + " time_end -> " + endTime + " result -> " + result + " time_delta -> " + deltaTime);

    }

}