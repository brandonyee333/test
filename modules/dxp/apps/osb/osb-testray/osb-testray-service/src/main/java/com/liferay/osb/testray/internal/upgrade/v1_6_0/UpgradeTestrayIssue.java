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

package com.liferay.osb.testray.internal.upgrade.v1_6_0;

import com.liferay.osb.testray.internal.upgrade.v1_5_0.BaseUpgradeIndex;
import com.liferay.portal.kernel.util.StringBundler;

/**
 * @author Ethan Bustad
 */
public class UpgradeTestrayIssue extends BaseUpgradeIndex {

	@Override
	protected void doUpgrade() throws Exception {
		if (tableHasIndex("OSB_TestrayIssue", "IX_EF624647")) {
			return;
		}

		StringBundler sb = new StringBundler(15);

		sb.append("delete from OSB_TestrayIssue where testrayIssueId in (");
		sb.append("37695232,37695356,37695408,37695409,37695481,37695482,");
		sb.append("37695506,37695507,37695518,37695543,37695544,37695551,");
		sb.append("37695552,37695561,37695580,37695603,37695604,37695607,");
		sb.append("37695617,37695632,37695643,37695644,37695647,37695650,");
		sb.append("37695653,37695664,37695676,37695681,37695694,37695702,");
		sb.append("37695704,37695706,37695707,37695719,37695720,37695723,");
		sb.append("37695724,37695727,37695755,37695758,37695802,37695810,");
		sb.append("37695817,37695848,37695863,37695865,37695880,37695881,");
		sb.append("37695885,37695897,37695920,37696030,37696057,37696070,");
		sb.append("37696150,37696160,37696170,37696173,37696179,37696187,");
		sb.append("37696188,37696198,37696215,37696284,37696336,37696341,");
		sb.append("37696342,37696343,37696384,37696439,37696583,37696618,");
		sb.append("37696673,37696724,37696805,37696805,37696943,37697092,");
		sb.append("37697093,37697170,37697191,37697192,37697328)");

		runSQL(sb.toString());

		runSQL(
			"create unique index IX_EF624647 on OSB_TestrayIssue " +
				"(name[$COLUMN_LENGTH:150$])");
	}

}