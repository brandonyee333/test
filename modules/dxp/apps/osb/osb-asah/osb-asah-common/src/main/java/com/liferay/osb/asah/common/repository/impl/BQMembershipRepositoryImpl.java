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
import com.liferay.osb.asah.common.filter.expression.FilterExpression;
import com.liferay.osb.asah.common.repository.CustomBQMembershipRepository;
import com.liferay.osb.asah.common.repository.executor.QueryExecutor;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jooq.AggregateFunction;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.DeleteUsingStep;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Record5;
import org.jooq.SelectJoinStep;
import org.jooq.SelectSelectStep;
import org.jooq.impl.DSL;

import org.springframework.beans.factory.annotation.Autowired;
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
	public void deleteByIndividualIdAndSegmentId(
		String individualId, Long segmentId) {

		Field<Object> individualIdField = DSL.field("individualId");
		Field<Object> segmentIdField = DSL.field("segmentId");

		DeleteUsingStep<Record> deleteUsingStep = _dslContext.deleteFrom(
			DSL.table("BQMembership"));

		deleteUsingStep.where(
			DSL.and(
				individualIdField.eq(individualId),
				segmentIdField.eq(segmentId))
		).execute();
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
	public List<Long> findSegmentIdByIdentityIdAndStatus(
		String identityId, String status) {

		Field<Object> segmentIdField = DSL.field("segmentId");

		SelectSelectStep<Record1<Object>> selectSelectStep = _dslContext.select(
			segmentIdField);

		Field<Object> statusField = DSL.field("status");
		Field<Object> identityIdField = DSL.field("identityId");

		return selectSelectStep.from(
			"BQMembership"
		).where(
			statusField.eq(status)
		).and(
			identityIdField.eq(identityId)
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

		Field<Object> statusField = DSL.field("status");
		Field<Object> identityIdField = DSL.field("identityId");

		return selectSelectStep.from(
			"BQMembership"
		).where(
			statusField.eq(status)
		).and(
			identityIdField.in(identityIds)
		).fetch(
			0, Long.class
		);
	}

	@Override
	public List<Map<String, Long>>
		findSegmentIdIdentitiesCountByIdentityIdAndStatusAnd(
			String identityId, String status) {

		Field<Long> segmentIdField = DSL.field(
			"BQMembership.segmentId", Long.class);
		Field<BigDecimal> identitiesCountField = DSL.field(
			"identitiesCount", BigDecimal.class);

		SelectSelectStep<Record2<Long, BigDecimal>> selectSelectStep =
			_dslContext.select(
				segmentIdField,
				DSL.sum(
					DSL.coalesce(identitiesCountField, BigDecimal.ZERO)
				).as(
					"identitiesCount"
				));

		Field<String> statusField = DSL.field("status", String.class);
		Field<String> identityIdField = DSL.field("identityId", String.class);

		return selectSelectStep.from(
			"BQMembership"
		).join(
			"BQMembershipChange"
		).on(
			DSL.field(
				"BQMembership.segmentId"
			).eq(
				DSL.field("BQMembershipChange.segmentId")
			)
		).where(
			statusField.eq(status)
		).and(
			identityIdField.eq(identityId)
		).groupBy(
			DSL.field("BQMembership.segmentId")
		).fetch(
			record -> {
				BigDecimal identitiesCount = record.get(identitiesCountField);

				return new HashMap<String, Long>() {
					{
						put("identitiesCount", identitiesCount.longValue());
						put("segmentId", record.get(segmentIdField));
					}
				};
			}
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

	@Override
	public void updateBQMemberships(String filterString, Long segmentId) {
		Date date = new Date();

		SelectSelectStep<Record5<Date, String, String, Date, Long>>
			selectSelectStep = _dslContext.select(
				DSL.val(
					date, Date.class
				).as(
					"createDate"
				),
				DSL.field(
					"Identity.id", String.class
				).as(
					"identityId"
				),
				DSL.field(
					"Identity.individualId", String.class
				).as(
					"individualId"
				),
				DSL.val(
					date, Date.class
				).as(
					"modifiedDate"
				),
				DSL.val(
					segmentId
				).as(
					"segmentId"
				));

		SelectJoinStep<Record5<Date, String, String, Date, Long>>
			selectJoinStep = selectSelectStep.from(
				DSL.table(
					"BQIdentity"
				).as(
					"Identity"
				));

		FilterExpression filterExpression = new FilterExpression(filterString);

		selectJoinStep = _getSelectJoinStep(
			filterExpression.getReferencedTableNames(), selectJoinStep);

		// TODO Replace DELETE/INSERT by BigQuery MERGE Statement

		_queryExecutor.queryExecute(
			_dslContext.deleteFrom(
				DSL.table("BQMembership")
			).where(
				DSL.field(
					"segmentId", Long.class
				).eq(
					segmentId
				)
			));

		_queryExecutor.queryExecute(
			_dslContext.insertInto(
				DSL.table("BQMembership")
			).columns(
				DSL.field("createDate", Date.class),
				DSL.field("identityId", String.class),
				DSL.field("individualId", String.class),
				DSL.field("modifiedDate", Date.class),
				DSL.field("segmentId", Long.class)
			).select(
				selectJoinStep.where(filterExpression.getCondition())
			));
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

	private SelectJoinStep<Record5<Date, String, String, Date, Long>>
		_getSelectJoinStep(
			Set<String> referencedTableNames,
			SelectJoinStep<Record5<Date, String, String, Date, Long>>
				selectJoinStep) {

		if (referencedTableNames.contains("Event")) {
			selectJoinStep = selectJoinStep.join(
				DSL.table(
					"BQEvent"
				).as(
					"Event"
				)
			).on(
				DSL.field(
					"Event.userId"
				).eq(
					DSL.field("Identity.id")
				)
			);
		}

		if (referencedTableNames.contains("Individual")) {
			selectJoinStep = selectJoinStep.join(
				DSL.table(
					"BQIndividual"
				).as(
					"Individual"
				)
			).on(
				DSL.field(
					"Identity.individualId"
				).eq(
					DSL.field("Individual.id")
				)
			);
		}

		if (referencedTableNames.contains("Session")) {
			selectJoinStep = selectJoinStep.join(
				DSL.table(
					"BQSession"
				).as(
					"Session"
				)
			).on(
				DSL.field(
					"Identity.id"
				).eq(
					DSL.field("Session.userId")
				)
			);
		}

		return selectJoinStep;
	}

	private final DSLContext _dslContext;

	@Autowired
	private QueryExecutor _queryExecutor;

}