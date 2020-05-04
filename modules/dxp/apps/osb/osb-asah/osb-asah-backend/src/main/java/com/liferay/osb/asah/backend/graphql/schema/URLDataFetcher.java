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

import com.liferay.osb.asah.backend.graphql.GraphQLTypeWiring;
import com.liferay.osb.asah.backend.model.AssetMetric;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import java.util.List;

import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
@GraphQLTypeWiring(fieldName = "urls", typeName = "BlogMetric")
@GraphQLTypeWiring(fieldName = "urls", typeName = "DocumentMetric")
@GraphQLTypeWiring(fieldName = "urls", typeName = "FormMetric")
@GraphQLTypeWiring(fieldName = "urls", typeName = "JournalMetric")
@GraphQLTypeWiring(fieldName = "urls", typeName = "PageMetric")
public class URLDataFetcher implements DataFetcher<List<String>> {

	@Override
	public List<String> get(DataFetchingEnvironment dataFetchingEnvironment) {
		AssetMetric assetMetric = dataFetchingEnvironment.getSource();

		return assetMetric.getURLs();
	}

}