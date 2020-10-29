package com.lzsoft.autoimport.service.base;

import java.util.List;

public interface IBaseImportMethod {

	/**
	 * 
	 * @param list
	 * @param resourceFilePath
	 * @return
	 */
	public void etl(List<Object> list, String resourceFilePath);

}
