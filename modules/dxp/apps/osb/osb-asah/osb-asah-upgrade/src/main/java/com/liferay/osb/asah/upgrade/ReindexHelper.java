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

import com.liferay.osb.asah.common.elasticsearch.ClientUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchConnection;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.admin.cluster.snapshots.status.SnapshotStatus;
import org.elasticsearch.action.admin.cluster.snapshots.status.SnapshotsStatusRequest;
import org.elasticsearch.action.admin.cluster.snapshots.status.SnapshotsStatusResponse;
import org.elasticsearch.action.admin.indices.alias.IndicesAliasesRequestBuilder;
import org.elasticsearch.action.admin.indices.refresh.RefreshRequestBuilder;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.AdminClient;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.ClusterAdminClient;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.ReindexAction;
import org.elasticsearch.index.reindex.ReindexRequestBuilder;
import org.elasticsearch.script.Script;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Michael Bowerman
 * @author Rachael Koestartyo
 */
@Component
public class ReindexHelper {

	public String createIndex(
		String indexAlias, String indexConfigurationJSON, String indexName,
		String version) {

		String newIndexName = getNewIndexName(indexName, version);

		if (_elasticsearchIndexManager.aliasExists(indexAlias) &&
			_elasticsearchIndexManager.exists(newIndexName) &&
			!Objects.equals(
				_elasticsearchIndexManager.getIndexName(indexAlias),
				newIndexName)) {

			_elasticsearchIndexManager.delete(newIndexName);
		}

		_elasticsearchIndexManager.create(indexConfigurationJSON, newIndexName);

		if (_log.isInfoEnabled()) {
			_log.info(
				"Created index " + newIndexName + " based on specified index " +
					"configuration JSON");
		}

		return newIndexName;
	}

	public void deleteIndex(String indexName) {
		for (int i = 10; i >= 0; i--) {
			if (_isSnapshotInProgress()) {
				if ((i == 0) && _log.isInfoEnabled()) {
					_log.info("Unable to delete index " + indexName);

					return;
				}

				if (_log.isInfoEnabled()) {
					_log.info("Snapshot is in progress. Retrying in 1 minute.");
				}

				try {
					Thread.sleep(60000);
				}
				catch (InterruptedException ie) {
					Thread thread = Thread.currentThread();

					thread.interrupt();
				}
			}
			else {
				_elasticsearchIndexManager.delete(indexName);

				if (_log.isInfoEnabled()) {
					_log.info("Deleted index " + indexName);
				}

				return;
			}
		}
	}

	public String getBaseIndexName(
		String collectionName, WeDeployDataService weDeployDataService) {

		return _elasticsearchIndexManager.getIndexName(
			collectionName, weDeployDataService);
	}

	public String getIndexAlias(
		String collectionName, WeDeployDataService weDeployDataService) {

		return ElasticsearchIndexUtil.getIndexAlias(
			collectionName,
			_elasticsearchIndexManager.getIndexNamespace(weDeployDataService));
	}

	public String getNewIndexName(String indexName, String version) {
		indexName = indexName + "_" + version;

		return indexName.toLowerCase();
	}

	@PostConstruct
	public void init() {
		_client = _elasticsearchConnection.getClient();

		_adminClient = _client.admin();
	}

	public void reassignAlias(
		String indexAlias, String newIndexName, String oldIndexName) {

		IndicesAdminClient indicesAdminClient = _adminClient.indices();

		IndicesAliasesRequestBuilder indicesAliasesRequestBuilder =
			indicesAdminClient.prepareAliases();

		indicesAliasesRequestBuilder.addAlias(newIndexName, indexAlias);

		if (oldIndexName != null) {
			indicesAliasesRequestBuilder.removeAlias(oldIndexName, indexAlias);
		}

		ClientUtil.waitForConnection(_client);

		ActionFuture<AcknowledgedResponse> actionFuture =
			indicesAliasesRequestBuilder.execute();

		actionFuture.actionGet();

		if (_log.isInfoEnabled()) {
			_log.info(
				"Reassigned alias " + indexAlias + " from old index " +
					oldIndexName + " to new index " + newIndexName);
		}
	}

	public void refreshIndex(String indexName) {
		IndicesAdminClient indicesAdminClient = _adminClient.indices();

		RefreshRequestBuilder refreshRequestBuilder =
			indicesAdminClient.prepareRefresh(indexName);

		ClientUtil.waitForConnection(_client);

		refreshRequestBuilder.get();

		if (_log.isInfoEnabled()) {
			_log.info("Refreshed " + indexName);
		}
	}

	public void reindex(
			String destination, Map<String, Object> params,
			QueryBuilder queryBuilder, String script, String source)
		throws Exception {

		ReindexRequestBuilder reindexRequestBuilder = new ReindexRequestBuilder(
			_elasticsearchConnection.getClient(), ReindexAction.INSTANCE);

		reindexRequestBuilder.destination(destination);
		reindexRequestBuilder.source(source);

		if (queryBuilder != null) {
			reindexRequestBuilder.filter(queryBuilder);
		}

		if (script != null) {
			reindexRequestBuilder.script(
				new Script(
					Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
					script, params));
		}

		for (int i = _MAX_RETRIES; i >= 0; i--) {
			ClientUtil.waitForConnection(_client);

			BulkByScrollResponse bulkByScrollResponse =
				reindexRequestBuilder.get();

			List<BulkItemResponse.Failure> bulkFailures =
				bulkByScrollResponse.getBulkFailures();

			if (bulkFailures.isEmpty()) {
				if (_log.isInfoEnabled()) {
					_log.info(
						"Reindex completed. Response: " +
							bulkByScrollResponse.toString());
				}

				return;
			}

			if (_log.isInfoEnabled()) {
				_log.info(
					"Reindex completed with failures. Response: " +
						bulkByScrollResponse.toString());
			}

			if (i == 0) {
				throw new Exception("Unable to reindex");
			}

			_log.info("Retrying...");

			try {
				Thread.sleep(5000);
			}
			catch (InterruptedException ie) {
				Thread thread = Thread.currentThread();

				thread.interrupt();
			}
		}
	}

	public void reindex(String destination, String source) throws Exception {
		reindex(destination, null, null, null, source);
	}

	private boolean _isSnapshotInProgress() {
		ClusterAdminClient clusterAdminClient = _adminClient.cluster();

		SnapshotsStatusRequest snapshotsStatusRequest =
			new SnapshotsStatusRequest() {
				{
					ignoreUnavailable(true);
					masterNodeTimeout(TimeValue.timeValueMinutes(1));
				}
			};

		ActionFuture<SnapshotsStatusResponse> actionFuture =
			clusterAdminClient.snapshotsStatus(snapshotsStatusRequest);

		SnapshotsStatusResponse snapshotsStatusResponse =
			actionFuture.actionGet();

		List<SnapshotStatus> snapshotStatuses =
			snapshotsStatusResponse.getSnapshots();

		if (snapshotStatuses.isEmpty()) {
			return false;
		}

		return true;
	}

	private static final int _MAX_RETRIES = 3;

	private static final Log _log = LogFactory.getLog(ReindexHelper.class);

	private AdminClient _adminClient;
	private Client _client;

	@Autowired
	private ElasticsearchConnection _elasticsearchConnection;

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

}