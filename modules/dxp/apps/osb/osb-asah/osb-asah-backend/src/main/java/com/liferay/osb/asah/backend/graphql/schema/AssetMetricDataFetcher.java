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

import com.liferay.osb.asah.backend.dog.MetricDog;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.graphql.GraphQLTypeWiring;
import com.liferay.osb.asah.backend.model.AssetMetric;

import graphql.language.Field;
import graphql.language.FragmentDefinition;
import graphql.language.FragmentSpread;
import graphql.language.Selection;
import graphql.language.SelectionSet;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.DataFetchingFieldSelectionSet;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Inácio Nery
 */
@Component
@GraphQLTypeWiring(fieldName = "blog", typeName = "QueryType")
@GraphQLTypeWiring(fieldName = "custom", typeName = "QueryType")
@GraphQLTypeWiring(fieldName = "document", typeName = "QueryType")
@GraphQLTypeWiring(fieldName = "form", typeName = "QueryType")
@GraphQLTypeWiring(fieldName = "journal", typeName = "QueryType")
@GraphQLTypeWiring(fieldName = "page", typeName = "QueryType")
@GraphQLTypeWiring(fieldName = "site", typeName = "QueryType")
public class AssetMetricDataFetcher extends BaseDataFetcher<AssetMetric> {

	@Override
	public AssetMetric get(
		DataFetchingEnvironment dataFetchingEnvironment,
		SearchQueryContext searchQueryContext) {

		Map<String, Object> context = dataFetchingEnvironment.getContext();

		return _metricDog.getAssetMetric(
			_getFieldNames(dataFetchingEnvironment), searchQueryContext,
			(Set<String>)context.get("selectedMetrics"));
	}

	private Set<String> _getFieldNames(
		DataFetchingEnvironment dataFetchingEnvironment) {

		DataFetchingFieldSelectionSet dataFetchingFieldSelectionSet =
			dataFetchingEnvironment.getSelectionSet();

		Map<String, List<Field>> selectionSetFields =
			dataFetchingFieldSelectionSet.get();

		Set<String> fieldNames = new HashSet<>();

		for (Map.Entry<String, List<Field>> entry :
			selectionSetFields.entrySet()) {

			for (Field field : entry.getValue()) {
				SelectionSet selectionSet = field.getSelectionSet();

				if (selectionSet != null) {
					List<Selection> selections = selectionSet.getSelections();

					for (Selection selection : selections) {
						if (selection instanceof FragmentSpread) {
							FragmentSpread fragmentSpread =
								(FragmentSpread)selection;

							fieldNames.addAll(
								_getFieldNamesFromFragment(
									dataFetchingEnvironment,
									fragmentSpread.getName()));
						}
						else if (selection instanceof Field) {
							Field selectionField = (Field)selection;

							fieldNames.add(selectionField.getName());
						}
					}
				}
			}
		}

		return fieldNames;
	}

	private Set<String> _getFieldNamesFromFragment(
		DataFetchingEnvironment dataFetchingEnvironment, String fragmentName) {

		Set<String> fieldNames = new HashSet<>();

		Map<String, FragmentDefinition> fragments =
			dataFetchingEnvironment.getFragmentsByName();

		FragmentDefinition fragment = fragments.get(fragmentName);

		SelectionSet selectionSet = fragment.getSelectionSet();

		for (Selection selection : selectionSet.getSelections()) {
			if (selection instanceof Field) {
				Field field = (Field)selection;

				fieldNames.add(field.getName());
			}
		}

		return fieldNames;
	}

	@Autowired
	private MetricDog _metricDog;

}