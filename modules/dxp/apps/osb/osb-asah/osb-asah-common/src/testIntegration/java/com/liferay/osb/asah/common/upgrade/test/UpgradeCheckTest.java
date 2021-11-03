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

import com.liferay.osb.asah.common.dog.AsahMarkerDog;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.common.entity.AsahMarker;
import com.liferay.osb.asah.common.entity.Project;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.multitenancy.ProjectDog;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.common.upgrade.UpgradeCheck;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.util.ReleaseInfo;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.spring.OSBAsahElasticsearchTestExecutionListener;
import com.liferay.osb.asah.test.util.spring.OSBAsahRepositoryTestExecutionListener;
import com.liferay.osb.asah.test.util.spring.OSBAsahSQLTestExecutionListener;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit5ClassRunner;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.util.ReflectionTestUtils;

/**
 * @author André Miranda
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@ExtendWith(OSBAsahSpringJUnit5ClassRunner.class)
@TestExecutionListeners(
	mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS,
	value = {
		OSBAsahElasticsearchTestExecutionListener.class,
		OSBAsahRepositoryTestExecutionListener.class,
		OSBAsahSQLTestExecutionListener.class
	}
)
public class UpgradeCheckTest {

	@BeforeEach
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
			_upgradeCheck, "_asahMarkerDog", _asahMarkerDog);
		ReflectionTestUtils.setField(_upgradeCheck, "_projectDog", _projectDog);
	}

	@AfterEach
	public void tearDown() throws Exception {
		_elasticsearchIndexManager.delete(
			"project1_*", "project2_*", "project3_*", "project4_*");
	}

	@Test
	public void testCheckVersionEmpty() throws Exception {
		Assertions.assertTrue(_upgradeCheck.checkVersion());
	}

	@Test
	public void testCheckVersionIgnoreUnknownProjects() throws Exception {
		_addUpgradeMarker("project1", ReleaseInfo.getVersion());
		_addUpgradeMarker("project2", ReleaseInfo.getVersion());
		_addUpgradeMarker("project3", ReleaseInfo.getVersion());
		_addUpgradeMarker("project4", "2.8.0");

		Assertions.assertTrue(_upgradeCheck.checkVersion());
	}

	@Test
	public void testCheckVersionNotUpgrade() throws Exception {
		_addUpgradeMarker("project1", "2.8.0");
		_addUpgradeMarker("project2", "2.8.0");
		_addUpgradeMarker("project3", "2.8.0");

		Assertions.assertFalse(_upgradeCheck.checkVersion());
	}

	@Test
	public void testCheckVersionUpgraded() throws Exception {
		_addUpgradeMarker("project1", ReleaseInfo.getVersion());
		_addUpgradeMarker("project2", ReleaseInfo.getVersion());
		_addUpgradeMarker("project3", ReleaseInfo.getVersion());

		Assertions.assertTrue(_upgradeCheck.checkVersion());
	}

	@Test
	public void testCheckVersionUpgradedButOne() throws Exception {
		_addUpgradeMarker("project1", ReleaseInfo.getVersion());
		_addUpgradeMarker("project2", ReleaseInfo.getVersion());
		_addUpgradeMarker("project3", "2.8.0");

		Assertions.assertFalse(_upgradeCheck.checkVersion());
	}

	private void _addUpgradeMarker(String projectId, String version) {
		ProjectIdThreadLocal.forProject(
			projectId,
			() -> _asahMarkerDog.addAsahMarker(
				new AsahMarker("Upgrade", JSONUtil.put("version", version)),
				WeDeployDataService.OSB_ASAH_FARO_INFO));
	}

	@Autowired
	private AsahMarkerDog _asahMarkerDog;

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

	@Mock
	private ProjectDog _projectDog;

	private UpgradeCheck _upgradeCheck;

}