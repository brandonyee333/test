/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.cache.ehcache.multiple.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Tina Tian
 */
@ExtendedObjectClassDefinition(category = "foundation")
@Meta.OCD(
	id = "com.liferay.portal.cache.ehcache.multiple.configuration.EhcacheMultipleConfiguration",
	localization = "content/Language",
	name = "ehcache-multiple-configuration-name"
)
public interface EhcacheMultipleConfiguration {

	@Meta.AD(
		deflt = "com.liferay.portal.cache.ehcache.multiple.internal.bootstrap.RMIBootstrapCacheLoaderFactory",
		required = false
	)
	public String bootstrapCacheLoaderFactoryClass();

	@Meta.AD(
		deflt = "com.liferay.portal.cache.ehcache.multiple.internal.distribution.RMICacheReplicatorFactory",
		required = false
	)
	public String cacheReplicatorFactoryClass();

}