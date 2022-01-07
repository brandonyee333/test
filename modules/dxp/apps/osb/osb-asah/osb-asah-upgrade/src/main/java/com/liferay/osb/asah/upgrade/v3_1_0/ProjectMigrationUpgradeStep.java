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

package com.liferay.osb.asah.upgrade.v3_1_0;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.impl.ElasticsearchInvokerManager;
import com.liferay.osb.asah.common.entity.Project;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.postgresql.PostgreSQLSchemaManager;
import com.liferay.osb.asah.common.repository.ProjectRepository;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class ProjectMigrationUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) throws Exception {
		_postgreSQLSchemaManager.createGlobalSchema();

		ElasticsearchInvoker globalElasticsearchInvoker =
			_elasticsearchInvokerManager.getGlobalElasticsearchInvoker();

		List<Project> projects = JSONUtil.toList(
			globalElasticsearchInvoker.get("projects"),
			jsonObject -> _objectMapper.convertValue(
				jsonObject, Project.class));

		try {
			ProjectIdThreadLocal.setGlobalContext(true);

			_projectRepository.saveAll(projects);
		}
		finally {
			ProjectIdThreadLocal.setGlobalContext(false);
		}
	}

	@Autowired
	private ElasticsearchInvokerManager _elasticsearchInvokerManager;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private PostgreSQLSchemaManager _postgreSQLSchemaManager;

	@Autowired
	private ProjectRepository _projectRepository;

}