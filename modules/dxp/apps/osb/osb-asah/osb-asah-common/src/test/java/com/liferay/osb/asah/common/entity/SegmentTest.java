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

package com.liferay.osb.asah.common.entity;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Marcos Martins
 */
public class SegmentTest {

	@Test
	public void testGetReferencedAssetIds() {
		Segment segment = new Segment();

		segment.setFilter(
			String.join(
				"(activities.filterByCount(filter='(activityKey eq ",
				"''Form#formViewed#5687'' and day gt ''last24Hours'')',",
				"operator='ge',value=1))"));

		List<Long> referencedAssetIds = new ArrayList(
			segment.getReferencedAssetIds());

		Assertions.assertEquals(
			1, referencedAssetIds.size(), referencedAssetIds.toString());
		Assertions.assertEquals(5687, referencedAssetIds.get(0));
	}

}