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

package com.liferay.osb.asah.common.configuration.impl;

import com.liferay.osb.asah.common.configuration.RuntimeConfiguration;

import java.util.Objects;

/**
 * @author Brian Wing Shun Chan
 */
public abstract class BaseRuntimeConfigurationImpl
	implements RuntimeConfiguration {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BaseRuntimeConfigurationImpl)) {
			return false;
		}

		BaseRuntimeConfigurationImpl baseRuntimeConfigurationImpl =
			(BaseRuntimeConfigurationImpl)obj;

		if (Objects.equals(
				_dataSourceId, baseRuntimeConfigurationImpl._dataSourceId) &&
			Objects.equals(
				_dataSourceState,
				baseRuntimeConfigurationImpl._dataSourceState) &&
			Objects.equals(
				_dataSourceStatus,
				baseRuntimeConfigurationImpl._dataSourceStatus)) {

			return true;
		}

		return false;
	}

	@Override
	public String getDataSourceId() {
		return _dataSourceId;
	}

	@Override
	public String getDataSourceState() {
		return _dataSourceState;
	}

	@Override
	public String getDataSourceStatus() {
		return _dataSourceStatus;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_dataSourceId, _dataSourceState, _dataSourceStatus);
	}

	@Override
	public void setDataSourceId(String dataSourceId) {
		_dataSourceId = dataSourceId;
	}

	@Override
	public void setDataSourceState(String dataSourceState) {
		_dataSourceState = dataSourceState;
	}

	@Override
	public void setDataSourceStatus(String dataSourceStatus) {
		_dataSourceStatus = dataSourceStatus;
	}

	private String _dataSourceId;
	private String _dataSourceState;
	private String _dataSourceStatus;

}