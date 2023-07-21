/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.solr.internal.query;

import com.liferay.portal.kernel.search.TermRangeQuery;
import com.liferay.portal.search.solr.query.TermRangeQueryTranslator;

import org.apache.lucene.search.Query;

import org.osgi.service.component.annotations.Component;

/**
 * @author André de Oliveira
 * @author Miguel Angelo Caldas Gallindo
 */
@Component(immediate = true, service = TermRangeQueryTranslator.class)
public class TermRangeQueryTranslatorImpl implements TermRangeQueryTranslator {

	@Override
	public Query translate(TermRangeQuery termRangeQuery) {
		org.apache.lucene.search.TermRangeQuery luceneTermRangeQuery =
			org.apache.lucene.search.TermRangeQuery.newStringRange(
				termRangeQuery.getField(), termRangeQuery.getLowerTerm(),
				termRangeQuery.getUpperTerm(), termRangeQuery.includesLower(),
				termRangeQuery.includesUpper());

		if (!termRangeQuery.isDefaultBoost()) {
			luceneTermRangeQuery.setBoost(termRangeQuery.getBoost());
		}

		return luceneTermRangeQuery;
	}

}