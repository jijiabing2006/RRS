package com.lzsoft.autoimport.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.DateUtils;
import org.jeecgframework.core.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lzsoft.autoimport.commons.Constants;
import com.lzsoft.autoimport.commons.ReadSourceFileConfig;
import com.lzsoft.autoimport.parser.IParser;
import com.lzsoft.entity.common.AccBankEntity;
import com.lzsoft.entity.common.AccCustEntity;
import com.lzsoft.entity.common.AccGLEntity;
import com.lzsoft.entity.common.BillEntity;
import com.lzsoft.entity.common.CapitalEntity;
import com.lzsoft.entity.common.CustBankEntity;
import com.lzsoft.entity.common.CustBaseEntity;
import com.lzsoft.entity.common.CustCorpEntity;
import com.lzsoft.entity.common.CustIndvEntity;
import com.lzsoft.entity.common.CustRelationEntity;
import com.lzsoft.entity.common.DepositEntity;
import com.lzsoft.entity.common.EncodeMapUsccEntity;
import com.lzsoft.entity.common.ExchangeRateEntity;
import com.lzsoft.entity.common.ExchangeRateFtzEntity;
import com.lzsoft.entity.common.FactoringEntity;
import com.lzsoft.entity.common.FixedMmEntity;
import com.lzsoft.entity.common.GuaranteeEntity;
import com.lzsoft.entity.common.JshEntity;
import com.lzsoft.entity.common.LimitEntity;
import com.lzsoft.entity.common.LoanEntity;
import com.lzsoft.entity.common.ParnRelationEntity;
import com.lzsoft.entity.common.TaskscheduleEntity;
import com.lzsoft.entity.common.TradefinanceEntity;
import com.lzsoft.entity.common.TradefinanceSecEntity;
import com.lzsoft.entity.common.TranDetailEntity;
import com.lzsoft.entity.common.WorkingDayEntity;
import com.lzsoft.entity.parameter.NescEntity;
import com.lzsoft.entity.summary.OutlineImport;
import com.lzsoft.util.LogUtil;

@Service("importData")
@Transactional
public class AutoImportDataMainService extends CommonServiceImpl {

	/**
	 * 日志
	 */
	private static Logger logger = Logger.getLogger(AutoImportDataMainService.class);
	
	@Resource(name = "parserimpl")
	private IParser parserimpl;

	private Map<String, String> resourceMappingMap;
	private Map<String, CustBaseEntity> customerMap;
	
	private List<WorkingDayEntity> workingdays = null;					//	A0营业日期表
	private List<CustIndvEntity> custIndvs = null;						//	A1个人客户信息表
	private List<CustCorpEntity> custCorps = null;						//	A2对公客户信息表
	private List<CustBankEntity> custBanks = null;						//	A3同业客户信息表
	private List<TradefinanceEntity> tradefinances = null;				//	A4贸易融资业务
	private List<BillEntity> bills = null;								//	A5票据业务
	private List<JshEntity> jshs = null;								//	A6结售汇业务
	private List<GuaranteeEntity> guarantees = null;					//	A7担保交易
	private List<LoanEntity> loans = null;								//	A8贷款信息表
	private List<DepositEntity> deposits = null;						//	A9存款信息表
	private List<TranDetailEntity> tranDetails = null;					//	A10交易流水表
	private List<AccCustEntity> acccusts = null;						//	A11账户信息表(客户账户)
	private List<AccBankEntity> accbanks = null;						//	A11账户信息表(同业账户)
	private List<AccGLEntity> accgls = null;							//	A13会计信息表
	private List<LimitEntity> limits = null;							//	A14授信额度表
	private List<CapitalEntity> capitals = null;						//	A15注册资本金表
	private List<ExchangeRateEntity> exchangeRates = null;				//	A16汇率表
	private List<ExchangeRateFtzEntity> exchangeRateFtzs = null;		//	A16_2自贸区离岸汇率表
	private List<ExchangeRateEntity> exchangeRatesFinal = null;
	private List<ExchangeRateFtzEntity> exchangeRatesFtzFinal = null;
	private List<TradefinanceSecEntity> tradefinanceSecs = null;		// 	A17贸易融资二表
	private List<CustRelationEntity> custRelations = null;				// 	A18客户关系表
	private List<ParnRelationEntity> parnRelations = null;				// 	A18_2集团关系表
	private List<FixedMmEntity> fixedMms = null;						// 	A20 存放同业定期存款信息(MM)
	private List<FactoringEntity> factoring = null;						// 	A8_1保理
//	private List<FactoringTxdtlEntity> factoringTxdtl = null;			// 	A9_4保理流水
	
	//private List<BankinfoEntity> banks = null;						//	银行分支机构表
	//private Map<String, BankinfoEntity> bankm = null;
	private Map<String, ExchangeRateEntity> exchangeRateMaps = null;
	private Map<String, ExchangeRateFtzEntity> exchangeRateFtzMaps = null;
	private Map<String, NescEntity> nescMaps = null;					// 国民经济代码Mapping
	private Map<String, AccGLEntity> accglMaps = null;					// GLMap，用于保存上一天的GL,判断和当天的区别
	

	/**
	 * @param filePath
	 * @param tranDate
	 * @return
	 * @throws Exception
	 * @TODO 将T24数据导入到数据库中
	 */
	public int autoImportDataToDB(String filePath, Date tranDate) throws Exception {
		File filePathDir = new File(filePath);
		if (null != filePathDir && filePathDir.isDirectory()) {
			String[] sourceFileList = filePathDir.list();
			if (null == sourceFileList || sourceFileList.length == 0) {
				return 4;
			}
			// 导入数据的准备工作
			System.out.println("Initialization beginning........");
			initauto(filePath, tranDate);
			System.out.println("Initialization completed........");
			// 开始导入数据
			System.out.println("Start import data........");
			insertToDB(tranDate);
			return 1;
		} else {
			return 6;
		}
	}

	/**
	 * 导入数据的准备工作:将tableName与文件路径对应放入Map中,获取国民经济代码Mapping
	 * @param filePath
	 * @param tranDate
	 * @throws Exception
	 */
	public void initauto(String filePath, Date tranDate)
			throws Exception {
		try {
			//根据配置文件，将tableName与文件路径对应放入Map中
			resourceMappingMap = ReadSourceFileConfig.getSourcefileConfig(tranDate, filePath);
			//获取国民经济代码Mapping
			nescMaps = getNescMapConfig();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 获取国民经济代码Mapping
	 * @return
	 */
	private Map<String, NescEntity> getNescMapConfig() {
		Map<String, NescEntity> map = new HashMap<String, NescEntity>();
		List<NescEntity> nescEntities = getList(NescEntity.class);
		for(NescEntity entity: nescEntities) {
			map.put(entity.getSector(), entity);
		}
		return map;
	}

	/**
	 * 导入数据到数据库
	 * @param trandate
	 * @throws Exception
	 */
	private void insertToDB(Date trandate)
			throws Exception {

		try {
			if (true) {
				feedback(trandate);							// 强制导入数据时，先清除数据库中数据
			}
			getDataFromSourcefiles(trandate);				// 	解析源数据文件
			getDiffGlFromPreDay();							//	判断gl两天的差别
			//Date nextWorkingDayFromData = DateUtils.str2Date(workingdays.get(0).getNextWorkingDay(), new SimpleDateFormat("yyyyMMdd"));
			//Date nextDay = DateUtils.addDate(trandate, 1);
			setSomeDefConvValue(trandate);					// 	设置需要默认，转换的一些值：如导数日期，Accounttype等
			calculateAmounts();								//	计算金额
			calculateDates();								//	计算日期
			insertAlldata2DB(trandate);						// 	插入数据库
			setNull();
			setTaskschedule(trandate);
			//while(nextWorkingDayFromData != nextDay){			// 如果两个日期不同，需要导入节假日的数据
			//	getDataFromSourcefiles(nextDay);				// 	读取源数据文件
			//	setSomeDefConvValue(nextDay);					// 	设置需要默认，转换的一些值：如导数日期，Accounttype等
			//	calculateAmounts();								//	计算金额
			//	calculateDates();								//	计算日期
			//	insertAlldata2DB(nextDay);						// 	插入数据库
			//	setNull();
			//	setTaskschedule(nextDay);
			//	nextDay = DateUtils.addDate(trandate, 1);
			//}
		} catch (Exception e) {
			e.printStackTrace();
			feedback(trandate);
			throw e;
		}
	}

	/**
	 * 判断gl两天的差别
	 * @throws Exception
	 * @TODO
	 */
	private void getDiffGlFromPreDay() throws Exception {
		// 初始化前一天的GL
		Object importdate = getMaxFieldValueByWhere(TaskscheduleEntity.class, "importdate", "taskname=?", new Object[]{"autoImportData"});
		List<AccGLEntity> accGlsPre = findHql(" from AccGLEntity where importdate=?", new Object[] {importdate});
		accglMaps = new HashMap<String, AccGLEntity>();
		for(AccGLEntity gl : accGlsPre){
			String key = "";
			if(null == gl.getParentbrca()) {
				key = "父行号为空|"+gl.getBrca()+"|"+gl.getNum()+"|"+gl.getCcy();
			} else {
				key = gl.getParentbrca()+"|"+gl.getBrca()+"|"+gl.getNum()+"|"+gl.getCcy();
			}
			if(null == gl.getParentbrca()){
				accglMaps.put(key, gl);
			} else {
				accglMaps.put(key, gl);
			}
		}
		// 判断GL的差异性，并记录日志
		for(AccGLEntity gl : accgls) {
			String key = "";
			if(null == gl.getParentbrca()) {
				key = "父行号为空|"+gl.getBrca()+"|"+gl.getNum()+"|"+gl.getCcy();
			} else {
				key = gl.getParentbrca()+"|"+gl.getBrca()+"|"+gl.getNum()+"|"+gl.getCcy();
			}
			if(null == accglMaps.get(key)) {
				LogUtil.generateLog(logPath, "此GL在前一天不存在，当天存在，为新增GL："+ gl.toString());
			} else {
				accglMaps.remove(key);
			}
		}
		if(accglMaps.size() !=0) {
			for(String key : accglMaps.keySet()) {
				LogUtil.generateLog(logPath, "此GL在前一天存在，当天不存在，为删除GL："+accglMaps.get(key).toString());
			}
		}
	}

	/**
	 * todo 将各种业务信息中的字段进行补充
	 * 1、设置AccoutType 	   账户类型
	 * 2、设置NESC		   国名经济代码
	 * 3、设置AccountLevel 账户级别
	 * @param trandate
	 * @throws Exception 
	 */
	private void setSomeDefConvValue(Date trandate) throws Exception {
		Configuration accounttypecny = getAccounttypeCny();
		Configuration accounttypenotcny = getAccounttypeNotCny();
		Configuration incomtypeIn = getIncomtypeIn();
		Configuration incomtypeOut = getIncomtypeOut();
		Configuration paytypeIn = getPaytypeIn();
		Configuration paytypeOut = getPaytypeOut();
		Configuration depositAccounttype = getDepositAccounttype();
		customerMap = new HashMap<String, CustBaseEntity>();
		for(CustIndvEntity entity : custIndvs) {
			customerMap.put(entity.getCsnm(), entity);									// 对私客户
			entity.setSource("A1");
			convertNesc(entity);                                                        //根据mapping更新客户信息
		} 
		System.out.println("indv:" + customerMap.size());
		for(CustCorpEntity entity : custCorps) {
			customerMap.put(entity.getCsnm(), entity);									//	对公客户
			entity.setSource("A2");
			convertEncode(entity);														//	转换组织机构代码
			convertNesc(entity);
		}
		for(CustBankEntity entity : custBanks) {
			customerMap.put(entity.getCsnm(), entity);									//	同业客户
			entity.setSource("A3");
			convertNesc(entity);
		}
		// 因为需要从acccust删除同业客户，故用Iterator迭代循环。
		Iterator<AccCustEntity> iterator = acccusts.iterator();
		while(iterator.hasNext()){
			AccCustEntity acc = iterator.next();
			// 根据币种是否为CNY，判断采取何种Mapping方式
			if("CNY".equals(acc.getCcy()))	{
				acc.setAccounttype(accounttypecny.getString(acc.getCategory()));	// Category转换成标准的AccountType
			}else{
				acc.setAccounttype(accounttypenotcny.getString(acc.getCategory()));	// Category转换成标准的AccountType
			}
			//设置账户的一些信息
			setAccountInfo(acc);
			CustBaseEntity customertype = customerMap.get(acc.getCsnm());
			// 同业客户和其他客户的账户信息分表存放
			if(null == customertype){	//如果customertype为空，代表客户号为空
				iterator.remove();		//注意这个地方,用iterator的remove方法删除
			} else if("CustBankEntity".equals(customertype.getClass().getSimpleName())) {
				//如果是同业客户，复制到AccBank中,并且AccCust中删除
				AccBankEntity accBank = new AccBankEntity();
				PropertyUtils.copyProperties(accBank, acc);
				accbanks.add(accBank);
				iterator.remove();		//注意这个地方,用iterator的remove方法删除
			} 
		}
		//将汇率数据进行筛选，并且进行对应Mapping
		exchangeRateMaps= new HashMap<String,ExchangeRateEntity>();
		exchangeRatesFinal = new ArrayList<ExchangeRateEntity>();
		for (ExchangeRateEntity exchangeRate: exchangeRates) {
			//目前只使用ratetype=11的Rate汇率
			if("11".equals(exchangeRate.getRatetype()) && !"TWD".equals(exchangeRate.getCcy())){
				exchangeRateMaps.put(exchangeRate.getRatetype()+":"+exchangeRate.getCcy(), exchangeRate);
				exchangeRatesFinal.add(exchangeRate);
			}
			// TODO TWD 特殊处理，使用ratetype=2的Rate汇率
			if("2".equals(exchangeRate.getRatetype()) && "TWD".equals(exchangeRate.getCcy())) {
				exchangeRate.setRatetype("11");	// ratetype=2临时设置为11
				exchangeRateMaps.put(exchangeRate.getRatetype()+":"+exchangeRate.getCcy(), exchangeRate);
				exchangeRatesFinal.add(exchangeRate);
			}
		}
		//将FTZ汇率数据进行筛选，并且进行对应Mapping
		exchangeRateFtzMaps= new HashMap<String,ExchangeRateFtzEntity>();
		exchangeRatesFtzFinal = new ArrayList<ExchangeRateFtzEntity>();
		for (ExchangeRateFtzEntity exchangeFtzRate: exchangeRateFtzs) {
			//目前只使用ratetype=11的Rate汇率
			if("11".equals(exchangeFtzRate.getRatetype())){
				exchangeRateFtzMaps.put(exchangeFtzRate.getRatetype()+":"+exchangeFtzRate.getCcy(), exchangeFtzRate);
				exchangeRatesFtzFinal.add(exchangeFtzRate);
			}
		}
		//根据科目编号，设置科目级别
		for(AccGLEntity entity : accgls) {
			if(entity.getNum().length()==4){
				entity.setLevel("1");
			} else if(entity.getNum().length()==6){
				entity.setLevel("2");
			} else if(entity.getNum().length()==8){
				entity.setLevel("3");
			}
		}
		//根据产品类别,设置存款信息的产品名称
		for(DepositEntity entity : deposits){
			// 根据DTYP Category Mapping 存款产品类型
			entity.setProductcode(depositAccounttype.getString(entity.getDtyp()));
		}
		//根据授信编号,设置授信额度的授信类型
		for(LimitEntity entity : limits){
			// 根据LineCode取两个.之间的内容
			entity.setFtypCode(entity.getLineCode().split("\\.")[1]);
		}
		//设置贷款的质量和贷款的风险等级
		for(LoanEntity entity : loans){
			// 年初贷款质量状态BEG_LOAN_QUL
			// 先判断贷款发放日是否是今年：若是，则默认为"正常"；若否，则从年初金标档抓取贷款质量状态
			if(DateUtils.formatDate(entity.getDealdate()).substring(0, 5).equals(DateUtils.getCurrDate8().substring(0, 5))){
				entity.setBegLoanQul("FQ01");
			} else {
				entity.setBegLoanQul("FQ01"); // 16年先全部默认为正常，17年再抓17年年初或16年年底的贷款质量
			}
			//	系统判断，贷款风险分类LOAN_RISK（配报表以此为准）
			//	人工调整，贷款质量（手工调整后，通常为空）LOANGRADE
			//  如果Loangrade不为空，说明手工调整过等级，采用系统判断出的贷款等级。
			if(null != entity.getLoangrade()) {
				entity.setLoanRisk(entity.getLoangrade());
			}
//			// 贷款投向临时逻辑
//			if(null != entity.getUseLoan() && !"99999".equals(entity.getUseLoan())){
//				entity.setUseLoan(entity.getCuin());
//			}
		}
		//设置交易流水中交易金额，
		for(TranDetailEntity entity : tranDetails){
			//设置交易流水中的交易金额，交易附言
			if("0".equals(entity.getDrcr()) && entity.getPsta().compareTo(new BigDecimal(0.0))==-1){
				entity.setPsta(entity.getPsta().abs());
			} else if ("0".equals(entity.getDrcr()) && entity.getPsta().compareTo(new BigDecimal(0.0))==1){
				entity.setPnar(entity.getPnar() + "借贷标识为：0，但是金额为正，请确认是否正确！");
			} else if ("1".equals(entity.getDrcr()) && entity.getPsta().compareTo(new BigDecimal(0.0))==-1){
				entity.setPnar(entity.getPnar() + "借贷标识为：1，但是金额为负，请确认是否正确！");
			}
			//设置交易流水中的收付款性质和首付款类型
			if("0".equals(entity.getDrcr())){
				entity.setPaytype(paytypeOut.getString(entity.getPaytype()==null?"":entity.getPaytype()));
				entity.setIncomtype(incomtypeOut.getString(entity.getIncomtype()==null?"":entity.getIncomtype()));
			} else if("1".equals(entity.getDrcr())){
				entity.setPaytype(paytypeIn.getString(entity.getPaytype()==null?"":entity.getPaytype()));
				entity.setIncomtype(incomtypeIn.getString(entity.getIncomtype()==null?"":entity.getIncomtype()));
			}
			//设置交易流水中的主账号
			if(null == entity.getMainacod() || "".equals(entity.getMainacod())) {
				entity.setMainacod(entity.getAcod());
			}
			// 如果是NRA开头的账户，设置主账号也为NRA开头，用于报送ACC
			if(entity.getBpacod().startsWith("NRA")) {
				entity.setMainacod("NRA"+entity.getMainacod());
			}
			// 如果是CNAPS交易，设置BOP相关信息
			if(null != entity.getPnar() && entity.getPnar().contains("#CNAPS#")&&entity.getCcy().equals("CNY")){
				entity.setBopamt(entity.getPsta());
				entity.setBopccy(entity.getCcy());
				entity.setOthamt(entity.getPsta());
				entity.setOthaccount(entity.getMainacod());
			}
			/**
			 * 贸融部：USD14217|CNY14217|HKD14217|EUR14217|GBP14217|JPY14217为内部账号。
			 * 存汇部：USD14211|CNY14211|HKD14211|EUR14211|GBP14211|JPY14211为内部账号。
			 * 中间账户所对应的客户都是“9”开头或为0，客户名称为‘国泰世华商业银行股份有限公司上海分行’（本行）
			 * 在日后的BOP申报页面，如果扣款账号（for IMP） and 入账账号（for EXP）为中间账户的话，同事会在 
			 * 备注.1 处写入客户编号，并以“#”开始及“#”结束 ，如“#cust id#”
			 */
			//如果需要BOP，从备注中截取客户编号
			String bopflag = entity.getInbop();
			if(null != bopflag && "".equals(bopflag) && !"1".equals(bopflag)) {
				String acodflag = entity.getAcod().substring(3, 8);
				if("14217".equals(acodflag) || "14211".equals(acodflag)) {
					String pnar = entity.getPnar();
					if(null != pnar && pnar.length()==9) {
						entity.setCsnm(StringUtil.midString(pnar, "#", "#")[0]);
					}
				}
			}
		}
	}

	/**
	 * 个人外币账户要区分境内境外来确定是否报送ACC
	 * @param acc
	 * @param cust
	 */
	private void setAccountTypeForIndv(AccCustEntity acc, CustBaseEntity cust) {
		if("1101".equals(acc.getCategory())||"6121".equals(acc.getCategory())||"6101".equals(acc.getCategory())){
			if(!"CNY".equals(acc.getCcy())){
				if("2".equals(cust.getIslocal())) {
					acc.setAccounttype("3400");	// 境外非居民报送3400
				} else {
					acc.setAccounttype("0");	// 境内居民不报送ACC
				}
			} else {
				acc.setAccounttype("0");	// 人民币账户不报送ACC
			}
		}
	}

	/**
	 * 根据客户的CS来Mapping一些值，如国民经济部门代码，客户分类（中资，外资）等。
	 * @param entity
	 */
	private void convertNesc(CustBaseEntity entity) {
		if(null != nescMaps.get(entity.getCs())) {
			entity.setNesclv1(nescMaps.get(entity.getCs()).getNescidlv1());			//	国民经济代码大类
			entity.setNesclv2(nescMaps.get(entity.getCs()).getNescidlv2());			//	国民经济代码小类
			entity.setNescft(nescMaps.get(entity.getCs()).getFtnescid());			//	国民经济代码FT自贸区
			entity.setAttribute(nescMaps.get(entity.getCs()).getAttribute());		//	客户分类（中资，外资）
		} else {
			entity.setNesclv1("无匹配项");
		}
	}
	
	/**
	 * 转换组织机构代码
	 * 1、如果encode有值，直接赋值组织机构代码
	 * 2、如果encode无值:
	 *    1) 如果三证合一后代码CITP_USCC有值，截取9到17位。
	 *    2) 如果CITP_USCC无值，有可能用户输成营业执照代码BUSLICENSE。判断18位，截取9到17位。
	 * 	  3) 如果CITP_OSCC有值（9位），且国籍非CHN，把CITP_OSCC的值赋给encode，上报用。
	 * @param entity
	 */
	private void convertEncode(CustCorpEntity entity) {
		if(null == entity.getEncode() || "".equals(entity.getEncode())) {
			if(null != entity.getCitpUscc() && !"".equals(entity.getCitpUscc()) && entity.getCitpUscc().length() == 18){
				entity.setEncode(entity.getCitpUscc().substring(8,17));
			} else if(null != entity.getBuslicense() && entity.getBuslicense().length() == 18){
				entity.setEncode(entity.getBuslicense().substring(8,17));
			} else if(null != entity.getCitpOscc() && entity.getCitpOscc().length()==9 && !"CHN".equals(entity.getCtnt())){
				entity.setEncode(entity.getCitpOscc());
			}
		}
		
		/*add by fenny 20171010 9位 组织机构代码转18位统一社会信用代码*/
//		CriteriaQuery cq = new CriteriaQuery(EncodeMapUsccEntity.class);
//		cq.eq("encode", entity.getEncode());
//		cq.le("status", entity.getSysDate());
//		cq.add();
//		EncodeMapUsccEntity tmp = getUniqueObjectByCriteriaQuery(cq);
//		if(null != tmp) {
//			entity.setEncode(tmp.getCitpUscc());
//		}
		/*end*/
	}

//	private void getBankInfos() throws Exception {
//		banks = loadAll(BankinfoEntity.class);
//		bankm = new HashMap<String, BankinfoEntity>();
//		for (BankinfoEntity bankinfo : banks) {
//			bankm.put(bankinfo.getBrca(), bankinfo);
//		}
//	}

	/**
	 * 计算等值金额
	 */
	private void calculateAmounts() {
		calculateTradefinanceAmount();		//	A4贸易融资业务
		calculateJshAmount();				//	A6结售汇业务
		calculateGuaranteeAmount();			//	A7担保交易
		calculateLoanAmount();				//	A8贷款信息表
		calculateDepositAmount();			//	A9存款信息表
		calculateAcctGlAmount();			//	A13会计信息表
		calculateLimitAmount();				//	A14授信额度表
		calculateCapitalAmount();			//	A15注册资本金表
		calculateTradefinanceSecAmount();	//	A17贸易融资二表
		calculateFixedMmAmount();			//	A20 存放同业定期存款信息(MM)
		calculateFactoringAmount();			//	A8_1 保理
//		calculateFactoringTxdtlAmount();	//	A19_4 保理
	}

	/**
	 * 计算日期相差天数，月数
	 */
	private void calculateDates() {
		calculateTradefinanceDates();		//	A4贸易融资业务
		calculateLoanDates();				//	A8贷款信息表
		calculateFactoringDates();          //  A8_1  保理业务信息表
		calculateDepositDates();			//	A9存款信息表
		calculateFixedMmDates();			//	A20 存放同业定期存款信息(MM)
	}
	
	private void calculateFixedMmDates() {
		for(FixedMmEntity fixedMm : fixedMms){
			// 计算到期天数，月数 ，MATD（到期日）-报告日
			if (null != fixedMm.getMatd()) {
				fixedMm.setMatdimpd(DateUtils.dateDiff('d', fixedMm.getMatd(), fixedMm.getImportdate()));
				fixedMm.setMatdimpm((int)DateUtils.getMonthByDay(fixedMm.getMatd(), fixedMm.getImportdate()));
			}
		}
	}
	
	/**
	 * @TODO 计算贸易融资业务的到期天数和月数
	 */
	private void calculateTradefinanceDates() {
		for(TradefinanceEntity tradefinance : tradefinances){
			// 计算到期天数，月数 ，[ADEXRYDATE]到期日-[ISDT]开证日期
			if (null != tradefinance.getAdexrydate() && null != tradefinance.getIsdt()) {
				tradefinance.setIsdtadexryd(DateUtils.dateDiff('d', tradefinance.getAdexrydate(), tradefinance.getIsdt()));
				tradefinance.setIsdtadexrym((int)DateUtils.getMonthByDay(tradefinance.getAdexrydate(), tradefinance.getIsdt()));
			}
		}
	}

	/**
	 * @TODO 计算存款信息表，到期天数和月数
	 */
	private void calculateDepositDates() {
		for(DepositEntity deposit : deposits){
			/** ****************************** */
			// 计算到期天数，月数 ，DepoAgrEddt(定存到期日期)-报告日
			if (null != deposit.getDepoAgrEddt()) {
				deposit.setMatreportday(DateUtils.dateDiff('d', deposit.getDepoAgrEddt(), deposit.getImportdate()));
				deposit.setMatreportmonth((int)DateUtils.getMonthByDay(deposit.getDepoAgrEddt(), deposit.getImportdate()));
			}
			// 计算到期天数，月数 ，到期日DEPO_AGR_EDDT-起始日DEPO_AGR_STDT
			if (null != deposit.getDepoAgrEddt() && null != deposit.getDepoAgrStdt()) {
				deposit.setStdteddtday(DateUtils.dateDiff('d', deposit.getDepoAgrEddt(), deposit.getDepoAgrStdt()));
				deposit.setStdteddtmonth((int)DateUtils.getMonthByDay(deposit.getDepoAgrEddt(), deposit.getDepoAgrStdt()));
			}
			// 计算到期天数，月数 ，getAutoRenewdt(自动转存日)-报告日
			if (null != deposit.getAutoRenewdt()) {
				deposit.setNmatreportday(DateUtils.dateDiff('d', deposit.getAutoRenewdt(), deposit.getImportdate()));
				deposit.setNmatreportmonth((int)DateUtils.getMonthByDay(deposit.getImportdate(), deposit.getAutoRenewdt()));
			}
			// 计算下次付息日天数，月数 ，NIDT（下次付息日）- 报告日
			if (null != deposit.getNidt()) {
				deposit.setNidtreportday(DateUtils.dateDiff('d', deposit.getNidt(), deposit.getImportdate()));
				deposit.setNidtreportmonth((int)DateUtils.getMonthByDay(deposit.getImportdate(), deposit.getNidt()));
			}
			// 计算生效天数
	//		if (null != deposit.get && null != deposit.getMdat()) {
	//			deposit.setVatas(DateUtils.getDateDeff(deadepositlsdc.getMdat(), deposit.getVdat()));
	//			deposit.setVatms(DateUtils.calculateMonth(deposit.getVdat(), deposit.getMdat()));
	//		}
			// 计算有效天数
	//		if (null != dealsdc.getVdat()) {
	//			dealsdc.setEatas(DateUtil.getDateDeff(guidedate, dealsdc.getVdat()));
	//			dealsdc.setEatms(DateUtil.getMonth(dealsdc.getVdat(), guidedate));
	//		}
		}
	}
	
	/**
	 * @todo 计算贷款到期日
	 */
	private void calculateLoanDates() {
		for(LoanEntity loan : loans) {
			/**************用于计算到期日天数********************/
			// 到期日
			if (null != loan.getMatdate()) {
				// 计算到期天数
				loan.setMatreportday(DateUtils.dateDiff('d', loan.getMatdate(), loan.getImportdate()));
				// 计算到期月以30天为一月
				loan.setMatreportmonth((int)DateUtils.getMonthByDay(loan.getImportdate(), loan.getMatdate()));
			}
			// 实际到期日
			if (null != loan.getActEndLoan()) {
				// 计算到期天数
				loan.setActreportday(DateUtils.dateDiff('d', loan.getActEndLoan(), loan.getImportdate()));
				// 计算到期月以30天为一月
				loan.setActreportmonth((int)DateUtils.getMonthByDay(loan.getImportdate(), loan.getActEndLoan()));
			}
			// 下次付息日NX_PAY
			if (null != loan.getNxPay()) {
				// 计算到期天数
				loan.setNxreportday(DateUtils.dateDiff('d', loan.getMatdate(), loan.getImportdate()));
				// 计算到期月以30天为一月
				loan.setNxreportmonth((int)DateUtils.getMonthByDay(loan.getImportdate(), loan.getMatdate()));
			}
			// 到期日-发放日
			if (null != loan.getDealdate() && null != loan.getMatdate()) {
				// 计算到期天数
				loan.setMatdealday(DateUtils.dateDiff('d', loan.getMatdate(), loan.getDealdate()));
				// 计算有效月以30天为一月
				loan.setMatdealmonth((int)DateUtils.getMonthByDay(loan.getDealdate(), loan.getMatdate()));
			}
		}
	}
	
	/**
	 * 
	 * @author  sunxiaobei
	 * @date 2017年11月23日 上午9:12:44
	 * @TODO add  by  sunxiaobei  增加factory中到期日月的字段
	 */
	private void calculateFactoringDates() {
		for(FactoringEntity  fa: factoring) {
			/**************用于计算到期日天数********************/
			// 到期日
			if (null != fa.getMatdate()) {
				// 计算到期天数
				fa.setMatreportday(DateUtils.dateDiff('d', DateUtils.str2Date(fa.getMatdate(),DateUtils.yyyyMMdd), fa.getImportdate()));
				// 计算到期月以30天为一月
				fa.setMatreportmonth((int)DateUtils.getMonthByDay(fa.getImportdate(), DateUtils.str2Date(fa.getMatdate(),DateUtils.yyyyMMdd)));
			}
			// 下次付息日NX_PAY
			if (null != fa.getNxPay()) {
				// 计算到期天数
				fa.setNxreportday(DateUtils.dateDiff('d', DateUtils.str2Date(fa.getMatdate(),DateUtils.yyyyMMdd), fa.getImportdate()));
				// 计算到期月以30天为一月
				fa.setNxreportmonth((int)DateUtils.getMonthByDay(fa.getImportdate(), DateUtils.str2Date(fa.getMatdate(),DateUtils.yyyyMMdd)));
			}
		}
	} 

//	private void calculateTranDetailAmount() {
//		ExchangeRateEntity exchrate = null;
//		ExchangeRateEntity exchrateUsd = exchangeRateMaps.get("11:USD");
//		BigDecimal ou;
//		BigDecimal oc;
//		for (TranDetailEntity entity : tranDetails) {
//			exchrate = exchangeRateMaps.get("11:"+entity.getCcy());
//			BigDecimal o = entity.getPsta();
//			oc = getCnyValue(exchrate, o);
//			ou = getUsdValue(exchrateUsd, oc);
//			entity.setPstausd(ou);
//			entity.setPstacny(oc);
//		}
//	}
	
	private void calculateCapitalAmount() {
		ExchangeRateEntity exchrate = null;
		ExchangeRateEntity exchrateUsd = exchangeRateMaps.get("11:USD");
		BigDecimal ou;
		BigDecimal oc;
		for (CapitalEntity entity : capitals) {
			exchrate = exchangeRateMaps.get("11:"+entity.getCcy());
			BigDecimal o = entity.getBalance();
			oc = getCnyValue(exchrate, o);
			ou = getUsdValue(exchrateUsd, oc);
			entity.setBalanceusd(ou);
			entity.setBalancecny(oc);
		}
	}

	private void calculateAcctGlAmount() {
		ExchangeRateEntity exchrate = null;
		ExchangeRateEntity exchrateUsd = exchangeRateMaps.get("11:USD");
		BigDecimal ou;
		BigDecimal oc;
		for (AccGLEntity entity : accgls) {
			exchrate = exchangeRateMaps.get("11:"+entity.getCcy());
			BigDecimal o = entity.getBalance();
			oc = getCnyValue(exchrate, o);
			ou = getUsdValue(exchrateUsd, oc);
			entity.setBalanceusd(ou);
			entity.setBalancecny(oc);
		}
	}
	
	private void calculateLimitAmount() {
		ExchangeRateEntity exchrate = null;
		ExchangeRateEntity exchrateUsd = exchangeRateMaps.get("11:USD");
		BigDecimal ou;
		BigDecimal oc;
		for (LimitEntity entity : limits) {
			exchrate = exchangeRateMaps.get("11:"+entity.getFccy());
			BigDecimal o = entity.getFamt();
			// 授信金额
			oc = getCnyValue(exchrate, o);
			ou = getUsdValue(exchrateUsd, oc);
			entity.setFamtusd(ou);
			entity.setFamtcny(oc);
			// 授信余额
			o = entity.getFuam();
			oc = getCnyValue(exchrate, o);
			ou = getUsdValue(exchrateUsd, oc);
			entity.setFuamusd(ou);
			entity.setFuamcny(oc);
		}
	}

	private void calculateDepositAmount() {
		ExchangeRateEntity exchrate = null;
		ExchangeRateEntity exchrateUsd = exchangeRateMaps.get("11:USD");
		BigDecimal ou;
		BigDecimal oc;
		for (DepositEntity entity : deposits) {
			exchrate = exchangeRateMaps.get("11:"+entity.getCcy());
			// 存款余额
			BigDecimal o = entity.getWorkingbalance();
			oc = getCnyValue(exchrate, o);
			ou = getUsdValue(exchrateUsd, oc);
			entity.setWorkingbalanceusd(ou);
			entity.setWorkingbalancecny(oc);
			// 存款本金
			o = entity.getPcpl();
			oc = getCnyValue(exchrate, o);
			ou = getUsdValue(exchrateUsd, oc);
			entity.setPcplusd(ou);
			entity.setPcplcny(oc);
			// 应付利息
			o = entity.getAipd();
			oc = getCnyValue(exchrate, o);
			ou = getUsdValue(exchrateUsd, oc);
			entity.setAipdusd(ou);
			entity.setAipdcny(oc);
		}
	}
	
	private void calculateFixedMmAmount() {
		ExchangeRateEntity exchrate = null;
		ExchangeRateEntity exchrateUsd = exchangeRateMaps.get("11:USD");
		BigDecimal ou;
		BigDecimal oc;
		for (FixedMmEntity entity : fixedMms) {
			exchrate = exchangeRateMaps.get("11:"+entity.getCcy());
			// 本金
			BigDecimal o = entity.getPcpl();
			oc = getCnyValue(exchrate, o);
			ou = getUsdValue(exchrateUsd, oc);
			entity.setPcplusd(ou);
			entity.setPcplcny(oc);
			// 应计利息
			o = entity.getTotintamt();
			oc = getCnyValue(exchrate, o);
			ou = getUsdValue(exchrateUsd, oc);
			entity.setTotintamtusd(ou);
			entity.setTotintamtcny(oc);
		}
	}
	
	private void calculateFactoringAmount() {
		ExchangeRateEntity exchrate = null;
		ExchangeRateEntity exchrateUsd = exchangeRateMaps.get("11:USD");
		BigDecimal ou;
		BigDecimal oc;
		for (FactoringEntity entity : factoring) {
			/*发票币种*/
			exchrate = exchangeRateMaps.get("11:"+entity.getBillCcy());
			BigDecimal o;
			o = entity.getBillAm();
			oc = getCnyValue(exchrate, o);
			ou = getUsdValue(exchrateUsd, oc);
			entity.setBillAmUsd(ou);
			entity.setBillAmCny(oc);
				
			o = entity.getBillBl();
			oc = getCnyValue(exchrate, o);
			ou = getUsdValue(exchrateUsd, oc);
			entity.setBillBlUsd(ou);
			entity.setBillBlCny(oc);

			/*承保币种*/
//			exchrate = exchangeRateMaps.get("11:"+entity.getCcyCov());
//			o = entity.getAmtCov();
//			oc = getCnyValue(exchrate, o);
//			ou = getUsdValue(exchrateUsd, oc);
//			entity.setAmtCovUsd(ou);
//			entity.setAmtCovCny(oc);

			/*融资贷款币种*/
			exchrate = exchangeRateMaps.get("11:"+entity.getNotesellCcy());
			o = entity.getNotesellAm();
			oc = getCnyValue(exchrate, o);
			ou = getUsdValue(exchrateUsd, oc);
			entity.setNotesellAmUsd(ou);
			entity.setNotesellAmCny(oc);

			o = entity.getNotesellBl();
			oc = getCnyValue(exchrate, o);
			ou = getUsdValue(exchrateUsd, oc);
			entity.setNotesellBlUsd(ou);
			entity.setNotesellBlCny(oc);
			
			/*cur_ins_recv/ove_defp/general_pro/special_pro/specific_pro*/
			o = entity.getCurInsRecv();
			oc = getCnyValue(exchrate, o);
			ou = getUsdValue(exchrateUsd, oc);
			entity.setCurInsRecvUsd(ou);
			entity.setCurInsRecvCny(oc);
			
			o = entity.getOveDefp();
			oc = getCnyValue(exchrate, o);
			ou = getUsdValue(exchrateUsd, oc);
			entity.setOveDefpUsd(ou);
			entity.setOveDefpCny(oc);
			
			o = entity.getGeneralPro();
			oc = getCnyValue(exchrate, o);
			ou = getUsdValue(exchrateUsd, oc);
			entity.setGeneralProUsd(ou);
			entity.setGeneralProCny(oc);
			
			o = entity.getSpecialPro();
			oc = getCnyValue(exchrate, o);
			ou = getUsdValue(exchrateUsd, oc);
			entity.setSpecialProUsd(ou);
			entity.setSpecialProCny(oc);
			
			o = entity.getSpecificPro();
			oc = getCnyValue(exchrate, o);
			ou = getUsdValue(exchrateUsd, oc);
			entity.setSpecificProUsd(ou);
			entity.setSpecificProCny(oc);
		}
	}
	
//	private void calculateFactoringTxdtlAmount() {
//		ExchangeRateEntity exchrate = null;
//		ExchangeRateEntity exchrateUsd = exchangeRateMaps.get("11:USD");
//		BigDecimal ou;
//		BigDecimal oc;
//		for (FactoringTxdtlEntity entity : factoringTxdtl) {
//			/*交易币种*/
//			exchrate = exchangeRateMaps.get("11:"+entity.getCcy());
//
//			BigDecimal o = new BigDecimal(entity.getPsta());
//			oc = getCnyValue(exchrate, o);
//			ou = getUsdValue(exchrateUsd, oc);
//			entity.setPstausd(ou.toString());
//			entity.setPstacny(oc.toString());
//		}
//	}
	
	private void calculateTradefinanceAmount() {
		ExchangeRateEntity exchrate = null;
		ExchangeRateEntity exchrateUsd = exchangeRateMaps.get("11:USD");
		BigDecimal ou;
		BigDecimal oc;
		for (TradefinanceEntity entity : tradefinances) {
			// 信用证金额
			exchrate = exchangeRateMaps.get("11:"+entity.getLccr());
			BigDecimal o = entity.getLcam();
			oc = getCnyValue(exchrate, o);
			ou = getUsdValue(exchrateUsd, oc);
			entity.setLcamusd(ou);
			entity.setLcamcny(oc);
			// 信用证余额
			o = entity.getLcbl();
			oc = getCnyValue(exchrate, o);
			ou = getUsdValue(exchrateUsd, oc);
			entity.setLcblusd(ou);
			entity.setLcblcny(oc);
			//	授信金额
			if(null != entity.getFccy()) {
				exchrate = exchangeRateMaps.get("11:"+entity.getFccy());
				o = entity.getFcam();
				oc = getCnyValue(exchrate, o);
				ou = getUsdValue(exchrateUsd, oc);
				entity.setFcamusd(ou);
				entity.setFcamcny(oc);
			}
		}
	}
	
	private void calculateTradefinanceSecAmount() {
		ExchangeRateEntity exchrate = null;
		ExchangeRateEntity exchrateUsd = exchangeRateMaps.get("11:USD");
		BigDecimal ou;
		BigDecimal oc;
		for (TradefinanceSecEntity entity : tradefinanceSecs) {
			// 金额
			exchrate = exchangeRateMaps.get("11:"+entity.getLccr());
			BigDecimal o = entity.getLcam();
			oc = getCnyValue(exchrate, o);
			ou = getUsdValue(exchrateUsd, oc);
			entity.setLcamusd(ou);
			entity.setLcamcny(oc);
			//初始金额
			BigDecimal p = entity.getOrigLcam();
			oc = getCnyValue(exchrate, p);
			ou = getUsdValue(exchrateUsd, oc);
			entity.setOrigLcamusd(ou);
			entity.setOrigLcamcny(oc);						
		}
	}

	private void calculateLoanAmount() {
		ExchangeRateEntity exchrate = null;
		ExchangeRateEntity exchrateUsd = exchangeRateMaps.get("11:USD");
		BigDecimal ou;
		BigDecimal oc;
		for (LoanEntity entity : loans) {
			exchrate = exchangeRateMaps.get("11:"+entity.getCcy());
			// 借据余额
			BigDecimal o = entity.getNotesellBl();
			oc = getCnyValue(exchrate, o);
			ou = getUsdValue(exchrateUsd, oc);
			entity.setNotesellBlusd(ou);
			entity.setNotesellBlcny(oc);
			// 借据金额
			o = entity.getNotesellAm();
			oc = getCnyValue(exchrate, o);
			ou = getUsdValue(exchrateUsd, oc);
			entity.setNotesellAmusd(ou);
			entity.setNotesellAmcny(oc);
			// 应收利息
			o = entity.getCurInsRecv();
			oc = getCnyValue(exchrate, o);
			ou = getUsdValue(exchrateUsd, oc);
			entity.setCurInsRecvusd(ou);
			entity.setCurInsRecvcny(oc);
			// 贷款减值准备_一般准备金额
			o = entity.getGeneralPro();
			oc = getCnyValue(exchrate, o);
			ou = getUsdValue(exchrateUsd, oc);
			entity.setGeneralProusd(ou);
			entity.setGeneralProcny(oc);
			// 贷款减值准备_专项准备金额
			o = entity.getSpecialPro();
			oc = getCnyValue(exchrate, o);
			ou = getUsdValue(exchrateUsd, oc);
			entity.setSpecialProusd(ou);
			entity.setSpecialProcny(oc);
			// 贷款减值准备_特种准备金额
			o = entity.getSpecificPro();
			oc = getCnyValue(exchrate, o);
			ou = getUsdValue(exchrateUsd, oc);
			entity.setSpecificProusd(ou);
			entity.setSpecificProcny(oc);
		}
	}
	
	private void calculateJshAmount() {
		ExchangeRateEntity exchrate = null;
		ExchangeRateEntity exchrateUsd = exchangeRateMaps.get("11:USD");
		BigDecimal ou;
		BigDecimal oc;
		for (JshEntity entity : jshs) {
			// 买入金额
			exchrate = exchangeRateMaps.get("11:"+entity.getPucy());
			BigDecimal o = entity.getPuam();
			oc = getCnyValue(exchrate, o);
			ou = getUsdValue(exchrateUsd, oc);
			entity.setPuamusd(ou);
			entity.setPuamcny(oc);
			// 卖出金额
			exchrate = exchangeRateMaps.get("11:"+entity.getSlcy());
			o = entity.getSlam();
			oc = getCnyValue(exchrate, o);
			ou = getUsdValue(exchrateUsd, oc);
			entity.setSlamusd(ou);
			entity.setSlamcny(oc);
		}
	}
	
	private void calculateGuaranteeAmount() {
		ExchangeRateEntity exchrate = null;
		ExchangeRateEntity exchrateUsd = exchangeRateMaps.get("11:USD");
		BigDecimal ou;
		BigDecimal oc;
		for (GuaranteeEntity entity : guarantees) {
			// 金额
			exchrate = exchangeRateMaps.get("11:"+entity.getCcy());
			BigDecimal o = entity.getEstVal();
			oc = getCnyValue(exchrate, o);
			ou = getUsdValue(exchrateUsd, oc);
			entity.setEstValusd(ou);
			entity.setEstValcny(oc);
		}
	}

	/**
	 * 1、外币折成人民币、外币*汇率=人民币
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
	 * @param exchRateUsd
	 * @param oc
	 * @return
	 */
	protected BigDecimal getUsdValue(ExchangeRateEntity exchRateUsd, BigDecimal oc) {
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
	 * @TODO 将全局变量置空
	 */
	private void setNull() {

		custIndvs = null;			//	A1	个人客户信息表
		custCorps = null;			//	A2	企业客户信息表
		custBanks = null;			//	A3	同业客户信息表
		tradefinances = null;		//	A4	贸易融资业务
		bills = null;				//	A5	票据业务
		jshs = null;				//	A6	结售汇业务
		guarantees = null;			//	A7	担保交易
		loans = null;				//	A8	贷款信息表
		deposits = null;			//	A9	存款信息表
		tranDetails = null;			//	A10	交易流水表
		acccusts = null;			//	A11	账户信息表
		accbanks = null;			//	A11	账户信息表(同业)
		accgls = null;				//	A13	会计信息表
		limits = null;				//	A14	授信额度表
		capitals = null;			//	A15	注册资本金表
		exchangeRates = null;		//	A161	汇率表
		exchangeRatesFinal = null;	
		exchangeRateFtzs = null;	//	A162	自贸离岸汇率表
		exchangeRatesFtzFinal = null;	
		tradefinanceSecs = null;	// 	A17	贸易融资二表
		custRelations = null;		// 	A18	客户关系表
		parnRelations = null;		// 	A18_2集团关系表
		fixedMms = null;			// 	A20   存放同业定期存款信息(MM)
		exchangeRateMaps = null;
		factoring =null;
//		factoringTxdtl =null;
	}

	private Configuration getAccounttypeNotCny() throws Exception {
		return new PropertiesConfiguration(
				Constants.ACCOUNTTYPE_NOT_CNY_PATH_MAPPING);
	}

	private Configuration getAccounttypeCny() throws Exception {
		return new PropertiesConfiguration(
				Constants.ACCOUNTTYPE_CNY_PATH_MAPPING);
	}
	
	private Configuration getIncomtypeIn() throws Exception {
		return new PropertiesConfiguration(
				Constants.INCOMTYPE_IN_PATH_MAPPING);
	}
	
	private Configuration getIncomtypeOut() throws Exception {
		return new PropertiesConfiguration(
				Constants.INCOMTYPE_OUT_PATH_MAPPING);
	}
	
	private Configuration getPaytypeIn() throws Exception {
		return new PropertiesConfiguration(
				Constants.PAYTYPE_IN_PATH_MAPPING);
	}
	
	private Configuration getPaytypeOut() throws Exception {
		return new PropertiesConfiguration(
				Constants.PAYTYPE_OUT_PATH_MAPPING);
	}
	private Configuration getDepositAccounttype() throws Exception {
		return new PropertiesConfiguration(
				Constants.DEPOSIT_ACCOUNTTYPE_PATH_MAPPING);
	}

	/**
	 * 将所有处理完成的数据写入数据库
	 * @throws Exception 
	 */
	private void insertAlldata2DB(Date trandate) throws Exception {
		//A1	个人客户信息表
		if(null != custIndvs && custIndvs.size()!=0) {
			batchSave(custIndvs);
		}
		//A2	企业客户信息表
		if(null != custCorps && custCorps.size()!=0) {
			batchSave(custCorps);
		}
		//A3	同业客户信息表
		if(null != custBanks && custBanks.size()!=0) {
			batchSave(custBanks);
		}
		//A4	贸易融资业务
		if(null != tradefinances && tradefinances.size()!=0) {
			batchSave(tradefinances);
		}
		//A5	票据业务
		if(null != bills && bills.size()!=0) {
			batchSave(bills);
		}
		//A6	结售汇业务
		if(null != jshs && jshs.size()!=0) {
			batchSave(jshs);
		}
		//A7	担保交易
		if(null != guarantees && guarantees.size()!=0) {
			batchSave(guarantees);
		}
		//A8	贷款信息表
		if(null != loans && loans.size()!=0) {
			batchSave(loans);
		}
		//A9	存款信息表
		if(null != deposits && deposits.size()!=0) {
			batchSave(deposits);
		}
		//A10	交易流水表
		if(null != tranDetails && tranDetails.size()!=0) {
			batchSave(tranDetails);
		}
		//A11	账户信息表(普通)
		if(null != acccusts && acccusts.size()!=0) {
			batchSave(acccusts);
		}
		//A11	账户信息表(同业)
		if(null != accbanks && accbanks.size()!=0) {
			batchSave(accbanks);
		}
		//A13	会计信息表
		if(null != accgls && accgls.size()!=0) {
			batchSave(accgls);
		}
		//A14	授信额度表
		if(null != limits && limits.size()!=0) {
			batchSave(limits);
		}
		//A15	注册资本金表
		if(null != capitals && capitals.size()!=0) {
			batchSave(capitals);
		}
		//A161	汇率表
		if(null != exchangeRatesFinal && exchangeRatesFinal.size()!=0) {
			batchSave(exchangeRatesFinal);
		}
		//A162	自贸区离岸汇率表
		if(null != exchangeRatesFtzFinal && exchangeRatesFtzFinal.size()!=0) {
			batchSave(exchangeRatesFtzFinal);
		}
		//A17	贸易融资二表
		if(null != tradefinanceSecs && tradefinanceSecs.size()!=0) {
			batchSave(tradefinanceSecs);
		}
		//A18	客户关系表
		if(null != custRelations && custRelations.size()!=0) {
			batchSave(custRelations);
		}
		//A18_2	集团关系表
		if(null != parnRelations && parnRelations.size()!=0) {
			batchSave(parnRelations);
		}
		//A20	存放同业定期存款信息(MM)
		if(null != fixedMms && fixedMms.size()!=0) {
			batchSave(fixedMms);
		}
		
		//A8_1 保理
		if(null != factoring && factoring.size()!=0) {
			batchSave(factoring);
		}
		
		//A19_4 保理流水
//		if(null != factoringTxdtl && factoringTxdtl.size()!=0) {
//			batchSave(factoringTxdtl);
//		}
	}

	/**
	 * 从原文件中处理数据
	 * 
	 * @param trandate
	 * @throws Exception
	 */
	private void getDataFromSourcefiles(Date trandate) throws Exception {
		//workingdays = 		parserimpl.parser(resourceMappingMap.get("WORKINGDAY"), WorkingDayEntity.class, trandate);				//	A0时间
		custIndvs =   		parserimpl.parser(resourceMappingMap.get("CUSTINDV"), CustIndvEntity.class, trandate);					//	A1个人客户信息表
		custCorps = 		parserimpl.parser(resourceMappingMap.get("CUSTCORP"), CustCorpEntity.class, trandate);					//	A2对公客户信息表
		custBanks =  		parserimpl.parser(resourceMappingMap.get("CUSTBANK"), CustBankEntity.class, trandate);					//	A3同业客户信息表
		tradefinances = 	parserimpl.parser(resourceMappingMap.get("TRADEFINANCE"), TradefinanceEntity.class, trandate);			//	A4贸易融资业务
		bills = 			parserimpl.parser(resourceMappingMap.get("BILL"), BillEntity.class, trandate);							//	A5票据业务
		jshs = 				parserimpl.parser(resourceMappingMap.get("JSH"), JshEntity.class, trandate);							//	A6结售汇业务
		guarantees = 		parserimpl.parser(resourceMappingMap.get("GUARANTEE"), GuaranteeEntity.class, trandate);				//	A7担保交易
		loans = 			parserimpl.parser(resourceMappingMap.get("LOAN"), LoanEntity.class, trandate);							//	A8贷款信息表
		deposits = 			parserimpl.parser(resourceMappingMap.get("DEPOSIT"), DepositEntity.class, trandate);					//	A9存款信息表
		tranDetails =  		parserimpl.parser(resourceMappingMap.get("TRANDETAIL"), TranDetailEntity.class, trandate);				//	A10交易流水表
		acccusts =   		parserimpl.parser(resourceMappingMap.get("ACCCUST"), AccCustEntity.class, trandate);					//	A11账户信息表(一般客户)
		accbanks = 			new ArrayList<AccBankEntity>();																			//	A11账户信息表(同业客户)
		accgls =  			parserimpl.parser(resourceMappingMap.get("ACCGL"), AccGLEntity.class, trandate);						//	A13会计信息表
		limits = 			parserimpl.parser(resourceMappingMap.get("LIMIT"), LimitEntity.class, trandate);						//	A14授信额度表
		capitals = 			parserimpl.parser(resourceMappingMap.get("CAPITAL"), CapitalEntity.class, trandate);					//	A15注册资本金表
		exchangeRates = 	parserimpl.parser(resourceMappingMap.get("EXCHANGERATE"),ExchangeRateEntity.class ,trandate);			//	A161汇率表
		exchangeRateFtzs = 	parserimpl.parser(resourceMappingMap.get("EXCHANGERATEFTZ"),ExchangeRateFtzEntity.class ,trandate);		//	A162自贸区离岸汇率表
		tradefinanceSecs = 	parserimpl.parser(resourceMappingMap.get("TRADEFINANCESEC"), TradefinanceSecEntity.class, trandate);	// 	A17贸易融资二表
		custRelations = 	parserimpl.parser(resourceMappingMap.get("CUSTRELATION"), CustRelationEntity.class, trandate);			//	A181客户关系表
		parnRelations = 	parserimpl.parser(resourceMappingMap.get("PARNRELATION"), ParnRelationEntity.class, trandate);			//	A182集团关系表
		fixedMms =		 	parserimpl.parser(resourceMappingMap.get("FIXEDMM"), FixedMmEntity.class ,trandate);	
		factoring =		 	parserimpl.parser(resourceMappingMap.get("FACTORING"), FactoringEntity.class ,trandate);                //	A8_1 保理业务
		//test		factoringTxdtl =	parserimpl.parser(resourceMappingMap.get("FACTORINGTXDTL"), FactoringTxdtlEntity.class ,trandate);      //	A19_4 保理业务流水
		System.out.println("A8_1 条数" + factoring.size() + "A1:" + custIndvs.size());
	}
	
	/**
	 * 初始化任务计划记录，增加计划任务
	 * @param trandate
	 */
	private void setTaskschedule(Date trandate) {
		TaskscheduleEntity ts1 = new TaskscheduleEntity();
		ts1.setParentbrca(Constants.HQBANKCODE);
		ts1.setBrca(Constants.HQBANKCODE);
		ts1.setExecutable(true);
		ts1.setImportdate(trandate);
		ts1.setTaskname("autoImportData");
		ts1.setTaskdesc("自动导入数据");
		saveOrUpdate(ts1);
		TaskscheduleEntity ts2 = new TaskscheduleEntity();
		ts2.setParentbrca(Constants.HQBANKCODE);
		ts2.setBrca(Constants.HQBANKCODE);
		ts2.setExecutable(true);
		ts2.setImportdate(trandate);
		ts2.setTaskname("autoExtractAcc");
		ts2.setTaskdesc("自动提取ACC");
		saveOrUpdate(ts2);
		TaskscheduleEntity ts3 = new TaskscheduleEntity();
		ts3.setParentbrca(Constants.HQBANKCODE);
		ts3.setBrca(Constants.HQBANKCODE);
		ts3.setExecutable(true);
		ts3.setImportdate(trandate);
		ts3.setTaskname("autoExtractBop");
		ts3.setTaskdesc("自动提取BOP");
		saveOrUpdate(ts3);
		TaskscheduleEntity ts4 = new TaskscheduleEntity();
		ts4.setParentbrca(Constants.HQBANKCODE);
		ts4.setBrca(Constants.HQBANKCODE);
		ts4.setExecutable(true);
		ts4.setImportdate(trandate);
		ts4.setTaskname("autoExtractJsh");
		ts4.setTaskdesc("自动提取JSH");
		saveOrUpdate(ts4);
		TaskscheduleEntity ts5 = new TaskscheduleEntity();
		ts5.setParentbrca(Constants.HQBANKCODE);
		ts5.setBrca(Constants.HQBANKCODE);
		ts5.setExecutable(true);
		ts5.setImportdate(trandate);
		ts5.setTaskname("autoImportSmtData");
		ts5.setTaskdesc("自动导入Summit数据");
		saveOrUpdate(ts5);
	}

	/**
	 * 强制导入时，删除导入日期的信息
	 * 
	 * @param trandate
	 */
	private void feedback(Date trandate) {
		deleteAllEntitie(findHql(CustIndvEntity.class, 			" importdate=?", new Object[] { trandate }));			//	A1个人客户信息表
		deleteAllEntitie(findHql(CustCorpEntity.class, 			" importdate=?", new Object[] { trandate }));			//	A2对公客户信息表
		deleteAllEntitie(findHql(CustBankEntity.class, 			" importdate=?", new Object[] { trandate }));			//	A3同业客户信息表
		deleteAllEntitie(findHql(TradefinanceEntity.class, 		" importdate=?", new Object[] { trandate }));			//	A4贸易融资业务
		deleteAllEntitie(findHql(BillEntity.class, 				" importdate=?", new Object[] { trandate }));			//	A5票据业务
		deleteAllEntitie(findHql(JshEntity.class, 				" importdate=?", new Object[] { trandate }));			//	A6结售汇业务
		deleteAllEntitie(findHql(GuaranteeEntity.class, 		" importdate=?", new Object[] { trandate }));			//	A7担保交易
		deleteAllEntitie(findHql(LoanEntity.class, 				" importdate=?", new Object[] { trandate }));			//	A8贷款信息表
		deleteAllEntitie(findHql(DepositEntity.class, 			" importdate=?", new Object[] { trandate }));			//	A9存款信息表
		deleteAllEntitie(findHql(TranDetailEntity.class, 		" importdate=?", new Object[] { trandate }));			//	A10交易流水表
		deleteAllEntitie(findHql(AccCustEntity.class, 			" importdate=?", new Object[] { trandate }));			//	A11账户信息表(一般客户)
		deleteAllEntitie(findHql(AccBankEntity.class, 			" importdate=?", new Object[] { trandate }));			//	A11账户信息表(同业客户)
		deleteAllEntitie(findHql(AccGLEntity.class, 			" importdate=?", new Object[] { trandate }));			//	A13会计信息表
		deleteAllEntitie(findHql(LimitEntity.class, 			" importdate=?", new Object[] { trandate }));			//	A14授信额度表
		deleteAllEntitie(findHql(CapitalEntity.class, 			" importdate=?", new Object[] { trandate }));			//	A15注册资本金表
		deleteAllEntitie(findHql(ExchangeRateEntity.class, 		" importdate=?", new Object[] { trandate }));			//	A16汇率表
		deleteAllEntitie(findHql(ExchangeRateFtzEntity.class, 	" importdate=?", new Object[] { trandate }));			//	A16_2自贸区离岸汇率表
		deleteAllEntitie(findHql(TradefinanceSecEntity.class, 	" importdate=?", new Object[] { trandate }));			// 	A17贸易融资二表
		deleteAllEntitie(findHql(CustRelationEntity.class, 		" importdate=?", new Object[] { trandate }));			//	A18客户关系表
		deleteAllEntitie(findHql(ParnRelationEntity.class, 		" importdate=?", new Object[] { trandate }));			//	A18_2集团关系表
		deleteAllEntitie(findHql(FixedMmEntity.class,			" importdate=?", new Object[] { trandate }));			//	A20存放同业定期存款信息(MM)
		deleteAllEntitie(findHql(FactoringEntity.class,			" importdate=?", new Object[] { trandate }));           //A8_1 保理
//		deleteAllEntitie(findHql(FactoringTxdtlEntity.class,	" importdate=?", new Object[] { trandate }));           //A19_4保理业务流水
		deleteAllEntitie(findHql(TaskscheduleEntity.class,		" importdate=?", new Object[] { trandate }));			//	计划任务
		
	}

	/**
	 * 导数前先备份源文件
	 * 
	 * @param sourcePath
	 *            源文件存在路径
	 * @param targetPath
	 *            源文件备份路径
	 * @param history2
	 * @return
	 * @throws IOException
	 */
	private static boolean copySourceFils(String trandate, String sourcePath,
			String targetPath) throws IOException {

		File sourcePathDir = new File(sourcePath);
		File[] sourceFileList = sourcePathDir.listFiles();
		if (null == sourceFileList) {
			return false;// 如果源文件目录下没有文件。返回空
		}
		for (File file : sourceFileList) {
			FileUtils.copyFileToDirectory(file, new File(targetPath + trandate + "\\"));
		}
		return true;
	}

	private static PropertiesConfiguration pc = null;
	private static String sourcePath = null;
	private static String filePath = null;
	private static String logPath = null;
	private static String history = null;

	/**
	 * @throws Exception
	 * @TODO 开始导入T24数据
	 */
	public void importData() throws Exception {
		try {
			//获取配置好的文件路径
			pc = new PropertiesConfiguration(Constants.DATA_FILES_PATH);
			sourcePath = pc.getString("sourcepath");
			filePath = pc.getString("filepath");
			logPath = pc.getString("logpath");
			history = pc.getString("history");

			System.out.println("数据导入开始时间：" + DateUtils.getCurrDate24() + "。");
			LogUtil.generateLog(logPath, "数据导入开始时间：" + DateUtils.getCurrDate24() + "。");
			/** *******************发布时的代码 开始************************* */
			SmbFile pbcdir = new SmbFile(sourcePath.toString());
			SmbFile[] dataFiles = pbcdir.listFiles();
			
			System.out.println(dataFiles.length);
			File succussFile = new File(filePath+"succuss");
//			if (null != dataFiles && dataFiles.length == 48) {
//			if (null != dataFiles && dataFiles.length == 52) {  
				if (null != dataFiles && dataFiles.length == 54) {  //by SXB  增加A10_檔案
		
				//通过success文件，判断是否已经操作成功
				if(!succussFile.exists()){
					//循环下载文件到本地
					for(SmbFile dataFile : dataFiles){
						copyRemoteFile(dataFile, filePath);
						LogUtil.generateLog(logPath, "数据文件:" + dataFile.getName()+"，下载成功！");
					}
					File[] sourcefiles = new File(filePath).listFiles();
					System.out.println("filePath:"+filePath);
					System.out.println("sourcefiles:"+sourcefiles.length);
					//读取业务日期
					String datestr = sourcefiles[1].getAbsolutePath().substring(sourcefiles[1].getAbsolutePath().indexOf('.')+1, sourcefiles[1].getAbsolutePath().lastIndexOf('.'));
					//将所有TXT文件名中日期去除
					for(File sourcefile : sourcefiles){
						if(sourcefile.getName().contains(".txt")){
							File oldfile=sourcefile; 
							File newfile=new File(oldfile.getAbsolutePath().substring(0, oldfile.getAbsolutePath().indexOf('.')).concat(".txt")); 
					        if(!oldfile.exists()){
					        	return;//重命名文件不存在
					        }
					        oldfile.renameTo(newfile);
						}
					}
					//解析A0文件为workday
					workingdays = parserimpl.parser(sourcefiles[0], WorkingDayEntity.class);
					String datestrInA0 = workingdays.get(0).getToday();
					//对比是否与营业日相同
					if(!datestr.equals(datestrInA0)) {
						LogUtil.generateLog(logPath, "营业日不匹配，请检查源文件");
					}
					//获取营业日
					LogUtil.generateLog(logPath, "文件夹路径为:" + filePath);
					Date tranDate = DateUtils.strToDate(datestr, "yyyyMMdd");
					LogUtil.generateLog(logPath, "营业日为:" + tranDate);
					//将数据导入
					int result = autoImportDataToDB(filePath, tranDate);
					//拷贝源文件到History文件夹
					copySourceFils(datestr, filePath, history);
					//清空文件目录 
					FileUtils.cleanDirectory(new File(filePath));
					//创建成功标识文件
					FileUtils.touch(new File(filePath+"succuss"));
					LogUtil.generateLog(logPath, "数据导入完成时间：" + DateUtils.getCurrDate24() + "。");
				} else {
					LogUtil.generateLog(logPath, "存在success文件，导入跳过" + DateUtils.getCurrDate24() + "。");
				}
			} else {
				LogUtil.generateLog(logPath, "gl文件不存在或者文件数量不正确" + DateUtils.getCurrDate24() + "。");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.generateLog(logPath, "导入失败，错误原因:"+ e.getMessage() + DateUtils.getCurrDate24() + "。");
			FileUtils.cleanDirectory(new File(filePath));
			LogUtil.handle(logPath, e);
			LogUtil.generateLog(logPath, e.fillInStackTrace().toString());
		}
	}

	/**
	 * 设置账户相关信息 
	 * [1.账户状态 通过开，关户日期，客户名称，代码（ 开=11 ,变=12, 关=13）]
	 * (虚拟编号14，说明当日既有开也有关，在提取数据时要对些进行处理，拆成两笔记录上报）
	 * [2.开户主体类型
	 * lins=1400/1401/1402/1403/1430/1431/1432/1433/1520/1530/1540放11-對公,
	 * 若為lins=1460/1461/1490 放12-對私居民 若為lins=1600/1630/1690/1691/1720放13-對私非居民 ]
	 * [3.账户限额类型
	 * IFX的account type=303外债专户,则放 12-余额限额,
	 * accounttype=306外商投资企业外汇资本金账户,则放 13-贷方流入限额, 其餘放11-無限額 ）]
	 * [4.账户类别
	 * 现钞户=11,现汇户=12(都先放 12-現匯戶)]
	 * [5.账户状态(DS01正常/DS02休眠/DS03合同限制/DS04司法限制/DS05撤销)
	 * 目前，DS03和DS04有可能重复，有DS04的取DS04，没有的，取DS03，其他情况，记录日志]
	 * @throws Exception
	 */
	private void setAccountInfo(AccCustEntity acct) throws Exception {
		if (!Constants.DEFAULT_CNUM.equals(acct.getCsnm())) {
			if (null != acct.getAccounttype()) {
				CustBaseEntity cust = customerMap.get(acct.getCsnm());
				System.out.println("acc.getcsnm:" + acct.getCsnm());
				System.out.println("cust:" + cust.getCsnm());
				System.out.println("setAccountInfo-csnm" + cust.getCsnm() + "[" + cust.getCtnm() + "]");
				// 设置开关（变更在提取时判断处理）
				setAccountOpenOrClosed(acct);
				// 客户信息变动时，设置为变更
				setCustomerAmtype(acct, cust);
				// Smith add 个人非居民外币账户设置Accounttype
				setAccountTypeForIndv(acct, cust);
				acct.setAccountcata("12");// 账户类别(默认)
			}
			if(null == acct.getMainacod() || "".equals(acct.getMainacod())) {
				acct.setMainacod(acct.getAcod());
			}
			// 如果是NRA开头的账户，设置主账号也为NRA开头，用于报送ACC
			if(acct.getBpacod().startsWith("NRA")) {
				acct.setMainacod("NRA"+acct.getMainacod());
			}
			if(null != acct.getStatus() && acct.getStatus().length()>4){
				String[] status = acct.getStatus().split(";");
				acct.setStatus(status[0]);
				for(String sta :status){
					if("DS04".equals(sta)) {
						acct.setStatus(sta);
					} else if("DS01".equals(sta)||"DS02".equals(sta)||"DS05".equals(sta)) {
						LogUtil.generateLog(logPath, "账号：" + acct.getAcod() +  "的账户类型：" + status + "不满足事先判断标准，请确认！");
					}
				}
			}
		}
	}

	/**
	 * 设置账户状态（开，关） 变更提取时处理
	 * 
	 * @param acccust
	 * @throws Exception
	 */
	private void setAccountOpenOrClosed(AccCustEntity acccust) throws Exception {
		if (null != acccust.getOpendate()
				&& null != acccust.getClosedate()
				&& acccust.getOpendate().compareTo(acccust.getClosedate()) == 0
				&& acccust.getOpendate().compareTo(acccust.getImportdate()) == 0) {// 当天开关户时
			if (acccust.getBalance().compareTo(new BigDecimal(0)) == 0) {
				acccust.setAccountstat("14");
			} else {
				LogUtil.generateLog(
						logPath,
						"账号：" + acccust.getAcod() + " 币种：" + acccust.getCcy()
								+ "的开户日期：" + acccust.getOpendate() + " 关户日期："
								+ acccust.getClosedate() + " 余额："
								+ acccust.getBalance());
			}
		} else if (null != acccust.getOpendate()
				&& acccust.getOpendate().compareTo(acccust.getImportdate()) == 0) {
			acccust.setAccountstat("11");
		} else if (null != acccust.getClosedate()
				&& acccust.getClosedate().compareTo(acccust.getImportdate()) == 0) {
			if (acccust.getBalance().compareTo(new BigDecimal(0)) == 0) {
				acccust.setAccountstat("13");
			} else {
				LogUtil.generateLog(logPath, "账号：" + acccust.getAcod() + " 币种："
						+ acccust.getCcy() + "的关户日期：" + acccust.getClosedate()
						+ " 余额：" + acccust.getBalance());
			}
		}
	}

	/**
	 * 主体类型
	 * @param acct
	 */
	private void setCustomerAmtype(AccCustEntity acct, CustBaseEntity cust) {
		if (null != cust) {
			String ctnt = cust.getCtnt();
			if ("CustCorpEntity".equals(cust.getClass().getSimpleName())) {
				acct.setAmtype("11");// 开户主体类型 公
			} else if ( null != ctnt && "CHN".equals(ctnt)) {
				acct.setAmtype("12");// 开户主体类型 私
			} else if ( null != ctnt && !"CHN".equals(ctnt)) {
				acct.setAmtype("13");// 开户主体类型 非居民
			}
		}
	}

	/**
	 * 账户限额类型
	 * 
	 * @param ifxacct
	 */
//	private void setAccountLimittype(Ifxacct ifxacct) {
//		String ifxattr = ifxacct.getIfxattr();
//		if (null != ifxacct.getIfxattr()) {
//			if (ifxacct.getIfxattr().length() >= 5
//					&& "303".equals(StringUtils.substring(ifxattr, 2))) {
//				ifxacct.setLimittype("12");// 账户限额类型
//			} else if (ifxacct.getIfxattr().length() >= 5
//					&& "306".equals(StringUtils.substring(ifxattr, 2))) {
//				ifxacct.setLimittype("13");// 账户限额类型
//			} else {
//				ifxacct.setLimittype("11");// 账户限额类型
//			}
//		}
//	}

	public OutlineImport getOutlineImport() throws Exception {
		OutlineImport outlineImport = new OutlineImport();
		Date maxT24Importdate = getMaxImportdate(TaskscheduleEntity.class, "importdate", "taskname='autoImportData'", new Object[] {});
		Date maxSummitImportdate = getMaxImportdate(TaskscheduleEntity.class, "importdate", "taskname='autoImportSmtData' and executable='0'", new Object[] {});
		if (null != maxT24Importdate) {
			String t24Importdate = DateUtils.dateToStr(maxT24Importdate, Constants.DATEFORMAT);
			outlineImport.setLastt24importdate(t24Importdate);
		}
		if (null != maxSummitImportdate) {
			String summitImportdate = DateUtils.dateToStr(maxSummitImportdate, Constants.DATEFORMAT);
			outlineImport.setLastsummitimportdate(summitImportdate);
		}
		PropertiesConfiguration pc = new PropertiesConfiguration(Constants.DATA_FILES_PATH);
		String sourcePath = pc.getString("sourcepath");
		String msg = "";
		File pbcdir = new File(sourcePath.toString());
		File[] datedirs = pbcdir.listFiles();
		if (null != datedirs && datedirs.length != 1) {
			File datedir = datedirs[0];
			String datestr = datedir.getName();
			msg = "<span style='color:red;'>营业日期为:" + datestr
					+ "的T24源文件还没有导入，请等待或者与IT联系。待数据正常导入后，再进行报表上报工作。</span>";
			outlineImport.setImportmessage(msg);
		} else {
			msg = "未发现。";
			outlineImport.setImportmessage(msg);
		}
		
		if(null == outlineImport.getLastt24importdate()) {
			outlineImport.setT24summitdatediff("<span style='color:red;'>T24源文件还未导入，请等待或者与IT联系。待数据正常导入后，再进行报表上报工作。</span>");
		} else if(null == outlineImport.getLastsummitimportdate()){
			outlineImport.setT24summitdatediff("<span style='color:red;'>Summit源数据还未导入，请等待或者与IT联系。待数据正常导入后，再进行报表上报工作。</span>");
		} else if(maxSummitImportdate.after(maxT24Importdate)) {
			outlineImport.setT24summitdatediff("<span style='color:red;'>Summit源数据比T24源文件导入日期晚，由于Summit需要使用T24汇率，请与IT联系，待T24数据正常导入后，重新导入Summit数据。</span>");
		} else if(maxSummitImportdate.before(maxT24Importdate)) {
			outlineImport.setT24summitdatediff("<span style='color:red;'>Summit源数据比T24源文件导入日期早，请等待或者与IT联系。待Summit数据正常导入后，再进行报表上报工作。</span>");
		} else {
			outlineImport.setT24summitdatediff("<span style='color:green;'>Summit源数据和T24源文件均已导入。</span>");
		}
		return outlineImport;
	}

	private Date getMaxImportdate(Class clazz, String fieldname, String hql, Object[] params) {
		Object importdate = getMaxFieldValueByWhere(clazz, fieldname, hql, params);
		return (Date) importdate;
	}
	
	/**
	 * 拷贝远程文件到本地目录
	 * 
	 * @param smbFile
	 *            远程SmbFile
	 * @param localDirectory
	 *            本地存储目录,本地目录不存在时会自动创建,本地目录存在时可自行选择是否清空该目录下的文件,默认为不清空
	 * @return boolean 是否拷贝成功
	 */
	private static boolean copyRemoteFile(SmbFile smbFile, String localDirectory) {
		SmbFileInputStream in = null;
		FileOutputStream out = null;
		try {
			in = new SmbFileInputStream(smbFile);
			out = new FileOutputStream(localDirectory + smbFile.getName());
			byte[] buffer = new byte[1024];
			int len = -1;
			while ((len = in.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	}
}