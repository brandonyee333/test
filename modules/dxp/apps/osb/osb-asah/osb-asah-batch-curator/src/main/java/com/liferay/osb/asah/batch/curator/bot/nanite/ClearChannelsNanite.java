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

package com.liferay.osb.asah.batch.curator.bot.nanite;

import com.liferay.osb.asah.common.faro.info.dog.FaroInfoChannelDog;
import com.liferay.osb.asah.common.json.JSONUtil;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

/**
 * @author André Miranda
 */
@Component
public class ClearChannelsNanite extends BaseNanite {

	@Override
	public void run(JSONObject contextJSONObject) throws Exception {
		_faroInfoChannelDog.clearChannels(
			JSONUtil.toStringList(contextJSONObject.getJSONArray("channelIds")),
			this::monitorProcessedCount, this::monitorQueueSize);

		_clearCache();
	}

	private void _clearCache() {
		if (_cacheManager != null) {
			for (String cacheName : _cacheManager.getCacheNames()) {
				Cache cache = _cacheManager.getCache(cacheName);

				cache.clear();
			}
		}
	}

	@Autowired(required = false)
	private CacheManager _cacheManager;

	@Autowired
	private FaroInfoChannelDog _faroInfoChannelDog;

}