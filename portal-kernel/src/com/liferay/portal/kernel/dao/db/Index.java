/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.dao.db;

/**
 * @author Brian Wing Shun Chan
 */
public class Index {

	public Index(String indexName, String tableName, boolean unique) {
		_indexName = indexName;
		_tableName = tableName;
		_unique = unique;
	}

	public String getIndexName() {
		return _indexName;
	}

	public String getTableName() {
		return _tableName;
	}

	public boolean isUnique() {
		return _unique;
	}

	private final String _indexName;
	private final String _tableName;
	private final boolean _unique;

}