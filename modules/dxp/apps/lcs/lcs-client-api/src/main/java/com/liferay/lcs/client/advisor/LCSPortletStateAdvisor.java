/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.advisor;

import com.liferay.portal.kernel.license.messaging.LCSPortletState;

/**
 * @author Igor Beslic
 */
public interface LCSPortletStateAdvisor {

	public long getLastLicenseCheckTime();

	public LCSPortletState getLCSPortletState(boolean checkSubscription);

	public long updateLicenseCheckTime();

}