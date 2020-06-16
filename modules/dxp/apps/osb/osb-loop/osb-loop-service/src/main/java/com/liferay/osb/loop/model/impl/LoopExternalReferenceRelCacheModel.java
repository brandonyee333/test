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
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LoopExternalReferenceRelCacheModel)) {
			return false;
		}

		LoopExternalReferenceRelCacheModel loopExternalReferenceRelCacheModel =
			(LoopExternalReferenceRelCacheModel)obj;

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