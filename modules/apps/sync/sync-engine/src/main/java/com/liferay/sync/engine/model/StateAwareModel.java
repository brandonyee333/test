/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.model;

import com.j256.ormlite.field.DatabaseField;

/**
 * @author Michael Young
 */
public class StateAwareModel extends BaseModel {

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	@DatabaseField(index = true, useGetSet = true)
	protected int state;

}