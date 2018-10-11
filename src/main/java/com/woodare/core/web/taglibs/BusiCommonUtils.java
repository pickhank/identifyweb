package com.woodare.core.web.taglibs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.woodare.core.jpa.data.dicdata.DicDataData;
import com.woodare.core.jpa.data.systemmenu.SystemMenuData;
import com.woodare.framework.model.data.EnumSystemMenuType;

/**
 * 
 * @author lu_feng
 *
 */
public class BusiCommonUtils {

	/**
	 */
	private static final Comparator<SystemMenuData> menuSorter = new Comparator<SystemMenuData>() {

		@Override
		public int compare(SystemMenuData item1, SystemMenuData item2) {
			return item1.getSortNum().compareTo(item2.getSortNum());
		}
	};
	

	/**
	 * 
	 */
	private static final Comparator<DicDataData> dicSorter = new Comparator<DicDataData>() {

		@Override
		public int compare(DicDataData item1, DicDataData item2) {
			Integer sortNum1 = -1;
			Integer sortNum2 = -1;
			if (item1.getSortNum() != null) {
				sortNum1 = item1.getSortNum();
			}
			if (item2.getSortNum() != null) {
				sortNum2 = item2.getSortNum();
			}

			return sortNum1.compareTo(sortNum2);
		}
	};
	
	/**
	 * 
	 * @return
	 */
	private static <T extends SystemMenuData> List<T> getChildMenu(String menuId, List<T> allMenus) {
		List<T> ret = new ArrayList<T>();
		Map<String, List<T>> subRet = new HashMap<String, List<T>>();
		for(T item : allMenus) {
			if(menuId == null) {
				if(item.getParentMenuId() == null) {
					ret.add(item);
					if(item.getMenuType() != EnumSystemMenuType.ACTION) {
						subRet.put(item.getId(), getChildMenu(item.getId(), allMenus));
					}
					else {
						subRet.put(item.getId(), new ArrayList<T>());
					}
				}
			}
			else if(menuId.equals(item.getParentMenuId())) {
				ret.add(item);
				if(item.getMenuType() != EnumSystemMenuType.ACTION) {
					subRet.put(item.getId(), getChildMenu(item.getId(), allMenus));
				}
				else {
					subRet.put(item.getId(), new ArrayList<T>());
				}
			}
		}

		Collections.sort(ret, menuSorter);
		for (int i = ret.size() - 1; i >= 0; i--) {
			ret.addAll(i + 1, subRet.get(ret.get(i).getId()));
		}
		return ret;
	}
	
	/**
	 * 
	 * @param allMenus
	 * @return
	 */
	public static <T extends SystemMenuData> List<T> sortForMenuManage(List<T> allMenus) {
		return getChildMenu(null, allMenus);
	}
	
	/**
	 * 
	 * @param allMenus
	 * @return
	 */
	public static <T extends DicDataData> List<T> sortForDicDataData(List<T> items) {
		
		if(items != null && items.size() > 0) {
			Collections.sort(items, dicSorter);
		}
		
		return items;
	}
	
	public static void main(String[] args) {
		List<String> aa = new ArrayList<String>();
		aa.add("1");
		aa.add("1");
		aa.add("3");
		
		aa.add(aa.size(), "4");
		System.out.println(aa);
	}
}
