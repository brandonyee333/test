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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the SupportTeam service. Represents a row in the &quot;OSB_SupportTeam&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see SupportTeamModel
 * @see com.liferay.osb.model.impl.SupportTeamImpl
 * @see com.liferay.osb.model.impl.SupportTeamModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.osb.model.impl.SupportTeamImpl")
@ProviderType
public interface SupportTeam extends SupportTeamModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.model.impl.SupportTeamImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<SupportTeam, Long> SUPPORT_TEAM_ID_ACCESSOR = new Accessor<SupportTeam, Long>() {
			@Override
			public Long get(SupportTeam supportTeam) {
				return supportTeam.getSupportTeamId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<SupportTeam> getTypeClass() {
				return SupportTeam.class;
			}
		};
}