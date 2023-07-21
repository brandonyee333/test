/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.template.soy.utils;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONSerializer;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.InputStream;

import java.util.Map;
import java.util.Set;

/**
 * @author Bruno Basto
 */
public class SoyJavaScriptRenderer {

	public SoyJavaScriptRenderer() throws Exception {
		_jsonSerializer = JSONFactoryUtil.createJSONSerializer();
	}

	public String getJavaScript(
		Map<String, Object> context, String id, Set<String> modules) {

		String contextString = _jsonSerializer.serializeDeep(context);

		String modulesString = _jsonSerializer.serialize(modules);

		return StringUtil.replace(
			_JAVA_SCRIPT_TPL, new String[] {"$CONTEXT", "$ID", "$MODULES"},
			new String[] {contextString, id, modulesString});
	}

	private static String _getJavaScriptTPL() {
		Class<?> clazz = SoyJavaScriptRenderer.class;

		InputStream inputStream = clazz.getResourceAsStream(
			"dependencies/bootstrap.js.tpl");

		String js = StringPool.BLANK;

		try {
			js = StringUtil.read(inputStream);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug("Unable to read template");
			}
		}

		return js;
	}

	private static final String _JAVA_SCRIPT_TPL;

	private static final Log _log = LogFactoryUtil.getLog(
		SoyJavaScriptRenderer.class);

	static {
		_JAVA_SCRIPT_TPL = _getJavaScriptTPL();
	}

	private final JSONSerializer _jsonSerializer;

}