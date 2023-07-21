/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.solr.document;

import com.liferay.portal.kernel.search.Document;

import org.apache.solr.common.SolrInputDocument;

/**
 * @author Michael C. Han
 */
public interface SolrDocumentFactory {

	public SolrInputDocument getSolrInputDocument(Document document);

}