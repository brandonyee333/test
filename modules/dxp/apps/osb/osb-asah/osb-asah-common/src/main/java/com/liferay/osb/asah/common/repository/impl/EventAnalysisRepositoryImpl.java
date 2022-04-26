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

import com.liferay.osb.asah.common.entity.EventAnalysis;
import com.liferay.osb.asah.common.repository.CustomEventAnalysisRepository;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.SelectSelectStep;
import org.jooq.impl.DSL;

import org.json.JSONArray;

import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;

/**
 * @author Rachael Koestartyo
 */
public class EventAnalysisRepositoryImpl
	extends BaseRepository implements CustomEventAnalysisRepository {

	public EventAnalysisRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	@Override
	public long countEventAnalyses(Long channelId, @Nullable String keywords) {
		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.selectCount();

		return selectSelectStep.from(
			"EventAnalysis"
		).where(
			_getCondition(channelId, keywords)
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	@Override
	public List<EventAnalysis> searchEventAnalyses(
		Long channelId, @Nullable String keywords, Pageable pageable) {

		return _dslContext.select(
			DSL.asterisk()
		).from(
			"EventAnalysis"
		).where(
			_getCondition(channelId, keywords)
		).orderBy(
			getSortFields(pageable.getSort(), null)
		).limit(
			pageable.getPageSize()
		).offset(
			pageable.getOffset()
		).fetch(
			record -> {
				Map<String, Object> recordMap = record.intoMap();

				EventAnalysis eventAnalysis = new EventAnalysis(recordMap);

				eventAnalysis.setEventAnalysisBreakdownJSONArray(
					new JSONArray(String.valueOf(recordMap.get("breakdowns"))));
				eventAnalysis.setEventAnalysisFilterJSONArray(
					new JSONArray(String.valueOf(recordMap.get("filters"))));

				return eventAnalysis;
			}
		);
	}

	private Condition _getCondition(Long channelId, String keywords) {
		Condition condition = DSL.field(
			"EventAnalysis.channelId"
		).eq(
			channelId
		);

		if (StringUtils.isNotEmpty(keywords)) {
			condition = condition.and(
				DSL.field(
					"name"
				).containsIgnoreCase(
					keywords
				));
		}

		return condition;
	}

	private final DSLContext _dslContext;

}