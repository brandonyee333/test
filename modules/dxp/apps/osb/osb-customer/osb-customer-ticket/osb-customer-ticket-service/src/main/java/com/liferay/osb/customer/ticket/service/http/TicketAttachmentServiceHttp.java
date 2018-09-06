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

package com.liferay.osb.customer.ticket.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.ticket.service.TicketAttachmentServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * {@link TicketAttachmentServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * {@link HttpPrincipal} parameter.
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketAttachmentServiceSoap
 * @see HttpPrincipal
 * @see TicketAttachmentServiceUtil
 * @generated
 */
@ProviderType
public class TicketAttachmentServiceHttp {
	public static com.liferay.osb.customer.ticket.model.TicketAttachment addTicketAttachment(
		HttpPrincipal httpPrincipal, long accountEntryId, long zendeskTicketId,
		String fileRepositoryId, String fileName, long fileSize, int type)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(TicketAttachmentServiceUtil.class,
					"addTicketAttachment", _addTicketAttachmentParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					accountEntryId, zendeskTicketId, fileRepositoryId,
					fileName, fileSize, type);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.osb.customer.ticket.model.TicketAttachment)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.customer.ticket.model.TicketAttachment getTicketAttachment(
		HttpPrincipal httpPrincipal, long ticketAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(TicketAttachmentServiceUtil.class,
					"getTicketAttachment", _getTicketAttachmentParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					ticketAttachmentId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.osb.customer.ticket.model.TicketAttachment)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(TicketAttachmentServiceHttp.class);
	private static final Class<?>[] _addTicketAttachmentParameterTypes0 = new Class[] {
			long.class, long.class, String.class, String.class, long.class,
			int.class
		};
	private static final Class<?>[] _getTicketAttachmentParameterTypes1 = new Class[] {
			long.class
		};
}