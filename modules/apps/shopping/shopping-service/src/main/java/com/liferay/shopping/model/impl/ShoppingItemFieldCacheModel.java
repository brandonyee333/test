/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.shopping.model.impl;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.shopping.model.ShoppingItemField;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ShoppingItemField in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ShoppingItemFieldCacheModel
	implements CacheModel<ShoppingItemField>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ShoppingItemFieldCacheModel)) {
			return false;
		}

		ShoppingItemFieldCacheModel shoppingItemFieldCacheModel =
			(ShoppingItemFieldCacheModel)object;

		if (itemFieldId == shoppingItemFieldCacheModel.itemFieldId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, itemFieldId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{itemFieldId=");
		sb.append(itemFieldId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", itemId=");
		sb.append(itemId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", values=");
		sb.append(values);
		sb.append(", description=");
		sb.append(description);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ShoppingItemField toEntityModel() {
		ShoppingItemFieldImpl shoppingItemFieldImpl =
			new ShoppingItemFieldImpl();

		shoppingItemFieldImpl.setItemFieldId(itemFieldId);
		shoppingItemFieldImpl.setCompanyId(companyId);
		shoppingItemFieldImpl.setItemId(itemId);

		if (name == null) {
			shoppingItemFieldImpl.setName("");
		}
		else {
			shoppingItemFieldImpl.setName(name);
		}

		if (values == null) {
			shoppingItemFieldImpl.setValues("");
		}
		else {
			shoppingItemFieldImpl.setValues(values);
		}

		if (description == null) {
			shoppingItemFieldImpl.setDescription("");
		}
		else {
			shoppingItemFieldImpl.setDescription(description);
		}

		shoppingItemFieldImpl.resetOriginalValues();

		return shoppingItemFieldImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		itemFieldId = objectInput.readLong();

		companyId = objectInput.readLong();

		itemId = objectInput.readLong();
		name = objectInput.readUTF();
		values = objectInput.readUTF();
		description = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(itemFieldId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(itemId);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (values == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(values);
		}

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}
	}

	public long itemFieldId;
	public long companyId;
	public long itemId;
	public String name;
	public String values;
	public String description;

}