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

package com.liferay.osb.asah.backend.graphql.schema;

import com.liferay.osb.asah.backend.dog.ExperimentDog;
import com.liferay.osb.asah.backend.graphql.GraphQLTypeWiring;
import com.liferay.osb.asah.backend.model.Experiment;
import com.liferay.osb.asah.common.model.ExperimentStatus;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author André Miranda
 */
@Component
@GraphQLTypeWiring(fieldName = "experiment", typeName = "MutationType")
public class ExperimentMutationDataFetcher
	extends BaseExperimentDataFetcher implements DataFetcher<Experiment> {

	@Override
	public Experiment get(DataFetchingEnvironment dataFetchingEnvironment) {
		Experiment experiment = new Experiment();

		experiment.setExperimentStatus(
			ExperimentStatus.valueOf(
				dataFetchingEnvironment.getArgument("status")));
		experiment.setId(dataFetchingEnvironment.getArgument("experimentId"));
		experiment.setPublishedDXPVariantId(
			dataFetchingEnvironment.getArgument("publishedDXPVariantId"));

		Map<String, Object> experimentSettingsMap =
			dataFetchingEnvironment.getArgument("experimentSettings");

		return _experimentDog.patchExperiment(
			experiment, createExperimentSettings(experimentSettingsMap), true);
	}

	@Autowired
	private ExperimentDog _experimentDog;

}