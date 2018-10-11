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
import com.woodare.template.helper.cache.DownMerchantProducts;
import com.woodare.template.jpa.model.DownMerchantProduct;
import com.woodare.template.jpa.persistence.data.downmerchantproduct.DownMerchantProductData;
import com.woodare.template.jpa.persistence.data.downmerchantproduct.SearchDownMerchantProductData;
import com.woodare.template.jpa.persistence.persistence.IDownMerchantProductDAO;

/**
 * @author lu_feng
 */
public class DownMerchantProductListener extends AbstractCacheLoadListener implements CacheChangedListener, CacheLoadListener {

	@Override
	public Class<?> getCacheType() {
		return DownMerchantProductData.class;
	}

	@Override
	@SuppressWarnings({ "unchecked" })
	public void execute(Map<String, List<? extends AbstractData>> cacheMaps) {
		List<DownMerchantProductData> items = (List<DownMerchantProductData>) cacheMaps.get(DownMerchantProductData.class.getName());
		if (items != null) {
			DownMerchantProducts.resetAll(items);
		}
	}

	@Override
	public List<? extends AbstractData> doExecute() {
		ApplicationContext application = ApplicationContextHolder.getApplicationContext();
		IDownMerchantProductDAO dao = application.getBean(IDownMerchantProductDAO.class);
		SearchDownMerchantProductData search = new SearchDownMerchantProductData();
		search.setPageIndex(0);
		search.setPageSize(Integer.MAX_VALUE);
		List<DownMerchantProduct> models = dao.searchItems(search);
		return SaftyBeanUtils.cloneToList(models, DownMerchantProductData.class, new String[] { "createDate" });

	}
}
