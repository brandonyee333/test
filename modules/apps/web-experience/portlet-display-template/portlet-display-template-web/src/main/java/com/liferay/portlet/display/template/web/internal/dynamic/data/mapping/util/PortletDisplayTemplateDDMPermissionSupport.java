/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.display.template.web.internal.dynamic.data.mapping.util;

import com.liferay.dynamic.data.mapping.util.DDMTemplatePermissionSupport;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.template.TemplateHandler;
import com.liferay.portal.kernel.template.TemplateHandlerRegistryUtil;
import com.liferay.portal.kernel.util.StringPool;

import org.osgi.service.component.annotations.Component;

/**
 * @author Marcellus Tavares
 */
@Component(
	immediate = true,
	property = {
		"add.template.action.id=" + ActionKeys.ADD_PORTLET_DISPLAY_TEMPLATE,
		"default.model.resource.name=true",
		"model.class.name=com.liferay.portlet.display.template.PortletDisplayTemplate"
	}
)
public class PortletDisplayTemplateDDMPermissionSupport
	implements DDMTemplatePermissionSupport {

	@Override
	public String getResourceName(long classNameId) {
		TemplateHandler templateHandler =
			TemplateHandlerRegistryUtil.getTemplateHandler(classNameId);

		if (templateHandler != null) {
			return templateHandler.getResourceName();
		}

		return StringPool.BLANK;
	}

}