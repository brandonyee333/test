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

package com.liferay.osb.customer.admin.internal.model.listener;

import com.liferay.osb.customer.admin.constants.ExternalIdMapperConstants;
import com.liferay.osb.customer.admin.model.AccountEntry;
import com.liferay.osb.customer.admin.service.AccountEntryLocalService;
import com.liferay.osb.customer.admin.service.ExternalIdMapperLocalService;
import com.liferay.petra.lang.CentralizedThreadLocal;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.service.ClassNameLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = ModelListener.class)
public class AccountEntryModelListener extends BaseModelListener<AccountEntry> {

	@Override
	public void onAfterUpdate(AccountEntry accountEntry)
		throws ModelListenerException {

		try {
			if (!hasZendeskOrganization(accountEntry)) {
				return;
			}

			AccountEntry oldAccountEntry = _oldAccountEntry.get();

			if (!oldAccountEntry.isActiveSupport() &&
				accountEntry.isActiveSupport()) {

				// TODO
				// accountCustomerLocalService.resetClosedWorkers(accountEntryId);

			}
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new ModelListenerException(e);
		}
	}

	@Override
	public void onBeforeUpdate(AccountEntry accountEntry)
		throws ModelListenerException {

		try {
			AccountEntry oldAccountEntry =
				_accountEntryLocalService.getAccountEntry(
					accountEntry.getAccountEntryId());

			_oldAccountEntry.set(oldAccountEntry);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new ModelListenerException(e);
		}
	}

	protected boolean hasZendeskOrganization(AccountEntry accountEntry)
		throws PortalException {

		long classNameId = _classNameLocalService.getClassNameId(
			AccountEntry.class);

		boolean externalIdMappers =
			_externalIdMapperLocalService.hasExternalIdMappers(
				classNameId, accountEntry.getAccountEntryId(),
				ExternalIdMapperConstants.TYPE_ZENDESK);

		if (externalIdMappers) {
			return true;
		}

		return false;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AccountEntryModelListener.class);

	private static final ThreadLocal<AccountEntry> _oldAccountEntry =
		new CentralizedThreadLocal<>(
			AccountEntryModelListener.class + "._oldAccountEntry");

	@Reference
	private AccountEntryLocalService _accountEntryLocalService;

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private ExternalIdMapperLocalService _externalIdMapperLocalService;

}