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

package com.liferay.osb.customer.downloads.display.web.internal.display.context;

import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.dynamic.data.mapping.util.DDMIndexer;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.osb.customer.downloads.display.web.configuration.DownloadsDisplayConfiguration;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author Amos Fong
 */
public class DownloadsDisplayContext {

	public DownloadsDisplayContext(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws Exception {

		_renderRequest = renderRequest;
		_renderResponse = renderResponse;

		_ddmIndexer = (DDMIndexer)_renderRequest.getAttribute(
			DDMIndexer.class.getName());
		_downloadsDisplayConfiguration =
			(DownloadsDisplayConfiguration)_renderRequest.getAttribute(
				DownloadsDisplayConfiguration.class.getName());
		_themeDisplay = (ThemeDisplay)_renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long classNameId = ClassNameLocalServiceUtil.getClassNameId(
			JournalArticle.class);

		_ddmStructure = DDMStructureLocalServiceUtil.getStructure(
			_themeDisplay.getCompanyGroupId(), classNameId,
			_downloadsDisplayConfiguration.ddmStructureKey());
	}

	public SearchContainer getSearchContainer() throws PortalException {
		SearchContainer searchContainer = new SearchContainer(
			_renderRequest, _renderResponse.createRenderURL(), null, null);

		searchContainer.setSearch(true);

		Indexer indexer = IndexerRegistryUtil.getIndexer(JournalArticle.class);

		SearchContext searchContext = buildSearchContext(
			searchContainer.getStart(), searchContainer.getEnd());

		Hits hits = indexer.search(searchContext);

		searchContainer.setTotal(hits.getLength());

		List results = new ArrayList<>();

		for (Document document : hits.getDocs()) {
			long classPK = GetterUtil.getLong(
				document.get(Field.ENTRY_CLASS_PK));

			JournalArticle journalArticle =
				JournalArticleLocalServiceUtil.fetchLatestArticle(
					classPK, WorkflowConstants.STATUS_APPROVED);

			results.add(journalArticle);
		}

		searchContainer.setResults(results);

		return searchContainer;
	}

	protected SearchContext buildSearchContext(int start, int end) {
		SearchContext searchContext = new SearchContext();

		searchContext.setAndSearch(true);

		searchContext.setAttribute(
			Field.ENTRY_CLASS_NAME, JournalArticle.class.getName());
		searchContext.setAttribute(
			Field.STATUS, WorkflowConstants.STATUS_APPROVED);

		searchContext.setAttribute(
			"ddmStructureKey", _ddmStructure.getStructureKey());
		searchContext.setAttribute("head", Boolean.TRUE);
		searchContext.setAttribute("latest", Boolean.TRUE);

		searchContext.setCompanyId(_themeDisplay.getCompanyId());
		searchContext.setEnd(end);
		searchContext.setGroupIds(new long[] {_themeDisplay.getScopeGroupId()});

		QueryConfig queryConfig = new QueryConfig();

		queryConfig.setHighlightEnabled(false);
		queryConfig.setScoreEnabled(false);

		searchContext.setQueryConfig(queryConfig);

		String sortFieldName = _ddmIndexer.encodeName(
			_ddmStructure.getStructureId(), "downloadNumber",
			_themeDisplay.getLocale());

		sortFieldName += "_Number";

		Sort sort = new Sort(sortFieldName, Sort.LONG_TYPE, true);

		searchContext.setSorts(sort);

		searchContext.setStart(start);

		return searchContext;
	}

	private final DDMIndexer _ddmIndexer;
	private final DDMStructure _ddmStructure;
	private final DownloadsDisplayConfiguration _downloadsDisplayConfiguration;
	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;
	private final ThemeDisplay _themeDisplay;

}