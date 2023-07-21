/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.twitter.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Peter Fellwock
 */
@ExtendedObjectClassDefinition(
	category = "other", scope = ExtendedObjectClassDefinition.Scope.COMPANY
)
@Meta.OCD(
	id = "com.liferay.twitter.internal.configuration.TwitterGroupServiceConfiguration",
	localization = "content/Language",
	name = "twitter-service-configuration-name"
)
public interface TwitterGroupServiceConfiguration {

	@Meta.AD(
		deflt = "5", name = "twitter-synchronization-interval", required = false
	)
	public int twitterSynchronizationInterval();

}