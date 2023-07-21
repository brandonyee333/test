/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.model;

import com.barbarysoftware.watchservice.ExtendedWatchEventKind;
import com.barbarysoftware.watchservice.StandardWatchEventKind;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import com.liferay.sync.engine.service.persistence.BasePersistenceImpl;

/**
 * @author Michael Young
 */
@DatabaseTable(
	daoClass = BasePersistenceImpl.class, tableName = "SyncWatchEvent"
)
public class SyncWatchEvent extends BaseModel {

	public static final String EVENT_TYPE_CREATE =
		StandardWatchEventKind.ENTRY_CREATE.name();

	public static final String EVENT_TYPE_DELETE =
		StandardWatchEventKind.ENTRY_DELETE.name();

	public static final String EVENT_TYPE_MODIFY =
		StandardWatchEventKind.ENTRY_MODIFY.name();

	public static final String EVENT_TYPE_MOVE = "ENTRY_MOVE";

	public static final String EVENT_TYPE_OVERFLOW =
		StandardWatchEventKind.OVERFLOW.name();

	public static final String EVENT_TYPE_RENAME = "ENTRY_RENAME";

	public static final String EVENT_TYPE_RENAME_FROM =
		ExtendedWatchEventKind.ENTRY_RENAME_FROM.name();

	public static final String EVENT_TYPE_RENAME_TO =
		ExtendedWatchEventKind.ENTRY_RENAME_TO.name();

	public String getEventType() {
		return eventType;
	}

	public String getFilePathName() {
		return filePathName;
	}

	public String getFileType() {
		return fileType;
	}

	public String getPreviousFilePathName() {
		return previousFilePathName;
	}

	public long getSyncAccountId() {
		return syncAccountId;
	}

	public long getSyncWatchEventId() {
		return syncWatchEventId;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public void setFilePathName(String filePathName) {
		this.filePathName = filePathName;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public void setPreviousFilePathName(String previousFilePathName) {
		this.previousFilePathName = previousFilePathName;
	}

	public void setSyncAccountId(long syncAccountId) {
		this.syncAccountId = syncAccountId;
	}

	public void setSyncWatchEventId(long syncWatchEventId) {
		this.syncWatchEventId = syncWatchEventId;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	@DatabaseField(useGetSet = true)
	protected String eventType;

	@DatabaseField(index = true, useGetSet = true, width = 16777216)
	protected String filePathName;

	@DatabaseField(useGetSet = true)
	protected String fileType;

	@DatabaseField(useGetSet = true, width = 16777216)
	protected String previousFilePathName;

	@DatabaseField(useGetSet = true)
	protected long syncAccountId;

	@DatabaseField(generatedId = true, useGetSet = true)
	protected long syncWatchEventId;

	@DatabaseField(useGetSet = true)
	protected long timestamp;

}