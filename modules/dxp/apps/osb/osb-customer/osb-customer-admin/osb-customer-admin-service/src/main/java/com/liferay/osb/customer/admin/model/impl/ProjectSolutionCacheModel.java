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

import com.liferay.osb.customer.admin.model.ProjectSolution;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ProjectSolution in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ProjectSolutionCacheModel
	implements CacheModel<ProjectSolution>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ProjectSolutionCacheModel)) {
			return false;
		}

		ProjectSolutionCacheModel projectSolutionCacheModel =
			(ProjectSolutionCacheModel)object;

		if (salesforceProjectKey.equals(
				projectSolutionCacheModel.salesforceProjectKey)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, salesforceProjectKey);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(5);

		sb.append("{salesforceProjectKey=");
		sb.append(salesforceProjectKey);
		sb.append(", value=");
		sb.append(value);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ProjectSolution toEntityModel() {
		ProjectSolutionImpl projectSolutionImpl = new ProjectSolutionImpl();

		if (salesforceProjectKey == null) {
			projectSolutionImpl.setSalesforceProjectKey("");
		}
		else {
			projectSolutionImpl.setSalesforceProjectKey(salesforceProjectKey);
		}

		if (value == null) {
			projectSolutionImpl.setValue("");
		}
		else {
			projectSolutionImpl.setValue(value);
		}

		projectSolutionImpl.resetOriginalValues();

		return projectSolutionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		salesforceProjectKey = objectInput.readUTF();
		value = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (salesforceProjectKey == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(salesforceProjectKey);
		}

		if (value == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(value);
		}
	}

	public String salesforceProjectKey;
	public String value;

}