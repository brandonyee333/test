/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.internal.verify.model;

import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.portal.kernel.verify.model.VerifiableResourcedModel;

/**
 * @author Brian Wing Shun Chan
 */
public class DDMTemplateVerifiableModel implements VerifiableResourcedModel {

	@Override
	public String getModelName() {
		return DDMTemplate.class.getName();
	}

	@Override
	public String getPrimaryKeyColumnName() {
		return "templateId";
	}

	@Override
	public String getTableName() {
		return "DDMTemplate";
	}

	@Override
	public String getUserIdColumnName() {
		return "userId";
	}

}