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

package com.liferay.osb.asah.demo.bot.nanite;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.model.AnalyticsEvent;
import com.liferay.osb.asah.demo.util.DataSourceUtil;

import java.util.Date;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Inácio Nery
 */
public abstract class BaseNanite implements Nanite {

	@Override
	public void run() {
		int count = DataSourceUtil.getInt(1, 10);

		for (int i = 0; i < count; i++) {
			generateAnalyticsEvents();
		}

		_flush();
	}

	protected abstract void generateAnalyticsEvents();

	protected Date getNextDate(Date date, int minDelay, int maxDelay) {
		int delay = DataSourceUtil.getInt(minDelay, maxDelay);

		return new Date(date.getTime() + delay * 1000L);
	}

	protected void saveAnalyticsEvent(
		String applicationId, Map<String, Object> context, Date eventDate,
		String eventId, Map<String, String> eventProperties, String userId) {

		AnalyticsEvent analyticsEvent = new AnalyticsEvent();

		analyticsEvent.setApplicationId(applicationId);
		analyticsEvent.setCreateDate(eventDate);
		analyticsEvent.setContext(context);
		analyticsEvent.setDataSourceId("liferay.com");
		analyticsEvent.setEventDate(eventDate);
		analyticsEvent.setEventId(eventId);
		analyticsEvent.setEventProperties(eventProperties);
		analyticsEvent.setUserId(userId);

		_save(analyticsEvent);
	}

	private void _flush() {
		if (_log.isInfoEnabled()) {
			_log.info("Flushing " + _bufferJSONArray.length() + " events");
		}

		try {
			_cerebroRawElasticsearchInvoker.add(
				"analytics-events", _bufferJSONArray);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		_bufferJSONArray = new JSONArray();
	}

	@PostConstruct
	private void _init() {
		_cerebroRawElasticsearchInvoker =
			_elasticsearchInvokerFactory.forCerebroRaw();
	}

	private void _save(AnalyticsEvent analyticsEvent) {
		if (_bufferJSONArray.length() == 50) {
			_flush();
		}

		_bufferJSONArray.put(new JSONObject(analyticsEvent.toJSON()));
	}

	private static final Log _log = LogFactory.getLog(BaseNanite.class);

	private JSONArray _bufferJSONArray = new JSONArray();
	private ElasticsearchInvoker _cerebroRawElasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

}