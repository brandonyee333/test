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
import com.liferay.osb.asah.backend.dto.ExperimentDTO;
import com.liferay.osb.asah.common.entity.Experiment;
import com.liferay.osb.asah.common.graphql.GraphQLTypeWiring;
import com.liferay.osb.asah.common.model.ResultBag;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.util.ListUtil;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

/**
 * @author André Miranda
 */
@Component
@GraphQLTypeWiring(fieldName = "experiments", typeName = "QueryType")
public class ExperimentBagDataFetcher
	implements DataFetcher<ResultBag<ExperimentDTO>> {

	@Override
	public ResultBag<ExperimentDTO> get(
		DataFetchingEnvironment dataFetchingEnvironment) {

		String channelId = dataFetchingEnvironment.getArgument("channelId");
		String keywords = dataFetchingEnvironment.getArgument("keywords");
		Map<String, String> sort = dataFetchingEnvironment.getArgument("sort");
		int size = dataFetchingEnvironment.getArgument("size");
		int start = dataFetchingEnvironment.getArgument("start");

		Page<Experiment> experimentsPage = _experimentDog.getExperimentsPage(
			Long.valueOf(channelId), keywords, start, size, Sort.of(sort));

		return new ResultBag<>(
			ListUtil.map(experimentsPage.getContent(), ExperimentDTO::new),
			experimentsPage.getTotalElements());
	}

	@Autowired
	private ExperimentDog _experimentDog;

}