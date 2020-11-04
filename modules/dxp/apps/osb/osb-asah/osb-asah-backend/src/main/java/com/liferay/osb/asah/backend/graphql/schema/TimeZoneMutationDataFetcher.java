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

package com.liferay.osb.asah.backend.graphql.schema;

import com.liferay.osb.asah.backend.graphql.GraphQLTypeWiring;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoPreferenceDog;
import com.liferay.osb.asah.common.http.NanitesHttp;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

/**
 * @author André Miranda
 */
@Component
@GraphQLTypeWiring(fieldName = "timeZone", typeName = "MutationType")
public class TimeZoneMutationDataFetcher implements DataFetcher<String> {

	@Override
	public String get(DataFetchingEnvironment dataFetchingEnvironment) {
		String value = dataFetchingEnvironment.getArgument("value");

		_faroInfoPreferenceDog.addPreference("time-zone-id", value);

		_nanitesHttp.rescheduleNanites();

		if (_cacheManager != null) {
			for (String cacheName : _cacheManager.getCacheNames()) {
				Cache cache = _cacheManager.getCache(cacheName);

				cache.clear();
			}
		}

		return value;
	}

	@Autowired(required = false)
	private CacheManager _cacheManager;

	@Autowired
	private FaroInfoPreferenceDog _faroInfoPreferenceDog;

	@Autowired
	private NanitesHttp _nanitesHttp;

}