/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.taglib.util;

import com.liferay.taglib.TagSupport;

import javax.servlet.jsp.JspException;

/**
 * @author Brian Wing Shun Chan
 */
public class PropertyTag extends TagSupport {

	@Override
	public int doStartTag() throws JspException {
		PropertyAncestorTag propertyAncestorTag =
			(PropertyAncestorTag)findAncestorWithClass(
				this, PropertyAncestorTag.class);

		if (propertyAncestorTag == null) {
			throw new JspException();
		}

		propertyAncestorTag.addProperty(_name, _value);

		return SKIP_BODY;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setValue(String value) {
		_value = value;
	}

	private String _name;
	private String _value;

}