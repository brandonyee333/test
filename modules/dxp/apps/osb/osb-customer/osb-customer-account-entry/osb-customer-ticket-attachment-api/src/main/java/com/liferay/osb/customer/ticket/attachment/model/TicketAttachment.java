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

package com.liferay.osb.customer.ticket.attachment.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the TicketAttachment service. Represents a row in the &quot;OSBCustomer_TicketAttachment&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see TicketAttachmentModel
 * @see com.liferay.osb.customer.ticket.attachment.model.impl.TicketAttachmentImpl
 * @see com.liferay.osb.customer.ticket.attachment.model.impl.TicketAttachmentModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.osb.customer.ticket.attachment.model.impl.TicketAttachmentImpl")
@ProviderType
public interface TicketAttachment extends TicketAttachmentModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.customer.ticket.attachment.model.impl.TicketAttachmentImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<TicketAttachment, Long> TICKET_ATTACHMENT_ID_ACCESSOR =
		new Accessor<TicketAttachment, Long>() {
			@Override
			public Long get(TicketAttachment ticketAttachment) {
				return ticketAttachment.getTicketAttachmentId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<TicketAttachment> getTypeClass() {
				return TicketAttachment.class;
			}
		};

	public String getFilePath();
}