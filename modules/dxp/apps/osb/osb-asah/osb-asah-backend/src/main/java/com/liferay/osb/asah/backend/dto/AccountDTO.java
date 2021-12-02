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

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.entity.Account;
import com.liferay.osb.asah.common.entity.Field;
import com.liferay.osb.asah.common.util.ListUtil;
import com.liferay.osb.asah.common.util.SetUtil;
import com.liferay.osb.asah.common.util.StringUtil;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author Rachael Koestartyo
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonRootName("accounts")
public class AccountDTO {

	public AccountDTO() {
	}

	public AccountDTO(Account account) {
		_accountFieldDTO = new AccountFieldDTO(account.getFields());
		_accountPK = account.getAccountPK();
		_activeIndividualsCount = account.getActiveIndividualsCount();
		_activitiesCount = account.getActivitiesCount();

		List<ActivitiesCountDTO> activitiesCountDTOs = ListUtil.map(
			account.getActivitiesCounts(), ActivitiesCountDTO::new);

		if (!activitiesCountDTOs.isEmpty()) {
			_activitiesCountDTOs = activitiesCountDTOs;
		}

		_createDate = account.getCreateDate();
		_dataSourceId = StringUtil.get(account.getDataSourceId(), null);
		_id = StringUtil.get(account.getId(), null);
		_individualsCount = account.getIndividualsCount();

		List<IndividualCountDTO> individualCountDTOs = ListUtil.map(
			account.getIndividualsCounts(), IndividualCountDTO::new);

		if (!individualCountDTOs.isEmpty()) {
			_individualCountDTOs = individualCountDTOs;
		}

		_modifiedDate = account.getModifiedDate();
	}

	public AccountDTO(List<Account> accounts) {
		_accountDTOs = SetUtil.map(accounts, AccountDTO::new);
	}

	@JsonProperty("accounts")
	public Set<AccountDTO> getAccountDTOs() {
		return _accountDTOs;
	}

	@JsonProperty("organization")
	public AccountFieldDTO getAccountFieldDTO() {
		return _accountFieldDTO;
	}

	@JsonProperty("accountPK")
	public String getAccountPK() {
		return _accountPK;
	}

	@JsonAlias("activeIndividualsCount")
	@JsonProperty("activeIndividualCount")
	public Long getActiveIndividualsCount() {
		return _activeIndividualsCount;
	}

	@JsonProperty("activitiesCount")
	public Long getActivitiesCount() {
		return _activitiesCount;
	}

	@JsonProperty("activitiesCounts")
	public List<ActivitiesCountDTO> getActivitiesCountDTOs() {
		return _activitiesCountDTOs;
	}

	@JsonAlias("createDate")
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

	@JsonProperty("dataSourceId")
	public String getDataSourceId() {
		return _dataSourceId;
	}

	@JsonProperty("id")
	public String getId() {
		return _id;
	}

	@JsonProperty("individualCounts")
	public List<IndividualCountDTO> getIndividualCountDTOs() {
		return _individualCountDTOs;
	}

	@JsonAlias("individualsCount")
	@JsonProperty("individualCount")
	public Long getIndividualsCount() {
		return _individualsCount;
	}

	@JsonAlias("modifiedDate")
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

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class AccountFieldDTO {

		public AccountFieldDTO(Set<Field> fields) {
			for (Field field : fields) {
				_fieldMap.put(
					field.getName(),
					Collections.singletonList(new FieldDTO(field)));
			}
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}

			if (!(obj instanceof AccountFieldDTO)) {
				return false;
			}

			AccountFieldDTO accountFieldDTO = (AccountFieldDTO)obj;

			if (Objects.equals(_fieldMap, accountFieldDTO._fieldMap)) {
				return true;
			}

			return false;
		}

		@JsonAnyGetter
		public Map<String, Object> getField() {
			return _fieldMap;
		}

		@Override
		public int hashCode() {
			return Objects.hash(_fieldMap);
		}

		public void setFieldMap(Map<String, Object> fieldMap) {
			_fieldMap = fieldMap;
		}

		private Map<String, Object> _fieldMap = new HashMap<>();

	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class ActivitiesCountDTO {

		public ActivitiesCountDTO(
			Account.AccountActivityCount accountActivityCount) {

			_activitiesCount = accountActivityCount.getActivitiesCount();
			_channelId = String.valueOf(accountActivityCount.getChannelId());
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}

			if (!(obj instanceof ActivitiesCountDTO)) {
				return false;
			}

			ActivitiesCountDTO activitiesCountDTO = (ActivitiesCountDTO)obj;

			if (Objects.equals(
					_activitiesCount, activitiesCountDTO._activitiesCount) &&
				Objects.equals(_channelId, activitiesCountDTO._channelId)) {

				return true;
			}

			return false;
		}

		@JsonProperty("activitiesCount")
		public Long getActivitiesCount() {
			return _activitiesCount;
		}

		@JsonProperty("channelId")
		public String getChannelId() {
			return _channelId;
		}

		@Override
		public int hashCode() {
			return Objects.hash(_activitiesCount, _channelId);
		}

		public void setActivitiesCount(Long activitiesCount) {
			_activitiesCount = activitiesCount;
		}

		public void setChannelId(String channelId) {
			_channelId = channelId;
		}

		private Long _activitiesCount;
		private String _channelId;

	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class IndividualCountDTO {

		public IndividualCountDTO(
			Account.AccountIndividualCount accountIndividualCount) {

			_channelId = String.valueOf(accountIndividualCount.getChannelId());
			_individualsCount = accountIndividualCount.getIndividualsCount();
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}

			if (!(obj instanceof IndividualCountDTO)) {
				return false;
			}

			IndividualCountDTO individualCountDTO = (IndividualCountDTO)obj;

			if (Objects.equals(_channelId, individualCountDTO._channelId) &&
				Objects.equals(
					_individualsCount, individualCountDTO._individualsCount)) {

				return true;
			}

			return false;
		}

		@JsonProperty("channelId")
		public String getChannelId() {
			return _channelId;
		}

		@JsonAlias("individualsCount")
		@JsonProperty("individualCount")
		public Long getIndividualsCount() {
			return _individualsCount;
		}

		@Override
		public int hashCode() {
			return Objects.hash(_channelId, _individualsCount);
		}

		public void setChannelId(String channelId) {
			_channelId = channelId;
		}

		public void setIndividualsCount(Long individualsCount) {
			_individualsCount = individualsCount;
		}

		private String _channelId;
		private Long _individualsCount;

	}

	private Set<AccountDTO> _accountDTOs;
	private AccountFieldDTO _accountFieldDTO;
	private String _accountPK;
	private Long _activeIndividualsCount;
	private Long _activitiesCount;
	private List<ActivitiesCountDTO> _activitiesCountDTOs;
	private Date _createDate;
	private String _dataSourceId;
	private String _id;
	private List<IndividualCountDTO> _individualCountDTOs;
	private Long _individualsCount;
	private Date _modifiedDate;

}