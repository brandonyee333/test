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

package com.liferay.osb.asah.upgrade.test;

import com.liferay.osb.asah.common.model.Project;
import com.liferay.osb.asah.common.multitenancy.ProjectDog;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.upgrade.UpgradeProcess;
import com.liferay.osb.asah.upgrade.UpgradeProcessRunner;
import com.liferay.osb.asah.upgrade.UpgradeStep;
import com.liferay.osb.asah.upgrade.spring.OSBAsahUpgradeSpringBootApplication;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 * @author André Miranda
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahUpgradeSpringBootApplication.class)
public class UpgradeProcessRunnerTest {

	@Test
	public void testProjectId() throws Exception {
		Mockito.when(
			_projectDog.getProjects()
		).thenReturn(
			Arrays.asList(new Project("test1"), new Project("test2"))
		);

		Set<String> projectIds = new HashSet<>();

		UpgradeStep upgradeStep = version -> projectIds.add(
			ProjectIdThreadLocal.getProjectId());

		Mockito.when(
			_upgradeProcess.getUpgradeSteps(Mockito.isNull())
		).thenReturn(
			Collections.singletonList(upgradeStep)
		);

		Mockito.when(
			_upgradeProcess.getToVersionString(Mockito.isNull())
		).thenReturn(
			"1.0.0"
		);

		_upgradeProcessRunner.run();

		Assert.assertEquals(projectIds.toString(), 2, projectIds.size());
		Assert.assertTrue(projectIds.contains("test1"));
		Assert.assertTrue(projectIds.contains("test2"));
	}

	@MockBean
	private ProjectDog _projectDog;

	@MockBean
	private UpgradeProcess _upgradeProcess;

	@Autowired
	@InjectMocks
	private UpgradeProcessRunner _upgradeProcessRunner;

}