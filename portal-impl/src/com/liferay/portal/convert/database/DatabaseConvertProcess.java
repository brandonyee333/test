/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.convert.database;

import com.liferay.portal.convert.BaseConvertProcess;

import javax.sql.DataSource;

/**
 * @author     Alexander Chow
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public class DatabaseConvertProcess extends BaseConvertProcess {

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public void destroy() {
		throw new UnsupportedOperationException();
	}

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public String getDescription() {
		return null;
	}

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public String getParameterDescription() {
		return null;
	}

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public String[] getParameterNames() {
		return null;
	}

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isEnabled() {
		return false;
	}

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	protected DataSource buildDatasource() throws Exception {
		throw new UnsupportedOperationException();
	}

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	@Override
	protected void doConvert() throws Exception {
		throw new UnsupportedOperationException();
	}

}