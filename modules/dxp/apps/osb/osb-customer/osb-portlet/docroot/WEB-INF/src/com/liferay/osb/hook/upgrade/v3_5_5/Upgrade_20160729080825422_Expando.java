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

package com.liferay.osb.hook.upgrade.v3_5_5;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.portal.kernel.util.StringBundler;

/**
 * @author Luke Shackelford
 */
public class Upgrade_20160729080825422_Expando extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20160729080825422L;
	}

	protected void deleteOSBCountryExpandoValues() throws Exception {
		runSQL(
			"delete from ExpandoValue where columnId = " +
				_OSB_COUNTRY_EXPANDO_COLUMN_ID +
					" and data_ = 'netherlands-antilles'");
	}

	protected void deleteOSBIndustryExpandoValues() throws Exception {
		StringBundler sb = new StringBundler(4);

		sb.append("delete from ExpandoValue where columnId = ");
		sb.append(_OSB_INDUSTRY_EXPANDO_COLUMN_ID);
		sb.append(" and data_ in ('government', 'professional-services', ");
		sb.append("'unknown')");

		runSQL(sb.toString());
	}

	@Override
	protected void doUpgrade() throws Exception {
		updateListType();

		updateAccountEntryIndustry();

		deleteOSBIndustryExpandoValues();

		updateOSBIndustryExpandoColumn();

		updateOSBIndustryExpandoValue(
			"consulting", "consulting-market-research");
		updateOSBIndustryExpandoValue(
			"consumer-products", "retail-consumer-products");
		updateOSBIndustryExpandoValue("media-marketing", "media-entertainment");

		deleteOSBCountryExpandoValues();

		updateOSBCountryExpandoColumn();
	}

	protected void updateAccountEntryIndustry() throws Exception {
		StringBundler sb = new StringBundler(4);

		sb.append("update OSB_AccountEntry set industry = ");
		sb.append(_ACCOUNT_ENTRY_INDUSTRY_RETAIL_CONSUMER_PRODUCTS);
		sb.append(" where industry = ");
		sb.append(_ACCOUNT_ENTRY_INDUSTRY_CONSUMER_PRODUCTS);

		runSQL(sb.toString());

		sb = new StringBundler(8);

		sb.append("update OSB_AccountEntry set industry = 0 where industry ");
		sb.append("in (");
		sb.append(_ACCOUNT_ENTRY_INDUSTRY_GOVERNMENT);
		sb.append(", ");
		sb.append(_ACCOUNT_ENTRY_INDUSTRY_PROFESSIONAL_SERVICES);
		sb.append(", ");
		sb.append(_ACCOUNT_ENTRY_INDUSTRY_UNKNOWN);
		sb.append(")");

		runSQL(sb.toString());
	}

	protected void updateListType() throws Exception {
		insertListType(
			35023, "engineering",
			"com.liferay.osb.model.AccountEntry.industry");
		insertListType(
			35024, "government-federal",
			"com.liferay.osb.model.AccountEntry.industry");
		insertListType(
			35025, "government-state-local",
			"com.liferay.osb.model.AccountEntry.industry");
		insertListType(
			35026, "professional-services-agency-business",
			"com.liferay.osb.model.AccountEntry.industry");
		insertListType(
			35027, "professional-services-technical-web-it",
			"com.liferay.osb.model.AccountEntry.industry");

		runSQL(
			"update ListType set name = 'consulting-market-research' where " +
				"listTypeId = " + _ACCOUNT_ENTRY_INDUSTRY_CONSULTING);
		runSQL(
			"update ListType set name = 'media-entertainment' where " +
				"listTypeId = " + _ACCOUNT_ENTRY_INDUSTRY_MEDIA_MARKETING);
		runSQL(
			"update ListType set name = 'retail-consumer-products' where " +
				"listTypeId = " + _ACCOUNT_ENTRY_INDUSTRY_CONSUMER_PRODUCTS);

		runSQL(
			"delete from ListType where listTypeId = " +
				_ACCOUNT_ENTRY_INDUSTRY_GOVERNMENT);
		runSQL(
			"delete from ListType where listTypeId = " +
				_ACCOUNT_ENTRY_INDUSTRY_PROFESSIONAL_SERVICES);
		runSQL(
			"delete from ListType where listTypeId = " +
				_ACCOUNT_ENTRY_INDUSTRY_UNKNOWN);
	}

	protected void updateOSBCountryExpandoColumn() throws Exception {
		StringBundler sb = new StringBundler(48);

		sb.append("update ExpandoColumn set defaultData = '");
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
		sb.append("mozambique,namibia,nauru,nepal,netherlands,");
		sb.append("new-caledonia,new-zealand,nicaragua,niger,nigeria,");
		sb.append("niue,norfolk-island,north-korea,norway,oman,pakistan,");
		sb.append("palau,palestine,panama,papua-new-guinea,paraguay,peru,");
		sb.append("philippines,poland,portugal,puerto-rico,qatar,");
		sb.append("republic-of-congo,reunion-island,romania,russia,");
		sb.append("rwanda,san-marino,sao-tome-principe,saudi-arabia,");
		sb.append("senegal,serbia,seychelles,sierra-leone,singapore,");
		sb.append("slovakia,slovenia,solomon-islands,somalia,");
		sb.append("south-africa,south-korea,spain,sri-lanka,st-helena,");
		sb.append("st-kitts,st-lucia,st-pierre-miquelon,st-vincent,sudan,");
		sb.append("suriname,swaziland,sweden,switzerland,syria,taiwan,");
		sb.append("tajikistan,tanzania,thailand,togo,tonga,");
		sb.append("trinidad-tobago,tunisia,turkey,turkmenistan,");
		sb.append("turks-caicos,tuvalu,uganda,ukraine,");
		sb.append("united-arab-emirates,united-kingdom,united-states,");
		sb.append("uruguay,uzbekistan,vanuatu,vatican-city,venezuela,");
		sb.append("vietnam,wallis-futuna,western-samoa,yemen,zambia,");
		sb.append("zimbabwe' where columnId = ");
		sb.append(_OSB_COUNTRY_EXPANDO_COLUMN_ID);

		runSQL(sb.toString());
	}

	protected void updateOSBIndustryExpandoColumn() throws Exception {
		StringBundler sb = new StringBundler(13);

		sb.append("update ExpandoColumn set defaultData = '");
		sb.append("aerospace-and-defense,agriculture,automotive,");
		sb.append("consulting-market-research,education,energy,");
		sb.append("engineering,financial-services,government-federal,");
		sb.append("government-state-local,healthcare,hospitality-leisure,");
		sb.append("insurance,manufacturing,media-entertainment,");
		sb.append("not-for-profit-ngo,pharmaceuticals,");
		sb.append("professional-services-agency-business,");
		sb.append("professional-services-technical-web-it,");
		sb.append("retail-consumer-products,technology,");
		sb.append("telecommunications,transportation,utilities,other");
		sb.append("' where ColumnId = ");
		sb.append(_OSB_INDUSTRY_EXPANDO_COLUMN_ID);

		runSQL(sb.toString());
	}

	protected void updateOSBIndustryExpandoValue(
			String oldValue, String newValue)
		throws Exception {

		StringBundler sb = new StringBundler(7);

		sb.append("update ExpandoValue set data_ = '");
		sb.append(newValue);
		sb.append("' where columnId = ");
		sb.append(_OSB_INDUSTRY_EXPANDO_COLUMN_ID);
		sb.append(" and data_ = '");
		sb.append(oldValue);
		sb.append("'");

		runSQL(sb.toString());
	}

	private static final int _ACCOUNT_ENTRY_INDUSTRY_CONSULTING = 35003;

	private static final int _ACCOUNT_ENTRY_INDUSTRY_CONSUMER_PRODUCTS = 35004;

	private static final int _ACCOUNT_ENTRY_INDUSTRY_GOVERNMENT = 35008;

	private static final int _ACCOUNT_ENTRY_INDUSTRY_MEDIA_MARKETING = 35013;

	private static final int _ACCOUNT_ENTRY_INDUSTRY_PROFESSIONAL_SERVICES =
		35017;

	private static final int _ACCOUNT_ENTRY_INDUSTRY_RETAIL_CONSUMER_PRODUCTS =
		35004;

	private static final int _ACCOUNT_ENTRY_INDUSTRY_UNKNOWN = 35021;

	private static final long _OSB_COUNTRY_EXPANDO_COLUMN_ID = 29224278;

	private static final long _OSB_INDUSTRY_EXPANDO_COLUMN_ID = 29224281;

}