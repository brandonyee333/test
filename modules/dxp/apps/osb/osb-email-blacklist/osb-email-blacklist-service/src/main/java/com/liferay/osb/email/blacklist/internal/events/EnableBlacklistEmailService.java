/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.email.blacklist.internal.events;

import com.liferay.mail.kernel.service.MailServiceUtil;
import com.liferay.osb.email.blacklist.internal.email.service.BlacklistEmailServiceImpl;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jamie Sammons
 */
@Component(immediate = true, service = EnableBlacklistEmailService.class)
public class EnableBlacklistEmailService {

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		MailServiceUtil mailServiceUtil = new MailServiceUtil();

		mailServiceUtil.setService(_blacklistEmailServiceImpl);
	}

	@Deactivate
	protected void deactivate() {
		MailServiceUtil mailServiceUtil = new MailServiceUtil();

		mailServiceUtil.setService(
			_blacklistEmailServiceImpl.getOriginalMailService());
	}

	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

	@Reference
	private BlacklistEmailServiceImpl _blacklistEmailServiceImpl;

}