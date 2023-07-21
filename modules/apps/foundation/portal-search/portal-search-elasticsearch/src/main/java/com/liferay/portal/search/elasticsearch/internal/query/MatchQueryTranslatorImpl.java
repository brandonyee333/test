/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch.internal.query;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.search.generic.MatchQuery;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.osgi.service.component.annotations.Component;

/**
 * @author Michael C. Han
 */
@Component(immediate = true, service = MatchQueryTranslator.class)
public class MatchQueryTranslatorImpl
	extends BaseMatchQueryTranslatorImpl implements MatchQueryTranslator {

	@Override
	public QueryBuilder translate(MatchQuery matchQuery) {
		MatchQueryBuilder matchQueryBuilder = translateMatchQuery(matchQuery);

		if (Validator.isNotNull(matchQuery.getAnalyzer())) {
			matchQueryBuilder.analyzer(matchQuery.getAnalyzer());
		}

		if (matchQuery.getCutOffFrequency() != null) {
			matchQueryBuilder.cutoffFrequency(matchQuery.getCutOffFrequency());
		}

		if (matchQuery.getFuzziness() != null) {
			matchQueryBuilder.fuzziness(
				Fuzziness.build(matchQuery.getFuzziness()));
		}

		if (matchQuery.getFuzzyRewriteMethod() != null) {
			String matchQueryFuzzyRewrite = translate(
				matchQuery.getFuzzyRewriteMethod());

			matchQueryBuilder.fuzzyRewrite(matchQueryFuzzyRewrite);
		}

		if (matchQuery.getMaxExpansions() != null) {
			matchQueryBuilder.maxExpansions(matchQuery.getMaxExpansions());
		}

		if (Validator.isNotNull(matchQuery.getMinShouldMatch())) {
			matchQueryBuilder.minimumShouldMatch(
				matchQuery.getMinShouldMatch());
		}

		if (matchQuery.getOperator() != null) {
			MatchQueryBuilder.Operator operator = translate(
				matchQuery.getOperator());

			matchQueryBuilder.operator(operator);
		}

		if (matchQuery.getPrefixLength() != null) {
			matchQueryBuilder.prefixLength(matchQuery.getPrefixLength());
		}

		if (matchQuery.getSlop() != null) {
			matchQueryBuilder.slop(matchQuery.getSlop());
		}

		if (matchQuery.getZeroTermsQuery() != null) {
			MatchQueryBuilder.ZeroTermsQuery matchQueryBuilderZeroTermsQuery =
				translate(matchQuery.getZeroTermsQuery());

			matchQueryBuilder.zeroTermsQuery(matchQueryBuilderZeroTermsQuery);
		}

		if (!matchQuery.isDefaultBoost()) {
			matchQueryBuilder.boost(matchQuery.getBoost());
		}

		if (matchQuery.isFuzzyTranspositions() != null) {
			matchQueryBuilder.fuzzyTranspositions(
				matchQuery.isFuzzyTranspositions());
		}

		if (matchQuery.isLenient() != null) {
			matchQueryBuilder.setLenient(matchQuery.isLenient());
		}

		return matchQueryBuilder;
	}

	protected MatchQueryBuilder.Type translate(MatchQuery.Type matchQueryType) {
		if (matchQueryType == MatchQuery.Type.BOOLEAN) {
			return MatchQueryBuilder.Type.BOOLEAN;
		}
		else if (matchQueryType == MatchQuery.Type.PHRASE) {
			return MatchQueryBuilder.Type.PHRASE;
		}
		else if (matchQueryType == MatchQuery.Type.PHRASE_PREFIX) {
			return MatchQueryBuilder.Type.PHRASE_PREFIX;
		}

		throw new IllegalArgumentException(
			"Invalid match query type: " + matchQueryType);
	}

	protected MatchQueryBuilder translateMatchQuery(MatchQuery matchQuery) {
		String field = matchQuery.getField();

		MatchQuery.Type matchQueryType = matchQuery.getType();
		String value = matchQuery.getValue();

		if (value.startsWith(StringPool.QUOTE) &&
			value.endsWith(StringPool.QUOTE)) {

			matchQueryType = MatchQuery.Type.PHRASE;

			value = StringUtil.unquote(value);

			if (value.endsWith(StringPool.STAR)) {
				matchQueryType = MatchQuery.Type.PHRASE_PREFIX;
			}
		}

		MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery(
			field, value);

		if (matchQueryType != null) {
			matchQueryBuilder.type(translate(matchQueryType));
		}

		return matchQueryBuilder;
	}

}