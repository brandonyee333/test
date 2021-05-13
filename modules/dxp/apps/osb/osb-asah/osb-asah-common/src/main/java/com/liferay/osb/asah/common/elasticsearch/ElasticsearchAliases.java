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

import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Shinn Lok
 */
public class ElasticsearchAliases {

	public ElasticsearchAliases(
		ElasticsearchIndexManager elasticsearchIndexManager,
		WeDeployDataService weDeployDataService) {

		_elasticsearchIndexManager = elasticsearchIndexManager;
		_weDeployDataService = weDeployDataService;

		refresh();
	}

	public String check(String collectionName) {
		if (_weDeployDataService == null) {
			return collectionName;
		}

		String indexName = ElasticsearchIndexUtil.getIndexName(
			collectionName, _weDeployDataService);

		String indexAlias = _aliases.get(indexName);

		if (indexAlias != null) {
			return indexAlias;
		}

		refresh();

		indexAlias = _aliases.get(indexName);

		if (indexAlias == null) {
			return indexName;
		}

		return indexAlias;
	}

	public String get(String collectionName) {
		if (_weDeployDataService == null) {
			return collectionName;
		}

		return ElasticsearchIndexUtil.getIndexAlias(
			collectionName, _weDeployDataService);
	}

	public void refresh() {
		_aliases.putAll(
			_elasticsearchIndexManager.getAliases(_weDeployDataService));
	}

	private final Map<String, String> _aliases = new ConcurrentHashMap<>();
	private final ElasticsearchIndexManager _elasticsearchIndexManager;
	private final WeDeployDataService _weDeployDataService;

}