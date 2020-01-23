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

package com.liferay.osb.admin.servlet;

import com.liferay.expando.kernel.exception.DuplicateColumnNameException;
import com.liferay.expando.kernel.exception.NoSuchTableException;
import com.liferay.expando.kernel.model.ExpandoColumn;
import com.liferay.expando.kernel.model.ExpandoColumnConstants;
import com.liferay.expando.kernel.model.ExpandoTable;
import com.liferay.expando.kernel.model.ExpandoTableConstants;
import com.liferay.expando.kernel.service.ExpandoColumnLocalServiceUtil;
import com.liferay.expando.kernel.service.ExpandoTableLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 * @author Peter Shin
 */
public class AdminServletContextListenerExpandoHelper {

	public static void setup() throws Exception {
		long companyId = OSBConstants.COMPANY_ID;

		// Expando table - CUSTOM_FIELDS - User

		ExpandoTable table = null;

		try {
			table = ExpandoTableLocalServiceUtil.getTable(
				companyId, User.class.getName(),
				ExpandoTableConstants.DEFAULT_TABLE_NAME);
		}
		catch (NoSuchTableException nste) {
			table = ExpandoTableLocalServiceUtil.addTable(
				companyId, User.class.getName(),
				ExpandoTableConstants.DEFAULT_TABLE_NAME);
		}

		try {
			ExpandoColumnLocalServiceUtil.addColumn(
				table.getTableId(), "osbAgreedToContactEvents",
				ExpandoColumnConstants.BOOLEAN);
		}
		catch (DuplicateColumnNameException dcne) {
		}

		try {
			ExpandoColumnLocalServiceUtil.addColumn(
				table.getTableId(), "osbAgreedToContactSales",
				ExpandoColumnConstants.BOOLEAN);
		}
		catch (DuplicateColumnNameException dcne) {
		}

		try {
			ExpandoColumnLocalServiceUtil.addColumn(
				table.getTableId(), "osbAgreedToContactTrainings",
				ExpandoColumnConstants.BOOLEAN);
		}
		catch (DuplicateColumnNameException dcne) {
		}

		try {
			ExpandoColumnLocalServiceUtil.addColumn(
				table.getTableId(), "osbAgreedToContactTrialLicenses",
				ExpandoColumnConstants.BOOLEAN);
		}
		catch (DuplicateColumnNameException dcne) {
		}

		try {
			ExpandoColumnLocalServiceUtil.addColumn(
				table.getTableId(), "osbEvaluationEULA",
				ExpandoColumnConstants.STRING_ARRAY);
		}
		catch (DuplicateColumnNameException dcne) {
		}

		try {
			ExpandoColumnLocalServiceUtil.addColumn(
				table.getTableId(), "osbCompany",
				ExpandoColumnConstants.STRING);
		}
		catch (DuplicateColumnNameException dcne) {
		}

		try {
			ExpandoColumn expandoColumn =
				ExpandoColumnLocalServiceUtil.addColumn(
					table.getTableId(), "osbCompanyRole",
					ExpandoColumnConstants.STRING_ARRAY,
					getOSBCompanyRoleData());

			ExpandoColumnLocalServiceUtil.updateTypeSettings(
				expandoColumn.getColumnId(), "display-type=selection-list");
		}
		catch (DuplicateColumnNameException dcne) {
		}

		try {
			ExpandoColumn expandoColumn =
				ExpandoColumnLocalServiceUtil.addColumn(
					table.getTableId(), "osbCountry",
					ExpandoColumnConstants.STRING_ARRAY, getOSBCountryData());

			ExpandoColumnLocalServiceUtil.updateTypeSettings(
				expandoColumn.getColumnId(), "display-type=selection-list");
		}
		catch (DuplicateColumnNameException dcne) {
		}

		try {
			ExpandoColumnLocalServiceUtil.addColumn(
				table.getTableId(), "osbDXPCloudSubscription",
				ExpandoColumnConstants.BOOLEAN);
		}
		catch (DuplicateColumnNameException dcne) {
		}

		try {
			ExpandoColumn expandoColumn =
				ExpandoColumnLocalServiceUtil.addColumn(
					table.getTableId(), "osbIndustry",
					ExpandoColumnConstants.STRING_ARRAY, getOSBIndustryData());

			ExpandoColumnLocalServiceUtil.updateTypeSettings(
				expandoColumn.getColumnId(), "display-type=selection-list");
		}
		catch (DuplicateColumnNameException dcne) {
		}

		try {
			ExpandoColumnLocalServiceUtil.addColumn(
				table.getTableId(), "osbLiferaySyncEULA",
				ExpandoColumnConstants.STRING_ARRAY);
		}
		catch (DuplicateColumnNameException dcne) {
		}

		try {
			ExpandoColumnLocalServiceUtil.addColumn(
				table.getTableId(), "osbPhoneNumber",
				ExpandoColumnConstants.STRING);
		}
		catch (DuplicateColumnNameException dcne) {
		}

		try {
			ExpandoColumnLocalServiceUtil.addColumn(
				table.getTableId(), "osbStudioEULA",
				ExpandoColumnConstants.STRING_ARRAY);
		}
		catch (DuplicateColumnNameException dcne) {
		}

		try {
			ExpandoColumnLocalServiceUtil.addColumn(
				table.getTableId(), "osbTrialEULA",
				ExpandoColumnConstants.STRING_ARRAY);
		}
		catch (DuplicateColumnNameException dcne) {
		}

		// Expando table - OSB - User

		try {
			table = ExpandoTableLocalServiceUtil.getTable(
				companyId, User.class.getName(), "OSB");
		}
		catch (NoSuchTableException nste) {
			table = ExpandoTableLocalServiceUtil.addTable(
				companyId, User.class.getName(), "OSB");
		}

		try {
			ExpandoColumnLocalServiceUtil.addColumn(
				table.getTableId(), "osbTrialPurchased",
				ExpandoColumnConstants.BOOLEAN);
		}
		catch (DuplicateColumnNameException dcne) {
		}
	}

	protected static String[] getOSBCompanyRoleData() {
		StringBundler sb = new StringBundler(4);

		sb.append("analyst,architect,business-development,");
		sb.append("c-level-vp-executive,contractor-consultant,developer,");
		sb.append("director,management,other,project-manager,");
		sb.append("purchasing-finance");

		return StringUtil.split(sb.toString());
	}

	protected static String[] getOSBCountryData() {
		StringBundler sb = new StringBundler(45);

		sb.append("afghanistan,albania,algeria,american-samoa,andorra,");
		sb.append("angola,anguilla,antarctica,antigua,argentina,armenia,");
		sb.append("aruba,australia,austria,azerbaijan,bahamas,bahrain,");
		sb.append("bangladesh,barbados,belarus,belgium,belize,benin,");
		sb.append("bermuda,bhutan,bolivia,bosnia-herzegovina,botswana,");
		sb.append("brazil,british-virgin-islands,brunei,bulgaria,");
		sb.append("burkina-faso,burma-myanmar,burundi,cambodia,cameroon,");
		sb.append("canada,cape-verde-island,cayman-islands,");
		sb.append("central-african-republic,chad,chile,china,");
		sb.append("christmas-island,cocos-islands,colombia,comoros,");
		sb.append("cook-islands,costa-rica,croatia,cuba,cyprus,");
		sb.append("czech-republic,democratic-republic-of-congo,denmark,");
		sb.append("djibouti,dominica,dominican-republic,ecuador,egypt,");
		sb.append("el-salvador,equatorial-guinea,eritrea,estonia,");
		sb.append("ethiopia,faeroe-islands,falkland-islands,fiji-islands,");
		sb.append("finland,france,french-guiana,french-polynesia,gabon,");
		sb.append("gambia,georgia,germany,ghana,gibraltar,greece,");
		sb.append("greenland,grenada,guadeloupe,guam,guatemala,guinea,");
		sb.append("guinea-bissau,guyana,haiti,honduras,hong-kong,hungary,");
		sb.append("iceland,india,indonesia,iran,iraq,ireland,israel,");
		sb.append("italy,ivory-coast,jamaica,japan,jordan,kazakhstan,");
		sb.append("kenya,kiribati,kuwait,kyrgyzstan,laos,latvia,lebanon,");
		sb.append("lesotho,liberia,libya,liechtenstein,lithuania,");
		sb.append("luxembourg,macau,macedonia,madagascar,malawi,malaysia,");
		sb.append("maldives,mali,malta,marshall-islands,martinique,");
		sb.append("mauritania,mauritius,mayotte-island,mexico,micronesia,");
		sb.append("moldova,monaco,mongolia,montenegro,montserrat,morocco,");
		sb.append("mozambique,namibia,nauru,nepal,netherlands,new-caledonia,");
		sb.append("new-zealand,nicaragua,niger,nigeria,niue,norfolk-island,");
		sb.append("north-korea,norway,oman,pakistan,palau,palestine,");
		sb.append("panama,papua-new-guinea,paraguay,peru,philippines,");
		sb.append("poland,portugal,puerto-rico,qatar,republic-of-congo,");
		sb.append("reunion-island,romania,russia,rwanda,san-marino,");
		sb.append("sao-tome-principe,saudi-arabia,senegal,serbia,");
		sb.append("seychelles,sierra-leone,singapore,slovakia,slovenia,");
		sb.append("solomon-islands,somalia,south-africa,south-korea,");
		sb.append("spain,sri-lanka,st-helena,st-kitts,st-lucia,");
		sb.append("st-pierre-miquelon,st-vincent,sudan,suriname,");
		sb.append("swaziland,sweden,switzerland,syria,taiwan,tajikistan,");
		sb.append("tanzania,thailand,togo,tonga,trinidad-tobago,tunisia,");
		sb.append("turkey,turkmenistan,turks-caicos,tuvalu,uganda,");
		sb.append("ukraine,united-arab-emirates,united-kingdom,");
		sb.append("united-states,uruguay,uzbekistan,vanuatu,vatican-city,");
		sb.append("venezuela,vietnam,wallis-futuna,western-samoa,");
		sb.append("yemen,zambia,zimbabwe");

		return StringUtil.split(sb.toString());
	}

	protected static String[] getOSBIndustryData() {
		StringBundler sb = new StringBundler(10);

		sb.append("aerospace-and-defense,agriculture,automotive,");
		sb.append("consulting-market-research,education,energy,engineering,");
		sb.append("financial-services,government-federal,");
		sb.append("government-state-local,healthcare,hospitality-leisure,");
		sb.append("insurance,manufacturing,media-entertainment,");
		sb.append("not-for-profit-ngo,pharmaceuticals,");
		sb.append("professional-services-agency-business,");
		sb.append("professional-services-technical-web-it,");
		sb.append("retail-consumer-products,technology,telecommunications,");
		sb.append("transportation,utilities,other");

		return StringUtil.split(sb.toString());
	}

}