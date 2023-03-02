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

import com.liferay.osb.asah.common.model.Composition;
import com.liferay.osb.asah.common.model.CompositionResultBag;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.common.postgresql.converter.helper.InterestFilterStringConverterHelper;
import com.liferay.osb.asah.common.repository.CustomBQSessionInterestScoreRepository;
import com.liferay.osb.asah.common.repository.executor.QueryExecutor;
import com.liferay.osb.asah.common.repository.helper.DSLHelper;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record3;
import org.jooq.SelectOnConditionStep;
import org.jooq.impl.DSL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;

/**
 * @author Robson Pastor
 */
public class BQSessionInterestScoreRepositoryImpl
	extends BaseRepository implements CustomBQSessionInterestScoreRepository {

	public BQSessionInterestScoreRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;

		InterestFilterStringConverterHelper
			interestFilterStringConverterHelper =
				new InterestFilterStringConverterHelper();

		_fieldNames =
			interestFilterStringConverterHelper.getFieldNameConversionMap();
	}

	@Override
	public CompositionResultBag getInterestCompositionResultBag(
		@Nullable Long channelId, Pageable pageable, TimeRange timeRange) {

		SelectOnConditionStep<Record3<String, String, Integer>>
			selectSelectStep = _dslContext.select(
				DSL.field("BQSessionInterestScore.sessionId", String.class),
				DSL.field("BQSessionInterestScore.keyword", String.class),
				DSL.countDistinct(
					DSL.field("BQSessionInterestScore.sessionId")
				).over(
				).as(
					"totalCount"
				)
			).from(
				"BQSessionInterestScore"
			).join(
				"BQSession"
			).on(
				DSL.field(
					"BQSessionInterestScore.sessionId"
				).eq(
					DSL.field("BQSession.id")
				)
			);

		List<Condition> conditions = new ArrayList<>();

		if (channelId != null) {
			conditions.add(
				DSL.field(
					"BQSession.channelId", Long.class
				).eq(
					channelId
				));
		}

		conditions.add(
			DSL.field(
				"BQSessionInterestScore.interested", Boolean.class
			).eq(
				Boolean.TRUE
			));
		conditions.add(
			DSL.field(
				"BQSession.sessionStart", Date.class
			).between(
				timeRange.getStartDate(), timeRange.getEndDate()
			));

		List<Map<String, Object>> records = _queryExecutor.queryForList(
			Function.identity(),
			_dslContext.with(
				"KeywordSession"
			).as(
				selectSelectStep.where(conditions)
			).select(
				DSL.count(
					DSL.field("keyword")
				).as(
					"count"
				),
				DSL.field("keyword"),
				DSL.max(
					DSL.count(DSL.field("keyword"))
				).over(
				).as(
					"maxCount"
				),
				DSL.count(
					DSL.field("keyword")
				).over(
				).as(
					"total"
				),
				DSL.field("totalCount")
			).from(
				DSL.table("KeywordSession")
			).groupBy(
				DSL.field("keyword"), DSL.field("totalCount")
			).orderBy(
				DSL.count(
				).desc(),
				DSL.field("keyword")
			).limit(
				pageable.getPageSize()
			).offset(
				pageable.getOffset()
			));

		BigDecimal maxCountBigDecimal = BigDecimal.ZERO;
		BigDecimal totalBigDecimal = BigDecimal.ZERO;
		BigDecimal totalCountBigDecimal = BigDecimal.ZERO;

		List<Composition> compositions = new ArrayList<>();

		for (int i = 0; i < records.size(); i++) {
			Map<String, Object> record = records.get(i);

			if (i == 0) {
				maxCountBigDecimal = new BigDecimal(
					String.valueOf(record.get("maxCount")));
				totalBigDecimal = new BigDecimal(
					String.valueOf(record.get("total")));
				totalCountBigDecimal = new BigDecimal(
					String.valueOf(record.get("totalCount")));
			}

			BigDecimal count = new BigDecimal(
				String.valueOf(record.get("count")));

			compositions.add(
				new Composition(
					count.longValue(), (String)record.get("keyword")));
		}

		return new CompositionResultBag(
			maxCountBigDecimal.longValue(), compositions,
			totalBigDecimal.longValue(), totalCountBigDecimal.longValue());
	}

	private final DSLContext _dslContext;

	@Autowired
	private DSLHelper _dslHelper;

	private final Map<String, String> _fieldNames;

	@Autowired
	private QueryExecutor _queryExecutor;

}