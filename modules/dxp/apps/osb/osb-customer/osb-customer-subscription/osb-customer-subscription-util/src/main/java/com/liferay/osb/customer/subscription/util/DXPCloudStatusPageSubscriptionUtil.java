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

package com.liferay.osb.customer.subscription.util;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.osb.customer.admin.model.ProductEntry;
import com.liferay.osb.customer.admin.service.ProductEntryLocalService;
import com.liferay.osb.customer.identity.management.provider.UserIdentityProvider;
import com.liferay.osb.customer.koroneiki.web.service.AccountWebService;
import com.liferay.osb.customer.koroneiki.web.service.ContactWebService;
import com.liferay.osb.customer.koroneiki.web.service.ProductPurchaseWebService;
import com.liferay.osb.customer.service.DXPCloudStatusPageWebService;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
@Component(immediate = true, service = DXPCloudStatusPageSubscriptionUtil.class)
public class DXPCloudStatusPageSubscriptionUtil {

	public boolean hasActiveDXPCloud(String koroneikiAccountKey)
		throws Exception {

		StringBundler sb = new StringBundler(3);

		sb.append("accountKey eq '");
		sb.append(koroneikiAccountKey);
		sb.append("' and state eq 'active'");

		List<ProductPurchase> productPurchases =
			_productPurchaseWebService.search(sb.toString(), 1, 1000);

		for (ProductPurchase productPurchase : productPurchases) {
			ProductEntry productEntry =
				_productEntryLocalService.fetchProductEntryByKoroneikiKey(
					productPurchase.getProductKey());

			if (productEntry == null) {
				continue;
			}

			if (productEntry.isDXPCloud()) {
				return true;
			}
		}

		return false;
	}

	public void subscribe(User user) throws Exception {
		_subscribe(user, _getSubscribers());
	}

	public void syncAccount(String koroneikiAccountKey) throws Exception {
		boolean hasActiveDXPCloud = hasActiveDXPCloud(koroneikiAccountKey);

		Map<String, String> subscribers = _getSubscribers();

		List<Contact> contacts = _contactWebService.getAccountCustomerContacts(
			koroneikiAccountKey, 1, 1000);

		for (Contact contact : contacts) {
			User user = _userIdentityProvider.fetchUserByEmailAddress(
				contact.getEmailAddress());

			if (user == null) {
				continue;
			}

			if (hasActiveDXPCloud) {
				_subscribe(user, subscribers);
			}
			else {
				_unsubscribe(user, subscribers);
			}
		}
	}

	public void unsubscribe(User user) throws Exception {
		_unsubscribe(user, _getSubscribers());
	}

	private Map<String, String> _getSubscribers() throws Exception {
		JSONArray jsonArray = _dxpCloudStatusPagesWebService.getSubscribers();

		Map<String, String> subscribers = new HashMap<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject subscriberJSONObject = jsonArray.getJSONObject(i);

			String emailAddress = subscriberJSONObject.getString("email");
			String id = subscriberJSONObject.getString("id");

			subscribers.put(emailAddress, id);
		}

		return subscribers;
	}

	private void _subscribe(User user, Map<String, String> subscribers)
		throws Exception {

		if (subscribers.containsKey(user.getEmailAddress())) {
			return;
		}

		ExpandoBridge expandoBridge = user.getExpandoBridge();

		boolean osbDXPCloudSubscription = GetterUtil.getBoolean(
			expandoBridge.getAttribute("osbDXPCloudSubscription", false));

		if (!osbDXPCloudSubscription) {
			_dxpCloudStatusPagesWebService.postSubscriber(
				user.getEmailAddress());

			expandoBridge.setAttribute("osbDXPCloudSubscription", true, false);
		}
	}

	private void _unsubscribe(User user, Map<String, String> subscribers)
		throws Exception {

		String subscriberId = subscribers.get(user.getEmailAddress());

		if (Validator.isNull(subscriberId)) {
			return;
		}

		List<Account> accounts = _accountWebService.search(
			StringPool.BLANK,
			"customerContactEmailAddresses/any(s:s eq '" +
				user.getEmailAddress() + "')",
			1, 1000, StringPool.BLANK);

		for (Account account : accounts) {
			if (hasActiveDXPCloud(account.getKey())) {
				return;
			}
		}

		ExpandoBridge expandoBridge = user.getExpandoBridge();

		expandoBridge.setAttribute("osbDXPCloudSubscription", false, false);

		_dxpCloudStatusPagesWebService.deleteSubscriber(subscriberId);
	}

	@Reference
	private AccountWebService _accountWebService;

	@Reference
	private ContactWebService _contactWebService;

	@Reference
	private DXPCloudStatusPageWebService _dxpCloudStatusPagesWebService;

	@Reference
	private ProductEntryLocalService _productEntryLocalService;

	@Reference
	private ProductPurchaseWebService _productPurchaseWebService;

	@Reference(target = "(provider=okta)")
	private UserIdentityProvider _userIdentityProvider;

}