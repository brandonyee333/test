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

package com.liferay.osb.asah.extractor.cache;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageBus;
import com.liferay.osb.asah.common.messaging.MessageListener;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.lang3.StringUtils;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author André Miranda
 */
@Component
public class DataSourceCache implements MessageListener {

	public String getChannelId(String dataSourceId) {
		return _channelIds.get(dataSourceId);
	}

	public JSONObject getDataSourceJSONObject(String dataSourceId) {
		return _dataSources.get(dataSourceId);
	}

	public boolean isValidDataSourceId(String dataSourceId) {
		return _dataSources.containsKey(dataSourceId);
	}

	@Override
	public void onMessage(String message) {
		_cacheDataSources();
	}

	private void _cacheDataSources() {
		_channelIds = new HashMap<>();
		_dataSources = new HashMap<>();

		JSONArray jsonArray = _faroInfoElasticsearchInvoker.get(
			"data-sources", QueryBuilders.matchAllQuery());

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			_dataSources.put(jsonObject.getString("id"), jsonObject);

			String channelId = jsonObject.optString("channelId");

			if (StringUtils.isNotBlank(channelId)) {
				_channelIds.put(jsonObject.getString("id"), channelId);
			}
		}
	}

	@PreDestroy
	private void _destroy() {
		_messageBus.unregisterMessageListener(this);
	}

	@PostConstruct
	private void _init() {
		_faroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forFaroInfo();

		_cacheDataSources();

		_messageBus.registerMessageListener(Channel.DATA_SOURCES, this);
	}

	private Map<String, String> _channelIds;
	private Map<String, JSONObject> _dataSources;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private MessageBus _messageBus;

}