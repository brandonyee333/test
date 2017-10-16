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

package com.liferay.osb.customer.release.notes.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the ReleaseNotes service. Represents a row in the &quot;RN_ReleaseNotes&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ReleaseNotesModel
 * @see com.liferay.osb.customer.release.notes.model.impl.ReleaseNotesImpl
 * @see com.liferay.osb.customer.release.notes.model.impl.ReleaseNotesModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.osb.customer.release.notes.model.impl.ReleaseNotesImpl")
@ProviderType
public interface ReleaseNotes extends ReleaseNotesModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.customer.release.notes.model.impl.ReleaseNotesImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ReleaseNotes, Long> RELEASE_NOTES_ID_ACCESSOR = new Accessor<ReleaseNotes, Long>() {
			@Override
			public Long get(ReleaseNotes releaseNotes) {
				return releaseNotes.getReleaseNotesId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<ReleaseNotes> getTypeClass() {
				return ReleaseNotes.class;
			}
		};

	public java.lang.String[] getJiraIssueKeysArray();
}