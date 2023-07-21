/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.solr.internal.filter;

import com.liferay.portal.kernel.search.BooleanClause;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.search.filter.FilterVisitor;
import com.liferay.portal.search.solr.filter.BooleanFilterTranslator;

import java.util.List;

import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;

import org.osgi.service.component.annotations.Component;

/**
 * @author Michael C. Han
 */
@Component(immediate = true, service = BooleanFilterTranslator.class)
public class BooleanFilterTranslatorImpl implements BooleanFilterTranslator {

	@Override
	public Query translate(
		BooleanFilter booleanFilter, FilterVisitor<Query> filterVisitor) {

		BooleanQuery booleanQuery = new BooleanQuery();

		for (BooleanClause<Filter> booleanClause :
				booleanFilter.getMustBooleanClauses()) {

			Query luceneQuery = translate(booleanClause, filterVisitor);

			if (_isBooleanQueryClausesEmpty(luceneQuery)) {
				continue;
			}

			booleanQuery.add(
				luceneQuery, org.apache.lucene.search.BooleanClause.Occur.MUST);
		}

		for (BooleanClause<Filter> booleanClause :
				booleanFilter.getMustNotBooleanClauses()) {

			Query luceneQuery = translate(booleanClause, filterVisitor);

			booleanQuery.add(
				luceneQuery,
				org.apache.lucene.search.BooleanClause.Occur.MUST_NOT);
		}

		for (BooleanClause<Filter> booleanClause :
				booleanFilter.getShouldBooleanClauses()) {

			Query luceneQuery = translate(booleanClause, filterVisitor);

			booleanQuery.add(
				luceneQuery,
				org.apache.lucene.search.BooleanClause.Occur.SHOULD);
		}

		return booleanQuery;
	}

	protected Query translate(
		BooleanClause<Filter> booleanClause,
		FilterVisitor<Query> filterVisitor) {

		Filter filter = booleanClause.getClause();

		return filter.accept(filterVisitor);
	}

	private static boolean _isBooleanQueryClausesEmpty(Query luceneQuery) {
		if (luceneQuery instanceof BooleanQuery) {
			List<org.apache.lucene.search.BooleanClause> clauses =
				((BooleanQuery)luceneQuery).clauses();

			return clauses.isEmpty();
		}

		return false;
	}

}