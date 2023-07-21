/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.solr.internal.query;

import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.generic.DisMaxQuery;
import com.liferay.portal.kernel.search.query.QueryVisitor;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.search.solr.query.DisMaxQueryTranslator;

import org.apache.lucene.search.DisjunctionMaxQuery;

import org.osgi.service.component.annotations.Component;

/**
 * @author Michael C. Han
 */
@Component(immediate = true, service = DisMaxQueryTranslator.class)
public class DisMaxQueryTranslatorImpl implements DisMaxQueryTranslator {

	@Override
	public org.apache.lucene.search.Query translate(
		DisMaxQuery disMaxQuery,
		QueryVisitor<org.apache.lucene.search.Query> queryVisitor) {

		float tieBreaker = GetterUtil.getFloat(disMaxQuery.getTieBreaker());

		DisjunctionMaxQuery disjunctionMaxQuery = new DisjunctionMaxQuery(
			tieBreaker);

		if (!disMaxQuery.isDefaultBoost()) {
			disjunctionMaxQuery.setBoost(disjunctionMaxQuery.getBoost());
		}

		for (Query query : disMaxQuery.getQueries()) {
			org.apache.lucene.search.Query luceneQuery = query.accept(
				queryVisitor);

			disjunctionMaxQuery.add(luceneQuery);
		}

		return disjunctionMaxQuery;
	}

}