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

import com.liferay.osb.asah.backend.dog.SiteVisitorHeatMapDog;
import com.liferay.osb.asah.backend.graphql.GraphQLTypeWiring;
import com.liferay.osb.asah.backend.model.HeatMapMetric;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Leslie Wong
 */
@Component
@GraphQLTypeWiring(fieldName = "siteVisitorHeatMap", typeName = "QueryType")
public class SiteVisitorHeatMapDataFetcher
	implements DataFetcher<List<HeatMapMetric>> {

	@Override
	public List<HeatMapMetric> get(
		DataFetchingEnvironment dataFetchingEnvironment) {

		return siteVisitorHeatMapDog.getHeatMapMetrics(
			dataFetchingEnvironment.getArgument("assetId"),
			dataFetchingEnvironment.getArgument("channelId"),
			dataFetchingEnvironment.getArgument("rangeKey"),
			dataFetchingEnvironment.getArgument("timeZoneId"));
	}

	@Autowired
	protected SiteVisitorHeatMapDog siteVisitorHeatMapDog;

}