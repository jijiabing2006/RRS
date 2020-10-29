package com.finereport.main.workbook;

import com.fr.main.FineBook;
import com.fr.page.PageSetChainProvider;
import com.fr.report.ResultWorkBookProvider;
import com.fr.report.report.Report;
import com.fr.report.report.ResultReport;
import com.fr.stable.xml.XMLPrintWriter;
import com.fr.stable.xml.XMLVersion;
import java.util.List;
import java.util.Map;

public abstract interface ResultWorkBook extends FineBook, ResultWorkBookProvider
{
  public abstract ResultReport getResultReport(int paramInt);

  public abstract PageSetChainProvider generateReportPageSet(List paramList);

  public abstract Map getExecuteParameters();

  public abstract void writeCommonXML(XMLPrintWriter paramXMLPrintWriter);

  public abstract void setXMLVersion(XMLVersion paramXMLVersion);

  public abstract XMLVersion getXMLVersion();

  public abstract void addReport(String paramString, Report paramReport);
}

/* Location:           C:\FineReport_8.0\WebReport\WEB-INF\lib\fr-report-8.0.jar
 * Qualified Name:     com.fr.main.workbook.ResultWorkBook
 * JD-Core Version:    0.6.0
 */