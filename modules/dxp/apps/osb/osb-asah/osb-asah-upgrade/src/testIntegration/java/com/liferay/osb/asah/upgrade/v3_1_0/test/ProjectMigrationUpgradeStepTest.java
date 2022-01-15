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

package com.liferay.osb.asah.upgrade.v3_1_0.test;

import com.liferay.osb.asah.common.dog.ProjectDog;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.impl.ElasticsearchInvokerManager;
import com.liferay.osb.asah.common.entity.Project;
import com.liferay.osb.asah.common.repository.ProjectRepository;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.upgrade.OSBAsahUpgradeSpringTestContext;
import com.liferay.osb.asah.upgrade.v3_1_0.ProjectMigrationUpgradeStep;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Marcellus Tavares
 */
public class ProjectMigrationUpgradeStepTest
	implements OSBAsahUpgradeSpringTestContext {

	@BeforeEach
	public void setUp() throws Exception {
		_elasticsearchIndexManager.create(
			ResourceUtil.readResourceToString(
				"dependencies/projects_index_configuration.json", this),
			"projects");

		_elasticsearchIndexManager.addAlias("projects_alias", "projects");
	}

	@AfterEach
	public void tearDown() {
		_elasticsearchIndexManager.delete("projects");

		ProjectIdThreadLocal.forProject(
			"global", _projectRepository::deleteAll);
	}

	@Test
	public void testUpgrade1() throws Exception {
		JSONArray jsonArray = new JSONArray(
			ResourceUtil.readResourceToString(
				"dependencies/projects.json", this));

		ElasticsearchInvoker globalElasticsearchInvoker =
			_elasticsearchInvokerManager.getGlobalElasticsearchInvoker();

		globalElasticsearchInvoker.add("projects", jsonArray);

		_projectMigrationUpgradeStep.upgrade("");

		ProjectIdThreadLocal.forProject(
			"global",
			() -> {
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);

					Assertions.assertTrue(
						_projectRepository.existsById(
							jsonObject.getString("id")));
				}
			});
	}

	@Test
	public void testUpgrade2() throws Exception {
		JSONArray jsonArray = new JSONArray(
			ResourceUtil.readResourceToString(
				"dependencies/projects.json", this));

		ElasticsearchInvoker globalElasticsearchInvoker =
			_elasticsearchInvokerManager.getGlobalElasticsearchInvoker();

		globalElasticsearchInvoker.add("projects", jsonArray);

		_projectDog.addProject(
			new Project("asah9a3c72dd78374408a940b6445206b65e"));

		_projectMigrationUpgradeStep.upgrade("");

		List<Project> projects = _projectDog.getProjects();

		Assertions.assertEquals(10, projects.size());

		ProjectIdThreadLocal.forProject(
			"global",
			() -> {
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);

					Assertions.assertTrue(
						_projectRepository.existsById(
							jsonObject.getString("id")));
				}
			});
	}

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

	@Autowired
	private ElasticsearchInvokerManager _elasticsearchInvokerManager;

	@Autowired
	private ProjectDog _projectDog;

	@Autowired
	private ProjectMigrationUpgradeStep _projectMigrationUpgradeStep;

	@Autowired
	private ProjectRepository _projectRepository;

}