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
import com.liferay.osb.asah.common.model.Interval;
import com.liferay.osb.asah.common.model.SiteVisitorBehaviorMetric;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.common.repository.CustomBQSessionRepository;
import com.liferay.osb.asah.common.repository.executor.QueryExecutor;
import com.liferay.osb.asah.common.repository.helper.DSLHelper;
import com.liferay.osb.asah.common.util.GetterUtil;

import java.time.ZoneId;

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
import org.jooq.SelectFinalStep;
import org.jooq.SelectJoinStep;
import org.jooq.SelectOnConditionStep;
import org.jooq.SelectSelectStep;
import org.jooq.Table;
import org.jooq.impl.DSL;

import org.springframework.beans.factory.annotation.Autowired;

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
	public Map<String, Integer> getSessionsCountGroupedByBrowserName(
		Long channelId, TimeRange timeRange, ZoneId zoneId) {

		Field field = DSL.field("browserName");

		return _queryExecutor.queryForMap(
			GetterUtil::getString,
			_dslContext.select(
				field, DSL.count()
			).from(
				"BQSession"
			).where(
				_createWhereClause(channelId, timeRange, zoneId)
			).groupBy(
				field
			),
			GetterUtil::getInteger);
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
				deviceTypeField, platformNameField, DSL.count()
			).from(
				"BQSession"
			).where(
				_createWhereClause(channelId, timeRange, zoneId)
			).groupBy(
				deviceTypeField, platformNameField
			));
	}

	@Override
	public Map<String, Integer> getSessionsCountGroupedByGeolocation(
		Long channelId, TimeRange timeRange, ZoneId zoneId) {

		Field field = DSL.field("country");

		return _queryExecutor.queryForMap(
			GetterUtil::getString,
			_dslContext.select(
				field, DSL.count()
			).from(
				"BQSession"
			).where(
				_createWhereClause(channelId, timeRange, zoneId)
			).groupBy(
				field
			),
			GetterUtil::getInteger);
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
				_createWhereClause(
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
				_createWhereClause(
					channelId, includePrevious, timeRange, zoneId)
			).groupBy(
				sessionStartField
			));
	}

	@Override
	public List<Map<String, Object>> getVisitorsCountGroupedByDayAndTime(
		Long channelId, TimeRange timeRange, ZoneId zoneId) {

		Field dayOfWeekField = DSL.isoDayOfWeek(
			_dslHelper.dateTrunc(
				DatePart.valueOf(String.valueOf(Interval.HOUR)),
				_dslHelper.getDateAtTimeZoneField(
					"BQSession.sessionStart", zoneId.toString())));

		dayOfWeekField = dayOfWeekField.as("dayOfWeek");

		Field dateField = DSL.timestamp(
			_dslHelper.dateTrunc(
				DatePart.valueOf(String.valueOf(Interval.HOUR)),
				_dslHelper.getDateAtTimeZoneField(
					"BQSession.sessionStart", zoneId.toString())));

		Field hourOfDayField = DSL.extract(
			dateField, DatePart.HOUR
		).as(
			"hourOfDay"
		);

		return _queryExecutor.queryForList(
			Function.identity(),
			_joinWithIdentityTable(
				_dslContext.select(
					dayOfWeekField, hourOfDayField, _getUniqueVisitorsField()
				).from(
					"BQSession"
				)
			).where(
				_createWhereClause(channelId, timeRange, zoneId)
			).groupBy(
				dayOfWeekField, hourOfDayField
			));
	}

	private Condition _createWhereClause(
		Long channelId, boolean includePrevious, TimeRange timeRange,
		ZoneId zoneId) {

		TimeRange whereClauseTimeRange = timeRange;

		if (includePrevious) {
			whereClauseTimeRange = timeRange.getIncludePreviousTimeRange();
		}

		return _createWhereClause(channelId, whereClauseTimeRange, zoneId);
	}

	private Condition _createWhereClause(
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

	private Field<Integer> _getKnownVisitorsField(boolean alias) {
		Field<Integer> field = DSL.countDistinct(
			DSL.field("IndividualIdentity.individualId"));

		if (alias) {
			return field.as("knownvisitors");
		}

		return field;
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
				DSL.field("userid")
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
				"IndividualIdentity.userid"
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