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

package com.liferay.osb.customer.admin.service.impl;

import com.liferay.osb.customer.admin.model.ProjectSolution;
import com.liferay.osb.customer.admin.service.base.ProjectSolutionLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Kyle Bischof
 */
public class ProjectSolutionLocalServiceImpl
	extends ProjectSolutionLocalServiceBaseImpl {

	public ProjectSolution addProjectSolution(
		String salesforceProjectKey, String value) {

		ProjectSolution projectSolution = projectSolutionPersistence.create(
			salesforceProjectKey);

		projectSolution.setValue(value);

		return projectSolutionPersistence.update(projectSolution);
	}

	public ProjectSolution updateProjectSolution(
			String salesforceProjectKey, String value)
		throws PortalException {

		ProjectSolution projectSolution =
			projectSolutionPersistence.findByPrimaryKey(salesforceProjectKey);

		projectSolution.setValue(value);

		return projectSolutionPersistence.update(projectSolution);
	}

}