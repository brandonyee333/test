/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.testray.taglib.servlet.taglib;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.taglib.TagSupport;
import com.liferay.taglib.util.ParamAncestorTag;
import com.liferay.taglib.util.ParamAndPropertyAncestorTagImpl;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.jsp.JspException;

/**
 * @author Andrew Kim
 */
public class TestrayFilterPreferenceTag extends TagSupport {

	@Override
	public int doStartTag() throws JspException {
		ParamAncestorTag paramAncestorTag =
			(ParamAncestorTag)findAncestorWithClass(
				this, ParamAncestorTag.class);

		if (paramAncestorTag == null) {
			throw new JspException();
		}

		if (Validator.isNotNull(_value)) {
			try {
				_setFilterPreference(paramAncestorTag);
			}
			catch (JSONException jsone) {
				throw new JspException(jsone);
			}
		}

		return SKIP_BODY;
	}

	public void setValue(String value) {
		_value = value;
	}

	private void _setFilterPreference(ParamAncestorTag paramAncestorTag)
		throws JSONException {

		ParamAndPropertyAncestorTagImpl paramAndPropertyAncestorTag =
			(ParamAndPropertyAncestorTagImpl)paramAncestorTag;

		Map<String, String[]> paramsMap =
			paramAndPropertyAncestorTag.getParams();

		JSONObject filterPreferenceJSONObject =
			JSONFactoryUtil.createJSONObject(_value);

		Iterator<?> keys = filterPreferenceJSONObject.keys();

		while (keys.hasNext()) {
			String filterParamName = (String)keys.next();

			if (paramsMap.containsKey(filterParamName)) {
				continue;
			}

			String filterParamValue = (String)filterPreferenceJSONObject.get(
				filterParamName);

			paramAncestorTag.addParam(filterParamName, filterParamValue);
		}
	}

	private String _value;

}