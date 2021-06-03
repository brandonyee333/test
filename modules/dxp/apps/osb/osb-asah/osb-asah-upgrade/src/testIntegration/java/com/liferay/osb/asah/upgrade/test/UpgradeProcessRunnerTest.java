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

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.Project;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.multitenancy.ProjectDog;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 * @author André Miranda
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahUpgradeSpringBootApplication.class)
public class UpgradeProcessRunnerTest {

	@Before
	public void setUp() {
		_elasticsearchIndexManager.delete(
			"test1_osbasahfaroinfo_osbasahmarkers",
			"test2_osbasahfaroinfo_osbasahmarkers");
	}

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
			_upgradeProcess.getUpgradeSteps(ArgumentMatchers.eq("0.0.0"))
		).thenReturn(
			Collections.singletonList(upgradeStep)
		);

		Mockito.when(
			_upgradeProcess.getToVersionString(ArgumentMatchers.eq("0.0.0"))
		).thenReturn(
			"1.0.0"
		);

		_upgradeProcessRunner.run();

		Assert.assertEquals(projectIds.toString(), 2, projectIds.size());
		Assert.assertTrue(projectIds.contains("test1"));
		Assert.assertTrue(projectIds.contains("test2"));
	}

	@Test
	public void testVersionFormat() throws Exception {
		Project project = new Project("test1");

		ProjectIdThreadLocal.forProject(
			project,
			() -> _elasticsearchInvoker.add(
				"OSBAsahMarkers",
				JSONUtil.put(
					"context", JSONUtil.put("version", "2.11.0")
				).put(
					"id", "Upgrade"
				)));

		Mockito.when(
			_projectDog.getProjects()
		).thenReturn(
			Collections.singletonList(project)
		);

		Mockito.when(
			_upgradeProcess.getUpgradeSteps(ArgumentMatchers.eq("2.11.0"))
		).thenReturn(
			Collections.singletonList(
				version -> {
				})
		);

		Mockito.when(
			_upgradeProcess.getToVersionString(ArgumentMatchers.eq("2.11.0"))
		).thenReturn(
			"2.12.0"
		);

		_upgradeProcessRunner.run();

		ProjectIdThreadLocal.forProject(
			project,
			() -> JSONAssert.assertEquals(
				JSONUtil.put(
					"context", JSONUtil.put("version", "2.12.0")
				).put(
					"id", "Upgrade"
				),
				_elasticsearchInvoker.fetch("OSBAsahMarkers", "Upgrade"),
				true));
	}

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

	@MockBean
	private ProjectDog _projectDog;

	@MockBean
	private UpgradeProcess _upgradeProcess;

	@Autowired
	@InjectMocks
	private UpgradeProcessRunner _upgradeProcessRunner;

}