package com.woodare.template.jpa.persistence.persistence.impl;

import com.woodare.framework.persistence.service.impl.AbstractPagedDAO;
import com.woodare.template.jpa.persistence.data.downdspinvoice.SearchDownDspInvoiceData;
import com.woodare.template.jpa.persistence.data.sumary.DownDsapInoviceHis;
import com.woodare.template.jpa.persistence.persistence.ISummaryDAO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Service
public class SummaryDAO extends AbstractPagedDAO<DownDsapInoviceHis> implements ISummaryDAO {

    @Override
    protected Class<DownDsapInoviceHis> getDomainClass() {
        return DownDsapInoviceHis.class;
    }

    /**
     * 定时查询交易数据插入到历史表
     * @param searchData
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int sumSummary(SearchDownDspInvoiceData searchData) {
        StringBuffer sb = new StringBuffer();
        sb.append(" INSERT INTO down_dsap_inovice_his (id,create_date,update_date,agent_profit_amt,channel_fee_amt,count,dsp_mode,mch_fee_amt,mch_name,mch_no,");
        sb.append("profit_amt,trans_date,total_count,xtra_profit_amt)");
        sb.append(" SELECT m.* FROM(select a.id,a.create_date,a.update_date,");
        sb.append("sum(case when a.status = '1' then a.agent_profit else 0 end) agent_profit_amt,");
        sb.append("sum(case when a.status = '1' then a.channel_pay_fee else 0 end) channel_fee_amt,");
        sb.append("sum(case when a.status = '1' then 1 else 0 end) count,");
        sb.append("a.dsp_mode,");
        sb.append("sum(case when a.status = '1' then a.mch_pay_fee else 0 end) mch_fee_amt,");
        sb.append("a.mch_name,a.mch_no,");
        sb.append("sum(case when a.status = '1' then a.profit else 0 end) profit_amt,");
        sb.append("a.trans_date,");
        sb.append("count(a.id) total_count,");
        sb.append("sum(case when a.status = '1' then a.xtra_profit else 0 end) xtra_profit_amt");
        sb.append(" from down_dsp_invoice a WHERE trans_date = '" + searchData.getTransDate() + "' GROUP BY mch_no ORDER BY create_date) m");

        System.out.println(sb.toString());
        Query executeQuery = this.getEntityManager().createNativeQuery(sb.toString());

        return executeQuery.executeUpdate();
    }

    /**
     * 查询历史表
     * @param searchData
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<DownDsapInoviceHis> summary(SearchDownDspInvoiceData searchData) {
        StringBuffer sql = new StringBuffer("from DownDsapInoviceHis a");

        List<TypeCondition> conditions = new ArrayList<TypeCondition>();

        // Add filter conditions
        if (searchData.getIds() != null && searchData.getIds().size() > 0) {
            conditions.add(new TypeCondition("ids", "a.id in (:ids)", searchData.getIds()));
        }
        // change the key
        if (StringUtils.isNotEmpty(searchData.getKeywords())) {
            conditions.add(new TypeCondition("keywords", "(a.mchName like :keywords)", "%" + searchData.getKeywords() + "%"));
        }
        // TODO: add more conditionsd

        // Append conditions
        if (conditions != null && conditions.size() > 0) {
            sql.append(" where ").append(this.joinConditions(conditions, " and "));
        }

        if (true) {
            searchData.setOrderString("createDate desc");
        }

        // Create query statements
        TypedQuery<DownDsapInoviceHis> query = this.getEntityManager().createQuery(sql.toString(), DownDsapInoviceHis.class);
        TypedQuery<Long> totalQuery = this.getEntityManager().createQuery("select count(a.id) " + sql.toString(), Long.class);

        // Append conditions' variables
        this.addParameters(query, conditions);
        this.addParameters(totalQuery, conditions);

        // Send back returns
        return this.getPagedList(totalQuery, query, searchData);
    }


}













