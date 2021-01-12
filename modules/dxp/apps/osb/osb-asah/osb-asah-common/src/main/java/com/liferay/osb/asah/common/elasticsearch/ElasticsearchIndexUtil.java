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

package com.liferay.osb.asah.common.elasticsearch;

import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

/**
 * @author André Miranda
 */
public class ElasticsearchIndexUtil {

	public static String getIndexAlias(
		String collectionName, WeDeployDataService weDeployDataService) {

		String indexName =
			getIndexNamespace(weDeployDataService) + "_" + collectionName +
				"_alias";

		return indexName.toLowerCase();
	}

	public static String getIndexName(
		String collectionName, String indexNamespace) {

		String indexName = indexNamespace + "_" + collectionName;

		return indexName.toLowerCase();
	}

	public static String getIndexName(
		String collectionName, WeDeployDataService weDeployDataService) {

		String indexName =
			getIndexNamespace(weDeployDataService) + "_" + collectionName;

		return indexName.toLowerCase();
	}

	public static String getIndexNamespace(
		WeDeployDataService weDeployDataService) {

		String indexNamespace =
			ProjectIdThreadLocal.getProjectId() + "_" + weDeployDataService;

		return indexNamespace.toLowerCase();
	}

}