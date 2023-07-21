/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.ip.geocoder.internal;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Julio Camarero
 */
@ExtendedObjectClassDefinition(category = "foundation")
@Meta.OCD(
	id = "com.liferay.ip.geocoder.internal.IPGeocoderConfiguration",
	localization = "content/Language",
	name = "ip-geocoder-service-configuration-name"
)
public interface IPGeocoderConfiguration {

	@Meta.AD(
		description = "file-path-description", name = "file-path",
		required = false
	)
	public String filePath();

	@Meta.AD(
		deflt = "http://cdn.mirrors.liferay.com/geolite.maxmind.com/download/geoip/database/GeoLiteCity.dat.xz",
		description = "file-url-description", name = "file-url",
		required = false
	)
	public String fileURL();

}