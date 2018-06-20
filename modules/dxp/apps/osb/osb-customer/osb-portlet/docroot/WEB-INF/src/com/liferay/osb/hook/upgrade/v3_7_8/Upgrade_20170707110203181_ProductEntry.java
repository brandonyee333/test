/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.hook.upgrade.v3_7_8;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.ProductEntry;
import com.liferay.osb.model.ProductEntryConstants;
import com.liferay.osb.service.ProductEntryLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;

import java.util.List;

/**
 * @author Amos Fong
 */
public class Upgrade_20170707110203181_ProductEntry extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20170707110203181L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		List<ProductEntry> productEntries =
			ProductEntryLocalServiceUtil.getProductEntries(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (ProductEntry productEntry : productEntries) {
			if (productEntry.getType() == ProductEntryConstants.TYPE_ADD_ON) {
				continue;
			}

			if (productEntry.isDigitalEnterprise() || productEntry.isPortal()) {
				productEntry.setType(ProductEntryConstants.TYPE_PRIMARY);

				ProductEntryLocalServiceUtil.updateProductEntry(productEntry);
			}
		}

		runSQL("create index IX_884EBE59 on OSB_ProductEntry (environment)");
	}

}