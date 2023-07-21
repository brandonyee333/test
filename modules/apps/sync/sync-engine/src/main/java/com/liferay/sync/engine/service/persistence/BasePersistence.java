/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.service.persistence;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

/**
 * @author Shinn Lok
 */
public interface BasePersistence<TT, TID> extends Dao<TT, TID> {

	public int createTable() throws SQLException;

}