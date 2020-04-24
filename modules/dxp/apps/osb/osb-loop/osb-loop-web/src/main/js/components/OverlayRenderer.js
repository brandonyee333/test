import Component, {Config} from 'metal-jsx';
import getCN from 'classnames';
import {Align} from 'metal-position';
import {connect} from 'metal-redux';
import {Map, OrderedMap} from 'immutable';

import Card from './card';
import CommentMenu from './CommentMenu';
import DivisionProfileMenu from './profile-header/DivisionProfileMenu';
import EntitySummary from './EntitySummary';
import FollowingListMenu from './toolbar/FollowingListMenu';
import FollowMenu from './FollowMenu';
import JobTitleProfileMenu from './profile-header/JobTitleProfileMenu';
import OverlayErrorMessage from './OverlayErrorMessage';
import PageMenu from './pages/PageMenu';
import PersonProfileMenu from './profile-header/PersonProfileMenu';
import PostMenu from './PostMenu';
import TopicProfileMenu from './profile-header/TopicProfileMenu';
import {alignmentPositions, overlayTypes} from '../actions/overlays';

const {
	BOTTOM,
	BOTTOM_LEFT,
	BOTTOM_RIGHT,
	LEFT,
	RIGHT
} = alignmentPositions;

const COMPONENT_MAP = {
	[overlayTypes.COMMENT_MENU]: CommentMenu,
	[overlayTypes.DIVISION_PROFILE_MENU]: DivisionProfileMenu,
	[overlayTypes.ENTITY_SUMMARY]: EntitySummary,
	[overlayTypes.FOLLOW_MENU]: FollowMenu,
	[overlayTypes.FOLLOWING_LIST_MENU]: FollowingListMenu,
	[overlayTypes.JOB_TITLE_PROFILE_MENU]: JobTitleProfileMenu,
	[overlayTypes.OVERLAY_ERROR_MESSAGE]: OverlayErrorMessage,
	[overlayTypes.PAGE_MENU]: PageMenu,
	[overlayTypes.PERSON_PROFILE_MENU]: PersonProfileMenu,
	[overlayTypes.POST_MENU]: PostMenu,
	[overlayTypes.TOPIC_PROFILE_MENU]: TopicProfileMenu
};

const {
	zIndex: {
		OVERLAY: OVERLAY_ZINDEX,
		WINDOW: WINDOW_ZINDEX
	}
} = Liferay;

const OVERLAY_MODAL_ZINDEX = OVERLAY_ZINDEX + WINDOW_ZINDEX;

class OverlayRenderer extends Component {
	rendered() {
		this.props.overlayIOMap.toArray().forEach(
			(overlayIMap, i) => {
				this.getPosition_(overlayIMap, i);
			}
		);
	}

	getOffset_(position, overlayIMap) {
		const id = overlayIMap.get('id');
		const offset = overlayIMap.get('offset');

		let offsetPosition = `margin-bottom: ${offset}px`;

		if ([BOTTOM_RIGHT, BOTTOM, BOTTOM_LEFT].includes(position)) {
			offsetPosition = `margin-top: ${offset}px`;
		}
		else if (RIGHT === position) {
			offsetPosition = `margin-left: ${offset}px`;
		}
		else if (LEFT === position) {
			offsetPosition = `margin-right: ${offset}px`;
		}

		if (offsetPosition !== this.state.offsetStyles_.get(id)) {
			this.state.offsetStyles_ = this.state.offsetStyles_.set(id, offsetPosition);
		}
	}

	getPosition_(overlayIMap, i) {
		let target = document.getElementById(overlayIMap.get('id'));

		if (overlayIMap.get('alignWithParent')) {
			target = target.parentElement;
		}

		if (target) {
			this.getOffset_(
				Align.align(
					this.element.childNodes[i],
					target,
					overlayIMap.get('alignment')
				),
				overlayIMap
			);
		}
	}

	renderOverlay_(overlayIMap) {
		const {
			containerClass,
			id,
			overlayProps,
			overlayType
		} = overlayIMap.toObject();

		let content;

		if (overlayType) {
			const SpecificOverlay = COMPONENT_MAP[overlayType];

			content = <SpecificOverlay {...overlayProps.toObject()} />;
		}

		return (
			<div class={getCN('overlay-container', containerClass)} key={id} style={{zIndex: this.props.modalActive ? OVERLAY_MODAL_ZINDEX : OVERLAY_ZINDEX}}>
				<Card
					elementClasses="overlay-content"
					floating={true}
					onMouseLeave={overlayProps.get('onMouseLeave')}
					onMouseOver={overlayProps.get('onMouseInside')}
					style={this.state.offsetStyles_.get(id, '')}
				>
					{content}
				</Card>
			</div>
		);
	}

	render() {
		return (
			<span class="overlay-base-container">
				{this.props.overlayIOMap.toArray().map(overlayIMap => this.renderOverlay_(overlayIMap))}
			</span>
		);
	}
}

const STORE = {
	modalActive: Config.bool(),
	overlayIOMap: Config.instanceOf(OrderedMap)
};

OverlayRenderer.PROPS = {
	...STORE
};

OverlayRenderer.STATE = {
	offsetStyles_: Config.instanceOf(Map).value(Map())
};

export default connect(
	(state, ownProps) => {
		return {
			modalActive: !state.get('modals', Map()).isEmpty(),
			overlayIOMap: state.get('overlays', OrderedMap())
		};
	},
	null
)(OverlayRenderer);