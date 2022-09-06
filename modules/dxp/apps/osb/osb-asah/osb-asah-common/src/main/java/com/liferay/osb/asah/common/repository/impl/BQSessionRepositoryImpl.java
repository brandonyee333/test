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

import java.time.ZoneId;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

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
	public List<SiteVisitorBehaviorMetric> getSiteVisitorBehaviorMetrics(
		Long channelId, boolean includePrevious, TimeRange timeRange,
		ZoneId zoneId) {

		Field<Boolean> previousField = DSL.when(
			DSL.field(
				_getFieldName("sessionStart", "BQSession")
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
					_getUniqueVisitorsField("BQSession"),
					DSL.avg(
						DSL.field("duration", Long.class)
					).as(
						"averagesessionduration"
					),
					DSL.sum(
						DSL.field("bounce", Integer.class)
					).as(
						"bounces"
					),
					DSL.count(
					).as(
						"sessions"
					)
				).from(
					DSL.table("BQSession")
				),
				"BQSession"
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
					_getFieldName("sessionStart", "BQSession"),
					zoneId.toString())));

		sessionStartField = sessionStartField.as("eventdate");

		return _queryExecutor.queryForList(
			SiteVisitorBehaviorMetric::new,
			_joinWithIdentityTable(
				_dslContext.select(
					sessionStartField, _getKnownVisitorsField(true),
					_getUniqueVisitorsField("BQSession"),
					DSL.avg(
						DSL.field("duration", Long.class)
					).as(
						"averagesessionduration"
					),
					DSL.count(
					).as(
						"sessions"
					)
				).from(
					"BQSession"
				),
				"BQSession"
			).where(
				_createWhereClause(
					channelId, includePrevious, timeRange, zoneId)
			).groupBy(
				sessionStartField
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
				_getFieldName("channelId", "BQSession")
			).eq(
				channelId
			),
			DSL.field(
				_getFieldName("sessionStart", "BQSession")
			).between(
				_dslHelper.getDateParam(
					timeRange.getStartLocalDateTime(), zoneId.toString()),
				_dslHelper.getDateParam(
					timeRange.getEndLocalDateTime(), zoneId.toString())
			));
	}

	private String _getFieldName(String fieldName, String tableName) {
		return String.format("%s.%s", tableName, fieldName);
	}

	private Field<Integer> _getKnownVisitorsField(boolean alias) {
		Field<Integer> field = DSL.countDistinct(
			DSL.field("BQIdentity.emailaddresshashed"));

		if (alias) {
			return field.as("knownvisitors");
		}

		return field;
	}

	private Field<Integer> _getUniqueVisitorsField(String tableName) {
		return _getKnownVisitorsField(
			false
		).plus(
			DSL.countDistinct(
				DSL.when(
					DSL.field(
						"BQIdentity.emailaddresshashed"
					).isNull(),
					DSL.field(_getFieldName("userid", tableName))))
		).as(
			"visitors"
		);
	}

	private SelectOnConditionStep _joinWithIdentityTable(
		SelectJoinStep selectJoinStep, String tableName) {

		return selectJoinStep.leftJoin(
			"BQIdentity"
		).on(
			DSL.field(
				"BQIdentity.userid"
			).eq(
				DSL.field(_getFieldName("userid", tableName))
			)
		);
	}

	private final DSLContext _dslContext;

	@Autowired
	private DSLHelper _dslHelper;

	@Autowired
	private QueryExecutor _queryExecutor;

}