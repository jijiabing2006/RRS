package com.finereport.report.report;

import com.finereport.main.FineBook;
import com.fr.data.TableDataSource;
import com.fr.page.PageAttributeGetter;
import com.fr.page.ReportHFProvider;
import com.fr.page.ReportSettingsProvider;
import com.fr.report.core.ReportHF;
import com.fr.stable.xml.XMLable;

import java.util.Iterator;

public abstract interface Report
  extends PageAttributeGetter, XMLable
{
  public abstract FineBook getBook();
  
  public abstract TableDataSource getTableDataSource();
  
  public abstract boolean isElementCaseReport();
  
  public abstract Iterator iteratorOfElementCase();
  
  public abstract ReportHFProvider getHeader(int paramInt);
  
  public abstract ReportHFProvider getFooter(int paramInt);
  
  public abstract void setReportSettings(ReportSettingsProvider paramReportSettingsProvider);
  
  public abstract void setHeader(int paramInt, ReportHF paramReportHF);
  
  public abstract void setFooter(int paramInt, ReportHF paramReportHF);
}
