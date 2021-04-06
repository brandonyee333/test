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

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.model.Job;
import com.liferay.osb.asah.common.model.JobRun;
import com.liferay.osb.asah.common.model.JobRunFrequency;
import com.liferay.osb.asah.common.model.JobRunStatus;
import com.liferay.osb.asah.common.model.JobRunsMonthlyStatistics;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.repository.JobRunRepository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.TimeZone;
import java.util.stream.Stream;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.scheduling.support.CronSequenceGenerator;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class JobRunDog {

	public JobRun addJobRun(
		JSONObject contextJSONObject, Job job, JobRunStatus jobRunStatus,
		String step, String trigger) {

		Date date = new Date();

		JobRun jobRun = new JobRun();

		jobRun.setContextJSONObject(contextJSONObject);
		jobRun.setCreatedDate(date);
		jobRun.setJobId(job.getId());
		jobRun.setJobRunStatus(jobRunStatus);
		jobRun.setJobType(String.valueOf(job.getJobType()));
		jobRun.setLastUpdatedDate(date);
		jobRun.setStep(step);
		jobRun.setTrigger(trigger);

		return _jobRunRepository.save(jobRun);
	}

	public void deleteByJobId(Long jobId) {
		_jobRunRepository.deleteByJobId(jobId);
	}

	public boolean existsJobRunPublished(Long jobId) {
		return _jobRunRepository.existsByJobIdAndJobRunStatus(
			jobId, JobRunStatus.PUBLISHED);
	}

	public JobRun fetchLatestJobRun(Long jobId) {
		Optional<JobRun> jobRunOptional =
			_jobRunRepository.findFirstByJobIdOrderByIdDesc(jobId);

		return jobRunOptional.orElse(null);
	}

	public JobRun fetchLatestJobRun(Long jobId, String trigger) {
		Optional<JobRun> jobRunOptional =
			_jobRunRepository.findFirstByJobIdAndTriggerOrderByIdDesc(
				jobId, trigger);

		return jobRunOptional.orElse(null);
	}

	public String fetchLatestJobRunPublishedDateString(Long jobId) {
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

	public JobRunsMonthlyStatistics getJobRunsMonthlyStatistics(Job job) {
		List<JobRun> jobRuns = _getCurrentMonthJobRunResultBag(job.getId());

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
					_countCurrentMonthScheduledJobRuns(jobRuns, job));
			}
		};
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

	private JobRun _fetchLastScheduledJobRun(List<JobRun> jobRuns) {
		Stream<JobRun> stream = jobRuns.stream();

		Optional<JobRun> lastScheduledJobRunOptional = stream.sorted(
			Comparator.comparing(JobRun::getId, Collections.reverseOrder())
		).filter(
			jobRun -> Objects.equals(jobRun.getTrigger(), "SCHEDULE")
		).findFirst();

		return lastScheduledJobRunOptional.orElse(null);
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

	@Autowired
	private JobRunRepository _jobRunRepository;

	@Value("${osb.asah.content.recommendation.max.monthly.job.runs:10}")
	private int _maxMonthlyJobRuns;

	@Autowired
	private TimeZoneDog _timeZoneDog;

}