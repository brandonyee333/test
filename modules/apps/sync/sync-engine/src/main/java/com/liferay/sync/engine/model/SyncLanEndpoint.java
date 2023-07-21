/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import com.liferay.sync.engine.service.persistence.BasePersistenceImpl;

import java.util.Arrays;

/**
 * @author Dennis Ju
 */
@DatabaseTable(
	daoClass = BasePersistenceImpl.class, tableName = "SyncLanEndpoint"
)
public class SyncLanEndpoint extends BaseModel {

	@Override
	public boolean equals(Object object) {
		if (object == this) {
			return true;
		}

		if (!(object instanceof SyncLanEndpoint)) {
			return false;
		}

		SyncLanEndpoint syncLanEndpoint = (SyncLanEndpoint)object;

		if (!lanServerUuid.equals(syncLanEndpoint.lanServerUuid)) {
			return false;
		}
		else if (repositoryId != syncLanEndpoint.repositoryId) {
			return false;
		}
		else if (!syncLanClientUuid.equals(syncLanEndpoint.syncLanClientUuid)) {
			return false;
		}

		return true;
	}

	public String getLanServerUuid() {
		return lanServerUuid;
	}

	public long getRepositoryId() {
		return repositoryId;
	}

	public String getSyncLanClientUuid() {
		return syncLanClientUuid;
	}

	public long getSyncLanEndpointId() {
		return syncLanEndpointId;
	}

	@Override
	public int hashCode() {
		return Arrays.hashCode(
			new Object[] {lanServerUuid, repositoryId, syncLanClientUuid});
	}

	public void setLanServerUuid(String lanServerUuid) {
		this.lanServerUuid = lanServerUuid;
	}

	public void setRepositoryId(long repositoryId) {
		this.repositoryId = repositoryId;
	}

	public void setSyncLanClientUuid(String syncLanClientUuid) {
		this.syncLanClientUuid = syncLanClientUuid;
	}

	public void setSyncLanEndpointId(long syncLanEndpointId) {
		this.syncLanEndpointId = syncLanEndpointId;
	}

	@DatabaseField(useGetSet = true)
	protected String lanServerUuid;

	@DatabaseField(useGetSet = true)
	protected long repositoryId;

	@DatabaseField(useGetSet = true)
	protected String syncLanClientUuid;

	@DatabaseField(generatedId = true, useGetSet = true)
	protected long syncLanEndpointId;

}