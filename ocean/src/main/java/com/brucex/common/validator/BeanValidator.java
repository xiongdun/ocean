package com.brucex.common.validator;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * @description JSR303 Validator(Hibernate Validator)工具类.
 * 
 *              ConstraintViolation中包含propertyPath, message 和invalidValue等信息.
 *              提供了各种convert方法，适合不同的i18n需求: 1. List<String>, String内容为message 2.
 *              List<String>, String内容为propertyPath + separator + message 3.
 *              Map<propertyPath, message>
 * 
 *              详情见wiki:
 *              https://github.com/springside/springside4/wiki/HibernateValidator
 * @author xiong
 * @date 2017年6月25日上午11:31:17
 */
public class BeanValidator {
	
	/**
	 * @description 调用JSR303的validate方法, 验证失败时抛出ConstraintViolationException.
	 * @author xiong
	 * @param validator
	 * @param object
	 * @param groups
	 * @throws ConstraintViolationException
	 *             void
	 * @date 2017年6月25日上午11:34:52
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void validateWithException(Validator validator, Object object, Class<?>... groups)
			throws ConstraintViolationException {
		Set constraintViolations = validator.validate(object, groups);
		if (!constraintViolations.isEmpty()) {
			throw new ConstraintViolationException(constraintViolations);
		}
	}

	/**
	 * @description 辅助方法,
	 *              转换ConstraintViolationException中的Set<ConstraintViolations>中为List<message>.
	 * @author xiong
	 * @param e
	 * @return List<String>
	 * @date 2017年6月25日上午11:35:18
	 */
	public static List<String> extractMessage(ConstraintViolationException e) {
		return extractMessage(e.getConstraintViolations());
	}

	/**
	 * @description 辅助方法, 转换Set<ConstraintViolation>为List<message>
	 * @author xiong
	 * @param constraintViolations
	 * @return List<String>
	 * @date 2017年6月25日上午11:35:26
	 */
	@SuppressWarnings("rawtypes")
	public static List<String> extractMessage(Set<? extends ConstraintViolation> constraintViolations) {
		List<String> errorMessages = Lists.newArrayList();
		for (ConstraintViolation violation : constraintViolations) {
			errorMessages.add(violation.getMessage());
		}
		return errorMessages;
	}

	/**
	 * @description 辅助方法,
	 *              转换ConstraintViolationException中的Set<ConstraintViolations>为Map<property,
	 *              message>.
	 * @author xiong
	 * @param e
	 * @return Map<String,String>
	 * @date 2017年6月25日上午11:35:37
	 */
	public static Map<String, String> extractPropertyAndMessage(ConstraintViolationException e) {
		return extractPropertyAndMessage(e.getConstraintViolations());
	}

	/**
	 * @description 辅助方法, 转换Set<ConstraintViolation>为Map<property, message>.
	 * @author xiong
	 * @param constraintViolations
	 * @return Map<String,String>
	 * @date 2017年6月25日上午11:35:44
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, String> extractPropertyAndMessage(
			Set<? extends ConstraintViolation> constraintViolations) {
		Map<String, String> errorMessages = Maps.newHashMap();
		for (ConstraintViolation violation : constraintViolations) {
			errorMessages.put(violation.getPropertyPath().toString(), violation.getMessage());
		}
		return errorMessages;
	}

	/**
	 * @description 辅助方法,
	 *              转换ConstraintViolationException中的Set<ConstraintViolations>为List<propertyPath
	 *              message>.
	 * @author xiong
	 * @param e
	 * @return List<String>
	 * @date 2017年6月25日上午11:35:52
	 */
	public static List<String> extractPropertyAndMessageAsList(ConstraintViolationException e) {
		return extractPropertyAndMessageAsList(e.getConstraintViolations(), " ");
	}

	/**
	 * @description 辅助方法, 转换Set<ConstraintViolations>为List<propertyPath
	 *              message>.
	 * @author xiong
	 * @param constraintViolations
	 * @return List<String>
	 * @date 2017年6月25日上午11:36:00
	 */
	@SuppressWarnings("rawtypes")
	public static List<String> extractPropertyAndMessageAsList(
			Set<? extends ConstraintViolation> constraintViolations) {
		return extractPropertyAndMessageAsList(constraintViolations, " ");
	}

	/**
	 * @description 辅助方法,
	 *              转换ConstraintViolationException中的Set<ConstraintViolations>为List<propertyPath
	 *              +separator+ message>.
	 * @author xiong
	 * @param e
	 * @param separator
	 * @return List<String>
	 * @date 2017年6月25日上午11:36:08
	 */
	public static List<String> extractPropertyAndMessageAsList(ConstraintViolationException e, String separator) {
		return extractPropertyAndMessageAsList(e.getConstraintViolations(), separator);
	}

	/**
	 * @description 辅助方法, 转换Set<ConstraintViolation>为List<propertyPath
	 *              +separator+ message>.
	 * @author xiong
	 * @param constraintViolations
	 * @param separator
	 * @return List<String>
	 * @date 2017年6月25日上午11:36:16
	 */
	@SuppressWarnings("rawtypes")
	public static List<String> extractPropertyAndMessageAsList(Set<? extends ConstraintViolation> constraintViolations,
			String separator) {
		List<String> errorMessages = Lists.newArrayList();
		for (ConstraintViolation violation : constraintViolations) {
			errorMessages.add(violation.getPropertyPath() + separator + violation.getMessage());
		}
		return errorMessages;
	}
}
