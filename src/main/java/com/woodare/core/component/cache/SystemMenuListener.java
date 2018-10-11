package com.woodare.core.component.cache;

import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;

import com.woodare.core.jpa.data.systemmenu.SystemMenuData;
import com.woodare.core.jpa.model.SystemMenu;
import com.woodare.core.jpa.service.ISystemMenuDAO;
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
public class SystemMenuListener extends AbstractCacheLoadListener implements CacheChangedListener, CacheLoadListener {

    @Override
    public Class<?> getCacheType() {
        return SystemMenuData.class;
    }

    @Override
    public List<? extends AbstractData> doExecute() {
        ApplicationContext application = ApplicationContextHolder.getApplicationContext();
        ISystemMenuDAO dao = application.getBean(ISystemMenuDAO.class);

        List<SystemMenu> models = dao.findAll();

        return SaftyBeanUtils.cloneToList(models, SystemMenuData.class);
    }

    @Override
    public void execute(Map<String, List<? extends AbstractData>> newCachedMap) {
        // TODO Auto-generated method stub

    }
}