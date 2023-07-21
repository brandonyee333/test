/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.verify.model;

import com.liferay.portal.kernel.verify.model.VerifiableAuditedModel;
import com.liferay.portal.kernel.verify.model.VerifiableGroupedModel;
import com.liferay.portal.kernel.verify.model.VerifiableUUIDModel;

/**
 * @author Miguel Pastor
 */
public class MBThreadFlagVerifiableModel
	implements VerifiableAuditedModel, VerifiableGroupedModel,
			   VerifiableUUIDModel {

	@Override
	public String getJoinByTableName() {
		return "userId";
	}

	@Override
	public String getPrimaryKeyColumnName() {
		return "threadFlagId";
	}

	@Override
	public String getRelatedModelName() {
		return "User_";
	}

	@Override
	public String getRelatedPKColumnName() {
		return "userId";
	}

	@Override
	public String getRelatedPrimaryKeyColumnName() {
		return "threadId";
	}

	@Override
	public String getRelatedTableName() {
		return "MBThread";
	}

	@Override
	public String getTableName() {
		return "MBThreadFlag";
	}

	@Override
	public boolean isAnonymousUserAllowed() {
		return false;
	}

	@Override
	public boolean isUpdateDates() {
		return true;
	}

}