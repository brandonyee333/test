/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.solr.internal;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.suggest.SpellCheckIndexWriter;
import com.liferay.portal.kernel.search.suggest.SuggestionConstants;
import com.liferay.portal.kernel.util.PortalRunMode;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.search.solr.connection.SolrClientManager;
import com.liferay.portal.search.solr.document.SolrUpdateDocumentCommand;
import com.liferay.portal.search.solr.internal.util.LogUtil;
import com.liferay.portal.search.suggest.BaseGenericSpellCheckIndexWriter;

import java.util.Collection;
import java.util.Map;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.response.UpdateResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Daniela Zapata Riesco
 * @author David Gonzalez
 * @author Michael C. Han
 */
@Component(
	immediate = true, property = "search.engine.impl=Solr",
	service = SpellCheckIndexWriter.class
)
public class SolrSpellCheckIndexWriter
	extends BaseGenericSpellCheckIndexWriter {

	@Override
	public void clearQuerySuggestionDictionaryIndexes(
			SearchContext searchContext)
		throws SearchException {

		String deleteQuery = buildDeleteQuery(
			searchContext, SuggestionConstants.TYPE_QUERY_SUGGESTION);

		deleteByQuery(searchContext, deleteQuery);
	}

	@Override
	public void clearSpellCheckerDictionaryIndexes(SearchContext searchContext)
		throws SearchException {

		String deleteQuery = buildDeleteQuery(
			searchContext, SuggestionConstants.TYPE_SPELL_CHECKER);

		deleteByQuery(searchContext, deleteQuery);
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		setDocumentPrototype(new DocumentImpl());
	}

	@Override
	protected void addDocument(
			String documentType, SearchContext searchContext, Document document)
		throws SearchException {

		_solrUpdateDocumentCommand.updateDocument(
			searchContext, document, false);
	}

	@Override
	protected void addDocuments(
			String documentType, SearchContext searchContext,
			Collection<Document> documents)
		throws SearchException {

		_solrUpdateDocumentCommand.updateDocuments(
			searchContext, documents, false);
	}

	protected void addQuerySeparator(StringBundler sb) {
		sb.append(StringPool.SPACE);
		sb.append(StringPool.PLUS);
	}

	protected void addQueryType(StringBundler sb, String type) {
		sb.append(Field.TYPE);
		sb.append(StringPool.COLON);
		sb.append(type);
	}

	protected String buildDeleteQuery(
		SearchContext searchContext, String type) {

		StringBundler sb = new StringBundler(14);

		sb.append(StringPool.PLUS);
		sb.append(Field.COMPANY_ID);
		sb.append(StringPool.COLON);
		sb.append(searchContext.getCompanyId());

		addQuerySeparator(sb);

		addQueryType(sb, type);

		return sb.toString();
	}

	protected void deleteByQuery(
			SearchContext searchContext, String deleteQuery)
		throws SearchException {

		SolrClient solrClient = _solrClientManager.getSolrClient();

		try {
			UpdateResponse updateResponse = solrClient.deleteByQuery(
				deleteQuery);

			if (PortalRunMode.isTestMode() ||
				searchContext.isCommitImmediately()) {

				solrClient.commit();
			}

			LogUtil.logSolrResponseBase(_log, updateResponse);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}

			throw new SearchException(e.getMessage(), e);
		}
	}

	@Reference(unbind = "-")
	protected void setSolrClientManager(SolrClientManager solrClientManager) {
		_solrClientManager = solrClientManager;
	}

	@Reference(unbind = "-")
	protected void setSolrUpdateDocumentCommand(
		SolrUpdateDocumentCommand solrUpdateDocumentCommand) {

		_solrUpdateDocumentCommand = solrUpdateDocumentCommand;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SolrSpellCheckIndexWriter.class);

	private SolrClientManager _solrClientManager;
	private SolrUpdateDocumentCommand _solrUpdateDocumentCommand;

}