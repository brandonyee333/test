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

package com.liferay.osb.customer.web.internal.upgrade.v1_0_1;

import com.liferay.journal.service.persistence.JournalArticlePersistence;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringBundler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrew Madrazo
 */
public class UpgradeJournalArticle extends UpgradeProcess {

	public UpgradeJournalArticle(
		JournalArticlePersistence journalArticlePersistence) {

		_journalArticlePersistence = journalArticlePersistence;
	}

	@Override
	protected void doUpgrade() throws Exception {
		List<String> oldDisclaimers = new ArrayList<>();
		List<String> newDisclaimers = new ArrayList<>();

		StringBundler sb = new StringBundler(7);

		sb.append("This article is part of Liferay''s Customer Portal ");
		sb.append("Knowledge fast-track publication program, which provides ");
		sb.append("a huge library of solutions that Liferay engineers have ");
		sb.append("created while supporting our customers. In order to ");
		sb.append("provide the the knowledge you need the instant it becomes ");
		sb.append("available, these articles may be presented in a raw and ");
		sb.append("unedited form.");

		oldDisclaimers.add(sb.toString());

		sb = new StringBundler(7);

		sb.append("This article is part of Liferay''s Customer Portal ");
		sb.append("Knowledge fast-track publication program, which provides ");
		sb.append("a huge library of solutions that Liferay engineers have ");
		sb.append("created while supporting our customers. In order to ");
		sb.append("provide the knowledge you need the instant it becomes ");
		sb.append("available, these articles may be presented in a raw and ");
		sb.append("unedited form.");

		newDisclaimers.add(sb.toString());

		sb = new StringBundler(6);

		sb.append("Important: Information about products not created by ");
		sb.append("Liferay is provided for informational purposes only and ");
		sb.append("does not constitute Liferay Support recommendation nor ");
		sb.append("endorsement. Liferay also does not assume any ");
		sb.append("responsibility for any instructions herein or referenced ");
		sb.append("regarding these products.");

		oldDisclaimers.add(sb.toString());

		sb = new StringBundler(5);

		sb.append("Liferay Support does not recommend or endorse specific ");
		sb.append("third-party products over others. Liferay is not ");
		sb.append("responsible for any instructions herein or referenced ");
		sb.append("regarding these products. Any implementation of these ");
		sb.append("principles is the responsibility of the subscriber.");

		newDisclaimers.add(sb.toString());

		sb = new StringBundler(10);

		sb.append("Important: According to Liferay Support''s policy, our ");
		sb.append("Support team will be limited in their ability to assist ");
		sb.append("inquiries regarding custom development. Since Support is ");
		sb.append("unable to examine custom code, and assist in the ");
		sb.append("development of customizations, our team will instead ");
		sb.append("focus their efforts on addressing any questions regarding ");
		sb.append("Liferay API. If there are questions regarding the ");
		sb.append("information discussed in this article and/or regarding ");
		sb.append("Liferay API, feel free to open a ticket through our ");
		sb.append("Support ticketing system.");

		oldDisclaimers.add(sb.toString());

		sb = new StringBundler(6);

		sb.append("Important: Liferay will support our API and resolve any ");
		sb.append("issues and answer any questions having to do with the API ");
		sb.append("itself or any other part of Liferay''s software. Issues ");
		sb.append("and questions regarding custom development may be handled ");
		sb.append("by our Global Services team or by the developer of those ");
		sb.append("customizations.");

		newDisclaimers.add(sb.toString());

		sb = new StringBundler(4);

		sb.append("This article is no longer actively maintained by Liferay. ");
		sb.append("We continue to make it available because the information ");
		sb.append("is still valuable, but some steps or details may vary due ");
		sb.append("to product changes.");

		oldDisclaimers.add(sb.toString());

		sb = new StringBundler(4);

		sb.append("This article is a <i>legacy article</i>. It applies to ");
		sb.append("previous versions of the Liferay product. While the ");
		sb.append("article is no longer maintained, the information may ");
		sb.append("still be applicable.");

		newDisclaimers.add(sb.toString());

		for (int i = 0; i < oldDisclaimers.size(); i++) {
			upgradeDisclaimer(oldDisclaimers.get(i), newDisclaimers.get(i));
		}

		_journalArticlePersistence.clearCache();
	}

	protected void upgradeDisclaimer(String oldDisclaimer, String newDisclaimer)
		throws Exception {

		runSQL(
			"update JournalArticle set content = REPLACE(content, '" +
				oldDisclaimer + "', '" + newDisclaimer + "')");
	}

	private final JournalArticlePersistence _journalArticlePersistence;

}