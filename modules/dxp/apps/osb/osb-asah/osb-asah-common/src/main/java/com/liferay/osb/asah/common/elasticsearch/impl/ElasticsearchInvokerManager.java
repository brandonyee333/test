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

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchAliases;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchConnection;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Shinn Lok
 */
@Component
public class ElasticsearchInvokerManager {

	public ElasticsearchInvoker forWeDeployDataService(
		WeDeployDataService weDeployDataService) {

		return forWeDeployDataService(weDeployDataService, false);
	}

	public ElasticsearchInvoker forWeDeployDataService(
		WeDeployDataService weDeployDataService, boolean cacheable) {

		String weDeployServiceName = String.valueOf(weDeployDataService);

		if (cacheable) {
			weDeployServiceName = "cacheable#" + weDeployServiceName;
		}

		return _elasticsearchInvokers.get(weDeployServiceName);
	}

	public ElasticsearchInvoker getGlobalElasticsearchInvoker() {
		return _globalElasticsearchInvoker;
	}

	@PostConstruct
	private void _init() {
		for (WeDeployDataService weDeployDataService :
				WeDeployDataService.values()) {

			ElasticsearchAliases elasticsearchAliases =
				new ElasticsearchAliases(
					_elasticsearchIndexManager, weDeployDataService);

			_elasticsearchInvokers.put(
				"cacheable#" + weDeployDataService.toString(),
				new CacheableElasticsearchInvokerImpl(
					_elasticsearchConnection.getTransportClient(),
					elasticsearchAliases, weDeployDataService));
			_elasticsearchInvokers.put(
				weDeployDataService.toString(),
				new ElasticsearchInvokerImpl(
					_elasticsearchConnection.getTransportClient(),
					elasticsearchAliases, weDeployDataService));
		}

		_globalElasticsearchInvoker = new GlobalElasticsearchInvokerImpl(
			_elasticsearchConnection.getTransportClient());
	}

	@Autowired
	private ElasticsearchConnection _elasticsearchConnection;

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

	private final Map<String, ElasticsearchInvoker> _elasticsearchInvokers =
		new HashMap<>();
	private ElasticsearchInvoker _globalElasticsearchInvoker;

}