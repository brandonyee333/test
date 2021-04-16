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

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.Project;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.multitenancy.ProjectDog;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.common.upgrade.UpgradeCheck;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.util.ReleaseInfo;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.util.ReflectionTestUtils;

/**
 * @author André Miranda
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class UpgradeCheckTest {

	@Before
	public void setUp() throws Exception {
		List<Project> projects = Arrays.asList(
			new Project("project1"), new Project("project2"),
			new Project("project3"));

		Mockito.when(
			_projectDog.getProjects()
		).thenReturn(
			projects
		);

		_upgradeCheck = new UpgradeCheck();

		ReflectionTestUtils.setField(
			_upgradeCheck, "_elasticsearchInvoker", _elasticsearchInvoker);
		ReflectionTestUtils.setField(_upgradeCheck, "_projectDog", _projectDog);
	}

	@After
	public void tearDown() throws Exception {
		_elasticsearchIndexManager.delete(
			"project1_*", "project2_*", "project3_*", "project4_*");
	}

	@Test
	public void testCheckVersionEmpty() throws Exception {
		Assert.assertTrue(_upgradeCheck.checkVersion());
	}

	@Test
	public void testCheckVersionIgnoreUnknownProjects() throws Exception {
		_addUpgradeMarker("project1", ReleaseInfo.getVersion());
		_addUpgradeMarker("project2", ReleaseInfo.getVersion());
		_addUpgradeMarker("project3", ReleaseInfo.getVersion());
		_addUpgradeMarker("project4", "2.8.0");

		Assert.assertTrue(_upgradeCheck.checkVersion());
	}

	@Test
	public void testCheckVersionNotUpgrade() throws Exception {
		_addUpgradeMarker("project1", "2.8.0");
		_addUpgradeMarker("project2", "2.8.0");
		_addUpgradeMarker("project3", "2.8.0");

		Assert.assertFalse(_upgradeCheck.checkVersion());
	}

	@Test
	public void testCheckVersionUpgraded() throws Exception {
		_addUpgradeMarker("project1", ReleaseInfo.getVersion());
		_addUpgradeMarker("project2", ReleaseInfo.getVersion());
		_addUpgradeMarker("project3", ReleaseInfo.getVersion());

		Assert.assertTrue(_upgradeCheck.checkVersion());
	}

	@Test
	public void testCheckVersionUpgradedButOne() throws Exception {
		_addUpgradeMarker("project1", ReleaseInfo.getVersion());
		_addUpgradeMarker("project2", ReleaseInfo.getVersion());
		_addUpgradeMarker("project3", "2.8.0");

		Assert.assertFalse(_upgradeCheck.checkVersion());
	}

	private void _addUpgradeMarker(String projectId, String version) {
		ProjectIdThreadLocal.forProject(
			projectId,
			() -> _elasticsearchInvoker.add(
				"OSBAsahMarkers",
				JSONUtil.put(
					"id", "Upgrade"
				).put(
					"version", version
				)));
	}

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

	@Mock
	private ProjectDog _projectDog;

	private UpgradeCheck _upgradeCheck;

}