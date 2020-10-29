package com.finereport.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;

import org.jeecgframework.core.util.StreamUtils;

import com.finereport.entity.FRReportTemplateEntity;
import com.fr.base.Env;
import com.fr.base.io.XMLEncryptUtils;
import com.fr.general.FRLogManager;
import com.fr.general.Inter;
import com.fr.general.ModuleContext;
import com.fr.main.TemplateWorkBook;
import com.fr.main.impl.WorkBook;
import com.fr.report.module.EngineModule;
import com.fr.stable.StableUtils;

public final class TemplateWorkBookIO {
	public static TemplateWorkBook readTemplateWorkBook(FRReportTemplateEntity frtpl) throws Exception {
		if (frtpl == null)
			return null;
		WorkBook localWorkBook = null;
		FRLogManager.declareResourceReadStart(frtpl.getCptname());
		ModuleContext.startModule(EngineModule.class.getName());
		try {
			InputStream localInputStream =StreamUtils.byteTOInputStream(frtpl.getCpt());
			if (localInputStream != null) {
					localWorkBook = new WorkBook();
					localWorkBook.readStream(XMLEncryptUtils
							.decodeInputStream(localInputStream));
				localInputStream.close();
			} else {
				throw new FileNotFoundException(
						Inter.getLocText("NS_exception_noTemplate") + ":"
								+ frtpl.getCptname());
			}
		} catch (Exception localException) {
			throw FRLogManager.createLogPackedException(localException);
		} finally {
			FRLogManager.declareResourceReadEnd();
		}
		return (TemplateWorkBook) localWorkBook;
	}

	public static boolean writeTemplateWorkBook(Env paramEnv,
			TemplateWorkBook paramTemplateWorkBook, String paramString)
			throws Exception {
		FRLogManager.declareResourceWriteStart(paramString);
		ModuleContext.startModule(EngineModule.class.getName());
		try {
			OutputStream localOutputStream = paramEnv.writeBean(paramString,
					"reportlets");
			((WorkBook) paramTemplateWorkBook).export(localOutputStream);
			localOutputStream.flush();
			localOutputStream.close();
		} catch (Exception localException) {
			throw FRLogManager.createLogPackedException(localException);
		} finally {
			FRLogManager.declareResourceWriteEnd();
		}
		return true;
	}

	public static long getLastModifiedTime(Env paramEnv, String paramString) {
		try {
			File localFile = new File(StableUtils.pathJoin(new String[] {
					paramEnv.getPath(), "reportlets", paramString }));
			return localFile.lastModified();
		} catch (Exception localException) {
		}
		return 0L;
	}

}
