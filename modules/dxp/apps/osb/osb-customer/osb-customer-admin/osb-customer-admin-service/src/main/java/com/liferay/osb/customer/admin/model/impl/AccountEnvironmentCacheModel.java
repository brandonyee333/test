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

import com.liferay.osb.customer.admin.model.AccountEnvironment;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing AccountEnvironment in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AccountEnvironmentCacheModel
	implements CacheModel<AccountEnvironment>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AccountEnvironmentCacheModel)) {
			return false;
		}

		AccountEnvironmentCacheModel accountEnvironmentCacheModel =
			(AccountEnvironmentCacheModel)obj;

		if (accountEnvironmentId ==
				accountEnvironmentCacheModel.accountEnvironmentId) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, accountEnvironmentId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(37);

		sb.append("{accountEnvironmentId=");
		sb.append(accountEnvironmentId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", accountEntryId=");
		sb.append(accountEntryId);
		sb.append(", productEntryId=");
		sb.append(productEntryId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", envOS=");
		sb.append(envOS);
		sb.append(", envOSCustom=");
		sb.append(envOSCustom);
		sb.append(", envDB=");
		sb.append(envDB);
		sb.append(", envJVM=");
		sb.append(envJVM);
		sb.append(", envAS=");
		sb.append(envAS);
		sb.append(", envLFR=");
		sb.append(envLFR);
		sb.append(", envCommerce=");
		sb.append(envCommerce);
		sb.append(", envBrowser=");
		sb.append(envBrowser);
		sb.append(", envCS=");
		sb.append(envCS);
		sb.append(", envSearch=");
		sb.append(envSearch);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public AccountEnvironment toEntityModel() {
		AccountEnvironmentImpl accountEnvironmentImpl =
			new AccountEnvironmentImpl();

		accountEnvironmentImpl.setAccountEnvironmentId(accountEnvironmentId);
		accountEnvironmentImpl.setUserId(userId);

		if (userName == null) {
			accountEnvironmentImpl.setUserName("");
		}
		else {
			accountEnvironmentImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			accountEnvironmentImpl.setCreateDate(null);
		}
		else {
			accountEnvironmentImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			accountEnvironmentImpl.setModifiedDate(null);
		}
		else {
			accountEnvironmentImpl.setModifiedDate(new Date(modifiedDate));
		}

		accountEnvironmentImpl.setAccountEntryId(accountEntryId);
		accountEnvironmentImpl.setProductEntryId(productEntryId);

		if (name == null) {
			accountEnvironmentImpl.setName("");
		}
		else {
			accountEnvironmentImpl.setName(name);
		}

		accountEnvironmentImpl.setEnvOS(envOS);

		if (envOSCustom == null) {
			accountEnvironmentImpl.setEnvOSCustom("");
		}
		else {
			accountEnvironmentImpl.setEnvOSCustom(envOSCustom);
		}

		accountEnvironmentImpl.setEnvDB(envDB);
		accountEnvironmentImpl.setEnvJVM(envJVM);
		accountEnvironmentImpl.setEnvAS(envAS);
		accountEnvironmentImpl.setEnvLFR(envLFR);
		accountEnvironmentImpl.setEnvCommerce(envCommerce);
		accountEnvironmentImpl.setEnvBrowser(envBrowser);
		accountEnvironmentImpl.setEnvCS(envCS);

		if (envSearch == null) {
			accountEnvironmentImpl.setEnvSearch("");
		}
		else {
			accountEnvironmentImpl.setEnvSearch(envSearch);
		}

		accountEnvironmentImpl.resetOriginalValues();

		return accountEnvironmentImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		accountEnvironmentId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		accountEntryId = objectInput.readLong();

		productEntryId = objectInput.readLong();
		name = objectInput.readUTF();

		envOS = objectInput.readInt();
		envOSCustom = objectInput.readUTF();

		envDB = objectInput.readInt();

		envJVM = objectInput.readInt();

		envAS = objectInput.readInt();

		envLFR = objectInput.readInt();

		envCommerce = objectInput.readInt();

		envBrowser = objectInput.readInt();

		envCS = objectInput.readInt();
		envSearch = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(accountEnvironmentId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(accountEntryId);

		objectOutput.writeLong(productEntryId);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		objectOutput.writeInt(envOS);

		if (envOSCustom == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(envOSCustom);
		}

		objectOutput.writeInt(envDB);

		objectOutput.writeInt(envJVM);

		objectOutput.writeInt(envAS);

		objectOutput.writeInt(envLFR);

		objectOutput.writeInt(envCommerce);

		objectOutput.writeInt(envBrowser);

		objectOutput.writeInt(envCS);

		if (envSearch == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(envSearch);
		}
	}

	public long accountEnvironmentId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long accountEntryId;
	public long productEntryId;
	public String name;
	public int envOS;
	public String envOSCustom;
	public int envDB;
	public int envJVM;
	public int envAS;
	public int envLFR;
	public int envCommerce;
	public int envBrowser;
	public int envCS;
	public String envSearch;

}