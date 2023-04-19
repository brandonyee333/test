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

package com.liferay.osb.asah.common.constants;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Marcellus Tavares
 */
public class FieldMappingConstants {

	public static final Map<String, String> demographicsDisplayNames =
		Collections.unmodifiableMap(
			new HashMap<String, String>() {
				{
					put("agreedToTermsOfUse", "agreedToTermsOfUse");
					put("birthday", "birthDate");
					put("classNameId", "classNameId");
					put("classPK", "classPK");
					put("comments", "comments");
					put("companyId", "companyId");
					put("contactId", "contactId");
					put("createDate", "createDate");
					put("defaultUser", "defaultUser");
					put("emailAddress", "email");
					put("emailAddressVerified", "emailAddressVerified");
					put("employeeNumber", "employeeNumber");
					put("employeeStatusId", "employeeStatusId");
					put("externalReferenceCode", "externalReferenceCode");
					put("facebookId", "facebookId");
					put("facebookSn", "facebookSn");
					put("firstName", "givenName");
					put("gender", "gender");
					put("googleUserId", "googleUserId");
					put("greeting", "greeting");
					put("groupIds", "groupIds");
					put("hoursOfOperation", "hoursOfOperation");
					put("jabberSn", "jabberSn");
					put("jobClass", "jobClass");
					put("jobTitle", "jobTitle");
					put("languageId", "languageId");
					put("lastName", "familyName");
					put("ldapServerId", "ldapServerId");
					put("male", "male");
					put("middleName", "additionalName");
					put("modifiedDate", "modifiedDate");
					put("openId", "openId");
					put("organizationIds", "organizationIds");
					put("osbAsahDataSourceId", "osbAsahDataSourceId");
					put("parentContactId", "parentContactId");
					put("portraitId", "portraitId");
					put("prefixListTypeId", "prefixListTypeId");
					put("roleIds", "roleIds");
					put("screenName", "screenName");
					put("skypeSn", "skypeSn");
					put("smsSn", "smsSn");
					put("status", "status");
					put("suffixListTypeId", "suffixListTypeId");
					put("teamIds", "teamIds");
					put("timeZoneId", "timeZoneId");
					put("twitterSn", "twitterSn");
					put("userGroupIds", "userGroupIds");
					put("userId", "userId");
					put("userName", "userName");
					put("uuid", "uuid");
				}
			});

}