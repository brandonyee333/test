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

import java.io.Serializable;

import java.net.URI;

import java.nio.charset.StandardCharsets;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriUtils;

/**
 * @author Matthew Kong
 */
public class Acquisition implements Serializable {

	public Acquisition() {
	}

	public Acquisition(String referrer, String url) {
		UriComponentsBuilder urlUriComponentsBuilder =
			UriComponentsBuilder.fromUriString(url);

		UriComponents urlUriComponents = urlUriComponentsBuilder.build();

		MultiValueMap<String, String> queryParams =
			urlUriComponents.getQueryParams();

		_campaign = _decode(queryParams.getFirst("utm_campaign"));
		_content = _decode(queryParams.getFirst("utm_content"));
		_medium = _decode(queryParams.getFirst("utm_medium"));

		try {
			URI uri = new URI(referrer);

			_referrer = uri.getHost();
		}
		catch (Exception e) {
			throw new IllegalArgumentException(e);
		}

		_source = _decode(queryParams.getFirst("utm_source"));
		_term = _decode(queryParams.getFirst("utm_term"));
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (!(obj instanceof Acquisition)) {
			return false;
		}

		Acquisition acquisition = (Acquisition)obj;

		if (Objects.equals(_campaign, acquisition._campaign) &&
			Objects.equals(_content, acquisition._content) &&
			Objects.equals(_medium, acquisition._medium) &&
			Objects.equals(_source, acquisition._source) &&
			Objects.equals(_term, acquisition._term)) {

			return true;
		}

		return false;
	}

	public String getCampaign() {
		return _campaign;
	}

	public String getChannel() {
		if (Objects.equals(_medium, "affiliate")) {
			return "affiliates";
		}
		else if (Objects.isNull(_medium)) {
			return "direct";
		}
		else if (Objects.equals(_medium, "banner") ||
				 Objects.equals(_medium, "cpm") ||
				 Objects.equals(_medium, "display")) {

			return "display";
		}
		else if (Objects.equals(_medium, "email")) {
			return "email";
		}
		else if (Objects.equals(_medium, "organic")) {
			return "organic";
		}
		else if (Objects.equals(_medium, "content-text") ||
				 Objects.equals(_medium, "cpa") ||
				 Objects.equals(_medium, "cpp") ||
				 Objects.equals(_medium, "cpv")) {

			return "other advertising";
		}
		else if (Objects.equals(_medium, "cpc") ||
				 Objects.equals(_medium, "paidsearch") ||
				 Objects.equals(_medium, "ppc")) {

			return "paid search";
		}
		else if (Objects.equals(_medium, "referral")) {
			return "referral";
		}
		else if (Objects.equals(_medium, "sm") ||
				 Objects.equals(_medium, "social") ||
				 Objects.equals(_medium, "social media") ||
				 Objects.equals(_medium, "social network") ||
				 Objects.equals(_medium, "social-media") ||
				 Objects.equals(_medium, "social-network")) {

			return "social";
		}

		return "other";
	}

	public String getContent() {
		return _content;
	}

	public String getMedium() {
		if (!StringUtils.isEmpty(_medium)) {
			return _medium;
		}

		if (!StringUtils.isEmpty(_referrer)) {
			return "referral";
		}

		return null;
	}

	public String getSource() {
		if (!StringUtils.isEmpty(_source)) {
			return _source;
		}

		if (!StringUtils.isEmpty(_referrer)) {
			return _referrer;
		}

		return null;
	}

	public String getTerm() {
		return _term;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_campaign, _content, _medium, _source, _term);
	}

	public void setCampaign(String campaign) {
		_campaign = campaign;
	}

	public void setContent(String content) {
		_content = content;
	}

	public void setMedium(String medium) {
		_medium = medium;
	}

	public void setSource(String source) {
		_source = source;
	}

	public void setTerm(String term) {
		_term = term;
	}

	private String _decode(String value) {
		if (Objects.isNull(value)) {
			return null;
		}

		try {
			return UriUtils.decode(value, StandardCharsets.UTF_8.name());
		}
		catch (Exception e) {
			return null;
		}
	}

	private String _campaign;
	private String _content;
	private String _medium;
	private String _referrer;
	private String _source;
	private String _term;

}