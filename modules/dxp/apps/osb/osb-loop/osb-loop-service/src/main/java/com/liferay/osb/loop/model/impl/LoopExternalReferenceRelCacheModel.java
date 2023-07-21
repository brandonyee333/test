/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.model.impl;

import com.liferay.osb.loop.model.LoopExternalReferenceRel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing LoopExternalReferenceRel in entity cache.
 *
 * @author Ethan Bustad
 * @generated
 */
public class LoopExternalReferenceRelCacheModel
	implements CacheModel<LoopExternalReferenceRel>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof LoopExternalReferenceRelCacheModel)) {
			return false;
		}

		LoopExternalReferenceRelCacheModel loopExternalReferenceRelCacheModel =
			(LoopExternalReferenceRelCacheModel)object;

		if (loopExternalReferenceRelId ==
				loopExternalReferenceRelCacheModel.loopExternalReferenceRelId) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, loopExternalReferenceRelId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{loopExternalReferenceRelId=");
		sb.append(loopExternalReferenceRelId);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", externalReferenceName=");
		sb.append(externalReferenceName);
		sb.append(", externalReferencePK=");
		sb.append(externalReferencePK);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public LoopExternalReferenceRel toEntityModel() {
		LoopExternalReferenceRelImpl loopExternalReferenceRelImpl =
			new LoopExternalReferenceRelImpl();

		loopExternalReferenceRelImpl.setLoopExternalReferenceRelId(
			loopExternalReferenceRelId);
		loopExternalReferenceRelImpl.setClassNameId(classNameId);
		loopExternalReferenceRelImpl.setClassPK(classPK);

		if (externalReferenceName == null) {
			loopExternalReferenceRelImpl.setExternalReferenceName("");
		}
		else {
			loopExternalReferenceRelImpl.setExternalReferenceName(
				externalReferenceName);
		}

		if (externalReferencePK == null) {
			loopExternalReferenceRelImpl.setExternalReferencePK("");
		}
		else {
			loopExternalReferenceRelImpl.setExternalReferencePK(
				externalReferencePK);
		}

		loopExternalReferenceRelImpl.resetOriginalValues();

		return loopExternalReferenceRelImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		loopExternalReferenceRelId = objectInput.readLong();

		classNameId = objectInput.readLong();

		classPK = objectInput.readLong();
		externalReferenceName = objectInput.readUTF();
		externalReferencePK = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(loopExternalReferenceRelId);

		objectOutput.writeLong(classNameId);

		objectOutput.writeLong(classPK);

		if (externalReferenceName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(externalReferenceName);
		}

		if (externalReferencePK == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(externalReferencePK);
		}
	}

	public long loopExternalReferenceRelId;
	public long classNameId;
	public long classPK;
	public String externalReferenceName;
	public String externalReferencePK;

}