/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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