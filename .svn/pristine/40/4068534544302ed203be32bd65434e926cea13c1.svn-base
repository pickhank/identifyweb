package com.woodare.core.web.startup;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;

import com.woodare.framework.spring.ApplicationContextHolder;

/**
 * 
 * @author lu_feng
 *
 */
public class InitializeDatabase {

    public static interface IInitializer {
        void execute();
    }
    
    private static List<IInitializer> _initializes = new ArrayList<IInitializer>();

    /**
     * 
     * @param initializer
     */
    public static void register(IInitializer initializer) {
        _initializes.add(initializer);
    }
    
    /**
     * 
     * @param initializer
     */
    public static void execute() {
        ApplicationContext application = ApplicationContextHolder.getApplicationContext();
        IInitializer initializer = application.getBean(IInitializer.class);
        initializer.execute();
        
        for(IInitializer initialize : _initializes) {
            initialize.execute();
        }
    }
}
