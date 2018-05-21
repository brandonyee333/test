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

package com.liferay.osb.hook.upgrade.v3_3_0;

import com.liferay.message.boards.kernel.model.MBDiscussion;
import com.liferay.message.boards.kernel.service.MBDiscussionLocalServiceUtil;
import com.liferay.message.boards.kernel.service.MBMessageLocalServiceUtil;
import com.liferay.message.boards.kernel.service.MBThreadLocalServiceUtil;
import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Layout;

import java.util.List;

/**
 * @author Amos Fong
 */
public class Upgrade_20150617144546039_ObsoleteData extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20150617144546039L;
	}

	protected void deleteLayoutMBDiscussions() throws PortalException {
		List<MBDiscussion> mbDiscussions =
			MBMessageLocalServiceUtil.getDiscussions(Layout.class.getName());

		for (MBDiscussion mbDiscussion : mbDiscussions) {
			MBThreadLocalServiceUtil.deleteThread(mbDiscussion.getThreadId());

			MBDiscussionLocalServiceUtil.deleteMBDiscussion(mbDiscussion);
		}
	}

	protected void deletePortletPreferences(String portletId) throws Exception {
		runSQL(
			"delete from PortletPreferences where portletId = '" + portletId +
				"'");
	}

	protected void deleteResourceActions(String portletId) throws Exception {
		runSQL("delete from ResourceAction where name = '" + portletId + "'");
	}

	protected void deleteResourcePermissions(String portletId)
		throws Exception {

		runSQL(
			"delete from ResourcePermission where name = '" + portletId + "'");
	}

	@Override
	protected void doUpgrade() throws Exception {
		dropObsoleteTables();

		runSQL(
			"delete from Ticket where expirationDate < '2015-06-01 00:00:00'");

		deletePortletPreferences("1_WAR_wolportlet");
		deletePortletPreferences("2_WAR_wolportlet");
		deletePortletPreferences("3_WAR_wolportlet");
		deletePortletPreferences("4_WAR_wolportlet");
		deletePortletPreferences("5_WAR_wolportlet");
		deletePortletPreferences("6_WAR_wolportlet");
		deletePortletPreferences("7_WAR_wolportlet");
		deletePortletPreferences("8_WAR_wolportlet");
		deletePortletPreferences("9_WAR_wolportlet");
		deletePortletPreferences("10_WAR_wolportlet");
		deletePortletPreferences("1_WAR_eloquaportlet");

		deleteResourceActions("1_WAR_eloquaportlet");
		deleteResourceActions("1_WAR_twitterportlet");
		deleteResourceActions("1_WAR_wolportlet");
		deleteResourceActions("2_WAR_wolportlet");
		deleteResourceActions("3_WAR_wolportlet");
		deleteResourceActions("4_WAR_wolportlet");
		deleteResourceActions("5_WAR_wolportlet");
		deleteResourceActions("6_WAR_wolportlet");
		deleteResourceActions("7_WAR_wolportlet");
		deleteResourceActions("8_WAR_wolportlet");
		deleteResourceActions("9_WAR_wolportlet");
		deleteResourceActions("10_WAR_wolportlet");
		deleteResourceActions("com.liferay.portlet.tasks.model.TasksProposal");
		deleteResourceActions("my_summary_portlet_WAR_mysummaryportlet");

		/*Run in scripting console because it takes too long

		deleteLayoutMBDiscussions();

		deleteResourcePermissions("1_WAR_wolportlet");
		deleteResourcePermissions("2_WAR_wolportlet");
		deleteResourcePermissions("3_WAR_wolportlet");
		deleteResourcePermissions("4_WAR_wolportlet");
		deleteResourcePermissions("5_WAR_wolportlet");
		deleteResourcePermissions("6_WAR_wolportlet");
		deleteResourcePermissions("7_WAR_wolportlet");
		deleteResourcePermissions("8_WAR_wolportlet");
		deleteResourcePermissions("9_WAR_wolportlet");
		deleteResourcePermissions("10_WAR_wolportlet");
		deleteResourcePermissions("1_WAR_eloquaportlet");
		*/
	}

	protected void dropObsoleteTables() throws Exception {
		if (!hasTable("AI_AssetLink")) {
			return;
		}

		runSQL("drop table AI_AssetLink");
		runSQL("drop table SocialEquityAssetEntry");
		runSQL("drop table SocialEquityGroupSetting");
		runSQL("drop table SocialEquityHistory");
		runSQL("drop table SocialEquityLog");
		runSQL("drop table SocialEquitySetting");
		runSQL("drop table SocialEquityUser");
		runSQL("drop table TagsAsset");
		runSQL("drop table TagsAssets_TagsEntries");
		runSQL("drop table TagsEntry");
		runSQL("drop table TagsProperty");
		runSQL("drop table TagsSource");
		runSQL("drop table TagsVocabulary");
		runSQL("drop table TasksProposal");
		runSQL("drop table TasksReview");
		runSQL("drop table Twitter_Feed");
		runSQL("drop table WOL_MeetupsEntry");
		runSQL("drop table WOL_MeetupsRegistration");
		runSQL("drop table WSRPConfiguredProducer");
		runSQL("drop table WSRPConsumerRegistration");
		runSQL("drop table WSRPPortlet");
		runSQL("drop table WSRPProducer");
	}

}