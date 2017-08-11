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

package com.liferay.osb.hook.upgrade.v2_4_6;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import UpgradeProcess;
import com.liferay.osb.model.CorpEntry;
import com.liferay.osb.util.OSBConstants;
import com.liferay.expando.kernel.exception.NoSuchTableException;
import com.liferay.expando.kernel.model.ExpandoColumn;
import com.liferay.expando.kernel.model.ExpandoColumnConstants;
import com.liferay.expando.kernel.model.ExpandoTable;
import com.liferay.expando.kernel.service.ExpandoColumnLocalServiceUtil;
import com.liferay.expando.kernel.service.ExpandoTableLocalServiceUtil;

*/

/**
 * @author Peter Shin
 */
public class UpgradeExpando extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

	/*@Override
	protected void doUpgrade() throws Exception {
		long companyId = OSBConstants.COMPANY_ID;

		updateExpandoColumn(companyId);
	}

	protected void updateExpandoColumn(long companyId) throws Exception {
		ExpandoTable expandoTable = null;

		try {
			expandoTable = ExpandoTableLocalServiceUtil.getTable(
				companyId, CorpEntry.class.getName(), "OSB");
		}
		catch (NoSuchTableException nste) {
			expandoTable = ExpandoTableLocalServiceUtil.addTable(
				companyId, CorpEntry.class.getName(), "OSB");
		}

		ExpandoColumn expandoColumn = ExpandoColumnLocalServiceUtil.getColumn(
			companyId, expandoTable.getClassName(), expandoTable.getName(),
			"osbClassification");

		if (expandoColumn != null) {
			ExpandoColumnLocalServiceUtil.deleteColumn(
				expandoColumn.getColumnId());
		}

		ExpandoColumnLocalServiceUtil.addColumn(
			expandoTable.getTableId(), "osbClassification",
			ExpandoColumnConstants.STRING_ARRAY, new String[0]);
	}

	 */

}