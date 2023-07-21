/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.verify.model;

import com.liferay.document.library.kernel.model.DLFileEntryType;
import com.liferay.portal.kernel.verify.model.VerifiableResourcedModel;

/**
 * @author Michael Bowerman
 */
public class DLFileEntryTypeVerifiableModel
	implements VerifiableResourcedModel {

	@Override
	public String getModelName() {
		return DLFileEntryType.class.getName();
	}

	@Override
	public String getPrimaryKeyColumnName() {
		return "fileEntryTypeId";
	}

	@Override
	public String getTableName() {
		return "DLFileEntryType";
	}

	@Override
	public String getUserIdColumnName() {
		return "userId";
	}

}