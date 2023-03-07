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

/**
 * @author Riccardo Ferrari
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectDetailsDTO {

	public ProjectDetailsDTO(
		Boolean commerceChannelsSelected, String id, String timeZone) {

		_commerceChannelsSelected = commerceChannelsSelected;
		_id = id;
		_timeZone = timeZone;
	}

	public Boolean getCommerceChannelsSelected() {
		return _commerceChannelsSelected;
	}

	public String getId() {
		return _id;
	}

	public String getTimeZone() {
		return _timeZone;
	}

	private final Boolean _commerceChannelsSelected;
	private final String _id;
	private final String _timeZone;

}