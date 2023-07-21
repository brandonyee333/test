/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.internal.advisor;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Ivica Cardic
 */
public class InstallationEnvironmentAdvisorTest {

	@Test
	public void testGetHardwareMetadata() {
		InstallationEnvironmentAdvisor installationEnvironmentAdvisor =
			new DefaultInstallationEnvironmentAdvisor();

		Map<String, String> hardwareMetadata =
			installationEnvironmentAdvisor.getHardwareMetadata();

		Assert.assertNotNull(hardwareMetadata.get("cpu.total.cores"));
		Assert.assertNotNull(hardwareMetadata.get("fs.root"));
		Assert.assertNotNull(hardwareMetadata.get("fs.root.total.space"));
		Assert.assertNotNull(hardwareMetadata.get("fs.root.usable.space"));
	}

}