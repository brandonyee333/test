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

package com.liferay.osb.asah.common.multitenancy;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsonorg.JsonOrgModule;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.impl.ElasticsearchInvokerManager;
import com.liferay.osb.asah.common.model.Project;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author André Miranda
 */
@Component
public class ProjectDog {

	public void addProject(Project project) {
		ElasticsearchInvoker elasticsearchInvoker =
			_elasticsearchInvokerManager.getGlobalElasticsearchInvoker();

		elasticsearchInvoker.add(
			"projects", _objectMapper.convertValue(project, JSONObject.class));
	}

	public boolean deleteProject(String projectId) {
		ElasticsearchInvoker elasticsearchInvoker =
			_elasticsearchInvokerManager.getGlobalElasticsearchInvoker();

		return elasticsearchInvoker.delete("projects", projectId);
	}

	public List<Project> getProjects() throws Exception {
		ElasticsearchInvoker elasticsearchInvoker =
			_elasticsearchInvokerManager.getGlobalElasticsearchInvoker();

		JSONArray projectsJSONArray = elasticsearchInvoker.get("projects");

		TypeFactory typeFactory = TypeFactory.defaultInstance();

		return _objectMapper.readValue(
			projectsJSONArray.toString(),
			typeFactory.constructCollectionType(List.class, Project.class));
	}

	@Autowired
	private ElasticsearchInvokerManager _elasticsearchInvokerManager;

	private final ObjectMapper _objectMapper = new ObjectMapper() {
		{
			disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

			registerModule(new Jdk8Module());
			registerModule(new JsonOrgModule());
		}
	};

}