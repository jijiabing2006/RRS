package com.finereport.service.impl;

//程序网络报表中获取request中的值 

import java.util.List;
import java.util.Map;

import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.core.util.SpringUtils;
import org.jeecgframework.web.system.service.SystemService;

import com.finereport.entity.FRReportEntity;
import com.finereport.entity.FRReportParaEntity;
import com.finereport.service.URLParameterI;
import com.fr.base.FRContext;
import com.fr.base.Parameter;
import com.fr.dav.LocalEnv;
import com.fr.general.ModuleContext;
import com.fr.io.TemplateWorkBookIO;
import com.fr.main.TemplateWorkBook;
import com.fr.main.parameter.ReportParameterAttr;
import com.fr.report.module.EngineModule;
import com.fr.web.core.Reportlet;
import com.fr.web.request.ReportletRequest;
import com.lzsoft.util.Rootpath;
public class URLParameter extends Reportlet implements URLParameterI{
	private SystemService systemService=SpringUtils.getBean("systemService");
	
	public TemplateWorkBook createReport(ReportletRequest reportletRequest) {

		// String envPath="D:\\FineReport_7.1\\WebReport\\WEB-INF";
		String envpath = Rootpath.getAppPath() + "WEB-INF";
		FRContext.setCurrentEnv(new LocalEnv(envpath));
		ModuleContext.startModule(EngineModule.class.getName());
		CommonService cs=SpringUtils.getBean("commonService");
		// 获取外部传来的参数
		TemplateWorkBook wbTpl = null;
		TemplateWorkBook wbTpl2 = null;

		
		try {
			wbTpl = TemplateWorkBookIO.readTemplateWorkBook(
					FRContext.getCurrentEnv(), "\\Parameter.cpt");
			
			
			
			String rpname =
			reportletRequest.getParameter("rpname").toString();
			
			FRReportEntity frrp=(FRReportEntity) systemService.findUniqueByProperty(FRReportEntity.class, "reportname", rpname);
			
			wbTpl2 = TemplateWorkBookIO.readTemplateWorkBook(
					FRContext.getCurrentEnv(), "\\"+frrp.getTemplatename());

			ReportParameterAttr paraAttr = wbTpl.getReportParameterAttr();
			paraAttr.setDelayPlaying(true);
			// 提取报表参数组，由于原模板只有country一个参数，因此直接取index为0的参数，并将外部传入的值赋给该参数
			Parameter[] ps = wbTpl.getParameters();
			List<FRReportParaEntity> frl=  systemService.findByProperty(FRReportParaEntity.class,"reportid",frrp.getId() );
			for (Parameter parameter : ps) {
				for (FRReportParaEntity frr : frl) {
					if (frr.getParaname().equals(parameter.getName())) {
						parameter.setValue(frr.getParavalue());
					}
				}
			}
			wbTpl.setReportParameterAttr(paraAttr);
			// 提取报表参数组，由于原模板只有country一个参数，因此直接取index为0的参数，并将外部传入的值赋给该参数
			Parameter[] ps2 = wbTpl2.getParameters();
		//	ps2=ps.clone();
			wbTpl2.setReportParameterAttr(paraAttr);
		//	wbTpl.setReportParameterAttr(paraAttr);

			// 原模板定义有参数界面，参数已经从外部获得，去掉参数页面
			// 若您想保留参数界面，则将模板设置为不延迟报表展示，再传入参数后直接根据参数值显示结果，否则还需要再次点击查询按钮
			// wbTpl.getReportParameterAttr().setParameterUI(null);

		} catch (Exception e) {
			e.printStackTrace();
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
