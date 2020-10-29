package com.lzsoft.service.parameter.impl;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lzsoft.service.parameter.CurrencyServiceI;

@Service("currencyService")
@Transactional
public class CurrencyServiceImpl extends CommonServiceImpl implements CurrencyServiceI {
	
}