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

package com.liferay.osb.asah.common.rest.response;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONArray;

/**
 * @author Shinn Lok
 */
public interface TransformationJSONArrayFunction {

	public JSONArray apply(
			String collectionName, String computeFunctionString,
			ElasticsearchInvoker elasticsearchInvoker, int page, int size,
			List<Pair<String, SortOrder>> sortOrderPairs,
			String supportedFieldName, QueryBuilder queryBuilder)
		throws Exception;

	public long getTotalElements();

}