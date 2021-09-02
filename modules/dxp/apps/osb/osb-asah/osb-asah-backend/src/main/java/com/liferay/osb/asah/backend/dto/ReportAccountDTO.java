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

package com.liferay.osb.asah.backend.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import com.liferay.osb.asah.backend.model.Account;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.util.SetUtil;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author Rachael Koestartyo
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonRootName("accounts")
public class ReportAccountDTO {

	public ReportAccountDTO() {
	}

	public ReportAccountDTO(Account account) {
		_activeIndividualsCount = account.getActiveIndividualsCount();
		_createDate = account.getDateCreated();
		_id = account.getId();
		_individualsCount = account.getIndividualsCount();
		_modifiedDate = account.getDateModified();
		_reportAccountPropertiesDTO = new ReportAccountPropertiesDTO(
			account.getProperties());
	}

	public ReportAccountDTO(List<Account> accounts) {
		_reportAccountDTOs = SetUtil.map(accounts, ReportAccountDTO::new);
	}

	@JsonProperty("activeIndividualsCount")
	public Long getActiveIndividualsCount() {
		return _activeIndividualsCount;
	}

	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	@JsonProperty("dateCreated")
	public Date getCreateDate() {
		if (_createDate == null) {
			return null;
		}

		return new Date(_createDate.getTime());
	}

	@JsonProperty("id")
	public String getId() {
		return _id;
	}

	@JsonProperty("individualsCount")
	public Long getIndividualsCount() {
		return _individualsCount;
	}

	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	@JsonProperty("dateModified")
	public Date getModifiedDate() {
		if (_modifiedDate == null) {
			return null;
		}

		return new Date(_modifiedDate.getTime());
	}

	@JsonProperty("accounts")
	public Set<ReportAccountDTO> getReportAccountDTOs() {
		return _reportAccountDTOs;
	}

	@JsonProperty("properties")
	public ReportAccountPropertiesDTO getReportAccountPropertiesDTO() {
		return _reportAccountPropertiesDTO;
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class ReportAccountPropertiesDTO {

		public ReportAccountPropertiesDTO(Map<String, Object> propertiesMap) {
			_propertiesMap = propertiesMap;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}

			if (!(obj instanceof ReportAccountPropertiesDTO)) {
				return false;
			}

			ReportAccountPropertiesDTO reportAccountPropertiesDTO =
				(ReportAccountPropertiesDTO)obj;

			if (Objects.equals(
					_propertiesMap,
					reportAccountPropertiesDTO._propertiesMap)) {

				return true;
			}

			return false;
		}

		@JsonAnyGetter
		public Map<String, Object> getProperties() {
			return _propertiesMap;
		}

		@Override
		public int hashCode() {
			return Objects.hash(_propertiesMap);
		}

		private final Map<String, Object> _propertiesMap;

	}

	private Long _activeIndividualsCount;
	private Date _createDate;
	private String _id;
	private Long _individualsCount;
	private Date _modifiedDate;
	private Set<ReportAccountDTO> _reportAccountDTOs;
	private ReportAccountPropertiesDTO _reportAccountPropertiesDTO;

}