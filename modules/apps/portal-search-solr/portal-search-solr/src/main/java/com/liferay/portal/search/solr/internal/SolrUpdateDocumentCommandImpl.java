/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.solr.internal;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.search.solr.connection.SolrClientManager;
import com.liferay.portal.search.solr.document.SolrDocumentFactory;
import com.liferay.portal.search.solr.document.SolrUpdateDocumentCommand;
import com.liferay.portal.search.solr.internal.util.LogUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(immediate = true, service = SolrUpdateDocumentCommand.class)
public class SolrUpdateDocumentCommandImpl
	implements SolrUpdateDocumentCommand {

	@Override
	public String updateDocument(
			SearchContext searchContext, Document document, boolean deleteFirst)
		throws SearchException {

		doUpdateDocuments(searchContext, Arrays.asList(document), deleteFirst);

		// TODO

		return StringPool.BLANK;
	}

	@Override
	public void updateDocuments(
			SearchContext searchContext, Collection<Document> documents,
			boolean deleteFirst)
		throws SearchException {

		if (documents.isEmpty()) {
			return;
		}

		doUpdateDocuments(searchContext, documents, deleteFirst);
	}

	protected UpdateResponse doUpdateDocuments(
			SearchContext searchContext, Collection<Document> documents,
			boolean deleteFirst)
		throws SearchException {

		SolrClient solrClient = _solrClientManager.getSolrClient();

		try {
			List<SolrInputDocument> solrInputDocuments = new ArrayList<>(
				documents.size());

			List<String> uids = new ArrayList<>(documents.size());

			for (Document document : documents) {
				SolrInputDocument solrInputDocument =
					_solrDocumentFactory.getSolrInputDocument(document);

				solrInputDocuments.add(solrInputDocument);

				uids.add(document.getUID());
			}

			if (deleteFirst) {
				UpdateResponse updateResponse = solrClient.deleteById(uids);

				LogUtil.logSolrResponseBase(_log, updateResponse);
			}

			UpdateResponse updateResponse = solrClient.add(solrInputDocuments);

			if (searchContext.isCommitImmediately()) {
				solrClient.commit();
			}

			LogUtil.logSolrResponseBase(_log, updateResponse);

			return updateResponse;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new SearchException(e);
		}
	}

	@Reference(unbind = "-")
	protected void setSolrClientManager(SolrClientManager solrClientManager) {
		_solrClientManager = solrClientManager;
	}

	@Reference(unbind = "-")
	protected void setSolrDocumentFactory(
		SolrDocumentFactory solrDocumentFactory) {

		_solrDocumentFactory = solrDocumentFactory;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SolrUpdateDocumentCommandImpl.class);

	private SolrClientManager _solrClientManager;
	private SolrDocumentFactory _solrDocumentFactory;

}