/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.upgrade;

/**
 * @author Shinn Lok
 */
public abstract class UpgradeProcess {

	public abstract int getThreshold();

	public abstract void upgrade() throws Exception;

	public abstract void upgradeSchema() throws Exception;

}