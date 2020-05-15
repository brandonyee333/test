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

package com.liferay.osb.asah.stream.curator.bot.nanite.journal.click;

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.model.AnalyticsEvent;
import com.liferay.osb.asah.stream.curator.bot.nanite.BaseNanite;
import com.liferay.osb.asah.stream.curator.model.journal.JournalClick;

import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

/**
 * @author Inácio Nery
 * @author Brian Wing Shun Chan
 */
public class JournalClickNanite extends BaseNanite<JournalClick> {

	@Override
	public String getCollectionName() {
		return "journal-clicks";
	}

	@Override
	protected BinaryOperator<JournalClick> getBinaryOperator() {
		return (JournalClick oldJournalClick, JournalClick newJournalClick) -> {
			mergeModels(oldJournalClick, newJournalClick);

			oldJournalClick.addClicks(newJournalClick.getClicks());

			return oldJournalClick;
		};
	}

	@Override
	protected Predicate<JournalClick> getFilterPredicate() {
		return journalClick -> StringUtils.isNotBlank(
			journalClick.getAssetId());
	}

	@Override
	protected Log getLog() {
		return _log;
	}

	@Override
	protected Supplier<JournalClick> getModelSupplier() {
		return JournalClick::new;
	}

	@Override
	protected Function<JournalClick, String> getPrimaryKeyGeneratorFunction() {
		return journalClick -> digest(
			journalClick.getAssetId(), journalClick.getChannelId(),
			journalClick.getElementText(), journalClick.getElementType(),
			journalClick.getElementURL(), journalClick.getEventDate(),
			journalClick.getVariantId());
	}

	@Override
	protected QueryBuilder getQueryBuilder() {
		return BoolQueryBuilderUtil.filter(
			QueryBuilders.termQuery("applicationId", "WebContent")
		).filter(
			QueryBuilders.termQuery("eventId", "webContentClicked")
		);
	}

	@Override
	protected void setModelCustomProperties(
		AnalyticsEvent analyticsEvent, JournalClick journalClick) {

		journalClick.addClicks(1);

		Map<String, String> eventProperties =
			analyticsEvent.getEventProperties();

		journalClick.setAssetId(eventProperties.get("articleId"));
		journalClick.setElement(eventProperties);
	}

	private static final Log _log = LogFactory.getLog(JournalClickNanite.class);

}