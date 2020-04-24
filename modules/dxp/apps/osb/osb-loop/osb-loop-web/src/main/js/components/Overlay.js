import Component, {Config} from 'metal-jsx';
import {connect} from 'metal-redux';
import {values} from 'lodash';

import {
	bindAll,
	debounce,
	get,
	uniqueId
} from 'lodash';

import LoopConstants from '../lib/loop-constants';
import {alignmentPositions, hideOverlay, showOverlay} from '../actions/overlays';

class Overlay extends Component {
	created() {
		const {delay, hideDelay, overlayType} = this.props;

		this.hide_ = debounce(() => this.hideOverlay_(), hideDelay);

		this.show_ = debounce(() => this.setState({active_: true}), delay);

		this._id = `${LoopConstants.namespace}${overlayType}${uniqueId()}`;

		this._overlayVisible = false;

		bindAll(
			this,
			'handleMouseLeave_',
			'handleMouseInside_',
			'hideOverlay_',
			'renderContent_'
		);
	};

	disposed() {
		if (this.hide_) {
			this.hide_.cancel();
		}

		if (this.show_) {
			this.show_.cancel();
		}

		if (this.getActive_()) {
			this.props.hideOverlay(this._id);
		}
	}

	rendered() {
		if (!this.getActive_() && this._overlayVisible) {
			this._overlayVisible = false;

			this.props.hideOverlay(this._id);
		}
		else if (this.getActive_() && !this._overlayVisible) {
			this._overlayVisible = true;

			this.renderContent_();
		}
	}

	getActive_() {
		return !this.props.disabled && get(this.props, 'active', this.state.active_);
	}

	handleMouseInside_(event) {
		const {onOverlayOver} = this.context;

		if (onOverlayOver) {
			onOverlayOver();
		}

		this.hide_.cancel();

		this.show_();
	}

	handleMouseLeave_(event) {
		const {onOverlayLeave} = this.context;

		if (onOverlayLeave) {
			onOverlayLeave();
		}

		this.show_.cancel();

		this.hide_();
	}

	hideOverlay_() {
		this.state.active_ = false;

		if (this.show_) {
			this.show_.cancel();
		}
	}

	renderContent_() {
		const {
			handleMouseInside_,
			handleMouseLeave_,
			props: {
				alignment,
				alignWithParent,
				containerClass,
				offset,
				overlayProps,
				overlayType,
				showOverlay
			}
		} = this;

		showOverlay(
			{
				alignment,
				alignWithParent,
				containerClass,
				id: this._id,
				offset,
				overlayProps: {
					...overlayProps,
					active: this.getActive_(),
					onMouseInside: handleMouseInside_,
					onMouseLeave: handleMouseLeave_
				},
				overlayType
			}
		);
	}

	render() {
		const {
			handleMouseInside_,
			handleMouseLeave_,
			props: {hideOnClick, passedChildren}
		} = this;

		const trigger = passedChildren[0];

		trigger.props = {
			...trigger.props,
			active: this.getActive_(),
			id: this._id,
			onMouseEnter: handleMouseInside_,
			onMouseLeave: handleMouseLeave_
		};

		if (hideOnClick) {
			trigger.props.onClick = this.hideOverlay_;
		}

		return trigger;
	}
}

Overlay.PROPS = {
	active: Config.bool(),
	alignment: Config.oneOf(values(alignmentPositions)).value(alignmentPositions.BOTTOM),
	alignWithParent: Config.bool().value(false),
	containerClass: Config.string(),
	delay: Config.number().value(0),
	disabled: Config.bool().value(false),
	hideDelay: Config.number().value(400),
	hideOnClick: Config.bool().value(false),
	offset: Config.number().value(0),
	overlayProps: Config.object(),
	overlayType: Config.string().value('DEFAULT_OVERLAY')
};

Overlay.STATE = {
	active_: Config.bool().value(false)
};

export default connect(
	(state, {children}) => (
		{passedChildren: children}
	),
	{
		hideOverlay,
		showOverlay
	}
)(Overlay);