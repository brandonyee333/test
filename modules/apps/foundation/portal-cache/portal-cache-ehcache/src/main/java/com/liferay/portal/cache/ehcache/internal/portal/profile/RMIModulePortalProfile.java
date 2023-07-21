/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.cache.ehcache.internal.portal.profile;

import com.liferay.portal.cache.ehcache.internal.configurator.RMIMultiVMEhcachePortalCacheManagerConfigurator;
import com.liferay.portal.profile.BaseDSModulePortalProfile;
import com.liferay.portal.profile.PortalProfile;

import java.util.Collections;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * @author Tina Tian
 */
@Component(immediate = true, service = PortalProfile.class)
public class RMIModulePortalProfile extends BaseDSModulePortalProfile {

	@Activate
	public void activate(ComponentContext componentContext) {
		init(
			componentContext,
			Collections.singleton(PortalProfile.PORTAL_PROFILE_NAME_CE),
			RMIMultiVMEhcachePortalCacheManagerConfigurator.class.getName());
	}

}