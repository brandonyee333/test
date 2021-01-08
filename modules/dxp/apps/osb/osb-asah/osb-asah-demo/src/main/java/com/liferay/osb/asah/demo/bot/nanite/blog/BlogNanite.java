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

package com.liferay.osb.asah.demo.bot.nanite.blog;

import com.liferay.osb.asah.demo.bot.nanite.BaseNanite;
import com.liferay.osb.asah.demo.util.DataSourceUtil;

import java.time.Instant;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * @author Inácio Nery
 * @author Adolfo Pérez
 */
@Component
public class BlogNanite extends BaseNanite {

	@Override
	protected void generateAnalyticsEvents() {
		Map<String, Object> context = DataSourceUtil.getContext();
		Date eventDate = Date.from(Instant.now());
		String userId = DataSourceUtil.getUserId();

		_generatePageViewedAnalyticsEvent(context, eventDate, userId);

		long entryId = DataSourceUtil.getModelId();

		if (DataSourceUtil.getInt(0, 100) < 50) {
			int clicks = DataSourceUtil.getInt(1, 20);

			for (int i = 0; i < clicks; i++) {
				eventDate = getNextDate(eventDate, 10, 60);

				_generateBlogClickedAnalyticsEvent(
					context, entryId, eventDate, userId);
			}
		}

		_generateBlogViewedAnalyticsEvent(context, entryId, eventDate, userId);

		if (DataSourceUtil.getInt(0, 100) < 5) {
			String socialNetwork = DataSourceUtil.getSocialNetwork();

			if (DataSourceUtil.getInt(0, 100) < 10) {
				Map<String, Object> socialVisitContext =
					DataSourceUtil.getContext();

				socialVisitContext.put(
					"referrer", "http://" + socialNetwork + ".com");

				Date socialVisitEventDate = getNextDate(eventDate, 60, 3600);
				String socialVisitUserId = DataSourceUtil.getUserId();

				_generatePageViewedAnalyticsEvent(
					socialVisitContext, socialVisitEventDate,
					socialVisitUserId);

				_generateBlogViewedAnalyticsEvent(
					socialVisitContext, entryId, socialVisitEventDate,
					socialVisitUserId);
			}

			eventDate = getNextDate(eventDate, 60, 600);

			_generateSharedAnalyticsEvent(
				context, entryId, eventDate, socialNetwork, userId);
		}

		if (DataSourceUtil.getInt(0, 100) < 10) {
			eventDate = getNextDate(eventDate, 60, 600);

			_generateCommentAnalyticsEvent(context, entryId, eventDate, userId);
		}

		int percentageRead = DataSourceUtil.getInt(0, 100);

		for (int threshold : Arrays.asList(25, 50, 75, 100)) {
			if (percentageRead <= threshold) {
				break;
			}

			_generateDepthAnalyticsEvent(
				context, threshold, entryId, getNextDate(eventDate, 60, 240),
				userId);
		}

		if (DataSourceUtil.getInt(0, 100) < 30) {
			eventDate = getNextDate(eventDate, 60, 600);

			_generateRatingsAnalyticsEvent(context, entryId, eventDate, userId);
		}
	}

	private void _generateBlogClickedAnalyticsEvent(
		Map<String, Object> context, long entryId, Date eventDate,
		String userId) {

		Map<String, String> eventProperties = new HashMap<>();

		eventProperties.put("entryId", String.valueOf(entryId));
		eventProperties.put("href", DataSourceUtil.getURL());
		eventProperties.put("src", DataSourceUtil.getURL());
		eventProperties.put("tagName", DataSourceUtil.getTagName());
		eventProperties.put("text", DataSourceUtil.getString());

		saveAnalyticsEvent(
			"Blog", context, eventDate, "blogClicked", eventProperties, userId);
	}

	private void _generateBlogViewedAnalyticsEvent(
		Map<String, Object> context, long entryId, Date eventDate,
		String userId) {

		Map<String, String> eventProperties = new HashMap<>();

		eventProperties.put("entryId", String.valueOf(entryId));
		eventProperties.put("title", DataSourceUtil.getString());

		saveAnalyticsEvent(
			"Blog", context, eventDate, "blogViewed", eventProperties, userId);
	}

	private void _generateCommentAnalyticsEvent(
		Map<String, Object> context, long entryId, Date eventDate,
		String userId) {

		Map<String, String> eventProperties = new HashMap<>();

		eventProperties.put("className", "com.liferay.blogs.model.BlogsEntry");
		eventProperties.put("classPK", String.valueOf(entryId));

		saveAnalyticsEvent(
			"Comment", context, eventDate, "posted", eventProperties, userId);
	}

	private void _generateDepthAnalyticsEvent(
		Map<String, Object> context, int depth, long entryId, Date eventDate,
		String userId) {

		Map<String, String> eventProperties = new HashMap<>();

		eventProperties.put("depth", String.valueOf(depth));
		eventProperties.put("entryId", String.valueOf(entryId));

		saveAnalyticsEvent(
			"Blog", context, eventDate, "depth", eventProperties, userId);
	}

	private void _generatePageViewedAnalyticsEvent(
		Map<String, Object> context, Date eventDate, String userId) {

		saveAnalyticsEvent(
			"Page", context, eventDate, "pageViewed", new HashMap<>(), userId);
	}

	private void _generateRatingsAnalyticsEvent(
		Map<String, Object> context, long entryId, Date eventDate,
		String userId) {

		Map<String, String> eventProperties = new HashMap<>();

		eventProperties.put("className", "com.liferay.blogs.model.BlogsEntry");
		eventProperties.put("classPK", String.valueOf(entryId));
		eventProperties.put(
			"score", String.valueOf(DataSourceUtil.getInt(1, 5)));

		saveAnalyticsEvent(
			"Ratings", context, eventDate, "VOTE", eventProperties, userId);
	}

	private void _generateSharedAnalyticsEvent(
		Map<String, Object> context, long entryId, Date eventDate,
		String socialNetwork, String userId) {

		Map<String, String> eventProperties = new HashMap<>();

		eventProperties.put("className", "com.liferay.blogs.model.BlogsEntry");
		eventProperties.put("classPK", String.valueOf(entryId));
		eventProperties.put("type", socialNetwork);

		saveAnalyticsEvent(
			"shared", context, eventDate, "SocialBookmarks", eventProperties,
			userId);
	}

}