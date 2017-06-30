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

package com.liferay.osb.service.impl;

import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.osb.RequiredTrainingLocationException;
import com.liferay.osb.TrainingLocationNameException;
import com.liferay.osb.model.TrainingLocation;
import com.liferay.osb.service.base.TrainingLocationLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.Address;
import com.liferay.portal.model.User;

import java.util.Date;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 */
public class TrainingLocationLocalServiceImpl
	extends TrainingLocationLocalServiceBaseImpl {

	public TrainingLocation addTrainingLocation(
			long userId, String name, String street1, String street2,
			String street3, String city, String zip, long regionId,
			long countryId)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		validate(name);

		long trainingLocationId = counterLocalService.increment();

		TrainingLocation trainingLocation = trainingLocationPersistence.create(
			trainingLocationId);

		trainingLocation.setUserId(user.getUserId());
		trainingLocation.setUserName(user.getFullName());
		trainingLocation.setCreateDate(now);
		trainingLocation.setModifiedDate(now);
		trainingLocation.setName(name);

		Address address = addressLocalService.addAddress(
			userId, TrainingLocation.class.getName(), trainingLocationId,
			street1, street2, street3, city, zip, regionId, countryId, 0, true,
			true);

		trainingLocation.setAddressId(address.getAddressId());

		trainingLocationPersistence.update(trainingLocation, false);

		return trainingLocation;
	}

	@Override
	public TrainingLocation deleteTrainingLocation(long trainingLocationId)
		throws PortalException, SystemException {

		if (trainingEventPersistence.countByTrainingLocationId(
				trainingLocationId) > 0) {

			throw new RequiredTrainingLocationException();
		}

		TrainingLocation trainingLocation =
			trainingLocationPersistence.findByPrimaryKey(trainingLocationId);

		addressLocalService.deleteAddress(trainingLocation.getAddressId());

		return trainingLocationPersistence.remove(trainingLocation);
	}

	public List<TrainingLocation> getTrainingLocations(
			int start, int end, OrderByComparator obc)
		throws SystemException {

		return trainingLocationFinder.findAll(start, end, obc);
	}

	public TrainingLocation updateTrainingLocation(
			long trainingLocationId, String name, String street1,
			String street2, String street3, String city, String zip,
			long regionId, long countryId)
		throws PortalException, SystemException {

		validate(name);

		TrainingLocation trainingLocation =
			trainingLocationPersistence.findByPrimaryKey(trainingLocationId);

		trainingLocation.setModifiedDate(new Date());
		trainingLocation.setName(name);

		trainingLocationPersistence.update(trainingLocation, false);

		addressLocalService.updateAddress(
			trainingLocation.getAddressId(), street1, street2, street3, city,
			zip, regionId, countryId, 0, true, true);

		return trainingLocation;
	}

	protected void validate(String name) throws PortalException {
		if (Validator.isNull(name)) {
			throw new TrainingLocationNameException();
		}
	}

}