package com.woodare.template.component.cache;

import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;

import com.woodare.framework.component.AbstractCacheLoadListener;
import com.woodare.framework.component.CacheChangedListener;
import com.woodare.framework.component.CacheLoadListener;
import com.woodare.framework.data.impl.AbstractData;
import com.woodare.framework.spring.ApplicationContextHolder;
import com.woodare.framework.utils.SaftyBeanUtils;
import com.woodare.template.helper.cache.PasswayDspMerchants;
import com.woodare.template.jpa.model.PasswayDspMerchant;
import com.woodare.template.jpa.persistence.data.passwaydspmerchant.PasswayDspMerchantData;
import com.woodare.template.jpa.persistence.data.passwaydspmerchant.SearchPasswayDspMerchantData;
import com.woodare.template.jpa.persistence.persistence.IPasswayDspMerchantDAO;

/**
 * @author lu_feng
 */
public class PasswayDspMerchantListener extends AbstractCacheLoadListener implements CacheChangedListener, CacheLoadListener {

	@Override
	public Class<?> getCacheType() {
		return PasswayDspMerchantData.class;
	}

	@Override
	@SuppressWarnings({ "unchecked" })
	public void execute(Map<String, List<? extends AbstractData>> cacheMaps) {
		List<PasswayDspMerchantData> items = (List<PasswayDspMerchantData>) cacheMaps.get(PasswayDspMerchantData.class.getName());
		PasswayDspMerchants.resetAll(items);
	}

	@Override
	public List<? extends AbstractData> doExecute() {
		ApplicationContext application = ApplicationContextHolder.getApplicationContext();
		IPasswayDspMerchantDAO dao = application.getBean(IPasswayDspMerchantDAO.class);
		SearchPasswayDspMerchantData search = new SearchPasswayDspMerchantData();
		search.setPageIndex(0);
		search.setPageSize(Integer.MAX_VALUE);
		List<PasswayDspMerchant> models = dao.searchItems(search);
		return SaftyBeanUtils.cloneToList(models, PasswayDspMerchantData.class, new String[] { "createDate" });

	}
}
