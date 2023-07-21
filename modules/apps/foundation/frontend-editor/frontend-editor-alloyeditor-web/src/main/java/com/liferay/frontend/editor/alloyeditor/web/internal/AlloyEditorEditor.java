/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.editor.alloyeditor.web.internal;

import com.liferay.portal.kernel.editor.Editor;
import com.liferay.portal.kernel.servlet.PortalWebResourceConstants;

import org.osgi.service.component.annotations.Component;

/**
 * @author Raymond Augé
 * @author Roberto Díaz
 */
@Component(service = Editor.class)
public class AlloyEditorEditor implements Editor {

	@Override
	public String[] getJavaScriptModules() {
		return new String[] {"liferay-alloy-editor"};
	}

	@Override
	public String getJspPath() {
		return "/alloyeditor.jsp";
	}

	@Override
	public String getName() {
		return "alloyeditor";
	}

	@Override
	public String getResourceType() {
		return PortalWebResourceConstants.RESOURCE_TYPE_EDITOR_ALLOYEDITOR;
	}

}