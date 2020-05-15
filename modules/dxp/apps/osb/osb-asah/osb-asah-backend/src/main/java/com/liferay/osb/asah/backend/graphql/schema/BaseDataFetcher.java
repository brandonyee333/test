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

import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.model.AssetType;

import graphql.execution.ExecutionTypeInfo;

import graphql.language.Field;
import graphql.language.InlineFragment;
import graphql.language.Selection;
import graphql.language.SelectionSet;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLFieldDefinition;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Marcellus Tavares
 */
public abstract class BaseDataFetcher<T> implements DataFetcher<T> {

	@Override
	public T get(DataFetchingEnvironment dataFetchingEnvironment) {
		Map<String, Object> context = dataFetchingEnvironment.getContext();

		Set<String> selectedMetrics = (Set<String>)context.get(
			"selectedMetrics");

		if ((selectedMetrics == null) || selectedMetrics.isEmpty()) {
			context.put(
				"selectedMetrics",
				_getSelectedMetrics(
					dataFetchingEnvironment.getFields(), new HashSet<>()));
		}

		SearchQueryContext searchQueryContext = (SearchQueryContext)context.get(
			"searchQueryContext");

		if (searchQueryContext == null) {
			searchQueryContext = createSearchQueryContext(
				dataFetchingEnvironment);

			context.put("searchQueryContext", searchQueryContext);
		}

		return get(dataFetchingEnvironment, searchQueryContext);
	}

	public abstract T get(
		DataFetchingEnvironment dataFetchingEnvironment,
		SearchQueryContext searchQueryContext);

	protected SearchQueryContext createSearchQueryContext(
		DataFetchingEnvironment dataFetchingEnvironment) {

		SearchQueryContext searchQueryContext = new SearchQueryContext();

		searchQueryContext.setActive(
			dataFetchingEnvironment.getArgument("active"));
		searchQueryContext.setAssetId(
			dataFetchingEnvironment.getArgument("assetId"));
		searchQueryContext.setAssetType(getAssetType(dataFetchingEnvironment));
		searchQueryContext.setChannelId(
			dataFetchingEnvironment.getArgument("channelId"));
		searchQueryContext.setCountry(
			dataFetchingEnvironment.getArgument("country"));
		searchQueryContext.setDataSourceId(
			dataFetchingEnvironment.getArgument("dataSourceId"));
		searchQueryContext.setDeviceType(
			dataFetchingEnvironment.getArgument("deviceType"));
		searchQueryContext.setExperimentId(
			dataFetchingEnvironment.getArgument("experimentId"));

		if (dataFetchingEnvironment.getArgument("includePrevious") != null) {
			searchQueryContext.setIncludePrevious(
				dataFetchingEnvironment.getArgument("includePrevious"));
		}

		searchQueryContext.setInterval(
			dataFetchingEnvironment.getArgument("interval"));
		searchQueryContext.setKeywords(
			dataFetchingEnvironment.getArgument("keywords"));

		if (dataFetchingEnvironment.getArgument("rangeKey") != null) {
			searchQueryContext.setRangeKey(
				dataFetchingEnvironment.getArgument("rangeKey"));
		}

		searchQueryContext.setTerms(
			dataFetchingEnvironment.getArgument("terms"));
		searchQueryContext.setTimeZoneId(
			dataFetchingEnvironment.getArgument("timeZoneId"));
		searchQueryContext.setTitle(
			dataFetchingEnvironment.getArgument("title"));
		searchQueryContext.setURL(dataFetchingEnvironment.getArgument("url"));
		searchQueryContext.setVariantId(
			dataFetchingEnvironment.getArgument("variantId"));

		return searchQueryContext;
	}

	protected AssetType getAssetType(
		DataFetchingEnvironment dataFetchingEnvironment) {

		String assetTypeString = dataFetchingEnvironment.getArgument(
			"assetType");

		if (assetTypeString != null) {
			return AssetType.valueOf(assetTypeString);
		}

		ExecutionTypeInfo executionTypeInfo =
			dataFetchingEnvironment.getFieldTypeInfo();

		GraphQLFieldDefinition graphQLFieldDefinition =
			executionTypeInfo.getFieldDefinition();

		return AssetType.of(graphQLFieldDefinition.getName());
	}

	private Set<String> _getSelectedMetrics(
		List<Field> fields, Set<String> selectedMetrics) {

		if (fields == null) {
			return selectedMetrics;
		}

		for (Field field : fields) {
			for (Selection selection :
					_getSelections(field.getSelectionSet())) {

				if (selection instanceof Field) {
					Field selectionField = (Field)selection;

					String fieldName = selectionField.getName();

					if (fieldName.endsWith("Metric")) {
						selectedMetrics.add(selectionField.getName());
					}

					selectedMetrics.addAll(
						_getSelectedMetrics(
							Collections.singletonList(selectionField),
							selectedMetrics));
				}
				else if (selection instanceof InlineFragment) {
					InlineFragment inlineFragment = (InlineFragment)selection;

					for (Selection fragmentSelection :
							_getSelections(inlineFragment.getSelectionSet())) {

						if (fragmentSelection instanceof Field) {
							Field fragmentSelectionField =
								(Field)fragmentSelection;

							String fieldName = fragmentSelectionField.getName();

							if (fieldName.endsWith("Metric")) {
								selectedMetrics.add(
									fragmentSelectionField.getName());
							}
						}
					}
				}
			}
		}

		return selectedMetrics;
	}

	private List<Selection> _getSelections(SelectionSet selectionSet) {
		if (selectionSet == null) {
			return Collections.emptyList();
		}

		return selectionSet.getSelections();
	}

}