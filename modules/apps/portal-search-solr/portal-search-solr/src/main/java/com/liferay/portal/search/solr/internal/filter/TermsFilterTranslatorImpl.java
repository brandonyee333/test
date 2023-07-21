/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.solr.internal.filter;

import com.liferay.portal.kernel.search.filter.TermsFilter;
import com.liferay.portal.search.solr.filter.TermsFilterTranslator;

import java.util.ArrayList;

import org.apache.lucene.index.Term;
import org.apache.lucene.queries.TermsQuery;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.solr.client.solrj.util.ClientUtils;

import org.osgi.service.component.annotations.Component;

/**
 * @author Michael C. Han
 */
@Component(immediate = true, service = TermsFilterTranslator.class)
public class TermsFilterTranslatorImpl implements TermsFilterTranslator {

	@Override
	public Query translate(TermsFilter termsFilter) {
		String field = termsFilter.getField();

		ArrayList<Term> terms = new ArrayList<>();

		for (String value : termsFilter.getValues()) {
			terms.add(new Term(field, ClientUtils.escapeQueryChars(value)));
		}

		TermsQuery termsQuery = new TermsQuery(terms);

		if (terms.size() == 1) {
			return termsQuery;
		}

		BooleanQuery booleanQuery = new BooleanQuery();

		booleanQuery.add(termsQuery, BooleanClause.Occur.SHOULD);

		return booleanQuery;
	}

}