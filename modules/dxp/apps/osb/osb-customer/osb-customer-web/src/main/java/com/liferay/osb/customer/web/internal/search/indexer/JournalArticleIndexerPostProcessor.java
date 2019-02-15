/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.customer.web.internal.search.indexer;

import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.search.BaseIndexerPostProcessor;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.IndexerPostProcessor;
import com.liferay.portal.kernel.util.LocalizationUtil;

import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true,
	property = "indexer.class.name=com.liferay.journal.model.JournalArticle",
	service = IndexerPostProcessor.class
)
public class JournalArticleIndexerPostProcessor
	extends BaseIndexerPostProcessor {

	@Override
	public void postProcessDocument(Document document, Object obj)
		throws Exception {

		JournalArticle journalArticle = (JournalArticle)obj;

		String[] languageIds = LocalizationUtil.getAvailableLanguageIds(
			journalArticle.getDocument());

		document.addText("availableLanguageIds", languageIds);
	}

}