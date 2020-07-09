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

package com.liferay.osb.asah.backend.ext.seo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

/**
 * @author David Arques
 */
public class TrafficSource {

	public TrafficSource() {
	}

	public TrafficSource(
		List<CountrySearchKeywords> countrySearchKeywordsList, String name,
		long trafficAmount, double trafficShare) {

		_countrySearchKeywordsList = countrySearchKeywordsList;
		_name = name;
		_trafficAmount = trafficAmount;
		_trafficShare = trafficShare;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TrafficSource)) {
			return false;
		}

		TrafficSource trafficSource = (TrafficSource)obj;

		if (Objects.equals(
				_countrySearchKeywordsList,
				trafficSource._countrySearchKeywordsList) &&
			Objects.equals(_name, trafficSource._name) &&
			Objects.equals(_trafficAmount, trafficSource._trafficAmount) &&
			Objects.equals(_trafficShare, trafficSource._trafficShare)) {

			return true;
		}

		return false;
	}

	@JsonProperty("countryKeywords")
	public List<CountrySearchKeywords> getCountrySearchKeywordsList() {
		return _countrySearchKeywordsList;
	}

	public String getName() {
		return _name;
	}

	public long getTrafficAmount() {
		return _trafficAmount;
	}

	public double getTrafficShare() {
		return _trafficShare;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_countrySearchKeywordsList, _name, _trafficAmount, _trafficShare);
	}

	public void setCountrySearchKeywordsList(
		List<CountrySearchKeywords> countrySearchKeywordsList) {

		_countrySearchKeywordsList = countrySearchKeywordsList;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setTrafficAmount(long trafficAmount) {
		_trafficAmount = trafficAmount;
	}

	public void setTrafficShare(double trafficShare) {
		_trafficShare = trafficShare;
	}

	private List<CountrySearchKeywords> _countrySearchKeywordsList;
	private String _name;
	private long _trafficAmount;
	private double _trafficShare;

}