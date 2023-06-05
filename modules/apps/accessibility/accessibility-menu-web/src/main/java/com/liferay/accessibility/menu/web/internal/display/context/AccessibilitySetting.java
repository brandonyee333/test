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

package com.liferay.accessibility.menu.web.internal.display.context;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.function.Consumer;

/**
 * @author Evan Thibodeau
 */
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class AccessibilitySetting {

	public static AccessibilitySetting create(
		String label,
		Consumer<AccessibilitySetting> accessibilitySettingConsumer) {

		AccessibilitySetting accessibilitySetting = new AccessibilitySetting(
			label);

		accessibilitySettingConsumer.accept(accessibilitySetting);

		return accessibilitySetting;
	}

	public AccessibilitySetting(String label) {
		this.label = label;
	}

	public String getClassName() {
		return className;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public String getKey() {
		return key;
	}

	public String getLabel() {
		return label;
	}

	public String getSessionClicksValue() {
		return sessionClicksValue;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setSessionClicksValue(String sessionClicksValue) {
		this.sessionClicksValue = sessionClicksValue;
	}

	@JsonProperty
	protected String className;

	@JsonProperty
	protected String defaultValue;

	@JsonProperty
	protected String key;

	@JsonProperty
	protected String label;

	@JsonProperty
	protected String sessionClicksValue;

}