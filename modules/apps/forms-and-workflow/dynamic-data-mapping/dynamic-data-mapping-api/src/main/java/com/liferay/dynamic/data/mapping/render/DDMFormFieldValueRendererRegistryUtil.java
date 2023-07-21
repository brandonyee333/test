/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.render;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

/**
 * @author Marcellus Tavares
 */
public class DDMFormFieldValueRendererRegistryUtil {

	public static DDMFormFieldValueRenderer getDDMFormFieldValueRenderer(
		String ddmFormFieldType) {

		DDMFormFieldValueRendererRegistry ddmFormFieldValueRendererRegistry =
			getDDMFormFieldValueRendererRegistry();

		return ddmFormFieldValueRendererRegistry.getDDMFormFieldValueRenderer(
			ddmFormFieldType);
	}

	public static DDMFormFieldValueRendererRegistry
		getDDMFormFieldValueRendererRegistry() {

		PortalRuntimePermission.checkGetBeanProperty(
			DDMFormFieldValueRendererRegistryUtil.class);

		return _ddmFormFieldValueRendererRegistry;
	}

	public void setDDMFormFieldValueRendererRegistry(
		DDMFormFieldValueRendererRegistry ddmFormFieldValueRendererRegistry) {

		PortalRuntimePermission.checkGetBeanProperty(getClass());

		_ddmFormFieldValueRendererRegistry = ddmFormFieldValueRendererRegistry;
	}

	private static DDMFormFieldValueRendererRegistry
		_ddmFormFieldValueRendererRegistry;

}