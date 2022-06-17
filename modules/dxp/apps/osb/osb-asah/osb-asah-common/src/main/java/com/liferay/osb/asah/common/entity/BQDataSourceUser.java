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

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import com.liferay.osb.asah.common.util.BeanUtils;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author Rachael Koestartyo
 */
@Table
public class BQDataSourceUser {

	public BQDataSourceUser() {
	}

	public BQDataSourceUser(Map<String, Object> source) {
		BeanUtils.copyProperties(source, this);
	}

	public BQDataSourceUser(
		Set<String> accountPKs, Long dataSourceId, Long userId,
		Set<String> userPKs) {

		_accountPKs = accountPKs;
		_dataSourceId = dataSourceId;
		_userId = userId;
		_userPKs = userPKs;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BQDataSourceUser)) {
			return false;
		}

		BQDataSourceUser bqDataSourceUser = (BQDataSourceUser)obj;

		if (Objects.equals(_accountPKs, bqDataSourceUser._accountPKs) &&
			Objects.equals(_dataSourceId, bqDataSourceUser._dataSourceId) &&
			Objects.equals(_userId, bqDataSourceUser._userId) &&
			Objects.equals(_userPKs, bqDataSourceUser._userPKs)) {

			return true;
		}

		return false;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Set<String> getAccountPKs() {
		return _accountPKs;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@JsonSerialize(using = ToStringSerializer.class)
	public Long getDataSourceId() {
		return _dataSourceId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@JsonSerialize(using = ToStringSerializer.class)
	public Long getUserId() {
		return _userId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Set<String> getUserPKs() {
		return _userPKs;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_accountPKs, _dataSourceId, _userId, _userPKs);
	}

	public void setAccountPKs(Set<String> accountPKs) {
		_accountPKs = accountPKs;
	}

	public void setDataSourceId(Long dataSourceId) {
		_dataSourceId = dataSourceId;
	}

	public void setUserId(Long userId) {
		_userId = userId;
	}

	public void setUserPKs(Set<String> userPKs) {
		_userPKs = userPKs;
	}

	@Transient
	private Set<String> _accountPKs;

	@Transient
	private Long _dataSourceId;

	@Transient
	private Long _userId;

	@Transient
	private Set<String> _userPKs;

}