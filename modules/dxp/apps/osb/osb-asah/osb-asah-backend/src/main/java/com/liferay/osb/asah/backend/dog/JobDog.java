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

import com.liferay.osb.asah.backend.model.Job;
import com.liferay.osb.asah.backend.model.JobParameter;
import com.liferay.osb.asah.backend.model.JobRun;
import com.liferay.osb.asah.backend.model.JobRunDataPeriod;
import com.liferay.osb.asah.backend.model.JobRunFrequency;
import com.liferay.osb.asah.backend.model.JobRunStatus;
import com.liferay.osb.asah.backend.model.JobRunsMonthlyStatistics;
import com.liferay.osb.asah.backend.model.JobStatus;
import com.liferay.osb.asah.backend.model.JobType;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchRepository;
import com.liferay.osb.asah.common.elasticsearch.QueryUtil;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoOSBAsahTaskDog;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.ResultBag;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.util.ObjectMapperUtil;

import java.time.LocalDateTime;
import java.time.ZoneId;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.TimeZone;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;

import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.support.CronSequenceGenerator;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class JobDog {

	public Job addJob(
		List<JobParameter> jobParameters, JobRunDataPeriod jobRunDataPeriod,
		JobRunFrequency jobRunFrequency, JobType jobType, String name,
		boolean runNow) {

		Job job = new Job();

		Date date = new Date();

		job.setCreatedDate(date);
		job.setLastUpdatedDate(date);

		job.setName(name);
		job.setJobParameters(jobParameters);
		job.setJobRunDataPeriod(jobRunDataPeriod);
		job.setJobRunFrequency(jobRunFrequency);
		job.setJobType(jobType);

		job = _jobElasticsearchRepository.add(job);

		_scheduleOSBAsahTask(job);

		if (runNow) {
			runJob(job.getId(), jobRunDataPeriod);
		}

		return job;
	}

	public List<Boolean> deleteJobs(List<String> ids) {
		List<Boolean> statuses = new ArrayList<>();

		for (String id : ids) {
			statuses.add(_deleteJob(id));
		}

		return statuses;
	}

	public Job fetchJob(String name) {
		return _jobElasticsearchRepository.fetchFirst(
			searchSourceBuilder -> searchSourceBuilder.query(
				QueryBuilders.termQuery("name", name)));
	}

	public Job getJob(String id) {
		return _jobElasticsearchRepository.get(id);
	}

	public String getJobNextRunDateString(String id) {
		Job job = getJob(id);

		JobRunFrequency jobRunFrequency = job.getJobRunFrequency();

		if (jobRunFrequency == JobRunFrequency.MANUAL) {
			return null;
		}

		JobRun jobRun = _jobRunElasticsearchRepository.fetchFirst(
			searchSourceBuilder -> {
				searchSourceBuilder.query(
					BoolQueryBuilderUtil.filter(
						QueryBuilders.termQuery("job.id", id)
					).filter(
						QueryBuilders.termQuery("trigger", "SCHEDULE")
					));
				searchSourceBuilder.sort(
					SortBuilderUtil.fieldSort("id", SortOrder.DESC));
			});

		Date startDate = job.getLastUpdatedDate();

		if (jobRun != null) {
			startDate = jobRun.getCreatedDate();
		}

		CronSequenceGenerator cronSequenceGenerator = new CronSequenceGenerator(
			jobRunFrequency.getCronExpression(), TimeZone.getTimeZone("UTC"));

		return DateUtil.toUTCString(cronSequenceGenerator.next(startDate));
	}

	public ResultBag<Job> getJobResultBag(
		String keywords, int size, Sort sort, int start) {

		return _jobElasticsearchRepository.search(
			_buildKeywordsQueryBuilder(keywords), size, sort, start);
	}

	public String getJobRunDateString(String id) {
		JobRun jobRun = _jobRunElasticsearchRepository.fetchFirst(
			searchSourceBuilder -> {
				searchSourceBuilder.query(
					BoolQueryBuilderUtil.filter(
						QueryBuilders.termQuery("job.id", id)
					).filter(
						QueryBuilders.termQuery("status", "PUBLISHED")
					));
				searchSourceBuilder.sort(
					SortBuilderUtil.fieldSort("id", SortOrder.DESC));
			});

		if (jobRun == null) {
			return null;
		}

		return jobRun.getCompletedDateISO();
	}

	public ResultBag<JobRun> getJobRunResultBag(
		String jobId, int size, Sort sort, int start) {

		return _jobRunElasticsearchRepository.search(
			QueryBuilders.termQuery("job.id", jobId), size, sort, start);
	}

	public JobRunsMonthlyStatistics getJobRunsMonthlyStatistics(String id) {
		ResultBag<JobRun> jobRunResultBag = _getCurrentMonthJobRunResultBag(
			id, 20, Sort.desc("id"), 0);

		List<JobRun> jobRuns = jobRunResultBag.getResults();

		return new JobRunsMonthlyStatistics() {
			{
				setCompletedJobRuns(
					_countJobRunsByStatus(jobRuns, JobRunStatus.COMPLETED));
				setFailedJobRuns(
					_countJobRunsByStatus(jobRuns, JobRunStatus.FAILED));
				setMaxJobRuns(_maxMonthlyJobRuns);
				setPublishedJobRuns(
					_countJobRunsByStatus(jobRuns, JobRunStatus.PUBLISHED));
				setRunningJobRuns(
					_countJobRunsByStatus(jobRuns, JobRunStatus.RUNNING));
				setScheduledJobRuns(
					_countCurrentMonthScheduledJobRuns(jobRuns, getJob(id)));
			}
		};
	}

	public JobStatus getJobStatus(String id) {
		if (_hasJobRunPublished(id)) {
			return JobStatus.READY;
		}

		JobRun jobRun = _jobRunElasticsearchRepository.fetchFirst(
			searchSourceBuilder -> {
				searchSourceBuilder.query(
					QueryBuilders.termQuery("job.id", id));
				searchSourceBuilder.sort(
					SortBuilderUtil.fieldSort("id", SortOrder.DESC));
			});

		if (jobRun != null) {
			if (jobRun.getJobRunStatus() == JobRunStatus.RUNNING) {
				return JobStatus.RUNNING;
			}

			return JobStatus.FAILED;
		}

		Job job = getJob(id);

		if (job.getJobRunFrequency() == JobRunFrequency.MANUAL) {
			return JobStatus.PENDING;
		}

		return JobStatus.SCHEDULED;
	}

	public Job runJob(String id, JobRunDataPeriod jobRunDataPeriod) {
		JobRun jobRun = _fetchLatestJobRun(id);

		if ((jobRun != null) &&
			(jobRun.getJobRunStatus() == JobRunStatus.RUNNING)) {

			throw new IllegalStateException(
				String.format(
					"Unable to run job ID %s because it is already running",
					id));
		}

		JobRunsMonthlyStatistics jobRunsMonthlyStatistics =
			getJobRunsMonthlyStatistics(id);

		if (jobRunsMonthlyStatistics.getAvailableJobRuns() == 0) {
			throw new IllegalStateException(
				String.format(
					"Unable to run job ID %s because this run surpasses the " +
						"maximum allowed monthly runs threshold",
					id));
		}

		Job job = getJob(id);

		_faroInfoOSBAsahTaskDog.addOSBAsahTask(
			_jobTypeNaniteMap.get(job.getJobType()),
			JSONUtil.put(
				"job", ObjectMapperUtil.convertValue(job, JSONObject.class)
			).put(
				"runDataPeriod", jobRunDataPeriod.toString()
			).put(
				"trigger", "MANUAL"
			));

		return job;
	}

	public Job updateJob(
		String id, List<JobParameter> jobParameters,
		JobRunDataPeriod jobRunDataPeriod, JobRunFrequency jobRunFrequency,
		String name, boolean runNow) {

		Job job = _jobElasticsearchRepository.get(id);

		JobRunFrequency oldJobRunFrequency = job.getJobRunFrequency();

		job.setLastUpdatedDate(new Date());
		job.setName(name);
		job.setJobParameters(jobParameters);
		job.setJobRunFrequency(jobRunFrequency);
		job.setJobRunDataPeriod(jobRunDataPeriod);

		_rescheduleOSBAsahTask(job, oldJobRunFrequency);

		job = _jobElasticsearchRepository.update(id, job);

		if (runNow) {
			runJob(id, jobRunDataPeriod);
		}

		return job;
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

	private int _countCurrentMonthScheduledJobRuns(
		JobRunFrequency jobRunFrequency, Date startDate) {

		int count = 0;

		LocalDateTime nowLocalDateTime = LocalDateTime.now(ZoneId.of("UTC"));

		CronSequenceGenerator cronSequenceGenerator = new CronSequenceGenerator(
			jobRunFrequency.getCronExpression(), TimeZone.getTimeZone("UTC"));

		while (true) {
			startDate = cronSequenceGenerator.next(startDate);

			LocalDateTime nextJobRunLocalDateTime = DateUtil.toLocalDateTime(
				startDate, ZoneId.of("UTC"));

			if ((nextJobRunLocalDateTime.getMonthValue() >
					nowLocalDateTime.getMonthValue()) ||
				(nextJobRunLocalDateTime.getYear() >
					nowLocalDateTime.getYear())) {

				break;
			}

			if (nextJobRunLocalDateTime.getMonthValue() ==
					nowLocalDateTime.getMonthValue()) {

				count++;
			}
		}

		return count;
	}

	private int _countCurrentMonthScheduledJobRuns(
		List<JobRun> currentMonthJobRuns, Job job) {

		if (job.getJobRunFrequency() == JobRunFrequency.MANUAL) {
			return 0;
		}

		Date startDate = job.getLastUpdatedDate();

		JobRun lastScheduledJobRun = _fetchLastScheduledJobRun(
			currentMonthJobRuns);

		if (lastScheduledJobRun != null) {
			startDate = lastScheduledJobRun.getCreatedDate();
		}

		return _countCurrentMonthScheduledJobRuns(
			job.getJobRunFrequency(), startDate);
	}

	private long _countJobRunsByStatus(
		List<JobRun> jobRuns, JobRunStatus jobRunStatus) {

		Stream<JobRun> stream = jobRuns.stream();

		return stream.filter(
			jobRun -> jobRun.getJobRunStatus() == jobRunStatus
		).count();
	}

	private boolean _deleteJob(String id) {
		Job job = _jobElasticsearchRepository.get(id);

		_unscheduleOSBAsahTask(job);

		BulkByScrollResponse bulkByScrollResponse =
			_jobRunElasticsearchRepository.deleteByQuery(
				QueryBuilders.termQuery("job.id", job.getId()));

		List<BulkItemResponse.Failure> bulkFailures =
			bulkByScrollResponse.getBulkFailures();

		if (!bulkFailures.isEmpty()) {
			return false;
		}

		boolean success = _recommendationDog.deleteItemRecommendationsByJobId(
			id);

		if (!success) {
			return false;
		}

		return _jobElasticsearchRepository.delete(id);
	}

	private JobRun _fetchLastScheduledJobRun(List<JobRun> jobRuns) {
		Stream<JobRun> stream = jobRuns.stream();

		Optional<JobRun> lastScheduledJobRunOptional = stream.sorted(
			Comparator.comparing(JobRun::getId, Collections.reverseOrder())
		).filter(
			jobRun -> Objects.equals(jobRun.getTrigger(), "SCHEDULE")
		).findFirst();

		return lastScheduledJobRunOptional.orElse(null);
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

	private ResultBag<JobRun> _getCurrentMonthJobRunResultBag(
		String jobId, int size, Sort sort, int start) {

		RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(
			"createdDate");

		rangeQueryBuilder.timeZone("UTC");

		rangeQueryBuilder.gte("now/M");
		rangeQueryBuilder.lt("now");

		return _jobRunElasticsearchRepository.search(
			BoolQueryBuilderUtil.filter(
				rangeQueryBuilder
			).filter(
				QueryBuilders.termQuery("job.id", jobId)
			),
			size, sort, start);
	}

	private boolean _hasJobRunPublished(String id) {
		return _jobRunElasticsearchRepository.exists(
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("job.id", id)
			).filter(
				QueryBuilders.termsQuery("status", "PUBLISHED")
			));
	}

	@PostConstruct
	private void _init() {
		_jobElasticsearchRepository = new ElasticsearchRepository<>(
			"jobs", _elasticsearchInvokerFactory.forFaroInfo(), Job.class);

		_jobRunElasticsearchRepository = new ElasticsearchRepository<>(
			"job-runs", _elasticsearchInvokerFactory.forFaroInfo(),
			JobRun.class);
	}

	private void _rescheduleOSBAsahTask(
		Job job, JobRunFrequency oldJobRunFrequency) {

		JobRunFrequency newJobRunFrequency = job.getJobRunFrequency();

		if (newJobRunFrequency == oldJobRunFrequency) {
			return;
		}

		_unscheduleOSBAsahTask(job);

		_scheduleOSBAsahTask(job);
	}

	private void _scheduleOSBAsahTask(Job job) {
		JobRunFrequency jobRunFrequency = job.getJobRunFrequency();

		if (jobRunFrequency == JobRunFrequency.MANUAL) {
			return;
		}

		JSONObject osbAsahTaskJSONObject =
			_faroInfoOSBAsahTaskDog.scheduleOSBAsahTask(
				_jobTypeNaniteMap.get(job.getJobType()),
				JSONUtil.put(
					"job",
					ObjectMapperUtil.convertValue(job, JSONObject.class)),
				jobRunFrequency.getCronExpression());

		job.setOSBAsahTaskId(osbAsahTaskJSONObject.getString("id"));
	}

	private void _unscheduleOSBAsahTask(Job job) {
		String osbAsahTaskId = job.getOSBAsahTaskId();

		if (StringUtils.isBlank(osbAsahTaskId)) {
			return;
		}

		_faroInfoOSBAsahTaskDog.unscheduleOSBAsahTask(osbAsahTaskId);

		job.setOSBAsahTaskId("");
	}

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	@Autowired
	private FaroInfoOSBAsahTaskDog _faroInfoOSBAsahTaskDog;

	private ElasticsearchRepository<Job> _jobElasticsearchRepository;
	private ElasticsearchRepository<JobRun> _jobRunElasticsearchRepository;
	private final Map<JobType, String> _jobTypeNaniteMap =
		new HashMap<JobType, String>() {
			{
				put(
					JobType.COMMERCE_PRODUCT_RECOMMENDATION_PRODUCT_CONTENT,
					"CommerceProductRecommendationNanite");
				put(
					JobType.COMMERCE_PRODUCT_RECOMMENDATION_USER_INTERACTION,
					"CommerceProductRecommendationNanite");
				put(
					JobType.CONTENT_RECOMMENDATION_ITEM_SIMILARITY,
					"ContentRecommendationDataPreparationNanite");
			}
		};

	@Value("${osb.asah.content.recommendation.max.monthly.job.runs:10}")
	private int _maxMonthlyJobRuns;

	@Autowired
	private RecommendationDog _recommendationDog;

}