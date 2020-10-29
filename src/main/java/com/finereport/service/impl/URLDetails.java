package com.finereport.service.impl;

//程序网络报表中获取request中的值 

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jeecgframework.core.util.SpringUtils;
import org.jeecgframework.core.util.StreamUtils;
import org.jeecgframework.web.system.service.SystemService;

import com.finereport.entity.FRReportTemplateEntity;
import com.fr.general.FRLogManager;
import com.fr.main.TemplateWorkBook;
import com.fr.web.core.Reportlet;
import com.fr.web.request.AbstractReportletRequest;
import com.fr.web.request.ReportletRequest;

public class URLDetails extends Reportlet {
	private SystemService systemService = SpringUtils.getBean("systemService");

	public TemplateWorkBook createReport(ReportletRequest reportletRequest) { // 创建一个WorkBook工作薄，在工作薄中插入一个WorkSheet

		String conditions = getCurrentCellCondition(reportletRequest);

		String dcpt = reportletRequest.getParameter("DCPT").toString();// 得到Details模板的名称

		FRReportTemplateEntity detailstpl = (FRReportTemplateEntity) systemService
				.findUniqueByProperty(FRReportTemplateEntity.class, "cptname",
						dcpt);
		String s = StreamUtils.byteTOString(detailstpl.getCpt());
		String st = "<C c=\"0\" r=\"1\" s=\"";// 明细以第2行第1列开始
		String cc = StringUtils.substring(s, s.indexOf(st),
				s.indexOf("</C>", s.indexOf(st)));
		String dc = StringUtils.substring(cc, cc.indexOf("<Condition"),
				cc.indexOf("<RG", cc.indexOf("<Condition")));
		String cct = cc;

		cct = StringUtils.replace(cct, dc, conditions);
		s = StringUtils.replace(s, cc, cct);
		detailstpl.setCpt(StreamUtils.StringTObyte(s));
		// 获取外部传来的参数
		TemplateWorkBook wbTpl = null;
		try {
			wbTpl = TemplateWorkBookIO.readTemplateWorkBook(detailstpl);
		} catch (Exception e) {
			e.printStackTrace();
			FRLogManager.createLogPackedException(e);
			return null;
		}
		return wbTpl;
	}

	/**
	 * 得到Rpname下 依据 row/column为条件下的condition(过滤条件)。与数据集无关，只得到条件，换言之，
	 * Details中数据集与Rpname中数据集是默认要求一致的
	 * 
	 * 从<Condition 开始到<Rg这一行前结束
	 * 
	 * @param reportletRequest
	 * @return
	 */
	private String getCurrentCellCondition(ReportletRequest reportletRequest) {
		String rpname = reportletRequest.getParameter("RPNAME").toString();

		FRReportTemplateEntity rptpl = (FRReportTemplateEntity) systemService
				.findUniqueByProperty(FRReportTemplateEntity.class, "cptname",
						rpname);

		String s = StreamUtils.byteTOString(rptpl.getCpt());
		String row = reportletRequest.getParameter("ROW").toString();
		String column = reportletRequest.getParameter("COLUMN").toString();

		String rn = String.valueOf(Integer.parseInt(row) - 1);
		String cn = String.valueOf(Integer.parseInt(column) - 1);

		String st = "<C c=\"" + cn + "\" r=\"" + rn + "\" s=\"";
		// System.out.println(s.indexOf(st));
		// System.out.println(s.indexOf("</C>", s.indexOf(st)));
		// System.out.println(StringUtils.substring(s, s.indexOf(st),
		// s.indexOf("</C>", s.indexOf(st))));

		String cc = StringUtils.substring(s, s.indexOf(st),
				s.indexOf("</C>", s.indexOf(st)));

		// System.out.println(cc.indexOf("<Condition"));
		// System.out.println(cc.indexOf("<RG", cc.indexOf("<Condition")));
		// System.out.println(StringUtils.substring(cc,
		// cc.indexOf("<Condition"),
		// cc.indexOf("<RG", cc.indexOf("<Condition"))));
		String dc = StringUtils.substring(cc, cc.indexOf("<Condition"),
				cc.indexOf("<RG", cc.indexOf("<Condition")));
		return dc;
	}

	@Override
	public void setParameterMap(Map arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setTplPath(String arg0) {
		// TODO Auto-generated method stub

	}

}
