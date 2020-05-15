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

import com.liferay.osb.asah.backend.constants.DataConstants;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Adolfo Pérez
 */
public class Geolocation {

	public static Geolocation any() {
		return _GEOLOCATION_ANY;
	}

	public static Geolocation country(String country) {
		return region(country, DataConstants.ANY);
	}

	public static Geolocation region(String country, String region) {
		if (StringUtils.isEmpty(country)) {
			country = DataConstants.ANY;
		}

		if (StringUtils.isEmpty(region)) {
			region = DataConstants.ANY;
		}

		return new Geolocation(country, region);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Geolocation)) {
			return false;
		}

		Geolocation geolocation = (Geolocation)obj;

		if (Objects.equals(_country, geolocation._country) &&
			Objects.equals(_region, geolocation._region)) {

			return true;
		}

		return false;
	}

	public String getCountry() {
		return _country;
	}

	public String getRegion() {
		return _region;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_country, _region);
	}

	private Geolocation(String country, String region) {
		_country = country;
		_region = region;
	}

	private static final Geolocation _GEOLOCATION_ANY = new Geolocation(
		DataConstants.ANY, DataConstants.ANY);

	private final String _country;
	private final String _region;

}