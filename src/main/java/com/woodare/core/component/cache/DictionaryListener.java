package com.woodare.core.component.cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;

import com.woodare.core.constant.CommonConstant;
import com.woodare.core.jpa.data.dicdata.DicDataData;
import com.woodare.core.jpa.model.DicData;
import com.woodare.core.jpa.service.IDicDataDAO;
import com.woodare.core.web.common.viewdata.CodeAndName;
import com.woodare.framework.component.AbstractCacheLoadListener;
import com.woodare.framework.component.CacheChangedListener;
import com.woodare.framework.component.CacheLoadListener;
import com.woodare.framework.data.impl.AbstractData;
import com.woodare.framework.model.data.EnumDeleteStatus;
import com.woodare.framework.model.data.EnumDicExtraType;
import com.woodare.framework.spring.ApplicationContextHolder;
import com.woodare.framework.utils.SaftyBeanUtils;

/**
 * 获取字典表内容
 * 
 * @author lu_feng
 * 
 */
public class DictionaryListener extends AbstractCacheLoadListener implements CacheChangedListener, CacheLoadListener {

    @Override
    public Class<?> getCacheType() {
        return DicDataData.class;
    }

    @Override
    @SuppressWarnings({ "unchecked" })
    public void execute(Map<String, List<? extends AbstractData>> cacheMaps) {
        List<DicDataData> dicDataLst = (List<DicDataData>) cacheMaps.get(DicDataData.class.getName());

        rootDics.clear();
        codeToNameMap.clear();
        codeToChildrenMap.clear();

        if (dicDataLst != null) {
            for (DicDataData dicData : dicDataLst) {
                codeToNameMap.put(dicData.getCode(), dicData.getName());
                codeToDicData.put(dicData.getCode(), dicData);

                if (StringUtils.isEmpty(dicData.getParentCode()) && dicData.getIsValidFlag() != null && dicData.getIsValidFlag()) {

                    DictionaryData item = SaftyBeanUtils.cloneTo(dicData, DictionaryData.class);
                    item.setSortNum(rootDics.size());
                    List<DictionaryData> children = getChildren(dicData.getCode(), dicDataLst);
                    item.setChildren(children);
                    codeToChildrenMap.put(dicData.getCode(), toCodeAndNameList(children));
                    rootDics.add(item);
                }
            }

            codeToChildrenMap.put(CommonConstant.DICTIONRY.CODE_ROOT, toCodeAndNameList(rootDics));
        }
    }
    
    @Override
    public List<? extends AbstractData> doExecute() {
        ApplicationContext application = ApplicationContextHolder.getApplicationContext();
        IDicDataDAO dao = application.getBean(IDicDataDAO.class);

        List<DicData> models = dao.findAll();
        
        return SaftyBeanUtils.cloneToList(models, DicDataData.class);
    }

    private static List<DictionaryData> rootDics = new ArrayList<DictionaryData>();

    private static Map<String, String> codeToNameMap = new HashMap<String, String>();

    private static Map<String, DicDataData> codeToDicData = new HashMap<String, DicDataData>();

    private static Map<String, List<CodeAndName>> codeToChildrenMap = new HashMap<String, List<CodeAndName>>();

    /**
     * 
     * @param dictionaryLst
     * @return
     */
    private List<CodeAndName> toCodeAndNameList(List<DictionaryData> dictionaryLst) {
        List<CodeAndName> codeNames = new ArrayList<CodeAndName>();
        if (dictionaryLst != null) {
            for (DictionaryData d : dictionaryLst) {
                codeNames.add(new CodeAndName(d.getName(), d.getCode()));
            }
        }
        return codeNames;
    }

    /**
     * 
     * @param parentCode
     * @param dicDataLst
     * @return
     */
    private List<DictionaryData> getChildren(String parentCode, List<DicDataData> dicDataLst) {
        if (StringUtils.isEmpty(parentCode)) {
            return null;
        }

        List<DictionaryData> items = new ArrayList<DictionaryData>();

        for (DicDataData dicData : dicDataLst) {
            if (parentCode.equalsIgnoreCase(dicData.getParentCode()) && dicData.getIsValidFlag() != null && dicData.getIsValidFlag()) {
                DictionaryData item = SaftyBeanUtils.cloneTo(dicData, DictionaryData.class);
                List<DictionaryData> children = getChildren(dicData.getCode(), dicDataLst);
                item.setChildren(children);
                codeToChildrenMap.put(dicData.getCode(), toCodeAndNameList(children));
                items.add(item);
            }
        }
        Collections.sort(items, sorter);
        for (int i = 0; i < items.size(); i++) {
            DictionaryData item = items.get(i);
            item.setSortNum(i);
        }
        return items.size() > 0 ? items : null;
    }

    private static final Comparator<DictionaryData> sorter = new Comparator<DictionaryData>() {

        @Override
        public int compare(DictionaryData item1, DictionaryData item2) {
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
     * @param code
     * @return
     */
    public static String getNameByCode(String code) {
        String name = null;
        if (StringUtils.isNotEmpty(code)) {
            String[] cs = code.split(",");
            for (String c : cs) {
                if (name == null) {
                    name = codeToNameMap.get(c);
                } else {
                    name += "," + codeToNameMap.get(c);
                }
            }
        }
        return name;
    }

    /**
     * 
     * @param code
     * @return
     */
    public static String getExtraByCode(String code) {
        String name = "";
        if (StringUtils.isNotEmpty(code)) {
            String[] cs = code.split(",");
            for (String c : cs) {
                if (codeToDicData.get(c) != null && StringUtils.isNotEmpty(codeToDicData.get(c).getExtra())) {
                    name += "," + codeToDicData.get(c).getExtra();
                }
            }
        }
        return StringUtils.isNotEmpty(name) ? name.substring(1) : null;
    }

    /**
     * 
     * @param code
     * @return
     */
    public static String getFirstNameByCode(String code) {
        String name = null;
        if (StringUtils.isNotEmpty(code)) {
            String[] cs = code.split(",");
            for (String c : cs) {
                name = codeToNameMap.get(c);
                break;
            }
        }
        return name;
    }

    /**
     * 
     * @param code
     * @return
     */
    public static List<DictionaryData> getChildrenWithFull(String code) {
        List<DictionaryData> ret = null;
        if (StringUtils.isNotEmpty(code)) {
            for (DictionaryData dictionary : rootDics) {
                if (code.equals(dictionary.getCode())) {
                    ret = dictionary.getChildren();
                    break;
                }
            }
        }
        return ret;
    }

    /**
     * 
     * @param code
     * @return
     */
    public static List<CodeAndName> getChildren(String code) {
        return StringUtils.isNotEmpty(code) ? codeToChildrenMap.get(code) : null;
    }

    public static void main(String[] arg) {

        DicDataData s = new DicDataData();
        s.setName("1001");
        s.setCode("aaaa");
        s.setId("0e853358-cc54-4009-9315-42208f7943a1");
        s.setExtra("F1001");
        s.setExtraType(EnumDicExtraType.NONE);
        s.setParentCode("floor_000");
        s.setIsValidFlag(true);
        s.setSystemMenuFlag(true);
        s.setDeleteStatus(EnumDeleteStatus.VALID);

        DictionaryData item = SaftyBeanUtils.cloneTo(s, DictionaryData.class);
        System.out.println(item.getName());
        System.out.println(item.getCode());
    }

    public static class DictionaryData {
        //
        // /** ID */
        // private String id;

        /** Code */
        private String code;

        /** 名称 */
        private String name;
        //
        // /** 描述信息 */
        // private String descriptions;

        /** 父节点Code */
        private String parentCode;

        /** 排序 */
        private Integer sortNum;

        /** 备用字段 */
        private String extra;

        private List<DictionaryData> children;

        /**
         * @return the extra
         */
        public String getExtra() {
            return extra;
        }

        /**
         * @param extra
         *            the extra to set
         */
        public void setExtra(String extra) {
            this.extra = extra;
        }

        /**
         * @return the code
         */
        public String getCode() {
            return code;
        }

        /**
         * @param code
         *            the code to set
         */
        public void setCode(String code) {
            this.code = code;
        }

        /**
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name
         *            the name to set
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * @return the parentCode
         */
        public String getParentCode() {
            return parentCode;
        }

        /**
         * @param parentCode
         *            the parentCode to set
         */
        public void setParentCode(String parentCode) {
            this.parentCode = parentCode;
        }

        /**
         * @return the sortNum
         */
        public Integer getSortNum() {
            return sortNum;
        }

        /**
         * @param sortNum
         *            the sortNum to set
         */
        public void setSortNum(Integer sortNum) {
            this.sortNum = sortNum;
        }

        /**
         * @return the children
         */
        public List<DictionaryData> getChildren() {
            return children;
        }

        /**
         * @param children
         *            the children to set
         */
        public void setChildren(List<DictionaryData> children) {
            this.children = children;
        }
    }
}
