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
import com.woodare.template.helper.cache.DownAgents;
import com.woodare.template.jpa.model.DownAgent;
import com.woodare.template.jpa.persistence.data.downagent.DownAgentData;
import com.woodare.template.jpa.persistence.data.downagent.SearchDownAgentData;
import com.woodare.template.jpa.persistence.persistence.IDownAgentDAO;

/**
 * @author lu_feng
 */
public class DownAgentListener extends AbstractCacheLoadListener implements CacheChangedListener, CacheLoadListener {

	@Override
	public Class<?> getCacheType() {
		return DownAgentData.class;
	}

	@Override
	@SuppressWarnings({ "unchecked" })
	public void execute(Map<String, List<? extends AbstractData>> cacheMaps) {
		DownAgents.reset();
		List<DownAgentData> items = (List<DownAgentData>) cacheMaps.get(DownAgentData.class.getName());
		if (items != null) {
			for (DownAgentData item : items) {
				DownAgents.setByCode(item.getAgentNo(), item);
			}
		}
	}

	@Override
	public List<? extends AbstractData> doExecute() {
		ApplicationContext application = ApplicationContextHolder.getApplicationContext();
		IDownAgentDAO dao = application.getBean(IDownAgentDAO.class);
		SearchDownAgentData search = new SearchDownAgentData();
		search.setPageIndex(0);
		search.setPageSize(Integer.MAX_VALUE);
		List<DownAgent> models = dao.searchDownAgents(search);
		return SaftyBeanUtils.cloneToList(models, DownAgentData.class, new String[] { "createDate", "phone", "bankName", "cardNo" });
	}
}
