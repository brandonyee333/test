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

package com.liferay.osb.asah.stream.curator.bot.nanite.page.referrer;

import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageSubscriber;
import com.liferay.osb.asah.common.model.AnalyticsEvent;
import com.liferay.osb.asah.common.util.MapUtil;
import com.liferay.osb.asah.stream.curator.bot.nanite.BaseStreamNanite;
import com.liferay.osb.asah.stream.curator.model.page.PageReferrer;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Objects;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class PageReferrerNanite extends BaseStreamNanite<PageReferrer> {

	@Override
	public String getCollectionName() {
		return "page-referrers";
	}

	@Override
	protected BinaryOperator<PageReferrer> getBinaryOperator() {
		return (PageReferrer oldPageReferrer, PageReferrer newPageReferrer) -> {
			mergeModels(oldPageReferrer, newPageReferrer);

			NavigableSet<Date> accessDates = newPageReferrer.getAccessDates();

			oldPageReferrer.addAccessDates(accessDates);

			accessDates = oldPageReferrer.getAccessDates();

			oldPageReferrer.setAccess(accessDates.size());

			return oldPageReferrer;
		};
	}

	@Override
	protected Predicate<PageReferrer> getFilterPredicate() {
		return pageReferrer -> StringUtils.isNotBlank(pageReferrer.getURL());
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
	protected Supplier<PageReferrer> getModelSupplier() {
		return PageReferrer::new;
	}

	@Override
	protected Function<PageReferrer, String> getPrimaryKeyGeneratorFunction() {
		return pageReferrer -> digest(
			pageReferrer.getChannelId(), pageReferrer.getEventDate(),
			pageReferrer.getURL(), pageReferrer.getUserId(),
			pageReferrer.getReferrer(), pageReferrer.getVariantId());
	}

	@Override
	protected List<AnalyticsEvent> pullAnalyticsEvents() throws Exception {
		List<AnalyticsEvent> analyticsEvents = super.pullAnalyticsEvents();

		Stream<AnalyticsEvent> stream = analyticsEvents.stream();

		return stream.filter(
			analyticsEvent -> {
				if (Objects.equals(analyticsEvent.getApplicationId(), "Page") &&
					Objects.equals(analyticsEvent.getEventId(), "pageViewed")) {

					return true;
				}

				return false;
			}
		).collect(
			Collectors.toList()
		);
	}

	@Override
	protected void setModelCustomProperties(
		AnalyticsEvent analyticsEvent, PageReferrer pageReferrer) {

		pageReferrer.addAccessDate(analyticsEvent.getEventDate());
		pageReferrer.setAccess(1);

		Map<String, Object> context = analyticsEvent.getContext();

		String referrer = MapUtil.getString(context, "referrer", "");

		pageReferrer.setReferrer(referrer);

		String title = MapUtil.getString(context, "title");

		if (StringUtils.isNotEmpty(title)) {
			pageReferrer.setTitle(title);
		}
	}

	private static final Log _log = LogFactory.getLog(PageReferrerNanite.class);

	@MessageSubscriber.Autowired(channel = Channel.ANALYTICS_EVENTS_PAGE)
	private MessageSubscriber _messageSubscriber;

}