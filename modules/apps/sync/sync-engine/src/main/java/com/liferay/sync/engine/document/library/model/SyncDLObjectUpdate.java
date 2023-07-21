/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.document.library.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.liferay.sync.engine.model.SyncFile;

import java.util.List;
import java.util.Map;

/**
 * @author Michael Young
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SyncDLObjectUpdate {

	public static final String PREFERENCE_KEY_SYNC_CONTEXT_MODIFIED_TIME =
		"sync.context.modified.time";

	public long getLastAccessTime() {
		return lastAccessTime;
	}

	public int getResultsTotal() {
		return resultsTotal;
	}

	public Map<String, Long> getSettingsModifiedTimes() {
		return settingsModifiedTimes;
	}

	public List<SyncFile> getSyncFiles() {
		return syncFiles;
	}

	public void setLastAccessTime(long lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}

	public void setResultsTotal(int resultsTotal) {
		this.resultsTotal = resultsTotal;
	}

	public void setSettingsModifiedTimes(
		Map<String, Long> settingsModifiedTimes) {

		this.settingsModifiedTimes = settingsModifiedTimes;
	}

	public void setSyncFiles(List<SyncFile> syncFiles) {
		this.syncFiles = syncFiles;
	}

	protected long lastAccessTime;
	protected int resultsTotal;
	protected Map<String, Long> settingsModifiedTimes;

	@JsonProperty("syncDLObjects")
	protected List<SyncFile> syncFiles;

}