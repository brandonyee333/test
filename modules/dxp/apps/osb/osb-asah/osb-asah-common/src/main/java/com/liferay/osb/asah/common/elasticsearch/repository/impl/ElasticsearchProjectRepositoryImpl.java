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

package com.liferay.osb.asah.common.elasticsearch.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.impl.ElasticsearchInvokerManager;
import com.liferay.osb.asah.common.model.Project;
import com.liferay.osb.asah.common.repository.ProjectRepository;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * @author André Miranda
 */
@Component
@ConditionalOnProperty(
	havingValue = "false", matchIfMissing = true,
	value = "osb.asah.postgresql.enabled"
)
public class ElasticsearchProjectRepositoryImpl implements ProjectRepository {

	@Override
	public boolean deleteById(String projectId) {
		return _elasticsearchInvoker.delete("projects", projectId);
	}

	@Override
	public List<Project> findAll() {
		JSONArray projectsJSONArray = _elasticsearchInvoker.get("projects");

		List<Project> projects = new ArrayList<>(projectsJSONArray.length());

		for (int i = 0; i < projectsJSONArray.length(); i++) {
			JSONObject jsonObject = projectsJSONArray.getJSONObject(i);

			projects.add(new Project(jsonObject.getString("id")));
		}

		return projects;
	}

	@PostConstruct
	public void init() {
		_elasticsearchInvoker =
			_elasticsearchInvokerManager.getGlobalElasticsearchInvoker();
	}

	@Override
	public Project save(Project project) {
		JSONObject jsonObject = _elasticsearchInvoker.add(
			"projects", _objectMapper.convertValue(project, JSONObject.class));

		return new Project(jsonObject.getString("id"));
	}

	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerManager _elasticsearchInvokerManager;

	@Autowired
	private ObjectMapper _objectMapper;

}