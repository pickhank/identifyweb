package com.woodare.template.component.cache;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.alibaba.fastjson.JSONObject;
import com.woodare.core.util.SDFFactory;
import com.woodare.framework.component.AbstractCacheLoadListener;
import com.woodare.framework.component.CacheChangedListener;
import com.woodare.framework.component.CacheLoadListener;
import com.woodare.framework.data.impl.AbstractData;
import com.woodare.framework.spring.ApplicationContextHolder;
import com.woodare.framework.utils.SaftyBeanUtils;
import com.woodare.template.helper.cache.DownDspInvoiceRoutes;
import com.woodare.template.jpa.model.DownDspInvoiceRoute;
import com.woodare.template.jpa.persistence.data.downdspinvoiceroute.DownDspInvoiceRouteData;
import com.woodare.template.jpa.persistence.data.downdspinvoiceroute.SearchDownDspInvoiceRouteData;
import com.woodare.template.jpa.persistence.persistence.IDownDspInvoiceRouteDAO;

/**
 * @author lu_feng
 */
public class DownDspInvoiceRouteListener extends AbstractCacheLoadListener implements CacheChangedListener, CacheLoadListener {

	private static Logger log = Logger.getLogger(DownDspInvoiceRouteListener.class);

	@Override
	public Class<?> getCacheType() {
		return DownDspInvoiceRouteData.class;
	}

	@Override
	@SuppressWarnings({ "unchecked" })
	public void execute(Map<String, List<? extends AbstractData>> cacheMaps) {
		String logId = SDFFactory.getLogId();
		log.info("ResetDownDspInvoiceRoutes[]" + logId + "[Before]" + JSONObject.toJSONString(DownDspInvoiceRoutes.getAll()));

		DownDspInvoiceRoutes.reset();
		List<DownDspInvoiceRouteData> items = (List<DownDspInvoiceRouteData>) cacheMaps.get(DownDspInvoiceRouteData.class.getName());
		if (items != null) {
			DownDspInvoiceRoutes.setValues(items);
		}
		log.info("ResetDownDspInvoiceRoutes[]" + logId + "[After]" + JSONObject.toJSONString(DownDspInvoiceRoutes.getAll()));
	}

	@Override
	public List<? extends AbstractData> doExecute() {
		ApplicationContext application = ApplicationContextHolder.getApplicationContext();
		IDownDspInvoiceRouteDAO dao = application.getBean(IDownDspInvoiceRouteDAO.class);
		SearchDownDspInvoiceRouteData search = new SearchDownDspInvoiceRouteData();
		search.setPageIndex(0);
		search.setPageSize(Integer.MAX_VALUE);
		search.setOrderString("priority asc");
		List<DownDspInvoiceRoute> models = dao.searchItems(search);
		return SaftyBeanUtils.cloneToList(models, DownDspInvoiceRouteData.class, new String[] { "createDate" });

	}
}
