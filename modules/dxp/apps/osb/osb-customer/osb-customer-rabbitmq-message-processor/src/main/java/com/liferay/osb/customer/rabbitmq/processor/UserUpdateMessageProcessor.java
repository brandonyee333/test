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

package com.liferay.osb.customer.rabbitmq.processor;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.osb.service.RemoteUserLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Contact;
import com.liferay.portal.kernel.model.Phone;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
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
	immediate = true, property = "routing.key=entity.user.update",
	service = UserUpdateMessageProcessor.class
)
public class UserUpdateMessageProcessor extends BaseMessageProcessor {

	protected void doProcess(JSONObject jsonObject) throws Exception {
		User user = fetchUser(jsonObject);

		if (user == null) {
			return;
		}

		User remoteUser = RemoteUserLocalServiceUtil.translate(jsonObject);

		Contact contact = user.getContact();

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(contact.getBirthday());

		user = userLocalService.updateUser(
			user.getUserId(), null, null, null, false, null, null,
			remoteUser.getScreenName(), remoteUser.getEmailAddress(),
			user.getFacebookId(), user.getOpenId(), true, null,
			remoteUser.getLanguageId(), remoteUser.getTimeZoneId(),
			user.getGreeting(), user.getComments(), remoteUser.getFirstName(),
			remoteUser.getMiddleName(), remoteUser.getLastName(),
			contact.getPrefixId(), contact.getSuffixId(), contact.isMale(),
			calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE),
			calendar.get(Calendar.YEAR), contact.getSmsSn(),
			contact.getFacebookSn(), contact.getJabberSn(),
			contact.getSkypeSn(), contact.getTwitterSn(),
			remoteUser.getJobTitle(), null, null, null, null, null, null);

		updateUserPhones(contact.getContactId(), user, remoteUser);

		ExpandoBridge expandoBridge = user.getExpandoBridge();

		ExpandoBridge remoteExpandoBridge = remoteUser.getExpandoBridge();

		expandoBridge.setAttributes(remoteExpandoBridge.getAttributes(), false);
	}

	@Reference(
		target = "(module.service.lifecycle=osb.portlet.initialized)",
		unbind = "-"
	)
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

	protected void updateUserPhones(long contactId, User user, User remoteUser)
		throws PortalException {

		Set<Long> phoneIds = new HashSet<>();

		List<Phone> phones = remoteUser.getPhones();

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
					curPhone.getPrimary());
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
	private PhoneLocalService _phoneLocalService;

}