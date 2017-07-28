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

package com.liferay.osb.hook.upgrade;

/* TODO update rabbitMQ integration
*/

/**
 * @author Amos Fong
 */
public class OSBUpgradeImpl {// extends BaseUpgradeImpl {

/* TODO update custom upgrade class
	@Override
	public int[] getDeveloperBuildNumbers(String servletContextName) {

		try {
			Release release = ReleaseLocalServiceUtil.getRelease(
				servletContextName, 0);

			return new int[] {
				release.getBuildNumber() - 2, release.getBuildNumber() - 1,
				release.getBuildNumber()
			};
		}
		catch (PortalException pe) {
		}

		return new int[0];
	}

	@Override
	public boolean hasRun(long classPK) throws PortalException {

		long runTime = ExpandoValueLocalServiceUtil.getData(
			OSBConstants.COMPANY_ID, BaseUpgradeProcess.class.getName(),
			ExpandoTableConstants.DEFAULT_TABLE_NAME, _EXPANDO_COLUMN_NAME,
			classPK, 0L);

		if (runTime > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isDeveloperUpgrade() {
		return PortletPropsValues.DEVELOPER_UPGRADE_ENABLED;
	}

	@Override
	public void setRunTime(long classPK, long runTime) {
		try {
			ExpandoValueLocalServiceUtil.addValue(
				OSBConstants.COMPANY_ID, BaseUpgradeProcess.class.getName(),
				ExpandoTableConstants.DEFAULT_TABLE_NAME, _EXPANDO_COLUMN_NAME,
				classPK, runTime);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	@Override
	public void setUp() throws PortalException {
		ExpandoTable table = null;

		try {
			table = ExpandoTableLocalServiceUtil.getTable(
				OSBConstants.COMPANY_ID, BaseUpgradeProcess.class.getName(),
				ExpandoTableConstants.DEFAULT_TABLE_NAME);
		}
		catch (NoSuchTableException nste) {
			table = ExpandoTableLocalServiceUtil.addTable(
				OSBConstants.COMPANY_ID, BaseUpgradeProcess.class.getName(),
				ExpandoTableConstants.DEFAULT_TABLE_NAME);
		}

		try {
			ExpandoColumnLocalServiceUtil.addColumn(
				table.getTableId(), _EXPANDO_COLUMN_NAME,
				ExpandoColumnConstants.LONG);
		}
		catch (DuplicateColumnNameException dcne) {
		}
	}

	private static final String _EXPANDO_COLUMN_NAME = "runTime";

	private static Log _log = LogFactoryUtil.getLog(OSBUpgradeImpl.class);
*/
}