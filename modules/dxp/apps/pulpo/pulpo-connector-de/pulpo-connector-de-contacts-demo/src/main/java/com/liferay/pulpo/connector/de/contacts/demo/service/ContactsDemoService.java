/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.pulpo.connector.de.contacts.demo.service;

import com.github.javafaker.Faker;

import com.liferay.counter.kernel.service.CounterLocalService;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.model.ExpandoColumnConstants;
import com.liferay.expando.kernel.service.ExpandoColumnLocalService;
import com.liferay.expando.kernel.service.ExpandoTableLocalService;
import com.liferay.expando.kernel.service.ExpandoValueLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.model.Contact;
import com.liferay.portal.kernel.model.Country;
import com.liferay.portal.kernel.model.ListType;
import com.liferay.portal.kernel.model.ListTypeConstants;
import com.liferay.portal.kernel.model.Region;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserConstants;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.service.AddressLocalService;
import com.liferay.portal.kernel.service.BaseServiceImpl;
import com.liferay.portal.kernel.service.CountryService;
import com.liferay.portal.kernel.service.ListTypeService;
import com.liferay.portal.kernel.service.RegionService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PrefsPropsUtil;
import com.liferay.portal.util.PropsValues;

import java.io.IOException;
import java.io.InputStream;

import java.net.URL;

import java.text.DateFormat;
import java.text.ParseException;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Cristina González
 */
@Component(service = ContactsDemoService.class)
public class ContactsDemoService {

	public User createUser(long companyId) throws IOException, PortalException {
		try (InputStream is = (new URL(_RANDOM_USER_API)).openStream()) {
			String json = StringUtil.read(is);

			JSONObject rootJSONObject = JSONFactoryUtil.createJSONObject(json);

			JSONObject userJSONObject = rootJSONObject.getJSONArray(
				"results").getJSONObject(0);

			String emailAddress = _getEmailAddress(userJSONObject);

			boolean male = StringUtil.equalsIgnoreCase(
				userJSONObject.getString("gender"), "male");

			Date birthDate = _getBirthDate(userJSONObject);

			JSONObject pictureJSONObject = userJSONObject.getJSONObject(
				"picture");

			String portraitURL = pictureJSONObject.getString("large");

			byte[] portraitBytes = _getBytes(new URL(portraitURL));

			User user = _userLocalService.fetchUserByEmailAddress(
				companyId, emailAddress);

			if (user == null) {
				JSONObject nameJSONObject = userJSONObject.getJSONObject(
					"name");

				String firstName = nameJSONObject.getString("first");
				String lastName = nameJSONObject.getString("last");

				user = _createBasicUser(
					companyId, emailAddress, firstName, lastName, male,
					birthDate);

				_addAddress(userJSONObject, user);

				if (portraitBytes != null) {
					_portal.updateImageId(
						user, true, portraitBytes, "portraitId",
						PrefsPropsUtil.getLong(PropsKeys.USERS_IMAGE_MAX_SIZE),
						PropsValues.USERS_IMAGE_MAX_HEIGHT,
						PropsValues.USERS_IMAGE_MAX_WIDTH);
				}
			}

			Faker faker = new Faker();

			ExpandoBridge expandoBridge = user.getExpandoBridge();

			_addAttributeIfNotexist(user, "favouriteArtist");

			expandoBridge.setAttribute(
				"favouriteArtist", faker.artist().name());

			_addAttributeIfNotexist(user, "favouriteColor");

			expandoBridge.setAttribute("favouriteColor", faker.color().name());

			_addAttributeIfNotexist(user, "favouriteGenre");

			expandoBridge.setAttribute("favouriteGenre", faker.book().genre());

			_addAttributeIfNotexist(user, "favouritePokemon");

			expandoBridge.setAttribute(
				"favouritePokemon", faker.pokemon().name());

			user.setModifiedDate(new Date());

			user = _userLocalService.updateUser(user);

			return user;
		}
	}

	public long getUserId() throws PrincipalException {
		String name = PrincipalThreadLocal.getName();

		if (Validator.isNull(name)) {
			throw new PrincipalException("Principal is null");
		}
		else {
			for (int i = 0; i < BaseServiceImpl.ANONYMOUS_NAMES.length; i++) {
				if (StringUtil.equalsIgnoreCase(
						name, BaseServiceImpl.ANONYMOUS_NAMES[i])) {

					throw new PrincipalException(
						"Principal cannot be " +
							BaseServiceImpl.ANONYMOUS_NAMES[i]);
				}
			}
		}

		return GetterUtil.getLong(name);
	}

	private Address _addAddress(JSONObject userJSONObject, User user)
		throws PortalException {

		JSONObject location = userJSONObject.getJSONObject("location");

		String city = location.getString("city");

		Address address = _addressLocalService.createAddress(
			_counterLocalService.increment());

		address.setCity(city);

		String street = location.getString("street");

		address.setStreet1(street);

		String countryA2 = userJSONObject.getString("nat");

		Country country = _countryService.getCountryByA2(countryA2);

		address.setCountryId(country.getCountryId());

		List<Region> regions = _regionService.getRegions(
			country.getCountryId());

		String state = location.getString("state");

		for (Region region : regions) {
			if (state.equals(StringUtil.toLowerCase(region.getName()))) {
				address.setRegionId(region.getRegionId());

				break;
			}
		}

		String postcode = location.getString("postcode");

		address.setZip(postcode);

		address.setUserId(getUserId());

		address.setClassName(Contact.class.getName());
		address.setClassPK(user.getContact().getPrimaryKey());

		List<ListType> listTypes = _listTypeService.getListTypes(
			ListTypeConstants.CONTACT_ADDRESS);

		ListType listType = listTypes.get(0);

		address.setTypeId(listType.getListTypeId());

		address.setCompanyId(user.getCompanyId());

		return _addressLocalService.addAddress(address);
	}

	private void _addAttributeIfNotexist(User user, String columnName)
		throws PortalException {

		ExpandoBridge expandoBridge = user.getExpandoBridge();

		if (!expandoBridge.hasAttribute(columnName)) {
			expandoBridge.addAttribute(
				columnName, ExpandoColumnConstants.STRING);
		}
	}

	private User _createBasicUser(
			long companyId, String emailAddress, String firstName,
			String lastName, boolean male, Date birthDate)
		throws PortalException {

		boolean autoPassword = false;
		String password1 = "test";
		String password2 = "test";
		long facebookId = 0;
		String openId = StringPool.BLANK;
		Locale locale = LocaleUtil.getDefault();
		String middleName = StringPool.BLANK;
		long prefixId = 0;
		long suffixId = 0;

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(birthDate);

		int birthdayMonth = calendar.get(Calendar.MONTH);
		int birthdayDay = calendar.get(Calendar.DATE);
		int birthdayYear = calendar.get(Calendar.YEAR);

		long[] groupIds = null;
		long[] organizationIds = null;
		long[] roleIds = null;
		long[] userGroupIds = null;
		boolean sendMail = false;

		Faker faker = new Faker();

		String jobTitle = faker.company().profession();

		return _userLocalService.addUser(
			UserConstants.USER_ID_DEFAULT, companyId, autoPassword, password1,
			password2, true, null, emailAddress, facebookId, openId, locale,
			firstName, middleName, lastName, prefixId, suffixId, male,
			birthdayMonth, birthdayDay, birthdayYear, jobTitle, groupIds,
			organizationIds, roleIds, userGroupIds, sendMail,
			new ServiceContext());
	}

	private Date _getBirthDate(JSONObject userJSONObject) {
		String dob = userJSONObject.getString("dob");

		try {
			DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");

			return dateFormat.parse(dob);
		}
		catch (ParseException pe) {
			if (_log.isWarnEnabled()) {
				_log.warn(pe, pe);
			}
		}

		return new Date();
	}

	private byte[] _getBytes(URL url) throws IOException {
		try (InputStream is = url.openStream()) {
			return FileUtil.getBytes(is);
		}
	}

	private String _getEmailAddress(JSONObject userJSONObject) {
		String emailAddress = userJSONObject.getString("email");

		if (!Validator.isEmailAddress(emailAddress)) {
			String[] emailComponents = StringUtil.split(
				emailAddress, CharPool.AT);

			String normalizedEmail = FriendlyURLNormalizerUtil.normalize(
				emailComponents[0]);

			emailAddress = String.format(
				"%s@%s", normalizedEmail, emailComponents[1]);
		}

		return emailAddress;
	}

	private static final String _RANDOM_USER_API =
		"https://randomuser.me/api?inc=email,gender,dob,picture,name," +
			"location,nat&noinfo&nat=us,dk,fr,gb";

	private static final Log _log = LogFactoryUtil.getLog(
		ContactsDemoService.class);

	@Reference
	private AddressLocalService _addressLocalService;

	@Reference
	private CounterLocalService _counterLocalService;

	@Reference
	private CountryService _countryService;

	@Reference
	private ExpandoColumnLocalService _expandoColumnLocalService;

	@Reference
	private ExpandoTableLocalService _expandoTableLocalService;

	@Reference
	private ExpandoValueLocalService _expandoValueLocalService;

	@Reference
	private ListTypeService _listTypeService;

	@Reference
	private Portal _portal;

	@Reference
	private RegionService _regionService;

	@Reference
	private UserLocalService _userLocalService;

}