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

package com.liferay.osb.testray.dashboard.gateway.internal.util;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ethan Bustad
 */
public class ExternalAPIRequestProcessor {

	public ExternalAPIRequestProcessor(
		String userName, String userToken, long apiCacheTimeoutSeconds) {

		_userName = userName;
		_userToken = userToken;
		_apiCacheTimeoutSeconds = apiCacheTimeoutSeconds;
	}

	public String process(String apiName, String server) throws Exception {
		final String url = APITranslator.getAPIURL(server, apiName);

		if (isCacheExpired(url)) {
			synchronized (_urlCacheMap) {
				if (isCacheExpired(url)) {
					if (_log.isDebugEnabled()) {
						_log.debug("Getting fresh response for url " + url);
					}

					Runnable runnable = new Runnable() {

						public void run() {
							String results = HttpUtil.doGet(
								url, _userName, _userToken);

							_urlResultsCacheMap.put(url, results);
						}

					};

					Thread thread = new Thread(runnable);

					thread.start();

					_urlCacheMap.put(url, System.currentTimeMillis());
				}
				else if (_log.isDebugEnabled()) {
					_log.debug(
						"Lost race, using cached response for url " + url);
				}
			}
		}
		else if (_log.isDebugEnabled()) {
			_log.debug("Using cached response for url " + url);
		}

		if (!_urlResultsCacheMap.containsKey(url)) {
			return JSONFactoryUtil.getNullJSON();
		}

		return _urlResultsCacheMap.get(url);
	}

	protected boolean isCacheExpired(String url) {
		if (!_urlCacheMap.containsKey(url)) {
			return true;
		}

		long cacheTime = _urlCacheMap.get(url);

		long elapsedSeconds = (System.currentTimeMillis() - cacheTime) / 1000;

		if (elapsedSeconds >= _apiCacheTimeoutSeconds) {
			return true;
		}

		return false;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ExternalAPIRequestProcessor.class);

	private static final Map<String, Long> _urlCacheMap = new HashMap<>();
	private static final Map<String, String> _urlResultsCacheMap =
		Collections.synchronizedMap(new HashMap<String, String>());

	private final long _apiCacheTimeoutSeconds;
	private final String _userName;
	private final String _userToken;

}