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

import com.liferay.osb.asah.common.bigquery.BigQuerySchemaManager;
import com.liferay.osb.asah.common.entity.AsahMarker;
import com.liferay.osb.asah.common.entity.Project;
import com.liferay.osb.asah.common.http.NanitesHttp;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.postgresql.PostgreSQLSchemaManager;
import com.liferay.osb.asah.common.repository.ProjectRepository;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.util.ReleaseInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.annotation.PostConstruct;

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

	public void addConsumer(Consumer<String> consumer) {
		_consumers.add(consumer);
	}

	public void addProject(Project project) {
		try {
			ProjectIdThreadLocal.setGlobalContext(true);

			_projectRepository.save(project);
		}
		finally {
			ProjectIdThreadLocal.setGlobalContext(false);
		}

		for (Consumer<String> consumer : _consumers) {
			consumer.accept(project.getId());
		}

		_bigQuerySchemaManager.createSchema(project.getId());
		_postgreSQLSchemaManager.createSchema(project);

		try {
			ProjectIdThreadLocal.setProjectId(project.getId());

			_asahMarkerDog.addAsahMarker(
				new AsahMarker(
					"Upgrade",
					JSONUtil.put("version", ReleaseInfo.getVersion())));
		}
		catch (Exception exception) {
			_log.error(
				"Unable to create upgrade Asah marker for " + project.getId(),
				exception);
		}
		finally {
			ProjectIdThreadLocal.remove();
		}

		ProjectIdThreadLocal.forProject(
			project, _nanitesHttp::rescheduleNanites);
	}

	public void deleteProject(boolean deleteData, String projectId) {
		if (deleteData) {
			_bigQuerySchemaManager.deleteSchema(projectId);
		}

		ProjectIdThreadLocal.forProject(
			projectId, _nanitesHttp::removeSchedule);

		try {
			ProjectIdThreadLocal.setGlobalContext(true);

			_projectRepository.deleteById(projectId);
		}
		finally {
			ProjectIdThreadLocal.setGlobalContext(false);
		}

		if (deleteData) {
			_postgreSQLSchemaManager.deleteSchema(projectId);
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

	@PostConstruct
	public void init() {
		_postgreSQLSchemaManager.createGlobalSchema();

		_consumers.add(this::_createSnapshots);
	}

	public void updateVersion(String projectId, String version) {
		try {
			ProjectIdThreadLocal.setGlobalContext(true);

			_projectRepository.updateVersion(projectId, version);
		}
		finally {
			ProjectIdThreadLocal.setGlobalContext(false);
		}
	}

	private void _createSnapshots(String projectId) {
	}

	private static final Log _log = LogFactory.getLog(ProjectDog.class);

	@Autowired
	private AsahMarkerDog _asahMarkerDog;

	@Autowired
	private BigQuerySchemaManager _bigQuerySchemaManager;

	private final List<Consumer<String>> _consumers = new ArrayList<>();

	@Autowired
	private NanitesHttp _nanitesHttp;

	@Autowired
	private PostgreSQLSchemaManager _postgreSQLSchemaManager;

	@Autowired
	private ProjectRepository _projectRepository;

}