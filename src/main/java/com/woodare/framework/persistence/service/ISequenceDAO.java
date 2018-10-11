/******************************************************************************
 *                                                                             
 *                      Woodare PROPRIETARY INFORMATION                        
 *                                                                             
 *          The information contained herein is proprietary to Woodare         
 *           and shall not be reproduced or disclosed in whole or in part      
 *                    or used for any design or manufacture                    
 *              without direct written authorization from FengDa.              
 *                                                                             
 *            Copyright (c) 2013 by Woodare.  All rights reserved.             
 *                                                                             
 *****************************************************************************/
package com.woodare.framework.persistence.service;

/**
 * ClassName: ISequenceDAO
 * 
 * @description
 * @author Xinxing Jiang
 * @Date 2017年10月20日
 */
public interface ISequenceDAO {
	Long getSeq(String name);
}
