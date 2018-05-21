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

package com.liferay.osb.hook.upgrade.v3_5_2;

import com.liferay.expando.kernel.model.ExpandoValue;
import com.liferay.expando.kernel.service.ExpandoValueLocalServiceUtil;
import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.List;

/**
 * @author Douglas Wong
 * @author Rachael Koestartyo
 * @author Peter Shin
 */
public class Upgrade_20160629110621057_Expando extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20160629110621057L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		updateExpandoValues(OSBConstants.COMPANY_ID);
	}

	protected void updateExpandoValues(long companyId) throws Exception {
		List<ExpandoValue> expandoValues =
			ExpandoValueLocalServiceUtil.getColumnValues(
				companyId, User.class.getName(), _EXPANDO_TABLE_NAME,
				_EXPANDO_COLUMN_NAME, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (ExpandoValue expandoValue : expandoValues) {
			JSONObject jsonObject = null;

			try {
				jsonObject = JSONFactoryUtil.createJSONObject(
					expandoValue.getData());
			}
			catch (JSONException jsone) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Unable to update expando value for valueId " +
							expandoValue.getValueId() + " with data " +
								expandoValue.getData());
				}

				continue;
			}

			if ((jsonObject == null) || !jsonObject.has(_OLD_FIELD_NAME)) {
				continue;
			}

			long[] values = StringUtil.split(
				jsonObject.getString(_OLD_FIELD_NAME), 0L);

			jsonObject.put(_NEW_FIELD_NAME, StringUtil.merge(values));

			jsonObject.remove(_OLD_FIELD_NAME);

			ExpandoValueLocalServiceUtil.addValue(
				companyId, User.class.getName(), _EXPANDO_TABLE_NAME,
				_EXPANDO_COLUMN_NAME, expandoValue.getClassPK(),
				jsonObject.toString());
		}
	}

	private static final String _EXPANDO_COLUMN_NAME = "hsContactCache";

	private static final String _EXPANDO_TABLE_NAME = "HubSpot";

	private static final String _NEW_FIELD_NAME = "lrLiferayTrialLicenseKeys";

	private static final String _OLD_FIELD_NAME = "lrEETrialLicenseKeys";

	private static final Log _log = LogFactoryUtil.getLog(
		Upgrade_20160629110621057_Expando.class);

}