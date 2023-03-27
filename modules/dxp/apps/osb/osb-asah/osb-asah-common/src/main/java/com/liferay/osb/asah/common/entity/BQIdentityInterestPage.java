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

package com.liferay.osb.asah.common.entity;

import com.liferay.osb.asah.common.util.BeanUtils;

import java.util.Map;
import java.util.Objects;

/**
 * @author Leslie Wong
 */
public class BQIdentityInterestPage {

	public BQIdentityInterestPage() {
	}

	public BQIdentityInterestPage(Map<String, Object> source) {
		BeanUtils.copyProperties(source, this);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BQIdentityInterestPage)) {
			return false;
		}

		BQIdentityInterestPage bqIdentityInterestPage =
			(BQIdentityInterestPage)obj;

		if (Objects.equals(
				_canonicalUrl, bqIdentityInterestPage._canonicalUrl) &&
			Objects.equals(_channelId, bqIdentityInterestPage._channelId) &&
			Objects.equals(_identityId, bqIdentityInterestPage._identityId) &&
			Objects.equals(_keyword, bqIdentityInterestPage._keyword) &&
			Objects.equals(_title, bqIdentityInterestPage._title) &&
			Objects.equals(_views, bqIdentityInterestPage._views)) {

			return true;
		}

		return false;
	}

	public String getCanonicalUrl() {
		return _canonicalUrl;
	}

	public long getChannelId() {
		return _channelId;
	}

	public String getIdentityId() {
		return _identityId;
	}

	public String getKeyword() {
		return _keyword;
	}

	public String getTitle() {
		return _title;
	}

	public long getViews() {
		return _views;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_canonicalUrl, _channelId, _identityId, _keyword, _title, _views);
	}

	public void setCanonicalUrl(String canonicalUrl) {
		_canonicalUrl = canonicalUrl;
	}

	public void setChannelId(long channelId) {
		_channelId = channelId;
	}

	public void setIdentityId(String identityId) {
		_identityId = identityId;
	}

	public void setKeyword(String keyword) {
		_keyword = keyword;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public void setViews(long views) {
		_views = views;
	}

	private String _canonicalUrl;
	private long _channelId;
	private String _identityId;
	private String _keyword;
	private String _title;
	private long _views;

}