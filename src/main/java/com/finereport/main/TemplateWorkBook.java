package com.finereport.main;

import com.finereport.main.workbook.ResultWorkBook;
import com.finereport.report.report.TemplateReport;
import com.finereport.stable.fun.Actor;
import com.fr.base.ParameterHolder;
import com.fr.main.parameter.ReportParameterAttr;
import com.fr.report.worksheet.WorkSheet;

import java.util.Map;

public abstract interface TemplateWorkBook extends FineBook, ParameterHolder
{
  public abstract void addReport(TemplateReport paramTemplateReport);

  public abstract void addReport(String paramString, TemplateReport paramTemplateReport);

  public abstract void setReport(int paramInt, TemplateReport paramTemplateReport);

  public abstract void setReport(int paramInt, String paramString, TemplateReport paramTemplateReport);

  public abstract  TemplateReport getTemplateReport(int paramInt);

  public abstract WorkSheet getTemplateElementCaseReport(int paramInt);

  public abstract int getReportIndex(TemplateReport paramTemplateReport);

  public abstract void apply4Parameters(Map paramMap);

  public abstract ResultWorkBook execute(Map paramMap, Actor paramActor);

  public abstract ResultWorkBook execute(Map paramMap, Actor paramActor, int paramInt);

  public abstract ReportParameterAttr getReportParameterAttr();

  public abstract void setReportParameterAttr(ReportParameterAttr paramReportParameterAttr);
}

/* Location:           C:\FineReport_8.0\WebReport\WEB-INF\lib\fr-report-8.0.jar
 * Qualified Name:     com.fr.main.TemplateWorkBook
 * JD-Core Version:    0.6.0
 */