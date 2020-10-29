package com.lzsoft.service.common.impl;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lzsoft.service.common.MmFixDepositServiceI;

@Service("mmFixDepositService")
@Transactional
public class MmFixDepositServiceImpl extends CommonServiceImpl implements MmFixDepositServiceI {
	
}