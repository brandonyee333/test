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

package com.liferay.osb.asah.common.upgrade.test;

import com.liferay.osb.asah.common.OSBAsahCommonSpringTestContext;
import com.liferay.osb.asah.common.dog.AsahMarkerDog;
import com.liferay.osb.asah.common.dog.ProjectDog;
import com.liferay.osb.asah.common.entity.AsahMarker;
import com.liferay.osb.asah.common.entity.Project;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.upgrade.UpgradeCheck;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.util.ReleaseInfo;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.Collections;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;

/**
 * @author André Miranda
 */
public class UpgradeCheckTest
	implements OSBAsahCommonSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@BeforeEach
	public void setUp() throws Exception {
		Mockito.when(
			_projectDog.getProjects()
		).thenReturn(
			Collections.singletonList(new Project("project1"))
		);

		_upgradeCheck = new UpgradeCheck();

		ReflectionTestUtils.setField(
			_upgradeCheck, "_asahMarkerDog", _asahMarkerDog);
		ReflectionTestUtils.setField(_upgradeCheck, "_projectDog", _projectDog);
	}

	@AfterEach
	public void tearDown() throws Exception {

		// TODO

	}

	@Test
	public void testCheckVersionEmpty() throws Exception {
		Assertions.assertTrue(_upgradeCheck.checkVersion());
	}

	@Test
	public void testCheckVersionNotUpgrade() throws Exception {
		_addUpgradeMarker("project1", "2.8.0");

		Assertions.assertFalse(_upgradeCheck.checkVersion());
	}

	@Test
	public void testCheckVersionUpgraded() throws Exception {
		_addUpgradeMarker("project1", ReleaseInfo.getVersion());

		Assertions.assertTrue(_upgradeCheck.checkVersion());
	}

	@Test
	public void testCheckVersionUpgradedButOne() throws Exception {
		_addUpgradeMarker("project1", "2.8.0");

		Assertions.assertFalse(_upgradeCheck.checkVersion());
	}

	private void _addUpgradeMarker(String projectId, String version) {
		ProjectIdThreadLocal.forProject(
			projectId,
			() -> _asahMarkerDog.addAsahMarker(
				new AsahMarker("Upgrade", JSONUtil.put("version", version))));
	}

	@Autowired
	private AsahMarkerDog _asahMarkerDog;

	@Mock
	private ProjectDog _projectDog;

	private UpgradeCheck _upgradeCheck;

}