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
import com.liferay.lcs.advisor.answer.LCSPortletPreferencesUtilAnswer;
import com.liferay.lcs.internal.event.LCSEvent;
import com.liferay.lcs.util.LCSPortletPreferencesUtil;

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
@PrepareForTest({ManagementFactory.class, LCSPortletPreferencesUtil.class})
@RunWith(PowerMockRunner.class)
public class UptimeAdvisorTest extends PowerMockito {

	@Before
	public void setUp() {
		mockStatic(ManagementFactory.class, LCSPortletPreferencesUtil.class);

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

		_uptimeAdvisor = spy(new UptimeAdvisor(lcsKeyAdvisor));
	}

	@Test
	public void testGetUptimes() throws Exception {
		_uptimeAdvisor.init();

		List<Map<String, Long>> uptimeEntries =
			_uptimeAdvisor.getUptimeEntries();

		Assert.assertEquals(
			"uptimeEntries has one uptime entry", 1, uptimeEntries.size());

		Map<String, Long> uptime = uptimeEntries.get(0);

		Assert.assertTrue(
			"JVM end time greater than start time",
			uptime.get("endTime") > uptime.get("startTime"));
	}

	@Test
	public void testGetUptimesIfPersistedUptimesAvailable() throws Exception {
		_portletPreferences.setValue(
			"uptimes-lcsServerId",
			"[{\"startTime\":\"1539092605095\",\"endTime\":\"1539098932697\"}" +
				"]");

		_uptimeAdvisor.init();

		List<Map<String, Long>> uptimeEntries =
			_uptimeAdvisor.getUptimeEntries();

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
		_portletPreferences.setValue(
			"uptimes-lcsServerId",
			"[{\"startTime\":\"1539092605095\",\"endTime\":\"1539098932697\"}" +
				",{\"startTime\":\"1539099932697\",\"endTime\":" +
					"\"1539099992697\"}]");

		doAnswer(
			new LCSPortletPreferencesUtilAnswer(_portletPreferences)
		).when(
			LCSPortletPreferencesUtil.class, "store", Matchers.anyString(),
			Matchers.anyString()
		);

		_uptimeAdvisor.init();

		List<Map<String, Long>> uptimeEntries =
			_uptimeAdvisor.getUptimeEntries();

		Assert.assertEquals(
			"two uptime entries before handshake", 3, uptimeEntries.size());

		_uptimeAdvisor.onLCSEvent(LCSEvent.HANDSHAKE_SUCCESS);

		uptimeEntries = _uptimeAdvisor.getUptimeEntries();

		Map<String, Long> afterHandshake = uptimeEntries.get(0);

		Assert.assertEquals(
			"only one uptime entry after handshake", 1, uptimeEntries.size());

		Assert.assertTrue(
			"new start time equals to end time in previous session",
			afterHandshake.get("endTime") > afterHandshake.get("startTime"));
	}

	@Test
	public void testOnUnregisterLCSEvent() throws Exception {
		doAnswer(
			new LCSPortletPreferencesUtilAnswer(_portletPreferences)
		).when(
			LCSPortletPreferencesUtil.class, "store", Matchers.anyString(),
			Matchers.anyString()
		);

		_uptimeAdvisor.init();

		List<Map<String, Long>> uptimeEntries =
			_uptimeAdvisor.getUptimeEntries();

		Assert.assertEquals(
			"only one uptime entry before unregister", 1, uptimeEntries.size());

		_uptimeAdvisor.onLCSEvent(LCSEvent.LCS_CLUSTER_NODE_UNREGISTERED);

		uptimeEntries = _uptimeAdvisor.getUptimeEntries();

		Map<String, Long> beforeUnregister = uptimeEntries.get(0);
		Map<String, Long> afterUnregister = uptimeEntries.get(1);

		Assert.assertEquals(
			"new start time equals to end time in previous session",
			beforeUnregister.get("endTime"), afterUnregister.get("startTime"));
	}

	@Test
	public void testResetCurrentUptimeEndTime() throws Exception {
		_portletPreferences.setValue(
			"uptimes-lcsServerId",
			"[{\"startTime\":\"1539092605095\",\"endTime\":\"1539098932697\"}" +
				",{\"startTime\":\"1539099932697\",\"endTime\":" +
					"\"1539099992697\"}]");

		_uptimeAdvisor.init();

		List<Map<String, Long>> uptimeEntries =
			_uptimeAdvisor.getUptimeEntries();

		Assert.assertEquals(
			"uptime entries expected size", 3, uptimeEntries.size());

		Map<String, Long> uptime = uptimeEntries.get(2);

		Assert.assertNotNull(
			"entry with current uptime has end time defined",
			uptime.get("endTime"));

		_uptimeAdvisor.resetCurrentUptimeEndTime(uptimeEntries);

		uptime = uptimeEntries.get(2);

		Assert.assertNull(
			"entry with current uptime has end time null after reset",
			uptime.get("endTime"));
	}

	@Test
	public void testUpdateCurrentUptime() throws Exception {
		_uptimeAdvisor.init();

		List<Map<String, Long>> uptimeEntries =
			_uptimeAdvisor.getUptimeEntries();

		Map<String, Long> uptime = uptimeEntries.get(0);

		long endTimeBeforeUpdate = uptime.get("endTime");
		long startTimeBeforeUpdate = uptime.get("startTime");

		try {
			Thread.sleep(300);
		}
		catch (InterruptedException ie) {
		}

		_uptimeAdvisor.updateCurrentUptime();

		uptimeEntries = _uptimeAdvisor.getUptimeEntries();

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

	private final PortletPreferences _portletPreferences =
		new MockPortletPreferencesImpl();
	private UptimeAdvisor _uptimeAdvisor;

}