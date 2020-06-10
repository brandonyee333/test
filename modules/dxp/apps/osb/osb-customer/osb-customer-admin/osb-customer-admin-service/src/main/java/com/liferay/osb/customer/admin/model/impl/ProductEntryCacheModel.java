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

package com.liferay.osb.customer.admin.model.impl;

import com.liferay.osb.customer.admin.model.ProductEntry;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ProductEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ProductEntryCacheModel
	implements CacheModel<ProductEntry>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ProductEntryCacheModel)) {
			return false;
		}

		ProductEntryCacheModel productEntryCacheModel =
			(ProductEntryCacheModel)obj;

		if (productEntryId == productEntryCacheModel.productEntryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, productEntryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{productEntryId=");
		sb.append(productEntryId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", koroneikiProductKey=");
		sb.append(koroneikiProductKey);
		sb.append(", name=");
		sb.append(name);
		sb.append(", environment=");
		sb.append(environment);
		sb.append(", licenses=");
		sb.append(licenses);
		sb.append(", versionsListType=");
		sb.append(versionsListType);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ProductEntry toEntityModel() {
		ProductEntryImpl productEntryImpl = new ProductEntryImpl();

		productEntryImpl.setProductEntryId(productEntryId);
		productEntryImpl.setUserId(userId);

		if (userName == null) {
			productEntryImpl.setUserName("");
		}
		else {
			productEntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			productEntryImpl.setCreateDate(null);
		}
		else {
			productEntryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			productEntryImpl.setModifiedDate(null);
		}
		else {
			productEntryImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (koroneikiProductKey == null) {
			productEntryImpl.setKoroneikiProductKey("");
		}
		else {
			productEntryImpl.setKoroneikiProductKey(koroneikiProductKey);
		}

		if (name == null) {
			productEntryImpl.setName("");
		}
		else {
			productEntryImpl.setName(name);
		}

		productEntryImpl.setEnvironment(environment);
		productEntryImpl.setLicenses(licenses);

		if (versionsListType == null) {
			productEntryImpl.setVersionsListType("");
		}
		else {
			productEntryImpl.setVersionsListType(versionsListType);
		}

		productEntryImpl.resetOriginalValues();

		return productEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		productEntryId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		koroneikiProductKey = objectInput.readUTF();
		name = objectInput.readUTF();

		environment = objectInput.readInt();

		licenses = objectInput.readBoolean();
		versionsListType = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(productEntryId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (koroneikiProductKey == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(koroneikiProductKey);
		}

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		objectOutput.writeInt(environment);

		objectOutput.writeBoolean(licenses);

		if (versionsListType == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(versionsListType);
		}
	}

	public long productEntryId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String koroneikiProductKey;
	public String name;
	public int environment;
	public boolean licenses;
	public String versionsListType;

}