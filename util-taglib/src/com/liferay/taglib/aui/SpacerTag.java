/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.taglib.aui;

import com.liferay.petra.string.StringPool;
import com.liferay.taglib.TagSupport;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

/**
 * @author Eduardo Lundgren
 */
public class SpacerTag extends TagSupport {

	@Override
	public int doStartTag() throws JspException {
		try {
			JspWriter jspWriter = pageContext.getOut();

			jspWriter.write(StringPool.SPACE);
		}
		catch (Exception e) {
			throw new JspException(e);
		}

		return super.doStartTag();
	}

}