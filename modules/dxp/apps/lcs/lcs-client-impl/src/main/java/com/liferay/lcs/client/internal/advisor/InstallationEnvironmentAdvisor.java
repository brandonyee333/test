/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.internal.advisor;

import java.util.Map;

/**
 * @author Igor Beslic
 */
public interface InstallationEnvironmentAdvisor {

	public Map<String, String> getHardwareMetadata();

	public int getProcessorCoresTotal();

	public Map<String, String> getSoftwareMetadata();

}