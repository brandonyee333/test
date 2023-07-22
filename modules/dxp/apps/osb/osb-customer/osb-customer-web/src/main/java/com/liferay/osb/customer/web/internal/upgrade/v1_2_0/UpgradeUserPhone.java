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

package com.liferay.osb.customer.web.internal.upgrade.v1_2_0;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.model.Contact;
import com.liferay.portal.kernel.model.Phone;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.PhoneLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
@Component(service = UpgradeUserPhone.class)
public class UpgradeUserPhone extends UpgradeProcess {

	public static final String KORONEIKI_CONTACT_CLASS_NAME =
		"com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact";

	protected void cleanUpPhoneNumbers(Phone phone) throws Exception {
		User user = _userLocalService.fetchUser(phone.getUserId());

		if (user == null) {
			_phoneLocalService.deletePhone(phone);
		}

		List<Phone> phones = user.getPhones();

		if (!phones.isEmpty()) {
			for (Phone curPhone : phones) {
				if (curPhone.getTypeId() == phone.getTypeId()) {
					_phoneLocalService.updatePhone(
						curPhone.getPhoneId(), phone.getNumber(),
						curPhone.getExtension(), curPhone.getTypeId(),
						curPhone.getPrimary());
				}
			}
		}
		else {
			ServiceContext serviceContext = new ServiceContext();

			Contact contact = user.getContact();

			serviceContext.setUuid(contact.getUserUuid());

			_phoneLocalService.addPhone(
				user.getUserId(), Contact.class.getName(),
				contact.getContactId(), phone.getNumber(), StringPool.BLANK,
				phone.getTypeId(), phone.isPrimary(), serviceContext);
		}

		_phoneLocalService.deletePhone(phone);
	}

	@Override
	protected void doUpgrade() throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			long koroneikiContactClassId = _portal.getClassNameId(
				KORONEIKI_CONTACT_CLASS_NAME);

			ps = connection.prepareStatement(
				"select phoneId from Phone where classNameId = ? order by " +
					"modifiedDate asc");

			ps.setLong(1, koroneikiContactClassId);

			rs = ps.executeQuery();

			while (rs.next()) {
				long phoneId = rs.getLong("phoneId");

				Phone phone = _phoneLocalService.fetchPhone(phoneId);

				if (phone != null) {
					cleanUpPhoneNumbers(phone);
				}
			}
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}
	}

	@Reference
	private PhoneLocalService _phoneLocalService;

	@Reference
	private Portal _portal;

	@Reference
	private UserLocalService _userLocalService;

}