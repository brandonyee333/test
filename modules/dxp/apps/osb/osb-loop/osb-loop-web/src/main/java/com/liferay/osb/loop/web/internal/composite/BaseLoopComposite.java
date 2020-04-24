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