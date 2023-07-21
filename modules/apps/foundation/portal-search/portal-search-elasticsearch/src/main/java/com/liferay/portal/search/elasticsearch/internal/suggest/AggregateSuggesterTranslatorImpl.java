/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch.internal.suggest;

import com.liferay.portal.kernel.search.suggest.AggregateSuggester;
import com.liferay.portal.kernel.search.suggest.Suggester;
import com.liferay.portal.kernel.search.suggest.SuggesterTranslator;

import java.util.List;
import java.util.Map;

import org.elasticsearch.search.suggest.SuggestBuilder;

import org.osgi.service.component.annotations.Component;

/**
 * @author Michael C. Han
 */
@Component(immediate = true, service = AggregateSuggesterTranslator.class)
public class AggregateSuggesterTranslatorImpl
	implements AggregateSuggesterTranslator {

	@Override
	public SuggestBuilder translate(
		AggregateSuggester aggregateSuggester,
		SuggesterTranslator<SuggestBuilder> suggesterTranslator) {

		SuggestBuilder aggregateSuggestBuilder = new SuggestBuilder(
			aggregateSuggester.getName());

		aggregateSuggestBuilder.setText(aggregateSuggester.getValue());

		Map<String, Suggester> suggesters = aggregateSuggester.getSuggesters();

		for (Suggester suggester : suggesters.values()) {
			SuggestBuilder suggestBuilder = suggesterTranslator.translate(
				suggester, null);

			List<SuggestBuilder.SuggestionBuilder<?>> suggestionBuilders =
				suggestBuilder.getSuggestion();

			for (SuggestBuilder.SuggestionBuilder<?> suggestionBuilder :
					suggestionBuilders) {

				suggestionBuilder.text(null);

				aggregateSuggestBuilder.addSuggestion(suggestionBuilder);
			}
		}

		return aggregateSuggestBuilder;
	}

}