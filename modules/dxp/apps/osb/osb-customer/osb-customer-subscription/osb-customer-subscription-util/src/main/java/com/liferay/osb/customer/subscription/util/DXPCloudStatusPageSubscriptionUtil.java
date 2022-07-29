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
import com.liferay.osb.customer.koroneiki.constants.ContactRoleConstants;
import com.liferay.osb.customer.koroneiki.constants.EntitlementConstants;
import com.liferay.osb.customer.koroneiki.web.service.AccountWebService;
import com.liferay.osb.customer.koroneiki.web.service.ContactRoleWebService;
import com.liferay.osb.customer.service.DXPCloudStatusPageWebService;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRole;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
@Component(immediate = true, service = DXPCloudStatusPageSubscriptionUtil.class)
public class DXPCloudStatusPageSubscriptionUtil {

	public Map<String, String> getSubscribers() throws Exception {
		return _getSubscribers();
	}

	public void subscribe(User user) throws Exception {
		_subscribe(user, _getSubscribers());
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

	private boolean _hasPartnerWorker(User user) throws Exception {
		for (String contactRoleName :
				ContactRoleConstants.PARTNER_CONTACT_ROLES) {

			ContactRole contactRole = _contactRoleWebService.fetchContactRole(
				ContactRole.Type.ACCOUNT_CUSTOMER.toString(), contactRoleName);

			if (contactRole == null) {
				continue;
			}

			StringBundler sb = new StringBundler(7);

			sb.append("contactUuidContactRoleKeys/any(s:s eq '");
			sb.append(user.getUuid());
			sb.append("_");
			sb.append(contactRole.getKey());
			sb.append("') and entitlements/any(s:s eq '");
			sb.append(EntitlementConstants.NAME_PARTNER);
			sb.append("')");

			long accountsCount = _accountWebService.searchCount(
				StringPool.BLANK, sb.toString());

			if (accountsCount > 0) {
				return true;
			}
		}

		return false;
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

		if (_hasPartnerWorker(user)) {
			return;
		}

		ExpandoBridge expandoBridge = user.getExpandoBridge();

		expandoBridge.setAttribute("osbDXPCloudSubscription", false, false);

		_dxpCloudStatusPagesWebService.deleteSubscriber(subscriberId);
	}

	@Reference
	private AccountWebService _accountWebService;

	@Reference
	private ContactRoleWebService _contactRoleWebService;

	@Reference
	private DXPCloudStatusPageWebService _dxpCloudStatusPagesWebService;

}