/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.web.internal.indexer;

import com.liferay.osb.loop.model.LoopTopic;
import com.liferay.osb.loop.web.internal.util.LoopMarkdownUtil;
import com.liferay.osb.loop.web.internal.util.LoopStatsEntryUtil;
import com.liferay.osb.loop.web.internal.util.LoopStreamEntryUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.PortalUtil;

/**
 * @author Timothy Bell
 */
public class LoopTopicIndexer extends BaseLoopIndexer {

	public static LoopTopicIndexer getInstance() {
		return _instance;
	}

	@Override
	public void postProcessContextBooleanFilter(
			BooleanFilter contextBooleanFilter, SearchContext searchContext)
		throws Exception {

		super.postProcessContextBooleanFilter(
			contextBooleanFilter, searchContext);

		long createTime = GetterUtil.getLong(
			searchContext.getAttribute("createTime"));

		if (createTime > 0) {
			BooleanFilter booleanFilter = new BooleanFilter();

			booleanFilter.addRangeTerm("createDate_sortable", 0, createTime);

			contextBooleanFilter.add(booleanFilter, BooleanClauseOccur.MUST);
		}

		contextBooleanFilter.addRequiredTerm("parentLoopTopicId", 0);
	}

	@Override
	public void postProcessSearchQuery(
			BooleanQuery searchQuery, BooleanFilter booleanFilter,
			SearchContext searchContext)
		throws Exception {

		addSearchTerm(searchQuery, searchContext, Field.DESCRIPTION, true);
		addSearchTerm(searchQuery, searchContext, "topicName_sortable", true);
	}

	@Override
	protected Document doGetDocument(BaseModel<?> baseModel) throws Exception {
		LoopTopic loopTopic = (LoopTopic)baseModel;

		Document document = getBaseModelDocument(null, loopTopic);

		String description = LoopMarkdownUtil.markdownToHtml(
			loopTopic.getCompanyId(), loopTopic.getDescription(), false, false);

		document.addText(Field.DESCRIPTION, HtmlUtil.extractText(description));

		document.addText(Field.NAME, loopTopic.getName());
		document.addKeyword("topicName_sortable", loopTopic.getName(), true);

		document.addNumber(
			"followersCount",
			LoopStreamEntryUtil.getFollowersCount(
				PortalUtil.getClassNameId(LoopTopic.class),
				loopTopic.getLoopTopicId()));

		document.addKeyword(
			"parentLoopTopicId", loopTopic.getParentLoopTopicId());
		document.addNumber(
			"score",
			GetterUtil.getLong(
				LoopStatsEntryUtil.getScore(
					PortalUtil.getClassNameId(LoopTopic.class),
					loopTopic.getLoopTopicId()) * 100));

		return document;
	}

	private LoopTopicIndexer() {
		setClassName(LoopTopic.class.getName());
	}

	private static final LoopTopicIndexer _instance = new LoopTopicIndexer();

}