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
import com.liferay.osb.asah.backend.model.PropertyFilter;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.model.ResultBag;
import com.liferay.osb.asah.common.util.MapUtil;
import com.liferay.osb.asah.common.util.SetUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
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
		String applicationId, List<PropertyFilter> propertyFilters,
		String eventId, int rangeKey, int size, int start) {

		SearchHits searchHits = _dataDog.querySearchHits(
			"activities", _faroInfoElasticsearchInvoker,
			DogUtil.buildSearchSourceBuilder(
				Collections.emptyList(),
				_buildQueryBuilder(
					applicationId, propertyFilters, eventId, rangeKey),
				size, start));

		return DogUtil.createResultBag(Activity.class, searchHits);
	}

	public List<String> getEventContextKeys() {
		List<String> eventContextKeys = new ArrayList<>();

		Map<String, Object> indexMappings =
			_elasticsearchIndexManager.getIndexMappings(
				"activities", WeDeployDataService.OSB_ASAH_FARO_INFO);

		Map<String, Object> indexProperties = MapUtil.get(
			indexMappings, "properties");

		Map<String, Object> eventContext = MapUtil.get(
			indexProperties, "eventContext");

		Map<String, Object> eventContextProperties = MapUtil.get(
			eventContext, "properties");

		for (Map.Entry<String, Object> entry :
				eventContextProperties.entrySet()) {

			Map<String, Object> fieldMapping =
				(Map<String, Object>)entry.getValue();

			if (!_eventContextKeysExclude.contains(entry.getKey()) &&
				Objects.equals(
					MapUtil.getString(fieldMapping, "type"), "keyword")) {

				eventContextKeys.add(entry.getKey());
			}
		}

		return eventContextKeys;
	}

	private QueryBuilder _buildQueryBuilder(
		String applicationId, List<PropertyFilter> propertyFilters,
		String eventId, int rangeKey) {

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.termQuery("applicationId", applicationId)
		).filter(
			QueryBuilders.termQuery("eventId", eventId)
		).filter(
			_buildRangeQueryBuilder("startTime", rangeKey)
		);

		DogUtil.addBoolQueryBuilderPropertyFilters(
			boolQueryBuilder, propertyFilters);

		return boolQueryBuilder;
	}

	private RangeQueryBuilder _buildRangeQueryBuilder(
		String fieldName, int rangeKey) {

		RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(
			fieldName);

		rangeQueryBuilder.timeZone("UTC");

		rangeQueryBuilder.gte(String.format("now-%sd/d", rangeKey));
		rangeQueryBuilder.lt("now/d");

		return rangeQueryBuilder;
	}

	@Autowired
	private DataDog _dataDog;

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

	private final Set<String> _eventContextKeysExclude = SetUtil.of(
		"crawler", "devicePixelRatio", "experienceId", "experimentId",
		"referrer", "screenHeight", "screenHeightSize", "screenWidth",
		"screenWidthSize", "timezoneOffset", "userAgent", "variantId");

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}