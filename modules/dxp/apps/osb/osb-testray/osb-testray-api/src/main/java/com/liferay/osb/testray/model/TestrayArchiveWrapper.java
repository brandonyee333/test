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

package com.liferay.osb.testray.model;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.sql.Blob;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link TestrayArchive}.
 * </p>
 *
 * @author Ethan Bustad
 * @see TestrayArchive
 * @generated
 */
public class TestrayArchiveWrapper
	implements ModelWrapper<TestrayArchive>, TestrayArchive {

	public TestrayArchiveWrapper(TestrayArchive testrayArchive) {
		_testrayArchive = testrayArchive;
	}

	@Override
	public Class<?> getModelClass() {
		return TestrayArchive.class;
	}

	@Override
	public String getModelClassName() {
		return TestrayArchive.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("testrayArchiveId", getTestrayArchiveId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("compressedData", getCompressedData());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long testrayArchiveId = (Long)attributes.get("testrayArchiveId");

		if (testrayArchiveId != null) {
			setTestrayArchiveId(testrayArchiveId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
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

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		Blob compressedData = (Blob)attributes.get("compressedData");

		if (compressedData != null) {
			setCompressedData(compressedData);
		}
	}

	@Override
	public Object clone() {
		return new TestrayArchiveWrapper(
			(TestrayArchive)_testrayArchive.clone());
	}

	@Override
	public int compareTo(TestrayArchive testrayArchive) {
		return _testrayArchive.compareTo(testrayArchive);
	}

	/**
	 * Returns the fully qualified class name of this testray archive.
	 *
	 * @return the fully qualified class name of this testray archive
	 */
	@Override
	public String getClassName() {
		return _testrayArchive.getClassName();
	}

	/**
	 * Returns the class name ID of this testray archive.
	 *
	 * @return the class name ID of this testray archive
	 */
	@Override
	public long getClassNameId() {
		return _testrayArchive.getClassNameId();
	}

	/**
	 * Returns the class pk of this testray archive.
	 *
	 * @return the class pk of this testray archive
	 */
	@Override
	public long getClassPK() {
		return _testrayArchive.getClassPK();
	}

	/**
	 * Returns the company ID of this testray archive.
	 *
	 * @return the company ID of this testray archive
	 */
	@Override
	public long getCompanyId() {
		return _testrayArchive.getCompanyId();
	}

	/**
	 * Returns the compressed data of this testray archive.
	 *
	 * @return the compressed data of this testray archive
	 */
	@Override
	public Blob getCompressedData() {
		return _testrayArchive.getCompressedData();
	}

	/**
	 * Returns the create date of this testray archive.
	 *
	 * @return the create date of this testray archive
	 */
	@Override
	public Date getCreateDate() {
		return _testrayArchive.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _testrayArchive.getExpandoBridge();
	}

	/**
	 * Returns the group ID of this testray archive.
	 *
	 * @return the group ID of this testray archive
	 */
	@Override
	public long getGroupId() {
		return _testrayArchive.getGroupId();
	}

	/**
	 * Returns the modified date of this testray archive.
	 *
	 * @return the modified date of this testray archive
	 */
	@Override
	public Date getModifiedDate() {
		return _testrayArchive.getModifiedDate();
	}

	/**
	 * Returns the primary key of this testray archive.
	 *
	 * @return the primary key of this testray archive
	 */
	@Override
	public long getPrimaryKey() {
		return _testrayArchive.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _testrayArchive.getPrimaryKeyObj();
	}

	/**
	 * Returns the testray archive ID of this testray archive.
	 *
	 * @return the testray archive ID of this testray archive
	 */
	@Override
	public long getTestrayArchiveId() {
		return _testrayArchive.getTestrayArchiveId();
	}

	/**
	 * Returns the user ID of this testray archive.
	 *
	 * @return the user ID of this testray archive
	 */
	@Override
	public long getUserId() {
		return _testrayArchive.getUserId();
	}

	/**
	 * Returns the user name of this testray archive.
	 *
	 * @return the user name of this testray archive
	 */
	@Override
	public String getUserName() {
		return _testrayArchive.getUserName();
	}

	/**
	 * Returns the user uuid of this testray archive.
	 *
	 * @return the user uuid of this testray archive
	 */
	@Override
	public String getUserUuid() {
		return _testrayArchive.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _testrayArchive.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _testrayArchive.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _testrayArchive.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _testrayArchive.isNew();
	}

	@Override
	public void persist() {
		_testrayArchive.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_testrayArchive.setCachedModel(cachedModel);
	}

	@Override
	public void setClassName(String className) {
		_testrayArchive.setClassName(className);
	}

	/**
	 * Sets the class name ID of this testray archive.
	 *
	 * @param classNameId the class name ID of this testray archive
	 */
	@Override
	public void setClassNameId(long classNameId) {
		_testrayArchive.setClassNameId(classNameId);
	}

	/**
	 * Sets the class pk of this testray archive.
	 *
	 * @param classPK the class pk of this testray archive
	 */
	@Override
	public void setClassPK(long classPK) {
		_testrayArchive.setClassPK(classPK);
	}

	/**
	 * Sets the company ID of this testray archive.
	 *
	 * @param companyId the company ID of this testray archive
	 */
	@Override
	public void setCompanyId(long companyId) {
		_testrayArchive.setCompanyId(companyId);
	}

	/**
	 * Sets the compressed data of this testray archive.
	 *
	 * @param compressedData the compressed data of this testray archive
	 */
	@Override
	public void setCompressedData(Blob compressedData) {
		_testrayArchive.setCompressedData(compressedData);
	}

	/**
	 * Sets the create date of this testray archive.
	 *
	 * @param createDate the create date of this testray archive
	 */
	@Override
	public void setCreateDate(Date createDate) {
		_testrayArchive.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_testrayArchive.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_testrayArchive.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_testrayArchive.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the group ID of this testray archive.
	 *
	 * @param groupId the group ID of this testray archive
	 */
	@Override
	public void setGroupId(long groupId) {
		_testrayArchive.setGroupId(groupId);
	}

	/**
	 * Sets the modified date of this testray archive.
	 *
	 * @param modifiedDate the modified date of this testray archive
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_testrayArchive.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_testrayArchive.setNew(n);
	}

	/**
	 * Sets the primary key of this testray archive.
	 *
	 * @param primaryKey the primary key of this testray archive
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_testrayArchive.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_testrayArchive.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the testray archive ID of this testray archive.
	 *
	 * @param testrayArchiveId the testray archive ID of this testray archive
	 */
	@Override
	public void setTestrayArchiveId(long testrayArchiveId) {
		_testrayArchive.setTestrayArchiveId(testrayArchiveId);
	}

	/**
	 * Sets the user ID of this testray archive.
	 *
	 * @param userId the user ID of this testray archive
	 */
	@Override
	public void setUserId(long userId) {
		_testrayArchive.setUserId(userId);
	}

	/**
	 * Sets the user name of this testray archive.
	 *
	 * @param userName the user name of this testray archive
	 */
	@Override
	public void setUserName(String userName) {
		_testrayArchive.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this testray archive.
	 *
	 * @param userUuid the user uuid of this testray archive
	 */
	@Override
	public void setUserUuid(String userUuid) {
		_testrayArchive.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<TestrayArchive>
		toCacheModel() {

		return _testrayArchive.toCacheModel();
	}

	@Override
	public TestrayArchive toEscapedModel() {
		return new TestrayArchiveWrapper(_testrayArchive.toEscapedModel());
	}

	@Override
	public String toString() {
		return _testrayArchive.toString();
	}

	@Override
	public TestrayArchive toUnescapedModel() {
		return new TestrayArchiveWrapper(_testrayArchive.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _testrayArchive.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TestrayArchiveWrapper)) {
			return false;
		}

		TestrayArchiveWrapper testrayArchiveWrapper =
			(TestrayArchiveWrapper)obj;

		if (Objects.equals(
				_testrayArchive, testrayArchiveWrapper._testrayArchive)) {

			return true;
		}

		return false;
	}

	@Override
	public TestrayArchive getWrappedModel() {
		return _testrayArchive;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _testrayArchive.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _testrayArchive.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_testrayArchive.resetOriginalValues();
	}

	private final TestrayArchive _testrayArchive;

}