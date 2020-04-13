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

package com.liferay.osb.community.doc.project.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the DocProject service. Represents a row in the &quot;OSBCommunity_DocProject&quot; database table, with each column mapped to a property of this class.
 *
 * @author Ryan Park
 * @see DocProjectModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.osb.community.doc.project.model.impl.DocProjectImpl"
)
@ProviderType
public interface DocProject extends DocProjectModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.osb.community.doc.project.model.impl.DocProjectImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<DocProject, Long> DOC_PROJECT_ID_ACCESSOR =
		new Accessor<DocProject, Long>() {

			@Override
			public Long get(DocProject docProject) {
				return docProject.getDocProjectId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<DocProject> getTypeClass() {
				return DocProject.class;
			}

		};

	public DocProjectTypeSettings getDocProjectTypeSettings();

	public java.io.InputStream getIconInputStream()
		throws com.liferay.portal.kernel.exception.PortalException;

}