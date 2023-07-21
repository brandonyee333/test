/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.product.navigation.accessibility.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Chema Balsas
 */
@ExtendedObjectClassDefinition(category = "web-experience")
@Meta.OCD(
	id = "com.liferay.product.navigation.accessibility.internal.configuration.ProductNavigationAccessibilityConfiguration",
	localization = "content/Language", name = "details-configuration-name"
)
public interface ProductNavigationAccessibilityConfiguration {

	@Meta.AD(deflt = "false", name = "use-high-contrast", required = false)
	public boolean useHighContrast();

}