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

package com.liferay.osb.asah.stream.curator.bot.nanite.document.library;

import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageSubscriber;
import com.liferay.osb.asah.common.model.AnalyticsEvent;
import com.liferay.osb.asah.stream.curator.bot.nanite.BaseNanite;
import com.liferay.osb.asah.stream.curator.model.document.library.DocumentLibrary;

import java.util.Map;
import java.util.Objects;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class DocumentLibraryNanite extends BaseNanite<DocumentLibrary> {

	@Override
	public String getCollectionName() {
		return "document-libraries";
	}

	@Override
	protected BinaryOperator<DocumentLibrary> getBinaryOperator() {
		return (DocumentLibrary oldDocumentLibrary,
				DocumentLibrary newDocumentLibrary) -> {

			mergeModels(oldDocumentLibrary, newDocumentLibrary);

			oldDocumentLibrary.addComments(newDocumentLibrary.getComments());
			oldDocumentLibrary.addDownloads(newDocumentLibrary.getDownloads());
			oldDocumentLibrary.addPreviews(newDocumentLibrary.getPreviews());

			_setRatingScore(oldDocumentLibrary, newDocumentLibrary);

			return oldDocumentLibrary;
		};
	}

	@Override
	protected Predicate<DocumentLibrary> getFilterPredicate() {
		return documentLibrary -> StringUtils.isNotBlank(
			documentLibrary.getAssetId());
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
	protected Supplier<DocumentLibrary> getModelSupplier() {
		return DocumentLibrary::new;
	}

	@Override
	protected Function<DocumentLibrary, String>
		getPrimaryKeyGeneratorFunction() {

		return documentLibrary -> digest(
			documentLibrary.getAssetId(), documentLibrary.getChannelId(),
			documentLibrary.getEventDate(), documentLibrary.getUserId(),
			documentLibrary.getVariantId());
	}

	@Override
	protected void setModelCustomProperties(
		AnalyticsEvent analyticsEvent, DocumentLibrary documentLibrary) {

		String eventId = analyticsEvent.getEventId();

		Map<String, String> eventProperties =
			analyticsEvent.getEventProperties();

		if (Objects.equals(eventId, "VOTE")) {
			documentLibrary.setRatings(1);

			documentLibrary.setAssetId(eventProperties.get("classPK"));
			documentLibrary.setRatingsScore(
				Float.parseFloat(eventProperties.get("score")));
		}
		else if (Objects.equals(eventId, "documentDownloaded")) {
			documentLibrary.addDownloads(1);

			documentLibrary.setAssetId(eventProperties.get("fileEntryId"));
		}
		else if (Objects.equals(eventId, "documentPreviewed")) {
			documentLibrary.addPreviews(1);

			documentLibrary.setAssetId(eventProperties.get("fileEntryId"));
		}
		else if (Objects.equals(eventId, "posted")) {
			documentLibrary.addComments(1);

			documentLibrary.setAssetId(eventProperties.get("classPK"));
		}
	}

	private void _setRatingScore(
		DocumentLibrary oldDocumentLibrary,
		DocumentLibrary newDocumentLibrary) {

		if (newDocumentLibrary.getRatings() == 0) {
			return;
		}

		if (newDocumentLibrary.getRatingsScore() < 0) {
			oldDocumentLibrary.setRatings(0);
			oldDocumentLibrary.setRatingsScore(0);
		}
		else {
			oldDocumentLibrary.setRatings(1);
			oldDocumentLibrary.setRatingsScore(
				newDocumentLibrary.getRatingsScore());
		}
	}

	private static final Log _log = LogFactory.getLog(
		DocumentLibraryNanite.class);

	@MessageSubscriber.Autowired(channel = Channel.ANALYTICS_EVENTS_DOCUMENT)
	private MessageSubscriber _messageSubscriber;

}