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

package com.liferay.osb.asah.demo.bot.nanite.journal;

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
public class JournalNanite extends BaseNanite {

	@Override
	protected void generateAnalyticsEvents() {
		Map<String, Object> context = DataSourceUtil.getContext();
		Date eventDate = Date.from(Instant.now());
		String userId = DataSourceUtil.getUserId();

		_generatePageViewedAnalyticsEvent(context, eventDate, userId);

		long articleId = DataSourceUtil.getModelId();

		if (DataSourceUtil.getInt(0, 100) < 30) {
			eventDate = getNextDate(eventDate, 60, 600);

			_generateRatingsAnalyticsEvent(
				articleId, context, eventDate, userId);
		}

		if (DataSourceUtil.getInt(0, 100) < 50) {
			int clicks = DataSourceUtil.getInt(1, 20);

			for (int i = 0; i < clicks; i++) {
				eventDate = getNextDate(eventDate, 10, 60);

				_generateWebContentClickedAnalyticsEvent(
					articleId, context, eventDate, userId);
			}
		}

		_generateWebContentViewedAnalyticsEvent(
			articleId, context, eventDate, userId);
	}

	private void _generatePageViewedAnalyticsEvent(
		Map<String, Object> context, Date eventDate, String userId) {

		saveAnalyticsEvent(
			"Page", context, eventDate, "pageViewed", new HashMap<>(), userId);
	}

	private void _generateRatingsAnalyticsEvent(
		long articleId, Map<String, Object> context, Date eventDate,
		String userId) {

		Map<String, String> eventProperties = new HashMap<>();

		eventProperties.put(
			"className", "com.liferay.journal.model.JournalArticle");
		eventProperties.put("classPK", String.valueOf(articleId));
		eventProperties.put(
			"score", String.valueOf(DataSourceUtil.getInt(1, 5)));

		saveAnalyticsEvent(
			"Ratings", context, eventDate, "VOTE", eventProperties, userId);
	}

	private void _generateWebContentClickedAnalyticsEvent(
		long articleId, Map<String, Object> context, Date eventDate,
		String userId) {

		Map<String, String> eventProperties = new HashMap<>();

		eventProperties.put("articleId", String.valueOf(articleId));
		eventProperties.put("href", DataSourceUtil.getURL());
		eventProperties.put("src", DataSourceUtil.getURL());
		eventProperties.put("tagName", DataSourceUtil.getTagName());
		eventProperties.put("text", DataSourceUtil.getString());

		saveAnalyticsEvent(
			"WebContent", context, eventDate, "webContentClicked",
			eventProperties, userId);
	}

	private void _generateWebContentViewedAnalyticsEvent(
		long articleId, Map<String, Object> context, Date eventDate,
		String userId) {

		Map<String, String> eventProperties = new HashMap<>();

		eventProperties.put("articleId", String.valueOf(articleId));
		eventProperties.put("title", DataSourceUtil.getString());

		saveAnalyticsEvent(
			"WebContent", context, eventDate, "webContentViewed",
			eventProperties, userId);
	}

}