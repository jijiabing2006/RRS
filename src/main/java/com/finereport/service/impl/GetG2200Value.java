package com.finereport.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jeecgframework.core.util.DateUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.SpringUtils;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;

import com.fr.data.DefinedSubmitJob;
import com.fr.data.JobValue;
import com.fr.script.Calculator;
import com.lzsoft.entity.common.ResultTable;

public class GetG2200Value extends DefinedSubmitJob {
	
	/**
	 * 当模板自定义事件增加的属性 名称与下面变量有对应时，则会自动赋值于此对应变量
	 */
	private JobValue g2200C15;				// 流动性资产人民币
	private JobValue g2200C24;				// 流动性负债人民币
	private JobValue g2200D15;				// 流动性资产美元折人民币
	private JobValue g2200D24;				// 流动性负债美元折人民币
	private JobValue g2200E26;				// 流动性比例 (项目1.10/项目2.8×100％) 人民币美元合计列
	private JobValue g2200importdate;		// 报送日期
	private List<ResultTable> resultTableList = new ArrayList<ResultTable>();
//	@Autowired
//	private SystemService systemService;
	
	private SystemService systemService = (SystemService) SpringUtils.getBean("systemService");
	/**
	 * 每一条记录执行一次此方法
	 * 同一提交事件在一个处理事务内，此对象是唯一的
	 */
	public void doJob(Calculator calculator) throws Exception {
		
		TSUser user=ResourceUtil.getSessionUserName();
		String bkid=user.getCurrentDepart().getBrca();
		Date importdate = DateUtils.str2Date(g2200importdate.getValue().toString(), new SimpleDateFormat("yyyy-MM-dd"));
		// 15 行 C(3) 列	 1.10流动性资产总和 (项目1.1~项目1.9之和) 人民币列
		createResultTable(bkid, importdate, 3, 15, g2200C15);
		// 15 行 D(4) 列	 1.10流动性资产总和 (项目1.1~项目1.9之和) 美元合计列
		createResultTable(bkid, importdate, 4, 15, g2200D15);
		// 24 行 C(3) 列	 2.8流动性负债总和 (项目2.1~项目2.7之和) 人民币列
		createResultTable(bkid, importdate, 3, 24, g2200C24);
		// 24 行 D(4) 列         2.8流动性负债总和 (项目2.1~项目2.7之和) 美元合计列
		createResultTable(bkid, importdate, 4, 24, g2200D24);
		// 26 行 E(5)  列         2.8 3.流动性比例 (项目1.10/项目2.8×100％) 人民币美元合计列
		createResultTable(bkid, importdate, 5, 26, g2200E26);
		for (ResultTable resultTable : resultTableList) {
			systemService.deleteAllEntitie(systemService.findHql(ResultTable.class, " importdate=? and bkid=?", new Object[] { resultTable.getImportdate(), resultTable.getBkid()}));
		}
		systemService.batchSave(resultTableList);
	}

	private void createResultTable(String bkid, Date importdate, int columnindex, int rowindex, JobValue obj) {
		ResultTable resultTable;
		resultTable = new ResultTable();
		resultTable.setColumnindex(columnindex);
		resultTable.setRowindex(rowindex);
		resultTable.setImportdate(importdate);
		resultTable.setTablename("G2200");
		resultTable.setCcy("CNY");
		resultTable.setTableamount(new Double(obj.getValue().toString()));
		resultTable.setBkid(bkid);
		resultTableList.add(resultTable);
	}

	@Override
	public String getJobType() {
		// TODO Auto-generated method stub
		return null;
	}
}