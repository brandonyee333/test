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
public class ProjectDetailDTO {

	public ProjectDetailDTO(
		Boolean accountsSelected, Boolean commerceChannelsSelected,
		Boolean contactsSelected, String id, Boolean sitesSelected,
		String timeZoneId) {

		_accountsSelected = accountsSelected;
		_commerceChannelsSelected = commerceChannelsSelected;
		_contactsSelected = contactsSelected;
		_id = id;
		_sitesSelected = sitesSelected;
		_timeZoneId = timeZoneId;
	}

	public Boolean getAccountsSelected() {
		return _accountsSelected;
	}

	public Boolean getCommerceChannelsSelected() {
		return _commerceChannelsSelected;
	}

	public Boolean getContactsSelected() {
		return _contactsSelected;
	}

	public String getId() {
		return _id;
	}

	public Boolean getSitesSelected() {
		return _sitesSelected;
	}

	public String getTimeZoneId() {
		return _timeZoneId;
	}

	private final Boolean _accountsSelected;
	private final Boolean _commerceChannelsSelected;
	private final Boolean _contactsSelected;
	private final String _id;
	private final Boolean _sitesSelected;
	private final String _timeZoneId;

}