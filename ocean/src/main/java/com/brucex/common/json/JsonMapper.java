/**
 * 
 */
package com.brucex.common.json;

import java.io.IOException;
import java.util.TimeZone;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.log4j.Logger;

import com.brucex.common.utils.StringUtils;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * @description JsonHelper.java json 处理工具类
 * 				简单封装Jackson，实现JSON String<->Java Object的Mapper.
 * @author xiong
 * @date 2017年6月25日下午4:44:43
 */
public class JsonMapper extends ObjectMapper {

	private static final long serialVersionUID = 493687532534241365L;
	
	private static Logger logger = Logger.getLogger(JsonMapper.class);
	
	private static JsonMapper mapper;
	
	public JsonMapper() {
		this(Include.NON_EMPTY);
	}
	
	/**
	 * @param nonEmpty
	 */
	public JsonMapper(Include include) {
		// 设置输出时包含属性的风格
		if (include != null) {
			this.setSerializationInclusion(include);
		}
		// 允许单引号、允许不带引号的字段名称
		this.enableSimple();
		// 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
		this.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // 空值处理为空串
		this.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>(){
			@Override
			public void serialize(Object value, JsonGenerator jgen,
					SerializerProvider provider) throws IOException,
					JsonProcessingException {
				jgen.writeString("");
			}
        });
		// 进行HTML解码。
		this.registerModule(new SimpleModule().addSerializer(String.class, new JsonSerializer<String>(){
			@Override
			public void serialize(String value, JsonGenerator jgen,
					SerializerProvider provider) throws IOException,
					JsonProcessingException {
				jgen.writeString(StringEscapeUtils.unescapeHtml4(value));
			}
        }));
		// 设置时区
		this.setTimeZone(TimeZone.getDefault());//getTimeZone("GMT+8:00")
	}
	
	/**
	 * @description 创建只输出非Null且非Empty(如List.isEmpty)的属性到Json字符串的Mapper,建议在外部接口中使用.
	 * @author xiong
	 * @return JsonMapper
	 * @date 2017年6月25日下午9:33:23
	 */
	public static JsonMapper getInstance() {
		if (mapper == null) {
			mapper = new JsonMapper().enableSimple();
		}
		return mapper;
	}
	
	/**
	 * @description 创建只输出初始值被改变的属性到Json字符串的Mapper, 最节约的存储方式，建议在内部接口中使用。
	 * @author xiong
	 * @return JsonMapper
	 * @date 2017年6月25日下午9:34:16
	 */
	public static JsonMapper nonDefaultMapper() {
		if (mapper == null) {
			mapper = new JsonMapper(Include.NON_DEFAULT);
		}
		return mapper;
	}
	
	/**
	 * @description Object可以是POJO，也可以是Collection或数组。
	 * 				如果对象为Null, 返回"null".
	 * 				如果集合为空集合, 返回"[]".
	 * @author xiong
	 * @param object
	 * @return String
	 * @date 2017年6月25日下午9:35:28
	 */
	public String toJson(Object object) {
		try {
			return this.writeValueAsString(object);
		} catch (IOException e) {
			logger.warn("write to json string error:" + object, e);
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public <T> T update(String jsonString, T object) {
		try {
			return (T) this.readerForUpdating(object).readValue(jsonString);
		} catch (JsonProcessingException e) {
			logger.warn("update json string:" + jsonString + " to object:" + object + " error.", e);
		} catch (IOException e) {
			logger.warn("update json string:" + jsonString + " to object:" + object + " error.", e);
		}
		return null;
	}

	/**
	 * @description 输出Json 格式的数据
	 * @author xiong
	 * @return String
	 * @date 2017年6月25日下午9:26:28
	 */
	public String toJsonData(String functionName, Object object) {
		return toJsonString(object);
	}
	
	/**
	 * @description 设定是否使用Enum 的toString() 方法来读写Enum,
	 * 				为False 时使用Enum 的name() 方法来读写Enum， 默认为false.
	 * 				需要注意本方法一定要在Mapper创建后，所有的读写动作调用之前调用
	 * @author xiong
	 * @return JsonMapper
	 * @date 2017年6月25日下午9:23:31
	 */
	public JsonMapper enableEnumUseToString() {
		this.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
		this.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
		return this;
	}
	
	/**
	 * @description 支持使用Jaxb的Annotation，使得POJO上的annotation不用与Jackson耦合。
	 * 默认会先查找jaxb的annotation，如果找不到再找jackson的。
	 * @author xiong
	 * @return JsonMapper
	 * @date 2017年6月25日下午9:22:04
	 */
	public JsonMapper enableJaxbAnnotation() {
		//JaxbAnnotationModule
		return null;
	}
	
	/**
	 * @description 允许单引号
	 * 				允许不带引号的字段名称
	 * @author xiong
	 * @return JsonMapper
	 * @date 2017年6月25日下午9:17:15
	 */
	public JsonMapper enableSimple() {
		this.configure(Feature.ALLOW_SINGLE_QUOTES, true);
		this.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		return this;
	}
	
	/**
	 * @description 取出Mapper做进一步的设置或使用其他序列化API.
	 * @author xiong
	 * @return ObjectMapper
	 * @date 2017年6月25日下午9:18:16
	 */
	public ObjectMapper getMapper() {
		return this;
	}
	
	/**
	 * @description 对象转换为JSON字符串
	 * @author xiong
	 * @param object
	 * @return String
	 * @date 2017年6月25日下午9:18:39
	 */
	public static String toJsonString(Object object) {
		return JsonMapper.getInstance().toJson(object);
	}
	
	/**
	 * @description 反序列化POJO或简单Collection如List<String>.
	 * 				如果JSON字符串为Null或"null"字符串, 返回Null.
	 * 				如果JSON字符串为"[]", 返回空集合.
	 * 				如需反序列化复杂Collection如List<MyBean>, 请使用fromJson(String,JavaType)
	 * @author xiong
	 * @param jsonString
	 * @param clazz
	 * @return T
	 * @date 2017年6月25日下午9:42:07
	 */
	@SuppressWarnings("unchecked")
	public <T> T fromJson(String jsonString, Class<?> clazz) {
		if (StringUtils.isEmpty(jsonString)) {
			return null;
		}
		try {
			return (T) this.readValue(jsonString, clazz);
		} catch (IOException e) {
			logger.warn("parse json string error:" + jsonString, e);
			return null;
		}
	}
	
	/**
	 * @description 反序列化复杂Collection如List<Bean>, 
	 * 				先使用函數createCollectionType构造类型,然后调用本函数.
	 * @author xiong
	 * @param jsonString
	 * @param javaType
	 * @return T
	 * @date 2017年6月25日下午9:42:44
	 */
	@SuppressWarnings("unchecked")
	public <T> T fromJson(String jsonString, JavaType javaType) {
		if (StringUtils.isEmpty(jsonString)) {
			return null;
		}
		try {
			return (T) this.readValue(jsonString, javaType);
		} catch (IOException e) {
			logger.warn("parse json string error:" + jsonString, e);
			return null;
		}
	}
	
	/**
	 * @description 構造泛型的Collection Type如:
	 * ArrayList<MyBean>, 则调用constructCollectionType(ArrayList.class,MyBean.class)
	 * HashMap<String,MyBean>, 则调用(HashMap.class,String.class, MyBean.class)
	 * @author xiong
	 * @param collectionClass
	 * @param elementClasses
	 * @return JavaType
	 * @date 2017年6月25日下午9:44:10
	 */
	@SuppressWarnings("deprecation")
	public JavaType createCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
		return this.getTypeFactory().constructParametricType(collectionClass, elementClasses);
	}
	
	/**
	 * @description JSON字符串转换为对象
	 * @author xiong
	 * @param jsonString
	 * @param clazz
	 * @return Object
	 * @date 2017年6月25日下午9:19:56
	 */
	public static Object fromJsonString(String jsonString, Class<?> clazz) {
		return JsonMapper.getInstance().fromJson(jsonString, clazz);
	}
}
