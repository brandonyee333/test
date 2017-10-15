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

package com.liferay.watson.internal.hook.upgrade.v1_0_2;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.watson.model.WatsonIncident;
import com.liferay.watson.service.WatsonIncidentLocalServiceUtil;

import java.util.List;

/**
 * @author Steven Smith
 */
public class UpgradeWatsonIncidents extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		updateIncidentNames();
	}

	protected void updateIncidentNames() throws Exception {
		List<WatsonIncident> watsonIncidents =
			WatsonIncidentLocalServiceUtil.getWatsonIncidents(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (WatsonIncident watsonIncident : watsonIncidents) {
			String incidentName = watsonIncident.getName();

			String[] incidentNameParts = StringUtil.split(
				incidentName, StringPool.DASH);

			String incidentYear = incidentNameParts[0];

			String incidentNameId = incidentNameParts[1];

			if (Validator.isNotNull(incidentNameId)) {
				incidentNameId = incidentNameId.replaceFirst(
					"^0+(?!$)", StringPool.BLANK);
			}

			watsonIncident.setName(
				incidentYear + StringPool.DASH + incidentNameId);

			WatsonIncidentLocalServiceUtil.updateWatsonIncident(watsonIncident);
		}
	}

}