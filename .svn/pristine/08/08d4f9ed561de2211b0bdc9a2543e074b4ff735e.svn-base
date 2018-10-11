package com.woodare.template.jersery.webservice.busi;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.thirdparty.passway._data.PasswayDspResponseData;
import com.woodare.core.util.SDFFactory;
import com.woodare.framework.exception.MessageWooException;
import com.woodare.framework.exception.RollbackMessageWooException;
import com.woodare.framework.utils.SaftyBeanUtils;
import com.woodare.template.constant.EnumError;
import com.woodare.template.helper.cache.DownAgents;
import com.woodare.template.helper.cache.DownMerchants;
import com.woodare.template.jersery.servicedata.downmerchant.DownMerchantServiceData;
import com.woodare.template.jersery.webservice.busi.base.IDownMerchantBalanceService;
import com.woodare.template.jpa.model.DownDspInvoice;
import com.woodare.template.jpa.model.DownMerchantBalance;
import com.woodare.template.jpa.model.DownMerchantFundAccount;
import com.woodare.template.jpa.model.data.EnumBalSourceType;
import com.woodare.template.jpa.model.data.EnumBalanceAccType;
import com.woodare.template.jpa.model.data.EnumBalanceType;
import com.woodare.template.jpa.model.data.EnumDspStatus;
import com.woodare.template.jpa.persistence.data.downagent.DownAgentData;
import com.woodare.template.jpa.persistence.data.downdspinvoice.DownDspInvoiceData;
import com.woodare.template.jpa.persistence.data.downmerchant.DownMerchantData;
import com.woodare.template.jpa.persistence.data.downmerchantfundaccount.SearchDownMerchantFundAccountData;
import com.woodare.template.jpa.persistence.persistence.IDownDspInvoiceDAO;
import com.woodare.template.jpa.persistence.persistence.IDownMerchantBalanceDAO;
import com.woodare.template.jpa.persistence.persistence.IDownMerchantDAO;
import com.woodare.template.jpa.persistence.persistence.IDownMerchantFundAccountDAO;

/**
 * @author Luke
 */
@Service(value = "downMerchantBalanceService")
public class DownMerchantBalanceService implements IDownMerchantBalanceService {
	private Log log = LogFactory.getLog(DownMerchantBalanceService.class);

	// 资金方向-正向
	public static int BAL_DIRECT_SUCC = 1;
	// 资金方向-反向
	public static int BAL_DIRECT_FAIL = -1;

	@Autowired
	private IDownMerchantDAO downMerchantDAO;

	@Autowired
	private IDownMerchantBalanceDAO downMerchantBalanceDAO;

	@Autowired
	private IDownMerchantFundAccountDAO downMerchantFundAccountDAO;

	@Autowired
	private IDownDspInvoiceDAO downDspInvoiceDAO;

	/**
	 * @param merchant
	 *            关联机构对象
	 * @param balanceDetail
	 *            待保存账单信息
	 * @param accType
	 *            操作资金账户
	 * @param balanceType
	 *            收入、支出
	 * @param txnAmt
	 *            资金额
	 * @param feeAmt
	 *            手续费
	 */
	public void addMerchantBalance(DownMerchantData merchant, DownMerchantBalance balanceDetail, EnumBalanceAccType accType, EnumBalanceType balanceType, long txnAmt, long feeAmt) throws RollbackMessageWooException {
		int ret = 0;

		// 变动资金数值（可加额、可减额）
		long totDiffAmt = (txnAmt + feeAmt);

		DownMerchantServiceData diffModel = new DownMerchantServiceData();
		diffModel.setMchNo(merchant.getMchNo());
		// 是否允许预授权使用
		diffModel.setPreAuthDspFlg(merchant.getPreAuthDspFlg());
		// 资金授信比率
		diffModel.setCreditRatio(merchant.getCreditRatio());

		// 更新[结算账户]
		if (EnumBalanceAccType.Settle.equals(accType)) {
			// 记收入
			if (EnumBalanceType.In.equals(balanceType)) {
				diffModel.setSettleInAmt(totDiffAmt);
			}
			// 记支出
			else {
				diffModel.setSettleOutAmt(totDiffAmt);
			}
		}
		// 更新[实时账户]
		else if (EnumBalanceAccType.Credit.equals(accType)) {
			// 记收入
			if (EnumBalanceType.In.equals(balanceType)) {
				diffModel.setCurInAmt(totDiffAmt);
			}
			// 记支出
			else {
				diffModel.setCurOutAmt(totDiffAmt);
			}
		}

		// 修改汇总余额
		ret = downMerchantFundAccountDAO.changeBalance(diffModel);
		if (ret == 0) {
			throw new RollbackMessageWooException("更新账户余额失败");
		}

		// 机构开启账户资金变动明细，且本次有余额变动
		if (merchant.getEnableBalChgFlg() != null && merchant.getEnableBalChgFlg()) {
			// 插入本次记账明细
			DownMerchantFundAccount fundAccount = downMerchantFundAccountDAO.findByMchNo(merchant.getMchNo());

			// 原账户余额
			long lastBal = 0;
			// 结算账户
			if (EnumBalanceAccType.Settle.equals(accType)) {
				lastBal = fundAccount.getSettleInAmt() - fundAccount.getSettleOutAmt();
				// 扣除本次收入
				if (EnumBalanceType.In.equals(balanceType)) {
					lastBal -= totDiffAmt;
				}
				// 累计本次支出
				else {
					lastBal += totDiffAmt;
				}
			}
			// 实时账户
			else if (EnumBalanceAccType.Credit.equals(accType)) {
				lastBal = fundAccount.getCurInAmt() - fundAccount.getCurOutAmt();
				// 扣除本次收入
				if (EnumBalanceType.In.equals(balanceType)) {
					lastBal -= totDiffAmt;
				}
				// 累计本次支出
				else {
					lastBal += totDiffAmt;
				}
			}

			// 代付资金记账
			DownMerchantBalance txnBal = new DownMerchantBalance();
			SaftyBeanUtils.copyProperties(balanceDetail, txnBal, new String[] { "id" });
			// 关联机构编号
			txnBal.setMchNo(merchant.getMchNo());
			// 关联机构名称
			txnBal.setMchName(merchant.getName());
			// 记账流水号
			txnBal.setBalanceNo(SDFFactory.getBalanceNo());
			// 账户类型
			txnBal.setAccType(accType);
			// 记账日期
			txnBal.setBalanceDate(SDFFactory.DATE.format(new Date()));
			// 本次交易额
			txnBal.setDiffAmt(new BigDecimal(txnAmt).divide(new BigDecimal("100")));
			// 交易入账后余额
			txnBal.setBalAmt(new BigDecimal(lastBal + txnAmt).divide(new BigDecimal("100")));
			txnBal.setSortNum((short) 0);

			Calendar nowTime = Calendar.getInstance();
			txnBal.setCreateDate(nowTime.getTime());
			downMerchantBalanceDAO.save(txnBal);

			if (feeAmt > 0) {
				// 代付资金记账 - 代付手续费
				DownMerchantBalance feeBal = new DownMerchantBalance();
				SaftyBeanUtils.copyProperties(txnBal, feeBal, new String[] { "id" });
				feeBal.setRemark(feeBal.getRemark() + "[手续费]");
				feeBal.setSortNum((short) 1);

				// 本次交易手续费记账
				feeBal.setDiffAmt(new BigDecimal(feeAmt).divide(new BigDecimal("100")));
				// 手续费记如后，账户余额
				feeBal.setBalAmt(new BigDecimal(lastBal + txnAmt).divide(new BigDecimal("100")));
				downMerchantBalanceDAO.save(feeBal);
			}
		}
	}

	/**
	 * @param agent
	 *            关联机构对象
	 * @param balanceDetail
	 *            待保存账单信息
	 * @param accType
	 *            操作资金账户
	 * @param balanceType
	 *            收入、支出
	 * @param diffAmt
	 *            资金额
	 * @throws RollbackMessageWooException
	 */
	private void addAgentBalance(DownAgentData agent, DownMerchantBalance balanceDetail, EnumBalanceAccType accType, EnumBalanceType balanceType, long diffAmt) throws RollbackMessageWooException {
		int ret = 0;

		// 变动资金数值（可加额、可减额）
		DownMerchantServiceData diffModel = new DownMerchantServiceData();
		diffModel.setMchNo(agent.getAgentNo());
		// 资金授信比率, 代理
		diffModel.setCreditRatio(100l);

		// 结算账户
		if (EnumBalanceAccType.Settle.equals(accType)) {
			// 记收入
			if (EnumBalanceType.In.equals(balanceType)) {
				diffModel.setSettleInAmt(diffAmt);
			}
			// 记支出
			else {
				diffModel.setSettleOutAmt(diffAmt);
			}
		}
		// 实时账户
		else if (EnumBalanceAccType.Credit.equals(accType)) {
			// 记收入
			if (EnumBalanceType.In.equals(balanceType)) {
				diffModel.setCurInAmt(diffAmt);
			}
			// 记支出
			else {
				diffModel.setCurOutAmt(diffAmt);
			}
		}

		// 修改汇总余额
		ret = downMerchantFundAccountDAO.changeBalance(diffModel);
		if (ret == 0) {
			throw new RollbackMessageWooException("更新账户余额失败");
		}

		// 机构开启账户资金变动明细，且本次有余额变动
		if (agent.getEnableBalChgFlg() != null && agent.getEnableBalChgFlg()) {
			// 插入本次记账明细
			DownMerchantFundAccount fundAccount = downMerchantFundAccountDAO.findByMchNo(agent.getAgentNo());

			// 原账户余额
			long lastBal = 0;
			// 结算账户
			if (EnumBalanceAccType.Settle.equals(accType)) {
				lastBal = fundAccount.getSettleInAmt() - fundAccount.getSettleOutAmt();
				// 扣除本次收入
				if (EnumBalanceType.In.equals(balanceType)) {
					lastBal -= diffAmt;
				}
				// 累计本次支出
				else {
					lastBal += diffAmt;
				}
			}
			// 实时账户
			else if (EnumBalanceAccType.Credit.equals(accType)) {
				lastBal = fundAccount.getCurInAmt() - fundAccount.getCurOutAmt();
				// 扣除本次收入
				if (EnumBalanceType.In.equals(balanceType)) {
					lastBal -= diffAmt;
				}
				// 累计本次支出
				else {
					lastBal += diffAmt;
				}
			}

			DownMerchantBalance txnBal = new DownMerchantBalance();
			SaftyBeanUtils.copyProperties(balanceDetail, txnBal, new String[] { "id" });
			// 关联机构编号
			balanceDetail.setMchNo(agent.getAgentNo());
			// 关联机构名称
			balanceDetail.setMchName(agent.getName());
			// 记账流水号
			balanceDetail.setBalanceNo(SDFFactory.getBalanceNo());
			// 账户类型
			balanceDetail.setAccType(accType);
			// 记账日期
			balanceDetail.setBalanceDate(SDFFactory.DATE.format(new Date()));
			// 本次交易额
			balanceDetail.setDiffAmt(new BigDecimal(diffAmt).divide(new BigDecimal("100")));
			// 交易入账后余额
			balanceDetail.setBalAmt(new BigDecimal(lastBal + diffAmt).divide(new BigDecimal("100")));
			balanceDetail.setSortNum((short) 0);

			Calendar nowTime = Calendar.getInstance();
			balanceDetail.setCreateDate(nowTime.getTime());
			downMerchantBalanceDAO.save(balanceDetail);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean recordSettle() throws Exception {
		String settleDate = SDFFactory.DATE.format(new Date());

		// 确认本次结算是否已存在数据
		SearchDownMerchantFundAccountData searchFundData = new SearchDownMerchantFundAccountData();
		searchFundData.setSettleDate(settleDate);
		searchFundData.setPageSize(1);
		List<DownMerchantFundAccount> oriModels = downMerchantFundAccountDAO.searchItems(searchFundData);
		// 防止已存在数据，跳过
		if (oriModels != null && oriModels.size() > 0) {
			return false;
		}

		// 查询存在余额变动的数据
		searchFundData = new SearchDownMerchantFundAccountData();
		searchFundData.setPageSize(Integer.MAX_VALUE);
		searchFundData.setChangedFlag(true);
		oriModels = downMerchantFundAccountDAO.searchItems(searchFundData);

		if (oriModels != null && oriModels.size() > 0) {
			// 记录变动数据至历史记录表内
			downMerchantFundAccountDAO.addToHistory(settleDate);

			// 生成本次记账批次号
			String balanceNo = SDFFactory.getBalanceNo();
			int cnt = downMerchantFundAccountDAO.makeSettle(settleDate, balanceNo);
			if (cnt > 0) {
				searchFundData = new SearchDownMerchantFundAccountData();
				// 限定符合本次数据变动范围的集合
				searchFundData.setSettleDate(settleDate);
				searchFundData.setPageSize(Integer.MAX_VALUE);

				short sortNum = 0;
				// 记录资金账户变动明细
				for (DownMerchantFundAccount oriModel : oriModels) {
					// 实时账户出账明细
					DownMerchantBalance txnBal = new DownMerchantBalance();
					txnBal.setMchNo(oriModel.getMchNo());
					txnBal.setMchName(oriModel.getMchName());
					// 实时账户
					txnBal.setAccType(EnumBalanceAccType.Credit);
					txnBal.setBalanceDate(settleDate);
					txnBal.setBalanceNo(balanceNo);
					txnBal.setSourceType(EnumBalSourceType.Settle);
					txnBal.setReferTransNo("");
					txnBal.setReferChannel("");
					txnBal.setReferChannelAccNo("");
					txnBal.setDiffAmt(new BigDecimal((oriModel.getCurInAmt() - oriModel.getCurOutAmt()) * -1 / 100.0).setScale(2, BigDecimal.ROUND_HALF_EVEN));
					txnBal.setBalAmt(new BigDecimal("0").setScale(2, BigDecimal.ROUND_HALF_EVEN));
					txnBal.setRemark("批结算转出");
					txnBal.setSortNum(sortNum++);
					downMerchantBalanceDAO.save(txnBal);

					// 结算账户入账明细
					txnBal = new DownMerchantBalance();
					txnBal.setMchNo(oriModel.getMchNo());
					txnBal.setMchName(oriModel.getMchName());
					// 结算账户
					txnBal.setAccType(EnumBalanceAccType.Settle);
					txnBal.setBalanceDate(settleDate);
					txnBal.setBalanceNo(balanceNo);
					txnBal.setSourceType(EnumBalSourceType.Settle);
					txnBal.setReferTransNo("");
					txnBal.setReferChannel("");
					txnBal.setReferChannelAccNo("");
					txnBal.setDiffAmt(new BigDecimal((oriModel.getCurInAmt() - oriModel.getCurOutAmt()) / 100.0).setScale(2, BigDecimal.ROUND_HALF_EVEN));
					txnBal.setBalAmt(new BigDecimal((oriModel.getSettleInAmt() - oriModel.getSettleOutAmt() + (oriModel.getCurInAmt() - oriModel.getCurOutAmt())) / 100.0).setScale(2, BigDecimal.ROUND_HALF_EVEN));
					txnBal.setRemark("批结算转入");
					txnBal.setSortNum(sortNum++);
					downMerchantBalanceDAO.save(txnBal);
				}

				// 更新机构表的结算日期
				downMerchantDAO.finishSettle(settleDate);

				return true;
			}
			else {
				// 回滚事务
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

				return false;
			}
		}
		else {
			// 更新机构表的结算日期
			downMerchantDAO.finishSettle(settleDate);
			return true;
		}
	}

	

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public DownDspInvoice handleDspResult(DownDspInvoice oriModel, PasswayDspResponseData respData) throws MessageWooException {
		// 是否计费
		boolean requiredFeeChange = false;

		// 拦截：原状态为非【处理中】
		if (!EnumDspStatus.PROCESSING.equals(oriModel.getStatus())) {
			log.error("[DspInvoiceDuplicateStatus]" + oriModel.getTransNo() + "[->]" + oriModel.getTradeNo());
			throw new MessageWooException("[" + respData.getRetCode() + "]" + respData.getRetDesc());
		}

		// 本次更新数据
		DownDspInvoiceData newModel = respData.getExtraInvoice();
		if (newModel == null) {
			newModel = new DownDspInvoiceData();
		}

		newModel.setStatus(respData.getStatus());
		newModel.setStatusDesc("[" + respData.getRetCode() + "]" + respData.getRetDesc());
		// 仅当返回[成功]时，才设置验证结果
		if (EnumDspStatus.SUCCESS.equals(respData.getStatus())) {
			requiredFeeChange = true;
			newModel.setVerifyStatus(respData.getVerifyStatus());
		}
		// 状态发生变化
		if (!oriModel.getStatus().equals(newModel.getStatus())) {
			newModel.setStatusChgDate(new Date());
		}

		// 限定更新的条件
		DownDspInvoiceData conModel = new DownDspInvoiceData();
		conModel.setId(oriModel.getId());
		conModel.setStatus(oriModel.getStatus());

		int ret = this.downDspInvoiceDAO.updateSelectiveByCons(newModel, conModel);

		// 状态更新成功, 登记记账流水
		if (ret > 0 && requiredFeeChange) {
			log.info("[DspInvoiceBalCharge][" + oriModel.getTransNo() + "]" + newModel.getStatus() + "[>>>]" + oriModel.getTradeNo());

			// 将最新数据拷贝至传入的交易对象内
			SaftyBeanUtils.copyNotEmptyProperties(newModel, oriModel, new String[] { "id" });
		}
		// 未更新到数据时，重新获取下对象数据
		else {
			oriModel = this.downDspInvoiceDAO.findOne(oriModel.getId());
		}
		return oriModel;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean recordDspInvoice(DownDspInvoice invoice, int direction, String remark) throws MessageWooException {
		boolean ret = false;

		// 交易手续费 - 扣减
		long feeAmt = invoice.getAddFeeAmt().multiply(new BigDecimal("100")).longValue() * direction;

		try {
			log.info(String.format("DspBalChargeBegin[%s/%s][%s]%s", invoice.getMchNo(), invoice.getTransNo(), (direction > 0 ? "减" : "加"), feeAmt));

			DownMerchantData merchant = DownMerchants.getByCode(invoice.getMchNo());
			if (!SDFFactory.DATE.format(new Date()).equals(merchant.getSettleDate())) {
				throw new MessageWooException("正在进行日终跑批，请稍后再试", EnumError.ERR_8003);
			}

			// 默认实时账户
			EnumBalanceAccType accType = EnumBalanceAccType.Settle;

			DownMerchantBalance txnBal = new DownMerchantBalance();
			// 记账流水号
			txnBal.setBalanceNo(SDFFactory.getBalanceNo());
			// 账单事项
			txnBal.setSourceType(EnumBalSourceType.Dsp);
			// 关联交易信息
			txnBal.setReferTransNo(invoice.getTransNo());
			txnBal.setReferChannel(invoice.getChannel().toString());
			txnBal.setReferChannelAccNo(invoice.getChannelAccNo());
			// 备注信息
			txnBal.setRemark(remark);

			// 交易记账明细登记
			this.addMerchantBalance(merchant, txnBal, accType, EnumBalanceType.Out, feeAmt, 0l);

			// 代理商分润
			long agentProfit = invoice.getAgentProfit().multiply(new BigDecimal("100")).longValue() * direction;
			// 仅记录分润不为0的交易
			if (StringUtils.isNotEmpty(merchant.getAgentId()) && agentProfit != 0) {
				DownAgentData agent = DownAgents.getByCode(merchant.getAgentId());
				addAgentBalance(agent, txnBal, accType, EnumBalanceType.In, agentProfit);
			}

			ret = true;
			log.info(String.format("DspBalChargeSucc[%s/%s][%s]%s", invoice.getMchNo(), invoice.getTransNo(), (direction > 0 ? "减" : "加"), feeAmt));
		} catch (RollbackMessageWooException e) {
			// 回滚事务
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

			log.info(String.format("DspBalChargeFail[%s/%s][%s]%s", invoice.getMchNo(), invoice.getTransNo(), (direction > 0 ? "减" : "加"), feeAmt));
		}

		return ret;

	}
}
