package com.finereport.service.impl.top10;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finereport.service.ExportTop10I;
import com.lzsoft.entity.common.CustBaseEntity;
import com.lzsoft.entity.common.CustCorpEntity;
import com.lzsoft.entity.common.CustIndvEntity;
import com.lzsoft.entity.common.ExchangeRateEntity;
import com.lzsoft.entity.common.GuaranteeEntity;
import com.lzsoft.entity.common.LimitEntity;
import com.lzsoft.entity.common.LoanEntity;
import com.lzsoft.entity.common.Top10Entity;
import com.lzsoft.entity.common.TradefinanceEntity;
import com.lzsoft.entity.common.TradefinanceSecEntity;

@Service("top10Service")
@Transactional
public class ExportTop10 extends CommonServiceImpl implements ExportTop10I {

	public void calculateTop10() {
		/**
		 * 这里要计算人民币金额 的字段 要在导数程序里先计算出人民币金额
		 * 
		 */
		Date busDate = getBusDate();
		calculateG1301(busDate);
	}

	private Date getBusDate() {
		CriteriaQuery cq = new CriteriaQuery(ExchangeRateEntity.class);
		cq.eq("ratetype", "11");
		cq.max("importdate");
		cq.add();
		return getUniqueResultByCriteriaQuery(cq);
	}

	private void calculateG1301(Date busDate) {
		List<LoanEntity> loans = getLoans(busDate);

		Map<String, List<String>> cusm = new HashMap<String, List<String>>();
		Map<String, List<LoanEntity>> ploans = new HashMap<>();
		Map<String, List<LimitEntity>> plimits = new HashMap<>();
		Map<String, List<TradefinanceEntity>> ptfs = new HashMap<>();
		Map<String, List<TradefinanceSecEntity>> ptf2s = new HashMap<>();
		Map<String, List<GuaranteeEntity>> pguars = new HashMap<>();
		List dcsnms = getFieldValueByWhere(
				LoanEntity.class,
				"csnm",
				"importdate=? and dtyp<>? and (loangrade=? or loanRisk=? and loangrade is null)  ",
				new Object[] { busDate, "02501", "FQ02", "FQ02" });
		@SuppressWarnings("unchecked")
		List<CustBaseEntity> csnms = getCustinfos(busDate, dcsnms);
		Map<String, String> c_encode_name = getCustEncodeandname(csnms);
		splitCustByParn(cusm, csnms);
		splitLoansToParn(loans, cusm, ploans);

		// 授信
		List<LimitEntity> limits = getLimits(dcsnms, busDate);
		splitLimitsToParn(limits, cusm, plimits);
		// 贸易及贸易2
		List<TradefinanceEntity> tfs = getTfs(dcsnms, busDate);
		splitTfsToParn(tfs, cusm, ptfs);
		List<TradefinanceSecEntity> tf2s = getTf2s(dcsnms, busDate);
		splitTf2sToParn(tf2s, cusm, ptf2s);
		// 保单
		List<GuaranteeEntity> guars = getGuarantees(dcsnms, busDate);
		splitGuaranteeToParn(guars, cusm, pguars);

		Set<String> keys = ploans.keySet();
		Map<String, List<Top10Entity>> pbls = new HashMap<>();
		List<Top10Entity> top10s = null;
		for (String key : keys) {
			Top10Entity top10 = new Top10Entity();

			List<LoanEntity> ls = ploans.get(key);
			if (pbls.get(ls.get(0).getParentbrca()) == null) {
				top10s = new ArrayList<>();
				pbls.put(ls.get(0).getParentbrca(), top10s);
			} else {
				top10s = pbls.get(ls.get(0).getParentbrca());
			}

			String e_n = "";
			if (key.indexOf("_") > -1) {// 没有parn code时
				e_n = c_encode_name.get(ls.get(0).getCsnm());
			} else {
				e_n = c_encode_name.get(key);
			}
			top10.setCtnm(StringUtils.substringAfter(e_n, "_"));// 1
			top10.setEncode(StringUtils.substringBefore(e_n, "_"));// 2
			top10.setD(calculateD(ls));// 3
			top10.setE(calculateE(ls));// 4
			top10.setF(calculateF(ls));// 5
			top10.setG(calculateG(ls));// 6
			top10.setH(calculateH(ls));// 7
			//top10.setI(calculateI(ls));// 8 //逻辑不正确
			List<LimitEntity> lms = plimits.get(key);
			List<TradefinanceEntity> tfms = ptfs.get(key);
			List<TradefinanceSecEntity> tf2ms = ptf2s.get(key);
			// top10.setJ(calculateJ(lms));// 9
			top10.setK(calculateK(lms, tfms, tf2ms));// 10
			// top10.setL(calculateL(lms));// 11
			List<GuaranteeEntity> guarms = pguars.get(key);
			top10.setM(calculateM(ls, guarms));// 12
			top10.setImportdate(busDate);
			top10.setRptno("G1301");
			top10.setParentbrca(ls.get(0).getParentbrca());
			top10s.add(top10);
		}

		Set<String> pbkey = pbls.keySet();

		for (String key : pbkey) {

			List<Top10Entity> t10 = new ArrayList<>();
			t10 = pbls.get(key);

			sortList(t10);
			// 插入10行记录，没有数据时插入seqno
			if (t10.size() >= 10) {
				t10 = t10.subList(0, 9);
				int seq = 1;
				for (Top10Entity top10 : t10) {
					top10.setSeqno(String.valueOf(seq));
				}
			} else {
				int count = t10.size();
				for (int i = 0; i < 10; i++) {
					if (i < count) {
						t10.get(i).setSeqno(String.valueOf(i + 1));
					} else {
						Top10Entity t = new Top10Entity();
						t.setSeqno(String.valueOf(i + 1));
						t.setImportdate(busDate);
						t.setParentbrca(key);
						t.setRptno("G1301");
						t10.add(t);
					}
				}
			}
			deleteAllEntitie(findHql(Top10Entity.class,
					" rptno=? and parentbrca=? and importdate=?",
					new Object[] { "G1301", key, busDate }));
			batchSave(t10);
		}
	}

	/**
	 * 排序
	 * 
	 * @param top10s
	 */
	private void sortList(List<Top10Entity> top10s) {
		if (!top10s.isEmpty()) {// top10s不为空时，排序,插入10行
			Collections.sort(top10s, new Comparator<Top10Entity>() {
				@Override
				public int compare(Top10Entity o1, Top10Entity o2) {
					// 按D列升序排列
					if (o1.getD().doubleValue() <= o2.getD().doubleValue()) {
						return 1;
					}
					return -1;
				}
			});
		}

	}

	private List<TradefinanceEntity> getTfs(List dcsnms, Date busDate) {
		CriteriaQuery cq = new CriteriaQuery(TradefinanceEntity.class);
		cq.eq("importdate", busDate);
		cq.in("csnm", dcsnms.toArray());
		cq.add();
		return getListByCriteriaQuery(cq, false);
	}

	private List<GuaranteeEntity> getGuarantees(List dcsnms, Date busDate) {
		CriteriaQuery cq = new CriteriaQuery(GuaranteeEntity.class);
		cq.eq("importdate", busDate);
		cq.in("csnm", dcsnms.toArray());
		cq.add();
		return getListByCriteriaQuery(cq, false);
	}

	private List<TradefinanceSecEntity> getTf2s(List dcsnms, Date busDate) {
		CriteriaQuery cq = new CriteriaQuery(TradefinanceSecEntity.class);
		cq.eq("importdate", busDate);
		cq.in("csnm", dcsnms.toArray());
		cq.add();
		return getListByCriteriaQuery(cq, false);
	}

	private List<LimitEntity> getLimits(List dcsnms, Date busDate) {
		CriteriaQuery cq = new CriteriaQuery(LimitEntity.class);
		cq.eq("importdate", busDate);
		cq.in("csnm", dcsnms.toArray());
		cq.add();
		return getListByCriteriaQuery(cq, false);
	}

	private BigDecimal calculateM(List<LoanEntity> ls,
			List<GuaranteeEntity> guarms) {
		BigDecimal sum = new BigDecimal(0);
		String mrt_code = "'200','250','310'";
		if (null != guarms)
			for (GuaranteeEntity g : guarms) {
				for (LoanEntity l : ls) {
					if (g.getCnlobs().indexOf(l.getLano()) > 0
							&& mrt_code.indexOf(g.getMrtcode()) > 0) {
						sum=sum.add(g.getEstValcny());	//这里要计算人民币金额
					}
				}
			}
		return sum;
	}

	private BigDecimal calculateK(List<LimitEntity> ls,
			List<TradefinanceEntity> tfms, List<TradefinanceSecEntity> tf2ms) {
		BigDecimal sum = new BigDecimal(0);
		for (LimitEntity limitEntity : ls) {
			if (// limitEntity.getLineCode().equals(limitEntity.getCreditLine())&&
					limitEntity.getRevoLoancmtm().equals("2")) {
				sum = sum.add(limitEntity.getFamt());// /这里要计算人民币金额 Fumt????
			}
		}
		String lcte = "'23001','23002','23005','23010','23015','23020','23025','23030','23170','23202','23206','23204'";
		if (null != tfms)
			for (TradefinanceEntity tf : tfms) {
				if (lcte.indexOf(tf.getLcte()) > 0) {
					//sum = sum.add(new BigDecimal(0));	// 这里要计算人民币金额
					sum = sum.add(tf.getLcblcny());		// 这里要计算人民币金额
				}

			}
		if (null != tf2ms)
			for (TradefinanceSecEntity tf2 : tf2ms) {
				sum = sum.add(tf2.getLcamcny());		// 这里要计算人民币金额
				//sum = sum.add(tf2.getLcam());			// 这里要计算人民币金额
			}
		return sum;
	}

	/**
	 * 
	 * 
	 * @param ls
	 * @return
	 */
	private BigDecimal calculateH(List<LoanEntity> ls) {
		BigDecimal sum = new BigDecimal(0);
		for (LoanEntity loanEntity : ls) {
			sum = sum.add(loanEntity.getSpecialProcny());	// 这里要计算人民币金额
			//sum = sum.add(loanEntity.getSpecialPro());		// 这里要计算人民币金额
		}
		return sum;
	}

	private String calculateG(List<LoanEntity> ls) {
		int ovdue_day = 0;
		for (LoanEntity loanEntity : ls) {
			if (Integer.parseInt(loanEntity.getOvdueDay()) > ovdue_day) {
				ovdue_day = Integer.parseInt(loanEntity.getOvdueDay());
			}
		}
		if (ovdue_day > 0 && ovdue_day <= 30) {
			return "30天以内";
		} else if (ovdue_day > 30 && ovdue_day <= 90) {
			return "31到90天";
		} else if (ovdue_day > 90 && ovdue_day <= 180) {
			return "91到180天";
		} else if (ovdue_day > 180 && ovdue_day <= 270) {
			return "181到270天";
		} else if (ovdue_day > 270 && ovdue_day <= 360) {
			return "271到360天";
		} else if (ovdue_day > 360) {
			return "361天以上";
		}
		return "";
	}

	private BigDecimal calculateF(List<LoanEntity> ls) {
		BigDecimal sum = new BigDecimal(0);
		for (LoanEntity loanEntity : ls) {
			if (loanEntity.getLoanStat().equals("FS03"))
				sum = sum.add(loanEntity.getCurInsRecvcny());

		}
		return sum;
	}

	private BigDecimal calculateE(List<LoanEntity> ls) {
		BigDecimal sum = new BigDecimal(0);
		String ovdue_loan_prd = "'OT03','OT04','OT05','OT06'";
		for (LoanEntity loanEntity : ls) {
			if (loanEntity.getLoanStat().equals("FS03")
					&& ovdue_loan_prd.indexOf(loanEntity.getOvdueLoanPrd()) > 0)
				sum = sum.add(loanEntity.getNotesellBlcny());

		}
		return sum;
	}

	/**
	 * 计算D列
	 * 
	 * @param ls
	 * @return
	 */

	private BigDecimal calculateD(List<LoanEntity> ls) {
		BigDecimal sum = new BigDecimal(0);
		for (LoanEntity loanEntity : ls) {
			sum = sum.add(loanEntity.getNotesellBlcny());
		}
		return sum;
	}

	private Map<String, String> getCustEncodeandname(List<CustBaseEntity> csnms) {
		Map<String, String> c_encode_name = new HashMap<>();
		for (CustBaseEntity custBaseEntity : csnms) {
			c_encode_name
					.put(custBaseEntity.getCsnm(), custBaseEntity.getEncode()
							+ "_" + custBaseEntity.getCtnm());
		}
		return c_encode_name;
	}

	/**
	 * 整合guars到各parn
	 * 
	 * @param guars
	 * @param cusm
	 * @param pguars
	 */
	private void splitGuaranteeToParn(List<GuaranteeEntity> guars,
			Map<String, List<String>> cusm,
			Map<String, List<GuaranteeEntity>> pguars) {
		Set<String> keys = cusm.keySet();
		for (String key : keys) {
			List<String> cs = cusm.get(key);
			for (GuaranteeEntity guar : guars) {
				if (cs.contains(guar.getCsnm())) {
					if (null == pguars.get(key)) {
						List<GuaranteeEntity> c = new ArrayList<>();
						c.add(guar);
						pguars.put(key, c);
					} else {
						pguars.get(key).add(guar);
					}
				}
			}

		}

	}

	/**
	 * 整合tfs到各parn
	 * 
	 * @param tfs
	 * @param cusm
	 * @param ptfs
	 */
	private void splitTfsToParn(List<TradefinanceEntity> tfs,
			Map<String, List<String>> cusm,
			Map<String, List<TradefinanceEntity>> ptfs) {
		Set<String> keys = cusm.keySet();
		for (String key : keys) {
			List<String> cs = cusm.get(key);
			for (TradefinanceEntity tf : tfs) {
				if (cs.contains(tf.getCsnm())) {
					if (null == ptfs.get(key)) {
						List<TradefinanceEntity> c = new ArrayList<>();
						c.add(tf);
						ptfs.put(key, c);
					} else {
						ptfs.get(key).add(tf);
					}
				}
			}

		}

	}

	/**
	 * 整合tf2s到各parn
	 * 
	 * @param tf2s
	 * @param cusm
	 * @param ptf2s
	 */
	private void splitTf2sToParn(List<TradefinanceSecEntity> tf2s,
			Map<String, List<String>> cusm,
			Map<String, List<TradefinanceSecEntity>> ptf2s) {
		Set<String> keys = cusm.keySet();
		for (String key : keys) {
			List<String> cs = cusm.get(key);
			for (TradefinanceSecEntity tf2 : tf2s) {
				if (cs.contains(tf2.getCsnm())) {
					if (null == ptf2s.get(key)) {
						List<TradefinanceSecEntity> c = new ArrayList<>();
						c.add(tf2);
						ptf2s.put(key, c);
					} else {
						ptf2s.get(key).add(tf2);
					}
				}
			}
		}
	}

	/**
	 * 整合limits到各parn
	 * 
	 * @param limits
	 * @param cusm
	 * @param plimits
	 */
	private void splitLimitsToParn(List<LimitEntity> limits,
			Map<String, List<String>> cusm,
			Map<String, List<LimitEntity>> plimits) {
		Set<String> keys = cusm.keySet();
		for (String key : keys) {
			List<String> cs = cusm.get(key);
			for (LimitEntity limit : limits) {
				if (cs.contains(limit.getCsnm())) {
					if (null == plimits.get(key)) {
						List<LimitEntity> c = new ArrayList<>();
						c.add(limit);
						plimits.put(key, c);
					} else {
						plimits.get(key).add(limit);
					}
				}
			}

		}

	}

	/**
	 * 整合loans到各parn
	 * 
	 * @param loans
	 * @param cusm
	 * @param ploans
	 */
	private void splitLoansToParn(List<LoanEntity> loans,
			Map<String, List<String>> cusm, Map<String, List<LoanEntity>> ploans) {
		Set<String> keys = cusm.keySet();
		for (String key : keys) {
			List<String> cs = cusm.get(key);
			for (LoanEntity loan : loans) {
				if (cs.contains(loan.getCsnm())) {
					if (null == ploans.get(key)) {
						List<LoanEntity> c = new ArrayList<>();
						c.add(loan);
						ploans.put(key, c);
					} else {
						ploans.get(key).add(loan);
					}
				}
			}

		}
	}

	/**
	 * 
	 * @param cusm
	 *            整合后客户Map
	 * @param csnms
	 */
	private void splitCustByParn(Map<String, List<String>> cusm,
			List<CustBaseEntity> csnms) {
		for (CustBaseEntity custBaseEntity : csnms) {
			if (custBaseEntity.getSource() == "A1") {
				CustIndvEntity indv = (CustIndvEntity) custBaseEntity;
				List<String> c = new ArrayList<>();
				c.add(indv.getCsnm());
				cusm.put(indv.getCsnm(), c);
			} else {
				CustCorpEntity corp = (CustCorpEntity) custBaseEntity;

				String key = "";
				if (null != corp.getParn()) {// 集团有code
					key = corp.getParn();
				} else if (null != corp.getPctnm()) {// 只有母公司名称
					key = "_" + corp.getPctnm();
				} else {
					key = corp.getCsnm();// 单一法人
				}
				if (null == cusm.get(key)) {
					List<String> c = new ArrayList<String>();
					c.add(corp.getCsnm());
					cusm.put(key, c);
				} else {
					cusm.get(key).add(corp.getCsnm());
				}
			}
		}
	}

	private List<LoanEntity> getLoans(Date busDate) {

		CriteriaQuery cq = new CriteriaQuery(LoanEntity.class);
		cq.eq("importdate", busDate);
		cq.notEq("dtyp", "02501");
		cq.eq("loangrade", "FQ02");
		cq.add();
		List<LoanEntity> l1 = getListByCriteriaQuery(cq, false);

		CriteriaQuery cq2 = new CriteriaQuery(LoanEntity.class);
		cq2.eq("importdate", busDate);
		cq2.notEq("dtyp", "02501");
		cq2.eq("loanRisk", "FQ02");
		cq2.isNull("loangrade");
		cq2.add();
		List<LoanEntity> l2 = getListByCriteriaQuery(cq2, false);
		l1.addAll(l2);
		return l1;

	}

	/**
	 * 提取日期下，客户编号在csnms范围内的客户
	 * 
	 * @param busDate
	 * @param csnms
	 * @return
	 */
	protected List<CustBaseEntity> getCustinfos(Date busDate, List<String> csnms) {
		List<CustBaseEntity> custs = new ArrayList<CustBaseEntity>();
		CriteriaQuery cqc = new CriteriaQuery(CustCorpEntity.class);
		cqc.eq("importdate", busDate);
		cqc.in("csnm", csnms.toArray());
		cqc.add();
		List<CustBaseEntity> corps = getListByCriteriaQuery(cqc, false);
		CriteriaQuery cqi = new CriteriaQuery(CustIndvEntity.class);
		cqi.eq("importdate", busDate);
		cqi.in("csnm", csnms.toArray());
		cqi.add();
		List<CustBaseEntity> indvs = getListByCriteriaQuery(cqi, false);
		custs.addAll(corps);
		custs.addAll(indvs);
		return custs;
	}
}
