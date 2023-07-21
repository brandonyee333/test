/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.lan.session;

import com.liferay.sync.engine.model.SyncLanClient;

/**
 * @author Dennis Ju
 */
public class SyncLanClientQueryResult {

	public int getConnectionsCount() {
		return _connectionsCount;
	}

	public int getDownloadRate() {
		return _downloadRate;
	}

	public String getEncryptedToken() {
		return _encryptedToken;
	}

	public int getMaxConnections() {
		return _maxConnections;
	}

	public SyncLanClient getSyncLanClient() {
		return _syncLanClient;
	}

	public void setConnectionsCount(int connectionsCount) {
		_connectionsCount = connectionsCount;
	}

	public void setDownloadRate(int downloadRate) {
		_downloadRate = downloadRate;
	}

	public void setEncryptedToken(String encryptedToken) {
		_encryptedToken = encryptedToken;
	}

	public void setMaxConnections(int maxConnections) {
		_maxConnections = maxConnections;
	}

	public void setSyncLanClient(SyncLanClient syncLanClient) {
		_syncLanClient = syncLanClient;
	}

	private int _connectionsCount;
	private int _downloadRate;
	private String _encryptedToken;
	private int _maxConnections;
	private SyncLanClient _syncLanClient;

}