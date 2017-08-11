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

package com.liferay.osb.hook.upgrade.v3_0_6;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.ecommerce.model.ECDocumentEntryConstants;
import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.portal.kernel.util.StringBundler;

*/

/**
 * @author Douglas Wong
 */
public class Upgrade_20140725100517062_ECDocumentEntry extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

	/*@Override
	public long getTimestamp() {
		return 20140725100517062L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		upgradeECDocumentEntry();
	}

	protected void upgradeECDocumentEntry() throws Exception {
		StringBundler sb = new StringBundler(4);

		sb.append("update ECommerce_ECDocumentEntry set status = ");
		sb.append(ECDocumentEntryConstants.STATUS_PAID);
		sb.append(" where ecDocumentEntryId = ");
		sb.append(_MALFORMED_EC_DOCUMENT_ENTRY_ID);

		runSQL(sb.toString(), false);
	}

	private static final long _MALFORMED_EC_DOCUMENT_ENTRY_ID = 44001;

	 */

}