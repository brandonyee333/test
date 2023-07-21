/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.verify.model;

import com.liferay.document.library.kernel.model.DLFileShortcutConstants;
import com.liferay.portal.kernel.verify.model.VerifiableResourcedModel;

/**
 * @author Brian Wing Shun Chan
 */
public class DLFileShortcutVerifiableModel implements VerifiableResourcedModel {

	@Override
	public String getModelName() {
		return DLFileShortcutConstants.getClassName();
	}

	@Override
	public String getPrimaryKeyColumnName() {
		return "fileShortcutId";
	}

	@Override
	public String getTableName() {
		return "DLFileShortcut";
	}

	@Override
	public String getUserIdColumnName() {
		return "userId";
	}

}