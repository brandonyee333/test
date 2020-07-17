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

package com.liferay.osb.asah.stream.curator.bot.nanite.blog.social.share;

import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageSubscriber;
import com.liferay.osb.asah.common.model.AnalyticsEvent;
import com.liferay.osb.asah.common.util.MapUtil;
import com.liferay.osb.asah.stream.curator.bot.nanite.BaseNanite;
import com.liferay.osb.asah.stream.curator.model.blog.BlogSocialShare;

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
public class BlogSocialShareNanite extends BaseNanite<BlogSocialShare> {

	@Override
	public String getCollectionName() {
		return "blog-social-shares";
	}

	@Override
	protected BinaryOperator<BlogSocialShare> getBinaryOperator() {
		return (BlogSocialShare oldBlogSocialShare,
				BlogSocialShare newBlogSocialShare) -> {

			mergeModels(oldBlogSocialShare, newBlogSocialShare);

			oldBlogSocialShare.addSocialShares(
				newBlogSocialShare.getSocialShares());

			return oldBlogSocialShare;
		};
	}

	@Override
	protected Predicate<BlogSocialShare> getFilterPredicate() {
		return blogSocialShare -> StringUtils.isNotBlank(
			blogSocialShare.getAssetId());
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
	protected Supplier<BlogSocialShare> getModelSupplier() {
		return BlogSocialShare::new;
	}

	@Override
	protected Function<BlogSocialShare, String>
		getPrimaryKeyGeneratorFunction() {

		return blogSocialShare -> digest(
			blogSocialShare.getAssetId(), blogSocialShare.getChannelId(),
			blogSocialShare.getEventDate(),
			blogSocialShare.getSocialSharesDomain(),
			blogSocialShare.getUserId(), blogSocialShare.getVariantId());
	}

	@Override
	protected List<AnalyticsEvent> pullAnalyticsEvents() throws Exception {
		List<AnalyticsEvent> analyticsEvents = super.pullAnalyticsEvents();

		Stream<AnalyticsEvent> stream = analyticsEvents.stream();

		return stream.filter(
			analyticsEvent -> {
				if (Objects.equals(
						analyticsEvent.getApplicationId(), "SocialBookmarks") &&
					Objects.equals(analyticsEvent.getEventId(), "shared") &&
					Objects.equals(
						MapUtil.getString(
							analyticsEvent.getEventProperties(), "className"),
						"com.liferay.blogs.model.BlogsEntry")) {

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
		AnalyticsEvent analyticsEvent, BlogSocialShare blogSocialShare) {

		blogSocialShare.addSocialShares(1);

		Map<String, String> eventProperties =
			analyticsEvent.getEventProperties();

		blogSocialShare.setAssetId(eventProperties.get("classPK"));
		blogSocialShare.setSocialSharesDomain(eventProperties.get("type"));
	}

	private static final Log _log = LogFactory.getLog(
		BlogSocialShareNanite.class);

	@MessageSubscriber.Autowired(channel = Channel.ANALYTICS_EVENTS_BLOG)
	private MessageSubscriber _messageSubscriber;

}