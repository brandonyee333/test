/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.trash;

import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.trash.bundle.trashhandlerregistryutil.TestTrashHandler;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.SyntheticBundleRule;

import java.util.List;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Peter Fellwock
 */
public class TrashHandlerRegistryUtilTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			new SyntheticBundleRule("bundle.trashhandlerregistryutil"));

	@Test
	public void testGetTrashHandler() {
		TrashHandler trashHandler = TrashHandlerRegistryUtil.getTrashHandler(
			TestTrashHandler.class.getName());

		Class<?> clazz = trashHandler.getClass();

		Assert.assertEquals(TestTrashHandler.class.getName(), clazz.getName());
	}

	@Test
	public void testGetTrashHandlers() {
		List<TrashHandler> trashHandlers =
			TrashHandlerRegistryUtil.getTrashHandlers();

		for (TrashHandler trashHandler : trashHandlers) {
			Class<?> clazz = trashHandler.getClass();

			String className = clazz.getName();

			if (className.equals(TestTrashHandler.class.getName())) {
				return;
			}
		}

		Assert.fail();
	}

}