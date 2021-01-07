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

package com.liferay.osb.asah.backend.rest.controller;

import com.liferay.osb.asah.common.model.Project;
import com.liferay.osb.asah.common.multitenancy.ProjectDog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author André Miranda
 */
@RequestMapping(produces = "application/json", value = "/projects")
@RestController(
	"com.liferay.osb.asah.backend.rest.controller.ProjectsRestController"
)
public class ProjectsRestController extends BaseRestController {

	@DeleteMapping("/{id}")
	public boolean deleteProject(@PathVariable String id) {
		return _projectDog.deleteProject(id);
	}

	@GetMapping
	public List<Project> getProjects() throws Exception {
		return _projectDog.getProjects();
	}

	@PostMapping
	public void postProject(@RequestBody Project project) throws Exception {
		_projectDog.addProject(project);
	}

	@Autowired
	private ProjectDog _projectDog;

}