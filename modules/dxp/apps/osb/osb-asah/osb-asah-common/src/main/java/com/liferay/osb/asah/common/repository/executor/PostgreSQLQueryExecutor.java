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

import org.jooq.Query;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Result;
import org.jooq.SelectFinalStep;

import org.springframework.stereotype.Component;

/**
 * @author Matthew Kong
 */
@Component
@ConditionalOnGoogleApplicationCredentials(matchIfMissing = true)
public class PostgreSQLQueryExecutor implements QueryExecutor {

	@Override
	public void queryExecute(Query query) {
		query.execute();
	}

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
		Function<Map<String, Object>, T> rowMapperFunction,
		SelectFinalStep<? extends Record> selectFinalStep) {

		Result<? extends Record> result = selectFinalStep.fetch();

		return result.map(record -> rowMapperFunction.apply(record.intoMap()));
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
	public Map<String, Object> queryForMap(
		SelectFinalStep<? extends Record> selectFinalStep) {

		return selectFinalStep.fetchOneMap();
	}

	@Override
	public <T> Optional<T> queryForObject(
		Function<Map<String, Object>, T> rowMapperFunction,
		SelectFinalStep<Record> selectFinalStep) {

		return Optional.ofNullable(
			selectFinalStep.fetchAny(
				record -> rowMapperFunction.apply(record.intoMap())));
	}

}