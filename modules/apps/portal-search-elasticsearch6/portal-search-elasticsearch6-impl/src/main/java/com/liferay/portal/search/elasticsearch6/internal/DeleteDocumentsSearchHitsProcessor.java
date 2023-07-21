/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch6.internal;

import com.liferay.portal.kernel.search.IndexWriter;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;

import java.util.Collection;
import java.util.HashSet;

import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

/**
 * @author Michael C. Han
 */
public class DeleteDocumentsSearchHitsProcessor implements SearchHitsProcessor {

	public DeleteDocumentsSearchHitsProcessor(IndexWriter indexWriter) {
		_indexWriter = indexWriter;
	}

	@Override
	public void processSearchHits(
			SearchContext searchContext, SearchHits searchHits)
		throws SearchException {

		SearchHit[] searchHitsArray = searchHits.getHits();

		Collection<String> uids = new HashSet<>();

		for (SearchHit searchHit : searchHitsArray) {
			uids.add(searchHit.getId());
		}

		_indexWriter.deleteDocuments(searchContext, uids);
	}

	private final IndexWriter _indexWriter;

}