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

package com.liferay.osb.asah.demo.bot.nanite.page;

import com.liferay.osb.asah.common.util.MapUtil;
import com.liferay.osb.asah.demo.bot.nanite.BaseNanite;
import com.liferay.osb.asah.demo.util.DataSourceUtil;

import java.time.Instant;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * @author Inácio Nery
 */
@Component
public class PageNanite extends BaseNanite {

	@Override
	protected void generateAnalyticsEvents() {
		Map<String, Object> context = DataSourceUtil.getContext();
		Date eventDate = Date.from(Instant.now());
		String userId = DataSourceUtil.getUserId();

		int pages = DataSourceUtil.getInt(1, 10);

		for (int i = 0; i < pages; i++) {
			if (DataSourceUtil.getInt(0, 100) < 50) {
				eventDate = getNextDate(eventDate, 1, 60);

				_generatePageDepthReachedAnalyticsEvent(
					context, eventDate, userId);
			}

			eventDate = getNextDate(eventDate, 10, 60);

			_generatePageViewedAnalyticsEvent(context, eventDate, userId);

			context.put("referrer", MapUtil.getString(context, "url"));
			context.put("title", DataSourceUtil.getString());
			context.put("url", DataSourceUtil.getURL());
		}
	}

	private void _generatePageDepthReachedAnalyticsEvent(
		Map<String, Object> context, Date eventDate, String userId) {

		Map<String, String> eventProperties = new HashMap<>();

		eventProperties.put("depth", DataSourceUtil.getDepth());

		saveAnalyticsEvent(
			"Page", context, eventDate, "pageDepthReached", eventProperties,
			userId);
	}

	private void _generatePageViewedAnalyticsEvent(
		Map<String, Object> context, Date eventDate, String userId) {

		saveAnalyticsEvent(
			"Page", context, eventDate, "pageViewed", new HashMap<>(), userId);
	}

}