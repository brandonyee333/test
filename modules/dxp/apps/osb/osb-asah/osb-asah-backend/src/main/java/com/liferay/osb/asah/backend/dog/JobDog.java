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
import com.liferay.osb.asah.backend.model.JobRun;
import com.liferay.osb.asah.backend.model.JobRunStatus;
import com.liferay.osb.asah.backend.model.JobStatus;
import com.liferay.osb.asah.backend.model.JobTrainingFrequency;
import com.liferay.osb.asah.backend.model.JobTrainingPeriod;
import com.liferay.osb.asah.backend.model.JobType;
import com.liferay.osb.asah.backend.model.ResultBag;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.elasticsearch.QueryUtil;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoOSBAsahTaskDog;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Sort;

import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;

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
		List<JobParameter> jobParameters,
		JobTrainingFrequency jobTrainingFrequency,
		JobTrainingPeriod jobTrainingPeriod, JobType jobType, String name) {

		JSONObject jsonObject = new JSONObject();

		jsonObject.put("name", name);
		jsonObject.put(
			"parameters",
			_objectMapper.convertValue(jobParameters, JSONArray.class));
		jsonObject.put("trainingFrequency", jobTrainingFrequency.toString());
		jsonObject.put("trainingPeriod", jobTrainingPeriod.toString());
		jsonObject.put("type", jobType.toString());

		_scheduleOSBAsahTask(jsonObject);

		jsonObject = _faroInfoElasticsearchInvoker.add("jobs", jsonObject);

		return _deserializeJob(jsonObject.toString());
	}

	public List<Boolean> deleteJobs(List<String> ids) {
		List<Boolean> statuses = new ArrayList<>();

		for (String id : ids) {
			statuses.add(_deleteJob(id));
		}

		return statuses;
	}

	public Job fetchJob(String name) {
		SearchHits searchHits = _dataDog.querySearchHits(
			"jobs", _faroInfoElasticsearchInvoker,
			_buildJobSearchSourceBuilder("name", name));

		if (searchHits.getTotalHits() == 0) {
			return null;
		}

		SearchHit searchHit = searchHits.getAt(0);

		return _deserializeJob(searchHit.getSourceAsString());
	}

	public Job getJob(String id) {
		SearchHits searchHits = _dataDog.querySearchHits(
			"jobs", _faroInfoElasticsearchInvoker,
			_buildJobSearchSourceBuilder("id", id));

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

		return _deserializeJob(searchHit.getSourceAsString());
	}

	public ResultBag<Job> getJobResultBag(
		String keywords, int size, Sort sort, int start) {

		SearchHits searchHits = _dataDog.querySearchHits(
			"jobs", _faroInfoElasticsearchInvoker,
			DogUtil.buildSearchSourceBuilder(
				SortBuilderUtil.fieldSort(sort),
				_buildKeywordsQueryBuilder(keywords), size, start));

		return DogUtil.createResultBag(Job.class, searchHits);
	}

	public ResultBag<JobRun> getJobRunResultBag(
		String jobId, int size, Sort sort, int start) {

		SearchHits searchHits = _dataDog.querySearchHits(
			"job-runs", _faroInfoElasticsearchInvoker,
			DogUtil.buildSearchSourceBuilder(
				SortBuilderUtil.fieldSort(sort),
				QueryBuilders.termQuery("job.id", jobId), size, start));

		return DogUtil.createResultBag(JobRun.class, searchHits);
	}

	public JobStatus getJobStatus(String id) {
		if (_hasJobCompleted(id)) {
			return JobStatus.READY;
		}

		JSONObject jobRunJSONObject = _faroInfoElasticsearchInvoker.fetch(
			"job-runs", QueryBuilders.termQuery("job.id", id),
			SortBuilderUtil.fieldSort("id", SortOrder.DESC), null, null);

		if (jobRunJSONObject != null) {
			if (Objects.equals(
					jobRunJSONObject.getString("status"), "RUNNING")) {

				return JobStatus.TRAINING;
			}

			return JobStatus.FAILED;
		}

		Job job = getJob(id);

		if (job.getJobTrainingFrequency() == JobTrainingFrequency.MANUAL) {
			return JobStatus.PENDING;
		}

		return JobStatus.SCHEDULED;
	}

	public String getJobTrainingDateString(String id) {
		JSONObject jobRunJSONObject = _faroInfoElasticsearchInvoker.fetch(
			"job-runs",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("job.id", id)
			).filter(
				QueryBuilders.termQuery("status", "COMPLETED")
			),
			SortBuilderUtil.fieldSort("id", SortOrder.DESC), null, null);

		if (jobRunJSONObject == null) {
			return null;
		}

		return jobRunJSONObject.getString("completedDate");
	}

	public Job runJob(String id) {
		JobRun jobRun = _fetchLatestJobRun(id);

		if ((jobRun != null) &&
			(jobRun.getJobRunStatus() == JobRunStatus.RUNNING)) {

			throw new IllegalStateException(
				String.format(
					"Unable to run job %s because it is already running", id));
		}

		Job job = getJob(id);

		_faroInfoOSBAsahTaskDog.addOSBAsahTask(
			_jobTypeNaniteMap.get(job.getJobType()),
			JSONUtil.put(
				"job", _objectMapper.convertValue(job, JSONObject.class)));

		return job;
	}

	public Job updateJob(
		String id, List<JobParameter> jobParameters,
		JobTrainingFrequency jobTrainingFrequency,
		JobTrainingPeriod jobTrainingPeriod, String name) {

		JSONObject jsonObject = _faroInfoElasticsearchInvoker.get("jobs", id);

		String oldTrainingFrequency = jsonObject.getString("trainingFrequency");

		jsonObject.put("name", name);
		jsonObject.put(
			"parameters",
			_objectMapper.convertValue(jobParameters, JSONArray.class));
		jsonObject.put("trainingFrequency", jobTrainingFrequency.toString());
		jsonObject.put("trainingPeriod", jobTrainingPeriod.toString());

		_rescheduleOSBAsahTask(jsonObject, oldTrainingFrequency);

		jsonObject = _faroInfoElasticsearchInvoker.update(
			"jobs", id, jsonObject);

		return _deserializeJob(jsonObject.toString());
	}

	private SearchSourceBuilder _buildJobSearchSourceBuilder(
		String fieldName, String fieldValue) {

		SearchSourceBuilder searchSourceBuilder =
			SearchSourceBuilder.searchSource();

		searchSourceBuilder.query(
			QueryBuilders.termQuery(fieldName, fieldValue));
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

	private boolean _deleteJob(String id) {
		JSONObject jsonObject = _faroInfoElasticsearchInvoker.get("jobs", id);

		_unscheduleOSBAsahTask(jsonObject);

		BulkByScrollResponse bulkByScrollResponse =
			_faroInfoElasticsearchInvoker.deleteByQuery(
				QueryBuilders.termQuery("job.id", jsonObject.getString("id")),
				true, "job-runs");

		List<BulkItemResponse.Failure> bulkFailures =
			bulkByScrollResponse.getBulkFailures();

		if (!bulkFailures.isEmpty()) {
			return false;
		}

		return _faroInfoElasticsearchInvoker.delete(
			"jobs", jsonObject.getString("id"));
	}

	private Job _deserializeJob(String json) {
		try {
			return _objectMapper.readValue(json, Job.class);
		}
		catch (IOException ioe) {
			throw new RuntimeException("Unable to deserialize job JSON", ioe);
		}
	}

	private JobRun _fetchLatestJobRun(String jobId) {
		ResultBag<JobRun> resultBag = getJobRunResultBag(
			jobId, 1, Sort.desc("id"), 0);

		if (resultBag.getTotal() == 0) {
			return null;
		}

		List<JobRun> results = resultBag.getResults();

		return results.get(0);
	}

	private boolean _hasJobCompleted(String id) {
		return _faroInfoElasticsearchInvoker.exists(
			"job-runs",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("job.id", id)
			).filter(
				QueryBuilders.termQuery("status", "COMPLETED")
			));
	}

	@PostConstruct
	private void _init() {
		_faroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forFaroInfo();
	}

	private void _rescheduleOSBAsahTask(
		JSONObject jobJSONObject, String oldTrainingFrequency) {

		JobTrainingFrequency newJobTrainingFrequency =
			JobTrainingFrequency.valueOf(
				jobJSONObject.getString("trainingFrequency"));
		JobTrainingFrequency oldJobTrainingFrequency =
			JobTrainingFrequency.valueOf(oldTrainingFrequency);

		if (newJobTrainingFrequency == oldJobTrainingFrequency) {
			return;
		}

		_unscheduleOSBAsahTask(jobJSONObject);

		_scheduleOSBAsahTask(jobJSONObject);
	}

	private void _scheduleOSBAsahTask(JSONObject jobJSONObject) {
		JobTrainingFrequency jobTrainingFrequency =
			JobTrainingFrequency.valueOf(
				jobJSONObject.getString("trainingFrequency"));

		if (jobTrainingFrequency == JobTrainingFrequency.MANUAL) {
			return;
		}

		JSONObject osbAsahTaskJSONObject =
			_faroInfoOSBAsahTaskDog.scheduleOSBAsahTask(
				_jobTypeNaniteMap.get(
					JobType.valueOf(jobJSONObject.getString("type"))),
				JSONUtil.put("job", jobJSONObject),
				jobTrainingFrequency.getCronExpression());

		jobJSONObject.put(
			"osbAsahTaskId", osbAsahTaskJSONObject.getString("id"));
	}

	private void _unscheduleOSBAsahTask(JSONObject jobJSONObject) {
		String osbAsahTaskId = jobJSONObject.optString("osbAsahTaskId", "");

		if (StringUtils.isBlank(osbAsahTaskId)) {
			return;
		}

		_faroInfoOSBAsahTaskDog.unscheduleOSBAsahTask(osbAsahTaskId);

		jobJSONObject.put("osbAsahTaskId", "");
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