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

package com.liferay.osb.asah.backend.configuration;

import java.util.Collection;
import java.util.Collections;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author Geyson Silva
 */
@Configuration
@Profile("!default")
public class CacheConfiguration {

	@Bean
	public CacheManager cacheManager() {
		return new CacheManager() {

			@Override
			public Cache getCache(String s) {
				return null;
			}

			@Override
			public Collection<String> getCacheNames() {
				return Collections.emptyList();
			}

		};
	}

}