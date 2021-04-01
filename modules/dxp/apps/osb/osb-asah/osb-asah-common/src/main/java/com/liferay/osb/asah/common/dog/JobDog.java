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

package com.liferay.osb.asah.common.dog;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.AsahTask;
import com.liferay.osb.asah.common.model.Job;
import com.liferay.osb.asah.common.model.JobParameter;
import com.liferay.osb.asah.common.model.JobRun;
import com.liferay.osb.asah.common.model.JobRunDataPeriod;
import com.liferay.osb.asah.common.model.JobRunFrequency;
import com.liferay.osb.asah.common.model.JobRunStatus;
import com.liferay.osb.asah.common.model.JobRunsMonthlyStatistics;
import com.liferay.osb.asah.common.model.JobStatus;
import com.liferay.osb.asah.common.model.JobType;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.repository.JobRepository;
import com.liferay.osb.asah.common.repository.JobRunRepository;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.TimeZone;
import java.util.stream.Stream;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.support.CronSequenceGenerator;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class JobDog {

	public Job addJob(
		Set<JobParameter> jobParameters, JobRunDataPeriod jobRunDataPeriod,
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

		job = _jobRepository.save(job);

		_scheduleAsahTask(job);

		if (runNow) {
			runJob(job.getId(), jobRunDataPeriod);
		}

		return job;
	}

	public void deleteJobs(List<Long> jobIds) {
		jobIds.forEach(this::_deleteJob);
	}

	public Job fetchJob(String name) {
		Optional<Job> jobOptional = _jobRepository.findFirstByName(name);

		return jobOptional.orElse(null);
	}

	public Job getJob(Long jobId) {
		Optional<Job> jobOptional = _jobRepository.findById(jobId);

		return jobOptional.orElseThrow(
			() -> new OSBAsahException(
				HttpStatus.BAD_REQUEST, "There is no job with ID " + jobId));
	}

	public String getJobNextRunDateString(Long jobId) {
		Job job = getJob(jobId);

		JobRunFrequency jobRunFrequency = job.getJobRunFrequency();

		if (jobRunFrequency == JobRunFrequency.MANUAL) {
			return null;
		}

		Optional<JobRun> jobRunOptional =
			_jobRunRepository.findFirstByJobIdAndTriggerOrderByIdDesc(
				jobId, "SCHEDULE");

		Date startDate = jobRunOptional.map(
			JobRun::getCreatedDate
		).orElse(
			job.getLastUpdatedDate()
		);

		CronSequenceGenerator cronSequenceGenerator = new CronSequenceGenerator(
			jobRunFrequency.getCronExpression(), TimeZone.getTimeZone("UTC"));

		return DateUtil.toUTCString(cronSequenceGenerator.next(startDate));
	}

	public Page<Job> getJobPage(
		String keywords, int page, int size, Sort sort) {

		PageRequest pageRequest = PageRequest.of(page, size, sort);

		return PageableExecutionUtils.getPage(
			_jobRepository.findByNameContainingIgnoreCase(
				keywords, pageRequest),
			pageRequest,
			() -> _jobRepository.countByNameContainingIgnoreCase(keywords));
	}

	public String getJobRunDateString(Long jobId) {
		Optional<JobRun> jobRunOptional =
			_jobRunRepository.findFirstByJobIdAndJobRunStatusOrderByIdDesc(
				jobId, JobRunStatus.PUBLISHED);

		return jobRunOptional.map(
			JobRun::getCompletedDate
		).map(
			DateUtil::toUTCString
		).orElse(
			null
		);
	}

	public Page<JobRun> getJobRunPage(
		Long jobId, int page, int size, Sort sort) {

		PageRequest pageRequest = PageRequest.of(page, size, sort);

		return PageableExecutionUtils.getPage(
			_jobRunRepository.findByJobId(jobId, pageRequest), pageRequest,
			() -> _jobRunRepository.countByJobId(jobId));
	}

	public JobRunsMonthlyStatistics getJobRunsMonthlyStatistics(Long jobId) {
		List<JobRun> jobRuns = _getCurrentMonthJobRunResultBag(jobId);

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
					_countCurrentMonthScheduledJobRuns(jobRuns, getJob(jobId)));
			}
		};
	}

	public JobStatus getJobStatus(Long jobId) {
		if (_hasJobRunPublished(jobId)) {
			return JobStatus.READY;
		}

		Optional<JobRun> jobRunOptional =
			_jobRunRepository.findFirstByJobIdOrderByIdDesc(jobId);

		if (jobRunOptional.isPresent()) {
			JobRun jobRun = jobRunOptional.get();

			if (jobRun.getJobRunStatus() == JobRunStatus.FAILED) {
				return JobStatus.FAILED;
			}
			else if ((jobRun.getJobRunStatus() == JobRunStatus.RUNNING) ||
					 (jobRun.getJobRunStatus() == JobRunStatus.UNKNOWN)) {

				return JobStatus.RUNNING;
			}
		}

		Job job = getJob(jobId);

		if (job.getJobRunFrequency() == JobRunFrequency.MANUAL) {
			return JobStatus.PENDING;
		}

		return JobStatus.SCHEDULED;
	}

	public Job runJob(Long jobId, JobRunDataPeriod jobRunDataPeriod) {
		JobRun jobRun = _fetchLatestJobRun(jobId);

		if ((jobRun != null) &&
			(jobRun.getJobRunStatus() == JobRunStatus.RUNNING)) {

			throw new IllegalStateException(
				String.format(
					"Unable to run job ID %s because it is already running",
					jobId));
		}

		JobRunsMonthlyStatistics jobRunsMonthlyStatistics =
			getJobRunsMonthlyStatistics(jobId);

		if (jobRunsMonthlyStatistics.getAvailableJobRuns() == 0) {
			throw new IllegalStateException(
				String.format(
					"Unable to run job ID %s because this run surpasses the " +
						"maximum allowed monthly runs threshold",
					jobId));
		}

		Job job = getJob(jobId);

		_asahTaskDog.scheduleAsahTask(
			_jobTypeNaniteMap.get(job.getJobType()),
			JSONUtil.put(
				"job", _objectMapper.convertValue(job, JSONObject.class)
			).put(
				"runDataPeriod", jobRunDataPeriod.toString()
			).put(
				"trigger", "MANUAL"
			));

		return job;
	}

	public Job updateJob(
		Long jobId, Set<JobParameter> jobParameters,
		JobRunDataPeriod jobRunDataPeriod, JobRunFrequency jobRunFrequency,
		String name, boolean runNow) {

		Job job = getJob(jobId);

		JobRunFrequency oldJobRunFrequency = job.getJobRunFrequency();

		job.setLastUpdatedDate(new Date());
		job.setName(name);
		job.setJobParameters(jobParameters);
		job.setJobRunFrequency(jobRunFrequency);
		job.setJobRunDataPeriod(jobRunDataPeriod);

		_rescheduleAsahTask(job, oldJobRunFrequency);

		job = _jobRepository.save(job);

		if (runNow) {
			runJob(jobId, jobRunDataPeriod);
		}

		return job;
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

	private void _deleteJob(Long jobId) {
		Job job = getJob(jobId);

		_unscheduleAsahTask(job);

		_jobRunRepository.deleteByJobId(jobId);

		_recommendationDog.deleteItemRecommendationsByJobId(jobId);

		_jobRepository.delete(job);
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

	private JobRun _fetchLatestJobRun(Long jobId) {
		Optional<JobRun> jobRunOptional =
			_jobRunRepository.findFirstByJobIdOrderByIdDesc(jobId);

		return jobRunOptional.orElse(null);
	}

	private List<JobRun> _getCurrentMonthJobRunResultBag(Long jobId) {
		LocalDateTime endLocalDateTime = LocalDateTime.now(
			_timeZoneDog.getZoneId());

		LocalDateTime startLocalDateTime = endLocalDateTime.withDayOfMonth(1);

		startLocalDateTime = startLocalDateTime.with(LocalTime.MIDNIGHT);

		return _jobRunRepository.findByJobIdAndCreatedDateBetween(
			jobId, Date.from(startLocalDateTime.toInstant(ZoneOffset.UTC)),
			Date.from(endLocalDateTime.toInstant(ZoneOffset.UTC)));
	}

	private boolean _hasJobRunPublished(Long jobId) {
		return _jobRunRepository.existsByJobIdAndJobRunStatus(
			jobId, JobRunStatus.PUBLISHED);
	}

	private void _rescheduleAsahTask(
		Job job, JobRunFrequency oldJobRunFrequency) {

		JobRunFrequency newJobRunFrequency = job.getJobRunFrequency();

		if (newJobRunFrequency == oldJobRunFrequency) {
			return;
		}

		_unscheduleAsahTask(job);

		_scheduleAsahTask(job);
	}

	private void _scheduleAsahTask(Job job) {
		JobRunFrequency jobRunFrequency = job.getJobRunFrequency();

		if (jobRunFrequency == JobRunFrequency.MANUAL) {
			return;
		}

		AsahTask asahTask = _asahTaskDog.scheduleAsahTask(
			_jobTypeNaniteMap.get(job.getJobType()),
			JSONUtil.put(
				"job", _objectMapper.convertValue(job, JSONObject.class)),
			jobRunFrequency.getCronExpression());

		job.setAsahTaskId(asahTask.getId());
	}

	private void _unscheduleAsahTask(Job job) {
		Long asahTaskId = job.getAsahTaskId();

		if (asahTaskId == null) {
			return;
		}

		_asahTaskDog.unscheduleAsahTask(asahTaskId);

		job.setAsahTaskId(null);
	}

	@Autowired
	private AsahTaskDog _asahTaskDog;

	@Autowired
	private JobRepository _jobRepository;

	@Autowired
	private JobRunRepository _jobRunRepository;

	private final Map<JobType, String> _jobTypeNaniteMap =
		new HashMap<JobType, String>() {
			{
				put(
					JobType.COMMERCE_PRODUCT_RECOMMENDATION_FREQUENT_PATTERN,
					"CommerceProductRecommendationNanite");
				put(
					JobType.COMMERCE_PRODUCT_RECOMMENDATION_PRODUCT_CONTENT,
					"CommerceProductRecommendationNanite");
				put(
					JobType.COMMERCE_PRODUCT_RECOMMENDATION_USER_INTERACTION,
					"CommerceProductRecommendationNanite");
				put(
					JobType.COMMERCE_REVENUE_FORECAST_ACCOUNT,
					"CommerceProductRecommendationNanite");
				put(
					JobType.COMMERCE_REVENUE_FORECAST_ACCOUNT_CATEGORY,
					"CommerceProductRecommendationNanite");
				put(
					JobType.CONTENT_RECOMMENDATION_ITEM_SIMILARITY,
					"ContentRecommendationDataPreparationNanite");
			}
		};

	@Value("${osb.asah.content.recommendation.max.monthly.job.runs:10}")
	private int _maxMonthlyJobRuns;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private RecommendationDog _recommendationDog;

	@Autowired
	private TimeZoneDog _timeZoneDog;

}