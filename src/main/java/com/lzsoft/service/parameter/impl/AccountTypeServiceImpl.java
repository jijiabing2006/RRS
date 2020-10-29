package com.lzsoft.service.parameter.impl;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lzsoft.service.parameter.AccountTypeServiceI;

@Service("accountTypeService")
@Transactional
public class AccountTypeServiceImpl extends CommonServiceImpl implements AccountTypeServiceI {
	
}