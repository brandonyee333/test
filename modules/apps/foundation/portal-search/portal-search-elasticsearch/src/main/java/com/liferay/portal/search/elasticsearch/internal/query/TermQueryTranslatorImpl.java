/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch.internal.query;

import com.liferay.portal.kernel.search.QueryTerm;
import com.liferay.portal.kernel.search.TermQuery;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;

import org.osgi.service.component.annotations.Component;

/**
 * @author André de Oliveira
 * @author Miguel Angelo Caldas Gallindo
 */
@Component(immediate = true, service = TermQueryTranslator.class)
public class TermQueryTranslatorImpl implements TermQueryTranslator {

	@Override
	public QueryBuilder translate(TermQuery termQuery) {
		QueryTerm queryTerm = termQuery.getQueryTerm();

		String field = queryTerm.getField();
		String value = queryTerm.getValue();

		TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery(
			field, value);

		if (!termQuery.isDefaultBoost()) {
			termQueryBuilder.boost(termQuery.getBoost());
		}

		return termQueryBuilder;
	}

}