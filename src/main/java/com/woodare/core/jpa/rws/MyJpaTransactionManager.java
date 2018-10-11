package com.woodare.core.jpa.rws;

import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionStatus;

/**
 * @author Luke
 */
@SuppressWarnings("serial")
public class MyJpaTransactionManager extends JpaTransactionManager {
	// private static final Logger logger = LoggerFactory.getLogger(MyJpaTransactionManager.class);

	public static final ThreadLocal<String> holder = new ThreadLocal<String>();

	@Override
	protected void doBegin(Object transaction, TransactionDefinition definition) {
		if (definition.isReadOnly()) {
			RwDataSourceHolder.localSlave();
		}
		else {
			RwDataSourceHolder.localMaster();
		}

		holder.set(System.currentTimeMillis() + "");
		// logger.info("JPATransBegin[" + holder.get() + "][" + RwDataSourceHolder.getDataSouce() + "]");
		super.doBegin(transaction, definition);
	}

	@Override
	protected void doCommit(DefaultTransactionStatus status) {
		// logger.info("JPATransCommit[" + holder.get() + "][" + RwDataSourceHolder.getDataSouce() + "]");
		super.doCommit(status);
	}

	@Override
	protected void doRollback(DefaultTransactionStatus status) {
		// logger.info("JPATransRoll[" + holder.get() + "][" + RwDataSourceHolder.getDataSouce() + "]");
		super.doRollback(status);
	}

}
