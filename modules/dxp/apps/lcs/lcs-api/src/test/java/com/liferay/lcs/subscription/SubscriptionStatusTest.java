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

package com.liferay.lcs.subscription;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Igor Beslic
 */
@RunWith(PowerMockRunner.class)
public class SubscriptionStatusTest extends PowerMockito {

	@Test
	public void testValueOf() {
		for (SubscriptionStatus subscriptionStatus :
				SubscriptionStatus.values()) {

			Assert.assertEquals(
				subscriptionStatus,
				SubscriptionStatus.valueOf(subscriptionStatus.getStatus()));
		}

		Assert.assertEquals(
			SubscriptionStatus.UNDEFINED, SubscriptionStatus.valueOf(-1));
	}

}