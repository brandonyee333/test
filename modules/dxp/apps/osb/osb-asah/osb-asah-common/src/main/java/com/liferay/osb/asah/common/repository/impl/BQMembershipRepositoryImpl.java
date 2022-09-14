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

import com.liferay.osb.asah.common.entity.BQMembership;
import com.liferay.osb.asah.common.repository.CustomBQMembershipRepository;

import java.util.ArrayList;
import java.util.List;

import org.jooq.AggregateFunction;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.DeleteUsingStep;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectSelectStep;
import org.jooq.impl.DSL;

import org.springframework.lang.Nullable;

/**
 * @author Inácio Nery
 */
public class BQMembershipRepositoryImpl
	implements CustomBQMembershipRepository {

	public BQMembershipRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	@Override
	public void deleteBySegmentIdIn(List<Long> segmentIds) {
		Field<Object> segmentIdField = DSL.field("segmentId");

		DeleteUsingStep<Record> deleteUsingStep = _dslContext.deleteFrom(
			DSL.table("BQMembership"));

		deleteUsingStep.where(
			segmentIdField.in(segmentIds)
		).execute();
	}

	@Override
	public List<Long> findSegmentIdByStatusAndUserId(
		String status, String userId) {

		Field<Object> segmentIdField = DSL.field("segmentId");

		SelectSelectStep<Record1<Object>> selectSelectStep = _dslContext.select(
			segmentIdField);

		Field<Object> statusField = DSL.field("status");
		Field<Object> userIdField = DSL.field("userId");

		return selectSelectStep.from(
			"BQMembership"
		).where(
			statusField.eq(status)
		).and(
			userIdField.eq(userId)
		).fetch(
			0, Long.class
		);
	}

	@Override
	public List<Long> findSegmentIdByStatusAndUserIdIn(
		String status, List<String> userIds) {

		Field<Object> segmentIdField = DSL.field("segmentId");

		SelectSelectStep<Record1<Object>> selectSelectStep =
			_dslContext.selectDistinct(segmentIdField);

		Field<Object> userIdField = DSL.field("userId");
		Field<Object> statusField = DSL.field("status");

		return selectSelectStep.from(
			"BQMembership"
		).where(
			statusField.eq(status)
		).and(
			userIdField.in(userIds)
		).fetch(
			0, Long.class
		);
	}

	@Override
	public List<Long> findTop20SegmentIdByUserId(String userId) {
		Field<Object> segmentIdField = DSL.field("segmentId");

		SelectSelectStep<Record1<Object>> selectSelectStep = _dslContext.select(
			segmentIdField);

		Field<Object> userIdIdField = DSL.field("userId");

		return selectSelectStep.from(
			"BQMembership"
		).where(
			userIdIdField.eq(userId)
		).groupBy(
			segmentIdField
		).orderBy(
			DSL.count(
				userIdIdField
			).desc()
		).limit(
			20
		).fetch(
			0, Long.class
		);
	}

	@Override
	public List<String> findUserIdBySegmentIdAndStatus(
		Long segmentId, String status) {

		Field<Object> userIdField = DSL.field("userId");

		SelectSelectStep<Record1<Object>> selectSelectStep = _dslContext.select(
			userIdField);

		Field<Object> segmentIdField = DSL.field("segmentId");
		Field<Object> statusField = DSL.field("status");

		return selectSelectStep.from(
			"BQMembership"
		).where(
			segmentIdField.eq(segmentId)
		).and(
			statusField.eq(status)
		).fetch(
			0, String.class
		);
	}

	@Override
	public List<String> findUserIdBySegmentIdIn(
		List<Long> segmentIds, int max, int min, boolean ascending) {

		Field<Object> userIdField = DSL.field("userId");

		SelectSelectStep<Record1<Object>> selectSelectStep = _dslContext.select(
			userIdField);

		Field<Object> segmentIdField = DSL.field("segmentId");

		AggregateFunction<Integer> aggregateFunction = DSL.count(
			segmentIdField);

		return selectSelectStep.from(
			"BQMembership"
		).where(
			segmentIdField.in(segmentIds)
		).groupBy(
			userIdField
		).having(
			_getConditions(max, min, ascending)
		).orderBy(
			ascending ? aggregateFunction.asc() : aggregateFunction.desc()
		).fetch(
			0, String.class
		);
	}

	@Override
	public List<BQMembership> searchBQMemberships(
		@Nullable Long id, Long segmentId, int size, String status) {

		Condition condition = DSL.and(
			DSL.field(
				"segmentId"
			).eq(
				segmentId
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
			"BQMembership"
		).where(
			condition
		).orderBy(
			DSL.field(
				"id"
			).asc()
		).limit(
			size
		).fetch(
			record -> new BQMembership(record.intoMap())
		);
	}

	private List<Condition> _getConditions(
		int max, int min, boolean ascending) {

		List<Condition> conditions = new ArrayList<>();

		AggregateFunction<Integer> aggregateFunction = DSL.count(
			DSL.field("segmentId"));

		conditions.add(aggregateFunction.ge(min));

		if (ascending) {
			conditions.add(aggregateFunction.le(max));
		}

		return conditions;
	}

	private final DSLContext _dslContext;

}