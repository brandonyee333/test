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

import java.util.Map;

import org.elasticsearch.client.Client;

import org.json.JSONObject;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

/**
 * @author Shinn Lok
 */
public class CacheableElasticsearchInvokerImpl
	extends ElasticsearchInvokerImpl {

	public CacheableElasticsearchInvokerImpl(
		Map<String, String> aliases, CacheManager cacheManager, Client client,
		String indexNamespace) {

		super(aliases, client, indexNamespace);

		_cacheManager = cacheManager;
	}

	@Override
	public JSONObject add(String collectionName, JSONObject jsonObject) {
		jsonObject = super.add(collectionName, jsonObject);

		Cache cache = _getCache(collectionName);

		if (cache != null) {
			cache.put(jsonObject.get("id"), jsonObject.toString());
		}

		return jsonObject;
	}

	@Override
	public boolean delete(String collectionName, String id) {
		boolean deleted = super.delete(collectionName, id);

		Cache cache = _getCache(collectionName);

		if (deleted && (cache != null)) {
			cache.evict(id);
		}

		return deleted;
	}

	@Override
	public JSONObject fetch(String collectionName, String id) {
		Cache cache = _getCache(collectionName);

		if (cache != null) {
			Cache.ValueWrapper valueWrapper = cache.get(id);

			if (valueWrapper != null) {
				return new JSONObject((String)valueWrapper.get());
			}
		}

		JSONObject jsonObject = super.fetch(collectionName, id);

		if ((cache != null) && (jsonObject != null)) {
			cache.put(id, jsonObject.toString());
		}

		return jsonObject;
	}

	@Override
	public JSONObject update(
		String collectionName, String id, JSONObject jsonObject) {

		jsonObject = super.update(collectionName, id, jsonObject);

		Cache cache = _getCache(collectionName);

		if (cache != null) {
			cache.put(id, jsonObject.toString());
		}

		return jsonObject;
	}

	private Cache _getCache(String collectionName) {
		if (_cacheManager != null) {
			return _cacheManager.getCache(collectionName);
		}

		return null;
	}

	private final CacheManager _cacheManager;

}