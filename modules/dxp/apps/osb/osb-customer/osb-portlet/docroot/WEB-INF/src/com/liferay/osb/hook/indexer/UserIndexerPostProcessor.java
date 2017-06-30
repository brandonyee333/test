/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.hook.indexer;

import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.osb.model.TrainingLinkedUser;
import com.liferay.osb.service.TrainingCustomerLocalServiceUtil;
import com.liferay.osb.service.TrainingLinkedUserLocalServiceUtil;
import com.liferay.portal.kernel.search.BaseIndexerPostProcessor;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.BooleanQueryFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Joan Kim
 */
public class UserIndexerPostProcessor extends BaseIndexerPostProcessor {

	@Override
	public void postProcessContextQuery(
			BooleanQuery contextQuery, SearchContext searchContext)
		throws Exception {

		String trainingCustomer = GetterUtil.getString(
			searchContext.getAttribute("trainingCustomer"));

		if (Validator.isNotNull(trainingCustomer)) {
			contextQuery.addRequiredTerm("trainingCustomer", trainingCustomer);
		}

		BooleanQuery booleanQuery = BooleanQueryFactoryUtil.create(
			searchContext);

		String trainingCustomerEmailAddress = GetterUtil.getString(
			searchContext.getAttribute("trainingCustomerEmailAddress"));

		if (Validator.isNotNull(trainingCustomerEmailAddress)) {
			if (searchContext.isAndSearch()) {
				booleanQuery.addRequiredTerm(
					"trainingCustomerEmailAddress",
					trainingCustomerEmailAddress, true);
			}
			else {
				booleanQuery.addTerm(
					"trainingCustomerEmailAddress",
					trainingCustomerEmailAddress, true);
			}
		}

		String trainingCustomerFirstName = GetterUtil.getString(
			searchContext.getAttribute("trainingCustomerFirstName"));

		if (Validator.isNotNull(trainingCustomerFirstName)) {
			if (searchContext.isAndSearch()) {
				booleanQuery.addRequiredTerm(
					"trainingCustomerFirstName", trainingCustomerFirstName,
					true);
			}
			else {
				booleanQuery.addTerm(
					"trainingCustomerFirstName", trainingCustomerFirstName,
					true);
			}
		}

		String trainingCustomerLastName = GetterUtil.getString(
			searchContext.getAttribute("trainingCustomerLastName"));

		if (Validator.isNotNull(trainingCustomerLastName)) {
			if (searchContext.isAndSearch()) {
				booleanQuery.addRequiredTerm(
					"trainingCustomerLastName", trainingCustomerLastName, true);
			}
			else {
				booleanQuery.addTerm(
					"trainingCustomerLastName", trainingCustomerLastName, true);
			}
		}

		if (Validator.isNull(booleanQuery.toString())) {
			return;
		}

		contextQuery.add(booleanQuery, BooleanClauseOccur.MUST);
	}

	@Override
	public void postProcessDocument(Document document, Object object)
		throws Exception {

		User user = (User)object;

		int trainingCustomersCount =
			TrainingCustomerLocalServiceUtil.getUserTrainingCustomersCount(
				user.getUserId());

		TrainingLinkedUser trainingLinkedUser =
			TrainingLinkedUserLocalServiceUtil.fetchUserTrainingLinkedUser(
				user.getUserId());

		if ((trainingCustomersCount <= 0) && (trainingLinkedUser == null)) {
			document.addKeyword("trainingCustomer", false);

			return;
		}

		if ((trainingLinkedUser != null) &&
			(trainingLinkedUser.getPrimaryUserId() != user.getUserId())) {

			document.addKeyword("trainingCustomer", false);

			return;
		}

		document.addKeyword("trainingCustomer", true);

		List<TrainingLinkedUser> trainingLinkedUsers =
			TrainingLinkedUserLocalServiceUtil.getTrainingLinkedUsers(
				user.getUserId());

		Set<String> trainingCustomerEmailAddress = new HashSet<String>(
			trainingLinkedUsers.size() + 1);
		Set<String> trainingCustomerFirstName = new HashSet<String>(
			trainingLinkedUsers.size() + 1);
		Set<String> trainingCustomerLastName = new HashSet<String>(
			trainingLinkedUsers.size() + 1);

		trainingCustomerEmailAddress.add(user.getEmailAddress());
		trainingCustomerFirstName.add(user.getFirstName());
		trainingCustomerLastName.add(user.getLastName());

		for (TrainingLinkedUser curTrainingLinkedUser : trainingLinkedUsers) {
			User trainingLinkedUserUser = UserLocalServiceUtil.getUser(
				curTrainingLinkedUser.getUserId());

			trainingCustomerEmailAddress.add(
				trainingLinkedUserUser.getEmailAddress());
			trainingCustomerFirstName.add(
				trainingLinkedUserUser.getFirstName());
			trainingCustomerLastName.add(trainingLinkedUserUser.getLastName());
		}

		document.addText(
			"trainingCustomerEmailAddress",
			trainingCustomerEmailAddress.toArray(
				new String[trainingCustomerEmailAddress.size()]));
		document.addText(
			"trainingCustomerFirstName",
			trainingCustomerFirstName.toArray(
				new String[trainingCustomerFirstName.size()]));
		document.addText(
			"trainingCustomerLastName",
			trainingCustomerLastName.toArray(
				new String[trainingCustomerLastName.size()]));
	}

	@Override
	public void postProcessSearchQuery(
			BooleanQuery searchQuery, SearchContext searchContext)
		throws Exception {

		String keywords = searchContext.getKeywords();

		if (Validator.isNull(keywords)) {
			return;
		}

		searchQuery.addTerm("trainingCustomerEmailAddress", keywords, true);
		searchQuery.addTerm("trainingCustomerFirstName", keywords, true);
		searchQuery.addTerm("trainingCustomerLastName", keywords, true);
	}

}