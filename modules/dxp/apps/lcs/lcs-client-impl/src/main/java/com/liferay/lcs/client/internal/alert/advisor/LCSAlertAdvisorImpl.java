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

package com.liferay.lcs.client.internal.alert.advisor;

import com.liferay.lcs.client.alert.LCSAlert;
import com.liferay.lcs.client.alert.advisor.LCSAlertAdvisor;
import com.liferay.lcs.client.event.LCSEvent;
import com.liferay.lcs.client.event.LCSEventListener;
import com.liferay.lcs.client.internal.event.LCSEventManager;
import com.liferay.lcs.client.internal.util.LCSUtil;
import com.liferay.portal.kernel.license.messaging.LCSPortletState;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Igor Beslic
 */
@Component(immediate = true, service = LCSAlertAdvisor.class)
public class LCSAlertAdvisorImpl implements LCSAlertAdvisor, LCSEventListener {

	public LCSAlertAdvisorImpl() {
	}

	public LCSAlertAdvisorImpl(LCSEventManager lcsEventManager) {
		_lcsEventManager = lcsEventManager;

		activate();
	}

	@Activate
	public void activate() {
		_lcsEventManager.subscribe(LCSEvent.HANDSHAKE_FAILED, this);
		_lcsEventManager.subscribe(LCSEvent.HANDSHAKE_SUCCESS, this);
		_lcsEventManager.subscribe(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_CHECK_SUCCESS, this);
		_lcsEventManager.subscribe(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_MISSING, this);
		_lcsEventManager.subscribe(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_MULTIPLE_TOKENS, this);
		_lcsEventManager.subscribe(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_CHECK_TOKEN_CORRUPTED, this);
		_lcsEventManager.subscribe(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_INVALID, this);
		_lcsEventManager.subscribe(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_ENVIRONMENT_MISMATCH, this);
		_lcsEventManager.subscribe(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_INVALID_USER_CREDENTIALS, this);
		_lcsEventManager.subscribe(LCSEvent.LCS_GATEWAY_UNAVAILABLE, this);
	}

	public void add(LCSAlert lcsAlert) {
		_lcsAlerts.put(lcsAlert, lcsAlert);
	}

	public void clear() {
		_lcsAlerts.clear();
	}

	public Set<LCSAlert> getLCSAlerts() {
		return _lcsAlerts.keySet();
	}

	@Override
	public void onLCSEvent(LCSEvent lcsEvent) {
		if (lcsEvent == LCSEvent.HANDSHAKE_FAILED) {
			add(LCSAlert.WARNING_HANDSHAKE_FAILED);
		}
		else if (lcsEvent == LCSEvent.HANDSHAKE_SUCCESS) {
			remove(LCSAlert.WARNING_HANDSHAKE_FAILED);
		}
		else if (lcsEvent == LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_CHECK_SUCCESS) {
			add(LCSAlert.SUCCESS_VALID_TOKEN);
		}
		else if (lcsEvent == LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_MISSING) {
			add(LCSAlert.ERROR_MISSING_TOKEN);
		}
		else if (lcsEvent == LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_MULTIPLE_TOKENS) {
			add(LCSAlert.ERROR_MULTIPLE_TOKENS);
		}
		else if ((lcsEvent ==
					LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_CHECK_TOKEN_CORRUPTED) ||
				 (lcsEvent == LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_INVALID)) {

			add(LCSAlert.ERROR_INVALID_TOKEN);
		}
		else if (lcsEvent ==
					LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_ENVIRONMENT_MISMATCH) {

			add(LCSAlert.ERROR_ENVIRONMENT_MISMATCH);
		}
		else if (lcsEvent ==
					LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_INVALID_USER_CREDENTIALS) {

			add(LCSAlert.ERROR_INVALID_USER_CREDENTIALS);
		}
		else if (lcsEvent == LCSEvent.LCS_GATEWAY_AVAILABLE) {
			LCSUtil.processLCSPortletState(LCSPortletState.NO_SUBSCRIPTION);

			clear();

			add(LCSAlert.SUCCESS_CONNECTION_TO_LCS_VALID);
		}
		else if (lcsEvent == LCSEvent.LCS_GATEWAY_UNAVAILABLE) {
			LCSUtil.processLCSPortletState(LCSPortletState.NO_CONNECTION);

			clear();
		}
	}

	public void remove(LCSAlert lcsAlert) {
		_lcsAlerts.remove(lcsAlert);
	}

	private final Map<LCSAlert, LCSAlert> _lcsAlerts =
		new ConcurrentHashMap<>();

	@Reference
	private LCSEventManager _lcsEventManager;

}