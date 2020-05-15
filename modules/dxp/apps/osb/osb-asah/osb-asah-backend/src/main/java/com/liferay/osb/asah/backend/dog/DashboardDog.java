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

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import com.liferay.osb.asah.backend.model.Dashboard;
import com.liferay.osb.asah.backend.model.ResultBag;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.elasticsearch.QueryUtil;

import java.io.IOException;
import java.io.InputStream;

import java.time.Clock;
import java.time.Instant;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;

import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;

import org.json.JSONObject;
import org.json.JSONTokener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author André Miranda
 */
@Component
public class DashboardDog {

	public Dashboard getDashboard(String dashboardId) {
		SearchHits searchHits = _dataDog.querySearchHits(
			"custom-asset-dashboards", _cerebroInfoElasticsearchInvoker,
			_buildDashboardSearchSourceBuilder(dashboardId));

		if (searchHits.getTotalHits() != 1) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					String.format(
						"Unable to retrieve the dashboard definition for the " +
							"dashboard ID %s. Returned %d total hits.",
						dashboardId, searchHits.getTotalHits()));
			}

			return null;
		}

		SearchHit searchHit = searchHits.getAt(0);

		String source = searchHit.getSourceAsString();

		try {
			return _objectMapper.readValue(source, Dashboard.class);
		}
		catch (IOException ioe) {
			throw new RuntimeException("Unable to process search request", ioe);
		}
	}

	public ResultBag<Dashboard> getDashboardResultBag(
		String channelId, FieldSortBuilder fieldSortBuilder, String keywords,
		int size, int start) {

		SearchHits searchHits = _dataDog.querySearchHits(
			"custom-asset-dashboards", _cerebroInfoElasticsearchInvoker,
			DogUtil.buildSearchSourceBuilder(
				fieldSortBuilder,
				_buildKeywordsQueryBuilder(channelId, keywords), size, start));

		ResultBag<Dashboard> resultBag = new ResultBag<>();

		try {
			List<Dashboard> dashboards = new ArrayList<>();

			for (SearchHit searchHit : searchHits) {
				String source = searchHit.getSourceAsString();

				dashboards.add(
					_objectMapper.readValue(source, Dashboard.class));
			}

			resultBag.setResults(dashboards);
			resultBag.setTotal(searchHits.getTotalHits());

			return resultBag;
		}
		catch (IOException ioe) {
			throw new RuntimeException("Unable to process search request", ioe);
		}
	}

	public Dashboard updateDashboard(
		String dashboardId, String definition, String modifiedByUserId,
		String modifiedByUserName) {

		_validate(definition);

		JSONObject jsonObject = new JSONObject();

		jsonObject.put("definition", definition);
		jsonObject.put("id", dashboardId);
		jsonObject.put("modifiedByUserId", modifiedByUserId);
		jsonObject.put("modifiedByUserName", modifiedByUserName);
		jsonObject.put("modifiedDate", Instant.now(Clock.systemUTC()));

		jsonObject = _cerebroInfoElasticsearchInvoker.update(
			"custom-asset-dashboards", jsonObject);

		try {
			return _objectMapper.readValue(
				jsonObject.toString(), Dashboard.class);
		}
		catch (IOException ioe) {
			throw new RuntimeException("Unable to process update request", ioe);
		}
	}

	private SearchSourceBuilder _buildDashboardSearchSourceBuilder(
		String dashboardId) {

		SearchSourceBuilder searchSourceBuilder =
			SearchSourceBuilder.searchSource();

		searchSourceBuilder.query(QueryBuilders.termQuery("id", dashboardId));
		searchSourceBuilder.size(1);

		return searchSourceBuilder;
	}

	private QueryBuilder _buildKeywordsQueryBuilder(
		String channelId, String keywords) {

		BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();

		BoolQueryBuilderUtil.filterTerm(
			boolQueryBuilder, "channelId", channelId);

		if (StringUtils.isNotBlank(keywords)) {
			boolQueryBuilder.filter(
				BoolQueryBuilderUtil.should(
					QueryBuilders.queryStringQuery(
						String.format(
							"%s:*%s*", "assetTitle.search",
							QueryUtil.escapeKeywords(keywords)))
				).should(
					QueryBuilders.matchQuery(
						"assetTitle.search", keywords
					).fuzziness(
						Fuzziness.AUTO
					)
				));
		}

		if (boolQueryBuilder.hasClauses()) {
			return boolQueryBuilder;
		}

		return QueryBuilders.matchAllQuery();
	}

	@PostConstruct
	private void _init() {
		_cerebroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forCerebroInfo();

		Class<?> clazz = getClass();

		try (InputStream inputStream = clazz.getResourceAsStream(
				"dashboard-definition-schema.json")) {

			_dashboardDefinitionSchema = SchemaLoader.load(
				new JSONObject(new JSONTokener(inputStream)));
		}
		catch (IOException ioe) {
			_log.error(ioe);

			throw new IllegalStateException(
				"Unable to read dashboard definition schema", ioe);
		}
	}

	private void _validate(String definition) throws ValidationException {
		_dashboardDefinitionSchema.validate(new JSONObject(definition));
	}

	private static final Log _log = LogFactory.getLog(DashboardDog.class);

	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;
	private Schema _dashboardDefinitionSchema;

	@Autowired
	private DataDog _dataDog;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	private final ObjectMapper _objectMapper = new ObjectMapper() {
		{
			disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

			registerModule(new JavaTimeModule());
			registerModule(new Jdk8Module());
		}
	};

}