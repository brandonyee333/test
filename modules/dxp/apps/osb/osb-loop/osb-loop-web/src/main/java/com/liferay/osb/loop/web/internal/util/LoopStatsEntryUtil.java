/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.web.internal.util;

import com.liferay.alloy.mvc.AlloyController;
import com.liferay.alloy.mvc.AlloyServiceInvoker;
import com.liferay.osb.loop.asset.entry.set.model.AssetEntrySet;
import com.liferay.osb.loop.asset.sharing.model.AssetSharingEntry;
import com.liferay.osb.loop.asset.sharing.service.AssetSharingEntryLocalServiceUtil;
import com.liferay.osb.loop.model.LoopStatsEntry;
import com.liferay.osb.loop.model.LoopTopic;
import com.liferay.osb.loop.service.LoopStatsEntryLocalServiceUtil;
import com.liferay.osb.loop.service.LoopTopicLocalServiceUtil;
import com.liferay.osb.loop.web.internal.configuration.LoopWebConfigurationValues;
import com.liferay.portal.kernel.util.PortalUtil;

import java.util.List;

/**
 * @author Timothy Bell
 */
public class LoopStatsEntryUtil {

	public static void deleteLoopStatsEntries(long classNameId, long classPK)
		throws Exception {

		List<LoopStatsEntry> loopStatsEntries =
			_alloyServiceInvoker.executeDynamicQuery(
				new Object[] {"classNameId", classNameId, "classPK", classPK});

		for (LoopStatsEntry loopStatsEntry : loopStatsEntries) {
			LoopStatsEntryLocalServiceUtil.deleteLoopStatsEntry(loopStatsEntry);
		}
	}

	public static double getScore(long classNameId, long classPK)
		throws Exception {

		List<LoopStatsEntry> loopStatsEntries =
			_alloyServiceInvoker.executeDynamicQuery(
				new Object[] {"classNameId", classNameId, "classPK", classPK});

		if (!loopStatsEntries.isEmpty()) {
			LoopStatsEntry loopStatsEntry = loopStatsEntries.get(0);

			return loopStatsEntry.getScore();
		}

		return 0;
	}

	public static void updateScore(
			AlloyController alloyController, AssetEntrySet assetEntrySet,
			double scoreChange)
		throws Exception {

		long assetEntrySetId = LoopAssetEntrySetUtil.getRootAssetEntrySetId(
			assetEntrySet);

		LoopStatsEntry assetEntrySetLoopStatsEntry = getLoopStatsEntry(
			alloyController, PortalUtil.getClassNameId(AssetEntrySet.class),
			assetEntrySetId);

		updateScore(alloyController, assetEntrySetLoopStatsEntry, scoreChange);

		List<AssetSharingEntry> assetSharingEntries =
			AssetSharingEntryLocalServiceUtil.getAssetSharingEntries(
				PortalUtil.getClassNameId(AssetEntrySet.class), assetEntrySetId,
				PortalUtil.getClassNameId(LoopTopic.class));

		for (AssetSharingEntry assetSharingEntry : assetSharingEntries) {
			LoopStatsEntry loopTopicLoopStatsEntry = getLoopStatsEntry(
				alloyController, PortalUtil.getClassNameId(LoopTopic.class),
				assetSharingEntry.getSharedToClassPK());

			updateScore(alloyController, loopTopicLoopStatsEntry, scoreChange);

			LoopTopic loopTopic = LoopTopicLocalServiceUtil.getLoopTopic(
				assetSharingEntry.getSharedToClassPK());

			if (loopTopic.getParentLoopTopicId() > 0) {
				continue;
			}

			alloyController.indexModel(loopTopic);
		}
	}

	protected static LoopStatsEntry getLoopStatsEntry(
			AlloyController alloyController, long classNameId, long classPK)
		throws Exception {

		List<LoopStatsEntry> loopStatsEntries =
			_alloyServiceInvoker.executeDynamicQuery(
				new Object[] {"classNameId", classNameId, "classPK", classPK});

		if (!loopStatsEntries.isEmpty()) {
			return loopStatsEntries.get(0);
		}

		LoopStatsEntry loopStatsEntry =
			LoopStatsEntryLocalServiceUtil.createLoopStatsEntry(0);

		alloyController.updateModelIgnoreRequest(
			loopStatsEntry, "classNameId", classNameId, "classPK", classPK);

		return loopStatsEntry;
	}

	protected static void updateScore(
			AlloyController alloyController, LoopStatsEntry loopStatsEntry,
			double scoreChange)
		throws Exception {

		double score = loopStatsEntry.getScore() + scoreChange;

		if (score <
				LoopWebConfigurationValues.
					LOOP_STATS_ENTRY_SCORE_DECAY_THRESHOLD) {

			LoopStatsEntryLocalServiceUtil.deleteLoopStatsEntry(loopStatsEntry);
		}
		else {
			alloyController.updateModelIgnoreRequest(
				loopStatsEntry, "modifiedTime", System.currentTimeMillis(),
				"score", score);
		}
	}

	private static final AlloyServiceInvoker _alloyServiceInvoker =
		new AlloyServiceInvoker(LoopStatsEntry.class.getName());

}