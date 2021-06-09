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

package com.liferay.osb.asah.backend.graphql.schema.test;

import com.liferay.osb.asah.backend.dto.JobDTO;
import com.liferay.osb.asah.backend.graphql.schema.JobByNameDataFetcher;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.entity.Job;
import com.liferay.osb.asah.common.entity.JobParameter;
import com.liferay.osb.asah.common.model.JobRunDataPeriod;
import com.liferay.osb.asah.common.model.JobRunFrequency;
import com.liferay.osb.asah.common.model.JobType;
import com.liferay.osb.asah.common.repository.JobRepository;
import com.liferay.osb.asah.common.util.SetUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.DataFetchingEnvironmentBuilder;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Alejo Ceballos
 * @author Marcos Martins
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahBackendSpringBootApplication.class)
public class JobByNameDataFetcherTest {

	@After
	public void tearDown() {
		_jobRepository.deleteAll();
	}

	@Test
	public void testGet() {
		Job job = new Job();

		job.setName("Product Recommendation Job");
		job.setJobParameters(SetUtil.of(new JobParameter("parameter1", "1.2")));
		job.setJobRunDataPeriod(JobRunDataPeriod.LAST_30_DAYS);
		job.setJobRunFrequency(JobRunFrequency.MANUAL);
		job.setJobType(JobType.CONTENT_RECOMMENDATION_ITEM_SIMILARITY);

		job = _jobRepository.save(job);

		JobDTO jobDTO = _jobByNameDataFetcher.get(
			_getDataFetchingEnvironment(job.getName()));

		Assert.assertEquals(
			job.getJobRunDataPeriod(), jobDTO.getJobRunDataPeriod());
		Assert.assertEquals(
			job.getJobRunFrequency(), jobDTO.getJobRunFrequency());
		Assert.assertEquals(job.getJobType(), jobDTO.getJobType());
		Assert.assertEquals(job.getId(), Long.valueOf(jobDTO.getId()));
		Assert.assertEquals(job.getName(), jobDTO.getName());
	}

	@Test
	public void testGetWithNoJob() {
		JobDTO jobDTO = _jobByNameDataFetcher.get(
			_getDataFetchingEnvironment("Product Recommendation Job"));

		Assert.assertNull(jobDTO);
	}

	private DataFetchingEnvironment _getDataFetchingEnvironment(String value) {
		DataFetchingEnvironmentBuilder dataFetchingEnvironmentBuilder =
			DataFetchingEnvironmentBuilder.newDataFetchingEnvironment();

		Map<String, Object> arguments = new HashMap<>();

		arguments.put("name", value);

		dataFetchingEnvironmentBuilder.arguments(arguments);

		dataFetchingEnvironmentBuilder.context(new HashMap<>());

		return dataFetchingEnvironmentBuilder.build();
	}

	@Autowired
	private JobByNameDataFetcher _jobByNameDataFetcher;

	@Autowired
	private JobRepository _jobRepository;

}