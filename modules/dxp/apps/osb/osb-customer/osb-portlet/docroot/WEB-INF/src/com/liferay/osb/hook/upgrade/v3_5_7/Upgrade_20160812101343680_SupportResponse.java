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

package com.liferay.osb.hook.upgrade.v3_5_7;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.SupportResponse;
import com.liferay.osb.model.SupportResponseConstants;
import com.liferay.osb.service.SupportResponseLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;

import java.util.List;

/**
 * @author Amos Fong
 */
public class Upgrade_20160812101343680_SupportResponse
	extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20160812101343680L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		List<SupportResponse> supportResponses =
			SupportResponseLocalServiceUtil.getSupportResponses(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (SupportResponse supportResponse : supportResponses) {
			long supportResponseId = supportResponse.getSupportResponseId();

			if ((supportResponseId == _SUPPORT_RESPONSE_GOLD_ID) ||
				(supportResponseId == _SUPPORT_RESPONSE_LIMITED_ID) ||
				(supportResponseId == _SUPPORT_RESPONSE_PLATINUM_ID) ||
				(supportResponseId == _SUPPORT_RESPONSE_SILVER_ID)) {

				continue;
			}

			if (supportResponse.getSupportLevel() ==
					SupportResponseConstants.SUPPORT_LEVEL_GOLD) {

				_updateSupportResponse(
					supportResponseId, _SUPPORT_RESPONSE_GOLD_ID);
			}
			else if (supportResponse.getSupportLevel() ==
						SupportResponseConstants.SUPPORT_LEVEL_PLATINUM) {

				_updateSupportResponse(
					supportResponseId, _SUPPORT_RESPONSE_PLATINUM_ID);
			}
			else if (supportResponse.getSupportLevel() ==
						SupportResponseConstants.SUPPORT_LEVEL_SILVER) {

				_updateSupportResponse(
					supportResponseId, _SUPPORT_RESPONSE_SILVER_ID);
			}
			else {
				_updateSupportResponse(
					supportResponseId, _SUPPORT_RESPONSE_LIMITED_ID);
			}

			SupportResponseLocalServiceUtil.deleteSupportResponse(
				supportResponseId);
		}

		runSQL("alter table OSB_SupportResponse drop column languageId");
	}

	private void _updateSupportResponse(
			long fromSupportResponseId, long toSupportResponseId)
		throws Exception {

		runSQL(
			"update OSB_AccountEntry set highestSupportResponseId = " +
				toSupportResponseId + " where highestSupportResponseId = " +
					fromSupportResponseId);
		runSQL(
			"update OSB_OfferingDefinition set supportResponseId = " +
				toSupportResponseId + " where supportResponseId = " +
					fromSupportResponseId);
	}

	private static final long _SUPPORT_RESPONSE_GOLD_ID = 77672072;

	private static final long _SUPPORT_RESPONSE_LIMITED_ID = 77672083;

	private static final long _SUPPORT_RESPONSE_PLATINUM_ID = 77672068;

	private static final long _SUPPORT_RESPONSE_SILVER_ID = 77672086;

}