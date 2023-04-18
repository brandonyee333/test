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

import com.liferay.osb.asah.backend.dto.ProjectDTO;
import com.liferay.osb.asah.backend.dto.ProjectDetailDTO;
import com.liferay.osb.asah.common.dog.DataSourceDog;
import com.liferay.osb.asah.common.dog.PreferenceDog;
import com.liferay.osb.asah.common.dog.ProjectDog;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.Preference;
import com.liferay.osb.asah.common.util.ListUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public void deleteProject(
		@RequestParam(defaultValue = "false") boolean deleteData,
		@PathVariable String id) {

		_projectDog.deleteProject(deleteData, id);
	}

	@GetMapping("/details")
	public List<ProjectDetailDTO> getProjectDetailDTOs() {
		List<ProjectDetailDTO> projectDetailDTOs = new ArrayList<>();

		ProjectIdThreadLocal.forProjects(
			_projectDog.getProjects(),
			() -> {
				boolean accountsSelected = false;
				boolean commerceChannelsSelected = false;
				boolean contactsSelected = false;
				boolean sitesSelected = false;

				try {
					for (DataSource dataSource :
							_dataSourceDog.getDataSources()) {

						if (BooleanUtils.isTrue(
								dataSource.getAccountsSelected())) {

							accountsSelected = true;
						}

						if (BooleanUtils.isTrue(
								dataSource.getCommerceChannelsSelected())) {

							commerceChannelsSelected = true;
						}

						if (BooleanUtils.isTrue(
								dataSource.getContactsSelected())) {

							contactsSelected = true;
						}

						if (BooleanUtils.isTrue(
								dataSource.getSitesSelected())) {

							sitesSelected = true;
						}

						if (accountsSelected && commerceChannelsSelected &&
							contactsSelected && sitesSelected) {

							break;
						}
					}
				}
				catch (Exception exception) {
					_log.error(exception, exception);
				}

				Preference preference = _preferenceDog.getPreference(
					"time-zone-id");

				projectDetailDTOs.add(
					new ProjectDetailDTO(
						accountsSelected, commerceChannelsSelected,
						contactsSelected, ProjectIdThreadLocal.getProjectId(),
						sitesSelected, preference.getValue()));
			});

		return projectDetailDTOs;
	}

	@GetMapping
	public List<ProjectDTO> getProjectDTOs() {
		return ListUtil.map(_projectDog.getProjects(), ProjectDTO::new);
	}

	@PostMapping
	public void postProject(@RequestBody ProjectDTO projectDTO) {
		_projectDog.addProject(projectDTO.getId());
	}

	private static final Log _log = LogFactory.getLog(
		ProjectsRestController.class);

	@Autowired
	private DataSourceDog _dataSourceDog;

	@Autowired
	private PreferenceDog _preferenceDog;

	@Autowired
	private ProjectDog _projectDog;

}