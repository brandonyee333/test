<%--
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
--%>

<%@ include file="/marketplace_admin/init.jsp" %>

<%
long addressId = ParamUtil.getLong(request, "addressId");

StringBundler sb = new StringBundler(13);

Address address = AddressLocalServiceUtil.getAddress(addressId);

sb.append(address.getStreet1());

if (Validator.isNotNull(address.getStreet2())) {
	sb.append(StringPool.COMMA_AND_SPACE);
	sb.append(address.getStreet2());
}

if (Validator.isNotNull(address.getStreet3())) {
	sb.append(StringPool.COMMA_AND_SPACE);
	sb.append(address.getStreet3());
}

sb.append(StringPool.COMMA_AND_SPACE);
sb.append(address.getCity());

Region region = address.getRegion();

if ((region != null) && Validator.isNotNull(region.getName())) {
	sb.append(StringPool.COMMA_AND_SPACE);
	sb.append(region.getName());
}

sb.append(StringPool.COMMA_AND_SPACE);
sb.append(address.getZip());

Country country = address.getCountry();

sb.append(StringPool.COMMA_AND_SPACE);
sb.append(country.getName());
%>

<%= HtmlUtil.escape(sb.toString()) %>