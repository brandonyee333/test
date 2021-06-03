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

package com.liferay.osb.asah.stream.curator.bot.nanite.blog.traffic.source;

import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageSubscriber;
import com.liferay.osb.asah.common.model.AnalyticsEvent;
import com.liferay.osb.asah.common.util.MapUtil;
import com.liferay.osb.asah.stream.curator.bot.nanite.BaseNanite;
import com.liferay.osb.asah.stream.curator.model.blog.BlogTrafficSource;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Inácio Nery
 */
public class BlogTrafficSourceNanite extends BaseNanite<BlogTrafficSource> {

	@Override
	public String getCollectionName() {
		return "blog-traffic-sources";
	}

	@Override
	protected BinaryOperator<BlogTrafficSource> getBinaryOperator() {
		return (BlogTrafficSource oldBlogTrafficSource,
				BlogTrafficSource newBlogTrafficSource) -> {

			mergeModels(oldBlogTrafficSource, newBlogTrafficSource);

			oldBlogTrafficSource.addTrafficSources(
				newBlogTrafficSource.getTrafficSources());

			return oldBlogTrafficSource;
		};
	}

	@Override
	protected Predicate<BlogTrafficSource> getFilterPredicate() {
		return blogTrafficSource -> StringUtils.isNotBlank(
			blogTrafficSource.getAssetId());
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
	protected Supplier<BlogTrafficSource> getModelSupplier() {
		return BlogTrafficSource::new;
	}

	@Override
	protected Function<BlogTrafficSource, String>
		getPrimaryKeyGeneratorFunction() {

		return blogTrafficSource -> digest(
			blogTrafficSource.getAssetId(), blogTrafficSource.getChannelId(),
			blogTrafficSource.getEventDate(),
			blogTrafficSource.getTrafficSourcesDomain(),
			blogTrafficSource.getUserId(), blogTrafficSource.getVariantId());
	}

	@Override
	protected List<AnalyticsEvent> pullAnalyticsEvents() throws Exception {
		List<AnalyticsEvent> analyticsEvents = super.pullAnalyticsEvents();

		Stream<AnalyticsEvent> stream = analyticsEvents.stream();

		return stream.filter(
			analyticsEvent -> {
				if (Objects.equals(analyticsEvent.getApplicationId(), "Blog") &&
					Objects.equals(analyticsEvent.getEventId(), "blogViewed") &&
					StringUtils.isNotBlank(
						MapUtil.getString(
							analyticsEvent.getContext(), "referrer"))) {

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
		AnalyticsEvent analyticsEvent, BlogTrafficSource blogTrafficSource) {

		blogTrafficSource.addTrafficSources(1);

		Map<String, String> eventProperties =
			analyticsEvent.getEventProperties();

		blogTrafficSource.setAssetId(eventProperties.get("entryId"));

		blogTrafficSource.setTrafficSourcesDomain(
			_getTrafficSourcesDomain(
				MapUtil.getString(analyticsEvent.getContext(), "referrer")));
	}

	private String _getTrafficSourcesDomain(String referrer) {
		Matcher matcher = _pattern.matcher(referrer);

		if (!matcher.matches()) {
			return "Unknown";
		}

		String domain = matcher.group(1);

		if (domain.contains("facebook.com")) {
			return "facebook";
		}

		if (domain.contains("linkedin.com")) {
			return "linkedin";
		}

		if (domain.contains("pinterest.com")) {
			return "pinterest";
		}

		if (domain.contains("twitter.com")) {
			return "twitter";
		}

		return domain;
	}

	private static final Log _log = LogFactory.getLog(
		BlogTrafficSourceNanite.class);

	private static final Pattern _pattern = Pattern.compile(
		"^https?://([^:/]+)");

	@MessageSubscriber.Autowired(channel = Channel.ANALYTICS_EVENTS_BLOG)
	private MessageSubscriber _messageSubscriber;

}