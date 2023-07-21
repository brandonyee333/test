/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.shopping.model.impl;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.shopping.model.ShoppingCart;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ShoppingCart in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ShoppingCartCacheModel
	implements CacheModel<ShoppingCart>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ShoppingCartCacheModel)) {
			return false;
		}

		ShoppingCartCacheModel shoppingCartCacheModel =
			(ShoppingCartCacheModel)object;

		if (cartId == shoppingCartCacheModel.cartId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, cartId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{cartId=");
		sb.append(cartId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", itemIds=");
		sb.append(itemIds);
		sb.append(", couponCodes=");
		sb.append(couponCodes);
		sb.append(", altShipping=");
		sb.append(altShipping);
		sb.append(", insure=");
		sb.append(insure);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ShoppingCart toEntityModel() {
		ShoppingCartImpl shoppingCartImpl = new ShoppingCartImpl();

		shoppingCartImpl.setCartId(cartId);
		shoppingCartImpl.setGroupId(groupId);
		shoppingCartImpl.setCompanyId(companyId);
		shoppingCartImpl.setUserId(userId);

		if (userName == null) {
			shoppingCartImpl.setUserName("");
		}
		else {
			shoppingCartImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			shoppingCartImpl.setCreateDate(null);
		}
		else {
			shoppingCartImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			shoppingCartImpl.setModifiedDate(null);
		}
		else {
			shoppingCartImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (itemIds == null) {
			shoppingCartImpl.setItemIds("");
		}
		else {
			shoppingCartImpl.setItemIds(itemIds);
		}

		if (couponCodes == null) {
			shoppingCartImpl.setCouponCodes("");
		}
		else {
			shoppingCartImpl.setCouponCodes(couponCodes);
		}

		shoppingCartImpl.setAltShipping(altShipping);
		shoppingCartImpl.setInsure(insure);

		shoppingCartImpl.resetOriginalValues();

		return shoppingCartImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput)
		throws ClassNotFoundException, IOException {

		cartId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		itemIds = (String)objectInput.readObject();
		couponCodes = objectInput.readUTF();

		altShipping = objectInput.readInt();

		insure = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(cartId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (itemIds == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(itemIds);
		}

		if (couponCodes == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(couponCodes);
		}

		objectOutput.writeInt(altShipping);

		objectOutput.writeBoolean(insure);
	}

	public long cartId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String itemIds;
	public String couponCodes;
	public int altShipping;
	public boolean insure;

}