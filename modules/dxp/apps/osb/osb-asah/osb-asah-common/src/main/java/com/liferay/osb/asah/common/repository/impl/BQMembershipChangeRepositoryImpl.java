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
import com.liferay.osb.asah.common.date.dog.util.TimeZoneDogUtil;
import com.liferay.osb.asah.common.entity.BQMembershipChange;
import com.liferay.osb.asah.common.model.Transformation;
import com.liferay.osb.asah.common.repository.CustomBQMembershipChangeRepository;
import com.liferay.osb.asah.common.repository.executor.QueryExecutor;
import com.liferay.osb.asah.common.repository.helper.DSLHelper;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;
import com.liferay.osb.asah.common.repository.util.ConditionUtil;

import java.math.BigDecimal;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.DatePart;
import org.jooq.DeleteUsingStep;
import org.jooq.Field;
import org.jooq.InsertValuesStep4;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.SelectConditionStep;
import org.jooq.SelectForUpdateStep;
import org.jooq.SelectSelectStep;
import org.jooq.Table;
import org.jooq.impl.DSL;

import org.springframework.beans.factory.annotation.Autowired;
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
					DSL.coalesce(
						DSL.field("individualId"), DSL.field("identityId"))
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

		_queryExecutor.queryExecute(
			deleteUsingStep.where(segmentIdField.in(segmentIds)));
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
	public List<Transformation> getMembershipChangeTransformations(
		boolean includeToday, Long segmentId, Pageable pageable) {

		List<Condition> conditions = new ArrayList<>();

		Field dateField = _dslHelper.dateTrunc(
			DatePart.DAY, DSL.field("createDate", OffsetDateTime.class));

		LocalDateTime localDateTime = DateUtil.newDayLocalDateTime(
			TimeZoneDogUtil.getZoneId());

		if (!includeToday) {
			conditions.add(
				DSL.field(
					"createDate"
				).lt(
					localDateTime
				));

			localDateTime = localDateTime.minusDays(1);
		}

		conditions.add(
			DSL.field(
				"createDate"
			).ge(
				localDateTime.minusDays(
					pageable.getOffset() + pageable.getPageSize() - 1)
			));

		conditions.add(
			DSL.field(
				"segmentId", Long.class
			).eq(
				segmentId
			));

		SelectForUpdateStep<Record4<Long, Long, Long, Integer>>
			selectConditionStep = _dslContext.select(
				DSL.field("identitiesCount", Long.class),
				DSL.function(
					"UNIX_MILLIS", Long.class, dateField
				).as(
					"intervalInitDate"
				),
				DSL.field("individualsCount", Long.class),
				DSL.rowNumber(
				).over(
				).partitionBy(
					dateField
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
				conditions
			);

		return _queryExecutor.queryForList(
			recordMap -> {
				Map<String, Object> termsMap = new HashMap<>();

				BigDecimal addedIndividualsCount = BigDecimal.ZERO;
				BigDecimal identitiesCount = (BigDecimal)recordMap.get(
					"identitiesCount");
				BigDecimal intervalInitDate = (BigDecimal)recordMap.get(
					"intervalInitDate");
				BigDecimal individualsCount = (BigDecimal)recordMap.get(
					"individualsCount");
				BigDecimal prevIndividualsCount =
					(BigDecimal)recordMap.getOrDefault(
						"prevIndividualsCount", identitiesCount);
				BigDecimal removedIndividualsCount = BigDecimal.ZERO;

				BigDecimal anonymousIndividualsCount = identitiesCount.subtract(
					individualsCount);

				if (identitiesCount.compareTo(prevIndividualsCount) > 0) {
					addedIndividualsCount = identitiesCount.subtract(
						prevIndividualsCount);
				}
				else {
					removedIndividualsCount = prevIndividualsCount.subtract(
						identitiesCount);
				}

				termsMap.put(
					"addedIndividualsCount", addedIndividualsCount.longValue());
				termsMap.put(
					"anonymousIndividualsCount",
					anonymousIndividualsCount.longValue());
				termsMap.put("individualsCount", identitiesCount.longValue());
				termsMap.put("intervalInitDate", intervalInitDate.longValue());
				termsMap.put(
					"knownIndividualsCount", individualsCount.longValue());
				termsMap.put(
					"removedIndividualsCount",
					removedIndividualsCount.longValue());

				return new Transformation(
					new Transformation.Term(termsMap), null);
			},
			_dslContext.select(
				DSL.field("identitiesCount", Long.class),
				DSL.field("intervalInitDate", Long.class),
				DSL.field("individualsCount", Long.class),
				DSL.lag(
					DSL.field("identitiesCount", Long.class)
				).over(
				).partitionBy(
				).orderBy(
					DSL.field("intervalInitDate")
				).as(
					"prevIndividualsCount"
				)
			).from(
				selectConditionStep
			).where(
				DSL.field(
					"rowNumber", Integer.class
				).eq(
					1
				)
			).orderBy(
				DSL.field("intervalInitDate")
			).limit(
				pageable.getPageSize()
			).offset(
				pageable.getOffset()
			));
	}

	@Override
	public void initializeBQMembershipChanges(Long segmentId, ZoneId zoneId) {
		InsertValuesStep4<Record, Object, Long, Long, Long> insertValuesStep4 =
			_dslContext.insertInto(
				DSL.table("BQMembershipChange")
			).columns(
				DSL.field("createDate", Object.class),
				DSL.field("identitiesCount", Long.class),
				DSL.field("individualsCount", Long.class),
				DSL.field("segmentId", Long.class)
			);

		LocalDateTime endLocalDateTime = DateUtil.newDayLocalDateTime(zoneId);

		LocalDateTime startLocalDateTime = endLocalDateTime.minusDays(30);

		while (startLocalDateTime.compareTo(endLocalDateTime) < 1) {
			insertValuesStep4 = insertValuesStep4.values(
				DateUtil.toUTCString(startLocalDateTime), 0L, 0L, segmentId);

			startLocalDateTime = startLocalDateTime.plusDays(1);
		}

		_queryExecutor.queryExecute(insertValuesStep4);
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

		return _queryExecutor.queryForList(
			recordMap -> new BQMembershipChange(recordMap),
			selectSelectStep.from(
				"BQMembershipChange"
			).where(
				_getConditions(filterHelper, segmentId)
			).limit(
				pageable.getPageSize()
			).offset(
				pageable.getOffset()
			));
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

	@Autowired
	private DSLHelper _dslHelper;

	private final QueryExecutor _queryExecutor;

}