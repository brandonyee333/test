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

package com.liferay.osb.asah.common.dog;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchSnapshotManager;
import com.liferay.osb.asah.common.elasticsearch.repository.impl.ElasticsearchProjectRepositoryImpl;
import com.liferay.osb.asah.common.entity.Project;
import com.liferay.osb.asah.common.http.NanitesHttp;
import com.liferay.osb.asah.common.repository.ProjectRepository;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;

import java.util.List;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author André Miranda
 */
@Component
public class ProjectDog {

	public void addProject(Project project) {
		try {
			ProjectIdThreadLocal.setGlobalContext(true);

			_projectRepository.save(project);
		}
		finally {
			ProjectIdThreadLocal.setGlobalContext(false);
		}

		_createSnapshots(project.getId());

		ProjectIdThreadLocal.forProject(
			project, _nanitesHttp::rescheduleNanites);
	}

	public void deleteProject(String projectId) {
		ProjectIdThreadLocal.forProject(
			projectId, _nanitesHttp::removeSchedule);

		try {
			ProjectIdThreadLocal.setGlobalContext(true);

			_projectRepository.deleteById(projectId);
		}
		finally {
			ProjectIdThreadLocal.setGlobalContext(false);
		}
	}

	public List<Project> getProjects() {
		try {
			ProjectIdThreadLocal.setGlobalContext(true);

			return IterableUtils.toList(_projectRepository.findAll());
		}
		finally {
			ProjectIdThreadLocal.setGlobalContext(false);
		}
	}

	private void _createSnapshots(String projectId) {
		try {
			if (_projectRepository instanceof
					ElasticsearchProjectRepositoryImpl) {

				_elasticsearchSnapshotManager.createSnapshotLifecyclePolicy(
					projectId);
			}
		}
		catch (Exception exception) {
			_log.error(
				"Unable to create snapshot lifecycle policy for project " +
					projectId,
				exception);
		}
	}

	private static final Log _log = LogFactory.getLog(ProjectDog.class);

	@Autowired
	private ElasticsearchSnapshotManager _elasticsearchSnapshotManager;

	@Autowired
	private NanitesHttp _nanitesHttp;

	@Autowired
	private ProjectRepository _projectRepository;

}