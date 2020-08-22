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

package com.liferay.osb.asah.common.model;

import java.util.Objects;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Matthew Kong
 */
public class PageAcquisition extends Acquisition {

	public PageAcquisition() {
	}

	public PageAcquisition(String referrer, String url) {
		super(referrer, url);
	}

	@Override
	public String getChannel() {
		String channel = super.getChannel();

		if (Objects.equals(channel, "organic") ||
			ArrayUtils.contains(_SEARCH_HOST_NAMES, referrer)) {

			return "organic";
		}

		if (Objects.equals(channel, "paid search") ||
			!StringUtils.isBlank(decode(queryParams.getFirst("gclid"))) ||
			ArrayUtils.contains(_PAID_HOST_NAMES, referrer)) {

			return "paid";
		}

		if (Objects.equals(channel, "social") ||
			ArrayUtils.contains(_SOCIAL_HOST_NAMES, referrer)) {

			return "social";
		}

		if (Objects.equals(channel, "referral") ||
			!StringUtils.isBlank(referrer)) {

			return "referral";
		}

		return "direct";
	}

	private static final String[] _PAID_HOST_NAMES = {
		"www.googleadservices.com"
	};

	private static final String[] _SEARCH_HOST_NAMES = {
		"duckduckgo.com", "www.ask.com", "www.baidu.com", "www.bing.com",
		"www.google.com", "www.yahoo.com", "www.yandex.com"
	};

	private static final String[] _SOCIAL_HOST_NAMES = {
		"twitter.com", "www.facebook.com", "www.instagram.com",
		"www.linkedin.com", "www.pinterest.com", "www.snapchat.com",
		"www.tiktok.com", "www.youtube.com"
	};

}