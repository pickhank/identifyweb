package com.woodare.core.component.cache;

import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;

import com.woodare.core.jpa.data.systemproperties.SystemPropertiesData;
import com.woodare.core.jpa.model.SystemProperties;
import com.woodare.core.jpa.service.ISystemPropertiesDAO;
import com.woodare.framework.component.AbstractCacheLoadListener;
import com.woodare.framework.component.CacheChangedListener;
import com.woodare.framework.component.CacheLoadListener;
import com.woodare.framework.data.impl.AbstractData;
import com.woodare.framework.spring.ApplicationContextHolder;
import com.woodare.framework.utils.DatabaseProperties;
import com.woodare.framework.utils.SaftyBeanUtils;

/**
 * 
 * @author lu_feng
 *
 */
public class SysPropertiesListener extends AbstractCacheLoadListener implements CacheChangedListener, CacheLoadListener {

    @Override
    public Class<?> getCacheType() {
        return SystemPropertiesData.class;
    }
    
    @Override
    @SuppressWarnings({ "unchecked" })
    public void execute(Map<String, List<? extends AbstractData>> cacheMaps) {
		DatabaseProperties.reset();
		List<SystemPropertiesData> items = (List<SystemPropertiesData>) cacheMaps.get(SystemPropertiesData.class.getName());
		
		if (items != null) {
			for (SystemPropertiesData item : items) {
				DatabaseProperties.setPropertyValue(item.getPcode(), item.getPvalue());
			}
		}
	}

    @Override
    public List<? extends AbstractData> doExecute() {
        ApplicationContext application = ApplicationContextHolder.getApplicationContext();
        ISystemPropertiesDAO dao = application.getBean(ISystemPropertiesDAO.class);

        List<SystemProperties> models = dao.findAll();
        
        return SaftyBeanUtils.cloneToList(models, SystemPropertiesData.class);
    }
}
