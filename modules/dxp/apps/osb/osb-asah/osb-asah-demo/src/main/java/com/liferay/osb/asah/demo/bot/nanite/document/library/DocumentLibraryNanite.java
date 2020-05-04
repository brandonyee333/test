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

package com.liferay.osb.asah.demo.bot.nanite.document.library;

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
public class DocumentLibraryNanite extends BaseNanite {

	@Override
	protected void generateAnalyticsEvents() {
		Map<String, Object> context = DataSourceUtil.getContext();
		Date eventDate = Date.from(Instant.now());
		String userId = DataSourceUtil.getUserId();

		_generatePageViewedAnalyticsEvent(context, eventDate, userId);

		long fileEntryId = DataSourceUtil.getModelId();

		if (DataSourceUtil.getInt(0, 100) < 10) {
			eventDate = getNextDate(eventDate, 10, 60);

			_generateCommentAnalyticsEvent(
				context, eventDate, fileEntryId, userId);
		}

		if (DataSourceUtil.getInt(0, 100) < 80) {
			eventDate = getNextDate(eventDate, 10, 60);

			_generateDocumentDownloadedAnalyticsEvent(
				context, eventDate, fileEntryId, userId);
		}

		if (DataSourceUtil.getInt(0, 100) < 50) {
			eventDate = getNextDate(eventDate, 10, 60);

			_generateDocumentPreviewedAnalyticsEvent(
				context, eventDate, fileEntryId, userId);
		}

		if (DataSourceUtil.getInt(0, 100) < 30) {
			eventDate = getNextDate(eventDate, 10, 60);

			_generateRatingsAnalyticsEvent(
				context, eventDate, fileEntryId, userId);
		}
	}

	private void _generateCommentAnalyticsEvent(
		Map<String, Object> context, Date eventDate, long fileEntryId,
		String userId) {

		Map<String, String> eventProperties = new HashMap<>();

		eventProperties.put(
			"className",
			"com.liferay.document.library.kernel.model.DLFileEntry");
		eventProperties.put("classPK", String.valueOf(fileEntryId));

		saveAnalyticsEvent(
			"Comment", context, eventDate, "posted", eventProperties, userId);
	}

	private void _generateDocumentDownloadedAnalyticsEvent(
		Map<String, Object> context, Date eventDate, long fileEntryId,
		String userId) {

		Map<String, String> eventProperties = new HashMap<>();

		eventProperties.put("fileEntryId", String.valueOf(fileEntryId));
		eventProperties.put("title", DataSourceUtil.getString());

		saveAnalyticsEvent(
			"Document", context, eventDate, "documentDownloaded",
			eventProperties, userId);
	}

	private void _generateDocumentPreviewedAnalyticsEvent(
		Map<String, Object> context, Date eventDate, long fileEntryId,
		String userId) {

		Map<String, String> eventProperties = new HashMap<>();

		eventProperties.put("fileEntryId", String.valueOf(fileEntryId));

		saveAnalyticsEvent(
			"Document", context, eventDate, "documentPreviewed",
			eventProperties, userId);
	}

	private void _generatePageViewedAnalyticsEvent(
		Map<String, Object> context, Date eventDate, String userId) {

		saveAnalyticsEvent(
			"Page", context, eventDate, "pageViewed", new HashMap<>(), userId);
	}

	private void _generateRatingsAnalyticsEvent(
		Map<String, Object> context, Date eventDate, long fileEntryId,
		String userId) {

		Map<String, String> eventProperties = new HashMap<>();

		eventProperties.put(
			"className",
			"com.liferay.document.library.kernel.model.DLFileEntry");
		eventProperties.put("classPK", String.valueOf(fileEntryId));
		eventProperties.put(
			"score", String.valueOf(DataSourceUtil.getInt(1, 5)));

		saveAnalyticsEvent(
			"Ratings", context, eventDate, "VOTE", eventProperties, userId);
	}

}