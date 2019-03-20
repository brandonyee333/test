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

package com.liferay.osb.customer.upgrade;

import com.liferay.osb.customer.upgrade.util.MessagePublisherUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Kyle Bischof
 */
public abstract class BaseUpgradeProcess extends UpgradeProcess {

	protected void runMetricsSQL(String schema, String sql) throws Exception {
		try {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("schema", schema);
			jsonObject.put("sql", sql);

			MessagePublisherUtil.sendMessage("metrics.upgrade", jsonObject);
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
	}

}