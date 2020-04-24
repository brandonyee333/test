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

import com.liferay.alloy.mvc.AlloyServiceInvoker;
import com.liferay.asset.kernel.service.AssetTagLocalService;
import com.liferay.osb.loop.asset.entry.set.util.AssetEntrySetParticipantInfo;
import com.liferay.osb.loop.model.LoopDivision;
import com.liferay.osb.loop.model.LoopParticipantAssignment;
import com.liferay.osb.loop.model.LoopPerson;
import com.liferay.osb.loop.model.LoopTopic;
import com.liferay.osb.loop.service.LoopDivisionLocalService;
import com.liferay.osb.loop.service.LoopPersonLocalService;
import com.liferay.osb.loop.service.LoopTopicLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringPool;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Matthew Kong
 * @author Timothy Bell
 */
@Component(immediate = true, service = AssetEntrySetParticipantInfo.class)
public class LoopAssetEntrySetParticipantInfoImpl
	implements AssetEntrySetParticipantInfo {

	public JSONArray getAssetTagsJSONArray(long userId, String[] assetTagNames)
		throws PortalException {

		JSONArray jsonArray = _jsonFactory.createJSONArray();

		User user = _userLocalService.getUser(userId);

		Group group = _groupLocalService.getCompanyGroup(user.getCompanyId());

		_assetTagLocalService.checkTags(userId, group, assetTagNames);

		for (String assetTagName : assetTagNames) {
			List<LoopTopic> loopTopics = new ArrayList<>();

			AlloyServiceInvoker loopTopicAlloyServiceInvoker =
				new AlloyServiceInvoker(LoopTopic.class.getName());

			try {
				loopTopics = loopTopicAlloyServiceInvoker.executeDynamicQuery(
					new Object[] {
						"companyId", user.getCompanyId(), "name", assetTagName
					});
			}
			catch (Exception e) {
				throw new SystemException(e);
			}

			if (loopTopics.isEmpty()) {
				throw new SystemException(
					"Loop topic does not exist for name " + assetTagName);
			}

			LoopTopic loopTopic = loopTopics.get(0);

			JSONObject jsonObject = _jsonFactory.createJSONObject();

			jsonObject.put(
				"entityClassNameId", _portal.getClassNameId(LoopTopic.class));
			jsonObject.put("entityClassPK", loopTopic.getLoopTopicId());
			jsonObject.put("name", loopTopic.getName());

			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

	public ObjectValuePair<Long, Long> getClassNameIdAndClassPKOVP(
		long userId) {

		List<LoopPerson> loopPersons = new ArrayList<>();

		AlloyServiceInvoker loopPersonAlloyServiceInvoker =
			new AlloyServiceInvoker(LoopPerson.class.getName());

		try {
			loopPersons = loopPersonAlloyServiceInvoker.executeDynamicQuery(
				new Object[] {"personUserId", userId});
		}
		catch (Exception e) {
			throw new SystemException(e);
		}

		if (loopPersons.isEmpty()) {
			throw new SystemException(
				"Loop person does not exist for user " + userId);
		}

		LoopPerson loopPerson = loopPersons.get(0);

		return new ObjectValuePair<>(
			_portal.getClassNameId(LoopPerson.class),
			loopPerson.getLoopPersonId());
	}

	public String[] getMembershipSearchTerms(long userId) {
		AlloyServiceInvoker alloyServiceInvoker = new AlloyServiceInvoker(
			LoopParticipantAssignment.class.getName());

		try {
			LoopPerson loopPerson =
				_loopPersonLocalService.getLoopPersonByPersonUserId(userId);

			List<LoopParticipantAssignment> loopParticipantAssignments =
				alloyServiceInvoker.executeDynamicQuery(
					new Object[] {
						"loopPersonId", loopPerson.getLoopPersonId()
					});

			String[] membershipSearchTerms =
				new String[loopParticipantAssignments.size() + 1];

			for (int i = 0; i < loopParticipantAssignments.size(); i++) {
				LoopParticipantAssignment loopParticipantAssignment =
					loopParticipantAssignments.get(i);

				membershipSearchTerms[i] = getSearchTerm(
					_portal.getClassNameId(LoopDivision.class),
					loopParticipantAssignment.getLoopDivisionId());
			}

			membershipSearchTerms[loopParticipantAssignments.size()] =
				getSearchTerm(
					_portal.getClassNameId(LoopPerson.class),
					loopPerson.getLoopPersonId());

			return membershipSearchTerms;
		}
		catch (Exception e) {
			return new String[0];
		}
	}

	public String getParticipantName(long classNameId, long classPK)
		throws PortalException {

		if (classNameId == _portal.getClassNameId(LoopDivision.class)) {
			LoopDivision loopDivision =
				_loopDivisionLocalService.fetchLoopDivision(classPK);

			if (loopDivision == null) {
				return StringPool.BLANK;
			}

			Organization organization =
				_organizationLocalService.getOrganization(
					loopDivision.getOrganizationId());

			return organization.getName();
		}
		else if (classNameId == _portal.getClassNameId(LoopPerson.class)) {
			LoopPerson loopPerson = _loopPersonLocalService.fetchLoopPerson(
				classPK);

			if (loopPerson == null) {
				return StringPool.BLANK;
			}

			User user = _userLocalService.fetchUser(
				loopPerson.getPersonUserId());

			if (user == null) {
				return StringPool.BLANK;
			}

			return user.getFullName();
		}
		else if (classNameId == _portal.getClassNameId(LoopTopic.class)) {
			LoopTopic loopTopic = _loopTopicLocalService.fetchLoopTopic(
				classPK);

			if (loopTopic == null) {
				return StringPool.BLANK;
			}

			return loopTopic.getName();
		}

		return StringPool.BLANK;
	}

	public String getSearchTerm(long classNameId, long classPK) {
		return classNameId + StringPool.UNDERLINE + classPK;
	}

	public boolean isMember(
		long classNameId, long classPK, long sharedToClassNameId,
		long sharedToClassPK) {

		if ((sharedToClassNameId == classNameId) &&
			(classPK == sharedToClassPK)) {

			return true;
		}

		if ((classNameId == _portal.getClassNameId(LoopDivision.class)) &&
			(sharedToClassNameId == _portal.getClassNameId(
				LoopDivision.class)) &&
			isChildLoopDivision(classPK, sharedToClassPK)) {

			return true;
		}

		if ((classNameId == _portal.getClassNameId(LoopPerson.class)) &&
			(sharedToClassNameId == _portal.getClassNameId(
				LoopDivision.class))) {

			AlloyServiceInvoker alloyServiceInvoker = new AlloyServiceInvoker(
				LoopParticipantAssignment.class.getName());

			try {
				long loopParticipantAssignmentCount =
					alloyServiceInvoker.executeDynamicQueryCount(
						new Object[] {
							"loopDivisionId", sharedToClassPK, "loopPersonId",
							classPK
						});

				if (loopParticipantAssignmentCount > 0) {
					return true;
				}
			}
			catch (Exception e) {
			}
		}

		return false;
	}

	protected boolean isChildLoopDivision(
		long childLoopDivisionId, long parentLoopDivisionId) {

		try {
			if (childLoopDivisionId > 0) {
				LoopDivision childLoopDivision =
					_loopDivisionLocalService.getLoopDivision(
						childLoopDivisionId);

				if (childLoopDivision.getParentLoopDivisionId() ==
						parentLoopDivisionId) {

					return true;
				}

				return isChildLoopDivision(
					childLoopDivision.getParentLoopDivisionId(),
					parentLoopDivisionId);
			}
		}
		catch (Exception e) {
		}

		return false;
	}

	@Reference
	private AssetTagLocalService _assetTagLocalService;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private LoopDivisionLocalService _loopDivisionLocalService;

	@Reference
	private LoopPersonLocalService _loopPersonLocalService;

	@Reference
	private LoopTopicLocalService _loopTopicLocalService;

	@Reference
	private OrganizationLocalService _organizationLocalService;

	@Reference
	private Portal _portal;

	@Reference
	private UserLocalService _userLocalService;

}