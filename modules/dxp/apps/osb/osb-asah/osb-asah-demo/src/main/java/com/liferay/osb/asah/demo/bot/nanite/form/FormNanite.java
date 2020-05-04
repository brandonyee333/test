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

package com.liferay.osb.asah.demo.bot.nanite.form;

import com.liferay.osb.asah.demo.bot.nanite.BaseNanite;
import com.liferay.osb.asah.demo.util.DataSourceUtil;

import java.time.Instant;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * @author Inácio Nery
 * @author Leonardo Barros
 */
@Component
public class FormNanite extends BaseNanite {

	@Override
	protected void generateAnalyticsEvents() {
		List<Page> pages = _createPages();

		boolean submit = false;

		if (DataSourceUtil.getInt(0, 100) < 25) {
			submit = true;
		}

		Map<String, Object> context = DataSourceUtil.getContext();
		Date eventDate = Date.from(Instant.now());
		String userId = DataSourceUtil.getUserId();

		_generatePageViewedAnalyticsEvent(context, eventDate, userId);

		long formId = DataSourceUtil.getModelId();

		if (submit) {
			_generateFormSubmittedAnalyticsEvent(
				context, getNextDate(eventDate, 60, 600), formId, userId);
		}

		_generateFormViewedAnalyticsEvent(context, eventDate, formId, userId);

		int lastPageIndex = _getLastPageIndex(pages.size(), submit);

		for (int i = 0; i <= lastPageIndex; i++) {
			eventDate = getNextDate(eventDate, 60, 600);

			_generateFieldAnalyticsEvents(
				userId, formId, pages.get(i), eventDate, context);
			_generatePageViewedAnalyticsEvent(
				context, eventDate, formId, i, userId);
		}
	}

	private List<String> _createFieldNames() {
		List<String> fieldNames = new ArrayList<>();

		int count = DataSourceUtil.getInt(1, 10);

		for (int i = 0; i < count; i++) {
			fieldNames.add(DataSourceUtil.getString());
		}

		return fieldNames;
	}

	private List<Page> _createPages() {
		List<Page> pages = new ArrayList<>();

		int count = DataSourceUtil.getInt(1, 4);

		for (int i = 0; i < count; i++) {
			pages.add(Page.of(_createFieldNames()));
		}

		return pages;
	}

	private void _generateFieldAnalyticsEvents(
		String userId, long formId, Page page, Date pageViewedEventDate,
		Map<String, Object> context) {

		for (String fieldName : page.getFieldNames()) {
			int refilledEventsLength = 0;

			if (DataSourceUtil.getInt(0, 100) < 25) {
				refilledEventsLength = DataSourceUtil.getInt(1, 5);
			}

			for (int i = 0; i < refilledEventsLength; i++) {
				Date fieldFocusedEventDate = getNextDate(
					pageViewedEventDate, 60, 600);

				_generateFieldBlurredAnalyticsEvent(
					context, getNextDate(fieldFocusedEventDate, 60, 600),
					fieldName, DataSourceUtil.getInt(20), formId, userId);
				_generateFieldFocusedAnalyticsEvent(
					context, fieldFocusedEventDate, fieldName, formId, userId);
			}
		}
	}

	private void _generateFieldBlurredAnalyticsEvent(
		Map<String, Object> context, Date eventDate, String fieldName,
		long focusDuration, long formId, String userId) {

		Map<String, String> eventProperties = new HashMap<>();

		eventProperties.put("fieldName", fieldName);
		eventProperties.put("focusDuration", String.valueOf(focusDuration));
		eventProperties.put("formId", String.valueOf(formId));

		saveAnalyticsEvent(
			"Form", context, eventDate, "fieldBlurred", eventProperties,
			userId);
	}

	private void _generateFieldFocusedAnalyticsEvent(
		Map<String, Object> context, Date eventDate, String fieldName,
		long formId, String userId) {

		Map<String, String> eventProperties = new HashMap<>();

		eventProperties.put("fieldName", fieldName);
		eventProperties.put("formId", String.valueOf(formId));

		saveAnalyticsEvent(
			"Form", context, eventDate, "fieldFocused", eventProperties,
			userId);
	}

	private void _generateFormSubmittedAnalyticsEvent(
		Map<String, Object> context, Date eventDate, long formId,
		String userId) {

		Map<String, String> eventProperties = new HashMap<>();

		eventProperties.put("formId", String.valueOf(formId));

		saveAnalyticsEvent(
			"Form", context, eventDate, "formSubmitted", eventProperties,
			userId);
	}

	private void _generateFormViewedAnalyticsEvent(
		Map<String, Object> context, Date eventDate, long formId,
		String userId) {

		Map<String, String> eventProperties = new HashMap<>();

		eventProperties.put("formId", String.valueOf(formId));
		eventProperties.put("title", DataSourceUtil.getString());

		saveAnalyticsEvent(
			"Form", context, eventDate, "formViewed", eventProperties, userId);
	}

	private void _generatePageViewedAnalyticsEvent(
		Map<String, Object> context, Date eventDate, long formId, int page,
		String userId) {

		Map<String, String> eventProperties = new HashMap<>();

		eventProperties.put("formId", String.valueOf(formId));
		eventProperties.put("page", String.valueOf(page));

		saveAnalyticsEvent(
			"Form", context, eventDate, "pageViewed", eventProperties, userId);
	}

	private void _generatePageViewedAnalyticsEvent(
		Map<String, Object> context, Date eventDate, String userId) {

		saveAnalyticsEvent(
			"Page", context, eventDate, "pageViewed", new HashMap<>(), userId);
	}

	private int _getLastPageIndex(int size, boolean submit) {
		if ((size == 1) || submit) {
			return size - 1;
		}

		return DataSourceUtil.getInt(0, size - 1);
	}

	private static class Page {

		public static Page of(List<String> fieldNames) {
			return new Page(fieldNames);
		}

		public Page(List<String> fieldNames) {
			_fieldNames = fieldNames;
		}

		public List<String> getFieldNames() {
			return _fieldNames;
		}

		private final List<String> _fieldNames;

	}

}