/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import com.liferay.sync.engine.service.persistence.BasePersistenceImpl;
import com.liferay.sync.engine.util.Validator;

/**
 * @author Dennis Ju
 */
@DatabaseTable(daoClass = BasePersistenceImpl.class, tableName = "SyncUser")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SyncUser extends BaseModel {

	public String getEmailAddress() {
		return emailAddress;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getFullName() {
		if (Validator.isBlank(lastName)) {
			return firstName;
		}

		return firstName + " " + lastName;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public String getLastName() {
		return lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public long getPortraitId() {
		return portraitId;
	}

	public String getScreenName() {
		return screenName;
	}

	public long getSyncAccountId() {
		return syncAccountId;
	}

	public long getSyncUserId() {
		return syncUserId;
	}

	public long getUserId() {
		return userId;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public void setPortraitId(long portraitId) {
		this.portraitId = portraitId;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public void setSyncAccountId(long syncAccountId) {
		this.syncAccountId = syncAccountId;
	}

	public void setSyncUserId(long syncUserId) {
		this.syncUserId = syncUserId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@DatabaseField(useGetSet = true)
	protected String emailAddress;

	@DatabaseField(useGetSet = true)
	protected String firstName;

	@DatabaseField(useGetSet = true)
	protected String jobTitle;

	@DatabaseField(useGetSet = true)
	protected String lastName;

	@DatabaseField(useGetSet = true)
	protected String middleName;

	@DatabaseField(useGetSet = true)
	protected long portraitId;

	@DatabaseField(useGetSet = true)
	protected String screenName;

	@DatabaseField(useGetSet = true)
	protected long syncAccountId;

	@DatabaseField(generatedId = true, useGetSet = true)
	protected long syncUserId;

	@DatabaseField(useGetSet = true)
	protected long userId;

}