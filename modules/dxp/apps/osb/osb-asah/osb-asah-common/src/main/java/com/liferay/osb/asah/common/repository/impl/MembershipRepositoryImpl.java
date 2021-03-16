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

import java.util.ArrayList;
import java.util.List;

import org.jooq.AggregateFunction;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.SelectSelectStep;
import org.jooq.impl.DSL;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

/**
 * @author Inácio Nery
 */
@ConditionalOnProperty(
	havingValue = "true", value = "osb.asah.postgresql.enabled"
)
public class MembershipRepositoryImpl {

	public MembershipRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	public List<Long> findIndividualIdByIndividualSegmentIdIn(
		List<Long> individualSegmentIds, int max, int min, boolean ascending) {

		Field<Object> individualSegmentId = DSL.field("individualSegmentId");

		SelectSelectStep<Record1<Object>> select = _dslContext.select(
			individualSegmentId);

		AggregateFunction<Integer> count = DSL.count(
			DSL.field("individualSegmentId"));

		return select.from(
			"Membership"
		).where(
			individualSegmentId.in(individualSegmentIds)
		).groupBy(
			individualSegmentId
		).having(
			_getConditions(max, min, ascending)
		).orderBy(
			ascending ? count.asc() : count.desc()
		).fetch(
			0, Long.class
		);
	}

	private List<Condition> _getConditions(
		int max, int min, boolean ascending) {

		List<Condition> conditions = new ArrayList<>();

		AggregateFunction<Integer> count = DSL.count(
			DSL.field("individualSegmentId"));

		conditions.add(count.ge(min));

		if (ascending) {
			conditions.add(count.le(max));
		}

		return conditions;
	}

	private final DSLContext _dslContext;

}