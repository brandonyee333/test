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

import com.liferay.osb.asah.common.converter.helper.FilterStringConverterHelper;
import com.liferay.osb.asah.common.filter.expression.FilterExpression;
import com.liferay.osb.asah.common.filter.expression.JoinCondition;
import com.liferay.osb.asah.common.model.IndividualMetricType;
import com.liferay.osb.asah.common.model.MetricType;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.common.repository.CustomBQIdentityRepository;
import com.liferay.osb.asah.common.repository.executor.QueryExecutor;
import com.liferay.osb.asah.common.repository.helper.DSLHelper;

import java.math.BigDecimal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.lang3.BooleanUtils;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.SelectConditionStep;
import org.jooq.SelectFinalStep;
import org.jooq.SelectJoinStep;
import org.jooq.SelectSelectStep;
import org.jooq.impl.DSL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

/**
 * @author Ivica Cardic
 */
public class BQIdentityRepositoryImpl
	extends BaseRepository implements CustomBQIdentityRepository {

	public BQIdentityRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	@Override
	public long countIndividuals(boolean includeAnonymousUsers) {
		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.selectCount();

		SelectJoinStep<Record1<Integer>> selectJoinStep = selectSelectStep.from(
			DSL.table(
				"BQIdentity"
			).as(
				"Identity"
			));

		if (!includeAnonymousUsers) {
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

		return _queryExecutor.queryForLong(selectJoinStep);
	}

	@Override
	public long getIndividualsCount(
		@Nullable Boolean active, @Nullable Long channelId, LocalDate localDate,
		MetricType metricType, ZoneId zoneId) {

		Optional<BigDecimal> optional = _queryExecutor.queryForObject(
			record -> record.get("count"),
			(SelectFinalStep)_getSelectConditionStep(
				active, channelId, localDate, metricType, 0, zoneId));

		if (optional.isPresent()) {
			BigDecimal value = optional.get();

			return value.longValue();
		}

		return 0;
	}

	@Override
	public List<Long> getIndividualsCounts(
		@Nullable Boolean active, @Nullable Long channelId,
		List<LocalDate> localDates, List<MetricType> metricTypes,
		ZoneId zoneId) {

		SelectConditionStep<Record1<BigDecimal>> selectConditionStep = null;

		int unionOrder = 0;

		for (LocalDate localDate : localDates) {
			for (MetricType metricType : metricTypes) {
				SelectConditionStep<Record1<BigDecimal>>
					curSelectConditionStep = _getSelectConditionStep(
						active, channelId, localDate, metricType, unionOrder++,
						zoneId);

				if (selectConditionStep == null) {
					selectConditionStep = curSelectConditionStep;
				}
				else {
					selectConditionStep.unionAll(curSelectConditionStep);
				}
			}
		}

		if (selectConditionStep == null) {
			return Collections.emptyList();
		}

		return _queryExecutor.queryForList(
			record -> {
				BigDecimal count = (BigDecimal)record.get("count");

				return count.longValue();
			},
			selectConditionStep.orderBy(DSL.field("unionOrder")));
	}

	@Override
	public List<Long> searchSegmentBQIdentityIds(
		String filterString,
		List<FilterStringConverterHelper>
			filterTypeFilterStringConverterHelpers) {

		Field<Long> identityIdField = DSL.field("Identity.id", Long.class);

		SelectSelectStep<Record1<Long>> selectSelectStep = _dslContext.select(
			identityIdField.as("id"));

		SelectJoinStep<Record1<Long>> selectJoinStep = selectSelectStep.from(
			DSL.table(
				"BQIdentity"
			).as(
				"Identity"
			));

		JoinCondition joinCondition = FilterExpression.convert(
			filterString, filterTypeFilterStringConverterHelpers);

		selectJoinStep = _getSelectJoinStep(
			joinCondition.getIncludedTableNames(), selectJoinStep);

		return _queryExecutor.queryForList(
			record -> (Long)record.get("id"),
			selectJoinStep.where(joinCondition.getCondition()));
	}

	private List<Condition> _getConditions(
		Boolean active, Long channelId, LocalDate localDate,
		MetricType metricType, ZoneId zoneId) {

		LocalDateTime localDateTime = localDate.atTime(LocalTime.MAX);

		List<Condition> conditions = new ArrayList<>();

		conditions.add(
			DSL.field(
				metricType.getFieldName()
			).lessOrEqual(
				_dslHelper.getDateParam(localDateTime, zoneId.toString())
			));

		if (channelId != null) {
			conditions.add(
				DSL.field(
					"IdentityActivity.channelId"
				).eq(
					channelId
				));
		}

		if (metricType == IndividualMetricType.ANONYMOUS_INDIVIDUALS) {
			conditions.add(
				DSL.or(
					DSL.field(
						"Identity.individualId"
					).isNull(),
					DSL.field(
						"Identity.individualId"
					).notIn(
						DSL.select(
							DSL.field("id")
						).from(
							DSL.table("BQIndividual")
						)
					)));
		}

		if (BooleanUtils.isTrue(active)) {
			TimeRange timeRange = TimeRange.LAST_30_DAYS;

			conditions.add(
				DSL.field(
					"IdentityActivity.lastActivityDate"
				).greaterThan(
					_dslHelper.getDateParam(
						timeRange.getStartLocalDateTime(), zoneId.toString())
				));
		}

		return conditions;
	}

	private SelectConditionStep _getSelectConditionStep(
		@Nullable Boolean active, @Nullable Long channelId, LocalDate localDate,
		MetricType metricType, int unionOrder, ZoneId zoneId) {

		Field<Integer> field = null;

		Field<Object> individualIdField = DSL.field("Individual.id");

		if (metricType == IndividualMetricType.ANONYMOUS_INDIVIDUALS) {
			field = _dslHelper.countIf(individualIdField.isNull());
		}
		else if (metricType == IndividualMetricType.KNOWN_INDIVIDUALS) {
			field = DSL.countDistinct(individualIdField);
		}
		else {
			field = _dslHelper.countIf(
				individualIdField.isNull()
			).plus(
				DSL.countDistinct(individualIdField)
			);
		}

		SelectSelectStep<Record2<BigDecimal, Integer>> selectSelectStep =
			_dslContext.select(
				DSL.cast(
					field, BigDecimal.class
				).as(
					"count"
				),
				DSL.val(
					unionOrder, Integer.class
				).as(
					"unionOrder"
				));

		SelectJoinStep<Record2<BigDecimal, Integer>> selectJoinStep =
			selectSelectStep.from(
				DSL.table(
					"BQIdentity"
				).as(
					"Identity"
				));

		if (BooleanUtils.isTrue(active) || (channelId != null)) {
			selectJoinStep = selectJoinStep.join(
				DSL.table(
					"BQIdentityActivity"
				).as(
					"IdentityActivity"
				)
			).on(
				DSL.field(
					"Identity.id"
				).eq(
					DSL.field("IdentityActivity.identityId")
				)
			);
		}

		selectJoinStep = selectJoinStep.leftJoin(
			DSL.table(
				"BQIndividual"
			).as(
				"Individual"
			)
		).on(
			DSL.field(
				"Identity.individualId"
			).eq(
				individualIdField
			)
		);

		return selectJoinStep.where(
			_getConditions(active, channelId, localDate, metricType, zoneId));
	}

	private SelectJoinStep<Record1<Long>> _getSelectJoinStep(
		Set<String> includedTableNames,
		SelectJoinStep<Record1<Long>> selectJoinStep) {

		if (includedTableNames.contains("Event")) {
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

		if (includedTableNames.contains("Individual")) {
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

		if (includedTableNames.contains("Session")) {
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
	private DSLHelper _dslHelper;

	@Autowired
	private QueryExecutor _queryExecutor;

}