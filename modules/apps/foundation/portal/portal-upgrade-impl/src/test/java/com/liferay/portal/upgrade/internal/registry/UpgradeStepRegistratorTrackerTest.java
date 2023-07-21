/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.upgrade.internal.registry;

import com.liferay.portal.kernel.dao.db.DBProcessContext;
import com.liferay.portal.kernel.upgrade.UpgradeStep;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Carlos Sierra Andrés
 */
public class UpgradeStepRegistratorTrackerTest {

	@Test
	public void testCreateUpgradeInfos() {
		TestUpgradeStep testUpgradeStep = new TestUpgradeStep();

		List<UpgradeInfo> upgradeInfos =
			UpgradeStepRegistratorTracker.createUpgradeInfos(
				"0.0.0", "1.0.0", 0, testUpgradeStep, testUpgradeStep,
				testUpgradeStep, testUpgradeStep);

		Assert.assertEquals(upgradeInfos.toString(), 4, upgradeInfos.size());
		Assert.assertEquals(
			Arrays.asList(
				new UpgradeInfo("0.0.0", "1.0.0-step-3", 0, testUpgradeStep),
				new UpgradeInfo(
					"1.0.0-step-3", "1.0.0-step-2", 0, testUpgradeStep),
				new UpgradeInfo(
					"1.0.0-step-2", "1.0.0-step-1", 0, testUpgradeStep),
				new UpgradeInfo("1.0.0-step-1", "1.0.0", 0, testUpgradeStep)),
			upgradeInfos);
	}

	@Test
	public void testCreateUpgradeInfosWithNoSteps() {
		List<UpgradeInfo> upgradeInfos =
			UpgradeStepRegistratorTracker.createUpgradeInfos(
				"0.0.0", "1.0.0", 0);

		Assert.assertTrue(upgradeInfos.toString(), upgradeInfos.isEmpty());
	}

	@Test
	public void testCreateUpgradeInfosWithOneStep() {
		TestUpgradeStep testUpgradeStep = new TestUpgradeStep();

		List<UpgradeInfo> upgradeInfos =
			UpgradeStepRegistratorTracker.createUpgradeInfos(
				"0.0.0", "1.0.0", 0, testUpgradeStep);

		Assert.assertEquals(upgradeInfos.toString(), 1, upgradeInfos.size());
		Assert.assertEquals(
			new UpgradeInfo("0.0.0", "1.0.0", 0, testUpgradeStep),
			upgradeInfos.get(0));
	}

	private static class TestUpgradeStep implements UpgradeStep {

		@Override
		public void upgrade(DBProcessContext dbProcessContext) {
		}

	}

}