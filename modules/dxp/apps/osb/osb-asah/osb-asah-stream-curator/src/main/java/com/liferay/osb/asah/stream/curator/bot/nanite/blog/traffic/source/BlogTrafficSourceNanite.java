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

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.model.AnalyticsEvent;
import com.liferay.osb.asah.common.util.MapUtil;
import com.liferay.osb.asah.stream.curator.bot.nanite.BaseNanite;
import com.liferay.osb.asah.stream.curator.model.blog.BlogTrafficSource;

import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

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
	protected QueryBuilder getQueryBuilder() {
		return BoolQueryBuilderUtil.filter(
			QueryBuilders.termQuery("applicationId", "Blog")
		).filter(
			QueryBuilders.termQuery("eventId", "blogViewed")
		).mustNot(
			QueryBuilders.termQuery("context.referrer", "")
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

	private static String _getTrafficSourcesDomain(String referrer) {
		Matcher matcher = _pattern.matcher(referrer);

		if (!matcher.matches()) {
			return "Unknown";
		}

		String domain = matcher.group(1);

		if (domain.contains("facebook.com")) {
			return "facebook";
		}
		else if (domain.contains("linkedin.com")) {
			return "linkedin";
		}
		else if (domain.contains("pinterest.com")) {
			return "pinterest";
		}
		else if (domain.contains("twitter.com")) {
			return "twitter";
		}

		return domain;
	}

	private static final Log _log = LogFactory.getLog(
		BlogTrafficSourceNanite.class);

	private static final Pattern _pattern = Pattern.compile(
		"^https?://([^:/]+)");

}