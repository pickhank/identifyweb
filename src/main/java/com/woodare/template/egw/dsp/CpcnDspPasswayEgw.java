package com.woodare.template.egw.dsp;

import org.springframework.stereotype.Service;

import com.thirdparty.passway.dsp._base.IDspCardApi;
import com.thirdparty.passway.dsp.cpcn.CpcnDspApi;
import com.woodare.template.egw.base.IDspCardPassway;

/**
 * @author Luke
 */
@Service(value = "cpcnDspPasswayEgw")
public class CpcnDspPasswayEgw extends AbstractDspPassway implements IDspCardPassway {

	private CpcnDspApi api = new CpcnDspApi();

	@Override
	public IDspCardApi getApi() {
		return api;
	}

}
