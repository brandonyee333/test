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

package com.liferay.osb.asah.stream.curator.bot.nanite.journal;

import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageSubscriber;
import com.liferay.osb.asah.common.model.AnalyticsEvent;
import com.liferay.osb.asah.stream.curator.bot.nanite.BaseStreamNanite;
import com.liferay.osb.asah.stream.curator.model.journal.Journal;

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
 * @author Inácio Nery
 * @author Brian Wing Shun Chan
 */
@Component
public class JournalNanite extends BaseStreamNanite<Journal> {

	@Override
	public String getCollectionName() {
		return "journals";
	}

	@Override
	protected BinaryOperator<Journal> getBinaryOperator() {
		return (Journal oldJournal, Journal newJournal) -> {
			mergeModels(oldJournal, newJournal);

			oldJournal.addViews(newJournal.getViews());

			_setRatingScore(oldJournal, newJournal);

			return oldJournal;
		};
	}

	@Override
	protected Predicate<Journal> getFilterPredicate() {
		return journal -> StringUtils.isNotBlank(journal.getAssetId());
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
	protected Supplier<Journal> getModelSupplier() {
		return Journal::new;
	}

	@Override
	protected Function<Journal, String> getPrimaryKeyGeneratorFunction() {
		return journal -> digest(
			journal.getAssetId(), journal.getChannelId(),
			journal.getEventDate(), journal.getUserId(),
			journal.getVariantId());
	}

	@Override
	protected void setModelCustomProperties(
		AnalyticsEvent analyticsEvent, Journal journal) {

		String eventId = analyticsEvent.getEventId();

		Map<String, String> eventProperties =
			analyticsEvent.getEventProperties();

		if (Objects.equals(eventId, "VOTE")) {
			journal.setRatings(1);

			journal.setAssetId(eventProperties.get("classPK"));
			journal.setRatingsScore(
				Float.valueOf(eventProperties.get("score")));
		}
		else if (Objects.equals(eventId, "webContentViewed")) {
			journal.addViews(1);

			journal.setAssetId(eventProperties.get("articleId"));
		}
	}

	private void _setRatingScore(Journal oldJournal, Journal newJournal) {
		if (newJournal.getRatings() == 0) {
			return;
		}

		oldJournal.setRatings(1);
		oldJournal.setRatingsScore(newJournal.getRatingsScore());
	}

	private static final Log _log = LogFactory.getLog(JournalNanite.class);

	@MessageSubscriber.Autowired(channel = Channel.ANALYTICS_EVENTS_JOURNAL)
	private MessageSubscriber _messageSubscriber;

}