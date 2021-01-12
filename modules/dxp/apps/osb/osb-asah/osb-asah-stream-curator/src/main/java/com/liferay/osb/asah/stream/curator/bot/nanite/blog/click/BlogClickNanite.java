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

package com.liferay.osb.asah.stream.curator.bot.nanite.blog.click;

import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageSubscriber;
import com.liferay.osb.asah.common.model.AnalyticsEvent;
import com.liferay.osb.asah.stream.curator.bot.nanite.BaseNanite;
import com.liferay.osb.asah.stream.curator.model.blog.BlogClick;

import java.util.List;
import java.util.Map;
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

/**
 * @author Inácio Nery
 */
public class BlogClickNanite extends BaseNanite<BlogClick> {

	@Override
	public String getCollectionName() {
		return "blog-clicks";
	}

	@Override
	protected BinaryOperator<BlogClick> getBinaryOperator() {
		return (BlogClick oldBlogClick, BlogClick newBlogClick) -> {
			mergeModels(oldBlogClick, newBlogClick);

			oldBlogClick.addClicks(newBlogClick.getClicks());

			return oldBlogClick;
		};
	}

	@Override
	protected Predicate<BlogClick> getFilterPredicate() {
		return blogClick -> StringUtils.isNotBlank(blogClick.getAssetId());
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
	protected Supplier<BlogClick> getModelSupplier() {
		return BlogClick::new;
	}

	@Override
	protected Function<BlogClick, String> getPrimaryKeyGeneratorFunction() {
		return blogClick -> digest(
			blogClick.getAssetId(), blogClick.getChannelId(),
			blogClick.getElementText(), blogClick.getElementType(),
			blogClick.getElementURL(), blogClick.getEventDate(),
			blogClick.getVariantId());
	}

	@Override
	protected List<AnalyticsEvent> pullAnalyticsEvents() throws Exception {
		List<AnalyticsEvent> analyticsEvents = super.pullAnalyticsEvents();

		Stream<AnalyticsEvent> stream = analyticsEvents.stream();

		return stream.filter(
			analyticsEvent -> {
				if (Objects.equals(analyticsEvent.getApplicationId(), "Blog") &&
					Objects.equals(
						analyticsEvent.getEventId(), "blogClicked")) {

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
		AnalyticsEvent analyticsEvent, BlogClick blogClick) {

		blogClick.addClicks(1);

		Map<String, String> eventProperties =
			analyticsEvent.getEventProperties();

		blogClick.setAssetId(eventProperties.get("entryId"));
		blogClick.setElement(eventProperties);
	}

	private static final Log _log = LogFactory.getLog(BlogClickNanite.class);

	@MessageSubscriber.Autowired(channel = Channel.ANALYTICS_EVENTS_BLOG)
	private MessageSubscriber _messageSubscriber;

}