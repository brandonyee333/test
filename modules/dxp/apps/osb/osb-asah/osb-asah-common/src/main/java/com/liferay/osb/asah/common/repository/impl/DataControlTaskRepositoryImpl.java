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

import com.liferay.osb.asah.common.entity.DataControlTask;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectSelectStep;
import org.jooq.impl.DSL;

import org.springframework.data.domain.Pageable;

/**
 * @author Marcellus Tavares
 */
public class DataControlTaskRepositoryImpl extends BaseRepository {

	public DataControlTaskRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	public long countDataControlTasks(
		Long batchId, String emailAddress, Date startCreateDate,
		List<String> statuses, List<String> types) {

		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.selectCount();

		return selectSelectStep.from(
			"DataControlTask"
		).where(
			_getConditions(
				batchId, emailAddress, startCreateDate, statuses, types)
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	public List<DataControlTask> searchDataControlTasks(
		Long batchId, String emailAddress, Date startCreateDate,
		List<String> statuses, List<String> types, Pageable pageable) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return selectSelectStep.from(
			"DataControlTask"
		).where(
			_getConditions(
				batchId, emailAddress, startCreateDate, statuses, types)
		).orderBy(
			getSortFields(pageable.getSort(), null)
		).limit(
			pageable.getPageSize()
		).offset(
			pageable.getOffset()
		).fetch(
		).map(
			record -> new DataControlTask(record.intoMap())
		);
	}

	private List<Condition> _getConditions(
		Long batchId, String emailAddress, Date startCreateDate,
		List<String> statuses, List<String> types) {

		List<Condition> conditions = new ArrayList<>();

		if (batchId != null) {
			conditions.add(
				DSL.field(
					"batchId"
				).eq(
					batchId
				));
		}

		if (!StringUtils.isBlank(emailAddress)) {
			conditions.add(
				DSL.field(
					"emailAddress"
				).containsIgnoreCase(
					emailAddress
				));
		}

		if (startCreateDate != null) {
			conditions.add(
				DSL.field(
					"createDate"
				).greaterOrEqual(
					startCreateDate
				));
		}

		if ((statuses != null) && !statuses.isEmpty()) {
			conditions.add(
				DSL.field(
					"status"
				).in(
					statuses
				));
		}

		if ((types != null) && !types.isEmpty()) {
			conditions.add(
				DSL.field(
					"type"
				).in(
					types
				));
		}

		return conditions;
	}

	private final DSLContext _dslContext;

}