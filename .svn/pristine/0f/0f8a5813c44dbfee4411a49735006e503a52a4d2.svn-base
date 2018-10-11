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
package com.woodare.framework.utils;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.woodare.framework.data.impl.PagedList;

/**
 * 
 * ClassName: JsonUtils
 * 
 * @description
 * @author framework
 * @Date 2013-4-1
 * 
 */
@SuppressWarnings("rawtypes")
public class JsonUtils {

    private static Map<Class<?>, Object> SERIALIZER;
    private static Map<Class<?>, Object> DESERIALIZER;

    private static class LogExclStrat implements ExclusionStrategy {

        public boolean shouldSkipClass(Class<?> arg0) {
            return false;
        }

        public boolean shouldSkipField(FieldAttributes f) {
            return f.getDeclaredClass() == org.apache.commons.logging.Log.class || f.getAnnotation(JsonIgnore.class) != null;
        }

    }

    static {
        DESERIALIZER = new HashMap<Class<?>, Object>();
        DESERIALIZER.put(Date.class, new JsonDeserializer<Date>() {
            @Override
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

                return json == null ? null : new Date(json.getAsLong());
            }
        });
        DESERIALIZER.put(java.sql.Date.class, new JsonDeserializer<Date>() {
            @Override
            public java.sql.Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

                return json == null ? null : new java.sql.Date(json.getAsLong());
            }
        });
        DESERIALIZER.put(PagedList.class, new JsonDeserializer<PagedList>() {
            @SuppressWarnings("unchecked")
            @Override
            public PagedList deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                PagedList rel = null;
                if (json != null) {
                    rel = new PagedList();
                    JsonObject pagedObj = json.getAsJsonObject();

                    if (pagedObj.get("totalSize") != null) {
                        rel.setTotalSize(pagedObj.get("totalSize").getAsLong());
                    }
                    if (pagedObj.get("pageSize") != null) {
                        rel.setPageSize(pagedObj.get("pageSize").getAsInt());
                    }
                    if (pagedObj.get("pageIndex") != null) {
                        rel.setPageIndex(pagedObj.get("pageIndex").getAsInt());
                    }
                    JsonElement classObject = pagedObj.get("itemClass");
                    JsonElement items = pagedObj.get("items");
                    if (items.isJsonArray()) {
                        try {
                            Class<?> objClass = Class.forName(classObject.getAsString());

                            JsonArray arr = pagedObj.get("items").getAsJsonArray();

                            Iterator<JsonElement> iterators = arr.iterator();
                            while (iterators.hasNext()) {
                                JsonElement ele = iterators.next();
                                rel.add(JsonUtils.fromJson(ele.getAsString(), objClass));
                            }
                        } catch (ClassNotFoundException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
                return rel;
            }
        });

        SERIALIZER = new HashMap<Class<?>, Object>();
        SERIALIZER.put(Date.class, new JsonSerializer<Date>() {
            @Override
            public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
                return src == null ? null : new JsonPrimitive(src.getTime());
            }
        });
        SERIALIZER.put(java.sql.Date.class, new JsonSerializer<java.sql.Date>() {
            @Override
            public JsonElement serialize(java.sql.Date src, Type typeOfSrc, JsonSerializationContext context) {
                return src == null ? null : new JsonPrimitive(src.getTime());
            }
        });
        SERIALIZER.put(PagedList.class, new JsonSerializer<PagedList>() {
            @Override
            public JsonElement serialize(PagedList src, Type typeOfSrc, JsonSerializationContext context) {
                JsonObject rel = null;
                if (src != null) {
                    rel = new JsonObject();
                    if (src.getTotalSize() != null) {
                        rel.add("totalSize", new JsonPrimitive(src.getTotalSize()));
                    }
                    if (src.getPageIndex() != null) {
                        rel.add("pageIndex", new JsonPrimitive(src.getPageIndex()));
                    }
                    if (src.getPageSize() != null) {
                        rel.add("pageSize", new JsonPrimitive(src.getPageSize()));
                    }
                    if (StringUtils.isNotEmpty(src.getExtraValue())) {
                        rel.add("extraValue", new JsonPrimitive(src.getExtraValue()));
                    }
                    JsonArray arr = null;
                    if (src.size() != 0) {
                        arr = new JsonArray();
                        for (Object obj : src) {
                            String eleStr = JsonUtils.toJson(obj);
                            JsonParser jsonParser = new JsonParser();
                            arr.add(jsonParser.parse(eleStr));
                        }
                    }
                    rel.add("items", arr);
                }
                return rel;
            }
        });
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        return fromJson(json, classOfT, DESERIALIZER);
    }

    public static <T> T fromJson(String json, Type type) {
        return fromJson(json, type, DESERIALIZER);
    }

    public static <T> T fromJson(String json, Type type, Map<Class<?>, Object> adapters) {
    	if(json == null || json.equals("")) {
    		return null;
    	}
        Gson gson = getGson(adapters);
        return gson.fromJson(json, type);
    }

    public static <T> T fromJson(String json, Class<T> classOfT, Map<Class<?>, Object> adapters) {
        Gson gson = getGson(adapters);
        return gson.fromJson(json, classOfT);
    }

    public static String toJson(Object jsonElement) {
        return toJson(jsonElement, SERIALIZER);
    }

    public static String toJson(Object jsonElement, Type type) {
        Gson gson = getGson(SERIALIZER);
        return gson.toJson(jsonElement, type);
    }

    public static String toJson(Object jsonElement, Map<Class<?>, Object> adapters) {
        Gson gson = getGson(adapters);
        return gson.toJson(jsonElement);
    }

    public static Gson getGson(Map<Class<?>, Object> adapters) {
        Gson gson = null;
        if (adapters != null) {
            GsonBuilder gsonBuilder = new GsonBuilder(); // If want to show null
                                                         // field add the
                                                         // following:
                                                         // .serializeNulls();
            for (Map.Entry<Class<?>, Object> entry : adapters.entrySet()) {
                gsonBuilder.registerTypeAdapter(entry.getKey(), entry.getValue());
            }
            gson = gsonBuilder.setExclusionStrategies(new LogExclStrat()).create();
        } else {
            gson = new GsonBuilder().setExclusionStrategies(new LogExclStrat()).create(); // .serializeNulls()
        }
        return gson;
    }

    public static void main(String[] args) {
    	 // new TypeToken<List<Student>>() {}.getType()
         
        JsonTestVO2 testvo2 = new JsonTestVO2();
        testvo2.setName("nm");
        testvo2.setTest("ddd");
        
        System.out.println(JsonUtils.toJson(testvo2));
        
        JsonTestVO testVO = JsonUtils.fromJson(JsonUtils.toJson(testvo2), JsonTestVO.class);

        System.out.println(JsonUtils.toJson(testVO));

//        
//
//        
//        JsonTestVO testVO = new JsonTestVO();
//        testVO.setIgnoreFields("fields");
//        testVO.setTest("this is test");
//        System.out.println("========================");
//        Gson gson = new Gson();
//        B b = new B();
//
//        System.out.println(new GsonBuilder().serializeNulls().create().toJson(b));
//
//        b.setDate(new Date());
//        b.setA("a");
//        b.setB("b");
//        A a = b;
//        System.out.println(gson.toJson(a));
//
//        String resp = JsonUtils.toJson(b);
//        System.out.println(resp);
//
//        PagedList<JsonTestVO> items = new PagedList<JsonTestVO>();
//        items.setTotalSize(20L);
//        JsonTestVO c = new JsonTestVO();
//        c.setTest("test");
//        items.add(c);
//
//        D d = new D();
//        d.setItems(items);
//
//        String tmp = JsonUtils.toJson(d);
//        System.out.println(tmp);
    }

    public static interface A {
        String getA();
    }

    public static class JsonTestVO2 {
        private String test;
        private String name;
        
        /**
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name the name to set
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * @return the test
         */
        public String getTest() {
            return test;
        }

        /**
         * @param test
         *            the test to set
         */
        public void setTest(String test) {
            this.test = test;
        }
    }

    public static class JsonTestVO {
        private String test;

        /** */
        @JsonIgnore
        private String ignoreFields = "123";

        /**
         * @return the ignoreFields
         */
        public String getIgnoreFields() {
            return ignoreFields;
        }

        /**
         * @param ignoreFields
         *            the ignoreFields to set
         */
        public void setIgnoreFields(String ignoreFields) {
            this.ignoreFields = ignoreFields;
        }

        /**
         * @return the test
         */
        public String getTest() {
            return test;
        }

        /**
         * @param test
         *            the test to set
         */
        public void setTest(String test) {
            this.test = test;
        }

    }

    public static class D extends B<JsonTestVO> {

    }

    public static class B<T> implements A {
        private Date date;
        private String a;
        private String b;
        private PagedList<T> items;

        /**
         * @return the items
         */
        public PagedList<T> getItems() {
            return items;
        }

        /**
         * @param items
         *            the items to set
         */
        public void setItems(PagedList<T> items) {
            this.items = items;
        }

        @Override
        public String getA() {

            return a;
        }

        /**
         * @return the date
         */
        public Date getDate() {
            return date;
        }

        /**
         * @param date
         *            the date to set
         */
        public void setDate(Date date) {
            this.date = date;
        }

        /**
         * @return the b
         */
        public String getB() {
            return b;
        }

        /**
         * @param b
         *            the b to set
         */
        public void setB(String b) {
            this.b = b;
        }

        /**
         * @param a
         *            the a to set
         */
        public void setA(String a) {
            this.a = a;
        }

    }
}
