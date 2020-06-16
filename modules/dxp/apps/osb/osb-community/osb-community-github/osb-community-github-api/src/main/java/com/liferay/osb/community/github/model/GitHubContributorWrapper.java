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

package com.liferay.osb.community.github.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link GitHubContributor}.
 * </p>
 *
 * @author Haote Chou
 * @see GitHubContributor
 * @generated
 */
@ProviderType
public class GitHubContributorWrapper implements GitHubContributor,
	ModelWrapper<GitHubContributor> {
	public GitHubContributorWrapper(GitHubContributor gitHubContributor) {
		_gitHubContributor = gitHubContributor;
	}

	@Override
	public Class<?> getModelClass() {
		return GitHubContributor.class;
	}

	@Override
	public String getModelClassName() {
		return GitHubContributor.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("gitHubContributorId", getGitHubContributorId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("gitHubRepositoryId", getGitHubRepositoryId());
		attributes.put("name", getName());
		attributes.put("avatarURL", getAvatarURL());
		attributes.put("contributions", getContributions());
		attributes.put("profileURL", getProfileURL());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long gitHubContributorId = (Long)attributes.get("gitHubContributorId");

		if (gitHubContributorId != null) {
			setGitHubContributorId(gitHubContributorId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long gitHubRepositoryId = (Long)attributes.get("gitHubRepositoryId");

		if (gitHubRepositoryId != null) {
			setGitHubRepositoryId(gitHubRepositoryId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String avatarURL = (String)attributes.get("avatarURL");

		if (avatarURL != null) {
			setAvatarURL(avatarURL);
		}

		Integer contributions = (Integer)attributes.get("contributions");

		if (contributions != null) {
			setContributions(contributions);
		}

		String profileURL = (String)attributes.get("profileURL");

		if (profileURL != null) {
			setProfileURL(profileURL);
		}
	}

	@Override
	public GitHubContributor toEscapedModel() {
		return new GitHubContributorWrapper(_gitHubContributor.toEscapedModel());
	}

	@Override
	public GitHubContributor toUnescapedModel() {
		return new GitHubContributorWrapper(_gitHubContributor.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _gitHubContributor.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _gitHubContributor.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _gitHubContributor.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _gitHubContributor.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<GitHubContributor> toCacheModel() {
		return _gitHubContributor.toCacheModel();
	}

	@Override
	public int compareTo(GitHubContributor gitHubContributor) {
		return _gitHubContributor.compareTo(gitHubContributor);
	}

	/**
	* Returns the contributions of this git hub contributor.
	*
	* @return the contributions of this git hub contributor
	*/
	@Override
	public int getContributions() {
		return _gitHubContributor.getContributions();
	}

	@Override
	public int hashCode() {
		return _gitHubContributor.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _gitHubContributor.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new GitHubContributorWrapper((GitHubContributor)_gitHubContributor.clone());
	}

	/**
	* Returns the avatar url of this git hub contributor.
	*
	* @return the avatar url of this git hub contributor
	*/
	@Override
	public java.lang.String getAvatarURL() {
		return _gitHubContributor.getAvatarURL();
	}

	/**
	* Returns the name of this git hub contributor.
	*
	* @return the name of this git hub contributor
	*/
	@Override
	public java.lang.String getName() {
		return _gitHubContributor.getName();
	}

	/**
	* Returns the profile url of this git hub contributor.
	*
	* @return the profile url of this git hub contributor
	*/
	@Override
	public java.lang.String getProfileURL() {
		return _gitHubContributor.getProfileURL();
	}

	/**
	* Returns the user name of this git hub contributor.
	*
	* @return the user name of this git hub contributor
	*/
	@Override
	public java.lang.String getUserName() {
		return _gitHubContributor.getUserName();
	}

	/**
	* Returns the user uuid of this git hub contributor.
	*
	* @return the user uuid of this git hub contributor
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _gitHubContributor.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _gitHubContributor.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _gitHubContributor.toXmlString();
	}

	/**
	* Returns the create date of this git hub contributor.
	*
	* @return the create date of this git hub contributor
	*/
	@Override
	public Date getCreateDate() {
		return _gitHubContributor.getCreateDate();
	}

	/**
	* Returns the modified date of this git hub contributor.
	*
	* @return the modified date of this git hub contributor
	*/
	@Override
	public Date getModifiedDate() {
		return _gitHubContributor.getModifiedDate();
	}

	/**
	* Returns the company ID of this git hub contributor.
	*
	* @return the company ID of this git hub contributor
	*/
	@Override
	public long getCompanyId() {
		return _gitHubContributor.getCompanyId();
	}

	/**
	* Returns the git hub contributor ID of this git hub contributor.
	*
	* @return the git hub contributor ID of this git hub contributor
	*/
	@Override
	public long getGitHubContributorId() {
		return _gitHubContributor.getGitHubContributorId();
	}

	/**
	* Returns the git hub repository ID of this git hub contributor.
	*
	* @return the git hub repository ID of this git hub contributor
	*/
	@Override
	public long getGitHubRepositoryId() {
		return _gitHubContributor.getGitHubRepositoryId();
	}

	/**
	* Returns the primary key of this git hub contributor.
	*
	* @return the primary key of this git hub contributor
	*/
	@Override
	public long getPrimaryKey() {
		return _gitHubContributor.getPrimaryKey();
	}

	/**
	* Returns the user ID of this git hub contributor.
	*
	* @return the user ID of this git hub contributor
	*/
	@Override
	public long getUserId() {
		return _gitHubContributor.getUserId();
	}

	@Override
	public void persist() {
		_gitHubContributor.persist();
	}

	/**
	* Sets the avatar url of this git hub contributor.
	*
	* @param avatarURL the avatar url of this git hub contributor
	*/
	@Override
	public void setAvatarURL(java.lang.String avatarURL) {
		_gitHubContributor.setAvatarURL(avatarURL);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_gitHubContributor.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this git hub contributor.
	*
	* @param companyId the company ID of this git hub contributor
	*/
	@Override
	public void setCompanyId(long companyId) {
		_gitHubContributor.setCompanyId(companyId);
	}

	/**
	* Sets the contributions of this git hub contributor.
	*
	* @param contributions the contributions of this git hub contributor
	*/
	@Override
	public void setContributions(int contributions) {
		_gitHubContributor.setContributions(contributions);
	}

	/**
	* Sets the create date of this git hub contributor.
	*
	* @param createDate the create date of this git hub contributor
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_gitHubContributor.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_gitHubContributor.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_gitHubContributor.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_gitHubContributor.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the git hub contributor ID of this git hub contributor.
	*
	* @param gitHubContributorId the git hub contributor ID of this git hub contributor
	*/
	@Override
	public void setGitHubContributorId(long gitHubContributorId) {
		_gitHubContributor.setGitHubContributorId(gitHubContributorId);
	}

	/**
	* Sets the git hub repository ID of this git hub contributor.
	*
	* @param gitHubRepositoryId the git hub repository ID of this git hub contributor
	*/
	@Override
	public void setGitHubRepositoryId(long gitHubRepositoryId) {
		_gitHubContributor.setGitHubRepositoryId(gitHubRepositoryId);
	}

	/**
	* Sets the modified date of this git hub contributor.
	*
	* @param modifiedDate the modified date of this git hub contributor
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_gitHubContributor.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this git hub contributor.
	*
	* @param name the name of this git hub contributor
	*/
	@Override
	public void setName(java.lang.String name) {
		_gitHubContributor.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_gitHubContributor.setNew(n);
	}

	/**
	* Sets the primary key of this git hub contributor.
	*
	* @param primaryKey the primary key of this git hub contributor
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_gitHubContributor.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_gitHubContributor.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the profile url of this git hub contributor.
	*
	* @param profileURL the profile url of this git hub contributor
	*/
	@Override
	public void setProfileURL(java.lang.String profileURL) {
		_gitHubContributor.setProfileURL(profileURL);
	}

	/**
	* Sets the user ID of this git hub contributor.
	*
	* @param userId the user ID of this git hub contributor
	*/
	@Override
	public void setUserId(long userId) {
		_gitHubContributor.setUserId(userId);
	}

	/**
	* Sets the user name of this git hub contributor.
	*
	* @param userName the user name of this git hub contributor
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_gitHubContributor.setUserName(userName);
	}

	/**
	* Sets the user uuid of this git hub contributor.
	*
	* @param userUuid the user uuid of this git hub contributor
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_gitHubContributor.setUserUuid(userUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof GitHubContributorWrapper)) {
			return false;
		}

		GitHubContributorWrapper gitHubContributorWrapper = (GitHubContributorWrapper)obj;

		if (Objects.equals(_gitHubContributor,
					gitHubContributorWrapper._gitHubContributor)) {
			return true;
		}

		return false;
	}

	@Override
	public GitHubContributor getWrappedModel() {
		return _gitHubContributor;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _gitHubContributor.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _gitHubContributor.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_gitHubContributor.resetOriginalValues();
	}

	private final GitHubContributor _gitHubContributor;
}