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

package com.liferay.osb.customer.legacy.message.subscriber;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.osb.customer.legacy.web.service.util.LegacyConverter;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Contact;
import com.liferay.portal.kernel.model.Phone;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.PhoneLocalService;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true, property = "topic.pattern=entity.user.update",
	service = UserUpdateMessageSubscriber.class
)
public class UserUpdateMessageSubscriber extends BaseMessageSubscriber {

	protected void doReceive(JSONObject jsonObject) throws Exception {
		User user = fetchUser(jsonObject.getString("uuid"));

		if (user == null) {
			return;
		}

		User legacyUser = _legacyConverter.toUser(jsonObject);

		Contact contact = user.getContact();

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(contact.getBirthday());

		user = userLocalService.updateUser(
			user.getUserId(), null, null, null, false, null, null,
			legacyUser.getScreenName(), legacyUser.getEmailAddress(),
			user.getFacebookId(), user.getOpenId(), true, null,
			legacyUser.getLanguageId(), legacyUser.getTimeZoneId(),
			user.getGreeting(), user.getComments(), legacyUser.getFirstName(),
			legacyUser.getMiddleName(), legacyUser.getLastName(),
			contact.getPrefixId(), contact.getSuffixId(), contact.isMale(),
			calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE),
			calendar.get(Calendar.YEAR), contact.getSmsSn(),
			contact.getFacebookSn(), contact.getJabberSn(),
			contact.getSkypeSn(), contact.getTwitterSn(),
			legacyUser.getJobTitle(), null, null, null, null, null, null);

		updateUserPhones(contact.getContactId(), user, legacyUser);

		ExpandoBridge expandoBridge = user.getExpandoBridge();

		ExpandoBridge remoteExpandoBridge = legacyUser.getExpandoBridge();

		expandoBridge.setAttributes(remoteExpandoBridge.getAttributes(), false);

		if (legacyUser.getStatus() != user.getStatus()) {
			userLocalService.updateStatus(
				user.getUserId(), legacyUser.getStatus(), new ServiceContext());
		}
	}

	protected void updateUserPhones(long contactId, User user, User legacyUser)
		throws PortalException {

		Set<Long> phoneIds = new HashSet<>();

		List<Phone> phones = legacyUser.getPhones();

		for (Phone curPhone : phones) {
			Phone phone = _phoneLocalService.fetchPhone(curPhone.getPhoneId());

			if (phone == null) {
				ServiceContext serviceContext = new ServiceContext();

				curPhone.setUuid(serviceContext.getUuid());

				curPhone.setUserId(user.getUserId());
				curPhone.setUserName(user.getFullName());
				curPhone.setClassPK(contactId);

				curPhone = _phoneLocalService.addPhone(curPhone);
			}
			else {
				curPhone = _phoneLocalService.updatePhone(
					curPhone.getPhoneId(), curPhone.getNumber(),
					curPhone.getExtension(), curPhone.getTypeId(),
					curPhone.isPrimary());
			}

			phoneIds.add(curPhone.getPhoneId());
		}

		phones = _phoneLocalService.getPhones(
			user.getCompanyId(), Contact.class.getName(), contactId);

		for (Phone phone : phones) {
			if (!phoneIds.contains(phone.getPhoneId())) {
				_phoneLocalService.deletePhone(phone.getPhoneId());
			}
		}
	}

	@Reference
	private LegacyConverter _legacyConverter;

	@Reference
	private PhoneLocalService _phoneLocalService;

}