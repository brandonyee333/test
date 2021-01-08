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

import com.liferay.osb.asah.backend.dog.SiteInterestCompositionDog;
import com.liferay.osb.asah.backend.graphql.GraphQLTypeWiring;
import com.liferay.osb.asah.backend.model.CompositionResultBag;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Rachael Koestartyo
 */
@Component
@GraphQLTypeWiring(fieldName = "siteInterests", typeName = "QueryType")
public class SiteInterestCompositionBagDataFetcher
	implements DataFetcher<CompositionResultBag> {

	@Override
	public CompositionResultBag get(
		DataFetchingEnvironment dataFetchingEnvironment) {

		return _siteInterestCompositionDog.getCompositionResultBag(
			dataFetchingEnvironment.getArgument("channelId"),
			dataFetchingEnvironment.getArgument("dataSourceId"),
			dataFetchingEnvironment.getArgument("rangeKey"),
			dataFetchingEnvironment.getArgument("size"),
			dataFetchingEnvironment.getArgument("start"));
	}

	@Autowired
	private SiteInterestCompositionDog _siteInterestCompositionDog;

}