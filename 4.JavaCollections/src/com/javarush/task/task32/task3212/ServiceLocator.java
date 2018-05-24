package com.javarush.task.task32.task3212;

import com.javarush.task.task32.task3212.contex.InitialContext;
import com.javarush.task.task32.task3212.service.Service;


public class ServiceLocator {
    private static Cache cache = new Cache();

    /**
     * First check the service object available in cache
     * If service object not available in cache do the lookup using
     * JNDI initial context and get the service object. Add it to
     * the cache for future use.
     *
     * @param jndiName The name of service object in context
     * @return Object mapped to name in context
     */
    public static Service getService(String jndiName) {
        Service s, s1;
        if ((s = cache.getService(jndiName))!= null) {
            return s;
        }

        InitialContext ic = new InitialContext();
        s1 = (Service) ic.lookup(jndiName);
        cache.addService(s1);
        return s1;
    }
}
