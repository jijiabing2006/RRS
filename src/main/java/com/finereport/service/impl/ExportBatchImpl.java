package com.finereport.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.util.DateUtils;
import org.jeecgframework.core.util.FileUtils;
import org.jeecgframework.core.util.LogUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finereport.entity.FRReportEntity;
import com.finereport.entity.FRReportParaEntity;
import com.finereport.entity.FRReportTemplateEntity;
import com.finereport.service.ExportBatchI;
import com.fr.base.FRContext;
import com.fr.dav.LocalEnv;
import com.fr.general.ModuleContext;
import com.fr.io.exporter.ExcelExporter;
import com.fr.main.TemplateWorkBook;
import com.fr.main.workbook.ResultWorkBook;
import com.fr.report.module.EngineModule;
import com.fr.stable.WriteActor;
import com.lzsoft.entity.common.AccGLEntity;
import com.lzsoft.entity.common.BankinfoEntity;
import com.lzsoft.service.safe.impl.ExtractServiceImpl;
import com.lzsoft.util.Rootpath;

@Service("exportBatch")
@Transactional
public class ExportBatchImpl extends ExtractServiceImpl implements ExportBatchI {

	public void batchExportByM() {

		List<FRReportEntity> frrl = getFRReportParaByFreq("monthly");
		if (null != frrl && !frrl.isEmpty()) {
			exportAllReports(frrl);
			// LogUtil.info(DateUtils.date2Str(maxImportdate,
			// DateUtils.date_sdf)
			// + "Reports had exported.........." + (new Date()));

		} else {
			LogUtil.info("没有Monthly类型的报表可以生成.........." + (new Date()));
		}
	}

	public void batchExportByD() {
		List<FRReportEntity> frrl = getFRReportParaByFreq("Daily");
		if (null != frrl && !frrl.isEmpty()) {
			exportAllReports(frrl);
			// LogUtil.info(DateUtils.date2Str(maxImportdate,
			// DateUtils.date_sdf)
			// + "Reports had exported.........." + (new Date()));

		} else {
			LogUtil.info("没有Monthly类型的报表可以生成.........." + (new Date()));
		}
	}

	private List<FRReportEntity> getFRReportParaByFreq(String statfreq) {
		CriteriaQuery cq2 = new CriteriaQuery(FRReportEntity.class);
		cq2.eq("statfreq", statfreq);
		cq2.add();
		return getListByCriteriaQuery(cq2, false);
	}

	private void exportAllReports(List<FRReportEntity> frrl) {

		CriteriaQuery cq = new CriteriaQuery(AccGLEntity.class);
		cq.max("importdate");
		cq.add();
		Date maxImportdate = getUniqueResultByCriteriaQuery(cq);

		List parentbrcas = getFieldValueByWhere(BankinfoEntity.class,
				"parentbrca", "parentbrca<>? and parentbrca<>?", new Object[] {
						"-1", "000000" });
		generalReports(maxImportdate, frrl, parentbrcas);
	}

	public void generalReports(Date importdate, List<FRReportEntity> frrl,
			List parentbrcas) {
		try {
			// 定义报表运行环境,用于执行报表
			// String envpath = "D:\\FineReport_7.1\\WebReport\\WEB-INF";
			String envpath = Rootpath.getAppPath() + "WEB-INF";
			FRContext.setCurrentEnv(new LocalEnv(envpath));
			ModuleContext.startModule(EngineModule.class.getName());
			// 定义保存参数的map，用于执行报表
			Map paramap = new HashMap();

			for (FRReportEntity frReportEntity : frrl) {
				// 读取环境下的模板文件
				FRReportTemplateEntity rptpl = (FRReportTemplateEntity) findUniqueByProperty(
						FRReportTemplateEntity.class, "cptname", frReportEntity
								.getReportname().trim());

				if (null != rptpl) {

					TemplateWorkBook workbook = TemplateWorkBookIO
							.readTemplateWorkBook(rptpl);
					for (Object brcao : parentbrcas) {
						paramap.put("rpdate",
								DateUtils.dateToStr(importdate, "yyyy-MM-dd"));
						paramap.put("brca", (String) brcao);
						List<FRReportParaEntity> pl = frReportEntity
								.getFrreportparas();
						if (null != pl && !pl.isEmpty()) {
							for (FRReportParaEntity frpa : pl) {
								paramap.put(frpa.getParaname(),
										frpa.getParavalue());
							}
						}
						ResultWorkBook result = workbook.execute(paramap,
								new WriteActor());
						OutputStream outputstream = newOutputFile(importdate,
								frReportEntity, brcao);
						ExcelExporter excelexporter = new ExcelExporter();
						excelexporter.export(outputstream, result);
						// 最后要清空一下参数map，用于下次计算
						paramap.clear();
						outputstream.close();
					}

				}
			}

			// 读取用于保存的参数值的txt文件
			// File parafile = new File(envpath + "\\para.txt");
			// FileInputStream fileinputstream;
			// fileinputstream = new FileInputStream(parafile);
			// BufferedReader bufferedReader = new BufferedReader(
			// new InputStreamReader(fileinputstream));
			/*
			 * 遍历参数值所在txt文件，txt文件中参数保存形式为 para1,para2 江苏,陈羽 江苏,安娜 首先取出第一行保存参数名称
			 * 遍历每个参数组合，如para1=江苏、para2=陈羽，根据参数执行模板，并将结果导出excel excel文件名为名称+导出编号
			 */
			// 读第一行，保存参数名称
			// String lineText = bufferedReader.readLine();
			// lineText = lineText.trim();
			// String[] paraname = StableUtils.splitString(lineText, ",");
			// System.out.println(Arrays.toString(paraname));
			// 遍历每个参数组合，执行模板，导出结果
			// int number = 0;
			// while ((lineText = bufferedReader.readLine()) != null) {
			// lineText = lineText.trim();
			// String[] paravalue = StableUtils.splitString(lineText, ",");
			// for (int j = 0; j < paravalue.length; j++) {
			// paramap.put(paraname[j], paravalue[j]);
			// }
			// ResultWorkBook result = workbook.execute(paramap,
			// new WriteActor());
			// OutputStream outputstream = new FileOutputStream(new File(
			// "E:\\ExportEg" + number + ".xls"));
			// ExcelExporter excelexporter = new ExcelExporter();
			// excelexporter.export(outputstream, result);
			// // 最后要清空一下参数map，用于下次计算
			// paramap.clear();
			// number++;
			// outputstream.close();
			// }
			ModuleContext.stopModules();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private OutputStream newOutputFile(Date importdate,
			FRReportEntity frReportEntity, Object brcao)
			throws FileNotFoundException {
		String reportpath = Rootpath.getAppPath() + "report" + File.separator
				+ (String) brcao + File.separator + frReportEntity.getSyskind()
				+ File.separator + DateUtils.dateToStr(importdate, "yyyyMMdd")
				+ File.separator;
		FileUtils.newFolder(reportpath);
		String filename = reportpath + frReportEntity.getReportname().trim()
				+ ".xls";
		OutputStream outputstream = new FileOutputStream(new File(filename));
		return outputstream;
	}

}
