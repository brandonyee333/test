/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.ip.geocoder;

import com.maxmind.geoip.Location;
import com.maxmind.geoip.regionName;

/**
 * @author Brian Wing Shun Chan
 */
public class IPInfo {

	public IPInfo() {
	}

	public IPInfo(String ipAddress, Location location) {
		_ipAddress = ipAddress;

		if (location != null) {
			_city = location.city;
			_countryCode = location.countryCode;
			_countryName = location.countryName;
			_latitude = location.latitude;
			_longitude = location.longitude;
			_postalCode = location.postalCode;

			_regionCode = location.region;

			_regionName = regionName.regionNameByCode(
				_countryCode, _regionCode);
		}
	}

	public String getCity() {
		return _city;
	}

	public String getCountryCode() {
		return _countryCode;
	}

	public String getCountryName() {
		return _countryName;
	}

	public String getIpAddress() {
		return _ipAddress;
	}

	public float getLatitude() {
		return _latitude;
	}

	public float getLongitude() {
		return _longitude;
	}

	public String getPostalCode() {
		return _postalCode;
	}

	public String getRegionCode() {
		return _regionCode;
	}

	public String getRegionName() {
		return _regionName;
	}

	public void setCity(String city) {
		_city = city;
	}

	public void setCountryCode(String countryCode) {
		_countryCode = countryCode;
	}

	public void setCountryName(String countryName) {
		_countryName = countryName;
	}

	public void setIpAddress(String ipAddress) {
		_ipAddress = ipAddress;
	}

	public void setLatitude(float latitude) {
		_latitude = latitude;
	}

	public void setLongitude(float longitude) {
		_longitude = longitude;
	}

	public void setPostalCode(String postalCode) {
		_postalCode = postalCode;
	}

	public void setRegionCode(String regionCode) {
		_regionCode = regionCode;
	}

	public void setRegionName(String regionName) {
		_regionName = regionName;
	}

	private String _city;
	private String _countryCode;
	private String _countryName;
	private String _ipAddress;
	private float _latitude;
	private float _longitude;
	private String _postalCode;
	private String _regionCode;
	private String _regionName;

}