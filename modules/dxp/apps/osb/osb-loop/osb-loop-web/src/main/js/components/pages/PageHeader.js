import Component, {Config} from 'metal-jsx';
import {Map} from 'immutable';

import Avatar from '../Avatar';
import EntityLink from '../EntityLink';
import {formatTime} from '../../lib/util';
import {lang} from '../../lib/lang-util';

class PageHeader extends Component {
	renderCreateSummary_() {
		const {creatorIMap, ownerIMap, verbiage} = this.props;

		let retVal = <EntityLink entity={creatorIMap.toJS()} key="creator" summary={true} />;

		if (ownerIMap && verbiage) {
			retVal = (
				<div class="created-summary">
					{lang(verbiage, [retVal, <EntityLink entity={ownerIMap.toJS()} key="owner" summary={true} />])}
				</div>
			);
		}

		return retVal;
	}

	render() {
		const {creatorIMap, modifiedTime} = this.props;

		const creator = creatorIMap.toJS();

		return (
			<div class="page-header-container">
				<Avatar entity={creator} size="smallest" summary={true} />

				<div class="header-info">
					{this.renderCreateSummary_()}

					<span class="secondary-info" data-tooltip title={formatTime(modifiedTime, true)}>{formatTime(modifiedTime)}</span>
				</div>
			</div>
		);
	}
}

PageHeader.PROPS = {
	creatorIMap: Config.instanceOf(Map).required(),
	modifiedTime: Config.number(),
	ownerIMap: Config.instanceOf(Map),
	verbiage: Config.string()
};

export default PageHeader;