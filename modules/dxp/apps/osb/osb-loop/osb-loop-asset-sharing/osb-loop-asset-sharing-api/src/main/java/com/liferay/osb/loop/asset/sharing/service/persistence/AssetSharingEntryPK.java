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

package com.liferay.osb.loop.asset.sharing.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class AssetSharingEntryPK implements Comparable<AssetSharingEntryPK>,
	Serializable {
	public long classNameId;
	public long classPK;
	public long sharedToClassNameId;
	public long sharedToClassPK;

	public AssetSharingEntryPK() {
	}

	public AssetSharingEntryPK(long classNameId, long classPK,
		long sharedToClassNameId, long sharedToClassPK) {
		this.classNameId = classNameId;
		this.classPK = classPK;
		this.sharedToClassNameId = sharedToClassNameId;
		this.sharedToClassPK = sharedToClassPK;
	}

	public long getClassNameId() {
		return classNameId;
	}

	public void setClassNameId(long classNameId) {
		this.classNameId = classNameId;
	}

	public long getClassPK() {
		return classPK;
	}

	public void setClassPK(long classPK) {
		this.classPK = classPK;
	}

	public long getSharedToClassNameId() {
		return sharedToClassNameId;
	}

	public void setSharedToClassNameId(long sharedToClassNameId) {
		this.sharedToClassNameId = sharedToClassNameId;
	}

	public long getSharedToClassPK() {
		return sharedToClassPK;
	}

	public void setSharedToClassPK(long sharedToClassPK) {
		this.sharedToClassPK = sharedToClassPK;
	}

	@Override
	public int compareTo(AssetSharingEntryPK pk) {
		if (pk == null) {
			return -1;
		}

		int value = 0;

		if (classNameId < pk.classNameId) {
			value = -1;
		}
		else if (classNameId > pk.classNameId) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (classPK < pk.classPK) {
			value = -1;
		}
		else if (classPK > pk.classPK) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (sharedToClassNameId < pk.sharedToClassNameId) {
			value = -1;
		}
		else if (sharedToClassNameId > pk.sharedToClassNameId) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (sharedToClassPK < pk.sharedToClassPK) {
			value = -1;
		}
		else if (sharedToClassPK > pk.sharedToClassPK) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AssetSharingEntryPK)) {
			return false;
		}

		AssetSharingEntryPK pk = (AssetSharingEntryPK)obj;

		if ((classNameId == pk.classNameId) && (classPK == pk.classPK) &&
				(sharedToClassNameId == pk.sharedToClassNameId) &&
				(sharedToClassPK == pk.sharedToClassPK)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		int hashCode = 0;

		hashCode = HashUtil.hash(hashCode, classNameId);
		hashCode = HashUtil.hash(hashCode, classPK);
		hashCode = HashUtil.hash(hashCode, sharedToClassNameId);
		hashCode = HashUtil.hash(hashCode, sharedToClassPK);

		return hashCode;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(10);

		sb.append("{");

		sb.append("classNameId=");

		sb.append(classNameId);
		sb.append(", classPK=");

		sb.append(classPK);
		sb.append(", sharedToClassNameId=");

		sb.append(sharedToClassNameId);
		sb.append(", sharedToClassPK=");

		sb.append(sharedToClassPK);

		sb.append("}");

		return sb.toString();
	}
}