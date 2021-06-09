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

import com.liferay.osb.asah.common.entity.RunLog;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectSelectStep;
import org.jooq.impl.DSL;

import org.springframework.lang.Nullable;

/**
 * @author Marcellus Tavares
 */
public class RunLogRepositoryImpl extends BaseRepository {

	public RunLogRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	public Optional<RunLog>
		findByDataSourceIdAndNaniteClassNameAndStatusOrderByDateLoggedDesc(
			@Nullable Long dataSourceId, String naniteClassName,
			@Nullable String status) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return selectSelectStep.from(
			"RunLog"
		).where(
			_getConditions(dataSourceId, naniteClassName, status)
		).orderBy(
			DSL.field(
				"dateLogged"
			).desc()
		).limit(
			1
		).fetchOptional(
		).map(
			record -> new RunLog(record.intoMap())
		);
	}

	private List<Condition> _getConditions(
		Long dataSourceId, String naniteClassName, String status) {

		List<Condition> conditions = new ArrayList<>();

		if (dataSourceId != null) {
			conditions.add(
				DSL.field(
					"dataSourceId"
				).eq(
					dataSourceId
				));
		}

		conditions.add(
			DSL.field(
				"naniteClassName"
			).eq(
				naniteClassName
			));

		if (StringUtils.isNotBlank(status)) {
			conditions.add(
				DSL.field(
					"status"
				).eq(
					status
				));
		}

		return conditions;
	}

	private final DSLContext _dslContext;

}