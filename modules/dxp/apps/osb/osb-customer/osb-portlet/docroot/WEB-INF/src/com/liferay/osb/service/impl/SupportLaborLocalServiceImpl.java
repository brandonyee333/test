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

import com.liferay.portal.kernel.util.Validator;
import com.liferay.osb.exception.NoSuchSupportWorkerException;
import com.liferay.osb.exception.SupportLaborHourException;
import com.liferay.osb.exception.SupportLaborNameException;
import com.liferay.osb.model.SupportLabor;
import com.liferay.osb.model.SupportWorker;
import com.liferay.osb.service.base.SupportLaborLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

/**
 * @author Brent Krone-Schmidt
 */
public class SupportLaborLocalServiceImpl
	extends SupportLaborLocalServiceBaseImpl {

	public SupportLabor addSupportLabor(
			String name, String description, String timeZoneId, int sunOpen,
			int sunClose, int monOpen, int monClose, int tueOpen, int tueClose,
			int wedOpen, int wedClose, int thuOpen, int thuClose, int friOpen,
			int friClose, int satOpen, int satClose)
		throws PortalException, SystemException {

		validate(
			name, sunOpen, sunClose, monOpen, monClose, tueOpen, tueClose,
			wedOpen, wedClose, thuOpen, thuClose, friOpen, friClose, satOpen,
			satClose);

		long supportLaborId = counterLocalService.increment();

		SupportLabor supportLabor = supportLaborPersistence.create(
			supportLaborId);

		supportLabor.setName(name);
		supportLabor.setDescription(description);
		supportLabor.setTimeZoneId(timeZoneId);
		supportLabor.setSunOpen(sunOpen);
		supportLabor.setSunClose(sunClose);
		supportLabor.setMonOpen(monOpen);
		supportLabor.setMonClose(monClose);
		supportLabor.setTueOpen(tueOpen);
		supportLabor.setTueClose(tueClose);
		supportLabor.setWedOpen(wedOpen);
		supportLabor.setWedClose(wedClose);
		supportLabor.setThuOpen(thuOpen);
		supportLabor.setThuClose(thuClose);
		supportLabor.setFriOpen(friOpen);
		supportLabor.setFriClose(friClose);
		supportLabor.setSatOpen(satOpen);
		supportLabor.setSatClose(satClose);

		supportLaborPersistence.update(supportLabor, false);

		return supportLabor;
	}

	public void addSupportWorkers(long[] supportWorkerIds, long supportLaborId)
		throws PortalException, SystemException {

		for (long supportWorkerId : supportWorkerIds) {
			SupportWorker supportWorker =
				supportWorkerPersistence.findByPrimaryKey(supportWorkerId);

			supportWorker.setSupportLaborId(supportLaborId);

			supportWorkerPersistence.update(supportWorker, false);
		}
	}

	@Override
	public SupportLabor deleteSupportLabor(long supportLaborId)
		throws PortalException, SystemException {

		SupportLabor supportLabor = supportLaborPersistence.remove(
			supportLaborId);

		List<SupportWorker> supportWorkers =
			supportWorkerPersistence.findBySupportLaborId(supportLaborId);

		for (SupportWorker supportWorker : supportWorkers) {
			supportWorker.setSupportLaborId(0);

			supportWorkerPersistence.update(supportWorker, false);
		}

		return supportLabor;
	}

	public boolean hasSupportWorker(long supportWorkerId, long supportLaborId)
		throws PortalException, SystemException {

		SupportWorker supportWorker = supportWorkerPersistence.findByPrimaryKey(
			supportWorkerId);

		if (supportWorker.getSupportLaborId() == supportLaborId) {
			return true;
		}

		return false;
	}

	public void removeSupportWorkers(long[] supportWorkerIds)
		throws PortalException, SystemException {

		for (long supportWorkerId : supportWorkerIds) {
			try {
				SupportWorker supportWorker =
					supportWorkerPersistence.findByPrimaryKey(supportWorkerId);

				supportWorker.setSupportLaborId(0);

				supportWorkerPersistence.update(supportWorker, false);
			}
			catch (NoSuchSupportWorkerException nsswe) {
			}
		}
	}

	public SupportLabor updateSupportLabor(
			long supportLaborId, String name, String description,
			String timeZoneId, int sunOpen, int sunClose, int monOpen,
			int monClose, int tueOpen, int tueClose, int wedOpen, int wedClose,
			int thuOpen, int thuClose, int friOpen, int friClose, int satOpen,
			int satClose)
		throws PortalException, SystemException {

		validate(
			name, sunOpen, sunClose, monOpen, monClose, tueOpen, tueClose,
			wedOpen, wedClose, thuOpen, thuClose, friOpen, friClose, satOpen,
			satClose);

		SupportLabor supportLabor = supportLaborPersistence.findByPrimaryKey(
			supportLaborId);

		supportLabor.setName(name);
		supportLabor.setDescription(description);
		supportLabor.setTimeZoneId(timeZoneId);
		supportLabor.setSunOpen(sunOpen);
		supportLabor.setSunClose(sunClose);
		supportLabor.setMonOpen(monOpen);
		supportLabor.setMonClose(monClose);
		supportLabor.setTueOpen(tueOpen);
		supportLabor.setTueClose(tueClose);
		supportLabor.setWedOpen(wedOpen);
		supportLabor.setWedClose(wedClose);
		supportLabor.setThuOpen(thuOpen);
		supportLabor.setThuClose(thuClose);
		supportLabor.setFriOpen(friOpen);
		supportLabor.setFriClose(friClose);
		supportLabor.setSatOpen(satOpen);
		supportLabor.setSatClose(satClose);

		supportLaborPersistence.update(supportLabor, false);

		return supportLabor;
	}

	protected void validate(
			String name, int sunOpen, int sunClose, int monOpen, int monClose,
			int tueOpen, int tueClose, int wedOpen, int wedClose, int thuOpen,
			int thuClose, int friOpen, int friClose, int satOpen, int satClose)
		throws PortalException {

		if (Validator.isNull(name)) {
			throw new SupportLaborNameException();
		}

		if ((sunOpen < 0) || (sunOpen > 1440) || (sunClose < 0) ||
			(sunClose > 1440) || (monOpen < 0) || (monOpen > 1440) ||
			(monClose < 0) || (monClose > 1440) || (tueOpen < 0) ||
			(tueOpen > 1440) || (tueClose < 0) || (tueClose > 1440) ||
			(wedOpen < 0) || (wedOpen > 1440) || (wedClose < 0) ||
			(wedClose > 1440) || (thuOpen < 0) || (thuOpen > 1440) ||
			(thuClose < 0) || (thuClose > 1440) || (friOpen < 0) ||
			(friOpen > 1440) || (friClose < 0) || (friClose > 1440) ||
			(satOpen < 0) || (satOpen > 1440) || (satClose < 0) ||
			(satClose > 1440)) {

			throw new SupportLaborHourException();
		}

		if ((sunClose < sunOpen) || (monClose < monOpen) ||
			(tueClose < tueOpen) || (wedClose < wedOpen) ||
			(thuClose < thuOpen) || (friClose < friOpen) ||
			(satClose < satOpen)) {

			throw new SupportLaborHourException();
		}
	}

}