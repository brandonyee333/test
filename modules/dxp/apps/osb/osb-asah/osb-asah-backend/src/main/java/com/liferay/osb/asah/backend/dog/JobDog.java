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
import com.fasterxml.jackson.datatype.jsonorg.JsonOrgModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import com.liferay.osb.asah.backend.model.Job;
import com.liferay.osb.asah.backend.model.JobParameter;
import com.liferay.osb.asah.backend.model.JobType;
import com.liferay.osb.asah.backend.model.ResultBag;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchBulkRequestBuilder;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.elasticsearch.QueryUtil;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoOSBAsahTaskDog;

import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class JobDog {

	public Job addJob(
		boolean active, String cronExpression, List<JobParameter> jobParameters,
		JobType jobType, String name) {

		JSONObject jsonObject = new JSONObject();

		jsonObject.put("active", active);
		jsonObject.put("cronExpression", cronExpression);
		jsonObject.put("name", name);
		jsonObject.put(
			"parameters",
			_objectMapper.convertValue(jobParameters, JSONArray.class));
		jsonObject.put("type", jobType.toString());

		jsonObject = _faroInfoElasticsearchInvoker.add("jobs", jsonObject);

		_faroInfoOSBAsahTaskDog.scheduleOSBAsahTask(
			_jobTypeNaniteMap.get(jobType), jsonObject, cronExpression);

		try {
			return _objectMapper.readValue(jsonObject.toString(), Job.class);
		}
		catch (IOException ioe) {
			throw new RuntimeException("Unable to process update request", ioe);
		}
	}

	public List<Boolean> deleteJobs(List<String> ids) {
		List<Boolean> statuses = new ArrayList<>();

		ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder =
			_faroInfoElasticsearchInvoker.
				createElasticsearchBulkRequestBuilder();

		for (String id : ids) {
			elasticsearchBulkRequestBuilder.delete("jobs", id);
		}

		BulkResponse bulkResponse = elasticsearchBulkRequestBuilder.get();

		bulkResponse.forEach(
			bulkItemResponse -> statuses.add(!bulkItemResponse.isFailed()));

		return statuses;
	}

	public Job getJob(String id) {
		SearchHits searchHits = _dataDog.querySearchHits(
			"jobs", _faroInfoElasticsearchInvoker,
			_buildJobSearchSourceBuilder(id));

		if (searchHits.getTotalHits() != 1) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					String.format(
						"Unable to retrieve the job definition for the job " +
							"ID %s. Returned %d total hits.",
						id, searchHits.getTotalHits()));
			}

			return null;
		}

		SearchHit searchHit = searchHits.getAt(0);

		String source = searchHit.getSourceAsString();

		try {
			return _objectMapper.readValue(source, Job.class);
		}
		catch (IOException ioe) {
			throw new RuntimeException("Unable to process search request", ioe);
		}
	}

	public ResultBag<Job> getJobResultBag(
		String keywords, int size, Map<String, String> sort, int start) {

		SearchHits searchHits = _dataDog.querySearchHits(
			"jobs", _faroInfoElasticsearchInvoker,
			DogUtil.buildSearchSourceBuilder(
				SortBuilderUtil.fieldSort(sort),
				_buildKeywordsQueryBuilder(keywords), size, start));

		return DogUtil.createResultBag(Job.class, searchHits);
	}

	private SearchSourceBuilder _buildJobSearchSourceBuilder(String jobId) {
		SearchSourceBuilder searchSourceBuilder =
			SearchSourceBuilder.searchSource();

		searchSourceBuilder.query(QueryBuilders.termQuery("id", jobId));
		searchSourceBuilder.size(1);

		return searchSourceBuilder;
	}

	private QueryBuilder _buildKeywordsQueryBuilder(String keywords) {
		if (StringUtils.isBlank(keywords)) {
			return QueryBuilders.matchAllQuery();
		}

		BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();

		boolQueryBuilder.filter(
			BoolQueryBuilderUtil.should(
				QueryBuilders.queryStringQuery(
					String.format(
						"name.search:*%1$s*",
						QueryUtil.escapeKeywords(keywords)))
			).should(
				QueryBuilders.matchQuery(
					"name.search", keywords
				).fuzziness(
					Fuzziness.AUTO
				)
			));

		return boolQueryBuilder;
	}

	@PostConstruct
	private void _init() {
		_faroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forFaroInfo();
	}

	private static final Log _log = LogFactory.getLog(JobDog.class);

	@Autowired
	private DataDog _dataDog;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private FaroInfoOSBAsahTaskDog _faroInfoOSBAsahTaskDog;

	private final Map<JobType, String> _jobTypeNaniteMap =
		new HashMap<JobType, String>() {
			{
				put(
					JobType.CONTENT_RECOMMENDATION_ITEM_SIMILARITY,
					"ContentRecommendationDataPreparationNanite");
			}
		};

	private final ObjectMapper _objectMapper = new ObjectMapper() {
		{
			disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

			registerModule(new JavaTimeModule());
			registerModule(new Jdk8Module());
			registerModule(new JsonOrgModule());
		}
	};

}