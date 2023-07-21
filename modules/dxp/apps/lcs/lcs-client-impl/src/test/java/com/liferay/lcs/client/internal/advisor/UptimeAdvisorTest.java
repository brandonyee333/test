/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.internal.advisor;

import com.liferay.lcs.client.event.LCSEvent;
import com.liferay.lcs.client.internal.MockPortletPreferencesImpl;
import com.liferay.lcs.client.internal.advisor.answer.LCSPortletPreferencesUtilAnswer;
import com.liferay.lcs.client.internal.event.LCSEventManager;
import com.liferay.lcs.client.internal.util.LCSPortletPreferences;

import java.lang.management.ManagementFactory;

import java.util.List;
import java.util.Map;

import javax.portlet.PortletPreferences;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Matchers;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
@PrepareForTest(ManagementFactory.class)
@RunWith(PowerMockRunner.class)
public class UptimeAdvisorTest extends PowerMockito {

	@Before
	public void setUp() {
		mockStatic(ManagementFactory.class);
	}

	@Test
	public void testGetUptimes() throws Exception {
		UptimeAdvisor uptimeAdvisor = _spyUptimeAdvisor(
			"lcsServerId", _spyLCSPortletPreferences());

		uptimeAdvisor.activate();

		List<Map<String, Long>> uptimeEntries =
			uptimeAdvisor.getUptimeEntries();

		Assert.assertEquals(
			"uptimeEntries has one uptime entry", 1, uptimeEntries.size());

		Map<String, Long> uptime = uptimeEntries.get(0);

		Assert.assertTrue(
			"JVM end time greater than start time",
			uptime.get("endTime") > uptime.get("startTime"));
	}

	@Test
	public void testGetUptimesIfPersistedUptimesAvailable() throws Exception {
		LCSPortletPreferences lcsPortletPreferences = _spyLCSPortletPreferences(
			"lcsServerId",
			"[{\"startTime\":\"1539092605095\",\"endTime\":\"1539098932697\"}" +
				"]");

		UptimeAdvisor uptimeAdvisor = _spyUptimeAdvisor(
			"lcsServerId", lcsPortletPreferences);

		uptimeAdvisor.activate();

		List<Map<String, Long>> uptimeEntries =
			uptimeAdvisor.getUptimeEntries();

		Assert.assertEquals(
			"uptimeEntries has two uptime entries", 2, uptimeEntries.size());

		Map<String, Long> uptime = uptimeEntries.get(0);

		Assert.assertEquals(
			"portlet preferences uptime end time", Long.valueOf(1539098932697L),
			uptime.get("endTime"));
		Assert.assertEquals(
			"portlet preferences uptime start time",
			Long.valueOf(1539092605095L), uptime.get("startTime"));
	}

	@Test
	public void testOnHandshakeSuccessLCSEvent() throws Exception {
		LCSPortletPreferences lcsPortletPreferences = _spyLCSPortletPreferences(
			"lcsServerId",
			"[{\"startTime\":\"1539092605095\",\"endTime\":\"1539098932697\"}" +
				",{\"startTime\":\"1539099932697\",\"endTime\":" +
					"\"1539099992697\"}]");

		UptimeAdvisor uptimeAdvisor = _spyUptimeAdvisor(
			"lcsServerId", lcsPortletPreferences);

		uptimeAdvisor.activate();

		List<Map<String, Long>> uptimeEntries =
			uptimeAdvisor.getUptimeEntries();

		Assert.assertEquals(
			"two uptime entries before handshake", 3, uptimeEntries.size());

		uptimeAdvisor.onLCSEvent(LCSEvent.HANDSHAKE_SUCCESS);

		uptimeEntries = uptimeAdvisor.getUptimeEntries();

		Map<String, Long> afterHandshake = uptimeEntries.get(0);

		Assert.assertEquals(
			"only one uptime entry after handshake", 1, uptimeEntries.size());

		Assert.assertTrue(
			"new start time equals to end time in previous session",
			afterHandshake.get("endTime") > afterHandshake.get("startTime"));
	}

	@Test
	public void testOnUnregisterLCSEvent() throws Exception {
		UptimeAdvisor uptimeAdvisor = _spyUptimeAdvisor(
			"lcsServerId", _spyLCSPortletPreferences());

		uptimeAdvisor.activate();

		List<Map<String, Long>> uptimeEntries =
			uptimeAdvisor.getUptimeEntries();

		Assert.assertEquals(
			"only one uptime entry before unregister", 1, uptimeEntries.size());

		uptimeAdvisor.onLCSEvent(LCSEvent.LCS_CLUSTER_NODE_UNREGISTERED);

		uptimeEntries = uptimeAdvisor.getUptimeEntries();

		Map<String, Long> beforeUnregister = uptimeEntries.get(0);
		Map<String, Long> afterUnregister = uptimeEntries.get(1);

		Assert.assertEquals(
			"new start time equals to end time in previous session",
			beforeUnregister.get("endTime"), afterUnregister.get("startTime"));
	}

	@Test
	public void testResetCurrentUptimeEndTime() throws Exception {
		LCSPortletPreferences lcsPortletPreferences = _spyLCSPortletPreferences(
			"lcsServerId",
			"[{\"startTime\":\"1539092605095\",\"endTime\":\"1539098932697\"}" +
				",{\"startTime\":\"1539099932697\",\"endTime\":" +
					"\"1539099992697\"}]");

		UptimeAdvisor uptimeAdvisor = _spyUptimeAdvisor(
			"lcsServerId", lcsPortletPreferences);

		uptimeAdvisor.activate();

		List<Map<String, Long>> uptimeEntries =
			uptimeAdvisor.getUptimeEntries();

		Assert.assertEquals(
			"uptime entries expected size", 3, uptimeEntries.size());

		Map<String, Long> uptime = uptimeEntries.get(2);

		Assert.assertNotNull(
			"entry with current uptime has end time defined",
			uptime.get("endTime"));

		uptimeAdvisor.resetCurrentUptimeEndTime(uptimeEntries);

		uptime = uptimeEntries.get(2);

		Assert.assertNull(
			"entry with current uptime has end time null after reset",
			uptime.get("endTime"));
	}

	@Test
	public void testUpdateCurrentUptime() throws Exception {
		UptimeAdvisor uptimeAdvisor = _spyUptimeAdvisor(
			"lcsServerId", _spyLCSPortletPreferences());

		uptimeAdvisor.activate();

		List<Map<String, Long>> uptimeEntries =
			uptimeAdvisor.getUptimeEntries();

		Map<String, Long> uptime = uptimeEntries.get(0);

		long endTimeBeforeUpdate = uptime.get("endTime");
		long startTimeBeforeUpdate = uptime.get("startTime");

		try {
			Thread.sleep(300);
		}
		catch (InterruptedException ie) {
		}

		uptimeAdvisor.updateCurrentUptime();

		uptimeEntries = uptimeAdvisor.getUptimeEntries();

		uptime = uptimeEntries.get(0);

		long endTimeAfterUpdate = uptime.get("endTime");

		long startTimeAfterUpdate = uptime.get("startTime");

		Assert.assertEquals(
			"start time before update equals to start time after update",
			startTimeBeforeUpdate, startTimeAfterUpdate);

		Assert.assertTrue(
			"end time after update greater than end time before update",
			endTimeAfterUpdate > endTimeBeforeUpdate);
	}

	private LCSPortletPreferences _spyLCSPortletPreferences() throws Exception {
		return _spyLCSPortletPreferences(null, null);
	}

	private LCSPortletPreferences _spyLCSPortletPreferences(
			String mockKey, String mockValues)
		throws Exception {

		LCSPortletPreferences lcsPortletPreferences = spy(
			new LCSPortletPreferences());

		PortletPreferences portletPreferences =
			new MockPortletPreferencesImpl();

		if ((mockKey != null) && (mockValues != null)) {
			portletPreferences.setValue("uptimes-" + mockKey, mockValues);
		}

		doReturn(
			portletPreferences
		).when(
			lcsPortletPreferences
		).fetchReadOnlyJxPortletPreferences();

		doAnswer(
			new LCSPortletPreferencesUtilAnswer(portletPreferences)
		).when(
			lcsPortletPreferences
		).store(
			Matchers.anyString(), Matchers.anyString()
		);

		return lcsPortletPreferences;
	}

	private UptimeAdvisor _spyUptimeAdvisor(
		String mockKey, LCSPortletPreferences lcsPortletPreferences) {

		LCSKeyAdvisor lcsKeyAdvisor = spy(new LCSKeyAdvisor());

		doReturn(
			mockKey
		).when(
			lcsKeyAdvisor
		).getKey();

		LCSEventManager lcsEventManager = new LCSEventManager();

		return new UptimeAdvisor(
			lcsEventManager, lcsKeyAdvisor, lcsPortletPreferences);
	}

}