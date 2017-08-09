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

package com.liferay.watson.service.messaging;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.watson.service.ClpSerializer;
import com.liferay.watson.service.WatsonActivityLocalServiceUtil;
import com.liferay.watson.service.WatsonAddressLocalServiceUtil;
import com.liferay.watson.service.WatsonHistoryLocalServiceUtil;
import com.liferay.watson.service.WatsonIncidentLocalServiceUtil;
import com.liferay.watson.service.WatsonIncidentRelLocalServiceUtil;
import com.liferay.watson.service.WatsonListTypeLocalServiceUtil;
import com.liferay.watson.service.WatsonListTypeRelLocalServiceUtil;
import com.liferay.watson.service.WatsonPersonLocalServiceUtil;
import com.liferay.watson.service.WatsonRelationshipLocalServiceUtil;
import com.liferay.watson.service.WatsonResourceLocalServiceUtil;
import com.liferay.watson.service.WatsonVehicleLocalServiceUtil;

/**
 * @author Eddie Olson
 */
@ProviderType
public class ClpMessageListener extends BaseMessageListener {

	public static String getServletContextName() {
		return ClpSerializer.getServletContextName();
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		String command = message.getString("command");
		String servletContextName = message.getString("servletContextName");

		if (command.equals("undeploy") &&
			servletContextName.equals(getServletContextName())) {

			WatsonActivityLocalServiceUtil.clearService();

			WatsonAddressLocalServiceUtil.clearService();

			WatsonHistoryLocalServiceUtil.clearService();

			WatsonIncidentLocalServiceUtil.clearService();

			WatsonIncidentRelLocalServiceUtil.clearService();

			WatsonListTypeLocalServiceUtil.clearService();

			WatsonListTypeRelLocalServiceUtil.clearService();

			WatsonPersonLocalServiceUtil.clearService();

			WatsonRelationshipLocalServiceUtil.clearService();

			WatsonResourceLocalServiceUtil.clearService();

			WatsonVehicleLocalServiceUtil.clearService();
		}
	}

}