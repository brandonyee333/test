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

package com.liferay.osb.loop.web.internal.composite;

import com.liferay.osb.loop.asset.entry.set.model.AssetEntrySet;
import com.liferay.osb.loop.model.LoopPerson;
import com.liferay.osb.loop.web.internal.constants.LoopUserNotificationConstants;
import com.liferay.osb.loop.web.internal.permission.LoopPermission;
import com.liferay.osb.loop.web.internal.util.AssetEntrySetUtil;
import com.liferay.osb.loop.web.internal.util.LoopFeaturedContentUtil;
import com.liferay.osb.loop.web.internal.util.LoopPersonUtil;
import com.liferay.osb.loop.web.internal.util.LoopStreamEntryUtil;
import com.liferay.osb.loop.web.internal.util.LoopUserNotificationSubscriptionUtil;
import com.liferay.osb.loop.web.internal.util.LoopUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;

/**
 * @author Timothy Bell
 */
public class AssetEntrySetComposite extends BaseLoopComposite {

	public AssetEntrySetComposite(
			ThemeDisplay themeDisplay, AssetEntrySet assetEntrySet)
		throws Exception {

		this(themeDisplay, assetEntrySet, 0);
	}

	public AssetEntrySetComposite(
			ThemeDisplay themeDisplay, AssetEntrySet assetEntrySet,
			long untruncatedAssetEntrySetId)
		throws Exception {

		super(themeDisplay);

		_assetEntrySet = assetEntrySet;
		_curLoopPerson = LoopPersonUtil.getLoopPerson(themeDisplay.getUserId());
		_untruncatedAssetEntrySetId = untruncatedAssetEntrySetId;
	}

	public long getAssetEntryId() {
		return _assetEntrySet.getAssetEntryId();
	}

	public long getAssetEntrySetId() {
		return _assetEntrySet.getAssetEntrySetId();
	}

	public int getAssetEntrySetLikesCount() {
		return _assetEntrySet.getAssetEntrySetLikesCount();
	}

	@Override
	public JSONObject getBaseJSONObject(JSONObject jsonObject)
		throws Exception {

		jsonObject = super.getBaseJSONObject(jsonObject);

		jsonObject.put("assetEntryId", getAssetEntryId());
		jsonObject.put("assetEntrySetId", getAssetEntrySetId());
		jsonObject.put("assetEntrySetLikesCount", getAssetEntrySetLikesCount());
		jsonObject.put(
			"childAssetEntrySets", getChildAssetEntrySetsJSONArray());
		jsonObject.put(
			"childAssetEntrySetsCount", getChildAssetEntrySetsCount());

		if (getAPIVersion() >= 5) {
			jsonObject.put("classNameId", getClassNameId());
			jsonObject.put("classPK", getClassPK());
		}

		jsonObject.put("companyId", getCompanyId());
		jsonObject.put("createTime", getCreateTime());
		jsonObject.put("creatorClassNameId", getCreatorClassNameId());
		jsonObject.put("creatorClassPK", getCreatorClassPK());
		jsonObject.put("creatorName", getCreatorName());
		jsonObject.put("displayURL", getDisplayURL());

		if ((getClassNameId() > 0) && (getClassPK() > 0)) {
			if (getAPIVersion() < 9) {
				jsonObject.put("foreignDisplayURL", getForeignDisplayURL());
			}

			jsonObject.put(
				"foreignEntity",
				LoopUtil.getCompositeJSONObject(
					themeDisplay, getClassNameId(), getClassPK(),
					StringPool.BLANK));
		}

		jsonObject.put("level", getLevel());
		jsonObject.put("modifiedTime", getModifiedTime());
		jsonObject.put("parentAssetEntrySetId", getParentAssetEntrySetId());
		jsonObject.put("payload", getPayload());
		jsonObject.put("permissionDelete", getPermissionDelete());
		jsonObject.put("permissionEdit", getPermissionEdit());
		jsonObject.put("permissionUpdate", getPermissionUpdate());
		jsonObject.put("privateAssetEntrySet", isPrivateAssetEntrySet());
		jsonObject.put("status", getStatus());
		jsonObject.put("stickyTime", getStickyTime());
		jsonObject.put("title", getTitle());
		jsonObject.put("type", getType());
		jsonObject.put("userId", getUserId());

		if (getLevel() == 0) {
			jsonObject.put("featured", isFeatured());
			jsonObject.put("following", isFollowing());
			jsonObject.put("notifying", isNotifying());
			jsonObject.put("notifyingEmail", isNotifyingEmail());
		}

		return jsonObject;
	}

	public int getChildAssetEntrySetsCount() {
		return _assetEntrySet.getChildAssetEntrySetsCount();
	}

	public JSONArray getChildAssetEntrySetsJSONArray() throws Exception {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (AssetEntrySet childAssetEntrySet :
				_assetEntrySet.getChildAssetEntrySets()) {

			jsonArray.put(
				AssetEntrySetUtil.getAssetEntrySetJSONObject(
					themeDisplay, childAssetEntrySet,
					_untruncatedAssetEntrySetId, 0, 0));
		}

		return jsonArray;
	}

	public long getClassNameId() {
		return _assetEntrySet.getClassNameId();
	}

	public long getClassPK() {
		return _assetEntrySet.getClassPK();
	}

	public long getCompanyId() {
		return _assetEntrySet.getCompanyId();
	}

	public long getCreateTime() {
		return _assetEntrySet.getCreateTime();
	}

	public long getCreatorClassNameId() {
		return _assetEntrySet.getCreatorClassNameId();
	}

	public long getCreatorClassPK() {
		return _assetEntrySet.getCreatorClassPK();
	}

	public String getCreatorName() {
		return _assetEntrySet.getCreatorName();
	}

	public String getDisplayURL() throws Exception {
		return LoopUtil.getDisplayURL(
			themeDisplay.getCompanyId(),
			PortalUtil.getClassNameId(AssetEntrySet.class),
			getAssetEntrySetId());
	}

	@Override
	public long getEntityClassNameId() {
		return PortalUtil.getClassNameId(AssetEntrySet.class);
	}

	@Override
	public long getEntityClassPK() {
		return getAssetEntrySetId();
	}

	public String getForeignDisplayURL() throws Exception {
		return LoopUtil.getDisplayURL(
			themeDisplay.getCompanyId(), getClassNameId(), getClassPK());
	}

	public int getLevel() {
		return _assetEntrySet.getLevel();
	}

	public long getModifiedTime() {
		return _assetEntrySet.getModifiedTime();
	}

	public long getParentAssetEntrySetId() {
		return _assetEntrySet.getParentAssetEntrySetId();
	}

	public String getPayload() {
		return _assetEntrySet.getPayload();
	}

	public boolean getPermissionDelete() {
		return LoopPermission.contains(themeDisplay, _assetEntrySet, "delete");
	}

	public boolean getPermissionEdit() {
		return LoopPermission.contains(themeDisplay, _assetEntrySet, "edit");
	}

	public boolean getPermissionUpdate() {
		return LoopPermission.contains(themeDisplay, _assetEntrySet, "update");
	}

	public int getStatus() {
		return _assetEntrySet.getStatus();
	}

	public long getStickyTime() {
		return _assetEntrySet.getStickyTime();
	}

	public String getTitle() {
		return _assetEntrySet.getTitle();
	}

	public int getType() {
		return _assetEntrySet.getType();
	}

	public long getUserId() {
		return _assetEntrySet.getUserId();
	}

	public boolean isFeatured() {
		return LoopFeaturedContentUtil.isFeatured(
			themeDisplay, "featuredAssetEntrySetIds", getAssetEntrySetId());
	}

	public boolean isFollowing() throws Exception {
		return LoopStreamEntryUtil.isFollowing(
			_curLoopPerson.getLoopPersonId(), getEntityClassNameId(),
			getAssetEntrySetId());
	}

	public boolean isNotifying() {
		return LoopUserNotificationSubscriptionUtil.isNotifying(
			_curLoopPerson.getLoopPersonId(), getEntityClassNameId(),
			getAssetEntrySetId(),
			LoopUserNotificationConstants.DELIVERY_TYPE_IN_APP);
	}

	public boolean isNotifyingEmail() {
		return LoopUserNotificationSubscriptionUtil.isNotifying(
			_curLoopPerson.getLoopPersonId(), getEntityClassNameId(),
			getAssetEntrySetId(),
			LoopUserNotificationConstants.DELIVERY_TYPE_EMAIL);
	}

	public boolean isPrivateAssetEntrySet() {
		return _assetEntrySet.isPrivateAssetEntrySet();
	}

	private AssetEntrySet _assetEntrySet;
	private LoopPerson _curLoopPerson;
	private long _untruncatedAssetEntrySetId;

}