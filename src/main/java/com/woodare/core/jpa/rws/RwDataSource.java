package com.woodare.core.jpa.rws;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author Luke
 */
public class RwDataSource extends AbstractRoutingDataSource {
	@Override
	protected Object determineCurrentLookupKey() {
		return RwDataSourceHolder.getDataSouce();
	}
}