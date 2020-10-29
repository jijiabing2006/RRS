package com.finereport.service.impl;

//程序网络报表中获取request中的值 

import java.util.List;
import java.util.Map;

import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.SpringUtils;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;

import com.finereport.entity.FRReportEntity;
import com.finereport.entity.FRReportParaEntity;
import com.finereport.entity.FRReportTemplateEntity;
import com.finereport.service.URLParameterI;
import com.fr.base.FRContext;
import com.fr.base.Parameter;
import com.fr.dav.LocalEnv;
import com.fr.general.FRLogManager;
import com.fr.general.ModuleContext;
import com.fr.main.TemplateWorkBook;
import com.fr.main.parameter.ReportParameterAttr;
import com.fr.report.module.EngineModule;
import com.fr.web.core.Reportlet;
import com.fr.web.request.ReportletRequest;
import com.lzsoft.util.Rootpath;

public class ReporletFromDB extends Reportlet implements URLParameterI {
	private SystemService systemService = SpringUtils.getBean("systemService");

	public TemplateWorkBook createReport(ReportletRequest reportletRequest) {

		// String envPath="D:\\FineReport_7.1\\WebReport\\WEB-INF";
		String envpath = Rootpath.getAppPath() + "WEB-INF";
		FRContext.setCurrentEnv(new LocalEnv(envpath));
		ModuleContext.startModule(EngineModule.class.getName());
		// 获取外部传来的参数
		TemplateWorkBook wbTpl = null;
		TemplateWorkBook wbTpl2 = null;

		try {
			// wbTpl =
			// TemplateWorkBookIO.readTemplateWorkBook(//如果要从硬盘读取Template，使用com.fr.io.TemplateWorkBookIO
			// FRContext.getCurrentEnv(), "\\Parameter.cpt");
			String rpname = reportletRequest.getParameter("rpname").toString();
			FRReportTemplateEntity paraptl = (FRReportTemplateEntity) systemService.findUniqueByProperty(FRReportTemplateEntity.class,
							"cptname", "parameter");
			FRReportTemplateEntity rptpl = (FRReportTemplateEntity) systemService.findUniqueByProperty(FRReportTemplateEntity.class,
							"cptname", rpname);

			if (null == paraptl || null == rptpl) {
				return null;
			}
			wbTpl = TemplateWorkBookIO.readTemplateWorkBook(paraptl);

			FRReportEntity frrp = (FRReportEntity) systemService.findUniqueByProperty(FRReportEntity.class, "reportname", rpname);

			// wbTpl2 = TemplateWorkBookIO.readTemplateWorkBook(
			// FRContext.getCurrentEnv(), "\\"+frrp.getTemplatename());
			wbTpl2 = TemplateWorkBookIO.readTemplateWorkBook(rptpl);

			ReportParameterAttr paraAttr = wbTpl.getReportParameterAttr();
			paraAttr.setDelayPlaying(true);
			// 提取报表参数组，由于原模板只有country一个参数，因此直接取index为0的参数，并将外部传入的值赋给该参数
			Parameter[] ps = wbTpl.getParameters();
			List<FRReportParaEntity> frl = systemService.findByProperty(
					FRReportParaEntity.class, "reportid", frrp.getId());
			TSUser user= ResourceUtil.getSessionUserName();
			String brca= user.getCurrentDepart().getBrca();
			for (Parameter parameter : ps) {
				for (FRReportParaEntity frr : frl) {
					if (frr.getParaname().equals(parameter.getName())) {
						parameter.setValue(frr.getParavalue());
					}
				}
			}
			for(Parameter parameter : ps){
				if("brca".equals(parameter.getName())) {
					if("0000".equals(parameter.getValue())){
						parameter.setValue("");
					} else {
						parameter.setValue(brca);
					}
				}
			}
			wbTpl.setReportParameterAttr(paraAttr);
			// 提取报表参数组，由于原模板只有country一个参数，因此直接取index为0的参数，并将外部传入的值赋给该参数
			Parameter[] ps2 = wbTpl2.getParameters();
			// ps2=ps.clone();
			wbTpl2.setReportParameterAttr(paraAttr);
			// wbTpl.setReportParameterAttr(paraAttr);

			// 原模板定义有参数界面，参数已经从外部获得，去掉参数页面
			// 若您想保留参数界面，则将模板设置为不延迟报表展示，再传入参数后直接根据参数值显示结果，否则还需要再次点击查询按钮
			// wbTpl.getReportParameterAttr().setParameterUI(null);

		} catch (Exception e) {
			e.printStackTrace();
			FRLogManager.createLogPackedException(e);
			return null;
		}
		
		return wbTpl2;
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
