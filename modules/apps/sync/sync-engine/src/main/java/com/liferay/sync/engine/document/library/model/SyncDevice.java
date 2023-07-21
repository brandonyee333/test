/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.document.library.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Shinn Lok
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SyncDevice {

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	protected String uuid;

}