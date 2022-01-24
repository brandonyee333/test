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

import com.liferay.osb.asah.common.dog.ProjectDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.impl.ElasticsearchInvokerManager;
import com.liferay.osb.asah.common.entity.Project;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.postgresql.PostgreSQLSchemaManager;
import com.liferay.osb.asah.common.repository.ProjectRepository;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class ProjectMigrationUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) throws Exception {
		ElasticsearchInvoker globalElasticsearchInvoker =
			_elasticsearchInvokerManager.getGlobalElasticsearchInvoker();

		List<Project> projects = JSONUtil.toList(
			globalElasticsearchInvoker.get(
				"projects",
				Collections.singletonList(
					SortBuilderUtil.fieldSort("id.keyword")),
				BoolQueryBuilderUtil.mustNot(
					QueryBuilders.termsQuery("id", _getProjectIds()))),
			jsonObject -> _objectMapper.convertValue(
				jsonObject, Project.class));

		if (projects.isEmpty()) {
			if (_log.isInfoEnabled()) {
				_log.info("No projects to migrate. Skipping.");
			}

			return;
		}

		try {
			ProjectIdThreadLocal.setGlobalContext(true);

			if (_log.isInfoEnabled()) {
				_log.info(
					String.format("Migrating %d projects", projects.size()));
			}

			_projectRepository.saveAll(projects);
		}
		finally {
			ProjectIdThreadLocal.setGlobalContext(false);
		}
	}

	private Set<String> _getProjectIds() {
		List<Project> projects = _projectDog.getProjects();

		Stream<Project> stream = projects.stream();

		return stream.map(
			Project::getId
		).collect(
			Collectors.toSet()
		);
	}

	private static final Log _log = LogFactory.getLog(
		ProjectMigrationUpgradeStep.class);

	@Autowired
	private ElasticsearchInvokerManager _elasticsearchInvokerManager;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private PostgreSQLSchemaManager _postgreSQLSchemaManager;

	@Autowired
	private ProjectDog _projectDog;

	@Autowired
	private ProjectRepository _projectRepository;

}