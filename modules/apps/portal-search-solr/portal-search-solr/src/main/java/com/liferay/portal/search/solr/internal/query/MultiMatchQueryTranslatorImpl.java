/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.solr.internal.query;

import com.liferay.portal.kernel.search.generic.MultiMatchQuery;
import com.liferay.portal.search.solr.query.MultiMatchQueryTranslator;

import java.util.Map;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;

import org.osgi.service.component.annotations.Component;

/**
 * @author Michael C. Han
 */
@Component(immediate = true, service = MultiMatchQueryTranslator.class)
public class MultiMatchQueryTranslatorImpl
	implements MultiMatchQueryTranslator {

	@Override
	public Query translate(MultiMatchQuery multiMatchQuery) {
		BooleanQuery booleanQuery = new BooleanQuery();

		MultiMatchQuery.Type multiMatchQueryType = multiMatchQuery.getType();

		Map<String, Float> fieldBoosts = multiMatchQuery.getFieldsBoosts();

		for (String field : multiMatchQuery.getFields()) {
			Term term = new Term(field, multiMatchQuery.getValue());

			Query query = null;

			if (multiMatchQueryType == MultiMatchQuery.Type.PHRASE) {
				PhraseQuery phraseQuery = new PhraseQuery();

				phraseQuery.add(term);

				if (multiMatchQuery.getSlop() != null) {
					phraseQuery.setSlop(multiMatchQuery.getSlop());
				}

				query = phraseQuery;
			}
			else if (multiMatchQueryType ==
						MultiMatchQuery.Type.PHRASE_PREFIX) {

				query = new PrefixQuery(term);
			}
			else {
				query = new TermQuery(term);
			}

			if (fieldBoosts.containsKey(field)) {
				Float fieldBoost = fieldBoosts.get(field);

				query.setBoost(fieldBoost);
			}

			booleanQuery.add(query, BooleanClause.Occur.SHOULD);
		}

		return booleanQuery;
	}

}