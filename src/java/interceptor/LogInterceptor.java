/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interceptor;

import java.util.logging.Logger;
import javax.interceptor.AroundConstruct;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 *
 * @author acer
 */
public class LogInterceptor {

    private Logger logger = Logger.getLogger("");

    public LogInterceptor() {

    }
    @AroundConstruct
    private Object logConstruction(InvocationContext ic) throws Exception {
       System.out.println("LogInterceptor - Logging BEFORE Costructor ");
        logger.fine("Entering constructor");
        try {
            return ic.proceed();
        } finally {
            logger.fine("Existing constructor");
            System.out.println("LogInterceptor - Logging AFTER Costructor ");
        }
        
    }

    @AroundInvoke
    public Object logInvocation(InvocationContext ic) throws Exception {
        System.out.println("LogInterceptor - Logging BEFORE calling method :"+ic.getMethod().getName() );
        logger.entering(ic.getTarget().toString(), ic.getMethod().getName());
        try {
            return ic.proceed();
        } finally {
            System.out.println("LogInterceptor - Logging AFTER calling method :"+ic.getMethod().getName() );
            logger.exiting(ic.getTarget().toString(), ic.getMethod().getName());
        }
    }
}
