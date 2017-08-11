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

package com.liferay.osb.hook.upgrade.v3_0_8;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.impl.HolidayCalendarModelImpl;
import com.liferay.osb.model.impl.HolidayCalendarRelImpl;

*/

/**
 * @author Brent Krone-Schmidt
 */
public class Upgrade_20140424154131198_HolidayCalendar extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

	/*@Override
	public long getTimestamp() {
		return 20140424154131198L;
	}

	protected void createHolidayCalendar() throws Exception {
		if (!hasTable(HolidayCalendarModelImpl.TABLE_NAME)) {
			runSQL(HolidayCalendarModelImpl.TABLE_SQL_CREATE);
		}
	}

	protected void createHolidayCalendarRel() throws Exception {
		if (!hasTable(HolidayCalendarRelImpl.TABLE_NAME)) {
			runSQL(HolidayCalendarRelImpl.TABLE_SQL_CREATE);

			runSQL(
				"create index IX_AB919B7 on OSB_HolidayCalendarRel " +
					"(holidayCalendarId)");
			runSQL(
				"create index IX_1738CAF1 on OSB_HolidayCalendarRel " +
					"(holidayCalendarId, userId)");
			runSQL(
				"create index IX_B2BCBD24 on OSB_HolidayCalendarRel (userId)");
		}
	}

	@Override
	protected void doUpgrade() throws Exception {
		createHolidayCalendar();
		createHolidayCalendarRel();
	}

	 */

}