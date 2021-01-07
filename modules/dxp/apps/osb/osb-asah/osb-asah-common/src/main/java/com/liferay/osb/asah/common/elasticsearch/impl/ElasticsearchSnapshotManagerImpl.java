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

package com.liferay.osb.asah.common.elasticsearch.impl;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchConnection;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchSnapshotManager;

import java.util.Collections;
import java.util.stream.IntStream;

import javax.annotation.PostConstruct;

import org.elasticsearch.action.admin.cluster.repositories.put.PutRepositoryRequest;
import org.elasticsearch.client.IndexLifecycleClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.SnapshotClient;
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

	private void _createRepository(String projectId) throws Exception {
		PutRepositoryRequest putRepositoryRequest = new PutRepositoryRequest(
			projectId);

		Settings.Builder builder = Settings.builder();

		putRepositoryRequest.settings(
			builder.put(
				"base_path", "workspaces/" + projectId
			).put(
				"bucket", "asah"
			).put(
				"client", "asah"
			).put(
				"compress", "true"
			).put(
				"endpoint", "https://s3.us-west-1.amazonaws.com"
			).put(
				"readonly", "false"
			).put(
				"region", "us-west-1"
			));

		putRepositoryRequest.type("s3");

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

	@PostConstruct
	private void _init() {
		RestHighLevelClient restHighLevelClient =
			_elasticsearchConnection.getRestHighLevelClient();

		_indexLifecycleClient = restHighLevelClient.indexLifecycle();
		_snapshotClient = restHighLevelClient.snapshot();
	}

	@Autowired
	private ElasticsearchConnection _elasticsearchConnection;

	@Autowired
	private Environment _environment;

	private IndexLifecycleClient _indexLifecycleClient;
	private SnapshotClient _snapshotClient;

}