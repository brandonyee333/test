/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.calendar.web.internal.social;

import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.social.kernel.util.SocialConfigurationUtil;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marcellus Tavares
 */
@Component(immediate = true)
public class CalendarSocialActivityConfigurator {

	@Activate
	protected void activate() throws Exception {
		Class<?> clazz = getClass();

		String xml = new String(
			FileUtil.getBytes(
				getClass(), "/META-INF/social/liferay-social.xml"));

		SocialConfigurationUtil.read(
			clazz.getClassLoader(), new String[] {xml});
	}

	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

}