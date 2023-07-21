/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wsrp.internal.bind;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.util.axis.ServletUtil;
import com.liferay.wsrp.model.WSRPProducer;
import com.liferay.wsrp.service.WSRPProducerLocalServiceUtil;

import javax.servlet.http.HttpServletRequest;

import oasis.names.tc.wsrp.v2.types.LocalizedString;

/**
 * @author Brian Wing Shun Chan
 */
public class BaseServiceImpl {

	protected LocalizedString getLocalizedString(String value) {
		return new LocalizedString(value, "en", null);
	}

	protected LocalizedString[] getLocalizedStrings(String[] values) {
		LocalizedString[] localizedStrings = new LocalizedString[values.length];

		for (int i = 0; i < values.length; i++) {
			String value = values[i];

			localizedStrings[i] = getLocalizedString(value);
		}

		return localizedStrings;
	}

	protected WSRPProducer getWSRPProducer() throws Exception {
		HttpServletRequest request = ServletUtil.getRequest();

		String wsrpProducerUuid = ParamUtil.getString(
			request, "wsrpProducerUuid");

		return WSRPProducerLocalServiceUtil.getWSRPProducer(wsrpProducerUuid);
	}

}