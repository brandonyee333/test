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

package com.liferay.osb.asah.common.model;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Marcos Martins
 */
public class SiteVisitorBehaviorMetricTest {

	@Test
	public void testGetSessionsPerVisitor() {
		SiteVisitorBehaviorMetric siteVisitorBehaviorMetric =
			new SiteVisitorBehaviorMetric();

		siteVisitorBehaviorMetric.setSessions(BigDecimal.valueOf(7));
		siteVisitorBehaviorMetric.setVisitors(BigDecimal.valueOf(3));

		Assertions.assertEquals(
			2.33, siteVisitorBehaviorMetric.getSessionsPerVisitor());
	}

}