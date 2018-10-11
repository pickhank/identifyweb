package com.woodare.core.web.taglibs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.woodare.core.component.cache.DictionaryListener;
import com.woodare.core.web.common.viewdata.CodeAndName;

/**
 * 
 * @author lu_feng
 *
 */
public class DicTaglibHelper {

	/**
	 * 
	 * @return
	 */
	public static List<CodeAndName> getOptions(String parentCode) { 
		List<CodeAndName> ret = new ArrayList<CodeAndName>();
		if(StringUtils.hasLength(parentCode)) {
			String[] codes = parentCode.split(",");
			for(String code : codes) {
				List<CodeAndName> codeNames = DictionaryListener.getChildren(code);
				if(codeNames != null) {
					ret.addAll(codeNames);
				}
			}
		}
		return ret;
	}
}
