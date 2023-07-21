/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.shopping.verify;

import com.liferay.portal.verify.VerifyProcess;
import com.liferay.portal.verify.VerifyResourcePermissions;
import com.liferay.shopping.internal.verify.model.ShoppingCategoryVerifiableModel;
import com.liferay.shopping.internal.verify.model.ShoppingItemVerifiableResourcedModel;
import com.liferay.shopping.internal.verify.model.ShoppingOrderVerifiableModel;
import com.liferay.shopping.service.ShoppingCartLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Peter Fellwock
 */
@Component(
	immediate = true,
	property = "verify.process.name=com.liferay.shopping.service",
	service = VerifyProcess.class
)
public class ShoppingServiceVerifyProcess extends VerifyProcess {

	@Override
	protected void doVerify() throws Exception {
		verifyResourcedModels();
	}

	@Reference(unbind = "-")
	protected void setShoppingCartLocalService(
		ShoppingCartLocalService shoppingCartLocalService) {
	}

	protected void verifyResourcedModels() throws Exception {
		_verifyResourcePermissions.verify(
			new ShoppingCategoryVerifiableModel());
		_verifyResourcePermissions.verify(
			new ShoppingItemVerifiableResourcedModel());
		_verifyResourcePermissions.verify(new ShoppingOrderVerifiableModel());
	}

	private final VerifyResourcePermissions _verifyResourcePermissions =
		new VerifyResourcePermissions();

}