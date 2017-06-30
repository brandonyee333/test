/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.marketplaceregistration.util;

import com.liferay.osb.model.AssetAttachment;
import com.liferay.osb.model.CorpEntry;
import com.liferay.osb.model.DeveloperEntry;
import com.liferay.osb.service.CorpEntryLocalServiceUtil;
import com.liferay.osb.service.DeveloperEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchContextFactory;
import com.liferay.portal.kernel.search.Sort;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Douglas Wong
 * @author Ryan Park
 * @author Joan Kim
 */
public class MarketplaceRegistrationUtil {

	public static List<AssetAttachment> getDocumentAssetAttachments(
			long corpEntryId)
		throws PortalException, SystemException {

		CorpEntry corpEntry = CorpEntryLocalServiceUtil.fetchCorpEntry(
			corpEntryId);

		if (corpEntry != null) {
			return Collections.emptyList();
		}

		DeveloperEntry developerEntry =
			DeveloperEntryLocalServiceUtil.fetchDeveloperEntry(
				corpEntry.getDossieraAccountKey());

		if (developerEntry != null) {
			return developerEntry.getDocumentAssetAttachments();
		}

		return Collections.emptyList();
	}

	public static Hits search(
			HttpServletRequest request, long countryId, int status,
			String keywords, Sort[] sorts, int start, int end)
		throws Exception {

		SearchContext searchContext = SearchContextFactory.getInstance(request);

		if (countryId > 0) {
			searchContext.setAttribute("countryId", String.valueOf(countryId));
		}

		searchContext.setAttribute(Field.NAME, String.valueOf(keywords));
		searchContext.setAttribute("status", String.valueOf(status));
		searchContext.setEnd(end);
		searchContext.setSorts(sorts);
		searchContext.setStart(start);

		Indexer indexer = IndexerRegistryUtil.getIndexer(CorpEntry.class);

		return indexer.search(searchContext);
	}

}