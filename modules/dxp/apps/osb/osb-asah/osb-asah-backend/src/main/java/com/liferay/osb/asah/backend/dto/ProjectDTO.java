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

package com.liferay.osb.asah.backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import com.liferay.osb.asah.common.entity.Project;

/**
 * @author Marcellus Tavares
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectDTO {

	public ProjectDTO() {
	}

	public ProjectDTO(Project project) {
		_id = project.getId();
		_version = project.getVersion();
	}

	public String getId() {
		return _id;
	}

	public String getVersion() {
		return _version;
	}

	public void setId(String id) {
		_id = id;
	}

	public void setVersion(String version) {
		_version = version;
	}

	private String _id;
	private String _version;

}