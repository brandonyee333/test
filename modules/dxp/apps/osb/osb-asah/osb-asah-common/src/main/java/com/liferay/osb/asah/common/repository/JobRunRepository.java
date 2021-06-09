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

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Marcellus Tavares
 */
public interface JobRunRepository extends CrudRepository<JobRun, Long> {

	public long countByJobId(Long jobId);

	public void deleteByJobId(Long id);

	public boolean existsByJobIdAndJobRunStatus(
		Long jobId, JobRunStatus jobRunStatus);

	public List<JobRun> findByJobId(Long jobId, Pageable pageable);

	public List<JobRun> findByJobIdAndCreateLocalDateTimeBetween(
		Long jobId, LocalDateTime endCreateLocalDateTime,
		LocalDateTime startCreateLocalDateTime);

	public List<JobRun> findByJobRunStatusAndJobTypeAndStep(
		JobRunStatus jobRunStatus, String jobType, String step);

	public Optional<JobRun> findFirstByJobIdAndJobRunStatusOrderByIdDesc(
		Long jobId, JobRunStatus jobRunStatus);

	public Optional<JobRun> findFirstByJobIdAndTriggerOrderByIdDesc(
		Long jobId, String trigger);

	public Optional<JobRun> findFirstByJobIdOrderByIdDesc(Long jobId);

}