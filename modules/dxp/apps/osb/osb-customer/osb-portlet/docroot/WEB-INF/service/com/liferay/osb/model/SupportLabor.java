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

package com.liferay.osb.model;

import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the SupportLabor service. Represents a row in the &quot;OSB_SupportLabor&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see SupportLaborModel
 * @see com.liferay.osb.model.impl.SupportLaborImpl
 * @see com.liferay.osb.model.impl.SupportLaborModelImpl
 * @generated
 */
public interface SupportLabor extends SupportLaborModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.model.impl.SupportLaborImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public java.lang.String formatDayHours(java.util.Locale locale, int day);

	public java.lang.String formatTime(java.util.Locale locale, int day,
		int type);

	public java.util.List<com.liferay.osb.model.SupportTeam> getSupportTeams()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public int getTime(int day, int type);
}