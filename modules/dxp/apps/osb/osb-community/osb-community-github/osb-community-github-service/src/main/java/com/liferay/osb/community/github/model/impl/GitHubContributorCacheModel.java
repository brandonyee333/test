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

package com.liferay.osb.community.github.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.community.github.model.GitHubContributor;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing GitHubContributor in entity cache.
 *
 * @author Haote Chou
 * @see GitHubContributor
 * @generated
 */
@ProviderType
public class GitHubContributorCacheModel implements CacheModel<GitHubContributor>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof GitHubContributorCacheModel)) {
			return false;
		}

		GitHubContributorCacheModel gitHubContributorCacheModel = (GitHubContributorCacheModel)obj;

		if (gitHubContributorId == gitHubContributorCacheModel.gitHubContributorId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, gitHubContributorId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{gitHubContributorId=");
		sb.append(gitHubContributorId);
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
		sb.append(", gitHubRepositoryId=");
		sb.append(gitHubRepositoryId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", avatarURL=");
		sb.append(avatarURL);
		sb.append(", contributions=");
		sb.append(contributions);
		sb.append(", profileURL=");
		sb.append(profileURL);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public GitHubContributor toEntityModel() {
		GitHubContributorImpl gitHubContributorImpl = new GitHubContributorImpl();

		gitHubContributorImpl.setGitHubContributorId(gitHubContributorId);
		gitHubContributorImpl.setCompanyId(companyId);
		gitHubContributorImpl.setUserId(userId);

		if (userName == null) {
			gitHubContributorImpl.setUserName(StringPool.BLANK);
		}
		else {
			gitHubContributorImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			gitHubContributorImpl.setCreateDate(null);
		}
		else {
			gitHubContributorImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			gitHubContributorImpl.setModifiedDate(null);
		}
		else {
			gitHubContributorImpl.setModifiedDate(new Date(modifiedDate));
		}

		gitHubContributorImpl.setGitHubRepositoryId(gitHubRepositoryId);

		if (name == null) {
			gitHubContributorImpl.setName(StringPool.BLANK);
		}
		else {
			gitHubContributorImpl.setName(name);
		}

		if (avatarURL == null) {
			gitHubContributorImpl.setAvatarURL(StringPool.BLANK);
		}
		else {
			gitHubContributorImpl.setAvatarURL(avatarURL);
		}

		gitHubContributorImpl.setContributions(contributions);

		if (profileURL == null) {
			gitHubContributorImpl.setProfileURL(StringPool.BLANK);
		}
		else {
			gitHubContributorImpl.setProfileURL(profileURL);
		}

		gitHubContributorImpl.resetOriginalValues();

		return gitHubContributorImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		gitHubContributorId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		gitHubRepositoryId = objectInput.readLong();
		name = objectInput.readUTF();
		avatarURL = objectInput.readUTF();

		contributions = objectInput.readInt();
		profileURL = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(gitHubContributorId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(gitHubRepositoryId);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (avatarURL == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(avatarURL);
		}

		objectOutput.writeInt(contributions);

		if (profileURL == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(profileURL);
		}
	}

	public long gitHubContributorId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long gitHubRepositoryId;
	public String name;
	public String avatarURL;
	public int contributions;
	public String profileURL;
}