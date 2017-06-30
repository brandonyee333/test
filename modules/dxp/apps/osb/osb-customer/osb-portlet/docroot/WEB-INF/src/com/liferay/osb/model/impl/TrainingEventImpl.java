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

package com.liferay.osb.model.impl;

import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.osb.model.PartnerEntry;
import com.liferay.osb.model.TrainingCourse;
import com.liferay.osb.model.TrainingCustomer;
import com.liferay.osb.model.TrainingCustomerConstants;
import com.liferay.osb.model.TrainingEvent;
import com.liferay.osb.model.TrainingEventConstants;
import com.liferay.osb.model.TrainingLocation;
import com.liferay.osb.model.TrainingWorker;
import com.liferay.osb.model.UserProfileHistory;
import com.liferay.osb.service.PartnerEntryLocalServiceUtil;
import com.liferay.osb.service.TrainingCourseLocalServiceUtil;
import com.liferay.osb.service.TrainingCustomerLocalServiceUtil;
import com.liferay.osb.service.TrainingLocationLocalServiceUtil;
import com.liferay.osb.service.TrainingWorkerLocalServiceUtil;
import com.liferay.osb.service.UserProfileHistoryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.CalendarUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Address;
import com.liferay.portal.model.Country;
import com.liferay.portal.service.AddressLocalServiceUtil;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class TrainingEventImpl extends TrainingEventBaseImpl {

	public TrainingEventImpl() {
	}

	public Address getAddress() throws PortalException, SystemException {
		return AddressLocalServiceUtil.getAddress(getAddressId());
	}

	public String getAddressDisplayHTML()
		throws PortalException, SystemException {

		StringBundler sb = new StringBundler(3);

		Address address = getAddress();

		sb.append(address.getCity());

		if (address.getCountryId() > 0) {
			sb.append(StringPool.COMMA_AND_SPACE);

			Country country = address.getCountry();

			sb.append(country.getName());
		}

		return HtmlUtil.escape(sb.toString());
	}

	public String getCertificateCountDisplayHTML()
		throws PortalException, SystemException {

		long certificateCount =
			TrainingCustomerLocalServiceUtil.getTrainingCustomersCount(
				PortalUtil.getClassNameId(TrainingEvent.class),
				getTrainingEventId(),
				new int[] {TrainingCustomerConstants.STATUS_CERTIFIED});

		long trainingCustomersCount =
			TrainingCustomerLocalServiceUtil.getTrainingCustomersCount(
				PortalUtil.getClassNameId(TrainingEvent.class),
				getTrainingEventId(),
				new int[] {
					TrainingCustomerConstants.
						STATUS_PENDING_PARTICIPANT_FORM_COMPLETION,
					TrainingCustomerConstants.STATUS_CERTIFIED
				});

		return HtmlUtil.escape(
			certificateCount + " out of " + trainingCustomersCount);
	}

	public PartnerEntry getPartnerEntry()
		throws PortalException, SystemException {

		if (getPartnerEntryId() == 0) {
			return null;
		}

		return PartnerEntryLocalServiceUtil.getPartnerEntry(
			getPartnerEntryId());
	}

	public TrainingCourse getTrainingCourse()
		throws PortalException, SystemException {

		return TrainingCourseLocalServiceUtil.getTrainingCourse(
			getTrainingCourseId());
	}

	public List<TrainingCustomer> getTrainingCustomers()
		throws SystemException {

		return TrainingCustomerLocalServiceUtil.getClassTrainingCustomers(
			PortalUtil.getClassNameId(TrainingEvent.class),
			getTrainingEventId());
	}

	public TrainingLocation getTrainingLocation()
		throws PortalException, SystemException {

		return TrainingLocationLocalServiceUtil.getTrainingLocation(
			getTrainingLocationId());
	}

	public String getTrainingWorkerDisplayHTML()
		throws PortalException, SystemException {

		String trainingWorkerDisplayHTML = StringPool.BLANK;

		List<TrainingWorker> trainingWorkers =
			TrainingWorkerLocalServiceUtil.getTrainingWorkers(
				PortalUtil.getClassNameId(TrainingEvent.class),
				getTrainingEventId());

		if (!trainingWorkers.isEmpty()) {
			TrainingWorker trainingWorker = trainingWorkers.get(0);

			UserProfileHistory userProfileHistory =
				UserProfileHistoryLocalServiceUtil.getUserProfileHistory(
					trainingWorker.getUserProfileHistoryId());

			trainingWorkerDisplayHTML = HtmlUtil.escape(
				userProfileHistory.getFullName());
		}

		return trainingWorkerDisplayHTML;
	}

	public String getTrainingWorkersDisplayHTML()
		throws PortalException, SystemException {

		List<TrainingWorker> trainingWorkers =
			TrainingWorkerLocalServiceUtil.getTrainingWorkers(
				PortalUtil.getClassNameId(TrainingEvent.class),
				getTrainingEventId());

		String trainingWorkersDisplayHTML = StringPool.BLANK;

		if (!trainingWorkers.isEmpty()) {
			for (TrainingWorker trainingWorker : trainingWorkers) {
				UserProfileHistory trainingWorkerUserProfileHistory =
					UserProfileHistoryLocalServiceUtil.getUserProfileHistory(
						trainingWorker.getUserProfileHistoryId());

				trainingWorkersDisplayHTML = StringUtil.add(
					trainingWorkersDisplayHTML,
					trainingWorkerUserProfileHistory.getFullName(),
					StringPool.COMMA_AND_SPACE);
			}

			trainingWorkersDisplayHTML = trainingWorkersDisplayHTML.substring(
				0, trainingWorkersDisplayHTML.length() - 2);
		}

		return trainingWorkersDisplayHTML;
	}

	public String getTypeLabel() {
		return TrainingEventConstants.getTypeLabel(getType());
	}

	public boolean isDurationSingleDay() {
		return CalendarUtil.equalsByDay(getStartDate(), getEndDate());
	}

}