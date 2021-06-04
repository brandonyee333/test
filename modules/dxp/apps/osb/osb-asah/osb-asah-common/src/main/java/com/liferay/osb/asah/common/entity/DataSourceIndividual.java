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
public class DataSourceIndividual {

	public DataSourceIndividual() {
	}

	public DataSourceIndividual(Map<String, Object> source) {
		BeanUtils.copyProperties(source, this);
	}

	public DataSourceIndividual(
		Set<String> accountPKs, Long dataSourceId, Long individualId,
		Set<String> individualPKs) {

		_accountPKs = accountPKs;
		_dataSourceId = dataSourceId;
		_individualId = individualId;
		_individualPKs = individualPKs;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DataSourceIndividual)) {
			return false;
		}

		DataSourceIndividual dataSourceIndividual = (DataSourceIndividual)obj;

		if (Objects.equals(_accountPKs, dataSourceIndividual._accountPKs) &&
			Objects.equals(_dataSourceId, dataSourceIndividual._dataSourceId) &&
			Objects.equals(_individualId, dataSourceIndividual._individualId) &&
			Objects.equals(
				_individualPKs, dataSourceIndividual._individualPKs)) {

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
	public Long getIndividualId() {
		return _individualId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Set<String> getIndividualPKs() {
		return _individualPKs;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_accountPKs, _dataSourceId, _individualId, _individualPKs);
	}

	public void setAccountPKs(Set<String> accountPKs) {
		_accountPKs = accountPKs;
	}

	public void setDataSourceId(Long dataSourceId) {
		_dataSourceId = dataSourceId;
	}

	public void setIndividualId(Long individualId) {
		_individualId = individualId;
	}

	public void setIndividualPKs(Set<String> individualPKs) {
		_individualPKs = individualPKs;
	}

	@Transient
	private Set<String> _accountPKs;

	@Transient
	private Long _dataSourceId;

	@Transient
	private Long _individualId;

	@Transient
	private Set<String> _individualPKs;

}