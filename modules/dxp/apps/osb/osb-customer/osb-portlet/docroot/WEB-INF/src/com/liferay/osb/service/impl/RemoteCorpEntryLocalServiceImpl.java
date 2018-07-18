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

package com.liferay.osb.service.impl;

import com.liferay.osb.remote.web.WebRESTWebServiceUtil;
import com.liferay.osb.service.base.RemoteCorpEntryLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;

/**
 * @author Amos Fong
 */
public class RemoteCorpEntryLocalServiceImpl
	extends RemoteCorpEntryLocalServiceBaseImpl {

	public boolean hasCorpEntry(String dossieraAccountKey)
		throws PortalException {

		JSONObject jsonObject = WebRESTWebServiceUtil.getCorpEntry(
			dossieraAccountKey);

		if (jsonObject.length() > 0) {
			return true;
		}
		else {
			return false;
		}
	}

}