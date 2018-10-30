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

package com.liferay.osb.customer.zendesk.listeners;

import com.liferay.osb.customer.zendesk.listeners.synchronizer.UserSynchronizer;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.Contact;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.model.Phone;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ContactLocalService;
import com.liferay.portal.kernel.service.UserLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = ModelListener.class)
public class PhoneModelListener extends BaseModelListener<Phone> {

	@Override
	public void onAfterCreate(Phone phone) throws ModelListenerException {
		try {
			String className = phone.getClassName();

			if (className.equals(Contact.class.getName())) {
				long userId = getContactUser(phone.getClassPK());

				_userSynchronizer.createPhone(userId, phone);
			}
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	@Override
	public void onAfterRemove(Phone phone) throws ModelListenerException {
		try {
			String className = phone.getClassName();

			if (className.equals(Contact.class.getName())) {
				long userId = getContactUser(phone.getClassPK());

				_userSynchronizer.deletePhone(userId, phone);
			}
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	@Override
	public void onAfterUpdate(Phone phone) throws ModelListenerException {
		try {
			String className = phone.getClassName();

			if (className.equals(Contact.class.getName())) {
				long userId = getContactUser(phone.getClassPK());

				_userSynchronizer.updatePhone(userId, phone);
			}
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	protected long getContactUser(long contactId) throws PortalException {
		Contact contact = _contactLocalService.getContact(contactId);

		User user = _userLocalService.getUser(contact.getUserId());

		return user.getUserId();
	}

	@Reference
	private ContactLocalService _contactLocalService;

	@Reference
	private UserLocalService _userLocalService;

	@Reference
	private UserSynchronizer _userSynchronizer;

}