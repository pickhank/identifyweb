/******************************************************************************
 *                                                                             
 *                      Woodare PROPRIETARY INFORMATION                        
 *                                                                             
 *          The information contained herein is proprietary to Woodare         
 *           and shall not be reproduced or disclosed in whole or in part      
 *                    or used for any design or manufacture                    
 *              without direct written authorization from FengDa.              
 *                                                                             
 *            Copyright (c) 2013 by Woodare.  All rights reserved.             
 *                                                                             
 *****************************************************************************/
package com.woodare.framework.component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.woodare.framework.data.impl.AbstractData;

/**
 * 
 * @author lu_feng
 * 
 */
public class SystemCache {
    private static Logger log = Logger.getLogger(SystemCache.class);
    
    private Map<String, List<? extends AbstractData>> cacheMaps = new HashMap<String, List<? extends AbstractData>>();

    private List<ICache> _listeners = new ArrayList<ICache>();

    private static SystemCache _CACHE = new SystemCache();

    /**
     * 
     * @return
     */
    private static SystemCache getInstance() {
        return _CACHE;
    }
    
    /**
     * 
     */
    private void fireAllCacheLoadEvent() {
        for (ICache listener : _listeners) {
            if (listener instanceof CacheLoadListener) {
                cacheMaps.put(listener.getCacheType().getName(), ((CacheLoadListener) listener).execute());
            }
        }
    }

    /**
     * 
     */
    private void fireAllCacheChangedEvent() {
        for (ICache listener : _listeners) {
            if (listener instanceof CacheChangedListener) {
                ((CacheChangedListener) listener).execute(cacheMaps);
            }
        }
    }
    
    /**
     * 
     */
    private void fireCacheLoadEvent(Class<?> objClass) {
        for (ICache listener : _listeners) {
            if (listener instanceof CacheLoadListener) {
                CacheLoadListener loadListener = (CacheLoadListener) listener;
                if(loadListener.getCacheType().equals(objClass)) {
                    cacheMaps.put(listener.getCacheType().getName(), loadListener.execute());
                }
            }
        }
    }
    
    /**
     * 
     */
    private void fireCacheLoadEventByPk(Class<?> objClass, Object pk) {
    	List<CacheChangedListener> chgListeners = new ArrayList<CacheChangedListener>();
    	
    	for (ICache listener : _listeners) {
            if (listener instanceof CacheChangedListener) {
                CacheChangedListener changedListener = (CacheChangedListener) listener;
                if(changedListener.getCacheType().equals(objClass)) {
                    // changedListener.execute(cacheMaps);
                    chgListeners.add(changedListener);
                }
            }
        }
    	
    	if(chgListeners.size() > 0) {
    		for (ICache listener : _listeners) {
                if (listener instanceof CacheLoadListener) {
                    CacheLoadListener loadListener = (CacheLoadListener) listener;
                    if(loadListener.getCacheType().equals(objClass)) {
                    	AbstractData cacheItem = loadListener.executeItem(pk);
                		for (CacheChangedListener tmp : chgListeners) {
                			tmp.executeItem(cacheItem);
                		}
                    }
                }
            }
    	}
    }

    /**
     * 
     */
    private void fireCacheChangedEvent(Class<?> objClass) {
        for (ICache listener : _listeners) {
            if (listener instanceof CacheChangedListener) {
                CacheChangedListener changedListener = (CacheChangedListener) listener;
                if(changedListener.getCacheType().equals(objClass)) {
                    changedListener.execute(cacheMaps);
                }
            }
        }
    }

    /**
     * 
     * @param key
     * @param dataLst
     */
    private void cleanCache() {
        synchronized (cacheMaps) {
            for(String objClassName : cacheMaps.keySet()) {
                cacheMaps.get(objClassName).clear();
            }
        }
    }

    /**
     * 
     * @param listener
     */
    public static void registListener(Class<?> objClass, ICache listener) {
        if (!getInstance()._listeners.contains(listener)) {
            getInstance()._listeners.add(listener);
        }
        
        if(listener instanceof CacheLoadListener) {
            if(!getInstance().cacheMaps.containsKey(objClass.getName())) {
                getInstance().cacheMaps.put(objClass.getName(), new ArrayList<AbstractData>());
            }
            else {
                log.error("The class has been duplicated registing: " + objClass);
                System.exit(-1);
            }
        }
    }

    /**
     * 
     * @param listener
     */
    public static void registListener(ICache listener) {
        if (!getInstance()._listeners.contains(listener)) {
            getInstance()._listeners.add(listener);
        }
    }

    /**
     * 
     * @param objClass
     * @return
     */
    @SuppressWarnings("unchecked")
    public synchronized static <K extends AbstractData> List<K> getItems(Class<K> objClass) {
        return (List<K>) getInstance().cacheMaps.get(objClass.getName());
    }

    /**
     * 
     * @param objClass
     * @return
     */
    @SuppressWarnings("unchecked")
    public synchronized static <K extends AbstractData> K getSingleItem(Class<?> objClass, Class<K> retClass) {
        List<K> items = (List<K>) getInstance().cacheMaps.get(objClass.getName());
        return items != null && items.size() > 0 ? items.get(0) : null;
    }

    /**
	 * 
	 */
    public static void clean() {
        getInstance().cleanCache();
    }

    /**
     * 
     */
    public static void fireLoadEvent() {
        getInstance().fireAllCacheLoadEvent();
        getInstance().fireAllCacheChangedEvent();
    }
    
    /**
     * 
     */
    public static void fireLoadEvent(Class<?> objClass) {
        getInstance().fireCacheLoadEvent(objClass);
        getInstance().fireCacheChangedEvent(objClass);
    }
    
    /**
     * 
     */
    public static void fireLoadEventByPk(Class<?> objClass, Object pk) {
        getInstance().fireCacheLoadEventByPk(objClass, pk);
    }
    
}
