package com.lzsoft.autoimport.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.DateUtils;
import org.springframework.stereotype.Service;

import com.lzsoft.autoimport.commons.Constants;
import com.lzsoft.entity.common.ExchangeRateEntity;
import com.lzsoft.entity.common.SmtBondEntity;
import com.lzsoft.entity.common.SmtCcsEntity;
import com.lzsoft.entity.common.SmtCollEntity;
import com.lzsoft.entity.common.SmtCustomerConfigEntity;
import com.lzsoft.entity.common.SmtFxforwordEntity;
import com.lzsoft.entity.common.SmtFxspotEntity;
import com.lzsoft.entity.common.SmtFxswapEntity;
import com.lzsoft.entity.common.SmtIrsEntity;
import com.lzsoft.entity.common.SmtMmEntity;
import com.lzsoft.entity.common.SmtPlupdEntity;
import com.lzsoft.entity.common.SmtRepoEntity;
import com.lzsoft.entity.common.TaskscheduleEntity;

/**
 * todo 更新summit数据 
 */
@Service(value = "summitUpdateDate")
public class SummitUpdateDataImpl extends CommonServiceImpl {

	/**
	 * 日志
	 */
	private static Logger logger = Logger.getLogger(SummitUpdateDataImpl.class);

	private List<SmtFxforwordEntity> smtfxfwds = null;
	private List<SmtFxspotEntity> smtfxspots = null;
	private List<SmtFxswapEntity> smtfxswaps = null;
	private List<SmtMmEntity> smtMms = null;
	private List<SmtRepoEntity> smtRepos = null;
	private List<SmtBondEntity> smtBonds = null;
	private List<SmtIrsEntity> smtIrss = null;
	private List<SmtCcsEntity> smtCcss = null;
	private List<SmtCollEntity> smtColls = null;
	private List<SmtPlupdEntity> smtPlupds = null;

//	private List<SmtFxforwordEntity> smtfxforwordvalues = null;
//	private List<SmtFxspotEntity> smtfxspotvalues = null;
//	private List<SmtFxswapEntity> smtfxswapvalues = null;

//	private List<SmtFxforwordEntity> smtfxforwordtrades = null;
//	private List<SmtFxspotEntity> smtfxspottrades = null;
//	private List<SmtFxswapEntity> smtfxswaptrades = null;

	private List<ExchangeRateEntity> exchangeRates = null;
	private Map<String, ExchangeRateEntity> exchangeRateMaps = null;

	public void getExchangeRateMaps(List<ExchangeRateEntity> exchangeRates) {
		exchangeRateMaps = new HashMap<String, ExchangeRateEntity>();
		for (ExchangeRateEntity exchangeRate : exchangeRates) {
			// 目前只使用ratetype=11的Rate汇率
			if ("11".equals(exchangeRate.getRatetype())) {
				exchangeRateMaps.put(exchangeRate.getRatetype() + ":"
						+ exchangeRate.getCcy(), exchangeRate);
			}
		}
	}

	public int autoUpdateDataToDB() {
		try {
			//获取最大营业日
			Date tranDate = getMaxImportdate(TaskscheduleEntity.class,
					"importdate",
					"taskname='autoImportSmtData' and Executable='1'",
					new Object[] {});
			if (null != tranDate) {
				String tranDateStr = DateUtils.dateToStr(tranDate, "yyyy-MM-dd");
				// 开始导入数据
//				System.out.println("Start Update Summit data.....Date:"+tranDateStr);
				logger.info("Start Update Summit data.....Date:"+tranDateStr);
				// 取得汇率表
				exchangeRates = findHql(ExchangeRateEntity.class, " importdate='" + tranDateStr + "'");
				getExchangeRateMaps(exchangeRates);
				// 查询数据库，把kettle导入的Summit数据加载到内存，做进一步处理计算等值美金，等值人民币，日期等
				findSummitData(tranDate);
				// 计算到期日更新到内存中
				calculateDateByInputDate(tranDate);
				// 计算等值金额，更新到内存中
				calculateAmountsByTradeDate();
				// 设置转换值
				calDefValues();
				// 根据TradeDate进行处理
				//	findSummitDataByTradeDate(tranDateStr);
				//	calculateAmountsByTradeDate();
				// 根据ValDate进行处理
				// findSummitDataByValDate(tranDateStr);
				// calculateAmountsByValDate();
//				updateData();
				//更新计划任务已执行
				setTaskschedule(tranDate);
//				System.out.println("Update Summit data Completed........");
				logger.info("Update Summit data Completed........");
			}else{
				return 0;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return 1;
	}
	
	/**
	 * 设置一些默认值，转换值等
	 */
	private void calDefValues() throws Exception {
		Configuration mmType = getMmType();
		Map<String, String> mmCustomerMap = getSmtCustomerMapConfig();
		for(SmtMmEntity mm :smtMms) {
			mm.setMmNote(mmType.getString(mm.getMmtype()));
			mm.setMmCustomer(mmCustomerMap.get(mm.getCust().substring(0,4)));
		}
	}

	/**
	 * 获取MM客户列表
	 * @return
	 */
	private Map<String, String> getSmtCustomerMapConfig() {
		Map<String, String> map = new HashMap<String, String>();
		List<SmtCustomerConfigEntity> smtCustomerConfigEntities = getList(SmtCustomerConfigEntity.class);
		for(SmtCustomerConfigEntity entity: smtCustomerConfigEntities) {
			map.put(entity.getSwiftcode(), entity.getCustomername());
		}
		return map;
	}
	
	private PropertiesConfiguration getMmType() throws Exception {
		return new PropertiesConfiguration(Constants.SUMMITMMTYPE_PATH_MAPPING);
	}
	
	private Date getMaxImportdate(Class clazz, String fieldname, String hql,
			Object[] params) {
		Object importdate = getMaxFieldValueByWhere(clazz, fieldname, hql,
				params);
		return (Date) importdate;
	}

	/**
	 * 强制导入时，删除导入日期的信息
	 * 
	 * @param trandate
	 */
//	private void feedback(Date trandate) {
//		deleteAllEntitie(findHql(SmtFxforwordEntity.class, " importdate=?", new Object[] { trandate }));
//		deleteAllEntitie(findHql(SmtFxspotEntity.class, " importdate=?", new Object[] { trandate }));
//		deleteAllEntitie(findHql(SmtFxswapEntity.class, " importdate=?", new Object[] { trandate }));
//		deleteAllEntitie(findHql(SmtMmEntity.class, " importdate=?", new Object[] { trandate }));
//		deleteAllEntitie(findHql(SmtCustomerEntity.class, " importdate=?", new Object[] { trandate }));
//	}

	/**
	 * 查出所有未到期的数据(importdate为报告日，未到期逻辑在Kettle处理)计算等值金额
	 * 
	 * @param strTranDate
	 * @throws Exception
	 */
	private void findSummitData(Date tranDate) throws Exception {
		smtfxfwds = findHql(SmtFxforwordEntity.class, " importdate=?", new Object[] {tranDate});
		smtfxspots = findHql(SmtFxspotEntity.class, " importdate=?", new Object[] {tranDate});
		smtfxswaps = findHql(SmtFxswapEntity.class, " importdate=?", new Object[] {tranDate});
		smtMms = findHql(SmtMmEntity.class, " importdate=?", new Object[] {tranDate});
		smtRepos = findHql(SmtRepoEntity.class, " importdate=?", new Object[] {tranDate});
		smtBonds = findHql(SmtBondEntity.class, " importdate=?", new Object[] {tranDate});
		smtIrss = findHql(SmtIrsEntity.class, " importdate=?", new Object[] {tranDate});
		smtCcss = findHql(SmtCcsEntity.class, " importdate=?", new Object[] {tranDate});
		smtColls = findHql(SmtCollEntity.class," importdate=?", new Object[] {tranDate});
		smtPlupds = findHql(SmtPlupdEntity.class," importdate=?", new Object[] {tranDate});
	}

	/**
	 * 根据Inputdate计算日期
	 */
	private void calculateDateByInputDate(Date tranDate) {
		calculatesmtfxfwdDate(tranDate);
		calculatesmtfxswapDate(tranDate);
		calculatesmtMmDate(tranDate);
		calculatesmtRepoDate(tranDate);
		calculatesmtBondDate(tranDate);
	}

	private void calculatesmtfxfwdDate(Date tranDate) {
//		for (SmtFxforwordEntity smtfxfwd : smtfxfwds) {
//			
//		}
	}
	
	private void calculatesmtfxswapDate(Date tranDate) {
		// for (SmtFxswapEntity smtfxswap : smtfxswaps) {
		// Date valDate = DateUtils.strToDate(smtfxswap.getValdate(),
		// "yyyyMMdd");
		// Date spotDate = DateUtils.strToDate(smtfxswap.getSpotDate(),
		// "yyyyMMdd");
		// Date shortValDate = DateUtils.strToDate(smtfxswap.getShortValDate(),
		// "yyyyMMdd");
		// long dateDeff = DateUtils.getDateDeff(valDate, spotDate);
		// smtfxswap.setVsatas(dateDeff);
		// dateDeff = DateUtils.getDateDeff(valDate, shortValDate);
		// smtfxswap.setVsvatas(dateDeff);
		// dateDeff = DateUtils.getDateDeff(shortValDate, spotDate);
		// smtfxswap.setSvsatas(dateDeff);
		//
		// dateDeff = DateUtils.getMonthByDay(valDate, spotDate);
		// smtfxswap.setVsatms(dateDeff);
		// dateDeff = DateUtils.getMonthByDay(valDate, shortValDate);
		// smtfxswap.setVsvatms(dateDeff);
		// dateDeff = DateUtils.getMonthByDay(shortValDate, spotDate);
		// smtfxswap.setSvsatms(dateDeff);
		// }
	}

	/**
	 * 计算summitMM到期日
	 * @param tranDate
	 * @TODO
	 */
	private void calculatesmtMmDate(Date tranDate) {
		for (SmtMmEntity smtmm : smtMms) {
			// MM到期日，MatDate-报告日
			if (null != smtmm.getMatdate()) {
				smtmm.setMatimpd(DateUtils.dateDiff('d', smtmm.getMatdate(), tranDate));
				smtmm.setMatimpm((int) DateUtils.getMonthByDay(smtmm.getMatdate(), tranDate));
			}
			// MM到期日，MatDate-EffDate
			if (null != smtmm.getMatdate() && null != smtmm.getEffdate()) {
				smtmm.setMateffd(DateUtils.dateDiff('d', smtmm.getMatdate(), smtmm.getEffdate()));
				smtmm.setMateffm((int) DateUtils.getMonthByDay(smtmm.getMatdate(), smtmm.getEffdate()));
			}
		}
	}

	/**
	 * 计算summitRepo 到期日
	 * @param tranDate
	 * @TODO
	 */
	private void calculatesmtRepoDate(Date tranDate) {
		for (SmtRepoEntity smtRepo : smtRepos) {
			// Repo到期日，MatDate-报告日
			if (null != smtRepo.getMatdate()) {
				smtRepo.setMatimpd(DateUtils.dateDiff('d', smtRepo.getMatdate(), tranDate));
				smtRepo.setMatimpm((int) DateUtils.getMonthByDay(smtRepo.getMatdate(), tranDate));
			}
		}
	}

	/**
	 * 计算summitBond到期日
	 * @param tranDate
	 * @TODO
	 */
	private void calculatesmtBondDate(Date tranDate) {
		for (SmtBondEntity smtBond : smtBonds) {
			// MM到期日，MatDate-报告日
			if (null != smtBond.getMatdate()) {
				smtBond.setMatimpd(DateUtils.dateDiff('d', smtBond.getMatdate(), tranDate));
				smtBond.setMatimpm((int) DateUtils.getMonthByDay(smtBond.getMatdate(), tranDate));
			}
		}
	}

	private void updateData() {
//		batchSave(smtfxfwds);
//		batchSave(smtfxspots);
//		batchSave(smtfxswaps);
//		batchSave(smtMms);
//		batchSave(smtRepos);
//		batchSave(smtBonds);
//		batchSave(smtIrss);
//		batchSave(smtCcss);
//		batchSave(smtColls);
//		batchSave(smtPlupds);
//		smtfxfwds = null;
//		smtfxspots = null;
//		smtfxswaps = null;
//		smtMms = null;
//		smtRepos = null;
//		smtBonds = null;
//		smtIrss = null;
//		smtCcss = null;
//		smtColls = null;
//		smtPlupds = null;
	}

	/**
	 * 根据TradeDate查数据
	 * 
	 * @param strTranDate
	 * @throws Exception
	 */
	private void findSummitDataByTradeDate(String strTranDate) throws Exception {
//		smtfxfwds = findHql(SmtFxforwordEntity.class, " maturitydate>=" + strTranDate);
//		smtfxspots = findHql(SmtFxspotEntity.class, " valdate>=" + strTranDate);
//		smtfxswaps = findHql(SmtFxswapEntity.class, " maturitydate>=" + strTranDate);
//		smtMms = findHql(SmtMmEntity.class, " matdate>=" + strTranDate);
//		smtRepos = findHql(SmtRepoEntity.class, " matdate>=" + strTranDate);
//		smtBonds = findHql(SmtBondEntity.class, " matdate>=" + strTranDate);
	}

	/**
	 * 根据TradeDate计算等值金额
	 */
	private void calculateAmountsByTradeDate() {
		calculatesmtfxfwdAmount();
		calculatesmtfxspotAmount();
		calculatesmtfxswapAmount();
		calculatesmtMmAmount();
		calculatesmtBondAmount();
		calculatesmtRepoAmount();
		calculatesmtCollAmount();
		calculatesmtIrsAmount();
		calculatesmtCcsAmount();
		calculatesmtPlupdAmount();
	}

	private void calculatesmtPlupdAmount() {
		ExchangeRateEntity exchrate = null;
		ExchangeRateEntity exchrateUsd = exchangeRateMaps.get("11:USD");
		BigDecimal ou;
		BigDecimal oc;
		for (SmtPlupdEntity entity : smtPlupds) {
			exchrate = exchangeRateMaps.get("11:" + entity.getCcy());

			BigDecimal notional = entity.getNotional();
			oc = getCnyValue(exchrate, notional);
			ou = getUsdValue(exchrateUsd, oc);
			entity.setNotionalcny(oc);
			entity.setNotionalusd(ou);
			
			BigDecimal endaccrual = entity.getEndaccrual();
			oc = getCnyValue(exchrate, endaccrual);
			ou = getUsdValue(exchrateUsd, oc);
			entity.setEndaccrualcny(oc);
			entity.setEndaccrualusd(ou);
			
			BigDecimal endvalue = entity.getEndvalue();
			oc = getCnyValue(exchrate, endvalue);
			ou = getUsdValue(exchrateUsd, oc);
			entity.setEndvaluecny(oc);
			entity.setEndvalueusd(ou);
			
//			BigDecimal np = entity.getEndvalueNp();
//			oc = getCnyValue(exchrate, np);
//			ou = getUsdValue(exchrateUsd, oc);
//			entity.setEndvalueNpcny(oc);
//			entity.setEndvalueNpusd(ou);
//			
//			BigDecimal ns = entity.getEndvalueNs();
//			oc = getCnyValue(exchrate, ns);
//			ou = getUsdValue(exchrateUsd, oc);
//			entity.setEndvalueNpcny(oc);
//			entity.setEndvalueNpusd(ou);
//			
//			BigDecimal fp = entity.getEndvalueFp();
//			oc = getCnyValue(exchrate, fp);
//			ou = getUsdValue(exchrateUsd, oc);
//			entity.setEndvalueFpcny(oc);
//			entity.setEndvalueFpusd(ou);
//					
//			BigDecimal fs = entity.getEndvalueFs();
//			oc = getCnyValue(exchrate, fs);
//			ou = getUsdValue(exchrateUsd, oc);
//			entity.setEndvalueFscny(oc);
//			entity.setEndvalueFsusd(ou);
		}
	}

	private void calculatesmtCcsAmount() {
		ExchangeRateEntity exchrate = null;
		ExchangeRateEntity exchrateUsd = exchangeRateMaps.get("11:USD");
		BigDecimal ou;
		BigDecimal oc;
		for (SmtCcsEntity entity : smtCcss) {
			exchrate = exchangeRateMaps.get("11:" + entity.getCcy1());

			BigDecimal notional1 = entity.getNotional1();
			oc = getCnyValue(exchrate, notional1);
			ou = getUsdValue(exchrateUsd, oc);
			entity.setNotional1cny(oc);
			entity.setNotional1usd(ou);
			
			exchrate = exchangeRateMaps.get("11:" + entity.getCcy2());
			BigDecimal notional2 = entity.getNotional2();
			oc = getCnyValue(exchrate, notional2);
			ou = getUsdValue(exchrateUsd, oc);
			entity.setNotional1cny(oc);
			entity.setNotional2usd(ou);
		}
	}

	private void calculatesmtIrsAmount() {
		ExchangeRateEntity exchrate = null;
		ExchangeRateEntity exchrateUsd = exchangeRateMaps.get("11:USD");
		BigDecimal ou;
		BigDecimal oc;
		for (SmtIrsEntity entity : smtIrss) {
			exchrate = exchangeRateMaps.get("11:" + entity.getCcy1());

			BigDecimal notional1 = entity.getNotional1();
			oc = getCnyValue(exchrate, notional1);
			ou = getUsdValue(exchrateUsd, oc);
			entity.setNotional1cny(oc);
			entity.setNotional1usd(ou);
			
			exchrate = exchangeRateMaps.get("11:" + entity.getCcy2());
			BigDecimal notional2 = entity.getNotional2();
			oc = getCnyValue(exchrate, notional2);
			ou = getUsdValue(exchrateUsd, oc);
			entity.setNotional2cny(oc);
			entity.setNotional2usd(ou);
		}
	}

	private void calculatesmtCollAmount() {
		ExchangeRateEntity exchrate = null;
		ExchangeRateEntity exchrateUsd = exchangeRateMaps.get("11:USD");
		BigDecimal ou;
		BigDecimal oc;
		for (SmtCollEntity entity : smtColls) {
			exchrate = exchangeRateMaps.get("11:" + entity.getCcy());

			BigDecimal quantity = entity.getQuantity();
			oc = getCnyValue(exchrate, quantity);
			ou = getUsdValue(exchrateUsd, oc);
			entity.setQuantitycny(oc);
			entity.setQuantityusd(ou);

			BigDecimal startprice = entity.getStartprice();
			oc = getCnyValue(exchrate, startprice);
			ou = getUsdValue(exchrateUsd, oc);
			entity.setStartpricecny(oc);
			entity.setStartpriceusd(ou);

			BigDecimal endPrice = entity.getEndprice();
			oc = getCnyValue(exchrate, endPrice);
			ou = getUsdValue(exchrateUsd, oc);
			entity.setEndpricecny(oc);
			entity.setEndpriceusd(ou);
		}
	}

	/**
	 * 根据ValueDate计算等值金额
	 */
	//private void calculateAmountsByValDate() {
		// calculatesmtfxfwdAmount("valuedate");
		// calculatesmtfxspotAmount("valuedate");
		// calculatesmtfxswapAmount("valuedate");
		// calculatesmtMmAmount();
		// calculatesmtBondAmount();
		// calculatesmtRepoAmount();
	//}

	private void calculatesmtfxfwdAmount() {
		ExchangeRateEntity exchrateSold = null;
		ExchangeRateEntity exchrateBought = null;
		ExchangeRateEntity exchrateUsd = exchangeRateMaps.get("11:USD");
		BigDecimal ou;
		BigDecimal oc;
		for (SmtFxforwordEntity entity : smtfxfwds) {

			exchrateBought = exchangeRateMaps.get("11:"+entity.getBoughtccy());
			BigDecimal boughtamt = entity.getBoughtamt();
			oc = getCnyValue(exchrateBought, boughtamt);
			ou = getUsdValue(exchrateUsd, oc);
			entity.setBoughtamtcnytrade(oc);
			entity.setBoughtamtusdtrade(ou);

			exchrateSold = exchangeRateMaps.get("11:"+entity.getSoldccy());
			BigDecimal soldamt = entity.getSoldamt();
			oc = getCnyValue(exchrateSold, soldamt);
			ou = getUsdValue(exchrateUsd, oc);
			entity.setSoldamtcnytrade(oc);
			entity.setSoldamtusdtrade(ou);
		}
	}

	private void calculatesmtfxspotAmount() {
		ExchangeRateEntity exchrateSold = null;
		ExchangeRateEntity exchrateBought = null;
		ExchangeRateEntity exchrateUsd = exchangeRateMaps.get("11:USD");
		BigDecimal ou;
		BigDecimal oc;
		for (SmtFxspotEntity entity : smtfxspots) {
			exchrateBought = exchangeRateMaps.get("11:"+entity.getBoughtccy());
			BigDecimal boughtamt = entity.getBoughtamt();
			oc = getCnyValue(exchrateBought, boughtamt);
			ou = getUsdValue(exchrateUsd, oc);
			entity.setBoughtamtcnytrade(oc);
			entity.setBoughtamtusdtrade(ou);

			exchrateSold = exchangeRateMaps.get("11:"+entity.getSoldccy());
			BigDecimal soldamt = entity.getSoldamt();
			oc = getCnyValue(exchrateSold, soldamt);
			ou = getUsdValue(exchrateUsd, oc);
			entity.setSoldamtcnytrade(oc);
			entity.setSoldamtusdtrade(ou);
		}
	}

	private void calculatesmtfxswapAmount() {
		ExchangeRateEntity exchrateSold = null;
		ExchangeRateEntity exchrateBought = null;
		ExchangeRateEntity exchrateUsd = exchangeRateMaps.get("11:USD");
		BigDecimal ou;
		BigDecimal oc;
		for (SmtFxswapEntity entity : smtfxswaps) {
			// boughtamtusdtrade,boughtamtcnytrade,
			exchrateBought = exchangeRateMaps.get("11:"+entity.getBoughtccy());
			BigDecimal boughtamt = entity.getBoughtamt();
			oc = getCnyValue(exchrateBought, boughtamt);
			ou = getUsdValue(exchrateUsd, oc);
			entity.setBoughtamtcnytrade(oc);
			entity.setBoughtamtusdtrade(ou);
			//soldamtusdtrade,soldamtcnytrade,
			exchrateSold = exchangeRateMaps.get("11:"+entity.getSoldccy());
			BigDecimal soldamt = entity.getSoldamt();
			oc = getCnyValue(exchrateSold, soldamt);
			ou = getUsdValue(exchrateUsd, oc);
			entity.setSoldamtcnytrade(oc);
			entity.setSoldamtusdtrade(ou);
			// fwdboughtamtusd,fwdboughtamtcny(注：币种取反)
			exchrateBought = exchangeRateMaps.get("11:"+entity.getSoldccy());
			BigDecimal fwdboughtamt = entity.getFwdboughtamt();
			oc = getCnyValue(exchrateBought, fwdboughtamt);
			ou = getUsdValue(exchrateUsd, oc);
			entity.setFwdboughtamtcny(oc);
			entity.setFwdboughtamtusd(ou);
			// fwdsoldamtusd,fwdsoldamtcny(注：币种取反)
			exchrateSold = exchangeRateMaps.get("11:"+entity.getBoughtccy());
			BigDecimal fwdsoldamt = entity.getFwdsoldamt();
			oc = getCnyValue(exchrateSold, fwdsoldamt);
			ou = getUsdValue(exchrateUsd, oc);
			entity.setFwdsoldamtcny(oc);
			entity.setFwdsoldamtusd(ou);
		}
	}

	private void calculatesmtMmAmount() {
		ExchangeRateEntity exchrate = null;
		ExchangeRateEntity exchrateUsd = exchangeRateMaps.get("11:USD");
		ExchangeRateEntity exchrateTwd = exchangeRateMaps.get("11:TWD");
		BigDecimal ou;
		BigDecimal oc;
		BigDecimal ot;
		for (SmtMmEntity entity : smtMms) {
			exchrate = exchangeRateMaps.get("11:" + entity.getCcy());
			BigDecimal notional = entity.getNotional();
			oc = getCnyValue(exchrate, notional);
			ou = getUsdValue(exchrateUsd, oc);
			ot = getTwdValue(exchrateTwd, oc);
			entity.setNotionalcny(oc);
			entity.setNotionalusd(ou);
			entity.setNotionaltwd(ot);
		}
	}

	/**
	 * Repo 计算金额 
	 * 1、Notional 面值
	 */
	private void calculatesmtRepoAmount() {
		ExchangeRateEntity exchrate = null;
		ExchangeRateEntity exchrateUsd = exchangeRateMaps.get("11:USD");
		BigDecimal ou;
		BigDecimal oc;
		for (SmtRepoEntity entity : smtRepos) {
			exchrate = exchangeRateMaps.get("11:" + entity.getCcy());
			BigDecimal notional = entity.getNotional();
			oc = getCnyValue(exchrate, notional);
			ou = getUsdValue(exchrateUsd, oc);
			entity.setNotionalcny(oc);
			entity.setNotionalusd(ou);
		}
	}

	/**
	 * Summit Bond 
	 * 计算金额
	 *  1、Notional 标准面值 
	 *  2、EndAccual 累计应计利息 
	 *  3、EndValue 期末金额
	 *  4、Rembookval 账簿金额
	 * 
	 */
	private void calculatesmtBondAmount() {
		ExchangeRateEntity exchrate = null;
		ExchangeRateEntity exchrateUsd = exchangeRateMaps.get("11:USD");
		BigDecimal ou;
		BigDecimal oc;
		for (SmtBondEntity entity : smtBonds) {
			exchrate = exchangeRateMaps.get("11:" + entity.getCcy());
			BigDecimal notional = entity.getNotional();
			oc = getCnyValue(exchrate, notional);
			ou = getUsdValue(exchrateUsd, oc);
			entity.setNotionalcny(oc);
			entity.setNotionalusd(ou);

			BigDecimal endAccrual = entity.getEndaccrual();
			oc = getCnyValue(exchrate, endAccrual);
			ou = getUsdValue(exchrateUsd, oc);
			entity.setEndaccrualcny(oc);
			entity.setEndaccrualusd(ou);

			BigDecimal endvalue = entity.getEndvalue();
			oc = getCnyValue(exchrate, endvalue);
			ou = getUsdValue(exchrateUsd, oc);
			entity.setEndvaluecny(oc);
			entity.setEndvalueusd(ou);
			
			BigDecimal rembookval = entity.getRembookval();
			oc = getCnyValue(exchrate, rembookval);
			ou = getUsdValue(exchrateUsd, oc);
			entity.setRembookvalcny(oc);
			entity.setRembookvalusd(ou);
		}
	}

	/**
	 * 
	 * @param strTranDate
	 * @throws Exception
	 */
//	private void findSummitDataByValDate(String strTranDate) throws Exception {
//		smtfxforwordvalues = findHql(SmtFxforwordEntity.class, " valdate=" + strTranDate);
//		smtfxspotvalues = findHql(SmtFxspotEntity.class, " valdate=" + strTranDate);
//		smtfxswapvalues = findHql(SmtFxswapEntity.class, " valdate=" + strTranDate);
//	}

	/**
	 * 1、外币折成人民币、T24默认M，外币*汇率=人民币
	 * 
	 * @param exchRate
	 * @param o
	 * @return
	 */
	protected BigDecimal getCnyValue(ExchangeRateEntity exchRate, BigDecimal o) {
		BigDecimal oc = null;
		if("M".equals(exchRate.getExchangemethod())) {
			oc = o.multiply(exchRate.getExchangerateMid());
		} else {
			oc = o.divide(exchRate.getExchangerateMid(), Constants.DEFAULT_SCALE, RoundingMode.HALF_UP);
		}
		return oc;
	}

	/**
	 * 2、人民币折成美金，人民币/美金汇率=美金
	 * 
	 * @param exchRateUsd
	 * @param oc
	 * @return
	 */
	protected BigDecimal getUsdValue(ExchangeRateEntity exchRateUsd,
			BigDecimal oc) {
		BigDecimal ou = null;
		if("D".equals(exchRateUsd.getExchangemethod())) {
			ou = oc.multiply(exchRateUsd.getExchangerateMid());
		} else {
			ou = oc.divide(exchRateUsd.getExchangerateMid(),
					Constants.DEFAULT_SCALE, RoundingMode.HALF_UP);
		}
		return ou;
	}
	
	/**
	 * 3、人民币折成台币，人民币 * 台币汇率=台币
	 * 
	 * @param exchRateTwd
	 * @param oc
	 * @return
	 */
	protected BigDecimal getTwdValue(ExchangeRateEntity exchRateTwd,
			BigDecimal oc) {
		BigDecimal ot = null;
		if("D".equals(exchRateTwd.getExchangemethod())) {
			ot = oc.multiply(exchRateTwd.getExchangerateMid());
		} else {
			ot = oc.divide(exchRateTwd.getExchangerateMid(),
					Constants.DEFAULT_SCALE, RoundingMode.HALF_UP);
		}
		return ot;
	}

	/**
	 * 初始化任务计划记录
	 * 
	 * @param trandate
	 */
	private void setTaskschedule(Date trandate) {
		CriteriaQuery cq = new CriteriaQuery(TaskscheduleEntity.class);
		cq.eq("importdate", trandate);
		cq.eq("taskname", "autoImportSmtData");
		cq.add();
		TaskscheduleEntity taskscheduleEntity= getUniqueObjectByCriteriaQuery(cq);
		if (null!=taskscheduleEntity&&taskscheduleEntity.isExecutable()) {
			taskscheduleEntity.setExecutable(false);
			saveOrUpdate(taskscheduleEntity);
		}
	}
}