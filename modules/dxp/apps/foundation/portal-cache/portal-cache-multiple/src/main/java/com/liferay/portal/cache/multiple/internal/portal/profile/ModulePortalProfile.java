/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.cache.multiple.internal.portal.profile;

import com.liferay.portal.cache.multiple.internal.ClusterLinkPortalCacheReplicatorFactory;
import com.liferay.portal.cache.multiple.internal.cluster.link.ClusterLinkPortalCacheClusterChannelFactory;
import com.liferay.portal.cache.multiple.internal.cluster.link.PortalCacheClusterLink;
import com.liferay.portal.cache.multiple.internal.cluster.link.messaging.ClusterLinkMessagingConfigurator;
import com.liferay.portal.cache.multiple.internal.cluster.link.messaging.ClusterLinkPortalCacheClusterListener;
import com.liferay.portal.profile.BaseDSModulePortalProfile;
import com.liferay.portal.profile.PortalProfile;

import java.util.Collections;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * @author Shuyang Zhou
 */
@Component(immediate = true, service = PortalProfile.class)
public class ModulePortalProfile extends BaseDSModulePortalProfile {

	@Activate
	public void activate(ComponentContext componentContext) {
		init(
			componentContext,
			Collections.singleton(PortalProfile.PORTAL_PROFILE_NAME_DXP),
			ClusterLinkMessagingConfigurator.class.getName(),
			ClusterLinkPortalCacheClusterChannelFactory.class.getName(),
			ClusterLinkPortalCacheClusterListener.class.getName(),
			ClusterLinkPortalCacheReplicatorFactory.class.getName(),
			PortalCacheClusterLink.class.getName());
	}

}