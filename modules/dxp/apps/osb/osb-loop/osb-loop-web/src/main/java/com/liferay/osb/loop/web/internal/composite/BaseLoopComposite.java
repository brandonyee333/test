/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.web.internal.composite;

import com.liferay.osb.loop.web.internal.util.LoopCompositeUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Timothy Bell
 */
public abstract class BaseLoopComposite {

	public BaseLoopComposite(ThemeDisplay themeDisplay) {
		this.themeDisplay = themeDisplay;
	}

	public abstract long getEntityClassNameId();

	public abstract long getEntityClassPK();

	public JSONObject getJSONObject() throws Exception {
		return getJSONObject(new String[0], true);
	}

	public JSONObject getJSONObject(String[] fields) throws Exception {
		return getJSONObject(fields, true);
	}

	public JSONObject getJSONObject(String[] fields, boolean includeBaseFields)
		throws Exception {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		if (includeBaseFields) {
			jsonObject = getBaseJSONObject(jsonObject);
		}

		LoopCompositeUtil.updateJSONObject(
			this, getClass(), fields, jsonObject);

		return jsonObject;
	}

	protected int getAPIVersion() {
		HttpServletRequest request = themeDisplay.getRequest();

		return request.getIntHeader("api-version");
	}

	protected JSONObject getBaseJSONObject(JSONObject jsonObject)
		throws Exception {

		if (getAPIVersion() < 5) {
			jsonObject.put("classNameId", getEntityClassNameId());
			jsonObject.put("classPK", getEntityClassPK());
		}

		jsonObject.put("entityClassNameId", getEntityClassNameId());
		jsonObject.put("entityClassPK", getEntityClassPK());

		return jsonObject;
	}

	protected final ThemeDisplay themeDisplay;

}