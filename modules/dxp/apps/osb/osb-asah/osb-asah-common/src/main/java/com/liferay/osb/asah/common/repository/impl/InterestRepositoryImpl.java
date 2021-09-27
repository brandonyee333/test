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

import com.liferay.osb.asah.common.entity.Interest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectSelectStep;
import org.jooq.impl.DSL;

/**
 * @author Robson Pastor
 */
public class InterestRepositoryImpl extends BaseRepository {

	public InterestRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	public List<Interest> findByOwnerTypeAndRecordedDate(
		Long interestId, String ownerType, Date recordedDate, int size) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		List<Condition> conditions = _getConditions(
			interestId, null, ownerType, recordedDate);

		return selectSelectStep.from(
			"Interest"
		).where(
			conditions
		).orderBy(
			DSL.field("id")
		).limit(
			size
		).fetch(
		).map(
			this::_toInterest
		);
	}

	private List<Condition> _getConditions(
		Long interestId, Long ownerId, String ownerType, Date recordedDate) {

		List<Condition> conditions = new ArrayList<>();

		if (interestId != null) {
			conditions.add(
				DSL.field(
					"id"
				).gt(
					interestId
				));
		}

		if (ownerId != null) {
			conditions.add(
				DSL.field(
					"ownerId"
				).eq(
					ownerId
				));
		}

		if (ownerType != null) {
			conditions.add(
				DSL.field(
					"ownerType"
				).eq(
					ownerType
				));
		}

		if (recordedDate != null) {
			conditions.add(
				DSL.field(
					"recordedDate"
				).eq(
					recordedDate
				));
		}

		return conditions;
	}

	private Interest _toInterest(Record interestRecord) {
		return new Interest(interestRecord.intoMap());
	}

	private final DSLContext _dslContext;

}