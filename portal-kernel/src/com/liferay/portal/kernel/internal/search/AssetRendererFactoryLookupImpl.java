/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.kernel.internal.search;

import com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceReference;
import com.liferay.registry.ServiceTracker;
import com.liferay.registry.ServiceTrackerCustomizer;

import java.time.Duration;
import java.time.Instant;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author Cristina Rodríguez Yrezábal
 * @author Mariano Álvaro Sáiz
 */
public class AssetRendererFactoryLookupImpl
	implements AssetRendererFactoryLookup {

	public AssetRendererFactoryLookupImpl() {
		_activated = Instant.now();
	}

	@Override
	public AssetRendererFactory<?> getAssetRendererFactoryByClassName(
		String className) {

		AssetRendererFactory<?> assetRendererFactory =
			AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(
				className);

		if ((assetRendererFactory != null) ||
			!_isIndexOnStartupWithDelayEnabled() ||
			_isAssetRendererFactoryInitialized(className)) {

			return assetRendererFactory;
		}

		_waitAssetRendererFactoryLoaded(className);

		_initializedAssetRendererFactories.add(className);

		return AssetRendererFactoryRegistryUtil.
			getAssetRendererFactoryByClassName(className);
	}

	private static boolean _isIndexOnStartupWithDelayEnabled() {
		if (_INDEX_ON_STARTUP && (_INDEX_ON_STARTUP_DELAY > 0)) {
			return true;
		}

		return false;
	}

	private boolean _isAssetRendererFactoryInitialized(String className) {
		return _initializedAssetRendererFactories.contains(className);
	}

	private long _secondsElapsedSinceActivated() {
		Instant now = Instant.now();

		Duration elapsedDuration = Duration.between(_activated, now);

		return elapsedDuration.getSeconds();
	}

	private void _waitAssetRendererFactoryLoaded(String className) {
		CountDownLatch countDownLatch =
			_assetRenderFactoriesCountDownLatchMap.computeIfAbsent(
				className, key -> new CountDownLatch(1));

		long secondsToWait = Math.max(
			0, _INDEX_ON_STARTUP_DELAY - _secondsElapsedSinceActivated());

		try {
			countDownLatch.await(secondsToWait, TimeUnit.SECONDS);
		}
		catch (InterruptedException interruptedException) {
			if (_log.isInfoEnabled()) {
				_log.info(
					"Interrupted while waiting to load factory",
					interruptedException);
			}
		}
	}

	private static final boolean _INDEX_ON_STARTUP = GetterUtil.getBoolean(
		PropsUtil.get(PropsKeys.INDEX_ON_STARTUP));

	private static final long _INDEX_ON_STARTUP_DELAY = GetterUtil.getLong(
		PropsUtil.get(PropsKeys.INDEX_ON_STARTUP_DELAY));

	private static final Log _log = LogFactoryUtil.getLog(
		AssetRendererFactoryLookupImpl.class);

	private static final Map<String, CountDownLatch>
		_assetRenderFactoriesCountDownLatchMap = new ConcurrentHashMap<>();
	private static final ServiceTracker
		<AssetRendererFactory<?>, AssetRendererFactory<?>> _serviceTracker;

	private final Instant _activated;
	private final Set<String> _initializedAssetRendererFactories =
		ConcurrentHashMap.newKeySet();

	private static class AssetRendererFactoryServiceTrackerCustomizer
		implements ServiceTrackerCustomizer
			<AssetRendererFactory<?>, AssetRendererFactory<?>> {

		@Override
		public AssetRendererFactory<?> addingService(
			ServiceReference<AssetRendererFactory<?>> serviceReference) {

			Registry registry = RegistryUtil.getRegistry();

			AssetRendererFactory<?> assetRendererFactory = registry.getService(
				serviceReference);

			_assetRenderFactoriesCountDownLatchMap.computeIfPresent(
				assetRendererFactory.getClassName(),
				(key, countDownLatch) -> {
					countDownLatch.countDown();

					return countDownLatch;
				});

			return assetRendererFactory;
		}

		@Override
		public void modifiedService(
			ServiceReference<AssetRendererFactory<?>> serviceReference,
			AssetRendererFactory<?> service) {
		}

		@Override
		public void removedService(
			ServiceReference<AssetRendererFactory<?>> serviceReference,
			AssetRendererFactory<?> service) {

			Registry registry = RegistryUtil.getRegistry();

			registry.ungetService(serviceReference);
		}

	}

	static {
		Registry registry = RegistryUtil.getRegistry();

		_serviceTracker = registry.trackServices(
			(Class<AssetRendererFactory<?>>)
				(Class<?>)AssetRendererFactory.class,
			new AssetRendererFactoryServiceTrackerCustomizer());

		_serviceTracker.open();
	}

}