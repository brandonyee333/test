/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wiki.internal.verify.model;

import com.liferay.portal.kernel.verify.model.VerifiableResourcedModel;
import com.liferay.wiki.model.WikiNode;

/**
 * @author Brian Wing Shun Chan
 */
public class WikiNodeVerifiableModel implements VerifiableResourcedModel {

	@Override
	public String getModelName() {
		return WikiNode.class.getName();
	}

	@Override
	public String getPrimaryKeyColumnName() {
		return "nodeId";
	}

	@Override
	public String getTableName() {
		return "WikiNode";
	}

	@Override
	public String getUserIdColumnName() {
		return "userId";
	}

}