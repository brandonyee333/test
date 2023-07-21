/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.asset.entry.set.model;

import java.io.Serializable;

/**
 * @author Matthew Kong
 */
public class AssetEntrySetReference implements Serializable {

	public AssetEntrySetReference(
		long sharedToClassNameId, long sharedToClassPK) {

		_sharedToClassNameId = sharedToClassNameId;
		_sharedToClassPK = sharedToClassPK;
	}

	public long getSharedToClassNameId() {
		return _sharedToClassNameId;
	}

	public long getSharedToClassPK() {
		return _sharedToClassPK;
	}

	public void setSharedToClassNameId(long sharedToClassNameId) {
		_sharedToClassNameId = sharedToClassNameId;
	}

	public void setSharedToClassPK(long sharedToClassPK) {
		_sharedToClassPK = sharedToClassPK;
	}

	private long _sharedToClassNameId;
	private long _sharedToClassPK;

}