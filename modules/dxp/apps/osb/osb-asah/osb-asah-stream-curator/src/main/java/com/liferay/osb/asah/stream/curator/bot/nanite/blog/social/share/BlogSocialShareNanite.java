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

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.model.AnalyticsEvent;
import com.liferay.osb.asah.stream.curator.bot.nanite.BaseNanite;
import com.liferay.osb.asah.stream.curator.model.blog.BlogSocialShare;

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
	protected QueryBuilder getQueryBuilder() {
		return BoolQueryBuilderUtil.filter(
			QueryBuilders.termQuery("applicationId", "shared")
		).filter(
			QueryBuilders.termQuery("eventId", "SocialBookmarks")
		).filter(
			QueryBuilders.termQuery(
				"eventProperties.className",
				"com.liferay.blogs.model.BlogsEntry")
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

}