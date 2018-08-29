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

package com.liferay.lcs;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Igor Beslic
 * @since   LCS 10.0.5
 */
public enum LCSService {

	CLUSTER_HEALTH(true, "cluster-health-lcs-service", LCSServiceScope.CLUSTER),
	HARDWARE_SETTINGS(
		true, "hardware-settings-lcs-service", LCSServiceScope.NODE),
	METRICS(false, "metrics-lcs-service", LCSServiceScope.NODE),
	PATCHES(false, "patches-lcs-service", LCSServiceScope.NODE),
	PORTAL_PROPERTIES(
		false, "portal-properties-lcs-service", LCSServiceScope.NODE),
	SUBSCRIPTION_VALIDATION(
		false, "subscription-validation-lcs-service", LCSServiceScope.NODE),
	SYSTEM_SETTINGS(true, "system-settings-lcs-service", LCSServiceScope.NODE);

	public String getLabel() {
		return _label;
	}

	public LCSServiceScope getLCSServiceScope() {
		return _lcsServiceScope;
	}

	public List<LCSService> getOptional() {
		List<LCSService> lcsServices = new ArrayList<LCSService>();

		for (LCSService lcsService : values()) {
			if (lcsService._required) {
				continue;
			}

			lcsServices.add(lcsService);
		}

		return lcsServices;
	}

	private LCSService(
		boolean required, String label, LCSServiceScope lcsServiceScope) {

		_required = required;
		_label = label;
		_lcsServiceScope = lcsServiceScope;
	}

	private final String _label;
	private final LCSServiceScope _lcsServiceScope;
	private final boolean _required;

}