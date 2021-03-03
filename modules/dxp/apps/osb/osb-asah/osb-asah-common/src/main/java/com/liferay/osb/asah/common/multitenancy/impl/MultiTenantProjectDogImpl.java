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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchSnapshotManager;
import com.liferay.osb.asah.common.elasticsearch.impl.ElasticsearchInvokerManager;
import com.liferay.osb.asah.common.http.NanitesHttp;
import com.liferay.osb.asah.common.model.Project;
import com.liferay.osb.asah.common.multitenancy.ProjectDog;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
	public void addProject(Project project) {
		_elasticsearchInvoker.add(
			"projects", _objectMapper.convertValue(project, JSONObject.class));

		try {
			_elasticsearchSnapshotManager.createSnapshotLifecyclePolicy(
				project.getId());
		}
		catch (Exception e) {
			_log.error(
				"Unable to create snapshot lifecycle policy for project " +
					project.getId(),
				e);
		}

		ProjectIdThreadLocal.forProject(
			project, _nanitesHttp::rescheduleNanites);
	}

	@Override
	public boolean deleteProject(String projectId) {
		ProjectIdThreadLocal.forProject(
			projectId, _nanitesHttp::removeSchedule);

		return _elasticsearchInvoker.delete("projects", projectId);
	}

	@Override
	public List<Project> getProjects() throws Exception {
		JSONArray projectsJSONArray = _elasticsearchInvoker.get("projects");

		return _objectMapper.convertValue(
			projectsJSONArray,
			new TypeReference<List<Project>>() {
			});
	}

	private static final Log _log = LogFactory.getLog(
		MultiTenantProjectDogImpl.class);

	private final ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private ElasticsearchSnapshotManager _elasticsearchSnapshotManager;

	@Autowired
	private NanitesHttp _nanitesHttp;

	@Autowired
	private ObjectMapper _objectMapper;

}