/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.solr.internal.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.util.StringBundler;

import org.apache.solr.client.solrj.response.SolrResponseBase;

/**
 * @author Michael C. Han
 */
public class LogUtil {

	public static void logSolrResponseBase(
		Log log, SolrResponseBase solrResponseBase) {

		if (log.isInfoEnabled()) {
			return;
		}

		StringBundler sb = new StringBundler(7);

		sb.append("{elapsedTime=");
		sb.append(solrResponseBase.getElapsedTime());
		sb.append(", requestURL=");
		sb.append(solrResponseBase.getRequestUrl());
		sb.append(", response=");
		sb.append(solrResponseBase);
		sb.append("}");

		log.info(sb);
	}

}