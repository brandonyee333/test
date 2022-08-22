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

package com.liferay.osb.asah.upgrade.elasticsearch.impl;

import com.liferay.osb.asah.upgrade.elasticsearch.ElasticsearchConnection;
import com.liferay.osb.asah.upgrade.elasticsearch.ElasticsearchSnapshotManager;

import java.util.Collections;
import java.util.stream.IntStream;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.admin.cluster.repositories.put.PutRepositoryRequest;
import org.elasticsearch.client.IndexLifecycleClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.SnapshotClient;
import org.elasticsearch.client.core.AcknowledgedResponse;
import org.elasticsearch.client.slm.DeleteSnapshotLifecyclePolicyRequest;
import org.elasticsearch.client.slm.PutSnapshotLifecyclePolicyRequest;
import org.elasticsearch.client.slm.SnapshotLifecyclePolicy;
import org.elasticsearch.client.slm.SnapshotRetentionConfiguration;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.TimeValue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author Shinn Lok
 * @author André Miranda
 */
@Component
public class ElasticsearchSnapshotManagerImpl
	implements ElasticsearchSnapshotManager {

	@Override
	public void createSnapshotLifecyclePolicy(String projectId)
		throws Exception {

		if (!_environment.acceptsProfiles("prod")) {
			return;
		}

		_createRepository(projectId);
		_createSnapshotLifecyclePolicy(projectId);
	}

	@Override
	public void createSnapshotRepository(String projectId) throws Exception {
		if (!_environment.acceptsProfiles("prod")) {
			return;
		}

		_createRepository(projectId);
	}

	@Override
	public void deleteSnapshotLifecyclePolicy(String projectId) {
		if (!_environment.acceptsProfiles("prod")) {
			return;
		}

		_deleteSnapshotLifecyclePolicy(projectId);
	}

	private void _createRepository(String projectId) throws Exception {
		PutRepositoryRequest putRepositoryRequest = new PutRepositoryRequest(
			projectId);

		Settings.Builder builder = Settings.builder();

		putRepositoryRequest.settings(
			builder.put(
				"base_path", "workspaces/" + projectId
			).put(
				"bucket", System.getenv("ELASTICSEARCH_SNAPSHOT_BUCKET")
			).put(
				"compress", "true"
			).put(
				"readonly", "false"
			));

		putRepositoryRequest.type("gcs");

		_snapshotClient.createRepository(
			putRepositoryRequest, RequestOptions.DEFAULT);
	}

	private void _createSnapshotLifecyclePolicy(String projectId)
		throws Exception {

		IntStream intStream = projectId.chars();

		int minute = intStream.filter(
			Character::isDigit
		).findFirst(
		).orElse(
			Character.forDigit(0, 10)
		);

		String schedule = "0 " + (char)minute + " * * * ?";

		PutSnapshotLifecyclePolicyRequest putSnapshotLifecyclePolicyRequest =
			new PutSnapshotLifecyclePolicyRequest(
				new SnapshotLifecyclePolicy(
					projectId + "-hourly-snapshots",
					"<" + projectId + "-snapshot-{now{YYYY.MM.dd.HH|UTC}}>",
					schedule, projectId,
					Collections.singletonMap(
						"indices", Collections.singletonList(projectId + "_*")),
					new SnapshotRetentionConfiguration(
						TimeValue.timeValueDays(30), 1, 720)));

		_indexLifecycleClient.putSnapshotLifecyclePolicy(
			putSnapshotLifecyclePolicyRequest, RequestOptions.DEFAULT);
	}

	private void _deleteSnapshotLifecyclePolicy(String projectId) {
		_indexLifecycleClient.deleteSnapshotLifecyclePolicyAsync(
			new DeleteSnapshotLifecyclePolicyRequest(
				projectId + "-hourly-snapshots"),
			RequestOptions.DEFAULT,
			new ActionListener<AcknowledgedResponse>() {

				@Override
				public void onFailure(Exception exception) {
					_log.error(
						"Unable to delete snapshot lifecycle policy for " +
							"project " + projectId,
						exception);
				}

				@Override
				public void onResponse(
					AcknowledgedResponse acknowledgedResponse) {

					if (_log.isInfoEnabled()) {
						_log.info(
							"Successfully deleted snapshot lifecycle policy " +
								"for project " + projectId);
					}
				}

			});
	}

	@PostConstruct
	private void _init() {
		RestHighLevelClient restHighLevelClient =
			_elasticsearchConnection.getRestHighLevelClient();

		_indexLifecycleClient = restHighLevelClient.indexLifecycle();
		_snapshotClient = restHighLevelClient.snapshot();
	}

	private static final Log _log = LogFactory.getLog(
		ElasticsearchSnapshotManagerImpl.class);

	@Autowired
	private ElasticsearchConnection _elasticsearchConnection;

	@Autowired
	private Environment _environment;

	private IndexLifecycleClient _indexLifecycleClient;
	private SnapshotClient _snapshotClient;

}