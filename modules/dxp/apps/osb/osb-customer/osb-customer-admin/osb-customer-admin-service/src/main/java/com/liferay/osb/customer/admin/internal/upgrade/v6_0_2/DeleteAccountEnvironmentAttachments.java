/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.admin.internal.upgrade.v6_0_2;

import com.liferay.osb.customer.admin.model.AccountEnvironmentAttachment;
import com.liferay.osb.customer.admin.service.AccountEnvironmentAttachmentLocalService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
@Component(service = DeleteAccountEnvironmentAttachments.class)
public class DeleteAccountEnvironmentAttachments extends UpgradeProcess {

	protected void deleteAccountEnvironmentAttachments() throws Exception {
		List<AccountEnvironmentAttachment> accountEnvironmentAttachments =
			_accountEnvironmentAttachmentLocalService.
				getAccountEnvironmentAttachments(
					QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (AccountEnvironmentAttachment accountEnvironmentAttachment :
				accountEnvironmentAttachments) {

			_accountEnvironmentAttachmentLocalService.
				deleteAccountEnvironmentAttachment(
					accountEnvironmentAttachment);
		}
	}

	@Override
	protected void doUpgrade() throws Exception {
		deleteAccountEnvironmentAttachments();
	}

	@Reference
	private AccountEnvironmentAttachmentLocalService
		_accountEnvironmentAttachmentLocalService;

}