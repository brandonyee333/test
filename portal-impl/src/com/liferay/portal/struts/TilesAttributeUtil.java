/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.struts;

import javax.servlet.jsp.PageContext;

import org.apache.struts.tiles.ComponentContext;
import org.apache.struts.tiles.taglib.ComponentConstants;

/**
 * @author Shuyang Zhou
 */
public class TilesAttributeUtil {

	public static Object getTilesAttribute(
		PageContext pageContext, String tilesAttributeName) {

		ComponentContext componentContext =
			(ComponentContext)pageContext.getAttribute(
				ComponentConstants.COMPONENT_CONTEXT,
				PageContext.REQUEST_SCOPE);

		if (componentContext == null) {
			return null;
		}

		return componentContext.getAttribute(tilesAttributeName);
	}

	public static void removeComponentContext(PageContext pageContext) {
		pageContext.removeAttribute(ComponentConstants.COMPONENT_CONTEXT);
	}

}