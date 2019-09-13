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

package com.liferay.lcs.client.internal.lifecycle;

import com.liferay.lcs.client.constants.LCSClientConstants;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Igor Beslic
 */
@Component(immediate = true, service = LCSModuleLifecycle.class)
public class LCSModuleLifecycle {

	@Activate
	protected void activate() {
		if (_log.isInfoEnabled()) {
			_log.info(_LCS_CLIENT_FULL_NAME + " activated");
		}
	}

	@Deactivate
	protected void deactivate() {
		if (_log.isWarnEnabled()) {
			_log.warn(_LCS_CLIENT_FULL_NAME + " deactivated");
		}
	}

	private static final String _LCS_CLIENT_FULL_NAME =
		"LCS Client " + LCSClientConstants.LCS_CLIENT_VERSION;

	private static final Log _log = LogFactoryUtil.getLog(
		LCSModuleLifecycle.class);

	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED)
	private ModuleServiceLifecycle _moduleServiceLifecycle;

}