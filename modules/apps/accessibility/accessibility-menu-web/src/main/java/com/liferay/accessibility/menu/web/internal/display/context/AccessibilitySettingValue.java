package com.liferay.accessibility.menu.web.internal.display.context;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.liferay.petra.string.StringUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * @author Evan Thibodeau
 */
public enum AccessibilitySettingValue {

	@JsonProperty("false")
	FALSE("false"),
	@JsonProperty("true")
	TRUE("true"),
	@JsonProperty("undefined")
	UNDEFINED("undefined");

	public static AccessibilitySettingValue toAccessibilitySettingValue(
		String string) {

		for (AccessibilitySettingValue accessibilitySettingValue : values()) {
			if (StringUtil.equalsIgnoreCase(
					accessibilitySettingValue._value, string)) {

				return accessibilitySettingValue;
			}
		}

		if (_log.isDebugEnabled()) {
			_log.debug(
				"String did not match a known accessibility setting value.");
		}

		return UNDEFINED;
	}

	public Boolean toBoolean() {
		if (this.equals(FALSE)) {
			return false;
		}
		else if (this.equals(TRUE)) {
			return true;
		}

		return null;
	}

	@Override
	public String toString() {
		return _value.toLowerCase();
	}

	private AccessibilitySettingValue(String value) {
		_value = value;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AccessibilitySettingValue.class);

	private final String _value;

}