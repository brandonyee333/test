import Component, {Config} from 'metal-jsx';
import {connect} from 'metal-redux';
import {isEmpty} from 'lodash';
import {Map} from 'immutable';

import ListCard from './ListCard';
import LoopConstants from '../lib/loop-constants';
import {getCopyData} from '../lib/util';
import {getPhoneTypeLabel, lang} from '../lib/lang-util';
import {personSelector} from '../lib/selectors';

const getPhoneTypeIcon = phoneType => {
	const {
		business,
		businessFax,
		mobile,
		personal,
		personalFax,
		tty
	} = LoopConstants.phoneTypes;

	let retVal = 'phone-work';

	if ([business, businessFax, tty].includes(phoneType)) {
		retVal = 'phone-business';
	}
	else if ([personal, personalFax].includes(phoneType)) {
		retVal = 'phone-house';
	}
	else if (mobile === phoneType) {
		retVal = 'phone';
	}

	return retVal;
};

const getEmailObject = (email, showLabels) => (
	{
		content: email,
		copyData: getCopyData(email),
		icon: 'mail',
		label: showLabels && Liferay.Language.get('email'),
		url: `mailto:${email}`
	}
);

const getPhoneObject = ({phoneNumber, typeId}, showLabels) => (
	{
		content: phoneNumber,
		copyData: getCopyData(phoneNumber, lang(Liferay.Language.get('click-to-copy-x'), [getPhoneTypeLabel(typeId)], true)),
		icon: getPhoneTypeIcon(typeId),
		label: showLabels && Liferay.Language.get('phone'),
		url: `tel:${phoneNumber}`
	}
);

class ContactCard extends Component {
	getInfo_() {
		const {personIMap, showLabels} = this.props;

		const {
			emailAddress,
			otherEmailAddressesJSONArray = [],
			otherPhonesJSONArray = [],
			primaryPhoneNumber,
			skypeSn
		} = personIMap.toJS();

		let retVal = [];

		if (emailAddress) {
			retVal.push(getEmailObject(emailAddress, showLabels));
		}

		if (otherEmailAddressesJSONArray.length) {
			retVal = retVal.concat(
				otherEmailAddressesJSONArray.map(item => getEmailObject(item, showLabels))
			);
		}

		if (!isEmpty(primaryPhoneNumber)) {
			retVal.push(getPhoneObject(primaryPhoneNumber, showLabels));
		}

		if (otherPhonesJSONArray.length) {
			retVal = retVal.concat(
				otherPhonesJSONArray.map(item => getPhoneObject(item, showLabels))
			);
		}

		if (skypeSn) {
			retVal.push(
				{
					content: skypeSn,
					copyData: getCopyData(skypeSn),
					icon: 'skype',
					label: showLabels && Liferay.Language.get('skype'),
					url: `skype:${skypeSn}?chat`
				}
			);
		}

		return retVal;
	}

	render() {
		const items = this.getInfo_();

		return (items && items.length) ? <ListCard items={items} title={Liferay.Language.get('contact')} /> : null;
	}
}

const STORE = {
	personIMap: Config.instanceOf(Map)
};

ContactCard.PROPS = {
	...STORE,
	id: Config.number(),
	showLabels: Config.bool().value(false)
};

export default connect(personSelector)(ContactCard);