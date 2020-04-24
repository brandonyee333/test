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