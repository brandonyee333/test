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

import com.liferay.osb.asah.backend.dto.ExperimentDTO;
import com.liferay.osb.asah.backend.graphql.schema.ExperimentDataFetcher;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.entity.Experiment;
import com.liferay.osb.asah.common.repository.ExperimentRepository;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.DataFetchingEnvironmentBuilder;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Marcos Martins
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahBackendSpringBootApplication.class)
public class ExperimentDataFetcherTest {

	@Test
	public void testGet() {
		Experiment experiment = new Experiment();

		experiment.setDataSourceId(331238757269547423L);
		experiment.setDXPExperienceId("1");
		experiment.setDXPExperienceName("Experience Name");
		experiment.setDXPSegmentId("123");
		experiment.setDXPSegmentName("Segment Name");
		experiment.setName("Experiment Name");

		ExperimentDTO expectedExperimentDTO = new ExperimentDTO(
			_experimentRepository.save(experiment));

		ExperimentDTO actualExperimentDTO = _experimentDataFetcher.get(
			_getDataFetchingEnvironment(expectedExperimentDTO.getId()));

		Assert.assertEquals(
			expectedExperimentDTO.getDataSourceId(),
			actualExperimentDTO.getDataSourceId());
		Assert.assertEquals(
			expectedExperimentDTO.getDXPExperienceId(),
			actualExperimentDTO.getDXPExperienceId());
		Assert.assertEquals(
			expectedExperimentDTO.getDXPExperienceName(),
			actualExperimentDTO.getDXPExperienceName());
		Assert.assertEquals(
			expectedExperimentDTO.getDXPSegmentId(),
			actualExperimentDTO.getDXPSegmentId());
		Assert.assertEquals(
			expectedExperimentDTO.getDXPSegmentName(),
			actualExperimentDTO.getDXPSegmentName());
		Assert.assertEquals(
			expectedExperimentDTO.getName(), actualExperimentDTO.getName());
	}

	private DataFetchingEnvironment _getDataFetchingEnvironment(String value) {
		DataFetchingEnvironmentBuilder dataFetchingEnvironmentBuilder =
			DataFetchingEnvironmentBuilder.newDataFetchingEnvironment();

		Map<String, Object> arguments = new HashMap<>();

		arguments.put("experimentId", value);

		dataFetchingEnvironmentBuilder.arguments(arguments);

		dataFetchingEnvironmentBuilder.context(new HashMap<>());

		return dataFetchingEnvironmentBuilder.build();
	}

	@Autowired
	private ExperimentDataFetcher _experimentDataFetcher;

	@Autowired
	private ExperimentRepository _experimentRepository;

}