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

package com.liferay.osb.customer.downloads.display.web.internal.indexer;

import com.liferay.dynamic.data.mapping.util.DDMIndexer;
import com.liferay.portal.kernel.search.BaseIndexerPostProcessor;
import com.liferay.portal.kernel.search.IndexerPostProcessor;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

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
	public void postProcessContextBooleanFilter(
			BooleanFilter booleanFilter, SearchContext searchContext)
		throws Exception {

		long ddmStructureId = GetterUtil.getLong(
			searchContext.getAttribute("ddmStructureId"));

		String fileType = (String)searchContext.getAttribute("fileType");

		if (Validator.isNotNull(fileType)) {
			String fieldName = _ddmIndexer.encodeName(
				ddmStructureId, "fileType", searchContext.getLocale());

			booleanFilter.addRequiredTerm(fieldName, fileType);
		}

		String product = (String)searchContext.getAttribute("product");

		if (Validator.isNotNull(product)) {
			String fieldName = _ddmIndexer.encodeName(
				ddmStructureId, "product", searchContext.getLocale());

			booleanFilter.addRequiredTerm(fieldName, product);
		}
	}

	@Reference
	private DDMIndexer _ddmIndexer;

}