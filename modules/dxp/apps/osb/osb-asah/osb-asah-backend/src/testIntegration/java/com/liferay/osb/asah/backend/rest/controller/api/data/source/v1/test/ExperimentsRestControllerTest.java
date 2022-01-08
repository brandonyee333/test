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

package com.liferay.osb.asah.backend.rest.controller.api.data.source.v1.test;

import com.liferay.osb.asah.backend.OSBAsahBackendSpringTestContext;
import com.liferay.osb.asah.backend.dog.ExperimentDog;
import com.liferay.osb.asah.backend.dto.ExperimentDTO;
import com.liferay.osb.asah.backend.dto.ExperimentVariantsDTO;
import com.liferay.osb.asah.backend.dto.GoalDTO;
import com.liferay.osb.asah.backend.model.ExperimentSettings;
import com.liferay.osb.asah.backend.rest.controller.api.data.source.v1.ExperimentsRestController;
import com.liferay.osb.asah.common.dxp.DXPClient;
import com.liferay.osb.asah.common.entity.Experiment;
import com.liferay.osb.asah.common.entity.ExperimentVariant;
import com.liferay.osb.asah.common.model.DXPVariantSettings;
import com.liferay.osb.asah.common.model.Goal;
import com.liferay.osb.asah.common.model.GoalMetric;
import com.liferay.osb.asah.common.repository.ChannelRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.annotation.RepositoryResource;
import com.liferay.osb.asah.test.util.spring.OSBAsahElasticsearchTestExecutionListener;
import com.liferay.osb.asah.test.util.spring.OSBAsahRepositoryTestExecutionListener;
import com.liferay.osb.asah.test.util.spring.OSBAsahSQLTestExecutionListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

/**
 * @author Marcellus Tavares
 */
@TestExecutionListeners(
	mergeMode = TestExecutionListeners.MergeMode.REPLACE_DEFAULTS,
	value = {
		DependencyInjectionTestExecutionListener.class,
		MockitoTestExecutionListener.class,
		OSBAsahElasticsearchTestExecutionListener.class,
		OSBAsahRepositoryTestExecutionListener.class,
		OSBAsahSQLTestExecutionListener.class
	}
)
public class ExperimentsRestControllerTest
	implements OSBAsahBackendSpringTestContext {

	@ElasticsearchIndex(
		name = "experiments", resourcePath = "experiments.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testDeleteExperiment() {
		_experimentsRestController.deleteExperiment(1L);

		Assertions.assertNull(_experimentDog.fetchExperiment(1L));

		Mockito.verifyZeroInteractions(_dxpClient);
	}

	@ElasticsearchIndex(
		name = "experiments", resourcePath = "experiments.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testPatchExperiment() {
		Experiment experiment = new Experiment();

		experiment.setGoal(new Goal(GoalMetric.BOUNCE_RATE, null));
		experiment.setName("New Experiment Name");

		ExperimentDTO expectedExperimentDTO = new ExperimentDTO(experiment);

		_experimentsRestController.patchExperiment(1L, expectedExperimentDTO);

		ExperimentDTO actualExperimentDTO =
			_experimentsRestController.getExperiment(1L);

		Assertions.assertEquals(
			expectedExperimentDTO.getName(), actualExperimentDTO.getName());
		_assertGoalDTO(
			expectedExperimentDTO.getGoalDTO(),
			actualExperimentDTO.getGoalDTO());
	}

	@RepositoryResource(
		repositoryClass = ChannelRepository.class,
		resourcePath = "osbasahfaroinfo/channels.json"
	)
	@RepositoryResource(
		repositoryClass = DataSourceRepository.class,
		resourcePath = "osbasahfaroinfo/data_sources.json"
	)
	@Test
	public void testPostExperiment() {
		Experiment experiment = new Experiment();

		experiment.setDataSourceId(331238757269547423L);
		experiment.setDXPExperienceId("1");
		experiment.setDXPExperienceName("Experience Name");
		experiment.setDXPSegmentId("123");
		experiment.setDXPSegmentName("Segment Name");
		experiment.setName("Experiment Name");

		ExperimentDTO expectedExperimentDTO = new ExperimentDTO(experiment);

		ExperimentDTO actualExperimentDTO =
			_experimentsRestController.postExperiment(expectedExperimentDTO);

		Assertions.assertEquals(12345, actualExperimentDTO.getChannelId());

		Assertions.assertNotNull(actualExperimentDTO.getId());
		Assertions.assertEquals(
			expectedExperimentDTO.getName(), actualExperimentDTO.getName());
		Assertions.assertEquals(
			expectedExperimentDTO.getDXPExperienceId(),
			actualExperimentDTO.getDXPExperienceId());
		Assertions.assertEquals(
			expectedExperimentDTO.getDXPExperienceName(),
			actualExperimentDTO.getDXPExperienceName());
		Assertions.assertEquals(
			expectedExperimentDTO.getDXPSegmentId(),
			actualExperimentDTO.getDXPSegmentId());
		Assertions.assertEquals(
			expectedExperimentDTO.getDXPSegmentName(),
			actualExperimentDTO.getDXPSegmentName());
	}

	@Test
	public void testPostExperimentEstimatedDaysDurationWithInvalidTrafficSplit() {
		ExperimentSettings experimentSettings = new ExperimentSettings();

		experimentSettings.setDXPVariantsSettings(
			new ArrayList<DXPVariantSettings>() {
				{
					add(new DXPVariantSettings(true, "1", 50.0));
					add(new DXPVariantSettings(false, "2", 60.0));
				}
			});

		Assertions.assertThrows(
			OSBAsahException.class,
			() ->
				_experimentsRestController.postExperimentEstimatedDaysDuration(
					1L, experimentSettings));
	}

	@ElasticsearchIndex(
		name = "experiments", resourcePath = "experiments.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testPutExperimentVariants() {
		Set<ExperimentVariant> expectedExperimentVariants =
			new HashSet<ExperimentVariant>() {
				{
					add(
						_createExperimentVariant(
							50, true, "1", "DXP Variant 1"));
					add(
						_createExperimentVariant(
							35, false, "2", "DXP Variant 2"));
				}
			};

		ExperimentVariantsDTO expectedExperimentVariantsDTO =
			new ExperimentVariantsDTO(expectedExperimentVariants);

		_experimentsRestController.putExperimentVariants(
			1L, expectedExperimentVariantsDTO);

		ExperimentDTO actualExperimentDTO =
			_experimentsRestController.getExperiment(1L);

		Assertions.assertEquals(
			expectedExperimentVariantsDTO.getExperimentVariantDTOs(),
			actualExperimentDTO.getExperimentVariantDTOs());
	}

	@ElasticsearchIndex(
		name = "experiments", resourcePath = "experiments.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testPutGoal() {
		Goal goal = new Goal();

		goal.setGoalMetric(GoalMetric.BOUNCE_RATE);
		goal.setTarget("");

		GoalDTO expectedGoalDTO = new GoalDTO(goal);

		_experimentsRestController.putGoal(1L, expectedGoalDTO);

		ExperimentDTO actualExperimentDTO =
			_experimentsRestController.getExperiment(1L);

		_assertGoalDTO(expectedGoalDTO, actualExperimentDTO.getGoalDTO());
	}

	private void _assertGoalDTO(
		GoalDTO actualGoalDTO, GoalDTO expectedGoalDTO) {

		Assertions.assertEquals(
			expectedGoalDTO.getGoalMetric(), actualGoalDTO.getGoalMetric());
		Assertions.assertEquals(
			expectedGoalDTO.getTarget(), actualGoalDTO.getTarget());
	}

	private ExperimentVariant _createExperimentVariant(
		Integer changes, boolean control, String dxpVariantId,
		String dxpVariantName) {

		ExperimentVariant experimentVariant = new ExperimentVariant();

		experimentVariant.setChanges(changes);
		experimentVariant.setControl(control);
		experimentVariant.setDXPVariantId(dxpVariantId);
		experimentVariant.setDXPVariantName(dxpVariantName);

		return experimentVariant;
	}

	@MockBean
	private DXPClient _dxpClient;

	@Autowired
	private ExperimentDog _experimentDog;

	@Autowired
	private ExperimentsRestController _experimentsRestController;

}