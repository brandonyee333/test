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

import com.liferay.osb.asah.backend.dog.ml.JobDog;
import com.liferay.osb.asah.backend.model.ml.Job;
import com.liferay.osb.asah.backend.model.ml.JobParameter;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.ArrayList;
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
			true, "0 0 0 * * ?",
			new ArrayList<JobParameter>() {
				{
					add(new JobParameter("parameter1", "1.2"));
				}
			},
			"Product Recommendation Job");

		Assert.assertNotNull(job);
		Assert.assertNotNull(job.getId());
		Assert.assertEquals("0 0 0 * * ?", job.getCronExpression());

		List<JobParameter> jobParameters = job.getJobParameters();

		JobParameter jobParameter = jobParameters.get(0);

		Assert.assertEquals("parameter1", jobParameter.getName());
		Assert.assertEquals("1.2", jobParameter.getValue());

		Assert.assertEquals("Product Recommendation Job", job.getName());
	}

	@ElasticsearchIndex(
		name = "jobs", resourcePath = "jobs-info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testDeleteJob() {
		boolean deleted = _jobDog.deleteJob("1");

		Assert.assertTrue(deleted);
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