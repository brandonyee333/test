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
import com.liferay.osb.asah.common.spring.annotation.CacheEvict;
import com.liferay.osb.asah.common.spring.annotation.CachePut;
import com.liferay.osb.asah.common.spring.annotation.Cacheable;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import org.elasticsearch.client.Client;

import org.json.JSONObject;

/**
 * @author Shinn Lok
 */
public class CacheableElasticsearchInvokerImpl
	extends ElasticsearchInvokerImpl {

	public CacheableElasticsearchInvokerImpl(
		Client client, ElasticsearchAliases elasticsearchAliases,
		WeDeployDataService weDeployDataService) {

		super(client, elasticsearchAliases, weDeployDataService);
	}

	@CachePut
	@Override
	public JSONObject add(String collectionName, JSONObject jsonObject) {
		return super.add(collectionName, jsonObject);
	}

	@CacheEvict
	@Override
	public boolean delete(String collectionName, String id) {
		return super.delete(collectionName, id);
	}

	@Cacheable
	@Override
	public JSONObject fetch(String collectionName, String id) {
		return super.fetch(collectionName, id);
	}

	@CachePut
	@Override
	public JSONObject update(
		String collectionName, String id, JSONObject jsonObject) {

		return super.update(collectionName, id, jsonObject);
	}

}