package com.woodare.core.component.cache;

import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;

import com.woodare.core.jpa.data.systemroleright.SystemRoleRightData;
import com.woodare.core.jpa.model.SystemRoleRight;
import com.woodare.core.jpa.service.ISystemRoleRightDAO;
import com.woodare.framework.component.AbstractCacheLoadListener;
import com.woodare.framework.component.CacheChangedListener;
import com.woodare.framework.component.CacheLoadListener;
import com.woodare.framework.data.impl.AbstractData;
import com.woodare.framework.spring.ApplicationContextHolder;
import com.woodare.framework.utils.SaftyBeanUtils;


/**
 * 
 * @author lu_feng
 *
 */
public class SystemRoleRightListener extends AbstractCacheLoadListener implements CacheChangedListener, CacheLoadListener {

    @Override
    public List<? extends AbstractData> doExecute() {
        ApplicationContext application = ApplicationContextHolder.getApplicationContext();
        ISystemRoleRightDAO dao = application.getBean(ISystemRoleRightDAO.class);

        List<SystemRoleRight> models = dao.findAll();
        
        return SaftyBeanUtils.cloneToList(models, SystemRoleRightData.class);
    }

    @Override
    public void execute(Map<String, List<? extends AbstractData>> newCachedMap) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Class<?> getCacheType() {
        return SystemRoleRight.class;
    }
}
