package com.lzsoft.entity.safe;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**   
 * @Title: Entity
 * @Description: bop_n
 * @author onlineGenerator
 * @date 2015-08-13 09:21:23
 * @version V1.0   
 *
 */
@Entity
@Table(name = "bop_n", schema = "")
@SuppressWarnings("serial")
@Inheritance(strategy = InheritanceType.JOINED)
public class BopNEntity extends BopControlEntity implements java.io.Serializable {}
