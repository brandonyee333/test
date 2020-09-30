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

package com.liferay.osb.asah.upgrade.v0_0_0;

import com.liferay.osb.asah.common.constants.ServiceConstants;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchConnection;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import java.util.Collections;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;

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
import org.springframework.stereotype.Component;

/**
 * @author Shinn Lok
 */
@Component
public class SnapshotsUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) throws Exception {
		if (StringUtils.isEmpty(System.getenv("LCP_PROJECT_CLUSTER"))) {
			return;
		}

		_createRepository();
		_createSnapshotLifecyclePolicy();
	}

	private void _createRepository() throws Exception {
		PutRepositoryRequest putRepositoryRequest = new PutRepositoryRequest(
			ServiceConstants.LCP_PROJECT_ID);

		Settings.Builder builder = Settings.builder();

		putRepositoryRequest.settings(
			builder.put(
				"base_path", "workspaces/" + ServiceConstants.LCP_PROJECT_ID
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

	private void _createSnapshotLifecyclePolicy() throws Exception {
		PutSnapshotLifecyclePolicyRequest putSnapshotLifecyclePolicyRequest =
			new PutSnapshotLifecyclePolicyRequest(
				new SnapshotLifecyclePolicy(
					ServiceConstants.LCP_PROJECT_ID + "-hourly-snapshots",
					"<" + ServiceConstants.LCP_PROJECT_ID +
						"-snapshot-{now{YYYY.MM.dd.HH|UTC}}>",
					"0 0 * * * ?", ServiceConstants.LCP_PROJECT_ID,
					Collections.singletonMap(
						"indices",
						Collections.singletonList(
							ServiceConstants.LCP_PROJECT_ID + "_*")),
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

	private IndexLifecycleClient _indexLifecycleClient;
	private SnapshotClient _snapshotClient;

}