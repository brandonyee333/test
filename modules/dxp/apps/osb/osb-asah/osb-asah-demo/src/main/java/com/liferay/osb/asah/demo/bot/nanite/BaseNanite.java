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

import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageBus;
import com.liferay.osb.asah.common.model.AnalyticsEvent;
import com.liferay.osb.asah.demo.util.DataSourceUtil;

import java.util.Date;
import java.util.Map;

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
	}

	protected abstract void generateAnalyticsEvents();

	protected abstract Channel getChannel();

	protected Date getNextDate(Date date, int minDelay, int maxDelay) {
		int delay = DataSourceUtil.getInt(minDelay, maxDelay);

		return new Date(date.getTime() + (delay * 1000L));
	}

	protected void sendAnalyticsEvent(
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

		_messageBus.sendMessage(getChannel(), analyticsEvent.toJSON());
	}

	@Autowired
	private MessageBus _messageBus;

}