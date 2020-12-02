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
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
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

	@PostConstruct
	private void _init() {
		Map<String, String> aliases = _elasticsearchIndexManager.getAliases();

		for (WeDeployDataService weDeployDataService :
				WeDeployDataService.values()) {

			Set<Map.Entry<String, String>> entries = aliases.entrySet();

			Stream<Map.Entry<String, String>> stream = entries.stream();

			Map<String, String> curAliases = stream.filter(
				map -> map.getKey(
				).contains(
					"_" + weDeployDataService + "_"
				)
			).collect(
				Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)
			);

			_elasticsearchInvokers.put(
				"cacheable#" + weDeployDataService.toString(),
				new CacheableElasticsearchInvokerImpl(
					curAliases, _cacheManager,
					_elasticsearchConnection.getTransportClient(),
					weDeployDataService.toString()));
			_elasticsearchInvokers.put(
				weDeployDataService.toString(),
				new ElasticsearchInvokerImpl(
					curAliases, _elasticsearchConnection.getTransportClient(),
					weDeployDataService.toString()));
		}
	}

	@Autowired(required = false)
	private CacheManager _cacheManager;

	@Autowired
	private ElasticsearchConnection _elasticsearchConnection;

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

	private final Map<String, ElasticsearchInvoker> _elasticsearchInvokers =
		new HashMap<>();

}