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

package com.liferay.lcs.messaging.echo.sample1.web.internal.activator;

import com.liferay.lcs.messaging.bus.LCSMessageBusService;
import com.liferay.lcs.messaging.bus.LCSMessageListener;
import com.liferay.lcs.messaging.echo.sample1.web.internal.messaging.EchoLCSMessageListener;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Ferrari
 */
@Component(immediate = true)
public class EchoActivator {

	@Activate
	public void start() {
		_lcsMessageListener = new EchoLCSMessageListener(_lcsMessageBusService);

		_lcsMessageBusService.registerLCSMessageListener(
			"lcs_echo", _lcsMessageListener);
	}

	@Deactivate
	public void stop() {
		_lcsMessageBusService.unregisterLCSMessageListener(
			"lcs_echo", _lcsMessageListener);
	}

	@Reference
	private LCSMessageBusService _lcsMessageBusService;

	private LCSMessageListener _lcsMessageListener;

}