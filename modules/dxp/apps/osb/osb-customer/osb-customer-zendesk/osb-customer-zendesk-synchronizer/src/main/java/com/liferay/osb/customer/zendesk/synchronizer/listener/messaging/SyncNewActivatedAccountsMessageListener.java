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

package com.liferay.osb.customer.zendesk.synchronizer.listener.messaging;

import com.liferay.osb.customer.admin.model.AccountEntry;
import com.liferay.osb.customer.admin.service.AccountEntryLocalService;
import com.liferay.osb.customer.koroneiki.constants.ProductConstants;
import com.liferay.osb.customer.koroneiki.util.AccountReader;
import com.liferay.osb.customer.koroneiki.web.service.AccountWebService;
import com.liferay.osb.customer.koroneiki.web.service.ProductPurchaseViewWebService;
import com.liferay.osb.customer.koroneiki.web.service.ProductWebService;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchaseView;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.SchedulerEntry;
import com.liferay.portal.kernel.scheduler.SchedulerEntryImpl;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.scheduler.TriggerFactory;
import com.liferay.portal.kernel.util.FastDateFormatFactory;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.text.Format;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true, service = SyncNewActivatedAccountsMessageListener.class
)
public class SyncNewActivatedAccountsMessageListener
	extends BaseMessageListener {

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_dateFormat = _fastDateFormatFactory.getSimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");

		Class<?> clazz = getClass();

		String className = clazz.getName();

		Trigger trigger = _triggerFactory.createTrigger(
			className, className, null, null, 1, TimeUnit.DAY);

		SchedulerEntry schedulerEntry = new SchedulerEntryImpl(
			className, trigger);

		_schedulerEngineHelper.register(
			this, schedulerEntry, DestinationNames.SCHEDULER_DISPATCH);
	}

	@Deactivate
	protected void deactivate() {
		_schedulerEngineHelper.unregister(this);
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		Calendar calendar = Calendar.getInstance();

		Date now = calendar.getTime();

		calendar.add(Calendar.DATE, -2);

		StringBundler sb = new StringBundler(6);

		sb.append("productKey in ('");
		sb.append(StringUtil.merge(_getSubscriptionProductKeys(), "', '"));
		sb.append("') and supportLifeStartDate ge ");
		sb.append(_dateFormat.format(calendar.getTime()));
		sb.append(" and supportLifeStartDate le ");
		sb.append(_dateFormat.format(now));

		List<ProductPurchaseView> productPurchaseViews =
			_productPurchaseViewWebService.getProductPurchaseViews(
				StringPool.BLANK, sb.toString(), 1, 1000, StringPool.BLANK);

		Set<String> accountKeys = new HashSet<>();

		for (ProductPurchaseView productPurchaseView : productPurchaseViews) {
			ProductPurchase productPurchase =
				productPurchaseView.getProductPurchases()[0];

			if (accountKeys.contains(productPurchase.getAccountKey())) {
				continue;
			}

			accountKeys.add(productPurchase.getAccountKey());

			AccountEntry accountEntry =
				_accountEntryLocalService.getKoroneikiAccountEntry(
					productPurchase.getAccountKey());

			Account account = _accountWebService.fetchAccount(
				productPurchase.getAccountKey());

			_accountEntryLocalService.updateAccountEntry(
				accountEntry.getAccountEntryId(),
				_accountReader.isActiveSupport(account.getEntitlements()),
				_accountReader.isActiveTicketSupport(account.getEntitlements()),
				_accountReader.getStatus(account));
		}
	}

	private List<String> _getSubscriptionProductKeys() throws Exception {
		List<String> productKeys = new ArrayList<>();

		Product product = _productWebService.fetchProductByName(
			ProductConstants.NAME_GOLD);

		if (product != null) {
			productKeys.add(product.getKey());
		}

		product = _productWebService.fetchProductByName(
			ProductConstants.NAME_PLATINUM);

		if (product != null) {
			productKeys.add(product.getKey());
		}

		product = _productWebService.fetchProductByName(
			ProductConstants.NAME_PREMIUM);

		if (product != null) {
			productKeys.add(product.getKey());
		}

		return productKeys;
	}

	@Reference
	private AccountEntryLocalService _accountEntryLocalService;

	@Reference
	private AccountReader _accountReader;

	@Reference
	private AccountWebService _accountWebService;

	private Format _dateFormat;

	@Reference
	private FastDateFormatFactory _fastDateFormatFactory;

	@Reference
	private ProductPurchaseViewWebService _productPurchaseViewWebService;

	@Reference
	private ProductWebService _productWebService;

	@Reference
	private SchedulerEngineHelper _schedulerEngineHelper;

	@Reference
	private TriggerFactory _triggerFactory;

}