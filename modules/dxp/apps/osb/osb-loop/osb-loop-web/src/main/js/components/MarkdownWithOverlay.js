import Component, {Config} from 'metal-jsx';
import {connect} from 'metal-redux';
import {forEach} from 'lodash';
import {fromJS, List} from 'immutable';

import MarkdownContent from './MarkdownContent';
import Overlay from './Overlay';
import renderer from './createRenderer';
import {getSharedTo} from '../lib/selectors';
import {overlayTypes} from '../actions/overlays';

class MarkdownWithOverlay extends Component {
	rendered() {
		forEach(
			this.element.querySelectorAll('[data-summaryId]'),
			element => this.setEntityLink_(element)
		);
	}

	setEntityLink_(target) {
		const dataArr = target.getAttribute('data-summaryId').split('-');

		const matchedEntity = this.props.sharedToIList.toJS().find(
			({entityClassNameId, entityClassPK}) => Number(dataArr[0]) === entityClassNameId && Number(dataArr[1]) === entityClassPK
		);

		if (matchedEntity) {
			const renderWithStore = renderer(this.context.store);

			target.removeAttribute('data-summaryId');

			renderWithStore(
				Overlay,
				{
					delay: 600,
					hideOnClick: true,
					offset: 12,
					overlayProps: {entityIMap: fromJS(matchedEntity)},
					overlayType: overlayTypes.ENTITY_SUMMARY
				},
				target,
				true,
				<span>{target.textContent}</span>
			);
		}
	}

	shouldUpdate(nextState, {message}) {
		let retVal;

		if (message) {
			const {newVal, prevVal} = message;

			retVal = newVal !== prevVal;
		}

		return retVal;
	}

	render() {
		return <MarkdownContent {...this.otherProps()} message={this.props.message} ref="markdownContent" />;
	}
}

const STORE = {
	sharedToIList: Config.instanceOf(List)
};

MarkdownWithOverlay.PROPS = {
	...STORE,
	id: Config.number(),
	message: Config.string()
};

export default connect(
	(state, {id}) => (
		{
			sharedToIList: getSharedTo(state, id)
		}
	)
)(MarkdownWithOverlay);