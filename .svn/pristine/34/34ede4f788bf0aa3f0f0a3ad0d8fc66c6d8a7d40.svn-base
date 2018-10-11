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
import com.woodare.template.helper.cache.DownDspProducts;
import com.woodare.template.jpa.model.DownDspProduct;
import com.woodare.template.jpa.persistence.data.downdspproduct.DownDspProductData;
import com.woodare.template.jpa.persistence.data.downdspproduct.SearchDownDspProductData;
import com.woodare.template.jpa.persistence.persistence.IDownDspProductDAO;

/**
 * @author lu_feng
 */
public class DownDspProductListener extends AbstractCacheLoadListener implements CacheChangedListener, CacheLoadListener {

	@Override
	public Class<?> getCacheType() {
		return DownDspProductData.class;
	}

	@Override
	@SuppressWarnings({ "unchecked" })
	public void execute(Map<String, List<? extends AbstractData>> cacheMaps) {
		List<DownDspProductData> items = (List<DownDspProductData>) cacheMaps.get(DownDspProductData.class.getName());
		if (items != null) {
			DownDspProducts.resetAll(items);
		}
	}

	@Override
	public List<? extends AbstractData> doExecute() {
		ApplicationContext application = ApplicationContextHolder.getApplicationContext();
		IDownDspProductDAO dao = application.getBean(IDownDspProductDAO.class);
		SearchDownDspProductData search = new SearchDownDspProductData();
		search.setPageIndex(0);
		search.setPageSize(Integer.MAX_VALUE);
		List<DownDspProduct> models = dao.searchItems(search);
		return SaftyBeanUtils.cloneToList(models, DownDspProductData.class, new String[] { "createDate" });

	}
}
