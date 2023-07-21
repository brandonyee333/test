/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.verify.model;

import com.liferay.portal.kernel.model.Team;
import com.liferay.portal.kernel.verify.model.VerifiableResourcedModel;
import com.liferay.portal.kernel.verify.model.VerifiableUUIDModel;

/**
 * @author Brian Wing Shun Chan
 */
public class TeamVerifiableModel
	implements VerifiableResourcedModel, VerifiableUUIDModel {

	@Override
	public String getModelName() {
		return Team.class.getName();
	}

	@Override
	public String getPrimaryKeyColumnName() {
		return "teamId";
	}

	@Override
	public String getTableName() {
		return "Team";
	}

	@Override
	public String getUserIdColumnName() {
		return "userId";
	}

}