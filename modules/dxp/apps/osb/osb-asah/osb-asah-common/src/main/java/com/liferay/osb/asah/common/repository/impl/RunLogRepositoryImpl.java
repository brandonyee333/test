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

package com.liferay.osb.asah.common.repository.impl;

import com.liferay.osb.asah.common.model.RunLog;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectSelectStep;
import org.jooq.impl.DSL;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

/**
 * @author Marcellus Tavares
 */
@ConditionalOnProperty(
	havingValue = "true", value = "osb.asah.postgresql.enabled"
)
public class RunLogRepositoryImpl extends BaseRepository {

	public RunLogRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	public Optional<RunLog>
		findByDataSourceIdAndNaniteClassNameAndStatusOrderByDateLoggedDesc(
			Optional<Long> dataSourceIdOptional, String naniteClassName,
			Optional<String> statusOptional) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return selectSelectStep.from(
			"RunLog"
		).where(
			_getConditions(
				dataSourceIdOptional, naniteClassName, statusOptional)
		).limit(
			1
		).fetchOptional(
		).map(
			record -> new RunLog(record.intoMap())
		);
	}

	private List<Condition> _getConditions(
		Optional<Long> dataSourceIdOptional, String naniteClassName,
		Optional<String> statusOptional) {

		List<Condition> conditions = new ArrayList<>();

		dataSourceIdOptional.ifPresent(
			dataSourceId -> conditions.add(
				DSL.field(
					"dataSourceId"
				).eq(
					dataSourceId
				)));

		conditions.add(
			DSL.field(
				"naniteClassName"
			).eq(
				naniteClassName
			));

		statusOptional.ifPresent(
			status -> conditions.add(
				DSL.field(
					"status"
				).eq(
					status
				)));

		return conditions;
	}

	private final DSLContext _dslContext;

}