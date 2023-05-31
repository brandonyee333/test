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

package com.liferay.osb.asah.common.entity;

import com.liferay.osb.asah.common.spring.annotation.BigQueryColumn;
import com.liferay.osb.asah.common.util.BeanUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Marcellus Tavares
 */
public class BQMembershipIndividual {

	public BQMembershipIndividual() {
	}

	public BQMembershipIndividual(Map<String, Object> source) {
		BeanUtils.copyProperties(source, this);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BQMembershipIndividual)) {
			return false;
		}

		BQMembershipIndividual bqMembershipIndividual =
			(BQMembershipIndividual)obj;

		if (Objects.equals(
				_dataSourceUsers, bqMembershipIndividual._dataSourceUsers) &&
			Objects.equals(
				_individualId, bqMembershipIndividual._individualId) &&
			Objects.equals(
				_modifiedDate, bqMembershipIndividual._modifiedDate) &&
			Objects.equals(_segmentId, bqMembershipIndividual._segmentId)) {

			return true;
		}

		return false;
	}

	@BigQueryColumn
	public List<DataSourceUser> getDataSourceUsers() {
		return _dataSourceUsers;
	}

	@BigQueryColumn
	public String getIndividualId() {
		return _individualId;
	}

	@BigQueryColumn
	public Date getModifiedDate() {
		if (_modifiedDate == null) {
			return null;
		}

		return new Date(_modifiedDate.getTime());
	}

	@BigQueryColumn
	public Long getSegmentId() {
		return _segmentId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_individualId, _modifiedDate, _segmentId);
	}

	public void setDataSourceUsers(List<DataSourceUser> dataSourceUsers) {
		_dataSourceUsers = dataSourceUsers;
	}

	public void setIndividualId(String individualId) {
		_individualId = individualId;
	}

	public void setModifiedDate(Date modifiedDate) {
		if (modifiedDate != null) {
			_modifiedDate = new Date(modifiedDate.getTime());
		}
	}

	public void setSegmentId(Long segmentId) {
		_segmentId = segmentId;
	}

	public static class DataSourceUser {

		public DataSourceUser() {
		}

		public DataSourceUser(Long dataSourceId, String uuid) {
			_dataSourceId = dataSourceId;
			_uuid = uuid;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}

			if (!(obj instanceof DataSourceUser)) {
				return false;
			}

			DataSourceUser dataSourceUser = (DataSourceUser)obj;

			if (Objects.equals(_dataSourceId, dataSourceUser._dataSourceId) &&
				Objects.equals(_uuid, dataSourceUser._uuid)) {

				return true;
			}

			return false;
		}

		@BigQueryColumn
		public Long getDataSourceId() {
			return _dataSourceId;
		}

		@BigQueryColumn
		public String getUuid() {
			return _uuid;
		}

		@Override
		public int hashCode() {
			return Objects.hash(_dataSourceId, _uuid);
		}

		public void setDataSourceId(Long dataSourceId) {
			_dataSourceId = dataSourceId;
		}

		public void setUuid(String uuid) {
			_uuid = uuid;
		}

		private Long _dataSourceId;
		private String _uuid;

	}

	private List<DataSourceUser> _dataSourceUsers;
	private String _individualId;
	private Date _modifiedDate;
	private Long _segmentId;

}