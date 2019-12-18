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

package com.liferay.osb.customer.admin.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the AccountEnvironmentAttachment service. Represents a row in the &quot;OSB_AccountEnvironmentAttachment&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see AccountEnvironmentAttachmentModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.osb.customer.admin.model.impl.AccountEnvironmentAttachmentImpl"
)
@ProviderType
public interface AccountEnvironmentAttachment
	extends AccountEnvironmentAttachmentModel, PersistedModel {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.osb.customer.admin.model.impl.AccountEnvironmentAttachmentImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<AccountEnvironmentAttachment, Long>
		ACCOUNT_ENVIRONMENT_ATTACHMENT_ID_ACCESSOR =
			new Accessor<AccountEnvironmentAttachment, Long>() {

				@Override
				public Long get(
					AccountEnvironmentAttachment accountEnvironmentAttachment) {

					return accountEnvironmentAttachment.
						getAccountEnvironmentAttachmentId();
				}

				@Override
				public Class<Long> getAttributeClass() {
					return Long.class;
				}

				@Override
				public Class<AccountEnvironmentAttachment> getTypeClass() {
					return AccountEnvironmentAttachment.class;
				}

			};

	public boolean fileExists()
		throws com.liferay.portal.kernel.exception.PortalException;

	public int getContentLength();

	public String getFileDir();

}