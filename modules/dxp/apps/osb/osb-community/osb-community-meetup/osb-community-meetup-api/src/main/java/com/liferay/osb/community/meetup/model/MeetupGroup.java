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

package com.liferay.osb.community.meetup.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the MeetupGroup service. Represents a row in the &quot;OSBCommunity_MeetupGroup&quot; database table, with each column mapped to a property of this class.
 *
 * @author Jamie Sammons
 * @see MeetupGroupModel
 * @see com.liferay.osb.community.meetup.model.impl.MeetupGroupImpl
 * @see com.liferay.osb.community.meetup.model.impl.MeetupGroupModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.osb.community.meetup.model.impl.MeetupGroupImpl")
@ProviderType
public interface MeetupGroup extends MeetupGroupModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.community.meetup.model.impl.MeetupGroupImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<MeetupGroup, Long> MEETUP_GROUP_ID_ACCESSOR = new Accessor<MeetupGroup, Long>() {
			@Override
			public Long get(MeetupGroup meetupGroup) {
				return meetupGroup.getMeetupGroupId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<MeetupGroup> getTypeClass() {
				return MeetupGroup.class;
			}
		};
}