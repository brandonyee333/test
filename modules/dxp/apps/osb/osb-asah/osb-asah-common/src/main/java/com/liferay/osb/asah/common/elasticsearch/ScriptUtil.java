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

package com.liferay.osb.asah.common.elasticsearch;

import java.io.IOException;
import java.io.InputStream;

import java.nio.charset.Charset;

import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.script.Script;

/**
 * @author André Miranda
 */
public class ScriptUtil {

	public static Script createScript(Class<?> clazz, String resourceName) {
		return new Script(loadScriptSource(clazz, resourceName));
	}

	public static Script createScript(
		Class<?> clazz, String resourceName, Map<String, Object> params) {

		return new Script(
			Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
			loadScriptSource(clazz, resourceName), params);
	}

	public static String loadScriptSource(Class<?> clazz, String resourceName) {
		try (InputStream inputStream = clazz.getResourceAsStream(
				"dependencies/" + resourceName)) {

			return IOUtils.toString(inputStream, Charset.defaultCharset());
		}
		catch (IOException ioe) {
			_log.error(ioe);

			throw new IllegalStateException(
				"Unable to read resource " + resourceName);
		}
	}

	private static final Log _log = LogFactory.getLog(ScriptUtil.class);

}