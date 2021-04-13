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

package com.liferay.osb.customer.identity.management.internal.saml.resolver;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.osb.customer.identity.management.internal.constants.NameIdTypeConstants;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Contact;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.PropertiesUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.saml.opensaml.integration.resolver.UserResolver;

import java.io.Serializable;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.joda.time.DateTime;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author William Newbury
 * @author Ryan Park
 * @author Amos Fong
 */
@Component(
	immediate = true, property = "service.ranking:Integer=" + Integer.MAX_VALUE,
	service = UserResolver.class
)
public class CustomerUserResolver implements UserResolver {

	@Override
	public User resolveUser(
			UserResolverSAMLContext userResolverSAMLContext,
			ServiceContext serviceContext)
		throws Exception {

		if (_log.isDebugEnabled()) {
			String subjectNameIdentifier =
				userResolverSAMLContext.resolveSubjectNameIdentifier();

			String subjectNameFormat =
				userResolverSAMLContext.resolveSubjectNameFormat();

			_log.debug(
				StringBundler.concat(
					"Resolving user with name ID format ", subjectNameFormat,
					" and value ", subjectNameIdentifier));
		}

		long companyId = CompanyThreadLocal.getCompanyId();

		String subjectNameIdentifier = getSubjectNameIdentifier(
			userResolverSAMLContext);

		String subjectNameIdentifierType = getSubjectNameIdentifierType(
			userResolverSAMLContext);

		return importUser(
			companyId, subjectNameIdentifier, subjectNameIdentifierType,
			userResolverSAMLContext, serviceContext);
	}

	protected User addUser(
			long companyId, Map<String, List<Serializable>> attributesMap,
			ServiceContext serviceContext)
		throws PortalException {

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Adding user with attributes map " +
					MapUtil.toString(attributesMap));
		}

		String screenName = getValueAsString("screenName", attributesMap, null);
		String emailAddress = getValueAsString(
			"emailAddress", attributesMap, null);
		String firstName = getValueAsString("firstName", attributesMap, null);
		String lastName = getValueAsString("lastName", attributesMap, null);

		serviceContext.setUuid(getValueAsString("uuid", attributesMap, null));

		User user = userLocalService.addUser(
			0, companyId, true, null, null, false, screenName, emailAddress, 0,
			StringPool.BLANK, serviceContext.getLocale(), firstName,
			StringPool.BLANK, lastName, 0, 0, true, Calendar.JANUARY, 1, 1970,
			StringPool.BLANK, null, null, null, null, false, serviceContext);

		user = userLocalService.updateEmailAddressVerified(
			user.getUserId(), true);

		user = userLocalService.updatePasswordReset(user.getUserId(), false);

		Date modifiedDate = getValueAsDate("modifiedDate", attributesMap, null);

		if (modifiedDate != null) {
			user = userLocalService.updateModifiedDate(
				user.getUserId(), modifiedDate);
		}

		return user;
	}

	protected Map<String, List<Serializable>> getAttributesMap(
		UserResolverSAMLContext userResolverSAMLContext) {

		String peerEntityId = userResolverSAMLContext.resolvePeerEntityId();

		try {
			if (_log.isDebugEnabled()) {
				_log.debug(
					StringBundler.concat(
						"Attributes mapping for ", peerEntityId, " ",
						_USER_ATTRIBUTE_MAPPINGS));
			}

			Properties userAttributeMappingsProperties = PropertiesUtil.load(
				_USER_ATTRIBUTE_MAPPINGS);

			return userResolverSAMLContext.
				resolveBearerAssertionAttributesWithMapping(
					userAttributeMappingsProperties);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e.getMessage(), e);
			}
			else if (_log.isWarnEnabled()) {
				_log.warn(e.getMessage());
			}
		}

		return Collections.emptyMap();
	}

	protected String getSubjectNameIdentifier(
		UserResolverSAMLContext userResolverSAMLContext) {

		return userResolverSAMLContext.resolveSubjectNameIdentifier();
	}

	protected String getSubjectNameIdentifierType(
		UserResolverSAMLContext userResolverSAMLContext) {

		String format = userResolverSAMLContext.resolveSubjectNameFormat();

		if (Validator.isNull(format)) {
			return _SUBJECT_NAME_TYPE_UNSPECIFIED;
		}

		if (format.equals(NameIdTypeConstants.EMAIL)) {
			return _SUBJECT_NAME_TYPE_EMAIL_ADDRESS;
		}
		else if (format.equals(NameIdTypeConstants.UNSPECIFIED)) {
			return _SUBJECT_NAME_TYPE_UUID;
		}

		return _SUBJECT_NAME_TYPE_SCREENNAME;
	}

	protected User getUser(
			long companyId, String subjectNameIdentifier,
			String subjectNameIdentifierType)
		throws PortalException {

		try {
			if (subjectNameIdentifierType.endsWith(
					_SUBJECT_NAME_TYPE_EMAIL_ADDRESS)) {

				return userLocalService.getUserByEmailAddress(
					companyId, subjectNameIdentifier);
			}
			else if (subjectNameIdentifierType.endsWith(
						_SUBJECT_NAME_TYPE_SCREENNAME)) {

				return userLocalService.getUserByScreenName(
					companyId, subjectNameIdentifier);
			}
			else if (subjectNameIdentifierType.endsWith(
						_SUBJECT_NAME_TYPE_UUID)) {

				return userLocalService.getUserByUuidAndCompanyId(
					subjectNameIdentifier, companyId);
			}
		}
		catch (NoSuchUserException nsue) {

			// LPS-52675

			if (_log.isDebugEnabled()) {
				_log.debug(nsue, nsue);
			}
		}

		return null;
	}

	protected Date getValueAsDate(
		String key, Map<String, List<Serializable>> attributesMap,
		Date defaultValue) {

		List<Serializable> values = attributesMap.get(key);

		if (ListUtil.isEmpty(values)) {
			return defaultValue;
		}

		DateTime dateTime = new DateTime(values.get(0));

		return dateTime.toDate();
	}

	protected String getValueAsString(
		String key, Map<String, List<Serializable>> attributesMap,
		String defaultValue) {

		List<Serializable> values = attributesMap.get(key);

		if (ListUtil.isEmpty(values)) {
			return defaultValue;
		}

		return String.valueOf(values.get(0));
	}

	protected List<String> getValueAsStringList(
		String key, Map<String, List<Serializable>> attributesMap) {

		List<Serializable> values = attributesMap.get(key);

		if (ListUtil.isEmpty(values)) {
			return null;
		}

		Stream<Serializable> stream = values.stream();

		return stream.map(
			value -> value.toString()
		).collect(
			Collectors.toList()
		);
	}

	protected User importUser(
			long companyId, String subjectNameIdentifier,
			String subjectNameIdentifierType,
			UserResolverSAMLContext userResolverSAMLContext,
			ServiceContext serviceContext)
		throws PortalException {

		if (_log.isDebugEnabled()) {
			_log.debug(
				StringBundler.concat(
					"Importing user with identifier ", subjectNameIdentifier,
					" of type ", subjectNameIdentifierType));
		}

		Map<String, List<Serializable>> attributesMap = getAttributesMap(
			userResolverSAMLContext);

		User user = getUser(
			companyId, subjectNameIdentifier, subjectNameIdentifierType);

		if (user != null) {
			if (_log.isDebugEnabled()) {
				_log.debug("Found user " + user.toString());
			}

			user = updateUser(user, attributesMap, serviceContext);
		}
		else {
			user = addUser(companyId, attributesMap, serviceContext);

			if (_log.isDebugEnabled()) {
				_log.debug("Added user " + user.toString());
			}
		}

		List<String> groups = getValueAsStringList("groups", attributesMap);

		if (groups != null) {
			List<Organization> oldOrganizations = user.getOrganizations();

			for (String organizationName : groups) {
				Organization organization =
					organizationLocalService.fetchOrganization(
						companyId, organizationName);

				if (organization == null) {
					continue;
				}

				ExpandoBridge expandoBridge = organization.getExpandoBridge();

				Boolean saml = (Boolean)expandoBridge.getAttribute(
					"saml", false);

				if ((saml != null) && saml) {
					userLocalService.addOrganizationUser(
						organization.getOrganizationId(), user.getUserId());
				}
			}

			for (Organization organization : oldOrganizations) {
				ExpandoBridge expandoBridge = organization.getExpandoBridge();

				Boolean saml = (Boolean)expandoBridge.getAttribute(
					"saml", false);

				if ((saml != null) && saml &&
					!groups.contains(organization.getName())) {

					userLocalService.unsetOrganizationUsers(
						organization.getOrganizationId(),
						new long[] {user.getUserId()});
				}
			}
		}

		return user;
	}

	protected User updateUser(
			User user, Map<String, List<Serializable>> attributesMap,
			ServiceContext serviceContext)
		throws PortalException {

		if (_log.isDebugEnabled()) {
			_log.debug(
				StringBundler.concat(
					"Updating user ", String.valueOf(user.getUserId()),
					" with attributes map ", MapUtil.toString(attributesMap)));
		}

		String screenName = getValueAsString(
			"screenName", attributesMap, user.getScreenName());
		String emailAddress = getValueAsString(
			"emailAddress", attributesMap, user.getEmailAddress());
		String firstName = getValueAsString(
			"firstName", attributesMap, user.getFirstName());
		String lastName = getValueAsString(
			"lastName", attributesMap, user.getLastName());
		Date modifiedDate = getValueAsDate(
			"modifiedDate", attributesMap, user.getModifiedDate());

		Contact contact = user.getContact();

		if (!StringUtil.equalsIgnoreCase(
				emailAddress, user.getEmailAddress())) {

			user = userLocalService.updateEmailAddress(
				user.getUserId(), StringPool.BLANK, emailAddress, emailAddress);

			user = userLocalService.updateEmailAddressVerified(
				user.getUserId(), true);
		}

		if (!Objects.equals(user.getFirstName(), firstName) ||
			!Objects.equals(user.getLastName(), lastName) ||
			!Objects.equals(user.getScreenName(), screenName) ||
			!Objects.equals(user.getModifiedDate(), modifiedDate)) {

			Date oldModifiedDate = user.getModifiedDate();

			Calendar birthdayCalendar = CalendarFactoryUtil.getCalendar();

			birthdayCalendar.setTime(contact.getBirthday());

			user = userLocalService.updateUser(
				user.getUserId(), StringPool.BLANK, StringPool.BLANK,
				StringPool.BLANK, false, user.getReminderQueryQuestion(),
				user.getReminderQueryAnswer(), screenName, emailAddress,
				user.getFacebookId(), user.getOpenId(), true, null,
				user.getLanguageId(), user.getTimeZoneId(), user.getGreeting(),
				user.getComments(), firstName, user.getMiddleName(), lastName,
				contact.getPrefixId(), contact.getSuffixId(), user.getMale(),
				birthdayCalendar.get(Calendar.MONTH),
				birthdayCalendar.get(Calendar.DATE),
				birthdayCalendar.get(Calendar.YEAR), contact.getSmsSn(),
				contact.getFacebookSn(), contact.getJabberSn(),
				contact.getSkypeSn(), contact.getTwitterSn(),
				contact.getJobTitle(), null, null, null, null, null,
				serviceContext);

			if (!Objects.equals(oldModifiedDate, modifiedDate)) {
				user = userLocalService.updateModifiedDate(
					user.getUserId(), modifiedDate);
			}
		}

		return user;
	}

	@Reference
	protected OrganizationLocalService organizationLocalService;

	@Reference
	protected UserLocalService userLocalService;

	private static final String _SUBJECT_NAME_TYPE_EMAIL_ADDRESS =
		"emailAddress";

	private static final String _SUBJECT_NAME_TYPE_SCREENNAME = "screenName";

	private static final String _SUBJECT_NAME_TYPE_UNSPECIFIED = "unspecified";

	private static final String _SUBJECT_NAME_TYPE_UUID = "uuid";

	private static final String _USER_ATTRIBUTE_MAPPINGS =
		"firstName\nlastName\nemailAddress\nscreenName\nuuid";

	private static final Log _log = LogFactoryUtil.getLog(
		CustomerUserResolver.class);

}