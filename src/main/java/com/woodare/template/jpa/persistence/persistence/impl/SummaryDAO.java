package com.woodare.template.jpa.persistence.persistence.impl;

import com.woodare.framework.persistence.service.impl.AbstractPagedDAO;
import com.woodare.template.jpa.persistence.data.downdspinvoice.DownDspInvoiceSumData;
import com.woodare.template.jpa.persistence.persistence.ISummaryDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.Query;

@Service
public class SummaryDAO extends AbstractPagedDAO<DownDspInvoiceSumData> implements ISummaryDAO {

    @Override
    protected Class<DownDspInvoiceSumData> getDomainClass() {
        return DownDspInvoiceSumData.class;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int sumSummary(String transDate) {
        StringBuffer sb = new StringBuffer();
        sb.append(" INSERT INTO down_dsp_invoice_sum_data (id,create_date,update_date,agent_profit_amt,channel_fee_amt,count,dsp_mode,mch_fee_amt,mch_name,mch_no,");
        sb.append("profit_amt,tans_date,total_count,xtra_profit_amt)");
        sb.append(" SELECT m.* FROM(select a.id,a.create_date,a.update_date,");
        sb.append("sum(case when a.status = '00' then a.agent_profit else 0 end) agent_profit_amt,");
        sb.append("sum(case when a.status = '00' then a.channel_pay_fee else 0 end) channel_fee_amt,");
        sb.append("sum(case when a.status = '00' then 1 else 0 end) `count`,");
        sb.append("a.dsp_mode,");
        sb.append("sum(case when a.status = '00' then a.mch_pay_fee else 0 end) mch_fee_amt,");
        sb.append("a.mch_name,a.mch_no,");
        sb.append("sum(case when a.status = '00' then a.profit else 0 end) profit_amt,");
        sb.append("a.trans_date,");
        sb.append("count(a.id) total_count,");
        sb.append("sum(case when a.status = '00' then a.xtra_profit else 0 end) xtra_profit_amt");
        sb.append(" from down_dsp_invoice a WHERE trans_date = '"+transDate+"' GROUP BY mch_no ORDER BY create_date) m");

        System.out.println(sb.toString());
        Query executeQuery = this.getEntityManager().createNativeQuery(sb.toString());

        return executeQuery.executeUpdate();
    }
}
