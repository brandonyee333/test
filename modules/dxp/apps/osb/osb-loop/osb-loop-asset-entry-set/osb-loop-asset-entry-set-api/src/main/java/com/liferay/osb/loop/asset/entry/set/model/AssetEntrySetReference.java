/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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