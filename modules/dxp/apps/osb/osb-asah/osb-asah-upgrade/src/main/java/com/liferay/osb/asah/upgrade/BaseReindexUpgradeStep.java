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

package com.liferay.osb.asah.upgrade;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.Objects;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Shinn Lok
 */
public abstract class BaseReindexUpgradeStep implements UpgradeStep {

	public abstract String[] getCollectionNames();

	public abstract WeDeployDataService getWeDeployDataService();

	@Override
	public void upgrade(String version) throws Exception {
		for (String collectionName : getCollectionNames()) {
			String indexAlias = _reindexHelper.getIndexAlias(
				collectionName, getWeDeployDataService());

			if (_log.isInfoEnabled()) {
				_log.info("Index alias: " + indexAlias);
			}

			String baseIndexName = _reindexHelper.getBaseIndexName(
				collectionName, getWeDeployDataService());

			String newIndexName = _reindexHelper.getNewIndexName(
				baseIndexName, version);

			if (_elasticsearchIndexManager.aliasExists(indexAlias) &&
				_elasticsearchIndexManager.exists(newIndexName) &&
				Objects.equals(
					_elasticsearchIndexManager.getIndexName(indexAlias),
					newIndexName)) {

				if (_log.isInfoEnabled()) {
					_log.info(
						"Skipped reindexing as " + newIndexName +
							" exists and has the expected alias");
				}

				continue;
			}

			newIndexName = _reindexHelper.createIndex(
				indexAlias,
				_elasticsearchIndexManager.readIndexConfiguration(
					collectionName, getWeDeployDataService()),
				baseIndexName, version);

			if (_log.isInfoEnabled()) {
				_log.info("New index created: " + newIndexName);
			}

			String oldIndexName = _elasticsearchIndexManager.getIndexName(
				indexAlias);

			if (_elasticsearchIndexManager.exists(oldIndexName)) {
				if (_log.isInfoEnabled()) {
					_log.info("Old index name: " + oldIndexName);
				}

				doUpgrade(newIndexName, oldIndexName);

				_reindexHelper.refreshIndex(newIndexName);

				_reindexHelper.reassignAlias(
					indexAlias, newIndexName, oldIndexName);

				_reindexHelper.deleteIndex(oldIndexName);
			}
			else {
				_reindexHelper.reassignAlias(indexAlias, newIndexName, null);
			}

			if (_log.isInfoEnabled()) {
				_log.info("Finished reindexing " + collectionName);
			}
		}
	}

	protected void doUpgrade(String newIndexName, String oldIndexName)
		throws Exception {

		_reindexHelper.reindex(newIndexName, oldIndexName);
	}

	private static final Log _log = LogFactory.getLog(
		BaseReindexUpgradeStep.class);

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

	@Autowired
	private ReindexHelper _reindexHelper;

}