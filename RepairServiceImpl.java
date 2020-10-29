package jeecg.system.service.impl;

import org.jeecgframework.web.system.pojo.base.TSFunction;
import org.jeecgframework.web.system.pojo.base.TSIcon;
import org.jeecgframework.web.system.service.RepairService;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** 
 * @Description 修复数据库Service
 * @ClassName: RepairService
 * @author tanghan
 * @date 2013-7-19 下午01:31:00  
 */ 
@Service("repairService")
@Transactional
public class RepairServiceImpl extends CommonServiceImpl implements RepairService{
        
	/** 
	 * @Description  先清空数据库，然后再修复数据库
	 * @author tanghan 2013-7-19  
	 */
	
	public void deleteAndRepair() {
		
	}

	/** 
	 * @Description  修复数据库
	 * @author tanghan 2013-7-19  
	 */
	
		synchronized public void repair() {
		repaireIcon(); //修复图标
		repairAttachment();  //修改附件
		repairDepart();
		repairMenu();// 修复菜单权限【权限控制到菜单级别】
		repairRole();// 修复角色
		repairUser();// 修复用户
//		repairRoleAuth();// 修复角色和权限的关系
//		repairUserRole();// 修复用户和角色的关系
//		repairDict();// 修复字典
//		repairOrg();// 修复组织机构
	}

/** 
	 * @Description 
	 * @author tanghan 2013-7-28  
	 */
	private void repairCgFormField() {
					//表单[图表配置] - 字段清单
			CgFormHeadEntity jform_graphreport_head = commonDao.findByProperty(CgFormHeadEntity.class, "tableName", "jform_graphreport_head").get(0);
			//表单[图表配置] - 字段清单
			CgFormHeadEntity jform_graphreport_item = commonDao.findByProperty(CgFormHeadEntity.class, "tableName", "jform_graphreport_item").get(0);
			//表单[第一个树] - 字段清单
			CgFormHeadEntity onlne_tree = commonDao.findByProperty(CgFormHeadEntity.class, "tableName", "onlne_tree").get(0);
			//表单[测试单表] - 字段清单
			CgFormHeadEntity test_onetable = commonDao.findByProperty(CgFormHeadEntity.class, "tableName", "test_onetable").get(0);
			//表单[t_s_function] - 字段清单
			CgFormHeadEntity t_s_function = commonDao.findByProperty(CgFormHeadEntity.class, "tableName", "t_s_function").get(0);
			//表单[sys_tree] - 字段清单
			CgFormHeadEntity sys_tree = commonDao.findByProperty(CgFormHeadEntity.class, "tableName", "sys_tree").get(0);
	        CgFormFieldEntity sys_tree_tree_id_ = new CgFormFieldEntity();
	        sys_tree_tree_id_.setFieldName("tree_id_");
	        sys_tree_tree_id_.setTable(sys_tree);
	        sys_tree_tree_id_.setFieldLength(120);
	        sys_tree_tree_id_.setIsKey("N");
	        sys_tree_tree_id_.setIsNull("N");
	        sys_tree_tree_id_.setIsQuery("N");
	        sys_tree_tree_id_.setIsShow("Y");
	        sys_tree_tree_id_.setShowType("text");
	        sys_tree_tree_id_.setLength(255);
	        sys_tree_tree_id_.setType("string");
	        sys_tree_tree_id_.setOrderNum(2);
	        sys_tree_tree_id_.setPointLength(0);
	        sys_tree_tree_id_.setQueryMode("group");
	        sys_tree_tree_id_.setContent("treeId");
	        sys_tree_tree_id_.setCreateBy("admin");
	        sys_tree_tree_id_.setCreateDate(new Date());
	        sys_tree_tree_id_.setCreateName("管理员");
	        sys_tree_tree_id_.setDictField("
Expression f.dictField is undefined on line 75, column 64 in init.ftl.
The problematic instruction:
----------
==> ${f.dictField} [on line 75, column 62 in init.ftl]
----------

Java backtrace for programmers:
----------
freemarker.core.InvalidReferenceException: Expression f.dictField is undefined on line 75, column 64 in init.ftl.
	at freemarker.core.TemplateObject.assertNonNull(TemplateObject.java:125)
	at freemarker.core.Expression.getStringValue(Expression.java:118)
	at freemarker.core.Expression.getStringValue(Expression.java:93)
	at freemarker.core.DollarVariable.accept(DollarVariable.java:76)
	at freemarker.core.Environment.visit(Environment.java:221)
	at freemarker.core.MixedContent.accept(MixedContent.java:92)
	at freemarker.core.Environment.visit(Environment.java:221)
	at freemarker.core.IteratorBlock$Context.runLoop(IteratorBlock.java:179)
	at freemarker.core.Environment.visit(Environment.java:428)
	at freemarker.core.IteratorBlock.accept(IteratorBlock.java:102)
	at freemarker.core.Environment.visit(Environment.java:221)
	at freemarker.core.MixedContent.accept(MixedContent.java:92)
	at freemarker.core.Environment.visit(Environment.java:221)
	at freemarker.core.IteratorBlock$Context.runLoop(IteratorBlock.java:179)
	at freemarker.core.Environment.visit(Environment.java:428)
	at freemarker.core.IteratorBlock.accept(IteratorBlock.java:102)
	at freemarker.core.Environment.visit(Environment.java:221)
	at freemarker.core.MixedContent.accept(MixedContent.java:92)
	at freemarker.core.Environment.visit(Environment.java:221)
	at freemarker.core.Environment.process(Environment.java:199)
	at freemarker.template.Template.process(Template.java:259)
	at test.JeecgInitDB.main(JeecgInitDB.java:286)
