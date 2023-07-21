/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.scheduler.single.internal.portal.profile;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Props;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.profile.BaseDSModulePortalProfile;
import com.liferay.portal.profile.PortalProfile;
import com.liferay.portal.scheduler.single.internal.SingleSchedulerEngineConfigurator;

import java.util.Collections;
import java.util.Set;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Shuyang Zhou
 */
@Component(immediate = true, service = PortalProfile.class)
public class ModulePortalProfile extends BaseDSModulePortalProfile {

	@Activate
	public void activate(ComponentContext componentContext) {
		Set<String> supportedPortalProfileNames = null;

		if (GetterUtil.getBoolean(_props.get(PropsKeys.SCHEDULER_ENABLED))) {
			supportedPortalProfileNames = Collections.singleton(
				PortalProfile.PORTAL_PROFILE_NAME_CE);
		}
		else {
			supportedPortalProfileNames = Collections.emptySet();
		}

		init(
			componentContext, supportedPortalProfileNames,
			SingleSchedulerEngineConfigurator.class.getName());
	}

	@Reference(unbind = "-")
	protected void setProps(Props props) {
		_props = props;
	}

	private Props _props;

}