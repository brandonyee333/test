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

package com.liferay.osb.asah.backend.dog.ml.test;

import com.liferay.osb.asah.backend.dog.JobDog;
import com.liferay.osb.asah.backend.model.Job;
import com.liferay.osb.asah.backend.model.JobParameter;
import com.liferay.osb.asah.backend.model.JobTrainingFrequency;
import com.liferay.osb.asah.backend.model.JobTrainingPeriod;
import com.liferay.osb.asah.backend.model.JobType;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Marcellus Tavares
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahBackendSpringBootApplication.class)
public class JobDogTest {

	@ElasticsearchIndex(
		name = "jobs", resourcePath = "jobs-info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testAddJob() {
		Job job = _jobDog.addJob(
			true,
			new ArrayList<JobParameter>() {
				{
					add(new JobParameter("parameter1", "1.2"));
				}
			},
			JobTrainingFrequency.MANUAL, JobTrainingPeriod.LAST_30_DAYS,
			JobType.CONTENT_RECOMMENDATION_ITEM_SIMILARITY,
			"Product Recommendation Job");

		Assert.assertNotNull(job);
		Assert.assertNotNull(job.getId());

		List<JobParameter> jobParameters = job.getJobParameters();

		JobParameter jobParameter = jobParameters.get(0);

		Assert.assertEquals("parameter1", jobParameter.getName());
		Assert.assertEquals("1.2", jobParameter.getValue());

		Assert.assertEquals(
			JobTrainingFrequency.MANUAL, job.getJobTrainingFrequency());
		Assert.assertEquals(
			JobTrainingPeriod.LAST_30_DAYS, job.getJobTrainingPeriod());
		Assert.assertEquals(
			JobType.CONTENT_RECOMMENDATION_ITEM_SIMILARITY, job.getJobType());
		Assert.assertEquals("Product Recommendation Job", job.getName());
	}

	@ElasticsearchIndex(
		name = "jobs", resourcePath = "jobs-info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testDeleteJob() {
		List<Boolean> statuses = _jobDog.deleteJobs(Arrays.asList("1"));

		Assert.assertTrue(statuses.get(0));
	}

	@ElasticsearchIndex(
		name = "jobs", resourcePath = "jobs-info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetJob() {
		Job job = _jobDog.getJob("1");

		Assert.assertNotNull(job);
		Assert.assertEquals("Related Content Job", job.getName());
	}

	@Autowired
	private JobDog _jobDog;

}