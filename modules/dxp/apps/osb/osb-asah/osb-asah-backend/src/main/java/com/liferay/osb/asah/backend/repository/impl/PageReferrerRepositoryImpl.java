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

package com.liferay.osb.asah.backend.repository.impl;

import com.liferay.osb.asah.backend.repository.PageReferrerRepository;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.common.repository.executor.QueryExecutor;
import com.liferay.osb.asah.common.util.GetterUtil;

import java.math.BigDecimal;

import java.time.ZoneId;

import java.util.Map;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.impl.DSL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

/**
 * @author Marcellus Tavares
 */
@Repository
public class PageReferrerRepositoryImpl implements PageReferrerRepository {

	@Override
	public Map<String, Double> getAcquisitionChannelAccesses(
		String canonicalUrl, @Nullable Long channelId, TimeRange timeRange,
		ZoneId zoneId) {

		Field<String> acquisitionChannelField = DSL.coalesce(
			DSL.field("acquisitionChannel", String.class), DSL.val("direct")
		).as(
			"acquisitionChannel"
		);

		Field<BigDecimal> accessesField = DSL.sum(
			DSL.field("access", Long.class)
		).as(
			"accesses"
		);

		return _queryExecutor.queryForMap(
			GetterUtil::getString,
			dslContext.select(
				acquisitionChannelField, accessesField
			).from(
				"BQPageReferrers"
			).where(
				_createWhereClauseCondition(
					canonicalUrl, channelId, timeRange, zoneId)
			).groupBy(
				acquisitionChannelField
			).orderBy(
				accessesField
			),
			value -> {
				BigDecimal bigDecimalValue = (BigDecimal)value;

				return bigDecimalValue.doubleValue();
			});
	}

	@Override
	public Map<String, Double> getPageReferrerAccesses(
		String canonicalUrl, @Nullable Long channelId, TimeRange timeRange,
		ZoneId zoneId) {

		Field<BigDecimal> accessesField = DSL.sum(
			DSL.field("access", Long.class)
		).as(
			"accesses"
		);

		Field<String> referrerField = DSL.field("referrer", String.class);

		return _queryExecutor.queryForMap(
			GetterUtil::getString,
			dslContext.select(
				referrerField, accessesField
			).from(
				"BQPageReferrers"
			).where(
				DSL.and(
					_createWhereClauseCondition(
						canonicalUrl, channelId, timeRange, zoneId),
					DSL.field(
						"referrer"
					).ne(
						""
					))
			).groupBy(
				referrerField
			).orderBy(
				accessesField.desc()
			).limit(
				3
			),
			value -> {
				BigDecimal bigDecimalValue = (BigDecimal)value;

				return bigDecimalValue.doubleValue();
			});
	}

	@Override
	public Map<String, Double>
		getSocialPageReferrerAccessesByReferrerCanonicalUrl(
			String canonicalUrl, @Nullable Long channelId, Pageable pageable,
			TimeRange timeRange, ZoneId zoneId) {

		Field<String> referrerCanonicalUrl = DSL.field(
			"referrerCanonicalUrl", String.class);

		Field<BigDecimal> accessesField = DSL.sum(
			DSL.field("access", Long.class)
		).as(
			"accesses"
		);

		return _queryExecutor.queryForMap(
			GetterUtil::getString,
			dslContext.select(
				referrerCanonicalUrl, accessesField
			).from(
				"BQPageReferrers"
			).where(
				DSL.and(
					_createWhereClauseCondition(
						canonicalUrl, channelId, timeRange, zoneId),
					DSL.field(
						"acquisitionChannel"
					).isNotNull(),
					DSL.field(
						"acquisitionChannel"
					).notIn(
						"organic", "social"
					),
					referrerCanonicalUrl.ne(""),
					referrerCanonicalUrl.isNotNull())
			).groupBy(
				referrerCanonicalUrl
			).orderBy(
				accessesField
			).limit(
				pageable.getPageSize()
			).offset(
				pageable.getOffset()
			),
			value -> {
				BigDecimal bigDecimalValue = (BigDecimal)value;

				return bigDecimalValue.doubleValue();
			});
	}

	@Override
	public Map<String, Double> getSocialPageReferrerAccessesByReferrerHost(
		String canonicalUrl, @Nullable Long channelId, Pageable pageable,
		TimeRange timeRange, ZoneId zoneId) {

		Field<String> referrerHostField = DSL.field(
			"referrerHost", String.class);

		Field<BigDecimal> accessesField = DSL.sum(
			DSL.field("access", Long.class)
		).as(
			"accesses"
		);

		return _queryExecutor.queryForMap(
			GetterUtil::getString,
			dslContext.select(
				referrerHostField, accessesField
			).from(
				"BQPageReferrers"
			).where(
				DSL.and(
					_createWhereClauseCondition(
						canonicalUrl, channelId, timeRange, zoneId),
					DSL.field(
						"acquisitionChannel"
					).isNotNull(),
					DSL.field(
						"acquisitionChannel"
					).notIn(
						"organic", "social"
					),
					referrerHostField.ne(""), referrerHostField.isNotNull())
			).groupBy(
				referrerHostField
			).orderBy(
				accessesField
			).limit(
				pageable.getPageSize()
			).offset(
				pageable.getOffset()
			),
			value -> {
				BigDecimal bigDecimalValue = (BigDecimal)value;

				return bigDecimalValue.doubleValue();
			});
	}

	@Autowired
	protected DSLContext dslContext;

	private Condition _createWhereClauseCondition(
		String canonicalUrl, @Nullable Long channelId, TimeRange timeRange,
		ZoneId zoneId) {

		Condition condition = DSL.and(
			DSL.field(
				"canonicalUrl"
			).eq(
				canonicalUrl
			),
			DSL.field(
				"eventDate"
			).between(
				DateUtil.toUTCLocalDateTime(
					timeRange.getStartLocalDateTime(), zoneId),
				DateUtil.toUTCLocalDateTime(
					timeRange.getEndLocalDateTime(), zoneId)
			));

		if (channelId != null) {
			condition = condition.and(
				DSL.field(
					"channelId"
				).eq(
					channelId
				));
		}

		return condition;
	}

	@Autowired
	private QueryExecutor _queryExecutor;

}