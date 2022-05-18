/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.asah.common.repository.executor;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import java.math.BigDecimal;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectFinalStep;

/**
 * @author Matthew Kong
 */
public interface QueryExecutor {

	public default <T> Constructor<T> getConstructor(Class<T> clazz) {
		try {
			return clazz.getConstructor(Map.class);
		}
		catch (NoSuchMethodException noSuchMethodException) {
			throw new RuntimeException(noSuchMethodException);
		}
	}

	public default <T> T getObject(
		Constructor<T> constructor, Map<String, Object> objectMap) {

		try {
			return constructor.newInstance(objectMap);
		}
		catch (IllegalAccessException | InstantiationException |
			   InvocationTargetException exception) {

			throw new RuntimeException(exception);
		}
	}

	public BigDecimal queryForBigDecimal(
		SelectFinalStep<Record1<Number>> selectFinalStep);

	public <T> List<T> queryForList(
		Class<T> clazz, SelectFinalStep<Record> selectFinalStep);

	public <T> List<T> queryForList(
		SelectFinalStep<Record1<T>> selectFinalStep);

	public long queryForLong(SelectFinalStep<Record1<Integer>> selectFinalStep);

	public <T, R> Map<T, R> queryForMap(
		Function<Object, T> keyMapperFunction,
		SelectFinalStep<? extends Record> selectFinalStep,
		Function<Object, R> valueMapperFunction);

	public <T, R> Map<T, R> queryForMap(
		SelectFinalStep<? extends Record> selectFinalStep);

	public <T> Optional<T> queryForObject(
		Class<T> clazz, SelectFinalStep<Record> selectFinalStep);

}