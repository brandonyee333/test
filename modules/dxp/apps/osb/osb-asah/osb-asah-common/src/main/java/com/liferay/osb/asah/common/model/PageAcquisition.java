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

import java.net.URI;

import java.util.Objects;

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
			_contains(_SEARCH_HOST_NAMES, referrer)) {

			return "organic";
		}

		if (Objects.equals(channel, "paid search") ||
			!StringUtils.isBlank(decode(queryParams.getFirst("gclid"))) ||
			_contains(_PAID_HOST_NAMES, referrer)) {

			return "paid";
		}

		if (Objects.equals(channel, "social") ||
			_contains(_SOCIAL_HOST_NAMES, referrer)) {

			return "social";
		}

		if (StringUtils.isBlank(referrer)) {
			return "direct";
		}

		if (Objects.equals(channel, "referral") || !_isInternalReferrer()) {
			return "referral";
		}

		return null;
	}

	private boolean _contains(String[] array, String referrer) {
		if (StringUtils.isBlank(referrer)) {
			return false;
		}

		referrer = StringUtils.removeEnd(referrer, "/");

		for (String value : array) {
			if (referrer.endsWith(value)) {
				return true;
			}
		}

		return false;
	}

	private boolean _isInternalReferrer() {
		try {
			URI uri = new URI(url);

			if (StringUtils.equals(uri.getHost(), referrer)) {
				return true;
			}
		}
		catch (Exception e) {
			throw new IllegalArgumentException(e);
		}

		return false;
	}

	private static final String[] _PAID_HOST_NAMES = {"googleadservices.com"};

	private static final String[] _SEARCH_HOST_NAMES = {
		"ask.com", "baidu.com", "bing.com", "duckduckgo.com", "google.com",
		"yahoo.com", "yandex.com"
	};

	private static final String[] _SOCIAL_HOST_NAMES = {
		"facebook.com", "instagram.com", "linkedin.com", "pinterest.com",
		"snapchat.com", "tiktok.com", "twitter.com", "youtube.com"
	};

}