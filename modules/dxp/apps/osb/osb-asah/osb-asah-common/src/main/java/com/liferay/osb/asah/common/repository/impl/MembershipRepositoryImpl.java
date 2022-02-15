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

import com.liferay.osb.asah.common.entity.Membership;

import java.util.ArrayList;
import java.util.List;

import org.jooq.AggregateFunction;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.SelectSelectStep;
import org.jooq.impl.DSL;

import org.springframework.lang.Nullable;

/**
 * @author Inácio Nery
 */
public class MembershipRepositoryImpl {

	public MembershipRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	public List<Long> findIndividualIdByIndividualSegmentIdIn(
		List<Long> individualSegmentIds, int max, int min, boolean ascending) {

		Field<Object> individualIdField = DSL.field("individualId");

		SelectSelectStep<Record1<Object>> selectSelectStep = _dslContext.select(
			individualIdField);

		Field<Object> individualSegmentIdField = DSL.field(
			"individualSegmentId");

		AggregateFunction<Integer> aggregateFunction = DSL.count(
			individualSegmentIdField);

		return selectSelectStep.from(
			"Membership"
		).where(
			individualSegmentIdField.in(individualSegmentIds)
		).groupBy(
			individualIdField
		).having(
			_getConditions(max, min, ascending)
		).orderBy(
			ascending ? aggregateFunction.asc() : aggregateFunction.desc()
		).fetch(
			0, Long.class
		);
	}

	public List<Long> findIndividualIdByIndividualSegmentIdIn(
		Long individualId, List<Long> individualSegmentIds, int max, int min,
		boolean ascending) {

		Field<Object> individualIdField = DSL.field("individualId");

		SelectSelectStep<Record1<Object>> selectSelectStep = _dslContext.select(
			individualIdField);

		Field<Object> individualSegmentIdField = DSL.field(
			"individualSegmentId");

		return selectSelectStep.from(
			"Membership"
		).where(
			DSL.and(
				individualSegmentIdField.in(individualSegmentIds),
				individualIdField.eq(individualId))
		).groupBy(
			individualIdField
		).having(
			_getConditions(max, min, ascending)
		).fetch(
			0, Long.class
		);
	}

	public List<Membership> searchMemberships(
		@Nullable Long id, Long individualSegmentId, int size, String status) {

		Condition condition = DSL.and(
			DSL.field(
				"individualSegmentId"
			).eq(
				individualSegmentId
			),
			DSL.field(
				"status"
			).eq(
				status
			));

		if (id != null) {
			condition = condition.and(
				DSL.field(
					"id"
				).gt(
					id
				));
		}

		return _dslContext.select(
		).from(
			"Membership"
		).where(
			condition
		).orderBy(
			DSL.field(
				"id"
			).asc()
		).limit(
			size
		).fetch(
			record -> new Membership(record.intoMap())
		);
	}

	private List<Condition> _getConditions(
		int max, int min, boolean ascending) {

		List<Condition> conditions = new ArrayList<>();

		AggregateFunction<Integer> aggregateFunction = DSL.count(
			DSL.field("individualSegmentId"));

		conditions.add(aggregateFunction.ge(min));

		if (ascending) {
			conditions.add(aggregateFunction.le(max));
		}

		return conditions;
	}

	private final DSLContext _dslContext;

}