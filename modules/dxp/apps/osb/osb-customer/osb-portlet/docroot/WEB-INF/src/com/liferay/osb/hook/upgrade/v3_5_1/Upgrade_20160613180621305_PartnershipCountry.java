/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.hook.upgrade.v3_5_1;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.portal.kernel.util.StringBundler;

/**
 * @author Enoch Chu
 */
public class Upgrade_20160613180621305_PartnershipCountry
	extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20160613180621305L;
	}

	protected void addPartnershipCountry(int countryId, String dossieraName)
		throws Exception {

		StringBundler sb = new StringBundler(6);

		sb.append("insert into OSBPartnership_PartnershipCountry ");
		sb.append("(countryId, dossieraName) values (");
		sb.append(String.valueOf(countryId));
		sb.append(", '");
		sb.append(dossieraName);
		sb.append("')");

		runSQL(sb.toString());
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (tableHasColumn(
				"OSBPartnership_PartnershipCountry", "dossieraName")) {

			return;
		}

		runSQL(
			"alter table OSBPartnership_PartnershipCountry add column " +
				"dossieraName VARCHAR(75)");

		addPartnershipCountry(0, "Global");
		addPartnershipCountry(20, "Afghanistan");
		addPartnershipCountry(21, "Albania");
		addPartnershipCountry(22, "Algeria");
		addPartnershipCountry(24, "Andorra");
		addPartnershipCountry(25, "Angola");
		addPartnershipCountry(29, "Argentina");
		addPartnershipCountry(30, "Armenia");
		addPartnershipCountry(31, "Aruba");
		addPartnershipCountry(32, "Australia");
		addPartnershipCountry(33, "Austria");
		addPartnershipCountry(34, "Azerbaijan");
		addPartnershipCountry(35, "Bahamas");
		addPartnershipCountry(37, "Bangladesh");
		addPartnershipCountry(38, "Barbados");
		addPartnershipCountry(39, "Belarus");
		addPartnershipCountry(40, "Belgium");
		addPartnershipCountry(41, "Belize");
		addPartnershipCountry(43, "Bermuda");
		addPartnershipCountry(45, "Bolivia, Plurinational State of");
		addPartnershipCountry(46, "Bosnia and Herzegovina");
		addPartnershipCountry(47, "Botswana");
		addPartnershipCountry(48, "Brazil");
		addPartnershipCountry(51, "Bulgaria");
		addPartnershipCountry(55, "Cambodia");
		addPartnershipCountry(56, "Cameroon");
		addPartnershipCountry(1, "Canada");
		addPartnershipCountry(58, "Cayman Islands");
		addPartnershipCountry(59, "Central African Republic");
		addPartnershipCountry(61, "Chile");
		addPartnershipCountry(2, "China");
		addPartnershipCountry(64, "Colombia");
		addPartnershipCountry(66, "Congo");
		addPartnershipCountry(67, "Congo, the Democratic Republic of the");
		addPartnershipCountry(69, "Costa Rica");
		addPartnershipCountry(70, "Croatia");
		addPartnershipCountry(73, "Czech Republic");
		addPartnershipCountry(74, "Denmark");
		addPartnershipCountry(77, "Dominican Republic");
		addPartnershipCountry(78, "Ecuador");
		addPartnershipCountry(79, "Egypt");
		addPartnershipCountry(80, "El Salvador");
		addPartnershipCountry(82, "Eritrea");
		addPartnershipCountry(83, "Estonia");
		addPartnershipCountry(84, "Ethiopia");
		addPartnershipCountry(88, "Finland");
		addPartnershipCountry(3, "France");
		addPartnershipCountry(89, "French Guiana");
		addPartnershipCountry(93, "Georgia");
		addPartnershipCountry(4, "Germany");
		addPartnershipCountry(94, "Ghana");
		addPartnershipCountry(96, "Greece");
		addPartnershipCountry(101, "Guatemala");
		addPartnershipCountry(102, "Guinea");
		addPartnershipCountry(104, "Guyana");
		addPartnershipCountry(105, "Haiti");
		addPartnershipCountry(106, "Honduras");
		addPartnershipCountry(6, "Hungary");
		addPartnershipCountry(107, "Iceland");
		addPartnershipCountry(108, "India");
		addPartnershipCountry(109, "Indonesia");
		addPartnershipCountry(110, "Iran, Islamic Republic of");
		addPartnershipCountry(111, "Iraq");
		addPartnershipCountry(112, "Ireland");
		addPartnershipCountry(7, "Israel");
		addPartnershipCountry(8, "Italy");
		addPartnershipCountry(114, "Jamaica");
		addPartnershipCountry(9, "Japan");
		addPartnershipCountry(115, "Jordan");
		addPartnershipCountry(116, "Kazakhstan");
		addPartnershipCountry(117, "Kenya");
		addPartnershipCountry(10, "Korea, Republic of");
		addPartnershipCountry(119, "Kuwait");
		addPartnershipCountry(121, "Kyrgyzstan");
		addPartnershipCountry(123, "Latvia");
		addPartnershipCountry(124, "Lebanon");
		addPartnershipCountry(127, "Libyan Arab Jamahiriya");
		addPartnershipCountry(129, "Lithuania");
		addPartnershipCountry(130, "Luxembourg");
		addPartnershipCountry(131, "Macao");
		addPartnershipCountry(132, "Macedonia");
		addPartnershipCountry(134, "Malawi");
		addPartnershipCountry(135, "Malaysia");
		addPartnershipCountry(136, "Maldives");
		addPartnershipCountry(138, "Malta");
		addPartnershipCountry(144, "Mexico");
		addPartnershipCountry(146, "Moldova, Republic of");
		addPartnershipCountry(147, "Monaco");
		addPartnershipCountry(148, "Mongolia");
		addPartnershipCountry(150, "Morocco");
		addPartnershipCountry(151, "Mozambique");
		addPartnershipCountry(53, "Myanmar");
		addPartnershipCountry(153, "Namibia");
		addPartnershipCountry(155, "Nepal");
		addPartnershipCountry(11, "Netherlands");
		addPartnershipCountry(158, "New Zealand");
		addPartnershipCountry(159, "Nicaragua");
		addPartnershipCountry(161, "Nigeria");
		addPartnershipCountry(164, "Norway");
		addPartnershipCountry(165, "Oman");
		addPartnershipCountry(166, "Pakistan");
		addPartnershipCountry(168, "Palestinian Territory, Occupied");
		addPartnershipCountry(169, "Panama");
		addPartnershipCountry(171, "Paraguay");
		addPartnershipCountry(172, "Peru");
		addPartnershipCountry(173, "Philippines");
		addPartnershipCountry(174, "Poland");
		addPartnershipCountry(12, "Portugal");
		addPartnershipCountry(178, "Romania");
		addPartnershipCountry(13, "Russian Federation");
		addPartnershipCountry(187, "Saudi Arabia");
		addPartnershipCountry(188, "Senegal");
		addPartnershipCountry(189, "Serbia");
		addPartnershipCountry(191, "Sierra Leone");
		addPartnershipCountry(14, "Singapore");
		addPartnershipCountry(192, "Slovakia");
		addPartnershipCountry(193, "Slovenia");
		addPartnershipCountry(195, "Somalia");
		addPartnershipCountry(196, "South Africa");
		addPartnershipCountry(15, "Spain");
		addPartnershipCountry(197, "Sri Lanka");
		addPartnershipCountry(199, "Suriname");
		addPartnershipCountry(200, "Swaziland");
		addPartnershipCountry(201, "Sweden");
		addPartnershipCountry(202, "Switzerland");
		addPartnershipCountry(204, "Taiwan, ROC");
		addPartnershipCountry(206, "Tanzania, United Republic of");
		addPartnershipCountry(207, "Thailand");
		addPartnershipCountry(211, "Tunisia");
		addPartnershipCountry(16, "Turkey");
		addPartnershipCountry(212, "Turkmenistan");
		addPartnershipCountry(215, "Uganda");
		addPartnershipCountry(216, "Ukraine");
		addPartnershipCountry(217, "United Arab Emirates");
		addPartnershipCountry(18, "United Kingdom");
		addPartnershipCountry(19, "United States");
		addPartnershipCountry(218, "Uruguay");
		addPartnershipCountry(219, "Uzbekistan");
		addPartnershipCountry(222, "Venezuela, Bolivarian Republic of");
		addPartnershipCountry(17, "Viet Nam");
		addPartnershipCountry(49, "Virgin Islands, British");
		addPartnershipCountry(225, "Yemen");
		addPartnershipCountry(227, "Zambia");
		addPartnershipCountry(228, "Zimbabwe");
	}

}