/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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