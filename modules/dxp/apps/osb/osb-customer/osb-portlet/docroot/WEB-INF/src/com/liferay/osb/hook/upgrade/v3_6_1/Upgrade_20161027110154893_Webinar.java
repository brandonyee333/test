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

package com.liferay.osb.hook.upgrade.v3_6_1;

import com.liferay.osb.hook.upgrade.v3_3_0.Upgrade_20150617144546039_ObsoleteData;

/**
 * @author Amos Fong
 */
public class Upgrade_20161027110154893_Webinar
	extends Upgrade_20150617144546039_ObsoleteData {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*@Override
	public long getTimestamp() {
		return 20161027110154893L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (!hasTable("OSB_WebinarCustomer")) {
			return;
		}

		deletePortletPreferences("6_WAR_osbportlet");
		deleteResourceActions("6_WAR_osbportlet");

		runSQL("drop table OSB_WebinarCustomer");
		runSQL("drop table OSB_WebinarEvent");
	}

*/

}