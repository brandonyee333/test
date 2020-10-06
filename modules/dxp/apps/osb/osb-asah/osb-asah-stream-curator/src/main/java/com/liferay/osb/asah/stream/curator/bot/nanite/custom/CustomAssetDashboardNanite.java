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
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageSubscriber;
import com.liferay.osb.asah.common.model.AnalyticsEvent;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.stream.curator.bot.nanite.Nanite;
import com.liferay.osb.asah.stream.curator.bot.nanite.util.NaniteUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.script.Script;

import org.json.JSONObject;

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
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	private void _addCustomAssetDashboards(
		String customAssetPrimaryKey, List<AnalyticsEvent> analyticsEvents) {

		JSONObject jsonObject = _toCustomAssetJSONObject(
			customAssetPrimaryKey,
			analyticsEvents.get(analyticsEvents.size() - 1));

		Script script = null;

		String assetTitle = jsonObject.optString("assetTitle");

		if (StringUtils.isEmpty(assetTitle)) {
			script = new Script("ctx.op = 'noop'");
		}
		else {
			script = new Script(
				Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
				"ctx._source.assetTitle = params.assetTitle",
				new HashMap() {
					{
						put("assetTitle", assetTitle);
					}
				});
		}

		_cerebroInfoElasticsearchInvoker.upsert(
			getCollectionName(), customAssetPrimaryKey, jsonObject, script);
	}

	private List<AnalyticsEvent> _fetchAnalyticsEvents() throws Exception {
		List<AnalyticsEvent> analyticsEvents = _messageSubscriber.pullMessages(
			50, AnalyticsEvent::toAnalyticsEvent);

		Stream<AnalyticsEvent> stream = analyticsEvents.stream();

		return stream.filter(
			analyticsEvent -> Objects.equals(
				analyticsEvent.getEventId(), "assetViewed")
		).collect(
			Collectors.toList()
		);
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
			long start = System.currentTimeMillis();

			List<AnalyticsEvent> analyticsEvents = _fetchAnalyticsEvents();

			if (analyticsEvents.isEmpty()) {
				break;
			}

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
	}

	private JSONObject _toCustomAssetJSONObject(
		String id, AnalyticsEvent analyticsEvent) {

		Map<String, String> eventProperties =
			analyticsEvent.getEventProperties();

		JSONObject jsonObject = new JSONObject();

		jsonObject.put("assetId", eventProperties.get("assetId"));

		String title = eventProperties.get("title");

		if (StringUtils.isNotEmpty(title)) {
			jsonObject.put("assetTitle", title);
		}

		jsonObject.put(
			"category", eventProperties.getOrDefault("category", "default"));
		jsonObject.put("channelId", analyticsEvent.getChannelId());
		jsonObject.put(
			"createDate", DateUtil.toUTCString(analyticsEvent.getEventDate()));
		jsonObject.put("dataSourceId", analyticsEvent.getDataSourceId());
		jsonObject.put("id", id);

		return jsonObject;
	}

	private static final Log _log = LogFactory.getLog(
		CustomAssetDashboardNanite.class);

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	@MessageSubscriber.Autowired(
		channel = Channel.ANALYTICS_EVENTS_CUSTOM_ASSET
	)
	private MessageSubscriber _messageSubscriber;

}