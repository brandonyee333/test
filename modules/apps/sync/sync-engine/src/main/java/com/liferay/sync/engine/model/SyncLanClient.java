/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import com.liferay.sync.engine.service.persistence.BasePersistenceImpl;

import java.util.Map;
import java.util.Set;

/**
 * @author Dennis Ju
 */
@DatabaseTable(
	daoClass = BasePersistenceImpl.class, tableName = "SyncLanClient"
)
@JsonIgnoreProperties(
	ignoreUnknown = true, value = {"hostname", "modifiedTime", "uiEvent"}
)
public class SyncLanClient extends BaseModel {

	public Map<String, Set<Long>> getEndpoints() {
		return endpoints;
	}

	public String getHostname() {
		return hostname;
	}

	public long getModifiedTime() {
		return modifiedTime;
	}

	public int getPort() {
		return port;
	}

	public String getSyncLanClientUuid() {
		return syncLanClientUuid;
	}

	public void setEndpoints(Map<String, Set<Long>> endpoints) {
		this.endpoints = endpoints;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public void setModifiedTime(long modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setSyncLanClientUuid(String syncLanClientUuid) {
		this.syncLanClientUuid = syncLanClientUuid;
	}

	protected Map<String, Set<Long>> endpoints;

	@DatabaseField(useGetSet = true)
	protected String hostname;

	@DatabaseField(useGetSet = true)
	protected long modifiedTime;

	@DatabaseField(useGetSet = true)
	protected int port;

	@DatabaseField(id = true, useGetSet = true)
	protected String syncLanClientUuid;

}