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

import com.liferay.osb.asah.common.spring.annotation.ConditionalOnGoogleApplicationCredentials;

import java.math.BigDecimal;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectFinalStep;

import org.springframework.stereotype.Component;

/**
 * @author Matthew Kong
 */
@Component
@ConditionalOnGoogleApplicationCredentials(matchIfMissing = true)
public class PostgreSQLQueryExecutor implements QueryExecutor {

	@Override
	public BigDecimal queryForBigDecimal(
		SelectFinalStep<Record1<Number>> selectFinalStep) {

		return selectFinalStep.fetchOptional(
			0, BigDecimal.class
		).orElse(
			BigDecimal.ZERO
		);
	}

	@Override
	public <T> List<T> queryForList(
		Class<T> clazz, SelectFinalStep<Record> selectFinalStep) {

		return selectFinalStep.fetch(
			record -> getObject(getConstructor(clazz), record.intoMap()));
	}

	public <T> List<T> queryForList(
		SelectFinalStep<Record1<T>> selectFinalStep) {

		return selectFinalStep.fetch(Record1::value1);
	}

	@Override
	public long queryForLong(
		SelectFinalStep<Record1<Integer>> selectFinalStep) {

		return selectFinalStep.fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	public <T, R> Map<T, R> queryForMap(
		Function<Object, T> keyMapperFunction,
		SelectFinalStep<? extends Record> selectFinalStep,
		Function<Object, R> valueMapperFunction) {

		Map<T, R> map = new LinkedHashMap<>();

		selectFinalStep.fetch(
		).forEach(
			record -> map.put(
				keyMapperFunction.apply(record.get(0)),
				valueMapperFunction.apply(record.get(1)))
		);

		return map;
	}

	@Override
	public <T, R> Map<T, R> queryForMap(
		SelectFinalStep<? extends Record> selectFinalStep) {

		Map<T, R> map = new LinkedHashMap<>();

		selectFinalStep.fetch(
		).forEach(
			record -> map.put((T)record.get(0), (R)record.get(1))
		);

		return map;
	}

	@Override
	public <T> Optional<T> queryForObject(
		Class<T> clazz, SelectFinalStep<Record> selectFinalStep) {

		return Optional.ofNullable(
			selectFinalStep.fetchAny(
				record -> getObject(getConstructor(clazz), record.intoMap())));
	}

}