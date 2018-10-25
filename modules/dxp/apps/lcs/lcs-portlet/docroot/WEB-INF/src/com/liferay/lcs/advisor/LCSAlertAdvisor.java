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

package com.liferay.lcs.advisor;

import com.liferay.lcs.internal.event.LCSEvent;
import com.liferay.lcs.internal.event.LCSEventListener;
import com.liferay.lcs.platform.gateway.LCSGatewayClient;
import com.liferay.lcs.util.LCSAlert;
import com.liferay.lcs.util.LCSUtil;
import com.liferay.portal.kernel.license.messaging.LCSPortletState;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Igor Beslic
 */
public class LCSAlertAdvisor implements LCSEventListener {

	public LCSAlertAdvisor(LCSGatewayClient lcsGatewayClient) {
		lcsGatewayClient.registerLCSEventListener(this);
	}

	public boolean add(LCSAlert lcsAlert) {
		return _lcsAlerts.add(lcsAlert);
	}

	public void clear() {
		_lcsAlerts.clear();
	}

	public Set<LCSAlert> getLCSAlerts() {
		return _lcsAlerts;
	}

	@Override
	public void onLCSEvent(LCSEvent lcsEvent) {
		if (lcsEvent == LCSEvent.AVAILABLE) {
			LCSUtil.processLCSPortletState(LCSPortletState.NO_SUBSCRIPTION);

			clear();

			add(LCSAlert.SUCCESS_CONNECTION_TO_LCS_VALID);
		}
		else if (lcsEvent == LCSEvent.UNAVAILABLE) {
			LCSUtil.processLCSPortletState(LCSPortletState.NO_CONNECTION);

			clear();
		}
	}

	public boolean remove(LCSAlert lcsAlert) {
		return _lcsAlerts.remove(lcsAlert);
	}

	private final Set<LCSAlert> _lcsAlerts = new HashSet<>();

}