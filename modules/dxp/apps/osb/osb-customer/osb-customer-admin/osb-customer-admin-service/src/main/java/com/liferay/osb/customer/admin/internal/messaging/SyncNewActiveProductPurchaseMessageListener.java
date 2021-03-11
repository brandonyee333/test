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

package com.liferay.osb.customer.admin.internal.messaging;

import com.liferay.osb.customer.admin.constants.AccountEntryConstants;
import com.liferay.osb.customer.admin.model.AccountEntry;
import com.liferay.osb.customer.admin.service.AccountEntryLocalService;
import com.liferay.osb.customer.constants.OSBCustomerConstants;
import com.liferay.osb.customer.koroneiki.util.AccountReader;
import com.liferay.osb.customer.koroneiki.web.service.AccountWebService;
import com.liferay.osb.customer.koroneiki.web.service.ProductPurchaseViewWebService;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
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

import java.text.Format;

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
 * @author Yuanyuan Huang
 */
@Component(
	immediate = true,
	service = SyncNewActiveProductPurchaseMessageListener.class
)
public class SyncNewActiveProductPurchaseMessageListener
	extends BaseMessageListener {

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_lastSyncDate = new Date();
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
		Date now = new Date();

		StringBundler sb = new StringBundler(4);

		sb.append("supportLifeStartDate ge ");
		sb.append(_dateFormat.format(_lastSyncDate));
		sb.append(" and supportLifeStartDate le ");
		sb.append(_dateFormat.format(now));

		List<ProductPurchaseView> productPurchaseViews =
			_productPurchaseViewWebService.getProductPurchaseViews(
				StringPool.BLANK, sb.toString(), 1, 1000, StringPool.BLANK);

		Set<String> accountKeys = new HashSet<>();

		for (ProductPurchaseView productPurchaseView : productPurchaseViews) {
			ProductPurchase[] productPurchases =
				productPurchaseView.getProductPurchases();

			ProductPurchase productPurchase = productPurchases[0];

			if (accountKeys.contains(productPurchase.getAccountKey())) {
				continue;
			}

			accountKeys.add(productPurchase.getAccountKey());

			Account account = _accountWebService.getAccount(
				productPurchase.getAccountKey());

			AccountEntry accountEntry =
				_accountEntryLocalService.fetchKoroneikiAccountEntry(
					productPurchase.getAccountKey());

			List<ProductPurchase> curProductPurchases =
				_accountReader.getProductPurchases(
					productPurchase.getAccountKey());

			if (accountEntry == null) {
				if (!_accountReader.isSyncAccount(curProductPurchases)) {
					return;
				}

				_accountEntryLocalService.addAccountEntry(
					OSBCustomerConstants.USER_DEFAULT_USER_ID, account.getKey(),
					_accountReader.getDossieraAccountKey(
						account.getExternalLinks()),
					_accountReader.getCorpProjectUuid(
						account.getExternalLinks()),
					_accountReader.getCorpProjectId(account.getExternalLinks()),
					account.getName(), account.getCode(), null,
					_accountReader.getSupportEndDate(curProductPurchases),
					_accountReader.getTicketSupportEndDate(curProductPurchases),
					_accountReader.getStatus(account),
					new String[] {
						AccountEntryConstants.getLanguageId(
							account.getLanguage())
					});
			}
			else {
				_accountEntryLocalService.updateAccountEntry(
					accountEntry.getAccountEntryId(),
					_accountReader.getSupportEndDate(curProductPurchases),
					_accountReader.getTicketSupportEndDate(curProductPurchases),
					_accountReader.getStatus(account));
			}
		}

		_lastSyncDate = now;
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

	private Date _lastSyncDate;

	@Reference
	private ProductPurchaseViewWebService _productPurchaseViewWebService;

	@Reference
	private SchedulerEngineHelper _schedulerEngineHelper;

	@Reference
	private TriggerFactory _triggerFactory;

}