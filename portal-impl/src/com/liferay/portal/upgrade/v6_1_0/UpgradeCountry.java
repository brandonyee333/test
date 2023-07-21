/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.upgrade.v6_1_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Kenneth Chang
 */
public class UpgradeCountry extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		for (String name : _NAMES) {
			runSQL(
				"update Country set zipRequired = [$FALSE$] where name = '" +
					name + "'");
		}
	}

	private static final String[] _NAMES = {
		"Angola", "Antigua", "Aruba", "Bahamas", "Belize", "Benin", "Botswana",
		"Burkina Faso", "Burundi", "Central African Republic", "Comoros",
		"Republic of Congo", "Democratic Republic of Congo", "Cook Islands",
		"Djibouti", "Dominica", "Equatorial Guinea", "Eritrea", "Fiji Islands",
		"Gambia", "Ghana", "Grenada", "Guinea", "Guyana", "Ireland", "Kiribati",
		"North Korea", "Macau", "Malawi", "Mali", "Mauritania", "Mauritius",
		"Montserrat", "Nauru", "Niue", "Qatar", "Rwanda", "St. Kitts",
		"St. Lucia", "Sao Tome & Principe", "Seychelles", "Sierra Leone",
		"Solomon Islands", "Somalia", "Suriname", "Syria", "Tanzania", "Tonga",
		"Trinidad & Tobago", "Tuvalu", "Uganda", "United Arab Emirates",
		"Vanuatu", "Yemen", "Zimbabwe"
	};

}