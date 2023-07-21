/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.taglib.aui;

import com.liferay.alloy.util.ReservedAttributeUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.taglib.aui.base.BaseComponentTag;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

/**
 * @author Eduardo Lundgren
 * @author Bruno Basto
 */
public class ComponentTag extends BaseComponentTag {

	protected boolean isEventAttribute(String key) {
		if (StringUtil.startsWith(key, "after") ||
			StringUtil.startsWith(key, "on")) {

			return true;
		}

		return false;
	}

	protected boolean isValidAttribute(String key) {
		String excludeAttributes = getExcludeAttributes();

		if (excludeAttributes == null) {
			return true;
		}

		Set<String> excludeAttributesSet = SetUtil.fromArray(
			StringUtil.split(excludeAttributes));

		if (key.equals("dynamicAttributes") ||
			excludeAttributesSet.contains(key)) {

			return false;
		}

		return true;
	}

	protected void proccessAttributes(
		Map<String, Object> options, Map<String, Object> jsonifiedOptions) {

		Map<String, String> afterEventOptions = new HashMap<>();
		Map<String, String> onEventOptions = new HashMap<>();

		for (Map.Entry<String, Object> entry : options.entrySet()) {
			String key = entry.getKey();

			if (!isValidAttribute(key)) {
				continue;
			}

			Object value = entry.getValue();

			String originalKey = ReservedAttributeUtil.getOriginalName(
				getName(), key);

			if (value instanceof Map) {
				Map<String, Object> childOptions = new HashMap<>();

				proccessAttributes((Map<String, Object>)value, childOptions);

				jsonifiedOptions.put(originalKey, childOptions);

				continue;
			}

			if (isEventAttribute(key)) {
				processEventAttribute(
					key, String.valueOf(value), afterEventOptions,
					onEventOptions);
			}
			else {
				jsonifiedOptions.put(originalKey, value);
			}
		}

		if (!afterEventOptions.isEmpty()) {
			jsonifiedOptions.put("after", afterEventOptions);
		}

		if (!onEventOptions.isEmpty()) {
			jsonifiedOptions.put("on", onEventOptions);
		}
	}

	protected void processEventAttribute(
		String key, String value, Map<String, String> afterEventOptions,
		Map<String, String> onEventsOptions) {

		if (key.startsWith("after")) {
			String eventName = StringUtils.uncapitalize(key.substring(5));

			afterEventOptions.put(eventName, value);
		}
		else {
			String eventName = StringUtils.uncapitalize(key.substring(2));

			onEventsOptions.put(eventName, value);
		}
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		Map<String, Object> options = getOptions();

		Map<String, Object> jsonifiedOptions = new HashMap<>();

		proccessAttributes(options, jsonifiedOptions);

		super.setAttributes(request);

		setNamespacedAttribute(request, "jsonifiedOptions", jsonifiedOptions);
		setNamespacedAttribute(request, "options", options);
	}

}