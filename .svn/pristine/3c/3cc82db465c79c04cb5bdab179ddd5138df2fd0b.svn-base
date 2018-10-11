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
import com.woodare.template.helper.cache.DownMerchants;
import com.woodare.template.jpa.model.DownMerchant;
import com.woodare.template.jpa.persistence.data.downmerchant.DownMerchantData;
import com.woodare.template.jpa.persistence.data.downmerchant.SearchDownMerchantData;
import com.woodare.template.jpa.persistence.persistence.IDownMerchantDAO;

/**
 * @author lu_feng
 */
public class DownMerchantListener extends AbstractCacheLoadListener implements CacheChangedListener, CacheLoadListener {

	@Override
	public Class<?> getCacheType() {
		return DownMerchantData.class;
	}

	@Override
	@SuppressWarnings({ "unchecked" })
	public void execute(Map<String, List<? extends AbstractData>> cacheMaps) {
		List<DownMerchantData> items = (List<DownMerchantData>) cacheMaps.get(DownMerchantData.class.getName());
		if (items != null) {
			DownMerchants.resetAll(items);
		}
	}

	@Override
	public List<? extends AbstractData> doExecute() {
		ApplicationContext application = ApplicationContextHolder.getApplicationContext();
		IDownMerchantDAO dao = application.getBean(IDownMerchantDAO.class);
		SearchDownMerchantData search = new SearchDownMerchantData();
		search.setPageIndex(0);
		search.setPageSize(Integer.MAX_VALUE);
		List<DownMerchant> models = dao.searchDownMerchants(search);
		return SaftyBeanUtils.cloneToList(models, DownMerchantData.class, new String[] { "createDate" });

	}
}
