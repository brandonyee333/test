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

package com.liferay.osb.asah.common.repository.test;

import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.entity.Job;
import com.liferay.osb.asah.common.entity.JobParameter;
import com.liferay.osb.asah.common.model.JobRunDataPeriod;
import com.liferay.osb.asah.common.model.JobRunFrequency;
import com.liferay.osb.asah.common.model.JobType;
import com.liferay.osb.asah.common.repository.JobRepository;
import com.liferay.osb.asah.common.util.SetUtil;

import java.time.LocalDateTime;

import org.junit.Before;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Marcellus Tavares
 */
public abstract class BaseJobRepositoryTestCase
	extends BaseRepositoryTestCase<Job, Long> {

	@Before
	public void setUp() {
		Job job = new Job();

		job.setCreateLocalDateTime(LocalDateTime.now(_timeZoneDog.getZoneId()));
		job.setJobType(JobType.CONTENT_RECOMMENDATION_ITEM_SIMILARITY);
		job.setJobRunFrequency(JobRunFrequency.MANUAL);
		job.setJobRunDataPeriod(JobRunDataPeriod.LAST_30_DAYS);
		job.setJobParameters(SetUtil.of(new JobParameter("parameter1", "1.2")));
		job.setModifiedLocalDateTime(LocalDateTime.now(_timeZoneDog.getZoneId()));
		job.setName("Product Recommendation Job");

		setUpRepository(job);
	}

	@Override
	protected CrudRepository<Job, Long> getCrudRepository() {
		return _jobRunRepository;
	}

	@Autowired
	private JobRepository _jobRunRepository;

	@Autowired
	private TimeZoneDog _timeZoneDog;
}