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

package com.liferay.osb.loop.asset.sharing.model.impl;

import com.liferay.osb.loop.asset.sharing.model.AssetSharingEntry;
import com.liferay.osb.loop.asset.sharing.service.persistence.AssetSharingEntryPK;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing AssetSharingEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AssetSharingEntryCacheModel
	implements CacheModel<AssetSharingEntry>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AssetSharingEntryCacheModel)) {
			return false;
		}

		AssetSharingEntryCacheModel assetSharingEntryCacheModel =
			(AssetSharingEntryCacheModel)obj;

		if (assetSharingEntryPK.equals(
				assetSharingEntryCacheModel.assetSharingEntryPK)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, assetSharingEntryPK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{classNameId=");
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

	@Override
	public AssetSharingEntry toEntityModel() {
		AssetSharingEntryImpl assetSharingEntryImpl =
			new AssetSharingEntryImpl();

		assetSharingEntryImpl.setClassNameId(classNameId);
		assetSharingEntryImpl.setClassPK(classPK);
		assetSharingEntryImpl.setSharedToClassNameId(sharedToClassNameId);
		assetSharingEntryImpl.setSharedToClassPK(sharedToClassPK);

		assetSharingEntryImpl.resetOriginalValues();

		return assetSharingEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		classNameId = objectInput.readLong();

		classPK = objectInput.readLong();

		sharedToClassNameId = objectInput.readLong();

		sharedToClassPK = objectInput.readLong();

		assetSharingEntryPK = new AssetSharingEntryPK(
			classNameId, classPK, sharedToClassNameId, sharedToClassPK);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(classNameId);

		objectOutput.writeLong(classPK);

		objectOutput.writeLong(sharedToClassNameId);

		objectOutput.writeLong(sharedToClassPK);
	}

	public long classNameId;
	public long classPK;
	public long sharedToClassNameId;
	public long sharedToClassPK;
	public transient AssetSharingEntryPK assetSharingEntryPK;

}