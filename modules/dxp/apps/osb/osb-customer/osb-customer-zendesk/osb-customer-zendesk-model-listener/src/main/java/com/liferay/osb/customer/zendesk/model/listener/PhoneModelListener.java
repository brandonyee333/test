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

package com.liferay.osb.customer.zendesk.model.listener;

import com.liferay.osb.customer.zendesk.model.listener.exception.ZendeskIntegrationException;
import com.liferay.osb.customer.zendesk.model.listener.synchronizer.UserSynchronizer;
import com.liferay.osb.service.ExternalIdMapperLocalServiceUtil;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.Contact;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.model.Phone;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.ContactLocalService;

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
				long userId = getContactUserId(phone.getClassPK());

				_userSynchronizer.addPhone(userId, phone);
			}
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new ZendeskIntegrationException(e);
		}
	}

	@Override
	public void onAfterRemove(Phone phone) throws ModelListenerException {
		try {
			String className = phone.getClassName();

			if (className.equals(Contact.class.getName())) {
				long userId = getContactUserId(phone.getClassPK());

				_userSynchronizer.deletePhone(userId, phone);

				ExternalIdMapperLocalServiceUtil.deleteExternalIdMappers(
					_classNameLocalService.getClassNameId(Phone.class),
					phone.getPhoneId());
			}
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new ZendeskIntegrationException(e);
		}
	}

	@Override
	public void onAfterUpdate(Phone phone) throws ModelListenerException {
		try {
			String className = phone.getClassName();

			if (className.equals(Contact.class.getName())) {
				long userId = getContactUserId(phone.getClassPK());

				_userSynchronizer.updatePhone(userId, phone);
			}
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new ZendeskIntegrationException(e);
		}
	}

	protected long getContactUserId(long contactId) throws PortalException {
		Contact contact = _contactLocalService.getContact(contactId);

		return contact.getUserId();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PhoneModelListener.class);

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private ContactLocalService _contactLocalService;

	@Reference
	private UserSynchronizer _userSynchronizer;

}