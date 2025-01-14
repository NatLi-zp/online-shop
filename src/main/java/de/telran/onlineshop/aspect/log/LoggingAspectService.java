package de.telran.onlineshop.aspect.log;

import org.antlr.v4.runtime.misc.NotNull;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspectService {
    private static final Logger log = LoggerFactory.getLogger(LoggingAspectService.class);

    @Around("execution(* de.telran.onlineshop.service..*(..)))")
    public Object mdcServiceC(@NotNull final ProceedingJoinPoint joinPoint) throws Throwable {
        String queryMethod = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        logBeforeServiceQuery(queryMethod, args);
        long startTime = System.currentTimeMillis();

        try {
            Object result = joinPoint.proceed();
            logAfterServiceQuery(queryMethod, args, result, startTime);
            return result;
        } catch (Exception ex) {
            logAndGetErrorMessage(queryMethod, args, ex, startTime);
            throw ex;
        }
    }

    private void logBeforeServiceQuery(final String queryMethod, final Object[] args) {
        MDCFields.SERVICE_STEP.putMdcField("SERVICE_IN");
        MDCFields.SERVICE_METHOD.putMdcFieldWithFieldName(queryMethod);
        String argsAsString = Arrays.toString(args);
        log.info("args={};", argsAsString);
        MDCFields.SERVICE_METHOD.removeMdcField();
        MDCFields.SERVICE_STEP.removeMdcField();
    }

    private void logAfterServiceQuery(final String queryMethod, final Object[] args, final Object result, final long startTime) {
        long callTime = System.currentTimeMillis() - startTime;
        String resultInfo = LogUtils.getDaoResultLogInfo(log, result);
        MDCFields.SERVICE_STEP.putMdcField("SERVICE_OUT");
        MDCFields.SERVICE_METHOD.putMdcFieldWithFieldName(queryMethod);
        MDCFields.SERVICE_TIME.putMdcFieldWithFieldName(callTime);
        String argsAsString = Arrays.toString(args);
        log.info(
                "args={}; RESULT: [{}]",
                argsAsString,
                resultInfo
        );
        MDCFields.SERVICE_TIME.removeMdcField();
        MDCFields.SERVICE_METHOD.removeMdcField();
        MDCFields.SERVICE_STEP.removeMdcField();
    }

    private void logAndGetErrorMessage(final String queryMethod, final Object[] args, final Exception ex, final long startTime) {
        long callTime = System.currentTimeMillis() - startTime;
        String errorMsg = String.format(
                "args=%s;",
                Arrays.toString(args)
        );

        MDCFields.SERVICE_STEP.putMdcField("SERVICE_ERROR");
        MDCFields.SERVICE_METHOD.putMdcFieldWithFieldName(queryMethod);
        MDCFields.SERVICE_TIME.putMdcFieldWithFieldName(callTime);
        log.error(errorMsg, ex);
        MDCFields.SERVICE_TIME.removeMdcField();
        MDCFields.SERVICE_METHOD.removeMdcField();
        MDCFields.SERVICE_STEP.removeMdcField();
        throw new LogException(ex.getMessage(), ex);
    }

}
