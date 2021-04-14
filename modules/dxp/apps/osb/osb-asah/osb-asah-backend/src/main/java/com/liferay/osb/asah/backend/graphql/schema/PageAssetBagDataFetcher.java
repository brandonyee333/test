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

import com.liferay.osb.asah.backend.dto.PageAssetDTO;
import com.liferay.osb.asah.common.dog.AssetDog;
import com.liferay.osb.asah.common.graphql.GraphQLTypeWiring;
import com.liferay.osb.asah.common.model.Asset;
import com.liferay.osb.asah.common.model.PropertyFilter;
import com.liferay.osb.asah.common.model.ResultBag;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.util.ListUtil;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
@GraphQLTypeWiring(fieldName = "pageAssets", typeName = "QueryType")
public class PageAssetBagDataFetcher
	implements DataFetcher<ResultBag<PageAssetDTO>> {

	@Override
	public ResultBag<PageAssetDTO> get(
		DataFetchingEnvironment dataFetchingEnvironment) {

		List<PropertyFilter> propertyFilters = ListUtil.map(
			dataFetchingEnvironment.getArgument("propertyFilters"),
			(Function<Map<String, Object>, PropertyFilter>)
				propertyFilterMap -> {
					PropertyFilter propertyFilter = PropertyFilter.of(
						propertyFilterMap);

					if (Objects.equals(
							propertyFilter.getPropertyName(), "title")) {

						propertyFilter.setPropertyName("name");
					}

					if (Objects.equals(
							propertyFilter.getPropertyName(), "keywords")) {

						propertyFilter.setPropertyName("keywords.keyword");

						propertyFilter.and(
							new PropertyFilter(
								"keywords.type = keyword", false));
					}

					return propertyFilter;
				});

		int size = dataFetchingEnvironment.getArgument("size");
		int start = dataFetchingEnvironment.getArgument("start");

		Page<Asset> assetPage = _assetDog.getAssetPage(
			"Page", dataFetchingEnvironment.getArgument("keywords"),
			propertyFilters, start / size, size,
			_getSort(dataFetchingEnvironment.getArgument("sort")));

		return new ResultBag<>(
			ListUtil.map(assetPage.getContent(), PageAssetDTO::new),
			assetPage.getTotalElements());
	}

	private Sort _getSort(Map<String, String> sort) {
		if (Objects.equals(sort.get("column"), "title")) {
			return new Sort("name", sort.get("type"));
		}

		return Sort.of(sort);
	}

	@Autowired
	private AssetDog _assetDog;

}