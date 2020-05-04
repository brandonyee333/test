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

import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageSubscriber;
import com.liferay.osb.asah.common.model.AnalyticsEvent;
import com.liferay.osb.asah.stream.curator.bot.nanite.BaseStreamNanite;
import com.liferay.osb.asah.stream.curator.bot.nanite.util.NaniteUtil;
import com.liferay.osb.asah.stream.curator.model.custom.CustomAsset;

import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class CustomAssetNanite extends BaseStreamNanite<CustomAsset> {

	@Override
	public String getCollectionName() {
		return "custom-assets";
	}

	@Override
	protected Function<CustomAsset, String>
		getAssetPrimaryKeyGeneratorFunction() {

		return customAsset -> digest(
			customAsset.getAssetId(), customAsset.getCategory(),
			customAsset.getChannelId(), customAsset.getVariantId());
	}

	@Override
	protected BinaryOperator<CustomAsset> getBinaryOperator() {
		return (CustomAsset oldCustomAsset, CustomAsset newCustomAsset) -> {
			mergeModels(oldCustomAsset, newCustomAsset);

			oldCustomAsset.addClicks(newCustomAsset.getClicks());
			oldCustomAsset.addDownloads(newCustomAsset.getDownloads());
			oldCustomAsset.addSubmissionDates(
				newCustomAsset.getSubmissionDates());
			oldCustomAsset.addViewDates(newCustomAsset.getViewDates());

			if (newCustomAsset.isFormEnabled()) {
				oldCustomAsset.setAbandonments(
					Math.max(
						0,
						oldCustomAsset.getViews() -
							oldCustomAsset.getSubmissions()));
			}

			_setReadPercentile(oldCustomAsset, newCustomAsset);
			_setReadTime(oldCustomAsset, newCustomAsset);
			_setSubmissionsTime(oldCustomAsset);

			return oldCustomAsset;
		};
	}

	@Override
	protected Predicate<CustomAsset> getFilterPredicate() {
		return customAsset -> StringUtils.isNotBlank(customAsset.getAssetId());
	}

	@Override
	protected Log getLog() {
		return _log;
	}

	@Override
	protected MessageSubscriber getMessageSubscriber() {
		return _messageSubscriber;
	}

	@Override
	protected Supplier<CustomAsset> getModelSupplier() {
		return CustomAsset::new;
	}

	@Override
	protected Function<CustomAsset, String> getPrimaryKeyGeneratorFunction() {
		return customAsset -> digest(
			customAsset.getAssetId(), customAsset.getCategory(),
			customAsset.getChannelId(), customAsset.getEventDate(),
			customAsset.getUserId());
	}

	@Override
	protected void setModelCustomProperties(
		AnalyticsEvent analyticsEvent, CustomAsset customAsset) {

		String eventId = analyticsEvent.getEventId();

		Map<String, String> eventProperties =
			analyticsEvent.getEventProperties();

		if (Objects.equals(eventId, "assetClicked")) {
			customAsset.addClicks(1);

			customAsset.setElement(eventProperties);
		}
		else if (Objects.equals(eventId, "assetDepthReached")) {
			if (StringUtils.isNotEmpty(eventProperties.get("depth"))) {
				customAsset.setReadPercentile(
					NaniteUtil.getReadPercentile(eventProperties.get("depth")));
			}
		}
		else if (Objects.equals(eventId, "assetDownloaded")) {
			customAsset.addDownloads(1);
		}
		else if (Objects.equals(eventId, "assetSubmitted")) {
			customAsset.addSubmissionDate(analyticsEvent.getEventDate());
		}
		else if (Objects.equals(eventId, "assetViewed")) {
			customAsset.addViewDate(analyticsEvent.getEventDate());

			if (StringUtils.isNotEmpty(eventProperties.get("formEnabled"))) {
				boolean formEnabled = Boolean.parseBoolean(
					eventProperties.get("formEnabled"));

				customAsset.setFormEnabled(formEnabled);

				if (formEnabled) {
					customAsset.addAbandonments(1);
				}
			}
		}

		customAsset.setAssetId(eventProperties.get("assetId"));
		customAsset.setCategory(
			eventProperties.getOrDefault("category", "default"));
		customAsset.setFirstEventDate(analyticsEvent.getEventDate());
	}

	private void _setReadPercentile(
		CustomAsset oldCustomAsset, CustomAsset newCustomAsset) {

		if (newCustomAsset.getReadPercentile() >
				oldCustomAsset.getReadPercentile()) {

			oldCustomAsset.setReadPercentile(
				newCustomAsset.getReadPercentile());
		}
	}

	private void _setReadTime(
		CustomAsset oldCustomAsset, CustomAsset newCustomAsset) {

		Date firstEventDate = oldCustomAsset.getFirstEventDate();

		if ((firstEventDate == null) ||
			(firstEventDate.compareTo(newCustomAsset.getFirstEventDate()) >
				0)) {

			firstEventDate = newCustomAsset.getFirstEventDate();

			oldCustomAsset.setFirstEventDate(firstEventDate);
		}

		Date lastEventDate = oldCustomAsset.getLastEventDate();

		oldCustomAsset.setReadTime(
			lastEventDate.getTime() - firstEventDate.getTime());
	}

	private void _setSubmissionsTime(CustomAsset oldCustomAsset) {
		long submissionsTime = NaniteUtil.getSubmissionsTime(
			oldCustomAsset.getEventDate(), oldCustomAsset.getSubmissionDates(),
			oldCustomAsset.getViewDates());

		oldCustomAsset.setSubmissionsTime(submissionsTime);
	}

	private static final Log _log = LogFactory.getLog(CustomAssetNanite.class);

	@MessageSubscriber.Autowired(
		channel = Channel.ANALYTICS_EVENTS_CUSTOM_ASSET
	)
	private MessageSubscriber _messageSubscriber;

}