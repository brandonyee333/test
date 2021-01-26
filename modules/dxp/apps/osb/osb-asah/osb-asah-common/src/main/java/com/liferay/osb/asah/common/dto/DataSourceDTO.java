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

package com.liferay.osb.asah.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.Date;
import java.util.List;

/**
 * @author Inácio Nery
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonRootName("data-sources")
public class DataSourceDTO {

	@JsonProperty("author")
	public AuthorDTO getAuthorDTO() {
		return _authorDTO;
	}

	@JsonProperty("channelId")
	public Long getChannelId() {
		return _channelId;
	}

	@JsonProperty("dateCreated")
	public Date getCreateDate() {
		if (_createDate == null) {
			return null;
		}

		return new Date(_createDate.getTime());
	}

	@JsonProperty("credentials")
	public CredentialDTO getCredentialDTO() {
		return _credentialDTO;
	}

	@JsonProperty("deletionDate")
	public Date getDeletionDate() {
		if (_deletionDate == null) {
			return null;
		}

		return new Date(_deletionDate.getTime());
	}

	@JsonProperty("faroBackendSecuritySignature")
	public String getFaroBackendSecuritySignature() {
		return _faroBackendSecuritySignature;
	}

	@JsonProperty("id")
	public Long getId() {
		return _id;
	}

	@JsonProperty("dateModified")
	public Date getModifiedDate() {
		if (_modifiedDate == null) {
			return null;
		}

		return new Date(_modifiedDate.getTime());
	}

	@JsonProperty("name")
	public String getName() {
		return _name;
	}

	@JsonProperty("provider")
	public ProviderDTO getProviderDTO() {
		return _providerDTO;
	}

	@JsonProperty("state")
	public String getState() {
		return _state;
	}

	@JsonProperty("status")
	public String getStatus() {
		return _status;
	}

	@JsonProperty("url")
	public String getURL() {
		return _url;
	}

	@JsonProperty("workspaceURL")
	public String getWorkspaceURL() {
		return _workspaceURL;
	}

	public void setAuthorDTO(AuthorDTO authorDTO) {
		_authorDTO = authorDTO;
	}

	public void setChannelId(Long channelId) {
		_channelId = channelId;
	}

	public void setCreateDate(Date createDate) {
		if (createDate != null) {
			_createDate = new Date(createDate.getTime());
		}
	}

	public void setCredentialDTO(CredentialDTO credentialDTO) {
		_credentialDTO = credentialDTO;
	}

	public void setDeletionDate(Date deletionDate) {
		if (deletionDate != null) {
			_deletionDate = new Date(deletionDate.getTime());
		}
	}

	public void setFaroBackendSecuritySignature(
		String faroBackendSecuritySignature) {

		_faroBackendSecuritySignature = faroBackendSecuritySignature;
	}

	public void setId(Long id) {
		_id = id;
	}

	public void setModifiedDate(Date modifiedDate) {
		if (modifiedDate != null) {
			_modifiedDate = new Date(modifiedDate.getTime());
		}
	}

	public void setName(String name) {
		_name = name;
	}

	public void setProviderDTO(ProviderDTO providerDTO) {
		_providerDTO = providerDTO;
	}

	public void setState(String state) {
		_state = state;
	}

	public void setStatus(String status) {
		_status = status;
	}

	public void setURL(String url) {
		_url = url;
	}

	public void setWorkspaceURL(String workspaceURL) {
		_workspaceURL = workspaceURL;
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class AuthorDTO {

		@JsonProperty("id")
		public Long getId() {
			return _id;
		}

		@JsonProperty("name")
		public String getName() {
			return _name;
		}

		public void setId(Long id) {
			_id = id;
		}

		public void setName(String name) {
			_name = name;
		}

		private Long _id;
		private String _name;

	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class CredentialDTO {

		@JsonProperty("login")
		public String getLogin() {
			return _login;
		}

		@JsonProperty("oAuthAccessSecret")
		public String getoAuthAccessSecret() {
			return _oAuthAccessSecret;
		}

		@JsonProperty("oAuthAccessToken")
		public String getoAuthAccessToken() {
			return _oAuthAccessToken;
		}

		@JsonProperty("oAuthClientId")
		public String getoAuthClientId() {
			return _oAuthClientId;
		}

		@JsonProperty("oAuthClientSecret")
		public String getoAuthClientSecret() {
			return _oAuthClientSecret;
		}

		@JsonProperty("oAuthConsumerKey")
		public String getoAuthConsumerKey() {
			return _oAuthConsumerKey;
		}

		@JsonProperty("oAuthConsumerSecret")
		public String getoAuthConsumerSecret() {
			return _oAuthConsumerSecret;
		}

		@JsonProperty("oAuthOwner")
		public OAuthOwnerDTO getoAuthOwnerDTO() {
			return _oAuthOwnerDTO;
		}

		@JsonProperty("oAuthRefreshToken")
		public String getoAuthRefreshToken() {
			return _oAuthRefreshToken;
		}

		@JsonProperty("password")
		public String getPassword() {
			return _password;
		}

		@JsonProperty("privateKey")
		public String getPrivateKey() {
			return _privateKey;
		}

		@JsonProperty("publicKey")
		public String getPublicKey() {
			return _publicKey;
		}

		@JsonProperty("type")
		public String getType() {
			return _type;
		}

		public void setLogin(String login) {
			_login = login;
		}

		public void setOAuthAccessSecret(String oAuthAccessSecret) {
			_oAuthAccessSecret = oAuthAccessSecret;
		}

		public void setOAuthAccessToken(String oAuthAccessToken) {
			_oAuthAccessToken = oAuthAccessToken;
		}

		public void setOAuthClientId(String oAuthClientId) {
			_oAuthClientId = oAuthClientId;
		}

		public void setOAuthClientSecret(String oAuthClientSecret) {
			_oAuthClientSecret = oAuthClientSecret;
		}

		public void setOAuthConsumerKey(String oAuthConsumerKey) {
			_oAuthConsumerKey = oAuthConsumerKey;
		}

		public void setOAuthConsumerSecret(String oAuthConsumerSecret) {
			_oAuthConsumerSecret = oAuthConsumerSecret;
		}

		public void setOAuthOwnerDTO(OAuthOwnerDTO oAuthOwnerDTO) {
			_oAuthOwnerDTO = oAuthOwnerDTO;
		}

		public void setOAuthRefreshToken(String oAuthRefreshToken) {
			_oAuthRefreshToken = oAuthRefreshToken;
		}

		public void setPassword(String password) {
			_password = password;
		}

		public void setPrivateKey(String privateKey) {
			_privateKey = privateKey;
		}

		public void setPublicKey(String publicKey) {
			_publicKey = publicKey;
		}

		public void setType(String type) {
			_type = type;
		}

		@JsonInclude(JsonInclude.Include.NON_NULL)
		public static class OAuthOwnerDTO {

			@JsonProperty("emailAddress")
			public String getEmailAddress() {
				return _emailAddress;
			}

			@JsonProperty("name")
			public String getName() {
				return _name;
			}

			public void setEmailAddress(String emailAddress) {
				_emailAddress = emailAddress;
			}

			public void setName(String name) {
				_name = name;
			}

			private String _emailAddress;
			private String _name;

		}

		private String _login;
		private String _oAuthAccessSecret;
		private String _oAuthAccessToken;
		private String _oAuthClientId;
		private String _oAuthClientSecret;
		private String _oAuthConsumerKey;
		private String _oAuthConsumerSecret;
		private OAuthOwnerDTO _oAuthOwnerDTO;
		private String _oAuthRefreshToken;
		private String _password;
		private String _privateKey;
		private String _publicKey;
		private String _type;

	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class ProviderDTO {

		@JsonProperty("accountsConfiguration")
		public AccountsConfigurationDTO getAccountsConfigurationDTO() {
			return _accountsConfigurationDTO;
		}

		@JsonProperty("analyticsConfiguration")
		public AnalyticsConfigurationDTO getAnalyticsConfigurationDTO() {
			return _analyticsConfigurationDTO;
		}

		@JsonProperty("contactsConfiguration")
		public ContactsConfigurationDTO getContactsConfigurationDTO() {
			return _contactsConfigurationDTO;
		}

		@JsonProperty("type")
		public String getType() {
			return _type;
		}

		public void setAccountsConfigurationDTO(
			AccountsConfigurationDTO accountsConfigurationDTO) {

			_accountsConfigurationDTO = accountsConfigurationDTO;
		}

		public void setAnalyticsConfigurationDTO(
			AnalyticsConfigurationDTO analyticsConfigurationDTO) {

			_analyticsConfigurationDTO = analyticsConfigurationDTO;
		}

		public void setContactsConfigurationDTO(
			ContactsConfigurationDTO contactsConfigurationDTO) {

			_contactsConfigurationDTO = contactsConfigurationDTO;
		}

		public void setType(String type) {
			_type = type;
		}

		@JsonInclude(JsonInclude.Include.NON_NULL)
		public static class AccountsConfigurationDTO {

			@JsonProperty("enableAllAccounts")
			public Boolean getEnableAllAccounts() {
				return _enableAllAccounts;
			}

			public void setEnableAllAccounts(Boolean enableAllAccounts) {
				_enableAllAccounts = enableAllAccounts;
			}

			private Boolean _enableAllAccounts;

		}

		@JsonInclude(JsonInclude.Include.NON_NULL)
		public static class AnalyticsConfigurationDTO {

			@JsonProperty("enableAllSites")
			public Boolean getEnableAllSites() {
				return _enableAllSites;
			}

			@JsonProperty("lastSyncTime")
			public Date getLastSyncTime() {
				if (_lastSyncTime == null) {
					return null;
				}

				return new Date(_lastSyncTime.getTime());
			}

			@JsonProperty("sites")
			public List<SiteDTO> getSiteDTOs() {
				return _siteDTOs;
			}

			public void setEnableAllSites(Boolean enableAllSites) {
				_enableAllSites = enableAllSites;
			}

			public void setLastSyncTime(Date lastSyncTime) {
				if (lastSyncTime != null) {
					_lastSyncTime = new Date(lastSyncTime.getTime());
				}
			}

			public void setSiteDTOs(List<SiteDTO> siteDTOs) {
				_siteDTOs = siteDTOs;
			}

			@JsonInclude(JsonInclude.Include.NON_NULL)
			public static class SiteDTO {

				@JsonProperty("enableAllChildren")
				public Boolean getEnableAllChildren() {
					return _enableAllChildren;
				}

				@JsonProperty("id")
				public Long getId() {
					return _id;
				}

				public void setEnableAllChildren(Boolean enableAllChildren) {
					_enableAllChildren = enableAllChildren;
				}

				public void setId(Long id) {
					_id = id;
				}

				private Boolean _enableAllChildren;
				private Long _id;

			}

			private Boolean _enableAllSites;
			private Date _lastSyncTime;
			private List<SiteDTO> _siteDTOs;

		}

		@JsonInclude(JsonInclude.Include.NON_NULL)
		public static class ContactsConfigurationDTO {

			@JsonProperty("enableAllContacts")
			public Boolean getEnableAllContacts() {
				return _enableAllContacts;
			}

			@JsonProperty("enableAllLeads")
			public Boolean getEnableAllLeads() {
				return _enableAllLeads;
			}

			@JsonProperty("lastSuccessfulAuditEventTime")
			public Date getLastSuccessfulAuditEventTime() {
				if (_lastSuccessfulAuditEventTime == null) {
					return null;
				}

				return new Date(_lastSuccessfulAuditEventTime.getTime());
			}

			@JsonProperty("lastSyncTime")
			public Date getLastSyncTime() {
				if (_lastSyncTime == null) {
					return null;
				}

				return new Date(_lastSyncTime.getTime());
			}

			@JsonProperty("organizations")
			public List<OrganizationDTO> getOrganizationDTOs() {
				return _organizationDTOs;
			}

			@JsonProperty("userGroups")
			public List<UserGroupDTO> getUserGroupDTOs() {
				return _userGroupDTOs;
			}

			public void setEnableAllContacts(Boolean enableAllContacts) {
				_enableAllContacts = enableAllContacts;
			}

			public void setEnableAllLeads(Boolean enableAllLeads) {
				_enableAllLeads = enableAllLeads;
			}

			public void setLastSuccessfulAuditEventTime(
				Date lastSuccessfulAuditEventTime) {

				if (lastSuccessfulAuditEventTime != null) {
					_lastSuccessfulAuditEventTime = new Date(
						lastSuccessfulAuditEventTime.getTime());
				}
			}

			public void setLastSyncTime(Date lastSyncTime) {
				if (lastSyncTime != null) {
					_lastSyncTime = new Date(lastSyncTime.getTime());
				}
			}

			public void setOrganizationDTOs(
				List<OrganizationDTO> organizationDTOs) {

				_organizationDTOs = organizationDTOs;
			}

			public void setUserGroupDTOs(List<UserGroupDTO> userGroupDTOs) {
				_userGroupDTOs = userGroupDTOs;
			}

			@JsonInclude(JsonInclude.Include.NON_NULL)
			public static class OrganizationDTO {

				@JsonProperty("enableAllChildren")
				public Boolean getEnableAllChildren() {
					return _enableAllChildren;
				}

				@JsonProperty("organizationIds")
				public List<Long> getOrganizationIds() {
					return _organizationIds;
				}

				public void setEnableAllChildren(Boolean enableAllChildren) {
					_enableAllChildren = enableAllChildren;
				}

				public void setOrganizationIds(List<Long> organizationIds) {
					_organizationIds = organizationIds;
				}

				private Boolean _enableAllChildren;
				private List<Long> _organizationIds;

			}

			@JsonInclude(JsonInclude.Include.NON_NULL)
			public static class UserGroupDTO {

				@JsonProperty("enableAllChildren")
				public Boolean getEnableAllChildren() {
					return _enableAllChildren;
				}

				@JsonProperty("userGroupIds")
				public List<Long> getUserGroupIds() {
					return _userGroupIds;
				}

				public void setEnableAllChildren(Boolean enableAllChildren) {
					_enableAllChildren = enableAllChildren;
				}

				public void setUserGroupIds(List<Long> userGroupIds) {
					_userGroupIds = userGroupIds;
				}

				private Boolean _enableAllChildren;
				private List<Long> _userGroupIds;

			}

			private Boolean _enableAllContacts;
			private Boolean _enableAllLeads;
			private Date _lastSuccessfulAuditEventTime;
			private Date _lastSyncTime;
			private List<OrganizationDTO> _organizationDTOs;
			private List<UserGroupDTO> _userGroupDTOs;

		}

		private AccountsConfigurationDTO _accountsConfigurationDTO;
		private AnalyticsConfigurationDTO _analyticsConfigurationDTO;
		private ContactsConfigurationDTO _contactsConfigurationDTO;
		private String _type;

	}

	private AuthorDTO _authorDTO;
	private Long _channelId;
	private Date _createDate;
	private CredentialDTO _credentialDTO;
	private Date _deletionDate;
	private String _faroBackendSecuritySignature;
	private Long _id;
	private Date _modifiedDate;
	private String _name;
	private ProviderDTO _providerDTO;
	private String _state;
	private String _status;
	private String _url;
	private String _workspaceURL;

}