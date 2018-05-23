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

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Ivica Cardic
 */
public class InstallationEnvironmentAdvisorTest {

	@Test
	public void testGetHardwareMetadata() {
		Map<String, String> hardwareMetadata =
			_installationEnvironmentAdvisor.getHardwareMetadata();

		Assert.assertNotNull(hardwareMetadata.get("cpu.total.cores"));
		Assert.assertNotNull(hardwareMetadata.get("fs.root"));
		Assert.assertNotNull(hardwareMetadata.get("fs.root.total.space"));
		Assert.assertNotNull(hardwareMetadata.get("fs.root.usable.space"));
		Assert.assertNotNull(hardwareMetadata.get("physical.memory.free"));
		Assert.assertNotNull(hardwareMetadata.get("physical.memory.total"));
		Assert.assertNotNull(hardwareMetadata.get("swap.free"));
		Assert.assertNotNull(hardwareMetadata.get("swap.total"));
	}

	private final InstallationEnvironmentAdvisor
		_installationEnvironmentAdvisor =
			InstallationEnvironmentAdvisorFactory.getInstance();

}