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

package com.liferay.osb.asah.stream.curator.bot.nanite.custom;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.entity.CustomAssetDashboard;
import com.liferay.osb.asah.common.lock.KeyReentrantLock;
import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageSubscriber;
import com.liferay.osb.asah.common.model.AnalyticsEvent;
import com.liferay.osb.asah.common.repository.CustomAssetDashboardRepository;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.stream.curator.bot.nanite.Nanite;
import com.liferay.osb.asah.stream.curator.bot.nanite.util.NaniteUtil;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PreDestroy;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class CustomAssetDashboardNanite implements Nanite {

	@Override
	public String getCollectionName() {
		return "custom-asset-dashboards";
	}

	@Override
	public long getInterval() {
		return DateUtil.MINUTE;
	}

	@Override
	public void run() {
		try {
			_run();
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}

		try {
			_semaphore.acquireUninterruptibly(4);
		}
		finally {
			_semaphore.release(4);
		}
	}

	private void _addCustomAssetDashboards(
		String customAssetPrimaryKey, List<AnalyticsEvent> analyticsEvents) {

		Optional<CustomAssetDashboard> customAssetDashboardOptional =
			_customAssetDashboardRepository.findById(customAssetPrimaryKey);

		CustomAssetDashboard customAssetDashboard = _updateCustomAssetDashboard(
			analyticsEvents,
			customAssetDashboardOptional.orElse(new CustomAssetDashboard()),
			customAssetPrimaryKey);

		if (!customAssetDashboardOptional.isPresent()) {
			customAssetDashboard.setIsNew(true);
		}

		_customAssetDashboardRepository.save(customAssetDashboard);
	}

	@PreDestroy
	private void _destroy() {
		_reentrantLock.lock();

		_executorService.shutdown();

		try {
			if (!_executorService.awaitTermination(1, TimeUnit.MINUTES)) {
				_executorService.shutdownNow();
			}
		}
		catch (InterruptedException interruptedException) {
			_log.error(
				"Interrupted while waiting for termination of executor",
				interruptedException);
		}
	}

	private String _getCustomAssetPrimaryKey(AnalyticsEvent analyticsEvent) {
		Map<String, String> eventProperties =
			analyticsEvent.getEventProperties();

		return NaniteUtil.digest(
			eventProperties.get("assetId"),
			eventProperties.getOrDefault("category", "default"),
			analyticsEvent.getChannelId());
	}

	private void _run() throws Exception {
		while (true) {
			try {
				_reentrantLock.lock();

				long start = System.currentTimeMillis();

				List<AnalyticsEvent> analyticsEvents =
					_messageSubscriber.pullMessages(
						50, AnalyticsEvent::toAnalyticsEvent);

				if (analyticsEvents.isEmpty()) {
					break;
				}

				Stream<AnalyticsEvent> stream = analyticsEvents.stream();

				stream.collect(
					Collectors.groupingBy(AnalyticsEvent::getProjectId)
				).forEach(
					this::_run
				);

				if (_log.isInfoEnabled()) {
					Class<?> clazz = getClass();

					_log.info(
						String.format(
							"%s dispatched %d events in %d ms",
							clazz.getSimpleName(), analyticsEvents.size(),
							System.currentTimeMillis() - start));
				}
			}
			finally {
				_reentrantLock.unlock();
			}
		}
	}

	private void _run(String projectId, List<AnalyticsEvent> analyticsEvents) {
		_semaphore.acquireUninterruptibly();

		ReentrantLock reentrantLock = KeyReentrantLock.getReentrantLock(
			getClass(), projectId);

		CompletableFuture.runAsync(
			() -> {
				try {
					reentrantLock.lock();

					long start = System.currentTimeMillis();

					ProjectIdThreadLocal.setProjectId(projectId);

					Stream<AnalyticsEvent> stream = analyticsEvents.stream();

					stream.collect(
						Collectors.groupingBy(this::_getCustomAssetPrimaryKey)
					).forEach(
						this::_addCustomAssetDashboards
					);

					if (_log.isInfoEnabled()) {
						Class<?> clazz = getClass();

						_log.info(
							String.format(
								"%s processed %d events in %d ms",
								clazz.getSimpleName(), analyticsEvents.size(),
								System.currentTimeMillis() - start));
					}
				}
				catch (Exception exception) {
					_log.error(exception.getMessage(), exception);
				}
				finally {
					reentrantLock.unlock();

					_semaphore.release();
				}
			},
			_executorService);
	}

	private CustomAssetDashboard _updateCustomAssetDashboard(
		List<AnalyticsEvent> analyticsEvents,
		CustomAssetDashboard customAssetDashboard, String id) {

		Stream<AnalyticsEvent> stream = analyticsEvents.stream();

		AnalyticsEvent analyticsEvent = stream.filter(
			event -> Objects.equals(event.getEventId(), "assetViewed")
		).findAny(
		).orElse(
			analyticsEvents.get(analyticsEvents.size() - 1)
		);

		Map<String, String> eventProperties =
			analyticsEvent.getEventProperties();

		customAssetDashboard.setAssetId(eventProperties.get("assetId"));

		String title = eventProperties.get("title");

		if (StringUtils.isNotEmpty(title)) {
			customAssetDashboard.setAssetTitle(title);
		}

		customAssetDashboard.setCategory(
			eventProperties.getOrDefault("category", "default"));
		customAssetDashboard.setChannelId(
			Long.valueOf(analyticsEvent.getChannelId()));
		customAssetDashboard.setCreateDate(analyticsEvent.getEventDate());
		customAssetDashboard.setDataSourceId(
			Long.valueOf(analyticsEvent.getDataSourceId()));
		customAssetDashboard.setId(id);

		return customAssetDashboard;
	}

	private static final Log _log = LogFactory.getLog(
		CustomAssetDashboardNanite.class);

	@Autowired
	private CustomAssetDashboardRepository _customAssetDashboardRepository;

	private final ExecutorService _executorService =
		Executors.newFixedThreadPool(2);

	@MessageSubscriber.Autowired(
		channel = Channel.ANALYTICS_EVENTS_CUSTOM_ASSET
	)
	private MessageSubscriber _messageSubscriber;

	private final ReentrantLock _reentrantLock = new ReentrantLock();
	private final Semaphore _semaphore = new Semaphore(4, true);

}