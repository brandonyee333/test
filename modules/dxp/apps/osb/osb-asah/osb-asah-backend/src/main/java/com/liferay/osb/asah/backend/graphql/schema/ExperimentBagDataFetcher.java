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
import com.liferay.osb.asah.backend.model.ResultBag;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author André Miranda
 */
@Component
@GraphQLTypeWiring(fieldName = "experiments", typeName = "QueryType")
public class ExperimentBagDataFetcher
	implements DataFetcher<ResultBag<Experiment>> {

	@Override
	public ResultBag<Experiment> get(
		DataFetchingEnvironment dataFetchingEnvironment) {

		String channelId = dataFetchingEnvironment.getArgument("channelId");
		String keywords = dataFetchingEnvironment.getArgument("keywords");
		Map<String, String> sort = dataFetchingEnvironment.getArgument("sort");
		int size = dataFetchingEnvironment.getArgument("size");
		int start = dataFetchingEnvironment.getArgument("start");

		return _experimentDog.getExperimentResultBag(
			channelId, SortBuilderUtil.fieldSort(sort), keywords, size, start);
	}

	@Autowired
	private ExperimentDog _experimentDog;

}