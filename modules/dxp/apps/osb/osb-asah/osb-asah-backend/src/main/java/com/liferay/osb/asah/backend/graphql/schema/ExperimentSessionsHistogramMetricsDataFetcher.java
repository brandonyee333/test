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
import com.liferay.osb.asah.backend.dto.ExperimentVariantDTO;
import com.liferay.osb.asah.backend.model.HistogramMetric;
import com.liferay.osb.asah.common.graphql.GraphQLTypeWiring;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
@GraphQLTypeWiring(
	fieldName = "sessionsHistogram", typeName = "ExperimentVariant"
)
@GraphQLTypeWiring(fieldName = "sessionsHistogram", typeName = "Experiment")
public class ExperimentSessionsHistogramMetricsDataFetcher
	implements DataFetcher<List<HistogramMetric>> {

	@Override
	public List<HistogramMetric> get(
		DataFetchingEnvironment dataFetchingEnvironment) {

		Map<String, Object> context = dataFetchingEnvironment.getContext();

		String experimentId = (String)context.get("experimentId");

		return _experimentDog.getExperimentSessionHistogramMetrics(
			Long.valueOf(experimentId),
			_getDXPVariantId(dataFetchingEnvironment.getSource()));
	}

	private String _getDXPVariantId(Object source) {
		if (source instanceof ExperimentVariantDTO) {
			ExperimentVariantDTO experimentVariantDTO =
				(ExperimentVariantDTO)source;

			return experimentVariantDTO.getDXPVariantId();
		}

		return null;
	}

	@Autowired
	private ExperimentDog _experimentDog;

}