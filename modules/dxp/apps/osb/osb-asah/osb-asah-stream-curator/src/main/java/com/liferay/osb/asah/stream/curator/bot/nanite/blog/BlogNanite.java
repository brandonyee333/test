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

package com.liferay.osb.asah.stream.curator.bot.nanite.blog;

import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageSubscriber;
import com.liferay.osb.asah.common.model.AnalyticsEvent;
import com.liferay.osb.asah.stream.curator.bot.nanite.BaseNanite;
import com.liferay.osb.asah.stream.curator.bot.nanite.util.NaniteUtil;
import com.liferay.osb.asah.stream.curator.model.blog.Blog;

import java.util.Date;
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

import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class BlogNanite extends BaseNanite<Blog> {

	@Override
	public String getCollectionName() {
		return "blogs";
	}

	@Override
	protected BinaryOperator<Blog> getBinaryOperator() {
		return (Blog oldBlog, Blog newBlog) -> {
			mergeModels(oldBlog, newBlog);

			oldBlog.addComments(newBlog.getComments());
			oldBlog.addClicks(newBlog.getClicks());
			oldBlog.addViews(newBlog.getViews());

			_setRatingScore(oldBlog, newBlog);
			_setReadTime(oldBlog, newBlog);

			return oldBlog;
		};
	}

	@Override
	protected Predicate<Blog> getFilterPredicate() {
		return blog -> StringUtils.isNotBlank(blog.getAssetId());
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
	protected Supplier<Blog> getModelSupplier() {
		return Blog::new;
	}

	@Override
	protected Function<Blog, String> getPrimaryKeyGeneratorFunction() {
		return blog -> digest(
			blog.getAssetId(), blog.getChannelId(), blog.getEventDate(),
			blog.getUserId(), blog.getVariantId());
	}

	@Override
	protected List<AnalyticsEvent> pullAnalyticsEvents() throws Exception {
		List<AnalyticsEvent> analyticsEvents = super.pullAnalyticsEvents();

		Stream<AnalyticsEvent> stream = analyticsEvents.stream();

		return stream.filter(
			analyticsEvent -> {
				if (Objects.equals(
						analyticsEvent.getApplicationId(), "SocialBookmarks")) {

					return false;
				}

				return true;
			}
		).collect(
			Collectors.toList()
		);
	}

	@Override
	protected void setModelCustomProperties(
		AnalyticsEvent analyticsEvent, Blog blog) {

		String eventId = analyticsEvent.getEventId();

		Map<String, String> eventProperties =
			analyticsEvent.getEventProperties();

		if (Objects.equals(eventId, "VOTE")) {
			blog.setRatings(1);

			blog.setAssetId(eventProperties.get("classPK"));
			blog.setRatingsScore(
				Float.parseFloat(eventProperties.get("score")));
		}
		else if (Objects.equals(eventId, "blogClicked")) {
			blog.addClicks(1);

			blog.setAssetId(eventProperties.get("entryId"));
		}
		else if (Objects.equals(eventId, "blogDepthReached")) {
			blog.setAssetId(eventProperties.get("entryId"));

			if (StringUtils.isNotEmpty(eventProperties.get("depth"))) {
				blog.setReadPercentile(
					NaniteUtil.getReadPercentile(eventProperties.get("depth")));
			}
		}
		else if (Objects.equals(eventId, "blogViewed")) {
			blog.addViews(1);

			blog.setAssetId(eventProperties.get("entryId"));
		}
		else if (Objects.equals(eventId, "posted")) {
			blog.addComments(1);

			blog.setAssetId(eventProperties.get("classPK"));
		}

		blog.setFirstEventDate(analyticsEvent.getEventDate());
	}

	private void _setRatingScore(Blog oldBlog, Blog newBlog) {
		if (newBlog.getRatings() == 0) {
			return;
		}

		if (newBlog.getRatingsScore() < 0) {
			oldBlog.setRatings(0);
			oldBlog.setRatingsScore(0);
		}
		else {
			oldBlog.setRatings(1);
			oldBlog.setRatingsScore(newBlog.getRatingsScore());
		}
	}

	private void _setReadTime(Blog oldBlog, Blog newBlog) {
		Date firstEventDate = oldBlog.getFirstEventDate();

		if ((firstEventDate == null) ||
			(firstEventDate.compareTo(newBlog.getFirstEventDate()) > 0)) {

			firstEventDate = newBlog.getFirstEventDate();

			oldBlog.setFirstEventDate(firstEventDate);
		}

		Date lastEventDate = oldBlog.getLastEventDate();

		if (newBlog.getReadPercentile() > oldBlog.getReadPercentile()) {
			oldBlog.setReadPercentile(newBlog.getReadPercentile());
		}

		oldBlog.setReadTime(lastEventDate.getTime() - firstEventDate.getTime());
	}

	private static final Log _log = LogFactory.getLog(BlogNanite.class);

	@MessageSubscriber.Autowired(channel = Channel.ANALYTICS_EVENTS_BLOG)
	private MessageSubscriber _messageSubscriber;

}