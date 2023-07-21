/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.verify.model;

import com.liferay.portal.kernel.model.LayoutSetBranch;
import com.liferay.portal.kernel.verify.model.VerifiableResourcedModel;

/**
 * @author Brian Wing Shun Chan
 */
public class LayoutSetBranchVerifiableModel
	implements VerifiableResourcedModel {

	@Override
	public String getModelName() {
		return LayoutSetBranch.class.getName();
	}

	@Override
	public String getPrimaryKeyColumnName() {
		return "layoutSetBranchId";
	}

	@Override
	public String getTableName() {
		return "LayoutSetBranch";
	}

	@Override
	public String getUserIdColumnName() {
		return "userId";
	}

}