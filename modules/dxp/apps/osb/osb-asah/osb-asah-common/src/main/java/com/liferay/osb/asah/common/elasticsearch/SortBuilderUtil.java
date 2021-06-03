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

import com.liferay.osb.asah.common.model.Sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.Pair;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.NestedSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;

import org.springframework.data.domain.Sort.Order;

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

	public static FieldSortBuilder fieldSort(Sort sort) {
		return fieldSort(sort.getColumn(), SortOrder.valueOf(sort.getType()));
	}

	public static FieldSortBuilder fieldSort(String fieldName) {
		return fieldSort(fieldName, SortOrder.ASC);
	}

	public static FieldSortBuilder fieldSort(
		String fieldName, SortOrder sortOrder) {

		return fieldSort(fieldName, sortOrder, "long");
	}

	public static FieldSortBuilder fieldSort(
		String fieldName, SortOrder sortOrder, String unmappedType) {

		FieldSortBuilder fieldSortBuilder = SortBuilders.fieldSort(fieldName);

		fieldSortBuilder.order(sortOrder);

		return fieldSortBuilder.unmappedType(unmappedType);
	}

	public static List<Order> getOrders(String[] sorts) {
		List<Pair<String, SortOrder>> sortOrderPairs = _getSortOrderPairs(
			sorts);

		if (sortOrderPairs.isEmpty()) {
			return Collections.emptyList();
		}

		Stream<Pair<String, SortOrder>> stream = sortOrderPairs.stream();

		return stream.map(
			sortOrderPair -> {
				if (sortOrderPair.getRight() == SortOrder.ASC) {
					return Order.asc(sortOrderPair.getLeft());
				}

				return Order.desc(sortOrderPair.getLeft());
			}
		).collect(
			Collectors.toList()
		);
	}

	public static List<Pair<String, SortOrder>> getSortOrderPairs(
		String[] sorts) {

		List<Pair<String, SortOrder>> sortOrderPairs = _getSortOrderPairs(
			sorts);

		sortOrderPairs.add(Pair.of("id", SortOrder.ASC));

		return sortOrderPairs;
	}

	private static List<Pair<String, SortOrder>> _getSortOrderPairs(
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

			fieldName = fieldName.replace('/', '.');

			if (sort.contains(",")) {
				SortOrder sortOrder = SortOrder.ASC;

				if (sortParts[1].equals("desc")) {
					sortOrder = SortOrder.DESC;
				}

				sortOrderPairs.add(Pair.of(fieldName, sortOrder));
			}
		}

		return sortOrderPairs;
	}

}