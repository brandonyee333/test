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

package com.liferay.osb.loop.asset.entry.set.service.persistence;

import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AssetEntrySetLikePK
	implements Comparable<AssetEntrySetLikePK>, Serializable {

	public long assetEntrySetId;
	public long classNameId;
	public long classPK;

	public AssetEntrySetLikePK() {
	}

	public AssetEntrySetLikePK(
		long assetEntrySetId, long classNameId, long classPK) {

		this.assetEntrySetId = assetEntrySetId;
		this.classNameId = classNameId;
		this.classPK = classPK;
	}

	public long getAssetEntrySetId() {
		return assetEntrySetId;
	}

	public void setAssetEntrySetId(long assetEntrySetId) {
		this.assetEntrySetId = assetEntrySetId;
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

	@Override
	public int compareTo(AssetEntrySetLikePK pk) {
		if (pk == null) {
			return -1;
		}

		int value = 0;

		if (assetEntrySetId < pk.assetEntrySetId) {
			value = -1;
		}
		else if (assetEntrySetId > pk.assetEntrySetId) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

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

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AssetEntrySetLikePK)) {
			return false;
		}

		AssetEntrySetLikePK pk = (AssetEntrySetLikePK)obj;

		if ((assetEntrySetId == pk.assetEntrySetId) &&
			(classNameId == pk.classNameId) && (classPK == pk.classPK)) {

			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		int hashCode = 0;

		hashCode = HashUtil.hash(hashCode, assetEntrySetId);
		hashCode = HashUtil.hash(hashCode, classNameId);
		hashCode = HashUtil.hash(hashCode, classPK);

		return hashCode;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(8);

		sb.append("{");

		sb.append("assetEntrySetId=");

		sb.append(assetEntrySetId);
		sb.append(", classNameId=");

		sb.append(classNameId);
		sb.append(", classPK=");

		sb.append(classPK);

		sb.append("}");

		return sb.toString();
	}

}