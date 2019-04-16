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

package com.liferay.osb.hook.upgrade.v5_0_7;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.portal.kernel.util.StringBundler;

/**
 * @author Jenny Chen
 */
public class UpgradeCountry extends BaseUpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		long countryId = CounterLocalServiceUtil.increment();

		StringBundler sb = new StringBundler(5);

		sb.append("insert into Country(countryId, name, a2, a3, number_, ");
		sb.append("idd_, zipRequired, active_, mvccVersion) values (");
		sb.append(countryId);
		sb.append(", 'svalbard-and-jan-mayen', 'SJ', 'SJM', '744', '47', 0, ");
		sb.append("1, 0)");

		runSQL(sb.toString());

		runSQL(
			"update Country set name = 'antigua-and-barbuda' where a2 = 'AG'");
		runSQL(
			"update Country set name = 'bolivia-plurinational-state-of' " +
				"where a2 = 'BO'");
		runSQL(
			"update Country set name = 'bonaire-sint-eustatius-and-saba' " +
				"where a2 = 'BQ'");
		runSQL(
			"update Country set name = 'bosnia-and-herzegovina' where a2 = " +
				"'BA'");
		runSQL("update Country set name = 'brunei-darussalam' where a2 = 'BN'");
		runSQL("update Country set name = 'cape-verde' where a2 = 'CV'");
		runSQL(
			"update Country set name = 'cocos-keeling-islands' where a2 = " +
				"'CC'");
		runSQL("update Country set name = 'congo' where a2 = 'CG'");
		runSQL(
			"update Country set name = " +
				"'congo-the-democratic-republic-of-the' where a2 = 'CD'");
		runSQL("update Country set name = 'cote-d''ivoire' where a2 = 'CI'");
		runSQL("update Country set name = 'curacao' where a2 = 'CW'");
		runSQL(
			"update Country set name = 'falkland-islands-malvinas' where a2 " +
				"= 'FK'");
		runSQL("update Country set name = 'faroe-islands' where a2 = 'FO'");
		runSQL("update Country set name = 'fiji' where a2 = 'FJ'");
		runSQL(
			"update Country set name = 'heard-island-and-mcdonald-islands' " +
				"where a2 = 'HM'");
		runSQL(
			"update Country set name = 'holy-see-vatican-city-state' where " +
				"a2 = 'VA'");
		runSQL(
			"update Country set name = 'iran-islamic-republic-of' where a2 = " +
				"'IR'");
		runSQL(
			"update Country set name = " +
				"'korea-democratic-people''s-republic-of' where a2 = 'KP'");
		runSQL("update Country set name = 'korea-republic-of' where a2 = 'KR'");
		runSQL(
			"update Country set name = 'lao-people''s-democratic-republic' " +
				"where a2 = 'LA'");
		runSQL(
			"update Country set name = 'libyan-arab-jamahiriya' where a2 = " +
				"'LY'");
		runSQL(
			"update Country set name = " +
				"'macedonia-the-former-yugoslav-republic-of' where a2 = 'MK'");
		runSQL("update Country set name = 'mayotte' where a2 = 'YT'");
		runSQL(
			"update Country set name = 'moldova-republic-of' where a2 = 'MD'");
		runSQL("update Country set name = 'myanmar' where a2 = 'MM'");
		runSQL("update Country set name = 'reunion' where a2 = 'RE'");
		runSQL(
			"update Country set name = 'russian-federation' where a2 = 'RU'");
		runSQL("update Country set name = 'saint-barthelemy' where a2 = 'BL'");
		runSQL(
			"update Country set name = " +
				"'saint-helena-ascension-and-tristan-da-cunha' where a2 = " +
					"'SH'");
		runSQL(
			"update Country set name = 'saint-kitts-and-nevis' where a2 = " +
				"'KN'");
		runSQL("update Country set name = 'saint-lucia' where a2 = 'LC'");
		runSQL(
			"update Country set name = 'saint-martin-french-part' where a2 = " +
				"'MF'");
		runSQL(
			"update Country set name = 'saint-pierre-and-miquelon' where a2 " +
				"= 'PM'");
		runSQL(
			"update Country set name = 'saint-vincent-and-the-grenadines' " +
				"where a2 = 'VC'");
		runSQL("update Country set name = 'samoa' where a2 = 'WS'");
		runSQL(
			"update Country set name = 'sao-tome-and-principe' where a2 = " +
				"'ST'");
		runSQL(
			"update Country set name = 'sint-maarten-dutch-part' where a2 = " +
				"'SX'");
		runSQL(
			"update Country set name = " +
				"'south-georgia-and-the-south-sandwich-islands' where a2 = " +
					"'GS'");
		runSQL(
			"update Country set name = 'syrian-arab-republic' where a2 = 'SY'");
		runSQL(
			"update Country set name = 'tanzania-united-republic-of' where " +
				"a2 = 'TZ'");
		runSQL(
			"update Country set name = 'trinidad-and-tobago' where a2 = 'TT'");
		runSQL(
			"update Country set name = 'turks-and-caicos-islands' where a2 = " +
				"'TC'");
		runSQL(
			"update Country set name = 'venezuela-bolivarian-republic-of' " +
				"where a2 = 'VE'");
		runSQL(
			"update Country set name = 'virgin-islands-british' where a2 = " +
				"'VG'");
		runSQL("update Country set name = 'wallis-and-futuna' where a2 = 'WF'");
	}

}