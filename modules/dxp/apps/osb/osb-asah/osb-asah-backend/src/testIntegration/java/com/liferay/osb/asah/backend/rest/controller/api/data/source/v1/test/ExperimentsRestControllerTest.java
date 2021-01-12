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

import com.liferay.osb.asah.backend.dog.ExperimentDog;
import com.liferay.osb.asah.backend.model.DXPVariant;
import com.liferay.osb.asah.backend.model.DXPVariants;
import com.liferay.osb.asah.backend.model.Experiment;
import com.liferay.osb.asah.backend.model.ExperimentSettings;
import com.liferay.osb.asah.backend.model.Goal;
import com.liferay.osb.asah.backend.model.GoalMetric;
import com.liferay.osb.asah.backend.rest.controller.api.data.source.v1.ExperimentsRestController;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.dxp.DXPClient;
import com.liferay.osb.asah.common.model.DXPVariantSettings;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Marcellus Tavares
 */
@ContextConfiguration(classes = OSBAsahBackendSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class ExperimentsRestControllerTest {

	@ElasticsearchIndex(
		name = "experiments", resourcePath = "experiments.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testDeleteExperiment() {
		_experimentsRestController.deleteExperiment("1");

		Assert.assertNull(_experimentDog.fetchExperiment("1"));

		Mockito.verifyZeroInteractions(_dxpClient);
	}

	@ElasticsearchIndex(
		name = "experiments", resourcePath = "experiments.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testPatchExperiment() {
		Experiment expectedExperiment = new Experiment();

		expectedExperiment.setGoal(new Goal(GoalMetric.BOUNCE_RATE, null));
		expectedExperiment.setName("New Experiment Name");

		_experimentsRestController.patchExperiment("1", expectedExperiment);

		Experiment actualExperiment = _experimentsRestController.getExperiment(
			"1");

		Assert.assertEquals(
			expectedExperiment.getGoal(), actualExperiment.getGoal());
		Assert.assertEquals(
			expectedExperiment.getName(), actualExperiment.getName());
	}

	@ElasticsearchIndex(
		name = "data-sources", resourcePath = "data_sources.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testPostExperiment() {
		Experiment expectedExperiment = new Experiment();

		expectedExperiment.setDataSourceId("331238757269547423");
		expectedExperiment.setDXPExperienceId("1");
		expectedExperiment.setDXPExperienceName("Experience Name");
		expectedExperiment.setDXPSegmentId("123");
		expectedExperiment.setDXPSegmentName("Segment Name");
		expectedExperiment.setName("Experiment Name");

		Experiment actualExperiment = _experimentsRestController.postExperiment(
			expectedExperiment);

		Assert.assertEquals("1", actualExperiment.getChannelId());

		Assert.assertNotNull(expectedExperiment.getId());
		Assert.assertEquals(
			expectedExperiment.getName(), actualExperiment.getName());
		Assert.assertEquals(
			expectedExperiment.getDXPExperienceId(),
			actualExperiment.getDXPExperienceId());
		Assert.assertEquals(
			expectedExperiment.getDXPExperienceName(),
			actualExperiment.getDXPExperienceName());
		Assert.assertEquals(
			expectedExperiment.getDXPSegmentId(),
			actualExperiment.getDXPSegmentId());
		Assert.assertEquals(
			expectedExperiment.getDXPSegmentName(),
			actualExperiment.getDXPSegmentName());
	}

	@Test(expected = OSBAsahException.class)
	public void testPostExperimentEstimatedDaysDurationWithInvalidTrafficSplit() {
		ExperimentSettings experimentSettings = new ExperimentSettings();

		experimentSettings.setDXPVariantsSettings(
			new ArrayList<DXPVariantSettings>() {
				{
					add(new DXPVariantSettings(true, "1", 50.0));
					add(new DXPVariantSettings(false, "2", 60.0));
				}
			});

		_experimentsRestController.postExperimentEstimatedDaysDuration(
			"1", experimentSettings);
	}

	@ElasticsearchIndex(
		name = "experiments", resourcePath = "experiments.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testPutDXPVariants() {
		List<DXPVariant> expectedDXPVariants = new ArrayList<DXPVariant>() {
			{
				add(_createDXPVariant(50, true, "1", "DXP Variant 1"));
				add(_createDXPVariant(35, false, "2", "DXP Variant 2"));
			}
		};

		_experimentsRestController.putDXPVariants(
			"1", new DXPVariants(expectedDXPVariants));

		Experiment actualExperiment = _experimentsRestController.getExperiment(
			"1");

		Assert.assertEquals(
			expectedDXPVariants, actualExperiment.getDXPVariants());
	}

	@ElasticsearchIndex(
		name = "experiments", resourcePath = "experiments.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testPutGoal() {
		Goal expectedGoal = new Goal();

		expectedGoal.setGoalMetric(GoalMetric.BOUNCE_RATE);
		expectedGoal.setTarget("");

		_experimentsRestController.putGoal("1", expectedGoal);

		Experiment actualExperiment = _experimentsRestController.getExperiment(
			"1");

		Assert.assertEquals(expectedGoal, actualExperiment.getGoal());
	}

	private DXPVariant _createDXPVariant(
		Integer changes, boolean control, String dxpVariantId,
		String dxpVariantName) {

		DXPVariant dxpVariant = new DXPVariant();

		dxpVariant.setChanges(changes);
		dxpVariant.setControl(control);
		dxpVariant.setDXPVariantId(dxpVariantId);
		dxpVariant.setDXPVariantName(dxpVariantName);

		return dxpVariant;
	}

	@MockBean
	private DXPClient _dxpClient;

	@Autowired
	private ExperimentDog _experimentDog;

	@Autowired
	private ExperimentsRestController _experimentsRestController;

}