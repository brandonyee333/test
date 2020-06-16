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
 * @generated
 */
@ImplementationClassName(
	"com.liferay.osb.customer.release.notes.model.impl.ReleaseNotesImpl"
)
@ProviderType
public interface ReleaseNotes extends PersistedModel, ReleaseNotesModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.osb.customer.release.notes.model.impl.ReleaseNotesImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ReleaseNotes, Long> RELEASE_NOTES_ID_ACCESSOR =
		new Accessor<ReleaseNotes, Long>() {

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

	public String[] getJiraIssueKeysArray();

}