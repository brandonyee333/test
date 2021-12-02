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

import com.liferay.osb.asah.common.dog.EventAnalysisDog;
import com.liferay.osb.asah.common.graphql.GraphQLTypeWiring;
import com.liferay.osb.asah.common.util.ListUtil;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Rachael Koestartyo
 */
@Component
@GraphQLTypeWiring(fieldName = "deleteEventAnalyses", typeName = "MutationType")
public class DeleteEventAnalysisMutationDataFetcher
	implements DataFetcher<Void> {

	@Override
	public Void get(DataFetchingEnvironment dataFetchingEnvironment) {
		List<String> eventAnalysisIds = dataFetchingEnvironment.getArgument(
			"eventAnalysisIds");

		_eventAnalysisDog.deleteEventAnalyses(
			ListUtil.map(eventAnalysisIds, Long::valueOf));

		return null;
	}

	@Autowired
	private EventAnalysisDog _eventAnalysisDog;

}