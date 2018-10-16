package com.woodare.template.jpa.persistence.persistence;

import com.woodare.framework.persistence.service.ISimpleDAO;
import com.woodare.template.jpa.model.DownDsapInoviceHis;
import com.woodare.template.jpa.persistence.data.downdspinvoice.SearchDownDspInvoiceData;

import java.util.List;


public interface ISummaryDAO extends ISimpleDAO<DownDsapInoviceHis> {

    /**
     * 定时备份
     * @param searchData
     * @return
     */
    int sumSummary(SearchDownDspInvoiceData searchData);

    /**
     * 页面查询
     * @param searchData
     * @return
     */
    List<DownDsapInoviceHis> summary(SearchDownDspInvoiceData searchData);


}
