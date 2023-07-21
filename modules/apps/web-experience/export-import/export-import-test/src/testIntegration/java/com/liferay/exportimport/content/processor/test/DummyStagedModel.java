/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.exportimport.content.processor.test;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.model.StagedModel;

import java.io.Serializable;

import java.util.Date;

/**
 * @author Michael Bowerman
 */
public class DummyStagedModel implements StagedModel {

	@Override
	public Object clone() {
		return null;
	}

	@Override
	public long getCompanyId() {
		return 0;
	}

	@Override
	public Date getCreateDate() {
		return null;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return null;
	}

	@Override
	public Class<?> getModelClass() {
		return null;
	}

	@Override
	public String getModelClassName() {
		return DummyStagedModel.class.getName();
	}

	@Override
	public Date getModifiedDate() {
		return null;
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return null;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(DummyStagedModel.class);
	}

	@Override
	public String getUuid() {
		return null;
	}

	@Override
	public void setCompanyId(long companyId) {
	}

	@Override
	public void setCreateDate(Date date) {
	}

	@Override
	public void setModifiedDate(Date date) {
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
	}

	@Override
	public void setUuid(String uuid) {
	}

}