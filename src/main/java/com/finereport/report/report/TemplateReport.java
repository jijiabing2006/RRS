package com.finereport.report.report;

import com.finereport.main.TemplateWorkBook;
import com.fr.privilege.finegrain.WorkSheetPrivilegeControl;

import java.util.Map;

public abstract interface TemplateReport extends Report
{

  public abstract TemplateWorkBook getTemplateWorkBook();

  public abstract void setTemplateWorkBook(TemplateWorkBook paramFineBook);

  public abstract WorkSheetPrivilegeControl getWorkSheetPrivilegeControl();

  public abstract void setWorkSheetPrivilegeControl(WorkSheetPrivilegeControl paramWorkSheetPrivilegeControl);
}

/* Location:           C:\FineReport_8.0\WebReport\WEB-INF\lib\fr-report-8.0.jar
 * Qualified Name:     com.fr.report.report.TemplateReport
 * JD-Core Version:    0.6.0
 */