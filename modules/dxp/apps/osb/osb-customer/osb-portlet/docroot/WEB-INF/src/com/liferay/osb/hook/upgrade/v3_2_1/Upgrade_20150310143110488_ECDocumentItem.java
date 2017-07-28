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

package com.liferay.osb.hook.upgrade.v3_2_1;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.ecommerce.model.ECDocumentEntryConstants;
import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.portal.kernel.util.StringBundler;

*/

/**
 * @author Joan Kim
 */
public class Upgrade_20150310143110488_ECDocumentItem extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*@Override
	public long getTimestamp() {
		return 20150310143110488L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		StringBundler sb = new StringBundler(5);

		sb.append("delete from ECommerce_ECDocumentItem where ");
		sb.append("ecDocumentEntryId in (select ecDocumentEntryId from ");
		sb.append("ECommerce_ECDocumentEntry where type_ = ");
		sb.append(ECDocumentEntryConstants.TYPE_SALES_ORDER);
		sb.append(")");

		runSQL(sb.toString());
	}

}

*/

}