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

package com.liferay.osb.asah.backend.dog.ml;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsonorg.JsonOrgModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import com.liferay.osb.asah.backend.dog.DataDog;
import com.liferay.osb.asah.backend.model.ml.Job;
import com.liferay.osb.asah.backend.model.ml.JobParameter;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoOSBAsahTaskDog;

import java.io.IOException;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
		String name) {

		JSONObject jsonObject = new JSONObject();

		jsonObject.put("active", active);
		jsonObject.put("cronExpression", cronExpression);
		jsonObject.put("name", name);
		jsonObject.put(
			"parameters",
			_objectMapper.convertValue(jobParameters, JSONArray.class));

		jsonObject = _faroInfoElasticsearchInvoker.add("jobs", jsonObject);

		_faroInfoOSBAsahTaskDog.scheduleOSBAsahTask(
			"JobExecutorNanite", jsonObject, cronExpression);

		try {
			return _objectMapper.readValue(jsonObject.toString(), Job.class);
		}
		catch (IOException ioe) {
			throw new RuntimeException("Unable to process update request", ioe);
		}
	}

	public boolean deleteJob(String id) {
		return _faroInfoElasticsearchInvoker.delete("jobs", id);
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

	private SearchSourceBuilder _buildJobSearchSourceBuilder(String jobId) {
		SearchSourceBuilder searchSourceBuilder =
			SearchSourceBuilder.searchSource();

		searchSourceBuilder.query(QueryBuilders.termQuery("id", jobId));
		searchSourceBuilder.size(1);

		return searchSourceBuilder;
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