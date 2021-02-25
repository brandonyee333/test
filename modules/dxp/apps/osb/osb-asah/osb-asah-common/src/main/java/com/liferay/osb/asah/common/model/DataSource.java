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

package com.liferay.osb.asah.common.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author Inácio Nery
 */
@Table
public class DataSource implements Persistable<Long> {

	public DataSource() {
	}

	public DataSource(String name) {
		_name = name;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DataSource)) {
			return false;
		}

		DataSource dataSource = (DataSource)obj;

		if (Objects.equals(
				_analyticslastSyncDate, dataSource._analyticslastSyncDate) &&
			Objects.equals(_authorId, dataSource._authorId) &&
			Objects.equals(_authorName, dataSource._authorName) &&
			Objects.equals(_channelId, dataSource._channelId) &&
			Objects.equals(
				_contactsLastSuccessfulAuditEventDate,
				dataSource._contactsLastSuccessfulAuditEventDate) &&
			Objects.equals(
				_contactsLastSyncDate, dataSource._contactsLastSyncDate) &&
			Objects.equals(_contactsSelected, dataSource._contactsSelected) &&
			Objects.equals(_createDate, dataSource._createDate) &&
			Objects.equals(_credentialType, dataSource._credentialType) &&
			Objects.equals(
				_dataSourceOrganizations,
				dataSource._dataSourceOrganizations) &&
			Objects.equals(_dataSourceSites, dataSource._dataSourceSites) &&
			Objects.equals(
				_dataSourceUserGroups, dataSource._dataSourceUserGroups) &&
			Objects.equals(_deletionDate, dataSource._deletionDate) &&
			Objects.equals(_enableAllAccounts, dataSource._enableAllAccounts) &&
			Objects.equals(_enableAllContacts, dataSource._enableAllContacts) &&
			Objects.equals(_enableAllLeads, dataSource._enableAllLeads) &&
			Objects.equals(_enableAllSites, dataSource._enableAllSites) &&
			Objects.equals(
				_faroBackendSecuritySignature,
				dataSource._faroBackendSecuritySignature) &&
			Objects.equals(_id, dataSource._id) &&
			Objects.equals(_login, dataSource._login) &&
			Objects.equals(_modifiedDate, dataSource._modifiedDate) &&
			Objects.equals(_name, dataSource._name) &&
			Objects.equals(_oAuthAccessSecret, dataSource._oAuthAccessSecret) &&
			Objects.equals(_oAuthAccessToken, dataSource._oAuthAccessToken) &&
			Objects.equals(_oAuthClientId, dataSource._oAuthClientId) &&
			Objects.equals(_oAuthClientSecret, dataSource._oAuthClientSecret) &&
			Objects.equals(_oAuthConsumerKey, dataSource._oAuthConsumerKey) &&
			Objects.equals(
				_oAuthConsumerSecret, dataSource._oAuthConsumerSecret) &&
			Objects.equals(
				_oAuthOwnerEmailAddress, dataSource._oAuthOwnerEmailAddress) &&
			Objects.equals(_oAuthOwnerName, dataSource._oAuthOwnerName) &&
			Objects.equals(_oAuthRefreshToken, dataSource._oAuthRefreshToken) &&
			Objects.equals(_password, dataSource._password) &&
			Objects.equals(_privateKey, dataSource._privateKey) &&
			Objects.equals(_providerType, dataSource._providerType) &&
			Objects.equals(_publicKey, dataSource._publicKey) &&
			Objects.equals(_sitesSelected, dataSource._sitesSelected) &&
			Objects.equals(_state, dataSource._state) &&
			Objects.equals(_status, dataSource._status) &&
			Objects.equals(_url, dataSource._url) &&
			Objects.equals(_workspaceURL, dataSource._workspaceURL)) {

			return true;
		}

		return false;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Date getAnalyticsLastSyncDate() {
		if (_analyticslastSyncDate == null) {
			return null;
		}

		return new Date(_analyticslastSyncDate.getTime());
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Long getAuthorId() {
		return _authorId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getAuthorName() {
		return _authorName;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Long getChannelId() {
		return _channelId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Date getContactsLastSuccessfulAuditEventDate() {
		if (_contactsLastSuccessfulAuditEventDate == null) {
			return null;
		}

		return new Date(_contactsLastSuccessfulAuditEventDate.getTime());
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Date getContactsLastSyncDate() {
		if (_contactsLastSyncDate == null) {
			return null;
		}

		return new Date(_contactsLastSyncDate.getTime());
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Boolean getContactsSelected() {
		if (_contactsSelected == null) {
			return false;
		}

		return _contactsSelected;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Date getCreateDate() {
		if (_createDate == null) {
			return null;
		}

		return new Date(_createDate.getTime());
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getCredentialType() {
		return _credentialType;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@MappedCollection(idColumn = "datasourceid")
	public Set<DataSourceOrganization> getDataSourceOrganizations() {
		return _dataSourceOrganizations;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@MappedCollection(idColumn = "datasourceid")
	public Set<DataSourceSite> getDataSourceSites() {
		return _dataSourceSites;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@MappedCollection(idColumn = "datasourceid")
	public Set<DataSourceUserGroup> getDataSourceUserGroups() {
		return _dataSourceUserGroups;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Date getDeletionDate() {
		if (_deletionDate == null) {
			return null;
		}

		return new Date(_deletionDate.getTime());
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Boolean getEnableAllAccounts() {
		return _enableAllAccounts;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Boolean getEnableAllContacts() {
		return _enableAllContacts;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Boolean getEnableAllLeads() {
		return _enableAllLeads;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Boolean getEnableAllSites() {
		return _enableAllSites;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getFaroBackendSecuritySignature() {
		return _faroBackendSecuritySignature;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@Id
	@Override
	public Long getId() {
		return _id;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getLogin() {
		return _login;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Date getModifiedDate() {
		if (_modifiedDate == null) {
			return null;
		}

		return new Date(_modifiedDate.getTime());
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getName() {
		return _name;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getOAuthAccessSecret() {
		return _oAuthAccessSecret;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getOAuthAccessToken() {
		return _oAuthAccessToken;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getOAuthClientId() {
		return _oAuthClientId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getOAuthClientSecret() {
		return _oAuthClientSecret;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getOAuthConsumerKey() {
		return _oAuthConsumerKey;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getOAuthConsumerSecret() {
		return _oAuthConsumerSecret;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getOAuthOwnerEmailAddress() {
		return _oAuthOwnerEmailAddress;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getOAuthOwnerName() {
		return _oAuthOwnerName;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getOAuthRefreshToken() {
		return _oAuthRefreshToken;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getPassword() {
		return _password;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getPrivateKey() {
		return _privateKey;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getProviderType() {
		return _providerType;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getPublicKey() {
		return _publicKey;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Boolean getSitesSelected() {
		if (_sitesSelected == null) {
			return false;
		}

		return _sitesSelected;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getState() {
		return _state;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getStatus() {
		return _status;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getURL() {
		return _url;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getWorkspaceURL() {
		return _workspaceURL;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_analyticslastSyncDate, _authorId, _authorName, _channelId,
			_contactsLastSuccessfulAuditEventDate, _contactsLastSyncDate,
			_contactsSelected, _createDate, _credentialType,
			_dataSourceOrganizations, _dataSourceSites, _dataSourceUserGroups,
			_deletionDate, _enableAllAccounts, _enableAllContacts,
			_enableAllLeads, _enableAllSites, _faroBackendSecuritySignature,
			_id, _login, _modifiedDate, _name, _oAuthAccessSecret,
			_oAuthAccessToken, _oAuthClientId, _oAuthClientSecret,
			_oAuthConsumerKey, _oAuthConsumerSecret, _oAuthOwnerEmailAddress,
			_oAuthOwnerName, _oAuthRefreshToken, _password, _privateKey,
			_providerType, _publicKey, _sitesSelected, _state, _status, _url,
			_workspaceURL);
	}

	@Override
	public boolean isNew() {
		if ((_id == null) || ((_isNew != null) && _isNew)) {
			return true;
		}

		return false;
	}

	public void setAnalyticsLastSyncDate(Date analyticslastSyncDate) {
		if (analyticslastSyncDate != null) {
			_analyticslastSyncDate = new Date(analyticslastSyncDate.getTime());
		}
	}

	public void setAuthorId(Long authorId) {
		_authorId = authorId;
	}

	public void setAuthorName(String authorName) {
		_authorName = authorName;
	}

	public void setChannelId(Long channelId) {
		_channelId = channelId;
	}

	public void setContactsLastSuccessfulAuditEventDate(
		Date contactsLastSuccessfulAuditEventDate) {

		if (contactsLastSuccessfulAuditEventDate != null) {
			_contactsLastSuccessfulAuditEventDate = new Date(
				contactsLastSuccessfulAuditEventDate.getTime());
		}
	}

	public void setContactsLastSyncDate(Date contactsLastSyncDate) {
		if (contactsLastSyncDate != null) {
			_contactsLastSyncDate = new Date(contactsLastSyncDate.getTime());
		}
	}

	public void setContactsSelected(Boolean contactsSelected) {
		_contactsSelected = contactsSelected;
	}

	public void setCreateDate(Date createDate) {
		if (createDate != null) {
			_createDate = new Date(createDate.getTime());
		}
	}

	public void setCredentialType(String credentialType) {
		_credentialType = credentialType;
	}

	public void setDataSourceOrganizations(
		Set<DataSourceOrganization> dataSourceOrganizations) {

		_dataSourceOrganizations = dataSourceOrganizations;
	}

	public void setDataSourceSites(Set<DataSourceSite> dataSourceSites) {
		_dataSourceSites = dataSourceSites;
	}

	public void setDataSourceUserGroups(
		Set<DataSourceUserGroup> dataSourceUserGroups) {

		_dataSourceUserGroups = dataSourceUserGroups;
	}

	public void setDeletionDate(Date deletionDate) {
		if (deletionDate != null) {
			_deletionDate = new Date(deletionDate.getTime());
		}
	}

	public void setEnableAllAccounts(Boolean enableAllAccounts) {
		_enableAllAccounts = enableAllAccounts;
	}

	public void setEnableAllContacts(Boolean enableAllContacts) {
		_enableAllContacts = enableAllContacts;
	}

	public void setEnableAllLeads(Boolean enableAllLeads) {
		_enableAllLeads = enableAllLeads;
	}

	public void setEnableAllSites(Boolean enableAllSites) {
		_enableAllSites = enableAllSites;
	}

	public void setFaroBackendSecuritySignature(
		String faroBackendSecuritySignature) {

		_faroBackendSecuritySignature = faroBackendSecuritySignature;
	}

	public void setId(Long id) {
		_id = id;
	}

	public void setIsNew(Boolean isNew) {
		_isNew = isNew;
	}

	public void setLogin(String login) {
		_login = login;
	}

	public void setModifiedDate(Date modifiedDate) {
		if (modifiedDate != null) {
			_modifiedDate = new Date(modifiedDate.getTime());
		}
	}

	public void setName(String name) {
		_name = name;
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

	public void setOAuthOwnerEmailAddress(
		String oAuthOwnerOAuthOwnerEmailAddress) {

		_oAuthOwnerEmailAddress = oAuthOwnerOAuthOwnerEmailAddress;
	}

	public void setOAuthOwnerName(String oAuthOwnerOAuthOwnerName) {
		_oAuthOwnerName = oAuthOwnerOAuthOwnerName;
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

	public void setProviderType(String providerType) {
		_providerType = providerType;
	}

	public void setPublicKey(String publicKey) {
		_publicKey = publicKey;
	}

	public void setSitesSelected(Boolean sitesSelected) {
		_sitesSelected = sitesSelected;
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

	@Transient
	private Date _analyticslastSyncDate;

	@Transient
	private Long _authorId;

	@Transient
	private String _authorName;

	@Transient
	private Long _channelId;

	@Transient
	private Date _contactsLastSuccessfulAuditEventDate;

	@Transient
	private Date _contactsLastSyncDate;

	@Transient
	private Boolean _contactsSelected;

	@Transient
	private Date _createDate;

	@Transient
	private String _credentialType;

	@Transient
	private Set<DataSourceOrganization> _dataSourceOrganizations =
		new HashSet<>();

	@Transient
	private Set<DataSourceSite> _dataSourceSites = new HashSet<>();

	@Transient
	private Set<DataSourceUserGroup> _dataSourceUserGroups = new HashSet<>();

	@Transient
	private Date _deletionDate;

	@Transient
	private Boolean _enableAllAccounts;

	@Transient
	private Boolean _enableAllContacts;

	@Transient
	private Boolean _enableAllLeads;

	@Transient
	private Boolean _enableAllSites;

	@Transient
	private String _faroBackendSecuritySignature;

	@Transient
	private Long _id;

	@Transient
	private Boolean _isNew;

	@Transient
	private String _login;

	@Transient
	private Date _modifiedDate;

	@Transient
	private String _name;

	@Transient
	private String _oAuthAccessSecret;

	@Transient
	private String _oAuthAccessToken;

	@Transient
	private String _oAuthClientId;

	@Transient
	private String _oAuthClientSecret;

	@Transient
	private String _oAuthConsumerKey;

	@Transient
	private String _oAuthConsumerSecret;

	@Transient
	private String _oAuthOwnerEmailAddress;

	@Transient
	private String _oAuthOwnerName;

	@Transient
	private String _oAuthRefreshToken;

	@Transient
	private String _password;

	@Transient
	private String _privateKey;

	@Transient
	private String _providerType;

	@Transient
	private String _publicKey;

	@Transient
	private Boolean _sitesSelected;

	@Transient
	private String _state;

	@Transient
	private String _status;

	@Transient
	private String _url;

	@Transient
	private String _workspaceURL;

}