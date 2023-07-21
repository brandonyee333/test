/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.util.bridges.python;

import com.liferay.util.bridges.scripting.ScriptingPortlet;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;

/**
 * @author Jorge Ferrer
 * @author Alberto Montero
 * @author Brian Wing Shun Chan
 */
public class PythonPortlet extends ScriptingPortlet {

	@Override
	public void init() throws PortletException {
		super.init();

		language = _LANGUAGE;
	}

	@Override
	protected String getFileName(RenderRequest renderRequest) {
		return renderRequest.getParameter("pythonFile");
	}

	private static final String _LANGUAGE = "python";

}