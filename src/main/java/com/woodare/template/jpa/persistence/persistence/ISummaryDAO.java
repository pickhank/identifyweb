package com.woodare.template.jpa.persistence.persistence;

import com.woodare.framework.persistence.service.ISimpleDAO;
import com.woodare.template.jpa.persistence.data.downdspinvoice.DownDspInvoiceSumData;

import java.util.List;

public interface ISummaryDAO extends ISimpleDAO<DownDspInvoiceSumData> {

    int sumSummary(String transDate);


}
