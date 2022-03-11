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

package com.liferay.osb.asah.dataflow.ingestion.event.ip.geocoder;

import com.maxmind.geoip.Location;
import com.maxmind.geoip.regionName;

/**
 * @author Inácio Nery
 */
public class IPInfo {

	public static final IPInfo LOCAL_NETWORK = new IPInfo("Local Network");

	public IPInfo(Location location) {
		_city = location.city;
		_country = location.countryName;
		_region = regionName.regionNameByCode(
			location.countryCode, location.region);
	}

	public String getCity() {
		return _city;
	}

	public String getCountry() {
		return _country;
	}

	public String getRegion() {
		return _region;
	}

	private IPInfo(String location) {
		_city = location;
		_country = location;
		_region = location;
	}

	private final String _city;
	private final String _country;
	private final String _region;

}