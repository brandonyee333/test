/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.hook.upgrade.v3_0_2;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.CorpEntry;
import com.liferay.osb.util.OSBConstants;
import com.liferay.expando.kernel.exception.NoSuchTableException;
import com.liferay.expando.kernel.model.ExpandoColumn;
import com.liferay.expando.kernel.model.ExpandoTable;
import com.liferay.expando.kernel.service.ExpandoColumnLocalServiceUtil;
import com.liferay.expando.kernel.service.ExpandoTableLocalServiceUtil;

/**
 * @author Amos Fong
 */
public class Upgrade_20140602162601983_Expando extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20140602162601983L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		updateExpandoColumn(OSBConstants.COMPANY_ID);
	}

	protected void updateExpandoColumn(long companyId) throws Exception {
		ExpandoTable expandoTable = null;

		try {
			expandoTable = ExpandoTableLocalServiceUtil.getTable(
				companyId, CorpEntry.class.getName(), "OSB");
		}
		catch (NoSuchTableException nste) {
			return;
		}

		ExpandoColumn expandoColumn = ExpandoColumnLocalServiceUtil.getColumn(
			companyId, expandoTable.getClassName(), expandoTable.getName(),
			"osbClassification");

		if (expandoColumn != null) {
			ExpandoColumnLocalServiceUtil.deleteColumn(
				expandoColumn.getColumnId());
		}
	}

}