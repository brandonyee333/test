/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.model;

import com.j256.ormlite.field.DatabaseField;

import com.liferay.sync.engine.util.JSONUtil;

import java.io.IOException;

/**
 * @author Shinn Lok
 */
public class BaseModel {

	public static final int UI_EVENT_NONE = 0;

	public int getUiEvent() {
		return uiEvent;
	}

	public void setUiEvent(int uiEvent) {
		this.uiEvent = uiEvent;
	}

	@Override
	public String toString() {
		try {
			return JSONUtil.writeValueAsString(this);
		}
		catch (IOException ioe) {
			return super.toString();
		}
	}

	@DatabaseField(index = true, useGetSet = true)
	protected int uiEvent;

}