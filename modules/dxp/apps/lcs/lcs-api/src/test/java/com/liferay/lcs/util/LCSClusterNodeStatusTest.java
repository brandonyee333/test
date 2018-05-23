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

package com.liferay.lcs.util;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Igor Beslic
 */
@RunWith(PowerMockRunner.class)
public class LCSClusterNodeStatusTest extends PowerMockito {

	@Test
	public void testGetObjectArrays() {
		int status = LCSClusterNodeStatus.ACTIVE.merge(
			LCSClusterNodeStatus.DATA_INITIALIZED.getStatus());

		Map<LCSClusterNodeStatus, Object[]> lcsClusterNodeStatusObjectArrays =
			LCSClusterNodeStatus.getObjectArrays(status);

		for (Map.Entry<LCSClusterNodeStatus, Object[]> entry :
				lcsClusterNodeStatusObjectArrays.entrySet()) {

			Object[] objectArray = entry.getValue();

			String label = (String)objectArray[0];
			Boolean statusEnabled = (Boolean)objectArray[1];
			Boolean statusExpected = (Boolean)objectArray[2];

			LCSClusterNodeStatus lcsClusterNodeStatus = entry.getKey();

			if ((lcsClusterNodeStatus == LCSClusterNodeStatus.ACTIVE) ||
				(lcsClusterNodeStatus ==
					LCSClusterNodeStatus.DATA_INITIALIZED)) {

				Assert.assertEquals(lcsClusterNodeStatus.getLabel(), label);
				Assert.assertTrue(statusEnabled);
			}
			else {
				Assert.assertEquals(
					lcsClusterNodeStatus.getOppositeLabel(), label);
				Assert.assertFalse(statusEnabled);
			}

			if (lcsClusterNodeStatus ==
					LCSClusterNodeStatus.HEARTBEAT_DELAYED) {

				Assert.assertFalse(statusExpected);
			}
			else {
				Assert.assertTrue(statusExpected);
			}
		}
	}

}