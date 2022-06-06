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
	public List<String> findIdentityIdByIdentityIdInAndSegmentIdAndStatus(
		List<String> identityIds, Long segmentId, String status) {

		Field<Object> identityIdField = DSL.field("identityId");

		SelectSelectStep<Record1<Object>> selectSelectStep = _dslContext.select(
			identityIdField);

		Field<Object> segmentIdField = DSL.field("segmentId");
		Field<Object> statusField = DSL.field("status");

		return selectSelectStep.from(
			"BQMembership"
		).where(
			identityIdField.in(identityIds)
		).and(
			segmentIdField.eq(segmentId)
		).and(
			statusField.eq(status)
		).fetch(
			0, String.class
		);
	}

	@Override
	public List<String> findIdentityIdBySegmentIdAndStatus(
		Long segmentId, String status) {

		Field<Object> identityIdField = DSL.field("identityId");

		SelectSelectStep<Record1<Object>> selectSelectStep = _dslContext.select(
			identityIdField);

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
	public List<String> findIdentityIdBySegmentIdIn(
		List<Long> segmentIds, int max, int min, boolean ascending) {

		Field<Object> identityIdField = DSL.field("identityId");

		SelectSelectStep<Record1<Object>> selectSelectStep = _dslContext.select(
			identityIdField);

		Field<Object> segmentIdField = DSL.field("segmentId");

		AggregateFunction<Integer> aggregateFunction = DSL.count(
			segmentIdField);

		return selectSelectStep.from(
			"BQMembership"
		).where(
			segmentIdField.in(segmentIds)
		).groupBy(
			identityIdField
		).having(
			_getConditions(max, min, ascending)
		).orderBy(
			ascending ? aggregateFunction.asc() : aggregateFunction.desc()
		).fetch(
			0, String.class
		);
	}

	@Override
	public List<String> findIdentityIdBySegmentIdIn(
		String identityId, List<Long> segmentIds, int max, int min,
		boolean ascending) {

		Field<Object> identityIdField = DSL.field("identityId");

		SelectSelectStep<Record1<Object>> selectSelectStep = _dslContext.select(
			identityIdField);

		Field<Object> segmentIdField = DSL.field("segmentId");

		return selectSelectStep.from(
			"BQMembership"
		).where(
			DSL.and(
				segmentIdField.in(segmentIds), identityIdField.eq(identityId))
		).groupBy(
			identityIdField
		).having(
			_getConditions(max, min, ascending)
		).fetch(
			0, String.class
		);
	}

	@Override
	public List<Long> findSegmentIdByIdentityIdAndStatus(
		String identityId, String status) {

		Field<Object> segmentIdField = DSL.field("segmentId");

		SelectSelectStep<Record1<Object>> selectSelectStep = _dslContext.select(
			segmentIdField);

		Field<Object> identityIdField = DSL.field("identityId");
		Field<Object> statusField = DSL.field("status");

		return selectSelectStep.from(
			"BQMembership"
		).where(
			identityIdField.eq(identityId)
		).and(
			statusField.eq(status)
		).fetch(
			0, Long.class
		);
	}

	@Override
	public List<Long> findSegmentIdByIdentityIdInAndStatus(
		List<String> identityIds, String status) {

		Field<Object> segmentIdField = DSL.field("segmentId");

		SelectSelectStep<Record1<Object>> selectSelectStep =
			_dslContext.selectDistinct(segmentIdField);

		Field<Object> identityIdField = DSL.field("identityId");
		Field<Object> statusField = DSL.field("status");

		return selectSelectStep.from(
			"BQMembership"
		).where(
			identityIdField.in(identityIds)
		).and(
			statusField.eq(status)
		).fetch(
			0, Long.class
		);
	}

	@Override
	public List<Long> findTop20SegmentIdByIdentityId(String identityId) {
		Field<Object> segmentIdField = DSL.field("segmentId");

		SelectSelectStep<Record1<Object>> selectSelectStep = _dslContext.select(
			segmentIdField);

		Field<Object> identityIdField = DSL.field("identityId");

		return selectSelectStep.from(
			"BQMembership"
		).where(
			identityIdField.eq(identityId)
		).groupBy(
			segmentIdField
		).orderBy(
			DSL.count(
				identityIdField
			).desc()
		).limit(
			20
		).fetch(
			0, Long.class
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