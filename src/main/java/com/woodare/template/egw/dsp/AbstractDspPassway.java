package com.woodare.template.egw.dsp;

import com.thirdparty.passway._data.PasswayDspRequestData;
import com.thirdparty.passway._data.PasswayDspResponseData;
import com.thirdparty.passway.dsp._base.IDspCardApi;
import com.woodare.framework.exception.AbstractWooException;
import com.woodare.template.egw.base.IDspCardPassway;

/**
 * @author Luke
 */
public abstract class AbstractDspPassway implements IDspCardPassway {

	public abstract IDspCardApi getApi();

	@Override
	public PasswayDspResponseData processC102(PasswayDspRequestData reqData) throws AbstractWooException {
		return getApi().processC102(reqData);
	}

	@Override
	public PasswayDspResponseData processC112(PasswayDspRequestData reqData) throws AbstractWooException {
		return getApi().processC112(reqData);
	}

	@Override
	public PasswayDspResponseData processC115(PasswayDspRequestData reqData) throws AbstractWooException {
		return getApi().processC115(reqData);
	}

	@Override
	public PasswayDspResponseData processC116(PasswayDspRequestData reqData) throws AbstractWooException {
		return getApi().processC116(reqData);
	}

	@Override
	public PasswayDspResponseData processC123(PasswayDspRequestData reqData) throws AbstractWooException {
		return getApi().processC123(reqData);
	}

}
