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

import com.liferay.lcs.client.configuration.LCSConfiguration;
import com.liferay.lcs.client.internal.configuration.LCSConfigurationProvider;
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
@Component
public class LCSModuleLifecycle {

	@Activate
	protected void activate() throws Exception {
		_lcsConfiguration = _lcsConfigurationProvider.getLCSConfiguration();

		if (_log.isInfoEnabled()) {
			_log.info(
				"LCS client " + _lcsConfiguration.lcsClientVersion() +
					" activated");
		}
	}

	@Deactivate
	protected void deactivate() throws Exception {
		if (_log.isWarnEnabled()) {
			_log.warn(
				"LCS client " + _lcsConfiguration.lcsClientVersion() +
					" deactivated");
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LCSModuleLifecycle.class);

	private LCSConfiguration _lcsConfiguration;

	@Reference
	private LCSConfigurationProvider _lcsConfigurationProvider;

	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED)
	private ModuleServiceLifecycle _moduleServiceLifecycle;

}