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

package com.liferay.osb.customer.admin.model;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link ProjectSolution}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProjectSolution
 * @generated
 */
public class ProjectSolutionWrapper
	implements ModelWrapper<ProjectSolution>, ProjectSolution {

	public ProjectSolutionWrapper(ProjectSolution projectSolution) {
		_projectSolution = projectSolution;
	}

	@Override
	public Class<?> getModelClass() {
		return ProjectSolution.class;
	}

	@Override
	public String getModelClassName() {
		return ProjectSolution.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("salesforceProjectKey", getSalesforceProjectKey());
		attributes.put("value", getValue());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String salesforceProjectKey = (String)attributes.get(
			"salesforceProjectKey");

		if (salesforceProjectKey != null) {
			setSalesforceProjectKey(salesforceProjectKey);
		}

		String value = (String)attributes.get("value");

		if (value != null) {
			setValue(value);
		}
	}

	@Override
	public Object clone() {
		return new ProjectSolutionWrapper(
			(ProjectSolution)_projectSolution.clone());
	}

	@Override
	public int compareTo(ProjectSolution projectSolution) {
		return _projectSolution.compareTo(projectSolution);
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _projectSolution.getExpandoBridge();
	}

	/**
	 * Returns the primary key of this project solution.
	 *
	 * @return the primary key of this project solution
	 */
	@Override
	public String getPrimaryKey() {
		return _projectSolution.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _projectSolution.getPrimaryKeyObj();
	}

	/**
	 * Returns the salesforce project key of this project solution.
	 *
	 * @return the salesforce project key of this project solution
	 */
	@Override
	public String getSalesforceProjectKey() {
		return _projectSolution.getSalesforceProjectKey();
	}

	/**
	 * Returns the value of this project solution.
	 *
	 * @return the value of this project solution
	 */
	@Override
	public String getValue() {
		return _projectSolution.getValue();
	}

	@Override
	public String getZendeskTag() {
		return _projectSolution.getZendeskTag();
	}

	@Override
	public int hashCode() {
		return _projectSolution.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _projectSolution.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _projectSolution.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _projectSolution.isNew();
	}

	@Override
	public void persist() {
		_projectSolution.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_projectSolution.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_projectSolution.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_projectSolution.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_projectSolution.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_projectSolution.setNew(n);
	}

	/**
	 * Sets the primary key of this project solution.
	 *
	 * @param primaryKey the primary key of this project solution
	 */
	@Override
	public void setPrimaryKey(String primaryKey) {
		_projectSolution.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_projectSolution.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the salesforce project key of this project solution.
	 *
	 * @param salesforceProjectKey the salesforce project key of this project solution
	 */
	@Override
	public void setSalesforceProjectKey(String salesforceProjectKey) {
		_projectSolution.setSalesforceProjectKey(salesforceProjectKey);
	}

	/**
	 * Sets the value of this project solution.
	 *
	 * @param value the value of this project solution
	 */
	@Override
	public void setValue(String value) {
		_projectSolution.setValue(value);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ProjectSolution>
		toCacheModel() {

		return _projectSolution.toCacheModel();
	}

	@Override
	public ProjectSolution toEscapedModel() {
		return new ProjectSolutionWrapper(_projectSolution.toEscapedModel());
	}

	@Override
	public String toString() {
		return _projectSolution.toString();
	}

	@Override
	public ProjectSolution toUnescapedModel() {
		return new ProjectSolutionWrapper(_projectSolution.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _projectSolution.toXmlString();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ProjectSolutionWrapper)) {
			return false;
		}

		ProjectSolutionWrapper projectSolutionWrapper =
			(ProjectSolutionWrapper)object;

		if (Objects.equals(
				_projectSolution, projectSolutionWrapper._projectSolution)) {

			return true;
		}

		return false;
	}

	@Override
	public ProjectSolution getWrappedModel() {
		return _projectSolution;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _projectSolution.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _projectSolution.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_projectSolution.resetOriginalValues();
	}

	private final ProjectSolution _projectSolution;

}