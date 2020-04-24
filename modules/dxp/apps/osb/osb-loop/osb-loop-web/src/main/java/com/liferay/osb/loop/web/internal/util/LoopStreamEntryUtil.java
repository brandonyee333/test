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
import com.liferay.osb.loop.model.LoopPerson;
import com.liferay.osb.loop.model.LoopStreamEntry;
import com.liferay.osb.loop.model.LoopTopic;
import com.liferay.osb.loop.model.impl.LoopStreamEntryModelImpl;
import com.liferay.osb.loop.service.LoopPersonLocalServiceUtil;
import com.liferay.osb.loop.service.LoopStreamEntryLocalServiceUtil;
import com.liferay.osb.loop.service.LoopTopicLocalServiceUtil;
import com.liferay.osb.loop.web.internal.composite.LoopPersonComposite;
import com.liferay.osb.loop.web.internal.constants.LoopStreamConstants;
import com.liferay.osb.loop.web.internal.constants.LoopStreamEntryConstants;
import com.liferay.osb.loop.web.internal.constants.LoopUserNotificationConstants;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;

import java.util.List;
import java.util.Map;

/**
 * @author Timothy Bell
 */
public class LoopStreamEntryUtil {

	public static void deleteLoopStreamEntries(
			AlloyController alloyController, long classNameId, long classPK)
		throws Exception {

		List<LoopStreamEntry> loopStreamEntries =
			_alloyServiceInvoker.executeDynamicQuery(
				new Object[] {"classNameId", classNameId, "classPK", classPK});

		deleteLoopStreamEntries(
			alloyController, loopStreamEntries, classNameId, classPK);
	}

	public static void deleteLoopStreamEntries(
			AlloyController alloyController, long loopPersonId,
			long loopStreamId, long classNameId, long classPK)
		throws Exception {

		List<LoopStreamEntry> loopStreamEntries =
			_alloyServiceInvoker.executeDynamicQuery(
				new Object[] {
					"loopPersonId", loopPersonId, "loopStreamId", loopStreamId,
					"classNameId", classNameId, "classPK", classPK
				});

		deleteLoopStreamEntries(
			alloyController, loopStreamEntries, classNameId, classPK);
	}

	public static LoopStreamEntry fetchLoopStreamEntry(
			long loopPersonId, long loopStreamId, long classNameId,
			long classPK)
		throws Exception {

		List<LoopStreamEntry> loopStreamEntries =
			_alloyServiceInvoker.executeDynamicQuery(
				new Object[] {
					"loopPersonId", loopPersonId, "loopStreamId", loopStreamId,
					"classNameId", classNameId, "classPK", classPK
				});

		if (!loopStreamEntries.isEmpty()) {
			return loopStreamEntries.get(0);
		}

		return null;
	}

	public static List<LoopPersonComposite> getFollowers(
			ThemeDisplay themeDisplay, long classNameId, long classPK,
			int start, int end)
		throws Exception {

		Map<String, Map<String, Object[]>> whereConditions =
			LoopSQLUtil.createWhereConditions(
				LoopStreamEntryModelImpl.TABLE_NAME, "loopStreamId",
				LoopStreamConstants.LOOP_STREAM_ID_FOLLOWING, "classNameId",
				classNameId, "classPK", classPK, "following", Boolean.TRUE);

		return LoopPersonUtil.getLoopPersonComposites(
			themeDisplay, LoopStreamEntryModelImpl.TABLE_NAME, "loopPersonId",
			whereConditions, start, end);
	}

	public static int getFollowersCount(long classNameId, long classPK)
		throws Exception {

		Map<String, Map<String, Object[]>> whereConditions =
			LoopSQLUtil.createWhereConditions(
				LoopStreamEntryModelImpl.TABLE_NAME, "loopStreamId",
				LoopStreamConstants.LOOP_STREAM_ID_FOLLOWING, "classNameId",
				classNameId, "classPK", classPK, "following", Boolean.TRUE);

		return LoopPersonUtil.getLoopPersonCount(
			LoopStreamEntryModelImpl.TABLE_NAME, "loopPersonId",
			whereConditions);
	}

	public static int getFollowingType(
			long loopPersonId, long classNameId, long classPK)
		throws Exception {

		List<LoopStreamEntry> loopStreamEntries =
			_alloyServiceInvoker.executeDynamicQuery(
				new Object[] {
					"loopPersonId", loopPersonId, "loopStreamId",
					LoopStreamConstants.LOOP_STREAM_ID_FOLLOWING, "classNameId",
					classNameId, "classPK", classPK
				});

		if (!loopStreamEntries.isEmpty()) {
			LoopStreamEntry loopStreamEntry = loopStreamEntries.get(0);

			return loopStreamEntry.getFollowingType();
		}

		return LoopStreamEntryConstants.TYPE_UNFOLLOWING;
	}

	public static List<LoopStreamEntry> getLoopStreamEntries(
			long loopPersonId, long loopStreamId)
		throws Exception {

		return _alloyServiceInvoker.executeDynamicQuery(
			new Object[] {
				"loopPersonId", loopPersonId, "loopStreamId", loopStreamId
			});
	}

	public static List<LoopStreamEntry> getLoopStreamEntries(
			long loopPersonId, long loopStreamId, boolean following)
		throws Exception {

		return _alloyServiceInvoker.executeDynamicQuery(
			new Object[] {
				"loopPersonId", loopPersonId, "loopStreamId", loopStreamId,
				"following", following
			});
	}

	public static DynamicQuery getLoopStreamEntryDynamicQuery(
			long loopPersonId, long classNameId, boolean following)
		throws Exception {

		DynamicQuery loopStreamEntryDynamicQuery =
			_alloyServiceInvoker.buildDynamicQuery(
				new Object[] {
					"loopPersonId", loopPersonId, "classNameId", classNameId,
					"following", following
				});

		Projection classPKProjection = ProjectionFactoryUtil.property(
			"classPK");

		loopStreamEntryDynamicQuery.setProjection(
			ProjectionFactoryUtil.distinct(classPKProjection));

		return loopStreamEntryDynamicQuery;
	}

	public static boolean isFollowing(
			long loopPersonId, long classNameId, long classPK)
		throws Exception {

		if (classNameId == PortalUtil.getClassNameId(AssetEntrySet.class)) {
			List<LoopStreamEntry> loopStreamEntries =
				_alloyServiceInvoker.executeDynamicQuery(
					new Object[] {
						"loopPersonId", loopPersonId, "loopStreamId",
						LoopStreamConstants.LOOP_STREAM_ID_FOLLOWING,
						"classNameId", classNameId, "classPK", classPK
					});

			if (!loopStreamEntries.isEmpty()) {
				LoopStreamEntry loopStreamEntry = loopStreamEntries.get(0);

				return loopStreamEntry.isFollowing();
			}

			return isIndirectlyFollowing(loopPersonId, classNameId, classPK);
		}

		long loopStreamEntriesCount =
			_alloyServiceInvoker.executeDynamicQueryCount(
				new Object[] {
					"loopPersonId", loopPersonId, "loopStreamId",
					LoopStreamConstants.LOOP_STREAM_ID_FOLLOWING, "classNameId",
					classNameId, "classPK", classPK, "following", Boolean.TRUE
				});

		if (loopStreamEntriesCount == 0) {
			return false;
		}

		return true;
	}

	public static void updateLoopStreamEntry(
			AlloyController alloyController, long loopPersonId,
			long loopStreamId, long classNameId, long classPK,
			boolean following, int followingType)
		throws Exception {

		LoopStreamEntry loopStreamEntry = fetchLoopStreamEntry(
			loopPersonId, loopStreamId, classNameId, classPK);

		if (loopStreamEntry == null) {
			loopStreamEntry =
				LoopStreamEntryLocalServiceUtil.createLoopStreamEntry(0);
		}

		if (followingType == LoopStreamEntryConstants.TYPE_UNFOLLOWING) {
			for (int deliveryType :
					LoopUserNotificationConstants.DELIVERY_TYPES) {

				LoopUserNotificationSubscriptionUtil.
					deleteLoopUserNotificationSubscription(
						loopPersonId, classNameId, classPK, deliveryType);
			}
		}
		else if (!loopStreamEntry.isFollowing()) {
			LoopPerson loopPerson = LoopPersonLocalServiceUtil.getLoopPerson(
				loopPersonId);

			LoopUserNotificationSubscriptionUtil.
				updateLoopUserNotificationSubscriptions(
					alloyController, loopPerson, classNameId, classPK,
					LoopUserNotificationConstants.SETTING_KEY_FOLLOWING);
		}

		alloyController.updateModelIgnoreRequest(
			loopStreamEntry, "loopPersonId", loopPersonId, "loopStreamId",
			loopStreamId, "classNameId", classNameId, "classPK", classPK,
			"following", following, "followingType", followingType);

		reindex(alloyController, classNameId, classPK);
	}

	protected static void deleteLoopStreamEntries(
			AlloyController alloyController,
			List<LoopStreamEntry> loopStreamEntries, long classNameId,
			long classPK)
		throws Exception {

		for (LoopStreamEntry loopStreamEntry : loopStreamEntries) {
			LoopStreamEntryLocalServiceUtil.deleteLoopStreamEntry(
				loopStreamEntry);
		}

		reindex(alloyController, classNameId, classPK);
	}

	protected static boolean isIndirectlyFollowing(
			long loopPersonId, long classNameId, long classPK)
		throws Exception {

		List<AssetSharingEntry> assetSharingEntries =
			AssetSharingEntryLocalServiceUtil.getAssetSharingEntries(
				classNameId, classPK);

		for (AssetSharingEntry assetSharingEntry : assetSharingEntries) {
			if (isFollowing(
					loopPersonId, assetSharingEntry.getSharedToClassNameId(),
					assetSharingEntry.getSharedToClassPK())) {

				return true;
			}

			if ((assetSharingEntry.getSharedToClassNameId() ==
					PortalUtil.getClassNameId(LoopPerson.class)) &&
				(assetSharingEntry.getSharedToClassPK() == loopPersonId)) {

				return true;
			}
		}

		return false;
	}

	protected static void reindex(
			AlloyController alloyController, long classNameId, long classPK)
		throws Exception {

		if (classNameId == PortalUtil.getClassNameId(LoopTopic.class)) {
			LoopTopic loopTopic = LoopTopicLocalServiceUtil.getLoopTopic(
				classPK);

			alloyController.indexModel(loopTopic);
		}
	}

	private static final AlloyServiceInvoker _alloyServiceInvoker =
		new AlloyServiceInvoker(LoopStreamEntry.class.getName());

}