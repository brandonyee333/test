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

package com.liferay.osb.asah.backend.rest.controller.api.data.source.v1;

import com.liferay.osb.asah.backend.dog.ExperimentDog;
import com.liferay.osb.asah.backend.model.DXPVariant;
import com.liferay.osb.asah.backend.model.DXPVariants;
import com.liferay.osb.asah.backend.model.Experiment;
import com.liferay.osb.asah.backend.model.ExperimentSettings;
import com.liferay.osb.asah.backend.model.Goal;
import com.liferay.osb.asah.backend.rest.controller.BaseRestController;
import com.liferay.osb.asah.common.model.DXPVariantSettings;
import com.liferay.osb.asah.common.model.ExperimentMetrics;
import com.liferay.osb.asah.common.model.ExperimentStatus;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Marcellus Tavares
 */
@RequestMapping(produces = "application/json", value = "/api/1.0/experiments")
@RestController
public class ExperimentsRestController extends BaseRestController {

	@DeleteMapping("/{id}")
	public void deleteExperiment(@PathVariable String id) {
		_experimentDog.deleteExperiment(id, false);
	}

	@GetMapping("/{id}/calculate-metrics")
	public ExperimentMetrics getCalculateExperimentMetrics(
		@PathVariable String id) {

		return _experimentDog.calculateExperimentMetrics(id);
	}

	@GetMapping("/{id}")
	public Experiment getExperiment(@PathVariable String id) {
		return _experimentDog.getExperiment(id);
	}

	@PatchMapping("/{id}")
	public void patchExperiment(
		@PathVariable String id, @RequestBody @Valid Experiment experiment) {

		experiment.setId(id);

		ExperimentSettings experimentSettings = null;

		if (experiment.getExperimentStatus() == ExperimentStatus.RUNNING) {
			experimentSettings = _createExperimentSettings(experiment);
		}

		_experimentDog.patchExperiment(experiment, experimentSettings, false);
	}

	@PostMapping
	public Experiment postExperiment(
		@RequestBody @Valid Experiment experiment) {

		return _experimentDog.addExperiment(experiment);
	}

	@PostMapping("/{id}/estimated-days-duration")
	public Long postExperimentEstimatedDaysDuration(
		@PathVariable String id,
		@RequestBody @Valid ExperimentSettings experimentSettings) {

		List<DXPVariantSettings> dxpVariantsSettings =
			experimentSettings.getDXPVariantsSettings();

		double variantsTrafficSum = _sumVariantsTraffic(dxpVariantsSettings);

		if (variantsTrafficSum != 100) {
			throw new OSBAsahException(
				HttpStatus.BAD_REQUEST,
				"The sum of all variant traffic splits must be 100");
		}

		return _experimentDog.getExperimentEstimatedDaysDuration(
			experimentSettings.getConfidenceLevel(), dxpVariantsSettings, id);
	}

	@PutMapping("/{id}/dxp-variants")
	public void putDXPVariants(
		@PathVariable String id, @RequestBody @Valid DXPVariants dxpVariants) {

		Experiment experiment = _experimentDog.getExperiment(id);

		experiment.setDXPVariants(dxpVariants.getDXPVariants());

		_experimentDog.updateExperiment(experiment);
	}

	@PutMapping("/{id}/goal")
	public void putGoal(
		@PathVariable String id, @RequestBody @Valid Goal goal) {

		Experiment experiment = _experimentDog.getExperiment(id);

		experiment.setGoal(goal);

		_experimentDog.updateExperiment(experiment);
	}

	private List<DXPVariantSettings> _createDXPVariantSettings(
		List<DXPVariant> dxpVariants) {

		Stream<DXPVariant> stream = dxpVariants.stream();

		return stream.map(
			dxpVariant -> new DXPVariantSettings(
				dxpVariant.isControl(), dxpVariant.getDXPVariantId(),
				dxpVariant.getTrafficSplit())
		).collect(
			Collectors.toList()
		);
	}

	private ExperimentSettings _createExperimentSettings(
		Experiment experiment) {

		Double confidenceLevel = experiment.getConfidenceLevel();

		if (confidenceLevel == 0) {
			return null;
		}

		ExperimentSettings experimentSettings = new ExperimentSettings();

		experimentSettings.setConfidenceLevel(confidenceLevel);
		experimentSettings.setDXPVariantsSettings(
			_createDXPVariantSettings(experiment.getDXPVariants()));

		return experimentSettings;
	}

	private double _sumVariantsTraffic(
		List<DXPVariantSettings> dxpVariantsSettings) {

		Stream<DXPVariantSettings> stream = dxpVariantsSettings.stream();

		return stream.map(
			DXPVariantSettings::getTrafficSplit
		).reduce(
			Double::sum
		).orElse(
			0.0
		);
	}

	@Autowired
	private ExperimentDog _experimentDog;

}