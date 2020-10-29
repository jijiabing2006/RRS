package com.lzsoft.entity.safe;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**   
 * @Title: Entity
 * @Description: bop_b
 * @author onlineGenerator
 * @date 2015-06-08 15:06:11
 * @version V1.0   
 *
 */
@Entity
@Table(name = "bop_b", schema = "")
@Inheritance(strategy = InheritanceType.JOINED)
public class BopBEntity extends BopBaseEntity implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
}
