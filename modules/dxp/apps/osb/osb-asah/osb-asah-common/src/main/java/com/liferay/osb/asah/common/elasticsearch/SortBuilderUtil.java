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

package com.liferay.osb.asah.common.elasticsearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.NestedSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;

/**
 * @author Shinn Lok
 */
public class SortBuilderUtil {

	public static FieldSortBuilder buildSort(
		String fieldName, String path, QueryBuilder queryBuilder,
		SortOrder sortOrder) {

		FieldSortBuilder fieldSortBuilder = fieldSort(fieldName, sortOrder);

		if (queryBuilder != null) {
			NestedSortBuilder nestedSortBuilder = new NestedSortBuilder(path);

			nestedSortBuilder.setFilter(queryBuilder);

			fieldSortBuilder.setNestedSort(nestedSortBuilder);
		}

		return fieldSortBuilder;
	}

	public static FieldSortBuilder fieldSort(Map<String, String> sort) {
		return fieldSort(
			sort.get("column"), SortOrder.valueOf(sort.get("type")));
	}

	public static FieldSortBuilder fieldSort(String fieldName) {
		return fieldSort(fieldName, SortOrder.ASC);
	}

	public static FieldSortBuilder fieldSort(
		String fieldName, SortOrder sortOrder) {

		FieldSortBuilder fieldSortBuilder = SortBuilders.fieldSort(fieldName);

		fieldSortBuilder.order(sortOrder);

		return fieldSortBuilder.unmappedType("long");
	}

	public static List<Pair<String, SortOrder>> getSortOrderPairs(
		String[] sorts) {

		if (sorts == null) {
			return Collections.emptyList();
		}

		List<String> sortsList = new ArrayList<>();

		for (int i = sorts.length - 1; i >= 0; i--) {
			String sort = sorts[i];

			if (sort.equals("asc") || sort.equals("desc")) {
				sortsList.add(0, String.join(",", sorts[i - 1], sorts[i]));

				i -= 1;
			}
			else {
				sortsList.add(0, sorts[i]);
			}
		}

		List<Pair<String, SortOrder>> sortOrderPairs = new ArrayList<>();

		for (String sort : sortsList) {
			String[] sortParts = sort.split(",");

			String fieldName = sortParts[0];

			fieldName = fieldName.replaceAll("/", ".");

			if (sort.contains(",")) {
				SortOrder sortOrder = SortOrder.ASC;

				if (sortParts[1].equals("desc")) {
					sortOrder = SortOrder.DESC;
				}

				sortOrderPairs.add(Pair.of(fieldName, sortOrder));
			}
		}

		sortOrderPairs.add(Pair.of("id", SortOrder.ASC));

		return sortOrderPairs;
	}

}