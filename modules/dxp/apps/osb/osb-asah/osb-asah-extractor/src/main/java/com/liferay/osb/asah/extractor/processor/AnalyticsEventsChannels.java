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

package com.liferay.osb.asah.extractor.processor;

import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.model.AnalyticsEvent;
import com.liferay.osb.asah.common.util.MapUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

import org.apache.commons.lang3.StringUtils;

import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class AnalyticsEventsChannels {

	public AnalyticsEventsChannels() {
		_channels.put(
			Channel.ANALYTICS_EVENTS_BLOG, _analyticsEventsBlogPredicate());
		_channels.put(
			Channel.ANALYTICS_EVENTS_CUSTOM_ASSET,
			_analyticsEventsCustomAssetPredicate());
		_channels.put(
			Channel.ANALYTICS_EVENTS_DOCUMENT,
			_analyticsEventsDocumentPredicate());
		_channels.put(
			Channel.ANALYTICS_EVENTS_FORM, _analyticsEventsFormPredicate());
		_channels.put(
			Channel.ANALYTICS_EVENTS_JOURNAL,
			_analyticsEventsJournalPredicate());
		_channels.put(
			Channel.ANALYTICS_EVENTS_PAGE, _analyticsEventsPagePredicate());
	}

	public List<Channel> getChannels(AnalyticsEvent analyticsEvent) {
		List<Channel> channels = new ArrayList<>();

		for (Map.Entry<Channel, Predicate<AnalyticsEvent>> entry :
				_channels.entrySet()) {

			Predicate<AnalyticsEvent> predicate = entry.getValue();

			if (predicate.test(analyticsEvent)) {
				channels.add(entry.getKey());
			}
		}

		return channels;
	}

	private Predicate<AnalyticsEvent> _analyticsEventsBlogPredicate() {
		return analyticsEvent -> {
			if (Objects.equals(analyticsEvent.getApplicationId(), "Blog")) {
				return true;
			}

			if (Objects.equals(analyticsEvent.getApplicationId(), "Comment") &&
				Objects.equals(analyticsEvent.getEventId(), "posted") &&
				Objects.equals(
					MapUtil.getString(
						analyticsEvent.getEventProperties(), "className"),
					"com.liferay.blogs.model.BlogsEntry")) {

				return true;
			}

			String ratingType = MapUtil.getString(
				analyticsEvent.getEventProperties(), "ratingType");

			if (Objects.equals(analyticsEvent.getApplicationId(), "Ratings") &&
				Objects.equals(analyticsEvent.getEventId(), "VOTE") &&
				Objects.equals(
					MapUtil.getString(
						analyticsEvent.getEventProperties(), "className"),
					"com.liferay.blogs.model.BlogsEntry") &&
				(Objects.isNull(ratingType) ||
				 Objects.equals(ratingType, "stars"))) {

				return true;
			}

			return false;
		};
	}

	private Predicate<AnalyticsEvent> _analyticsEventsCustomAssetPredicate() {
		return analyticsEvent -> {
			if (Objects.equals(analyticsEvent.getApplicationId(), "Custom") &&
				StringUtils.isNotBlank(
					MapUtil.getString(
						analyticsEvent.getEventProperties(), "assetId"))) {

				return true;
			}

			return false;
		};
	}

	private Predicate<AnalyticsEvent> _analyticsEventsDocumentPredicate() {
		return analyticsEvent -> {
			if (Objects.equals(analyticsEvent.getApplicationId(), "Comment") &&
				Objects.equals(analyticsEvent.getEventId(), "posted") &&
				Objects.equals(
					MapUtil.getString(
						analyticsEvent.getEventProperties(), "className"),
					"com.liferay.document.library.kernel.model.DLFileEntry")) {

				return true;
			}

			if (Objects.equals(analyticsEvent.getApplicationId(), "Document") &&
				(Objects.equals(
					analyticsEvent.getEventId(), "documentDownloaded") ||
				 Objects.equals(
					 analyticsEvent.getEventId(), "documentPreviewed"))) {

				return true;
			}

			String ratingType = MapUtil.getString(
				analyticsEvent.getEventProperties(), "ratingType");

			if (Objects.equals(analyticsEvent.getApplicationId(), "Ratings") &&
				Objects.equals(analyticsEvent.getEventId(), "VOTE") &&
				Objects.equals(
					MapUtil.getString(
						analyticsEvent.getEventProperties(), "className"),
					"com.liferay.document.library.kernel.model.DLFileEntry") &&
				(Objects.isNull(ratingType) ||
				 Objects.equals(ratingType, "stars"))) {

				return true;
			}

			return false;
		};
	}

	private Predicate<AnalyticsEvent> _analyticsEventsFormPredicate() {
		return analyticsEvent -> {
			if (!Objects.equals(analyticsEvent.getApplicationId(), "Form")) {
				return false;
			}

			String eventId = analyticsEvent.getEventId();

			if (Objects.equals(eventId, "fieldBlurred") ||
				Objects.equals(eventId, "fieldFocused") ||
				Objects.equals(eventId, "formSubmitted") ||
				Objects.equals(eventId, "formViewed") ||
				Objects.equals(eventId, "pageViewed")) {

				return true;
			}

			return false;
		};
	}

	private Predicate<AnalyticsEvent> _analyticsEventsJournalPredicate() {
		return analyticsEvent -> {
			String ratingType = MapUtil.getString(
				analyticsEvent.getEventProperties(), "ratingType");

			if (Objects.equals(analyticsEvent.getApplicationId(), "Ratings") &&
				Objects.equals(analyticsEvent.getEventId(), "VOTE") &&
				Objects.equals(
					MapUtil.getString(
						analyticsEvent.getEventProperties(), "className"),
					"com.liferay.journal.model.JournalArticle") &&
				(Objects.isNull(ratingType) ||
				 Objects.equals(ratingType, "stars"))) {

				return true;
			}

			if (Objects.equals(
					analyticsEvent.getApplicationId(), "WebContent") &&
				Objects.equals(
					analyticsEvent.getEventId(), "webContentViewed")) {

				return true;
			}

			return false;
		};
	}

	private Predicate<AnalyticsEvent> _analyticsEventsPagePredicate() {
		return analyticsEvent -> {
			String eventId = analyticsEvent.getEventId();

			if (Objects.equals(eventId, "blogViewed") ||
				Objects.equals(eventId, "formViewed") ||
				Objects.equals(eventId, "pageLoaded") ||
				Objects.equals(eventId, "pageUnloaded") ||
				Objects.equals(eventId, "webContentViewed")) {

				return false;
			}

			if (Objects.equals(analyticsEvent.getApplicationId(), "Form") &&
				Objects.equals(analyticsEvent.getEventId(), "pageViewed") &&
				Objects.equals(
					MapUtil.getLong(
						analyticsEvent.getEventProperties(), "page"),
					0L)) {

				return false;
			}

			return true;
		};
	}

	private Map<Channel, Predicate<AnalyticsEvent>> _channels = new HashMap<>();

}