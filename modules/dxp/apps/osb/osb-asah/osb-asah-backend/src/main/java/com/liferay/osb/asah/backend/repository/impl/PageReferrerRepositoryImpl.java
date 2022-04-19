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
import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.model.TimeRange;

import java.math.BigDecimal;

import java.util.LinkedHashMap;
import java.util.Map;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.impl.DSL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/**
 * @author Marcellus Tavares
 */
@Repository
public class PageReferrerRepositoryImpl implements PageReferrerRepository {

	@Override
	public Map<String, Double> getAcquisitionChannelAccesses(
		String canonicalUrl, Long channelId, Long dataSourceId,
		TimeRange timeRange) {

		Map<String, Double> result = new LinkedHashMap<>();

		Field<String> acquisitionChannelField = DSL.field(
			"acquisitionChannel", String.class);

		Field<BigDecimal> accessesField = DSL.sum(
			DSL.field("access", Long.class)
		).as(
			"accesses"
		);

		dslContext.select(
			acquisitionChannelField, accessesField
		).from(
			"BQPageReferrers"
		).where(
			_createWhereClause(canonicalUrl, channelId, dataSourceId, timeRange)
		).groupBy(
			acquisitionChannelField
		).orderBy(
			accessesField
		).fetch(
		).forEach(
			record -> {
				BigDecimal accessesBigDecimal = record.value2();

				result.put(record.value1(), accessesBigDecimal.doubleValue());
			}
		);

		return result;
	}

	@Override
	public Map<String, Double> getPageReferrerAccesses(
		String canonicalUrl, Long channelId, Long dataSourceId,
		TimeRange timeRange) {

		Map<String, Double> result = new LinkedHashMap<>();

		Field<String> referrerField = DSL.field("referrer", String.class);

		Field<BigDecimal> accessesField = DSL.sum(
			DSL.field("access", Long.class)
		).as(
			"accesses"
		);

		dslContext.select(
			referrerField, accessesField
		).from(
			"BQPageReferrers"
		).where(
			DSL.and(
				_createWhereClause(
					canonicalUrl, channelId, dataSourceId, timeRange),
				DSL.field(
					"referrer"
				).notEqual(
					""
				))
		).groupBy(
			referrerField
		).orderBy(
			accessesField
		).limit(
			3
		).fetch(
		).forEach(
			record -> {
				BigDecimal accessesBigDecimal = record.value2();

				result.put(record.value1(), accessesBigDecimal.doubleValue());
			}
		);

		return result;
	}

	@Override
	public Map<String, Double>
		getSocialPageReferrerAccessesByReferrerCanonicalUrl(
			String canonicalUrl, Long channelId, Long dataSourceId,
			Pageable pageable, TimeRange timeRange) {

		Map<String, Double> result = new LinkedHashMap<>();

		Field<String> referrerCanonicalUrl = DSL.field(
			"referrerCanonicalUrl", String.class);

		Field<BigDecimal> accessesField = DSL.sum(
			DSL.field("access", Long.class)
		).as(
			"accesses"
		);

		dslContext.select(
			referrerCanonicalUrl, accessesField
		).from(
			"BQPageReferrers"
		).where(
			DSL.and(
				_createWhereClause(
					canonicalUrl, channelId, dataSourceId, timeRange),
				DSL.not(
					DSL.field(
						"acquisitionChannel"
					).in(
						null, "organic", "social"
					)),
				referrerCanonicalUrl.notEqual(""))
		).groupBy(
			referrerCanonicalUrl
		).orderBy(
			accessesField
		).limit(
			pageable.getPageSize()
		).offset(
			pageable.getOffset()
		).fetch(
		).forEach(
			record -> {
				BigDecimal accessesBigDecimal = record.value2();

				result.put(record.value1(), accessesBigDecimal.doubleValue());
			}
		);

		return result;
	}

	@Override
	public Map<String, Double> getSocialPageReferrerAccessesByReferrerHost(
		String canonicalUrl, Long channelId, Long dataSourceId,
		Pageable pageable, TimeRange timeRange) {

		Map<String, Double> result = new LinkedHashMap<>();

		Field<String> referrerHostField = DSL.field(
			"referrerHost", String.class);

		Field<BigDecimal> accessesField = DSL.sum(
			DSL.field("access", Long.class)
		).as(
			"accesses"
		);

		dslContext.select(
			referrerHostField, accessesField
		).from(
			"BQPageReferrers"
		).where(
			DSL.and(
				_createWhereClause(
					canonicalUrl, channelId, dataSourceId, timeRange),
				DSL.not(
					DSL.field(
						"acquisitionChannel"
					).in(
						null, "organic", "social"
					)),
				referrerHostField.notEqual(""))
		).groupBy(
			referrerHostField
		).orderBy(
			accessesField
		).limit(
			pageable.getPageSize()
		).offset(
			pageable.getOffset()
		).fetch(
		).forEach(
			record -> {
				BigDecimal accessesBigDecimal = record.value2();

				result.put(record.value1(), accessesBigDecimal.doubleValue());
			}
		);

		return result;
	}

	@Autowired
	protected DSLContext dslContext;

	private Condition _createWhereClause(
		String canonicalUrl, Long channelId, Long dataSourceId,
		TimeRange timeRange) {

		return DSL.and(
			DSL.field(
				"dataSourceId"
			).eq(
				dataSourceId
			),
			DSL.field(
				"channelId"
			).eq(
				channelId
			),
			DSL.field(
				"canonicalUrl"
			).eq(
				canonicalUrl
			),
			DSL.field(
				"eventDate"
			).between(
				DateUtil.toUTCLocalDateTime(
					timeRange.getStartLocalDateTime(),
					_timeZoneDog.getZoneId()),
				DateUtil.toUTCLocalDateTime(
					timeRange.getEndLocalDateTime(), _timeZoneDog.getZoneId())
			));
	}

	@Autowired
	private TimeZoneDog _timeZoneDog;

}