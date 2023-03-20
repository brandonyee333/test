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
			Objects.equals(_id, bqIdentityInterestPage._id) &&
			Objects.equals(_identityId, bqIdentityInterestPage._identityId) &&
			Objects.equals(_keyword, bqIdentityInterestPage._keyword) &&
			Objects.equals(_pageTitle, bqIdentityInterestPage._pageTitle) &&
			Objects.equals(_views, bqIdentityInterestPage._views)) {

			return true;
		}

		return false;
	}

	public String getCanonicalUrl() {
		return _canonicalUrl;
	}

	public String getId() {
		return _id;
	}

	public String getIdentityId() {
		return _identityId;
	}

	public String getKeyword() {
		return _keyword;
	}

	public String getPageTitle() {
		return _pageTitle;
	}

	public long getViews() {
		return _views;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_canonicalUrl, _id, _identityId, _keyword, _pageTitle, _views);
	}

	public void setCanonicalUrl(String canonicalUrl) {
		_canonicalUrl = canonicalUrl;
	}

	public void setId(String id) {
		_id = id;
	}

	public void setIdentityId(String identityId) {
		_identityId = identityId;
	}

	public void setKeyword(String keyword) {
		_keyword = keyword;
	}

	public void setPageTitle(String pageTitle) {
		_pageTitle = pageTitle;
	}

	public void setViews(long views) {
		_views = views;
	}

	private String _canonicalUrl;
	private String _id;
	private String _identityId;
	private String _keyword;
	private String _pageTitle;
	private long _views;

}