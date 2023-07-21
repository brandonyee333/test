/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.alert.advisor;

import com.liferay.lcs.client.alert.LCSAlert;
import com.liferay.lcs.client.event.LCSEvent;

import java.util.Set;

/**
 * @author Igor Beslic
 */
public interface LCSAlertAdvisor {

	public void add(LCSAlert lcsAlert);

	public void clear();

	public Set<LCSAlert> getLCSAlerts();

	public void onLCSEvent(LCSEvent lcsEvent);

	public void remove(LCSAlert lcsAlert);

}