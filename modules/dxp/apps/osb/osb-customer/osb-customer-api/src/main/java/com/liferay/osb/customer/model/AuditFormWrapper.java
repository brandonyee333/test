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

package com.liferay.osb.customer.model;

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
 * This class is a wrapper for {@link AuditForm}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AuditForm
 * @generated
 */
public class AuditFormWrapper implements AuditForm, ModelWrapper<AuditForm> {

	public AuditFormWrapper(AuditForm auditForm) {
		_auditForm = auditForm;
	}

	@Override
	public Class<?> getModelClass() {
		return AuditForm.class;
	}

	@Override
	public String getModelClassName() {
		return AuditForm.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("auditFormId", getAuditFormId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("endUserName", getEndUserName());
		attributes.put("endUserEmailAddress", getEndUserEmailAddress());
		attributes.put("agreement", isAgreement());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long auditFormId = (Long)attributes.get("auditFormId");

		if (auditFormId != null) {
			setAuditFormId(auditFormId);
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

		String endUserName = (String)attributes.get("endUserName");

		if (endUserName != null) {
			setEndUserName(endUserName);
		}

		String endUserEmailAddress = (String)attributes.get(
			"endUserEmailAddress");

		if (endUserEmailAddress != null) {
			setEndUserEmailAddress(endUserEmailAddress);
		}

		Boolean agreement = (Boolean)attributes.get("agreement");

		if (agreement != null) {
			setAgreement(agreement);
		}
	}

	@Override
	public Object clone() {
		return new AuditFormWrapper((AuditForm)_auditForm.clone());
	}

	@Override
	public int compareTo(AuditForm auditForm) {
		return _auditForm.compareTo(auditForm);
	}

	/**
	 * Returns the agreement of this audit form.
	 *
	 * @return the agreement of this audit form
	 */
	@Override
	public boolean getAgreement() {
		return _auditForm.getAgreement();
	}

	/**
	 * Returns the audit form ID of this audit form.
	 *
	 * @return the audit form ID of this audit form
	 */
	@Override
	public long getAuditFormId() {
		return _auditForm.getAuditFormId();
	}

	/**
	 * Returns the create date of this audit form.
	 *
	 * @return the create date of this audit form
	 */
	@Override
	public Date getCreateDate() {
		return _auditForm.getCreateDate();
	}

	/**
	 * Returns the end user email address of this audit form.
	 *
	 * @return the end user email address of this audit form
	 */
	@Override
	public String getEndUserEmailAddress() {
		return _auditForm.getEndUserEmailAddress();
	}

	/**
	 * Returns the end user name of this audit form.
	 *
	 * @return the end user name of this audit form
	 */
	@Override
	public String getEndUserName() {
		return _auditForm.getEndUserName();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _auditForm.getExpandoBridge();
	}

	/**
	 * Returns the primary key of this audit form.
	 *
	 * @return the primary key of this audit form
	 */
	@Override
	public long getPrimaryKey() {
		return _auditForm.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _auditForm.getPrimaryKeyObj();
	}

	/**
	 * Returns the user ID of this audit form.
	 *
	 * @return the user ID of this audit form
	 */
	@Override
	public long getUserId() {
		return _auditForm.getUserId();
	}

	/**
	 * Returns the user name of this audit form.
	 *
	 * @return the user name of this audit form
	 */
	@Override
	public String getUserName() {
		return _auditForm.getUserName();
	}

	/**
	 * Returns the user uuid of this audit form.
	 *
	 * @return the user uuid of this audit form
	 */
	@Override
	public String getUserUuid() {
		return _auditForm.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _auditForm.hashCode();
	}

	/**
	 * Returns <code>true</code> if this audit form is agreement.
	 *
	 * @return <code>true</code> if this audit form is agreement; <code>false</code> otherwise
	 */
	@Override
	public boolean isAgreement() {
		return _auditForm.isAgreement();
	}

	@Override
	public boolean isCachedModel() {
		return _auditForm.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _auditForm.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _auditForm.isNew();
	}

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a audit form model instance should use the <code>AuditForm</code> interface instead.
	 */
	@Override
	public void persist() {
		_auditForm.persist();
	}

	/**
	 * Sets whether this audit form is agreement.
	 *
	 * @param agreement the agreement of this audit form
	 */
	@Override
	public void setAgreement(boolean agreement) {
		_auditForm.setAgreement(agreement);
	}

	/**
	 * Sets the audit form ID of this audit form.
	 *
	 * @param auditFormId the audit form ID of this audit form
	 */
	@Override
	public void setAuditFormId(long auditFormId) {
		_auditForm.setAuditFormId(auditFormId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_auditForm.setCachedModel(cachedModel);
	}

	/**
	 * Sets the create date of this audit form.
	 *
	 * @param createDate the create date of this audit form
	 */
	@Override
	public void setCreateDate(Date createDate) {
		_auditForm.setCreateDate(createDate);
	}

	/**
	 * Sets the end user email address of this audit form.
	 *
	 * @param endUserEmailAddress the end user email address of this audit form
	 */
	@Override
	public void setEndUserEmailAddress(String endUserEmailAddress) {
		_auditForm.setEndUserEmailAddress(endUserEmailAddress);
	}

	/**
	 * Sets the end user name of this audit form.
	 *
	 * @param endUserName the end user name of this audit form
	 */
	@Override
	public void setEndUserName(String endUserName) {
		_auditForm.setEndUserName(endUserName);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_auditForm.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_auditForm.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_auditForm.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_auditForm.setNew(n);
	}

	/**
	 * Sets the primary key of this audit form.
	 *
	 * @param primaryKey the primary key of this audit form
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_auditForm.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_auditForm.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the user ID of this audit form.
	 *
	 * @param userId the user ID of this audit form
	 */
	@Override
	public void setUserId(long userId) {
		_auditForm.setUserId(userId);
	}

	/**
	 * Sets the user name of this audit form.
	 *
	 * @param userName the user name of this audit form
	 */
	@Override
	public void setUserName(String userName) {
		_auditForm.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this audit form.
	 *
	 * @param userUuid the user uuid of this audit form
	 */
	@Override
	public void setUserUuid(String userUuid) {
		_auditForm.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<AuditForm>
		toCacheModel() {

		return _auditForm.toCacheModel();
	}

	@Override
	public AuditForm toEscapedModel() {
		return new AuditFormWrapper(_auditForm.toEscapedModel());
	}

	@Override
	public String toString() {
		return _auditForm.toString();
	}

	@Override
	public AuditForm toUnescapedModel() {
		return new AuditFormWrapper(_auditForm.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _auditForm.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AuditFormWrapper)) {
			return false;
		}

		AuditFormWrapper auditFormWrapper = (AuditFormWrapper)obj;

		if (Objects.equals(_auditForm, auditFormWrapper._auditForm)) {
			return true;
		}

		return false;
	}

	@Override
	public AuditForm getWrappedModel() {
		return _auditForm;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _auditForm.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _auditForm.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_auditForm.resetOriginalValues();
	}

	private final AuditForm _auditForm;

}