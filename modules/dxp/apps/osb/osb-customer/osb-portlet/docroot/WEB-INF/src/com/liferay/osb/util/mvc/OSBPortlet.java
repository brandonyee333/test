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

package com.liferay.osb.util.mvc;

import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.util.bridges.mvc.MVCPortlet;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.portal.kernel.portlet.LiferayPortletMode;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.StringPool;

import java.io.IOException;

import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author Ryan Park
 */
public class OSBPortlet extends MVCPortlet {

	@Override
	protected void doDispatch(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		if (!PortletPropsValues.DEVELOPER_PATHS_ENABLED) {
			super.doDispatch(renderRequest, renderResponse);

			return;
		}

		String path = getPath(renderRequest);

		if (Validator.isNull(path)) {
			path = getDefaultPath(renderRequest);
		}

		if (hasDeveloperPath(path)) {
			include(getDeveloperPath(path), renderRequest, renderResponse);
		}
		else {
			super.doDispatch(renderRequest, renderResponse);
		}
	}

	protected String getDefaultPath(RenderRequest renderRequest) {
		PortletMode portletMode = renderRequest.getPortletMode();

		if (portletMode.equals(PortletMode.VIEW)) {
			return viewTemplate;
		}
		else if (portletMode.equals(LiferayPortletMode.ABOUT)) {
			return aboutTemplate;
		}
		else if (portletMode.equals(LiferayPortletMode.CONFIG)) {
			return configTemplate;
		}
		else if (portletMode.equals(PortletMode.EDIT)) {
			return editTemplate;
		}
		else if (portletMode.equals(LiferayPortletMode.EDIT_DEFAULTS)) {
			return editDefaultsTemplate;
		}
		else if (portletMode.equals(LiferayPortletMode.EDIT_GUEST)) {
			return editGuestTemplate;
		}
		else if (portletMode.equals(PortletMode.HELP)) {
			return helpTemplate;
		}
		else if (portletMode.equals(LiferayPortletMode.PREVIEW)) {
			return previewTemplate;
		}
		else if (portletMode.equals(LiferayPortletMode.PRINT)) {
			return printTemplate;
		}

		return StringPool.BLANK;
	}

	protected String getDeveloperPath(String path) {
		String[] pathParts = StringUtil.split(path, CharPool.PERIOD);

		return
			pathParts[0] + _DEVELOPER_PATH_POSTFIX + CharPool.PERIOD +
				pathParts[1];
	}

	protected boolean hasDeveloperPath(String path) {
		return false;
	}

	private static final String _DEVELOPER_PATH_POSTFIX = ".developer";

}