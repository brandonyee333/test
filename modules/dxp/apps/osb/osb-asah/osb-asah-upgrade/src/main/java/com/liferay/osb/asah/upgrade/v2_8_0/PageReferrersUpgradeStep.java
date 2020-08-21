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

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsonorg.JsonOrgModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchBulkRequestBuilder;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.PageAcquisition;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import javax.annotation.PostConstruct;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Matthew Kong
 */
@Component
public class PageReferrersUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) throws Exception {
		_elasticsearchIndexManager.updateMapping(
			"activities",
			JSONUtil.put(
				"properties",
				JSONUtil.put(
					"acquisition",
					JSONUtil.put(
						"properties",
						JSONUtil.put(
							"campaign", JSONUtil.put("type", "keyword")
						).put(
							"channel", JSONUtil.put("type", "keyword")
						).put(
							"content", JSONUtil.put("type", "keyword")
						).put(
							"medium", JSONUtil.put("type", "keyword")
						).put(
							"term", JSONUtil.put("type", "keyword")
						)))
			).toString(),
			"activities", WeDeployDataService.OSB_ASAH_FARO_INFO);

		ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder =
			_elasticsearchInvoker.createElasticsearchBulkRequestBuilder();

		JSONArrayIterator.of(
			"page-referrers", _elasticsearchInvoker,
			pageReferrerJSONObject -> elasticsearchBulkRequestBuilder.update(
				"page-referrers",
				pageReferrerJSONObject.put(
					"acquisition",
					_objectMapper.convertValue(
						new PageAcquisition(
							pageReferrerJSONObject.getString("referrer"),
							pageReferrerJSONObject.getString("url")),
						JSONObject.class)))
		).setStopOnExceptions(
			false
		).iterate();
	}

	@PostConstruct
	private void _init() {
		_elasticsearchInvoker = _elasticsearchInvokerFactory.forCerebroInfo();
	}

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	private final ObjectMapper _objectMapper = new ObjectMapper() {
		{
			disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

			registerModule(new JavaTimeModule());
			registerModule(new Jdk8Module());
			registerModule(new JsonOrgModule());
		}
	};

}