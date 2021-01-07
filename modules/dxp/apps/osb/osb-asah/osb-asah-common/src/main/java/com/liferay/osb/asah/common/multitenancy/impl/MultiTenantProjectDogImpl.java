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

package com.liferay.osb.asah.common.multitenancy.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsonorg.JsonOrgModule;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchSnapshotManager;
import com.liferay.osb.asah.common.elasticsearch.impl.ElasticsearchInvokerManager;
import com.liferay.osb.asah.common.model.Project;
import com.liferay.osb.asah.common.multitenancy.ProjectDog;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author André Miranda
 */
public class MultiTenantProjectDogImpl implements ProjectDog {

	public MultiTenantProjectDogImpl(
		ElasticsearchInvokerManager elasticsearchInvokerManager) {

		_elasticsearchInvoker =
			elasticsearchInvokerManager.getGlobalElasticsearchInvoker();
	}

	@Override
	public void addProject(Project project) throws Exception {
		_elasticsearchInvoker.add(
			"projects", _objectMapper.convertValue(project, JSONObject.class));

		_elasticsearchSnapshotManager.createSnapshotLifecyclePolicy(
			project.getId());
	}

	@Override
	public boolean deleteProject(String projectId) {
		return _elasticsearchInvoker.delete("projects", projectId);
	}

	@Override
	public List<Project> getProjects() throws Exception {
		JSONArray projectsJSONArray = _elasticsearchInvoker.get("projects");

		TypeFactory typeFactory = TypeFactory.defaultInstance();

		return _objectMapper.readValue(
			projectsJSONArray.toString(),
			typeFactory.constructCollectionType(List.class, Project.class));
	}

	private final ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private ElasticsearchSnapshotManager _elasticsearchSnapshotManager;

	private final ObjectMapper _objectMapper = new ObjectMapper() {
		{
			disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

			registerModule(new Jdk8Module());
			registerModule(new JsonOrgModule());
		}
	};

}