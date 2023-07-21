/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.internal.verify.model;

import com.liferay.journal.model.JournalFeed;
import com.liferay.portal.kernel.verify.model.VerifiableResourcedModel;
import com.liferay.portal.kernel.verify.model.VerifiableUUIDModel;

/**
 * @author Brian Wing Shun Chan
 */
public class JournalFeedVerifiableModel
	implements VerifiableResourcedModel, VerifiableUUIDModel {

	@Override
	public String getModelName() {
		return JournalFeed.class.getName();
	}

	@Override
	public String getPrimaryKeyColumnName() {
		return "id_";
	}

	@Override
	public String getTableName() {
		return "JournalFeed";
	}

	@Override
	public String getUserIdColumnName() {
		return "userId";
	}

}