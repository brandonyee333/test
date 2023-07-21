/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch.internal.suggest;

import com.liferay.portal.kernel.search.suggest.Suggester;

/**
 * @author Michael C. Han
 */
public abstract class BaseSuggesterTranslatorImpl {

	protected String translate(Suggester.Sort sort) {
		if (sort == Suggester.Sort.FREQUENCY) {
			return "frequency";
		}

		return "score";
	}

	protected String translate(Suggester.StringDistance stringDistance) {
		if (stringDistance == Suggester.StringDistance.DAMERAU_LEVENSHTEIN) {
			return "damerau_levnshtein";
		}
		else if (stringDistance == Suggester.StringDistance.JAROWINKLER) {
			return "jarowinkler";
		}
		else if (stringDistance == Suggester.StringDistance.LEVENSTEIN) {
			return "levenstein";
		}
		else if (stringDistance == Suggester.StringDistance.NGRAM) {
			return "ngram";
		}

		return "internal";
	}

	protected String translate(Suggester.SuggestMode suggestMode) {
		if (suggestMode == Suggester.SuggestMode.ALWAYS) {
			return "always";
		}
		else if (suggestMode == Suggester.SuggestMode.POPULAR) {
			return "popular";
		}

		return "missing";
	}

}