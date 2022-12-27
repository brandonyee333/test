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

import com.liferay.osb.asah.backend.dog.SiteMetricDog;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.common.graphql.GraphQLTypeWiring;
import com.liferay.osb.asah.common.model.AcquisitionType;
import com.liferay.osb.asah.common.model.CompositionResultBag;

import graphql.schema.DataFetchingEnvironment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Matthew Kong
 */
@Component
@GraphQLTypeWiring(fieldName = "acquisitions", typeName = "QueryType")
public class AcquisitionCompositionBagDataFetcher
	extends BaseDataFetcher<CompositionResultBag> {

	@Override
	public CompositionResultBag get(
		DataFetchingEnvironment dataFetchingEnvironment,
		SearchQueryContext searchQueryContext) {

		return _siteMetricDog.getAcquisitionsMetricsCompositionResultBag(
			AcquisitionType.valueOf(
				dataFetchingEnvironment.getArgument("acquisitionType")),
			searchQueryContext.getChannelId(),
			dataFetchingEnvironment.getArgument("size"),
			dataFetchingEnvironment.getArgument("start"),
			searchQueryContext.getTimeRange());
	}

	@Autowired
	private SiteMetricDog _siteMetricDog;

}