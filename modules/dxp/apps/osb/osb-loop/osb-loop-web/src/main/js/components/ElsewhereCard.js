import Component, {Config} from 'metal-jsx';
import {connect} from 'metal-redux';
import {Map} from 'immutable';

import ListCard from './ListCard';
import LoopConstants from '../lib/loop-constants';
import {personSelector} from '../lib/selectors';

const {facebook, github, linkedin, twitter} = LoopConstants.socialUrls;

class ElsewhereCard extends Component {
	getInfo_() {
		const {personIMap, showLabels} = this.props;

		const {facebookSn, gitHubSn, linkedInSn, twitterSn} = personIMap.toJS();

		const retVal = [];

		if (facebookSn) {
			retVal.push(
				{
					content: facebookSn,
					icon: 'facebook',
					label: showLabels && Liferay.Language.get('facebook'),
					url: `${facebook}/${facebookSn}`
				}
			);
		}

		if (gitHubSn) {
			retVal.push(
				{
					content: gitHubSn,
					icon: 'github',
					label: showLabels && Liferay.Language.get('github'),
					url: `${github}/${gitHubSn}`
				}
			);
		}

		if (twitterSn) {
			retVal.push(
				{
					content: twitterSn,
					icon: 'twitter',
					label: showLabels && Liferay.Language.get('twitter'),
					url: `${twitter}/${twitterSn}`
				}
			);
		}

		if (linkedInSn) {
			retVal.push(
				{
					content: linkedInSn,
					icon: 'linkedin',
					label: showLabels && Liferay.Language.get('linkedin'),
					url: `${linkedin}/${linkedInSn}`
				}
			);
		}

		return retVal;
	}

	render() {
		const items = this.getInfo_();

		return (items && items.length) ? <ListCard items={items} title={Liferay.Language.get('elsewhere')} /> : null;
	}
}

const STORE = {
	personIMap: Config.instanceOf(Map)
};

ElsewhereCard.PROPS = {
	...STORE,
	id: Config.number(),
	showLabels: Config.bool().value(false)
};

export default connect(personSelector)(ElsewhereCard);