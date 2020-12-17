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

import com.liferay.osb.asah.common.constants.ServiceConstants;
import com.liferay.osb.asah.common.model.Project;
import com.liferay.osb.asah.common.multitenancy.ProjectDog;

import java.util.Collections;
import java.util.List;

/**
 * @author André Miranda
 */
public class SingleTenantProjectDogImpl implements ProjectDog {

	@Override
	public void addProject(Project project) {
	}

	@Override
	public boolean deleteProject(String projectId) {
		return false;
	}

	@Override
	public List<Project> getProjects() {
		return _projects;
	}

	private static final List<Project> _projects = Collections.singletonList(
		new Project(ServiceConstants.LCP_PROJECT_ID));

}