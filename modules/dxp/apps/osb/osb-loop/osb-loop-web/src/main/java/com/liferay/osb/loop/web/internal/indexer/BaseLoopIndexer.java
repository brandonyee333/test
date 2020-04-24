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

import com.liferay.alloy.mvc.BaseAlloyIndexer;
import com.liferay.osb.loop.constants.LoopIndexerConstants;
import com.liferay.osb.loop.model.LoopAuditEntry;
import com.liferay.osb.loop.model.LoopDivision;
import com.liferay.osb.loop.model.LoopPerson;
import com.liferay.osb.loop.model.LoopTopic;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

/**
 * @author Timothy Bell
 */
public abstract class BaseLoopIndexer extends BaseAlloyIndexer {

	@Override
	public void postProcessContextBooleanFilter(
			BooleanFilter contextBooleanFilter, SearchContext searchContext)
		throws Exception {

		super.postProcessContextBooleanFilter(
			contextBooleanFilter, searchContext);

		long entryClassPK = GetterUtil.getLong(
			searchContext.getAttribute(Field.ENTRY_CLASS_PK));

		if (entryClassPK > 0) {
			contextBooleanFilter.addRequiredTerm(
				Field.ENTRY_CLASS_PK, entryClassPK);
		}
	}

	@Override
	public Hits search(SearchContext searchContext) throws SearchException {
		Hits hits = super.search(searchContext);

		if (searchContext.getStart() >= hits.getLength()) {
			hits.setDocs(new Document[0]);
			hits.setScores(new float[0]);
		}

		return hits;
	}

	@Override
	protected void doDelete(BaseModel<?> baseModel) throws Exception {
		super.doDelete(baseModel);

		if (baseModel instanceof LoopAuditEntry ||
			baseModel instanceof LoopDivision ||
			baseModel instanceof LoopPerson || baseModel instanceof LoopTopic) {

			Indexer shareToIndexer = IndexerRegistryUtil.nullSafeGetIndexer(
				LoopIndexerConstants.SHARE_TO_INDEXER);

			shareToIndexer.delete(baseModel);
		}
	}

	@Override
	protected Summary doGetSummary(
		Document document, Locale locale, String snippet,
		PortletRequest portletRequest, PortletResponse portletResponse) {

		return createSummary(document);
	}

	@Override
	protected void doReindex(BaseModel<?> baseModel) throws Exception {
		super.doReindex(baseModel);

		if (baseModel instanceof LoopAuditEntry ||
			baseModel instanceof LoopDivision ||
			baseModel instanceof LoopPerson || baseModel instanceof LoopTopic) {

			Indexer shareToIndexer = IndexerRegistryUtil.nullSafeGetIndexer(
				LoopIndexerConstants.SHARE_TO_INDEXER);

			shareToIndexer.reindex(baseModel);
		}
	}

}