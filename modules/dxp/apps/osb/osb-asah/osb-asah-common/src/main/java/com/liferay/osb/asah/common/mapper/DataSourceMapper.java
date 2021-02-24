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

package com.liferay.osb.asah.common.mapper;

import com.liferay.osb.asah.common.dto.DataSourceDTO;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.DataSource;
import com.liferay.osb.asah.common.model.DataSourceOrganization;
import com.liferay.osb.asah.common.model.DataSourceSite;
import com.liferay.osb.asah.common.model.DataSourceUserGroup;

import java.util.HashSet;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.stereotype.Component;

/**
 * @author Inácio Nery
 */
@Component
public class DataSourceMapper extends Mapper<DataSource, DataSourceDTO> {

	@Override
	protected DataSourceDTO toDTO(DataSource dataSource) {
		return new DataSourceDTO(dataSource);
	}

	@Override
	protected DataSource toModel(JSONObject jsonObject) {
		DataSource dataSource = new DataSource();

		if (jsonObject.has("author") && !jsonObject.isNull("author")) {
			JSONObject authorJSONObject = jsonObject.getJSONObject("author");

			if (authorJSONObject.has("id") && !authorJSONObject.isNull("id")) {
				dataSource.setAuthorId(authorJSONObject.getLong("id"));
			}

			if (authorJSONObject.has("name") &&
				!authorJSONObject.isNull("name")) {

				dataSource.setAuthorName(authorJSONObject.getString("name"));
			}
		}

		if (jsonObject.has("channelId") && !jsonObject.isNull("channelId")) {
			dataSource.setChannelId(jsonObject.getLong("channelId"));
		}

		if (jsonObject.has("createDate") && !jsonObject.isNull("createDate")) {
			dataSource.setCreateDate(toUTCDate(jsonObject.get("createDate")));
		}
		else if (jsonObject.has("dateCreated") &&
				 !jsonObject.isNull("dateCreated")) {

			dataSource.setCreateDate(toUTCDate(jsonObject.get("dateCreated")));
		}

		if (jsonObject.has("credentials") &&
			!jsonObject.isNull("credentials")) {

			JSONObject credentialsJSONObject = jsonObject.getJSONObject(
				"credentials");

			if (credentialsJSONObject.has("login") &&
				!credentialsJSONObject.isNull("login")) {

				dataSource.setLogin(credentialsJSONObject.getString("login"));
			}

			if (credentialsJSONObject.has("oAuthAccessSecret") &&
				!credentialsJSONObject.isNull("oAuthAccessSecret")) {

				dataSource.setOAuthAccessSecret(
					credentialsJSONObject.getString("oAuthAccessSecret"));
			}

			if (credentialsJSONObject.has("oAuthAccessToken") &&
				!credentialsJSONObject.isNull("oAuthAccessToken")) {

				dataSource.setOAuthAccessToken(
					credentialsJSONObject.getString("oAuthAccessToken"));
			}

			if (credentialsJSONObject.has("oAuthClientId") &&
				!credentialsJSONObject.isNull("oAuthClientId")) {

				dataSource.setOAuthClientId(
					credentialsJSONObject.getString("oAuthClientId"));
			}

			if (credentialsJSONObject.has("oAuthClientSecret") &&
				!credentialsJSONObject.isNull("oAuthClientSecret")) {

				dataSource.setOAuthClientSecret(
					credentialsJSONObject.getString("oAuthClientSecret"));
			}

			if (credentialsJSONObject.has("oAuthConsumerKey") &&
				!credentialsJSONObject.isNull("oAuthConsumerKey")) {

				dataSource.setOAuthConsumerKey(
					credentialsJSONObject.getString("oAuthConsumerKey"));
			}

			if (credentialsJSONObject.has("oAuthConsumerSecret") &&
				!credentialsJSONObject.isNull("oAuthConsumerSecret")) {

				dataSource.setOAuthConsumerSecret(
					credentialsJSONObject.getString("oAuthConsumerSecret"));
			}

			if (credentialsJSONObject.has("oAuthOwner") &&
				!credentialsJSONObject.isNull("oAuthOwner")) {

				JSONObject oAuthOwnerJSONObject =
					credentialsJSONObject.getJSONObject("oAuthOwner");

				if (oAuthOwnerJSONObject.has("emailAddress") &&
					!oAuthOwnerJSONObject.isNull("emailAddress")) {

					dataSource.setOAuthOwnerEmailAddress(
						oAuthOwnerJSONObject.getString("emailAddress"));
				}

				if (oAuthOwnerJSONObject.has("name") &&
					!oAuthOwnerJSONObject.isNull("name")) {

					dataSource.setOAuthOwnerName(
						oAuthOwnerJSONObject.getString("name"));
				}
			}

			if (credentialsJSONObject.has("oAuthRefreshToken") &&
				!credentialsJSONObject.isNull("oAuthRefreshToken")) {

				dataSource.setOAuthRefreshToken(
					credentialsJSONObject.getString("oAuthRefreshToken"));
			}

			if (credentialsJSONObject.has("password") &&
				!credentialsJSONObject.isNull("password")) {

				dataSource.setPassword(
					credentialsJSONObject.getString("password"));
			}

			if (credentialsJSONObject.has("privateKey") &&
				!credentialsJSONObject.isNull("privateKey")) {

				dataSource.setPrivateKey(
					credentialsJSONObject.getString("privateKey"));
			}

			if (credentialsJSONObject.has("publicKey") &&
				!credentialsJSONObject.isNull("publicKey")) {

				dataSource.setPublicKey(
					credentialsJSONObject.getString("publicKey"));
			}

			if (credentialsJSONObject.has("type") &&
				!credentialsJSONObject.isNull("type")) {

				dataSource.setCredentialType(
					credentialsJSONObject.getString("type"));
			}
		}

		if (jsonObject.has("deletionDate") &&
			!jsonObject.isNull("deletionDate")) {

			dataSource.setDeletionDate(
				toUTCDate(jsonObject.get("deletionDate")));
		}

		if (jsonObject.has("details") && !jsonObject.isNull("details")) {
			JSONObject detailsJSONObject = jsonObject.getJSONObject("details");

			if (detailsJSONObject.has("contactsSelected") &&
				!detailsJSONObject.isNull("contactsSelected")) {

				dataSource.setContactsSelected(
					detailsJSONObject.getBoolean("contactsSelected"));
			}

			if (detailsJSONObject.has("sitesSelected") &&
				!detailsJSONObject.isNull("sitesSelected")) {

				dataSource.setSitesSelected(
					detailsJSONObject.getBoolean("sitesSelected"));
			}
		}

		if (jsonObject.has("faroBackendSecuritySignature") &&
			!jsonObject.isNull("faroBackendSecuritySignature")) {

			dataSource.setFaroBackendSecuritySignature(
				jsonObject.getString("faroBackendSecuritySignature"));
		}

		if (jsonObject.has("id") && !jsonObject.isNull("id")) {
			dataSource.setId(jsonObject.getLong("id"));
		}

		if (jsonObject.has("modifiedDate") &&
			!jsonObject.isNull("modifiedDate")) {

			dataSource.setModifiedDate(
				toUTCDate(jsonObject.get("modifiedDate")));
		}
		else if (jsonObject.has("dateModified") &&
				 !jsonObject.isNull("dateModified")) {

			dataSource.setModifiedDate(
				toUTCDate(jsonObject.get("dateModified")));
		}

		if (jsonObject.has("name") && !jsonObject.isNull("name")) {
			dataSource.setName(jsonObject.getString("name"));
		}

		if (jsonObject.has("provider") && !jsonObject.isNull("provider")) {
			JSONObject providerJSONObject = jsonObject.getJSONObject(
				"provider");

			if (providerJSONObject.has("accountsConfiguration") &&
				!providerJSONObject.isNull("accountsConfiguration")) {

				JSONObject accountsConfigurationJSONObject =
					providerJSONObject.getJSONObject("accountsConfiguration");

				if (accountsConfigurationJSONObject.has("enableAllAccounts") &&
					!accountsConfigurationJSONObject.isNull(
						"enableAllAccounts")) {

					dataSource.setEnableAllAccounts(
						accountsConfigurationJSONObject.getBoolean(
							"enableAllAccounts"));
				}
			}

			if (providerJSONObject.has("analyticsConfiguration") &&
				!providerJSONObject.isNull("analyticsConfiguration")) {

				JSONObject analyticsConfigurationJSONObject =
					providerJSONObject.getJSONObject("analyticsConfiguration");

				if (analyticsConfigurationJSONObject.has("enableAllSites") &&
					!analyticsConfigurationJSONObject.isNull(
						"enableAllSites")) {

					dataSource.setEnableAllSites(
						analyticsConfigurationJSONObject.getBoolean(
							"enableAllSites"));
				}

				if (analyticsConfigurationJSONObject.has("lastSyncTime") &&
					!analyticsConfigurationJSONObject.isNull("lastSyncTime")) {

					dataSource.setAnalyticsLastSyncDate(
						toUTCDate(
							analyticsConfigurationJSONObject.get(
								"lastSyncTime")));
				}

				if (analyticsConfigurationJSONObject.has("sites") &&
					!analyticsConfigurationJSONObject.isNull("sites")) {

					JSONArray sitesJSONArray =
						analyticsConfigurationJSONObject.getJSONArray("sites");
					Set<DataSourceSite> dataSourceSites = new HashSet<>();

					for (int j = 0; j < sitesJSONArray.length(); j++) {
						JSONObject siteJSONObject =
							sitesJSONArray.getJSONObject(j);

						DataSourceSite dataSourceSite = new DataSourceSite();

						if (siteJSONObject.has("enableAllChildren") &&
							!siteJSONObject.isNull("enableAllChildren")) {

							dataSourceSite.setEnableAllChildren(
								siteJSONObject.getBoolean("enableAllChildren"));
						}

						if (siteJSONObject.has("id") &&
							!siteJSONObject.isNull("id")) {

							dataSourceSite.setSiteId(
								siteJSONObject.getLong("id"));
						}

						dataSourceSites.add(dataSourceSite);
					}

					dataSource.setDataSourceSites(dataSourceSites);
				}
			}

			if (providerJSONObject.has("contactsConfiguration") &&
				!providerJSONObject.isNull("contactsConfiguration")) {

				JSONObject contactsConfigurationJSONObject =
					providerJSONObject.getJSONObject("contactsConfiguration");

				if (contactsConfigurationJSONObject.has("enableAllContacts") &&
					!contactsConfigurationJSONObject.isNull(
						"enableAllContacts")) {

					dataSource.setEnableAllContacts(
						contactsConfigurationJSONObject.getBoolean(
							"enableAllContacts"));
				}

				if (contactsConfigurationJSONObject.has("enableAllLeads") &&
					!contactsConfigurationJSONObject.isNull("enableAllLeads")) {

					dataSource.setEnableAllLeads(
						contactsConfigurationJSONObject.getBoolean(
							"enableAllLeads"));
				}

				if (contactsConfigurationJSONObject.has(
						"lastSuccessfulAuditEventTime") &&
					!contactsConfigurationJSONObject.isNull(
						"lastSuccessfulAuditEventTime")) {

					dataSource.setContactsLastSuccessfulAuditEventDate(
						toUTCDate(
							contactsConfigurationJSONObject.get(
								"lastSuccessfulAuditEventTime")));
				}

				if (contactsConfigurationJSONObject.has("lastSyncTime") &&
					!contactsConfigurationJSONObject.isNull("lastSyncTime")) {

					dataSource.setContactsLastSyncDate(
						toUTCDate(
							contactsConfigurationJSONObject.get(
								"lastSyncTime")));
				}

				if (contactsConfigurationJSONObject.has("organizations") &&
					!contactsConfigurationJSONObject.isNull("organizations")) {

					JSONArray organizationsJSONArray =
						contactsConfigurationJSONObject.getJSONArray(
							"organizations");
					Set<DataSourceOrganization> dataSourceOrganizations =
						new HashSet<>();

					for (int j = 0; j < organizationsJSONArray.length(); j++) {
						JSONObject organizationJSONObject =
							organizationsJSONArray.getJSONObject(j);

						DataSourceOrganization dataSourceOrganization =
							new DataSourceOrganization();

						if (organizationJSONObject.has("enableAllChildren") &&
							!organizationJSONObject.isNull(
								"enableAllChildren")) {

							dataSourceOrganization.setEnableAllChildren(
								organizationJSONObject.getBoolean(
									"enableAllChildren"));
						}

						if (organizationJSONObject.has("id") &&
							!organizationJSONObject.isNull("id")) {

							dataSourceOrganization.setOrganizationId(
								organizationJSONObject.getLong("id"));
						}

						if (organizationJSONObject.has("organizationIds") &&
							!organizationJSONObject.isNull("organizationIds")) {

							dataSourceOrganization.setOrganizationIds(
								JSONUtil.toLongSet(
									organizationJSONObject.getJSONArray(
										"organizationIds")));
						}

						dataSourceOrganizations.add(dataSourceOrganization);
					}

					dataSource.setDataSourceOrganizations(
						dataSourceOrganizations);
				}

				if (contactsConfigurationJSONObject.has("userGroups") &&
					!contactsConfigurationJSONObject.isNull("userGroups")) {

					JSONArray userGroupsJSONArray =
						contactsConfigurationJSONObject.getJSONArray(
							"userGroups");
					Set<DataSourceUserGroup> dataSourceUserGroups =
						new HashSet<>();

					for (int j = 0; j < userGroupsJSONArray.length(); j++) {
						JSONObject userGroupJSONObject =
							userGroupsJSONArray.getJSONObject(j);

						DataSourceUserGroup dataSourceUserGroup =
							new DataSourceUserGroup();

						if (userGroupJSONObject.has("enableAllChildren") &&
							!userGroupJSONObject.isNull("enableAllChildren")) {

							dataSourceUserGroup.setEnableAllChildren(
								userGroupJSONObject.getBoolean(
									"enableAllChildren"));
						}

						if (userGroupJSONObject.has("id") &&
							!userGroupJSONObject.isNull("id")) {

							dataSourceUserGroup.setUserGroupId(
								userGroupJSONObject.getLong("id"));
						}

						if (userGroupJSONObject.has("userGroupIds") &&
							!userGroupJSONObject.isNull("userGroupIds")) {

							dataSourceUserGroup.setUserGroupIds(
								JSONUtil.toLongSet(
									userGroupJSONObject.getJSONArray(
										"userGroupIds")));
						}

						dataSourceUserGroups.add(dataSourceUserGroup);
					}

					dataSource.setDataSourceUserGroups(dataSourceUserGroups);
				}
			}

			if (providerJSONObject.has("type") &&
				!providerJSONObject.isNull("type")) {

				dataSource.setProviderType(
					providerJSONObject.getString("type"));
			}
		}

		if (jsonObject.has("state") && !jsonObject.isNull("state")) {
			dataSource.setState(jsonObject.getString("state"));
		}

		if (jsonObject.has("status") && !jsonObject.isNull("status")) {
			dataSource.setStatus(jsonObject.getString("status"));
		}

		if (jsonObject.has("url") && !jsonObject.isNull("url")) {
			dataSource.setURL(jsonObject.getString("url"));
		}

		if (jsonObject.has("workspaceURL") &&
			!jsonObject.isNull("workspaceURL")) {

			dataSource.setWorkspaceURL(jsonObject.getString("workspaceURL"));
		}

		return dataSource;
	}

}