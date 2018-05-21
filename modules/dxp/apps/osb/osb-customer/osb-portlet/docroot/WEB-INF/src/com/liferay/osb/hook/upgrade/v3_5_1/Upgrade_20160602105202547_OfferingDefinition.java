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

package com.liferay.osb.hook.upgrade.v3_5_1;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.OfferingDefinition;
import com.liferay.osb.service.OfferingBundleLocalServiceUtil;
import com.liferay.osb.service.OfferingDefinitionLocalServiceUtil;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Amos Fong
 */
public class Upgrade_20160602105202547_OfferingDefinition
	extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20160602105202547L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (!hasColumn("OSB_OfferingDefinition", "productDescription")) {
			runSQL(
				"alter table OSB_OfferingDefinition add column " +
					"productDescription VARCHAR(75)");

			if (hasIndex("OSB_OfferingDefinition", "IX_7E51FA0D")) {
				runSQL("drop index IX_7E51FA0D on OSB_OfferingDefinition");
			}

			StringBundler sb = new StringBundler(4);

			sb.append("create index IX_19CF8762 on OSB_OfferingDefinition ");
			sb.append("(productEntryId, productDescription, ");
			sb.append("supportResponseId, servers, unlimitedServers, ");
			sb.append("supportTickets, unlimitedSupportTickets)");

			runSQL(sb.toString());
		}

		removeDuplicates();
	}

	protected String getKey(OfferingDefinition offeringDefinition) {
		StringBundler sb = new StringBundler(9);

		sb.append(offeringDefinition.getProductEntryId());
		sb.append(StringPool.POUND);
		sb.append(offeringDefinition.getProductDescription());
		sb.append(StringPool.POUND);
		sb.append(offeringDefinition.getSupportResponseId());
		sb.append(StringPool.POUND);
		sb.append(offeringDefinition.getLicenses());
		sb.append(StringPool.POUND);
		sb.append(offeringDefinition.getSupportTickets());

		return sb.toString();
	}

	protected boolean isPreferred(
		OfferingDefinition offeringDefinition,
		OfferingDefinition offeringDefinition2) {

		/*

		if (offeringDefinition.getOfferingDefinitionId() ==
				OSBConstants.OFFERING_DEFINITION_TRIAL_ID) {

			return true;
		}*/

		int offeringBundleCount =
			OfferingBundleLocalServiceUtil.
				getOfferingDefinitionOfferingBundlesCount(
					offeringDefinition.getOfferingDefinitionId());

		if (offeringBundleCount > 0) {
			return true;
		}

		int offeringBundle2Count =
			OfferingBundleLocalServiceUtil.
				getOfferingDefinitionOfferingBundlesCount(
					offeringDefinition2.getOfferingDefinitionId());

		if (offeringBundle2Count > 0) {
			return false;
		}

		/*int offeringEntryCount =
			OfferingEntryLocalServiceUtil.
				getOfferingDefinitionOfferingEntriesCount(
					offeringDefinition.getOfferingDefinitionId());
		int offeringEntryCount2 =
			OfferingEntryLocalServiceUtil.
				getOfferingDefinitionOfferingEntriesCount(
					offeringDefinition2.getOfferingDefinitionId());

		if (offeringEntryCount > offeringEntryCount2) {
			return true;
		}*/

		return false;
	}

	protected void removeDuplicates() throws Exception {
		List<OfferingDefinition> offeringDefinitions =
			OfferingDefinitionLocalServiceUtil.getOfferingDefinitions(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (OfferingDefinition offeringDefinition : offeringDefinitions) {
			/*int servers = offeringDefinition.getServers();

			if (servers > 1) {
				List<OfferingEntry> offeringEntries =
					OfferingEntryLocalServiceUtil.
						getOfferingDefinitionOfferingEntries(
							offeringDefinition.getOfferingDefinitionId());

				for (OfferingEntry offeringEntry : offeringEntries) {
					offeringEntry.setQuantity(
						offeringEntry.getQuantity() * servers);

					OfferingEntryLocalServiceUtil.updateOfferingEntry(
						offeringEntry, false);
				}

				offeringDefinition.setServers(1);

				OfferingDefinitionLocalServiceUtil.updateOfferingDefinition(
					offeringDefinition, false);
			}*/

			String key = getKey(offeringDefinition);

			OfferingDefinition curOfferingDefinition =
				_offeringDefinitionsMap.get(key);

			if (curOfferingDefinition == null) {
				_offeringDefinitionsMap.put(key, offeringDefinition);
			}
			else if (isPreferred(offeringDefinition, curOfferingDefinition)) {
				_offeringDefinitionsMap.put(key, offeringDefinition);
			}
		}

		for (OfferingDefinition offeringDefinition : offeringDefinitions) {
			String key = getKey(offeringDefinition);

			OfferingDefinition curOfferingDefinition =
				_offeringDefinitionsMap.get(key);

			if (offeringDefinition.getOfferingDefinitionId() ==
					curOfferingDefinition.getOfferingDefinitionId()) {

				continue;
			}

			StringBundler sb = new StringBundler(5);

			sb.append("update OSB_LicenseKey set offeringDefinitionId = ");
			sb.append(curOfferingDefinition.getOfferingDefinitionId());
			sb.append(" where offeringDefinitionId = ");
			sb.append(offeringDefinition.getOfferingDefinitionId());

			runSQL(sb.toString());

			sb.setIndex(0);

			sb.append("update OSB_OfferingEntry set offeringDefinitionId = ");
			sb.append(curOfferingDefinition.getOfferingDefinitionId());
			sb.append(" where offeringDefinitionId = ");
			sb.append(offeringDefinition.getOfferingDefinitionId());

			runSQL(sb.toString());

			sb.setIndex(0);

			sb.setIndex(0);

			sb.append("update OSB_OfferingBundles_OfferingDefinitions set ");
			sb.append("offeringDefinitionId = ");
			sb.append(curOfferingDefinition.getOfferingDefinitionId());
			sb.append(" where offeringDefinitionId = ");
			sb.append(offeringDefinition.getOfferingDefinitionId());

			runSQL(sb.toString());

			CacheRegistryUtil.clear();

			try {
				OfferingDefinitionLocalServiceUtil.deleteOfferingDefinition(
					offeringDefinition.getOfferingDefinitionId());
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		Upgrade_20160602105202547_OfferingDefinition.class);

	private final Map<String, OfferingDefinition> _offeringDefinitionsMap =
		new HashMap<>();

}