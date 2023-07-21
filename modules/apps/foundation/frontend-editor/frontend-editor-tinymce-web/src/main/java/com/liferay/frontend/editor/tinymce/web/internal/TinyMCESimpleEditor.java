/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.editor.tinymce.web.internal;

import com.liferay.portal.kernel.editor.Editor;
import com.liferay.portal.kernel.servlet.PortalWebResourceConstants;

import org.osgi.service.component.annotations.Component;

/**
 * @author Raymond Augé
 * @author Roberto Díaz
 */
@Component(service = Editor.class)
public class TinyMCESimpleEditor implements Editor {

	@Override
	public String[] getJavaScriptModules() {
		return new String[0];
	}

	@Override
	public String getJspPath() {
		return "/tinymce_simple.jsp";
	}

	@Override
	public String getName() {
		return "tinymce_simple";
	}

	@Override
	public String getResourceType() {
		return PortalWebResourceConstants.RESOURCE_TYPE_EDITOR_TINYMCEEDITOR;
	}

}