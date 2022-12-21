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

import com.liferay.osb.asah.common.entity.BQSession;
import com.liferay.osb.asah.common.model.AcquisitionType;
import com.liferay.osb.asah.common.model.Interval;
import com.liferay.osb.asah.common.model.SiteVisitorBehaviorMetric;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.common.repository.CustomBQSessionRepository;
import com.liferay.osb.asah.common.repository.executor.QueryExecutor;
import com.liferay.osb.asah.common.repository.helper.DSLHelper;
import com.liferay.osb.asah.common.util.GetterUtil;

import java.math.BigDecimal;

import java.sql.Date;

import java.time.OffsetDateTime;
import java.time.ZoneId;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.DatePart;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record3;
import org.jooq.SelectFinalStep;
import org.jooq.SelectJoinStep;
import org.jooq.SelectOnConditionStep;
import org.jooq.SelectSelectStep;
import org.jooq.Table;
import org.jooq.WithStep;
import org.jooq.impl.DSL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

/**
 * @author Marcos Martins
 */
public class BQSessionRepositoryImpl
	extends BaseRepository implements CustomBQSessionRepository {

	public BQSessionRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	@Override
	public List<BQSession> findAllById(Collection<String> sessionIds) {
		if (sessionIds.isEmpty()) {
			return Collections.emptyList();
		}

		Table<Record> sessionTable = DSL.table("BQSession");

		SelectSelectStep<Record> selectSelectStep = _dslContext.select(
			sessionTable.asterisk());

		return _queryExecutor.queryForList(
			BQSession::new,
			selectSelectStep.from(
				"BQSession"
			).where(
				DSL.field(
					"id"
				).in(
					sessionIds
				)
			));
	}

	@Override
	public Map<String, BigDecimal> getAcquisitionsMetrics(
		AcquisitionType acquisitionType, Long channelId, Pageable pageable,
		TimeRange timeRange, ZoneId zoneId) {

		if (acquisitionType == AcquisitionType.REFERRER) {
			Field field = DSL.field("referrers");

			return _queryExecutor.queryForMap(
				GetterUtil::getString,
				_dslContext.with(
					"acquisitionsReferrers"
				).as(
					_dslContext.select(
						field
					).from(
						DSL.table("BQSession")
					).where(
						DSL.and(
							_createWhereClauseCondition(
								channelId, timeRange, zoneId),
							field.isNotNull())
					)
				).with(
					"acquisitionsReferrersList"
				).as(
					_dslContext.select(
						DSL.asterisk()
					).from(
						DSL.table("acquisitionsReferrers"),
						DSL.table(
							"UNNEST(referrers)"
						).as(
							"field"
						)
					)
				).select(
					DSL.field("field"),
					DSL.count(
					).cast(
						BigDecimal.class
					)
				).from(
					"acquisitionsReferrersList"
				).groupBy(
					DSL.field("field")
				).limit(
					pageable.getPageSize()
				).offset(
					pageable.getOffset()
				),
				GetterUtil::getBigDecimal);
		}

		Field field = null;

		if (acquisitionType == AcquisitionType.CHANNEL) {
			field = DSL.field("acquisitionchannel");
		}
		else {
			field = DSL.field(
				"acquisitionsource || ' / ' || acquisitionmedium");
		}

		field = DSL.lower(field);

		return _queryExecutor.queryForMap(
			GetterUtil::getString,
			_dslContext.select(
				field.as("field"),
				DSL.count(
				).cast(
					BigDecimal.class
				)
			).from(
				DSL.table("BQSession")
			).where(
				DSL.and(
					_createWhereClauseCondition(channelId, timeRange, zoneId),
					field.isNotNull())
			).groupBy(
				DSL.field("field")
			).limit(
				pageable.getPageSize()
			).offset(
				pageable.getOffset()
			),
			GetterUtil::getBigDecimal);
	}

	@Override
	public long getAcquisitionsMetricsCount(
		AcquisitionType acquisitionType, Long channelId, TimeRange timeRange,
		ZoneId zoneId) {

		if (acquisitionType == AcquisitionType.REFERRER) {
			Field field = DSL.field("referrers");

			return _queryExecutor.queryForLong(
				_dslContext.with(
					"acquisitionsReferrers"
				).as(
					_dslContext.select(
						field
					).from(
						DSL.table("BQSession")
					).where(
						DSL.and(
							_createWhereClauseCondition(
								channelId, timeRange, zoneId),
							field.isNotNull())
					)
				).with(
					"acquisitionsReferrersList"
				).as(
					_dslContext.select(
						DSL.asterisk()
					).from(
						DSL.table("acquisitionsReferrers"),
						DSL.table(
							"UNNEST(referrers)"
						).as(
							"field"
						)
					)
				).select(
					DSL.count(DSL.asterisk())
				).from(
					"acquisitionsReferrersList"
				));
		}

		Field field = null;

		if (acquisitionType == AcquisitionType.CHANNEL) {
			field = DSL.field("acquisitionchannel");
		}
		else {
			field = DSL.field(
				"acquisitionsource || ' / ' || acquisitionmedium");
		}

		return _queryExecutor.queryForLong(
			_dslContext.select(
				DSL.count(DSL.field(field))
			).from(
				DSL.table("BQSession")
			).where(
				DSL.and(
					_createWhereClauseCondition(channelId, timeRange, zoneId),
					field.isNotNull())
			));
	}

	public List<Map<String, Object>> getCohortHeatMapTuples(
		Long channelId, Interval interval, TimeRange timeRange, ZoneId zoneId) {

		WithStep withStep = _createCohortWithStep(
			channelId, interval, timeRange, zoneId);

		Field<Object> sessionDateField = DSL.field(
			"retentionTable.sessionDate");

		return _queryExecutor.queryForList(
			Function.identity(),
			withStep.select(
				_getCohortFields(interval)
			).from(
				DSL.table("retentionTable")
			).leftJoin(
				DSL.table("cohortSize")
			).on(
				sessionDateField.eq(DSL.field("cohortSize.sessionDate"))
			).and(
				DSL.field(
					"retentionTable.isKnown"
				).eq(
					DSL.field("cohortSize.isKnown")
				)
			).where(
				sessionDateField.isNotNull()
			).groupBy(
				sessionDateField
			).orderBy(
				sessionDateField
			));
	}

	@Override
	public Map<String, BigDecimal> getSessionsCountGroupedByBrowserName(
		Long channelId, TimeRange timeRange, ZoneId zoneId) {

		Field field = DSL.field("browserName");

		return _queryExecutor.queryForMap(
			GetterUtil::getString,
			_dslContext.select(
				field, DSL.count()
			).from(
				"BQSession"
			).where(
				_createWhereClauseCondition(channelId, timeRange, zoneId)
			).groupBy(
				field
			),
			GetterUtil::getBigDecimal);
	}

	@Override
	public List<Map<String, Object>> getSessionsCountGroupedByDeviceName(
		Long channelId, TimeRange timeRange, ZoneId zoneId) {

		Field<String> deviceTypeField = DSL.field("deviceType", String.class);
		Field<String> platformNameField = DSL.field(
			"platformName", String.class);

		return _queryExecutor.queryForList(
			Function.identity(),
			_dslContext.select(
				deviceTypeField, platformNameField,
				DSL.count(
				).as(
					"count"
				)
			).from(
				"BQSession"
			).where(
				_createWhereClauseCondition(channelId, timeRange, zoneId)
			).groupBy(
				deviceTypeField, platformNameField
			));
	}

	@Override
	public Map<String, BigDecimal> getSessionsCountGroupedByGeolocation(
		Long channelId, TimeRange timeRange, ZoneId zoneId) {

		Field field = DSL.field("country");

		return _queryExecutor.queryForMap(
			GetterUtil::getString,
			_dslContext.select(
				field, DSL.count()
			).from(
				"BQSession"
			).where(
				_createWhereClauseCondition(channelId, timeRange, zoneId)
			).groupBy(
				field
			),
			GetterUtil::getBigDecimal);
	}

	@Override
	public List<SiteVisitorBehaviorMetric> getSiteVisitorBehaviorMetrics(
		Long channelId, boolean includePrevious, TimeRange timeRange,
		ZoneId zoneId) {

		Field<Boolean> previousField = DSL.when(
			DSL.field(
				"BQSession.sessionStart"
			).gt(
				_dslHelper.getDateParam(
					timeRange.getStartLocalDateTime(), zoneId.toString())
			),
			false
		).otherwise(
			true
		).as(
			"previous"
		);

		return _queryExecutor.queryForList(
			SiteVisitorBehaviorMetric::new,
			(SelectFinalStep)_joinWithIdentityTable(
				_dslContext.select(
					previousField, _getKnownVisitorsField(true),
					_getUniqueVisitorsField(),
					DSL.avg(
						DSL.field("duration", Long.class)
					).as(
						"averagesessionduration"
					),
					DSL.sum(
						DSL.field("bounce", Long.class)
					).as(
						"bounces"
					),
					DSL.count(
					).as(
						"sessions"
					)
				).from(
					DSL.table("BQSession")
				)
			).where(
				_createWhereClauseCondition(
					channelId, includePrevious, timeRange, zoneId)
			).groupBy(
				previousField
			));
	}

	@Override
	public List<SiteVisitorBehaviorMetric>
		getSiteVisitorBehaviorMetricsGroupedBySessionStart(
			Long channelId, boolean includePrevious, Interval interval,
			TimeRange timeRange, ZoneId zoneId) {

		Field sessionStartField = DSL.timestamp(
			_dslHelper.dateTrunc(
				DatePart.valueOf(interval.name()),
				_dslHelper.getDateAtTimeZoneField(
					"BQSession.sessionStart", zoneId.toString())));

		sessionStartField = sessionStartField.as("eventdate");

		return _queryExecutor.queryForList(
			SiteVisitorBehaviorMetric::new,
			_joinWithIdentityTable(
				_dslContext.select(
					sessionStartField, _getKnownVisitorsField(true),
					_getUniqueVisitorsField(),
					DSL.avg(
						DSL.field("duration", Long.class)
					).as(
						"averagesessionduration"
					),
					DSL.sum(
						DSL.field("bounce", Long.class)
					).as(
						"bounces"
					),
					DSL.count(
					).as(
						"sessions"
					)
				).from(
					"BQSession"
				)
			).where(
				_createWhereClauseCondition(
					channelId, includePrevious, timeRange, zoneId)
			).groupBy(
				sessionStartField
			));
	}

	@Override
	public List<Map<String, BigDecimal>> getVisitorsCountGroupedByDayAndTime(
		Long channelId, TimeRange timeRange, ZoneId zoneId) {

		Field dayOfWeekField = _dslHelper.getDayOfWeekField(
			_dslHelper.getDateAtTimeZoneField(
				"BQSession.sessionStart", zoneId.toString()));

		dayOfWeekField = dayOfWeekField.cast(BigDecimal.class);

		dayOfWeekField = dayOfWeekField.as("dayOfWeek");

		Field dateField = DSL.timestamp(
			_dslHelper.dateTrunc(
				DatePart.valueOf(String.valueOf(Interval.HOUR)),
				_dslHelper.getDateAtTimeZoneField(
					"BQSession.sessionStart", zoneId.toString())));

		Field hourOfDayField = DSL.extract(
			dateField, DatePart.HOUR
		).cast(
			BigDecimal.class
		);

		hourOfDayField = hourOfDayField.as("hourOfDay");

		Field uniqueVisitorsField = _getKnownVisitorsField(
			false
		).plus(
			DSL.countDistinct(
				DSL.when(
					DSL.field(
						"IndividualIdentity.individualId"
					).isNull(),
					DSL.field("BQSession.userid")))
		).cast(
			BigDecimal.class
		);

		uniqueVisitorsField = uniqueVisitorsField.as("visitors");

		return _queryExecutor.queryForList(
			Function.identity(),
			_joinWithIdentityTable(
				_dslContext.select(
					dayOfWeekField, hourOfDayField, uniqueVisitorsField
				).from(
					"BQSession"
				)
			).where(
				_createWhereClauseCondition(channelId, timeRange, zoneId)
			).groupBy(
				dayOfWeekField, hourOfDayField
			));
	}

	private WithStep _createCohortWithStep(
		Long channelId, Interval interval, TimeRange timeRange, ZoneId zoneId) {

		DatePart datePart = DatePart.DAY;

		if (Interval.MONTH.equals(interval)) {
			datePart = DatePart.MONTH;
		}
		else if (Interval.WEEK.equals(interval)) {
			datePart = DatePart.WEEK;
		}

		return _dslContext.with(
			"firstSession"
		).as(
			_getFirstSessionSelectJoinStep(
				channelId, datePart, timeRange, zoneId)
		).with(
			"sessions"
		).as(
			_getSessionsSelectJoinStep(channelId, datePart, timeRange, zoneId)
		).with(
			"cohortSize"
		).as(
			_dslContext.select(
				DSL.field("sessionDate"), DSL.field("isKnown"),
				DSL.count(
				).as(
					"visitorsCount"
				)
			).from(
				"firstSession"
			).groupBy(
				DSL.field("sessionDate"), DSL.field("isKnown")
			).orderBy(
				DSL.field("sessionDate")
			)
		).with(
			"retentionTable"
		).as(
			_dslContext.select(
				DSL.field("firstSession.sessionDate"),
				DSL.field("sessions.intervalNumber"),
				DSL.field("firstSession.isKnown"),
				DSL.count(
				).as(
					"visitorsCount"
				)
			).from(
				DSL.table("sessions")
			).leftJoin(
				DSL.table("firstSession")
			).on(
				DSL.field(
					"sessions.userId"
				).eq(
					DSL.field("firstSession.userId")
				)
			).groupBy(
				DSL.field("firstSession.sessionDate"),
				DSL.field("sessions.intervalNumber"),
				DSL.field("firstSession.isKnown")
			).orderBy(
				DSL.field("firstSession.sessionDate")
			)
		);
	}

	private Condition _createWhereClauseCondition(
		Long channelId, boolean includePrevious, TimeRange timeRange,
		ZoneId zoneId) {

		TimeRange whereClauseTimeRange = timeRange;

		if (includePrevious) {
			whereClauseTimeRange = timeRange.getIncludePreviousTimeRange();
		}

		return _createWhereClauseCondition(
			channelId, whereClauseTimeRange, zoneId);
	}

	private Condition _createWhereClauseCondition(
		Long channelId, TimeRange timeRange, ZoneId zoneId) {

		return DSL.and(
			DSL.field(
				"BQSession.channelId"
			).eq(
				channelId
			),
			DSL.field(
				"BQSession.sessionStart"
			).between(
				_dslHelper.getDateParam(
					timeRange.getStartLocalDateTime(), zoneId.toString()),
				_dslHelper.getDateParam(
					timeRange.getEndLocalDateTime(), zoneId.toString())
			));
	}

	private Field<BigDecimal> _getCohortField(
		int fieldIndex, boolean known, boolean retention) {

		String cohortType = "Total";

		if (retention) {
			cohortType = "Retention";
		}

		Field<Boolean> isKnownField = _dslHelper.getCastBooleanField(
			_dslHelper.getCastStringField(DSL.field("retentionTable.isKnown")));

		Condition isKnownCondition = isKnownField.eq(false);

		String visitorType = "Unknown";

		if (known) {
			isKnownCondition = isKnownField.eq(true);
			visitorType = "Known";
		}

		Field<Double> conditionStep = DSL.when(
			DSL.field(
				"retentionTable.intervalNumber"
			).eq(
				fieldIndex
			).and(
				isKnownCondition
			),
			_dslHelper.getCastNumberField(
				_dslHelper.getCastStringField(
					DSL.field("retentionTable.visitorsCount")))
		).otherwise(
			0D
		);

		if (retention) {
			conditionStep = conditionStep.div(
				_dslHelper.getCastNumberField(
					_dslHelper.getCastStringField(
						DSL.field("cohortSize.visitorsCount"))));
		}

		String fieldName = String.format(
			"interval%d%s%s", fieldIndex, cohortType, visitorType);

		return DSL.sum(
			conditionStep
		).as(
			fieldName
		);
	}

	private List<Field<?>> _getCohortFields(Interval interval) {
		List<Field<?>> fields = new ArrayList<>();

		Field<String> sessionDateField = _dslHelper.getCastStringField(
			DSL.field(
				"retentionTable.sessionDate"
			).cast(
				Date.class
			));

		fields.add(sessionDateField.as("cohortDate"));

		int maxIntervals = 7;

		if (Interval.DAY.equals(interval)) {
			maxIntervals = 8;
		}

		for (int i = 0; i < maxIntervals; i++) {
			fields.add(_getCohortField(i, true, true));
			fields.add(_getCohortField(i, false, true));
			fields.add(_getCohortField(i, true, false));
			fields.add(_getCohortField(i, false, false));
		}

		return fields;
	}

	private SelectJoinStep<? extends Record> _getFirstSessionSelectJoinStep(
		Long channelId, DatePart datePart, TimeRange timeRange, ZoneId zoneId) {

		SelectJoinStep<Record3<Object, Boolean, Date>> selectJoinStep =
			_dslContext.select(
				DSL.field("BQSession.userId"),
				DSL.when(
					DSL.field(
						"IndividualIdentity.individualId"
					).isNull(),
					false
				).otherwise(
					true
				).as(
					"isKnown"
				),
				DSL.min(
					_dslHelper.dateTrunc(
						datePart,
						_dslHelper.getDateAtTimeZoneField(
							"BQSession.sessionStart", zoneId.toString()))
				).as(
					"sessionDate"
				)
			).from(
				"BQSession"
			);

		SelectOnConditionStep<? extends Record> selectOnConditionStep =
			_joinWithIdentityTable(selectJoinStep);

		selectOnConditionStep.where(
			_createWhereClauseCondition(channelId, timeRange, zoneId)
		).groupBy(
			DSL.field("BQSession.userId"), DSL.field("isKnown")
		).orderBy(
			DSL.field("BQSession.userId"), DSL.field("isKnown")
		);

		return selectOnConditionStep;
	}

	private Field<Integer> _getKnownVisitorsField(boolean alias) {
		Field<Integer> field = DSL.countDistinct(
			DSL.field("IndividualIdentity.individualId"));

		if (alias) {
			return field.as("knownvisitors");
		}

		return field;
	}

	private SelectJoinStep<? extends Record> _getSessionsSelectJoinStep(
		Long channelId, DatePart datePart, TimeRange timeRange, ZoneId zoneId) {

		Field<OffsetDateTime> dateDiffField = _dslHelper.dateDiff(
			datePart,
			_dslHelper.dateTrunc(
				datePart,
				_dslHelper.getDateAtTimeZoneField(
					"BQSession.sessionStart", zoneId.toString())),
			DSL.field("firstSession.sessionDate", OffsetDateTime.class));

		SelectJoinStep<Record3<Object, Object, OffsetDateTime>> selectJoinStep =
			_dslContext.select(
				DSL.field("BQSession.userId"),
				DSL.field("firstSession.isKnown"),
				dateDiffField.as("intervalNumber")
			).from(
				"BQSession"
			);

		SelectOnConditionStep<? extends Record> selectOnConditionStep =
			_joinWithIdentityTable(selectJoinStep);

		selectOnConditionStep.leftJoin(
			DSL.table("firstSession")
		).on(
			DSL.field(
				"BQSession.userId"
			).eq(
				DSL.field("firstSession.userId")
			)
		).where(
			_createWhereClauseCondition(channelId, timeRange, zoneId)
		).groupBy(
			DSL.field("BQSession.userId"), DSL.field("firstSession.isKnown"),
			DSL.field("intervalNumber")
		).orderBy(
			DSL.field("BQSession.userId"), DSL.field("firstSession.isKnown")
		);

		return selectOnConditionStep;
	}

	private Field<Integer> _getUniqueVisitorsField() {
		return _getKnownVisitorsField(
			false
		).plus(
			DSL.countDistinct(
				DSL.when(
					DSL.field(
						"IndividualIdentity.individualId"
					).isNull(),
					DSL.field("BQSession.userid")))
		).as(
			"visitors"
		);
	}

	private SelectOnConditionStep<? extends Record> _joinWithIdentityTable(
		SelectJoinStep<? extends Record> selectJoinStep) {

		return selectJoinStep.leftJoin(
			_dslContext.select(
				DSL.field(
					"BQIdentity.individualId"
				).as(
					"individualId"
				),
				DSL.field("BQIdentity.id")
			).from(
				"BQIdentity"
			).innerJoin(
				"BQIndividual"
			).on(
				DSL.field(
					"BQIdentity.individualId"
				).eq(
					DSL.field("BQIndividual.id")
				)
			).asTable(
				"IndividualIdentity"
			)
		).on(
			DSL.field(
				"IndividualIdentity.id"
			).eq(
				DSL.field("BQSession.userid")
			)
		);
	}

	private final DSLContext _dslContext;

	@Autowired
	private DSLHelper _dslHelper;

	@Autowired
	private QueryExecutor _queryExecutor;

}