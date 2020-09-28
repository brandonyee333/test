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

package com.liferay.osb.asah.upgrade.v2_8_0;

import com.liferay.osb.asah.common.elasticsearch.ClientUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchConnection;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.upgrade.ReindexHelper;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import java.io.InputStream;

import java.nio.charset.Charset;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequestBuilder;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.admin.indices.settings.put.UpdateSettingsRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.AdminClient;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.common.settings.Settings;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Shinn Lok
 */
@Component
public class IndexSettingsUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) {
		Client client = _elasticsearchConnection.getTransportClient();

		AdminClient adminClient = client.admin();

		IndicesAdminClient indicesAdminClient = adminClient.indices();

		Settings.Builder settingsBuilder = Settings.builder();

		settingsBuilder.put("index.max_terms_count", Integer.MAX_VALUE);

		ClientUtil.waitForConnection(client);

		ActionFuture<AcknowledgedResponse> actionFuture =
			indicesAdminClient.updateSettings(
				new UpdateSettingsRequest(
					settingsBuilder.build(), _getIndices(indicesAdminClient)));

		actionFuture.actionGet();
	}

	private String[] _getIndices(IndicesAdminClient indicesAdminClient) {
		List<String> indices = new ArrayList<>();

		JSONObject indicesJSONObject = new JSONObject(_readIndices());

		for (WeDeployDataService weDeployDataService :
				WeDeployDataService.values()) {

			JSONArray jsonArray = indicesJSONObject.optJSONArray(
				weDeployDataService.toString());

			if (jsonArray == null) {
				continue;
			}

			for (int i = 0; i < jsonArray.length(); i++) {
				String indexAlias = _reindexHelper.getIndexAlias(
					jsonArray.getString(i), weDeployDataService);

				IndicesExistsRequestBuilder indicesExistsRequestBuilder =
					indicesAdminClient.prepareExists(indexAlias);

				IndicesExistsResponse indicesExistsResponse =
					indicesExistsRequestBuilder.get();

				if (indicesExistsResponse.isExists()) {
					indices.add(indexAlias);
				}
			}
		}

		return indices.toArray(new String[0]);
	}

	private String _readIndices() {
		try {
			Class<?> clazz = getClass();

			InputStream inputStream = clazz.getResourceAsStream(
				"/elasticsearch_indices.json");

			return IOUtils.toString(inputStream, Charset.defaultCharset());
		}
		catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	@Autowired
	private ElasticsearchConnection _elasticsearchConnection;

	@Autowired
	private ReindexHelper _reindexHelper;

}