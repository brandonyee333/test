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

package com.liferay.osb.customer.downloads.display.web.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Amos Fong
 */
@ExtendedObjectClassDefinition(category = "osb")
@Meta.OCD(
	id = "com.liferay.osb.customer.downloads.display.web.configuration.DownloadsDisplayConfiguration",
	localization = "content/Language",
	name = "downloads-display-configuration-name"
)
public interface DownloadsDisplayConfiguration {

	@Meta.AD(
		deflt = "OSB-CUSTOMER-THEME---DOWNLOAD", name = "ddm-structure-key",
		required = false
	)
	public String ddmStructureKey();

}