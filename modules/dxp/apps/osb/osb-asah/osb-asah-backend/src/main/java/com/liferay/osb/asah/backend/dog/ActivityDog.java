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

package com.liferay.osb.asah.backend.dog;

import com.liferay.osb.asah.backend.model.Activity;
import com.liferay.osb.asah.backend.model.ResultBag;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class ActivityDog {

	public ResultBag<Activity> getActivityResultBag(
		String ownerId, int size, int start) {

		SearchHits searchHits = _dataDog.querySearchHits(
			"activities", _faroInfoElasticsearchInvoker,
			DogUtil.buildSearchSourceBuilder(
				Collections.emptyList(),
				QueryBuilders.termQuery("ownerId", ownerId), size, start));

		return DogUtil.createResultBag(Activity.class, searchHits);
	}

	public ResultBag<Activity> getActivityResultBag(
		String applicationId, String eventContextFilterString, String eventId,
		int size, int start) {

		SearchHits searchHits = _dataDog.querySearchHits(
			"activities", _faroInfoElasticsearchInvoker,
			DogUtil.buildSearchSourceBuilder(
				Collections.emptyList(),
				_buildQueryBuilder(
					applicationId, eventContextFilterString, eventId),
				size, start));

		return DogUtil.createResultBag(Activity.class, searchHits);
	}

	private QueryBuilder _buildQueryBuilder(String eventContextFilterString) {
		List<String> tokens = _getFilterTokens(eventContextFilterString);

		if (tokens.size() != 3) {
			throw new IllegalArgumentException(
				"Invalid filter " + eventContextFilterString);
		}

		if (Objects.equals(tokens.get(1), "~")) {
			return QueryBuilders.regexpQuery(
				"eventContext." + tokens.get(0), tokens.get(2));
		}

		return QueryBuilders.termQuery(
			"eventContext." + tokens.get(0), tokens.get(2));
	}

	private QueryBuilder _buildQueryBuilder(
		String applicationId, String eventContextFilterString, String eventId) {

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.termQuery("applicationId", applicationId)
		).filter(
			QueryBuilders.termQuery("eventId", eventId)
		);

		if (StringUtils.isNotBlank(eventContextFilterString)) {
			boolQueryBuilder.filter(
				_buildQueryBuilder(eventContextFilterString));
		}

		return boolQueryBuilder;
	}

	private List<String> _getFilterTokens(String eventContextFilterString) {
		List<String> tokens = new ArrayList<>();

		for (String token : eventContextFilterString.split(" ")) {
			if (!StringUtils.isBlank(token)) {
				tokens.add(token);
			}
		}

		return tokens;
	}

	@PostConstruct
	private void _init() {
		_faroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forFaroInfo();
	}

	@Autowired
	private DataDog _dataDog;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}