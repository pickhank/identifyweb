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
package com.woodare.template.component.notify;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Service;

/**
 * ClassName: HandleEndpointDataSchedule
 * 
 * @description
 * @author Xinxing Jiang
 * @Date Aug 12, 2015
 */
@Service
public class NotifyService {

	private ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);

	public void exec(NotifyRunnable notify) {
		fixedThreadPool.execute(notify);
	}

}