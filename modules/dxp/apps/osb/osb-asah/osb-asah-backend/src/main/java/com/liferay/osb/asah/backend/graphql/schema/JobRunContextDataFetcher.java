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

import com.liferay.osb.asah.backend.dto.JobRunDTO;
import com.liferay.osb.asah.common.graphql.GraphQLTypeWiring;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;

import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
@GraphQLTypeWiring(fieldName = "context", typeName = "JobRun")
public class JobRunContextDataFetcher
	implements DataFetcher<List<Pair<String, String>>> {

	@Override
	public List<Pair<String, String>> get(
		DataFetchingEnvironment dataFetchingEnvironment) {

		JobRunDTO jobRunDTO = dataFetchingEnvironment.getSource();

		return _toPairs(jobRunDTO.getContext());
	}

	private List<Pair<String, String>> _toPairs(Map<String, Object> context) {
		List<Pair<String, String>> pairs = new ArrayList<>();

		for (Map.Entry<String, Object> entry : context.entrySet()) {
			pairs.add(
				Pair.of(entry.getKey(), String.valueOf(entry.getValue())));
		}

		return pairs;
	}

}