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

package com.liferay.osb.customer.admin.web.internal.portlet.action;

import com.liferay.osb.customer.admin.model.ProductEntry;
import com.liferay.osb.customer.admin.service.ProductEntryLocalService;
import com.liferay.osb.customer.admin.web.internal.constants.CustomerAdminPortletKeys;
import com.liferay.osb.customer.koroneiki.web.service.ProductWebService;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	property = {
		"javax.portlet.name=" + CustomerAdminPortletKeys.ADMIN,
		"mvc.command.name=linkProductEntries"
	},
	service = MVCActionCommand.class
)
public class LinkProductEntriesMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		List<ProductEntry> productEntries =
			_productEntryLocalService.getProductEntries(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (ProductEntry productEntry : productEntries) {
			String name = _getNewName(productEntry.getName());

			Product product = _productWebService.fetchProductByName(name);

			if (product == null) {
				_log.error(
					"No product was found for " + productEntry.getName());

				continue;
			}

			productEntry.setKoroneikiProductKey(product.getKey());
			productEntry.setName(name);

			_productEntryLocalService.updateProductEntry(productEntry);
		}
	}

	private String _getNewName(String name) {
		String newName = StringUtil.replace(name, "Digital Enterprise", "DXP");

		if (newName.startsWith("Liferay")) {
			newName = StringUtil.replaceFirst(
				newName, "Liferay", StringPool.BLANK);
		}

		return newName;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LinkProductEntriesMVCActionCommand.class);

	@Reference
	private ProductEntryLocalService _productEntryLocalService;

	@Reference
	private ProductWebService _productWebService;

}