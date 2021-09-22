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

package com.liferay.osb.asah.common.elasticsearch.converter.helper.faro.info;

import com.liferay.osb.asah.common.converter.helper.DefaultFilterStringConverterHelper;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.util.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

/**
 * @author Inácio Nery
 */
public class FaroInfoAssetFilterStringConverterHelper
	extends DefaultFilterStringConverterHelper {

	@Override
	public QueryBuilder getCustomFunctionQueryBuilder(
		List<String> arguments, String customFunctionName, boolean negated) {

		if (!customFunctionName.equalsIgnoreCase("similarTo")) {
			return null;
		}

		Map<String, String> fieldNames = getFieldNameConversionMap();

		String fieldName = toFieldName(
			fieldNames.getOrDefault(arguments.get(0), arguments.get(0)));

		QueryBuilder queryBuilder = QueryBuilders.regexpQuery(
			fieldName,
			StringUtil.unquoteAndDecodeInnerQuotes(arguments.get(1)));

		if (negated) {
			return BoolQueryBuilderUtil.mustNot(queryBuilder);
		}

		return queryBuilder;
	}

	@Override
	public Map<String, String> getFieldNameConversionMap() {
		return new HashMap<String, String>() {
			{
				put("title", "name");
			}
		};
	}

}