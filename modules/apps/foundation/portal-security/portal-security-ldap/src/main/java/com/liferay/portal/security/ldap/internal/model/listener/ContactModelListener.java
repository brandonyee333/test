/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.ldap.internal.model.listener;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.Contact;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.ldap.LDAPSettings;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.security.exportimport.UserExporter;
import com.liferay.portal.security.ldap.internal.UserImportTransactionThreadLocal;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Scott Lee
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
@Component(immediate = true, service = ModelListener.class)
public class ContactModelListener extends BaseLDAPExportModelListener<Contact> {

	@Override
	public void onAfterCreate(Contact contact) throws ModelListenerException {
		try {
			exportToLDAP(contact);
		}
		catch (Exception e) {
			throw new ModelListenerException(
				"Unable to export contact with user ID " + contact.getUserId() +
					" to LDAP on after create",
				e);
		}
	}

	@Override
	public void onAfterUpdate(Contact contact) throws ModelListenerException {
		try {
			exportToLDAP(contact);
		}
		catch (Exception e) {
			throw new ModelListenerException(
				"Unable to export contact with user ID " + contact.getUserId() +
					" to LDAP on after update",
				e);
		}
	}

	protected void exportToLDAP(Contact contact) throws Exception {
		if (UserImportTransactionThreadLocal.isOriginatesFromImport()) {
			return;
		}

		User user = _userLocalService.fetchUser(contact.getClassPK());

		exportToLDAP(user, _userExporter, _ldapSettings);
	}

	@Reference(
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	private volatile LDAPSettings _ldapSettings;

	@Reference(
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	private volatile UserExporter _userExporter;

	@Reference
	private UserLocalService _userLocalService;

}