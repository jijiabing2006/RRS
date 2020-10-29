package com.lzsoft.autoimport.parser;

import java.io.File;
import java.util.Date;
import java.util.List;

import com.lzsoft.entity.common.CustBankEntity;

public interface IParser {

	public <T> List<T> parser(File sourcefile, Class clazz) throws Exception;

	public <T> List<T> parser(String string, Class  clazz, Date trandate)
			throws Exception;

}
