/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch6.internal.query;

import com.liferay.portal.kernel.search.generic.MoreLikeThisQuery;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.elasticsearch6.internal.index.IndexNameBuilder;
import com.liferay.portal.search.elasticsearch6.internal.util.DocumentTypes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.elasticsearch.index.query.MoreLikeThisQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(immediate = true, service = MoreLikeThisQueryTranslator.class)
public class MoreLikeThisQueryTranslatorImpl
	implements MoreLikeThisQueryTranslator {

	@Override
	public QueryBuilder translate(MoreLikeThisQuery moreLikeThisQuery) {
		List<String> fields = moreLikeThisQuery.getFields();

		List<String> likeTexts = new ArrayList<>();

		likeTexts.addAll(fields);

		List<MoreLikeThisQueryBuilder.Item> likeItems = new ArrayList<>();

		if (moreLikeThisQuery.getDocumentUIDs() != null) {
			String type = moreLikeThisQuery.getType();

			if (Validator.isNotNull(type)) {
				type = DocumentTypes.LIFERAY;
			}

			for (String documentUID : moreLikeThisQuery.getDocumentUIDs()) {
				MoreLikeThisQueryBuilder.Item moreLikeThisQueryBuilderItem =
					new MoreLikeThisQueryBuilder.Item(
						indexNameBuilder.getIndexName(
							moreLikeThisQuery.getCompanyId()),
						type, documentUID);

				likeItems.add(moreLikeThisQueryBuilderItem);
			}
		}

		if (Validator.isNotNull(moreLikeThisQuery.getLikeText())) {
			likeTexts.add(moreLikeThisQuery.getLikeText());
		}

		MoreLikeThisQueryBuilder moreLikeThisQueryBuilder =
			QueryBuilders.moreLikeThisQuery(
				likeTexts.toArray(new String[0]),
				likeItems.toArray(new MoreLikeThisQueryBuilder.Item[0]));

		if (Validator.isNotNull(moreLikeThisQuery.getAnalyzer())) {
			moreLikeThisQueryBuilder.analyzer(moreLikeThisQuery.getAnalyzer());
		}

		if (moreLikeThisQuery.getMaxDocFrequency() != null) {
			moreLikeThisQueryBuilder.maxDocFreq(
				moreLikeThisQuery.getMaxDocFrequency());
		}

		if (moreLikeThisQuery.getMaxQueryTerms() != null) {
			moreLikeThisQueryBuilder.maxQueryTerms(
				moreLikeThisQuery.getMaxQueryTerms());
		}

		if (moreLikeThisQuery.getMaxWordLength() != null) {
			moreLikeThisQueryBuilder.maxWordLength(
				moreLikeThisQuery.getMaxWordLength());
		}

		if (moreLikeThisQuery.getMinDocFrequency() != null) {
			moreLikeThisQueryBuilder.minDocFreq(
				moreLikeThisQuery.getMinDocFrequency());
		}

		if (Validator.isNotNull(moreLikeThisQuery.getMinShouldMatch())) {
			moreLikeThisQueryBuilder.minimumShouldMatch(
				moreLikeThisQuery.getMinShouldMatch());
		}

		if (moreLikeThisQuery.getMinTermFrequency() != null) {
			moreLikeThisQueryBuilder.minTermFreq(
				moreLikeThisQuery.getMinTermFrequency());
		}

		if (moreLikeThisQuery.getMinWordLength() != null) {
			moreLikeThisQueryBuilder.minWordLength(
				moreLikeThisQuery.getMinWordLength());
		}

		if (!moreLikeThisQuery.isDefaultBoost()) {
			moreLikeThisQueryBuilder.boost(moreLikeThisQuery.getBoost());
		}

		Collection<String> stopWords = moreLikeThisQuery.getStopWords();

		if (!stopWords.isEmpty()) {
			moreLikeThisQueryBuilder.stopWords(
				stopWords.toArray(new String[0]));
		}

		if (moreLikeThisQuery.getTermBoost() != null) {
			moreLikeThisQueryBuilder.boostTerms(
				moreLikeThisQuery.getTermBoost());
		}

		if (moreLikeThisQuery.isIncludeInput() != null) {
			moreLikeThisQueryBuilder.include(
				moreLikeThisQuery.isIncludeInput());
		}

		return moreLikeThisQueryBuilder;
	}

	@Reference
	protected IndexNameBuilder indexNameBuilder;

}