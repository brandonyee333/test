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

package com.liferay.osb.asah.test.util.faro.backend.http;

import com.liferay.osb.asah.common.http.DataSourceHttp;
import com.liferay.osb.asah.common.json.JSONUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import org.mockito.Mockito;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author Rachael Koestartyo
 */
@TestConfiguration
public class DataSourceHttpTestConfiguration {

	@Bean
	@Primary
	public DataSourceHttp dataSourceHttp() {
		DataSourceHttp dataSourceHttp = Mockito.mock(DataSourceHttp.class);

		Mockito.doAnswer(
			invocation -> {
				JSONObject jsonObject = JSONUtil.put(
					"_embedded",
					JSONUtil.put(
						"groups",
						JSONUtil.put(
							JSONUtil.put(
								"active", true
							).put(
								"classNameId", "20001"
							).put(
								"classPK", "20126"
							).put(
								"companyId", "20099"
							).put(
								"friendlyURL", "/guest"
							).put(
								"groupId", "20126"
							).put(
								"parentGroupId", "0"
							).put(
								"site", true
							).put(
								"treePath", "/20126/"
							).put(
								"uuid", "ca8e80b4-e317-4c04-0e82-6c8dd91c0e1e"
							))
					).put(
						"page",
						JSONUtil.put(
							"end", 1
						).put(
							"start", 0
						).put(
							"totalElements", 1
						)
					));

				return new ResponseEntity<>(
					jsonObject.toString(), HttpStatus.OK);
			}
		).when(
			dataSourceHttp
		).getDXPGroups(
			Mockito.anyString(), Mockito.anyInt(), Mockito.anyString(),
			Mockito.anyLong(), Mockito.anyBoolean(), Mockito.anyInt()
		);

		Mockito.doAnswer(
			invocation -> {
				JSONObject jsonObject = JSONUtil.put(
					"_embedded",
					JSONUtil.put(
						"groups",
						JSONUtil.put(
							JSONUtil.put(
								"active", true
							).put(
								"classNameId", "20001"
							).put(
								"classPK", "20126"
							).put(
								"companyId", "20099"
							).put(
								"friendlyURL", "/guest"
							).put(
								"groupId", "20126"
							).put(
								"parentGroupId", "0"
							).put(
								"site", true
							).put(
								"treePath", "/20126/"
							).put(
								"uuid", "ca8e80b4-e317-4c04-0e82-6c8dd91c0e1e"
							))
					).put(
						"page",
						JSONUtil.put(
							"end", 1
						).put(
							"start", 0
						).put(
							"totalElements", 1
						)
					));

				return new ResponseEntity<>(
					jsonObject.toString(), HttpStatus.OK);
			}
		).when(
			dataSourceHttp
		).getDXPGroups(
			Mockito.anyString(), Mockito.anyString()
		);

		Mockito.doAnswer(
			invocation -> {
				JSONObject jsonObject = JSONUtil.put(
					"_embedded",
					JSONUtil.put(
						"organizations",
						JSONUtil.put(
							JSONUtil.put(
								"companyId", "20099"
							).put(
								"countryId", "26"
							).put(
								"name", "Organization Test"
							).put(
								"organizationId", "20127"
							).put(
								"parentOrganizationId", "0"
							).put(
								"recursable", true
							).put(
								"regionId", "0"
							).put(
								"statusId", "12017"
							).put(
								"treePath", "/330205/"
							).put(
								"type", "organization"
							).put(
								"userId", "20139"
							).put(
								"userName", "Test Test"
							).put(
								"uuid", "7e97571b-227d-b57e-8299-fc533f5ab6d1"
							))
					).put(
						"page",
						JSONUtil.put(
							"end", 1
						).put(
							"start", 0
						).put(
							"totalElements", 1
						)
					));

				return new ResponseEntity<>(
					jsonObject.toString(), HttpStatus.OK);
			}
		).when(
			dataSourceHttp
		).getDXPOrganizations(
			Mockito.anyString(), Mockito.anyString()
		);

		Mockito.doAnswer(
			invocation -> {
				JSONObject jsonObject = JSONUtil.put(
					"_embedded",
					JSONUtil.put(
						"organizations",
						JSONUtil.put(
							JSONUtil.put(
								"companyId", "20099"
							).put(
								"countryId", "26"
							).put(
								"name", "Organization Test"
							).put(
								"organizationId", "20127"
							).put(
								"parentOrganizationId", "0"
							).put(
								"recursable", true
							).put(
								"regionId", "0"
							).put(
								"statusId", "12017"
							).put(
								"treePath", "/330205/"
							).put(
								"type", "organization"
							).put(
								"userId", "20139"
							).put(
								"userName", "Test Test"
							).put(
								"uuid", "7e97571b-227d-b57e-8299-fc533f5ab6d1"
							))
					).put(
						"page",
						JSONUtil.put(
							"end", 1
						).put(
							"start", 0
						).put(
							"totalElements", 1
						)
					));

				return new ResponseEntity<>(
					jsonObject.toString(), HttpStatus.OK);
			}
		).when(
			dataSourceHttp
		).getDXPOrganizations(
			Mockito.anyString(), Mockito.anyInt(), Mockito.anyString(),
			Mockito.anyLong(), Mockito.anyInt()
		);

		Mockito.doAnswer(
			invocation -> {
				JSONObject jsonObject = JSONUtil.put(
					"_embedded",
					JSONUtil.put(
						"userGroups",
						JSONUtil.put(
							JSONUtil.put(
								"companyId", "20099"
							).put(
								"name", "User Group 1"
							).put(
								"parentUserGroupId", "0"
							).put(
								"userGroupId", "33213"
							).put(
								"userId", "20139"
							).put(
								"userName", "Test Test"
							).put(
								"uuid", "57f45b2f-116d-4f80-2c93-15ee3e4acd46"
							)))
				).put(
					"page",
					JSONUtil.put(
						"end", 1
					).put(
						"start", 0
					).put(
						"totalElements", 1
					)
				);

				return new ResponseEntity<>(
					jsonObject.toString(), HttpStatus.OK);
			}
		).when(
			dataSourceHttp
		).getDXPUserGroups(
			Mockito.anyString(), Mockito.anyInt(), Mockito.anyString(),
			Mockito.anyInt()
		);

		Mockito.doAnswer(
			invocation -> {
				JSONObject jsonObject = JSONUtil.put(
					"_embedded",
					JSONUtil.put(
						"userGroups",
						JSONUtil.put(
							JSONUtil.put(
								"companyId", "20099"
							).put(
								"name", "User Group 1"
							).put(
								"parentUserGroupId", "0"
							).put(
								"userGroupId", "33213"
							).put(
								"userId", "20139"
							).put(
								"userName", "Test Test"
							).put(
								"uuid", "57f45b2f-116d-4f80-2c93-15ee3e4acd46"
							)))
				).put(
					"page",
					JSONUtil.put(
						"end", 1
					).put(
						"start", 0
					).put(
						"totalElements", 1
					)
				);

				return new ResponseEntity<>(
					jsonObject.toString(), HttpStatus.OK);
			}
		).when(
			dataSourceHttp
		).getDXPUserGroups(
			Mockito.anyString(), Mockito.anyString()
		);

		Mockito.doAnswer(
			invocation -> {
				JSONArray jsonArray = JSONUtil.putAll(
					JSONUtil.put(
						"name", "birthday"
					).put(
						"values", JSONUtil.put("0")
					),
					JSONUtil.put(
						"name", "emailAddress"
					).put(
						"values", JSONUtil.put("test@liferay.com")
					),
					JSONUtil.put(
						"name", "lastName"
					).put(
						"values", JSONUtil.put("Test1")
					));

				return new ResponseEntity<>(
					jsonArray.toString(), HttpStatus.OK);
			}
		).when(
			dataSourceHttp
		).getDXPUsersFields(
			Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt()
		);

		Mockito.doAnswer(
			invocation -> new ResponseEntity<>("1", HttpStatus.OK)
		).when(
			dataSourceHttp
		).getDXPUsersTotal(
			Mockito.anyString(), Mockito.anyString()
		);

		Mockito.doAnswer(
			invocation -> {
				JSONArray jsonArray = JSONUtil.putAll(
					JSONUtil.put(
						"name", "Birthdate"
					).put(
						"values", JSONUtil.put("0")
					),
					JSONUtil.put(
						"name", "Email"
					).put(
						"values", JSONUtil.put("test@liferay.com")
					),
					JSONUtil.put(
						"name", "LastName"
					).put(
						"values", JSONUtil.put("Test1")
					));

				return new ResponseEntity<>(
					jsonArray.toString(), HttpStatus.OK);
			}
		).when(
			dataSourceHttp
		).getSalesforceAccountsFields(
			Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt()
		);

		Mockito.doAnswer(
			invocation -> {
				JSONArray jsonArray = JSONUtil.putAll(
					JSONUtil.put(
						"name", "birthday"
					).put(
						"values", JSONUtil.put("0")
					),
					JSONUtil.put(
						"name", "emailAddress"
					).put(
						"values", JSONUtil.put("test@salesforce.com")
					),
					JSONUtil.put(
						"name", "familyName"
					).put(
						"values", JSONUtil.put("Test1")
					));

				return new ResponseEntity<>(
					jsonArray.toString(), HttpStatus.OK);
			}
		).when(
			dataSourceHttp
		).getSalesforceUsersFields(
			Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt()
		);

		return dataSourceHttp;
	}

}