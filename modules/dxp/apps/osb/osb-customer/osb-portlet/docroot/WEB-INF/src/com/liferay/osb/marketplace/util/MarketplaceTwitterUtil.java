/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.marketplace.util;

import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.osb.model.AppEntry;
import com.liferay.osb.model.AppVersion;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.OSBPortletKeys;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Contact;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.Portal;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import twitter4j.conf.ConfigurationBuilder;

/**
 * @author Douglas Wong
 */
public class MarketplaceTwitterUtil {

	public static final int TWITTER_CHARACTER_LIMIT = 140;

	public static final int TWITTER_URL_LENGTH = 22;

	public static void sendNewAppEntryTweet(AppEntry appEntry) {
		try {
			StringBundler sb = new StringBundler(17);

			sb.append("Now Available!");

			setAppEntryTweetBody(sb, appEntry);

			sendTweet(sb.toString());
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	public static void sendTweet(String tweet) {
		try {
			Twitter twitter = getTwitter();

			if (_log.isInfoEnabled()) {
				_log.info("Twitter status update message: " + tweet);
			}

			twitter.updateStatus(tweet);
		}
		catch (TwitterException te) {
			_log.error(te, te);
		}
	}

	public static void sendUpdatedAppEntryTweet(AppEntry appEntry) {
		try {
			AppVersion appVersion = appEntry.getApprovedAppVersion();

			StringBundler sb = new StringBundler(17);

			sb.append("Recently Updated:");

			setAppEntryTweetBody(sb, appEntry);

			sendTweet(sb.toString());
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	protected static Twitter getTwitter() {
		ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();

		if (PortletPropsValues.DEVELOPER_MODE_ENABLED) {
			configurationBuilder.setDebugEnabled(true);
		}

		configurationBuilder.setOAuthAccessToken(
			PortletPropsValues.MARKETPLACE_TWITTER_ACCESS_TOKEN);
		configurationBuilder.setOAuthAccessTokenSecret(
			PortletPropsValues.MARKETPLACE_TWITTER_ACCESS_TOKEN_SECRET);
		configurationBuilder.setOAuthConsumerKey(
			PortletPropsValues.MARKETPLACE_TWITTER_CONSUMER_KEY);
		configurationBuilder.setOAuthConsumerSecret(
			PortletPropsValues.MARKETPLACE_TWITTER_CONSUMER_SECRET);
		configurationBuilder.setUseSSL(true);

		TwitterFactory twitterFactory = new TwitterFactory(
			configurationBuilder.build());

		return twitterFactory.getInstance();
	}

	protected static void setAppEntryTweetBody(
			StringBundler sb, AppEntry appEntry)
		throws PortalException, SystemException {

		AppVersion appVersion = appEntry.getApprovedAppVersion();

		sb.append(StringPool.SPACE);
		sb.append(appEntry.getTitle());
		sb.append(StringPool.SPACE);
		sb.append(appVersion.getVersion());
		sb.append(StringPool.SPACE);
		sb.append(StringPool.OPEN_PARENTHESIS);
		sb.append(appEntry.getDeveloperEntryName());

		User user = UserLocalServiceUtil.getUser(appVersion.getUserId());

		Contact contact = user.getContact();

		String twitterSn = contact.getTwitterSn();

		if (Validator.isNotNull(twitterSn)) {
			sb.append(StringPool.SPACE);
			sb.append(StringPool.AT);
			sb.append(twitterSn);
		}

		sb.append(StringPool.CLOSE_PARENTHESIS);

		int length =
			sb.length() + TWITTER_URL_LENGTH +
				(PortletPropsValues.MARKETPLACE_TWITTER_HASHTAG.length() + 2);

		if (!PortletPropsValues.DEVELOPER_MODE_ENABLED &&
			(length <= TWITTER_CHARACTER_LIMIT)) {

			sb.append(StringPool.SPACE);

			String fullLayoutURL = PortalUtil.getLayoutFullURL(
				OSBConstants.GROUP_GUEST_ID, OSBPortletKeys.OSB_MARKETPLACE);

			String appEntryURL =
				fullLayoutURL + Portal.FRIENDLY_URL_SEPARATOR +
					"mp/application/" + appEntry.getAppEntryId();

			sb.append(appEntryURL);
		}

		sb.append(StringPool.SPACE);
		sb.append(StringPool.POUND);
		sb.append(PortletPropsValues.MARKETPLACE_TWITTER_HASHTAG);
	}

	private static Log _log = LogFactoryUtil.getLog(
		MarketplaceTwitterUtil.class);

}