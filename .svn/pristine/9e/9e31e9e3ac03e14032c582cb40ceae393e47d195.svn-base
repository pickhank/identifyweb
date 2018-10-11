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
import com.woodare.template.helper.cache.Kbins;
import com.woodare.template.jpa.model.Kbin;
import com.woodare.template.jpa.persistence.data.kbin.KbinData;
import com.woodare.template.jpa.persistence.data.kbin.SearchKbinData;
import com.woodare.template.jpa.persistence.persistence.IKbinDAO;

/**
 * @author lu_feng
 */
public class KbinListener extends AbstractCacheLoadListener implements CacheChangedListener, CacheLoadListener {

	@Override
	public Class<?> getCacheType() {
		return KbinData.class;
	}

	@Override
	@SuppressWarnings({ "unchecked" })
	public void execute(Map<String, List<? extends AbstractData>> cacheMaps) {
		Kbins.reset();
		List<KbinData> items = (List<KbinData>) cacheMaps.get(KbinData.class.getName());
		if (items != null) {
			for (KbinData item : items) {
				Kbins.setValues(item);
			}
		}
	}

	@Override
	public List<? extends AbstractData> doExecute() {
		ApplicationContext application = ApplicationContextHolder.getApplicationContext();
		IKbinDAO dao = application.getBean(IKbinDAO.class);
		SearchKbinData search = new SearchKbinData();
		search.setPageIndex(0);
		search.setPageSize(Integer.MAX_VALUE);
		List<Kbin> models = dao.searchKbins(search);
		return SaftyBeanUtils.cloneToList(models, KbinData.class, new String[] { "id", "createDate" });

	}
}
