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

package com.liferay.osb.asah.common.repository;

import com.liferay.osb.asah.common.entity.JobRun;
import com.liferay.osb.asah.common.model.JobRunStatus;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.repository.query.Param;

/**
 * @author Marcellus Tavares
 */
public interface JobRunRepository extends Repository<JobRun, Long> {

	@Cacheable
	public long countByJobId(Long jobId);

	@CacheEvict(allEntries = true)
	@Modifying
	public void deleteByJobId(@Param("jobId") Long jobId);

	@Cacheable
	public boolean existsByJobIdAndJobRunStatus(
		Long jobId, JobRunStatus jobRunStatus);

	@Cacheable
	public List<JobRun> findByCreateLocalDateTimeBetweenAndJobId(
		LocalDateTime createLocalDateTime1,
		LocalDateTime createLocalDateTime2, Long jobId);

	@Cacheable
	public List<JobRun> findByJobId(Long jobId, Pageable pageable);

	@Cacheable
	public List<JobRun> findByJobRunStatusAndJobTypeAndStep(
		JobRunStatus jobRunStatus, String jobType, String step);

	@Cacheable
	public List<JobRun> findByJobRunStatusIn(List<String> jobRunStatus);

	@Cacheable
	public Optional<JobRun> findFirstByJobIdAndJobRunStatusOrderByIdDesc(
		Long jobId, JobRunStatus jobRunStatus);

	@Cacheable
	public Optional<JobRun> findFirstByJobIdAndTriggerOrderByIdDesc(
		Long jobId, String trigger);

	@Cacheable
	public Optional<JobRun> findFirstByJobIdOrderByIdDesc(Long jobId);

}