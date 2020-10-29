package com.finereport.main;

import com.finereport.report.report.Report;
import com.fr.data.TableDataSource;
import com.fr.io.attr.ReportExportAttr;
import com.fr.report.report.ECReport;
import com.fr.stable.fun.ReportFitAttrProvider;
import com.fr.stable.xml.XMLable;
import com.fr.web.ImportJsCssProvider;
import com.fr.web.attr.ReportWebAttr;

public abstract interface FineBook extends TableDataSource, XMLable, ImportJsCssProvider
{
  public abstract String getReportName(int paramInt);

  public abstract void setReportName(int paramInt, String paramString);

  public abstract void removeReport(String paramString);

  public abstract void removeReport(int paramInt);

  public abstract void removeReports();

  public abstract Report getReport(int paramInt);

  public abstract boolean isElementCaseBook();

  public abstract boolean isElementCaseBook(int paramInt);

  public abstract ECReport getElementCaseReport(int paramInt);

  public abstract int getReportCount();

  public abstract ReportWebAttr getReportWebAttr();

  public abstract void setReportWebAttr(ReportWebAttr paramReportWebAttr);

  public abstract ReportExportAttr getReportExportAttr();

  public abstract void setReportExportAttr(ReportExportAttr paramReportExportAttr);

  public abstract ReportFitAttrProvider getFitAttr();

  public abstract void setFitAttr(ReportFitAttrProvider paramReportFitAttrProvider);
}

/* Location:           C:\FineReport_8.0\WebReport\WEB-INF\lib\fr-report-8.0.jar
 * Qualified Name:     com.fr.main.FineBook
 * JD-Core Version:    0.6.0
 */