/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.solr.internal.query;

import com.liferay.portal.kernel.search.QueryTerm;
import com.liferay.portal.kernel.search.TermQuery;
import com.liferay.portal.search.solr.query.TermQueryTranslator;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.Query;

import org.osgi.service.component.annotations.Component;

/**
 * @author André de Oliveira
 * @author Miguel Angelo Caldas Gallindo
 */
@Component(immediate = true, service = TermQueryTranslator.class)
public class TermQueryTranslatorImpl implements TermQueryTranslator {

	@Override
	public Query translate(TermQuery termQuery) {
		QueryTerm queryTerm = termQuery.getQueryTerm();

		String field = queryTerm.getField();
		String value = queryTerm.getValue();

		Query luceneQuery = new org.apache.lucene.search.TermQuery(
			new Term(field, value));

		if (!termQuery.isDefaultBoost()) {
			luceneQuery.setBoost(termQuery.getBoost());
		}

		return luceneQuery;
	}

}