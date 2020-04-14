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