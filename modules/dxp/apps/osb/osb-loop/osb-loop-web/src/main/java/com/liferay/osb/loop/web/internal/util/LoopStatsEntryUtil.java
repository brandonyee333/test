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