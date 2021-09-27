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

import com.liferay.osb.asah.common.entity.Project;
import com.liferay.osb.asah.common.http.NanitesHttp;
import com.liferay.osb.asah.common.multitenancy.ProjectDog;
import com.liferay.osb.asah.common.repository.ProjectRepository;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;

import java.util.List;
import java.util.function.Consumer;

import org.apache.commons.collections4.IterableUtils;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author André Miranda
 */
public class MultiTenantProjectDogImpl implements ProjectDog {

	public MultiTenantProjectDogImpl(
		Consumer<String> postCreationConsumer,
		ProjectRepository projectRepository) {

		_postCreationConsumer = postCreationConsumer;
		_projectRepository = projectRepository;
	}

	@Override
	public void addProject(Project project) {
		try {
			ProjectIdThreadLocal.setGlobalContext(true);

			_projectRepository.save(project);
		}
		finally {
			ProjectIdThreadLocal.setGlobalContext(false);
		}

		if (_postCreationConsumer != null) {
			_postCreationConsumer.accept(project.getId());
		}

		ProjectIdThreadLocal.forProject(
			project, _nanitesHttp::rescheduleNanites);
	}

	@Override
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

	@Override
	public List<Project> getProjects() {
		try {
			ProjectIdThreadLocal.setGlobalContext(true);

			return IterableUtils.toList(_projectRepository.findAll());
		}
		finally {
			ProjectIdThreadLocal.setGlobalContext(false);
		}
	}

	@Autowired
	private NanitesHttp _nanitesHttp;

	private final Consumer<String> _postCreationConsumer;
	private final ProjectRepository _projectRepository;

}