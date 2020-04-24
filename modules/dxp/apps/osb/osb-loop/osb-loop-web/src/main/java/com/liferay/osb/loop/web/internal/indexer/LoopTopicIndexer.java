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

	private static LoopTopicIndexer _instance = new LoopTopicIndexer();

}