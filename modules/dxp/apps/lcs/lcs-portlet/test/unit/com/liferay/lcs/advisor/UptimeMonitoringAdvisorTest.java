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

import com.liferay.lcs.MockPortletPreferencesImpl;
import com.liferay.lcs.util.LCSPortletPreferencesUtil;
import com.liferay.lcs.util.LCSUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.util.PropsUtil;

import java.lang.management.ManagementFactory;

import java.util.List;
import java.util.Map;

import javax.portlet.PortletPreferences;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Ivica Cardic
 */
@PrepareForTest(
	{
		JSONFactoryUtil.class, LCSUtil.class, ManagementFactory.class,
		PropsUtil.class, LCSPortletPreferencesUtil.class
	}
)
@RunWith(PowerMockRunner.class)
public class UptimeMonitoringAdvisorTest extends PowerMockito {

	@Before
	public void setUp() throws Exception {
		mockStatic(
			LCSUtil.class, ManagementFactory.class,
			LCSPortletPreferencesUtil.class);

		when(
			LCSPortletPreferencesUtil.fetchReadOnlyJxPortletPreferences()
		).thenReturn(
			_portletPreferences
		);

		LCSKeyAdvisor lcsKeyAdvisor = spy(new LCSKeyAdvisor());

		doReturn(
			"lcsServerId"
		).when(
			lcsKeyAdvisor
		).getKey();

		_uptimeMonitoringAdvisor.setLCSKeyAdvisor(lcsKeyAdvisor);

		_uptimeMonitoringAdvisor.init();
	}

	@Test
	public void testGetUptimes() throws Exception {
		List<Map<String, Long>> uptimes = _uptimeMonitoringAdvisor.getUptimes();

		Assert.assertEquals("uptimes has one uptime entry", 1, uptimes.size());

		Map<String, Long> uptime = uptimes.get(0);

		Assert.assertTrue(
			"JVM end time greater than start time",
			uptime.get("endTime") > uptime.get("startTime"));
	}

	@Test
	public void testGetUptimesIfPortletPreferencesUptimeAvailable()
		throws Exception {

		_portletPreferences.setValue(
			"uptimes-lcsServerId",
			"[{\"startTime\":\"1539092605095\",\"endTime\":\"1539098932697\"}" +
				"]");

		List<Map<String, Long>> uptimes = _uptimeMonitoringAdvisor.getUptimes();

		Assert.assertEquals(
			"uptimes has two uptime entries", 2, uptimes.size());

		Map<String, Long> uptime = uptimes.get(0);

		Assert.assertEquals(
			"portlet preferences uptime end time", Long.valueOf(1539098932697L),
			uptime.get("endTime"));
		Assert.assertEquals(
			"portlet preferences uptime start time",
			Long.valueOf(1539092605095L), uptime.get("startTime"));
	}

	@Test
	public void testResetCurrentUptimeEndTime() throws Exception {
		_portletPreferences.setValue(
			"uptimes-lcsServerId",
			"[{\"startTime\":\"1539092605095\",\"endTime\":\"1539098932697\"}" +
				",{\"startTime\":\"1539099932697\",\"endTime\":" +
					"\"1539099992697\"}]");

		List<Map<String, Long>> uptimes = _uptimeMonitoringAdvisor.getUptimes();

		Assert.assertEquals(
			"uptimes has two uptime entries", 3, uptimes.size());

		Map<String, Long> uptime = uptimes.get(2);

		Assert.assertNotNull(
			"current uptime end time defined", uptime.get("endTime"));

		_uptimeMonitoringAdvisor.resetCurrentUptimeEndTime(uptimes);

		uptime = uptimes.get(2);

		Assert.assertNull(
			"current uptime end time must be null after reset",
			uptime.get("endTime"));
	}

	@Test
	public void testUpdateCurrentUptime() throws Exception {
		List<Map<String, Long>> uptimes = _uptimeMonitoringAdvisor.getUptimes();

		Map<String, Long> uptime = uptimes.get(0);

		long endTimeBeforeUpdate = uptime.get("endTime");

		_uptimeMonitoringAdvisor.resetUptimes();

		try {
			Thread.sleep(300);
		}
		catch (InterruptedException ie) {
		}

		_uptimeMonitoringAdvisor.updateCurrentUptime();

		uptimes = _uptimeMonitoringAdvisor.getUptimes();

		uptime = uptimes.get(0);

		long updatedEndTime = uptime.get("endTime");

		Assert.assertNotEquals(endTimeBeforeUpdate, updatedEndTime);
		Assert.assertTrue(
			"updated end time greater than old end time",
			updatedEndTime > endTimeBeforeUpdate);
	}

	private final PortletPreferences _portletPreferences =
		new MockPortletPreferencesImpl();
	private final UptimeMonitoringAdvisor _uptimeMonitoringAdvisor = spy(
		new UptimeMonitoringAdvisor());

}