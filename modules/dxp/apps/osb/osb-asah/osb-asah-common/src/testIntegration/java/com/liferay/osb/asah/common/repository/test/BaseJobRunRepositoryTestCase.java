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

import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.JobRun;
import com.liferay.osb.asah.common.model.JobRunStatus;
import com.liferay.osb.asah.common.repository.JobRunRepository;

import java.util.Date;

import org.junit.Before;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Marcellus Tavares
 */
public abstract class BaseJobRunRepositoryTestCase
	extends BaseRepositoryTestCase<JobRun> {

	@Before
	public void setUp() {
		JobRun jobRun = new JobRun();

		jobRun.setCompletedDate(new Date());
		jobRun.setContextJSONObject(JSONUtil.put("key", "value"));
		jobRun.setCreatedDate(new Date());
		jobRun.setJobId(1L);
		jobRun.setJobRunStatus(JobRunStatus.RUNNING);
		jobRun.setTrigger("manual");

		setUpRepository(jobRun);

		_jobRun = entityModels.get(0);
	}

	@Override
	protected CrudRepository<JobRun, Long> getCrudRepository() {
		return _jobRunRepository;
	}

	private JobRun _jobRun;

	@Autowired
	private JobRunRepository _jobRunRepository;

}