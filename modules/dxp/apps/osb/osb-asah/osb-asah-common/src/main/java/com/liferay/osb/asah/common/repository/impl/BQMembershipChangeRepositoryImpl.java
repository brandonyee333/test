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

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.entity.BQMembershipChange;
import com.liferay.osb.asah.common.repository.CustomBQMembershipChangeRepository;
import com.liferay.osb.asah.common.repository.executor.QueryExecutor;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;
import com.liferay.osb.asah.common.repository.util.ConditionUtil;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.DeleteUsingStep;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectConditionStep;
import org.jooq.SelectSelectStep;
import org.jooq.Table;
import org.jooq.impl.DSL;

import org.springframework.data.domain.Pageable;

/**
 * @author Rachael Koestartyo
 */
public class BQMembershipChangeRepositoryImpl
	implements CustomBQMembershipChangeRepository {

	public BQMembershipChangeRepositoryImpl(
		DSLContext dslContext, QueryExecutor queryExecutor) {

		_dslContext = dslContext;
		_queryExecutor = queryExecutor;
	}

	@Override
	public void addBQMembershipChange(Long segmentId) {
		Map<String, Object> membershipSnapshot = _queryExecutor.queryForMap(
			_dslContext.select(
				DSL.countDistinct(
					DSL.field("identityId")
				).as(
					"identitiesCount"
				),
				DSL.countDistinct(
					DSL.field("individualId")
				).as(
					"individualsCount"
				)
			).from(
				"BQMembership"
			).where(
				DSL.field(
					"segmentId", Long.class
				).eq(
					segmentId
				)
			));

		BigDecimal identitiesCountBigDecimal =
			(BigDecimal)membershipSnapshot.get("identitiesCount");
		BigDecimal individualsCountBigDecimal =
			(BigDecimal)membershipSnapshot.get("individualsCount");

		_queryExecutor.queryExecute(
			_dslContext.insertInto(
				DSL.table("BQMembershipChange")
			).columns(
				DSL.field("createDate"),
				DSL.field("identitiesCount", Long.class),
				DSL.field("individualsCount", Long.class),
				DSL.field("segmentId", Long.class)
			).values(
				DateUtil.newDateString(), identitiesCountBigDecimal.longValue(),
				individualsCountBigDecimal.longValue(), segmentId
			));
	}

	@Override
	public long countBQMembershipChanges(
		FilterHelper filterHelper, Long segmentId) {

		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.selectCount();

		return selectSelectStep.from(
			"BQMembershipChange"
		).where(
			_getConditions(filterHelper, segmentId)
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	@Override
	public long countBySegmentId(Long segmentId) {
		return 0;
	}

	@Override
	public void deleteBySegmentIdIn(List<Long> segmentIds) {
		Field<Object> segmentIdField = DSL.field("segmentId");

		DeleteUsingStep<Record> deleteUsingStep = _dslContext.deleteFrom(
			DSL.table("BQMembershipChange"));

		deleteUsingStep.where(
			segmentIdField.in(segmentIds)
		).execute();
	}

	@Override
	public List<BQMembershipChange> findAll() {
		return null;
	}

	@Override
	public BQMembershipChange findBySegmentId(long segmentId) {
		return null;
	}

	@Override
	public List<BQMembershipChange> findLastBQMembershipChangeBySegmentIds(
		List<Long> segmentIds) {

		if (segmentIds.isEmpty()) {
			return Collections.emptyList();
		}

		SelectConditionStep<Record> tempTableSelectConditionStep =
			_dslContext.select(
				DSL.asterisk(),
				DSL.rowNumber(
				).over(
				).partitionBy(
					DSL.field("segmentId")
				).orderBy(
					DSL.field(
						"createDate"
					).desc()
				).as(
					"rowNumber"
				)
			).from(
				"BQMembershipChange"
			).where(
				DSL.field(
					"segmentId", Long.class
				).in(
					segmentIds
				)
			);

		return _queryExecutor.queryForList(
			BQMembershipChange::new,
			_dslContext.select(
				DSL.field("createDate"), DSL.field("identitiesCount"),
				DSL.field("individualsCount"), DSL.field("segmentId")
			).from(
				tempTableSelectConditionStep
			).where(
				DSL.field(
					"rowNumber", Integer.class
				).eq(
					1
				)
			));
	}

	@Override
	public List<Long> findSegmentIdByFilterString(String filterString) {
		Field<Object> createDateField = DSL.field("createDate");

		Field<Object> segmentIdField = DSL.field("segmentId");

		SelectSelectStep<Record1<Object>> selectSelectStep = _dslContext.select(
			segmentIdField);

		Table<Record> bqMembershipChangeTable = DSL.table("BQMembershipChange");

		return selectSelectStep.from(
			bqMembershipChangeTable
		).where(
			DSL.row(
				createDateField, segmentIdField
			).in(
				DSL.select(
					DSL.max(createDateField), segmentIdField
				).from(
					bqMembershipChangeTable
				).groupBy(
					segmentIdField
				)
			).and(
				ConditionUtil.toCondition(filterString)
			)
		).fetch(
			0, Long.class
		);
	}

	@Override
	public BQMembershipChange insert(BQMembershipChange bqMembershipChange) {
		_queryExecutor.queryExecute(
			_dslContext.insertInto(
				DSL.table("BQMembershipChange")
			).columns(
				DSL.field("createDate", Date.class),
				DSL.field("identitiesCount", Long.class),
				DSL.field("individualsCount", Long.class),
				DSL.field("segmentId", Long.class)
			).values(
				bqMembershipChange.getCreateDate(),
				bqMembershipChange.getIdentitiesCount(),
				bqMembershipChange.getIndividualsCount(),
				bqMembershipChange.getSegmentId()
			));

		return bqMembershipChange;
	}

	@Override
	public List<BQMembershipChange> searchBQMembershipChanges(
		FilterHelper filterHelper, Long segmentId, Pageable pageable) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return selectSelectStep.from(
			"BQMembershipChange"
		).where(
			_getConditions(filterHelper, segmentId)
		).limit(
			pageable.getPageSize()
		).offset(
			pageable.getOffset()
		).fetch(
			record -> new BQMembershipChange(record.intoMap())
		);
	}

	private List<Condition> _getConditions(
		FilterHelper filterHelper, Long segmentId) {

		List<Condition> conditions = new ArrayList<>();

		if (segmentId != null) {
			conditions.add(
				DSL.field(
					"segmentId"
				).eq(
					segmentId
				));
		}

		if (!StringUtils.isEmpty(filterHelper.getFilterString())) {
			conditions.add(filterHelper.getCondition());
		}

		if (conditions.isEmpty()) {
			conditions.add(DSL.noCondition());
		}

		return conditions;
	}

	private final DSLContext _dslContext;
	private final QueryExecutor _queryExecutor;

}