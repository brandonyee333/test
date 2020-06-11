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

import com.liferay.osb.asah.backend.dog.AssetDog;
import com.liferay.osb.asah.backend.graphql.GraphQLTypeWiring;
import com.liferay.osb.asah.backend.model.PageAsset;
import com.liferay.osb.asah.backend.model.ResultBag;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
@GraphQLTypeWiring(fieldName = "pageAssets", typeName = "QueryType")
public class PageAssetBagDataFetcher
	implements DataFetcher<ResultBag<PageAsset>> {

	@Override
	public ResultBag<PageAsset> get(
		DataFetchingEnvironment dataFetchingEnvironment) {

		return _assetDog.getPageAssetResultBag(
			dataFetchingEnvironment.getArgument("keywords"),
			dataFetchingEnvironment.getArgument("propertyFilter"),
			dataFetchingEnvironment.getArgument("size"),
			dataFetchingEnvironment.getArgument("sort"),
			dataFetchingEnvironment.getArgument("start"));
	}

	@Autowired
	private AssetDog _assetDog;

}