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

import java.util.Collection;

import org.apache.commons.lang3.StringUtils;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.springframework.util.CollectionUtils;

/**
 * @author Marcellus Tavares
 */
public class BoolQueryBuilderUtil {

	public static BoolQueryBuilder filter(QueryBuilder queryBuilder) {
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

		return boolQueryBuilder.filter(queryBuilder);
	}

	public static BoolQueryBuilder filterTerm(
		BoolQueryBuilder boolQueryBuilder, String name, String value) {

		if (StringUtils.isNoneEmpty(name, value)) {
			boolQueryBuilder.filter(QueryBuilders.termQuery(name, value));
		}

		return boolQueryBuilder;
	}

	public static BoolQueryBuilder filterTerms(
		BoolQueryBuilder boolQueryBuilder, String name, Collection<?> values) {

		if (!CollectionUtils.isEmpty(values)) {
			boolQueryBuilder.filter(QueryBuilders.termsQuery(name, values));
		}

		return boolQueryBuilder;
	}

	public static BoolQueryBuilder mustNot(QueryBuilder queryBuilder) {
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

		return boolQueryBuilder.mustNot(queryBuilder);
	}

	public static BoolQueryBuilder should(QueryBuilder queryBuilder) {
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

		return boolQueryBuilder.should(queryBuilder);
	}

	public static BoolQueryBuilder shouldNot(QueryBuilder queryBuilder) {
		return should(mustNot(queryBuilder));
	}

}