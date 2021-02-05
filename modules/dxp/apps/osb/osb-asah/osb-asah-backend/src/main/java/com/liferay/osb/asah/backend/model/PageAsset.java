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

package com.liferay.osb.asah.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.liferay.osb.asah.common.graphql.GraphQLProperty;
import com.liferay.osb.asah.common.graphql.GraphQLType;

import java.util.List;
import java.util.Objects;

/**
 * @author Marcellus Tavares
 */
@GraphQLType
public class PageAsset {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PageAsset)) {
			return false;
		}

		PageAsset pageAsset = (PageAsset)obj;

		if (Objects.equals(_canonicalUrl, pageAsset._canonicalUrl) &&
			Objects.equals(_description, pageAsset._description) &&
			Objects.equals(_keywords, pageAsset._keywords) &&
			Objects.equals(_id, pageAsset._id) &&
			Objects.equals(_title, pageAsset._title) &&
			Objects.equals(_url, pageAsset._url)) {

			return true;
		}

		return false;
	}

	public String getCanonicalUrl() {
		return _canonicalUrl;
	}

	public String getDescription() {
		return _description;
	}

	public String getId() {
		return _id;
	}

	public List<Keyword> getKeywords() {
		return _keywords;
	}

	@JsonProperty("name")
	public String getTitle() {
		return _title;
	}

	@GraphQLProperty("url")
	public String getURL() {
		return _url;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_canonicalUrl, _description, _keywords, _id, _title, _url);
	}

	public void setCanonicalUrl(String canonicalUrl) {
		_canonicalUrl = canonicalUrl;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public void setId(String id) {
		_id = id;
	}

	public void setKeywords(List<Keyword> keywords) {
		_keywords = keywords;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public void setURL(String url) {
		_url = url;
	}

	private String _canonicalUrl;
	private String _description;
	private String _id;
	private List<Keyword> _keywords;
	private String _title;
	private String _url;

}