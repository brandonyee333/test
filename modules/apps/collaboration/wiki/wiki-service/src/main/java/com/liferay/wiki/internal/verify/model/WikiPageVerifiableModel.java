/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wiki.internal.verify.model;

import com.liferay.portal.kernel.verify.model.VerifiableResourcedModel;
import com.liferay.wiki.model.WikiPage;

/**
 * @author Brian Wing Shun Chan
 */
public class WikiPageVerifiableModel implements VerifiableResourcedModel {

	@Override
	public String getModelName() {
		return WikiPage.class.getName();
	}

	@Override
	public String getPrimaryKeyColumnName() {
		return "resourcePrimKey";
	}

	@Override
	public String getTableName() {
		return "WikiPage";
	}

	@Override
	public String getUserIdColumnName() {
		return "userId";
	}

}