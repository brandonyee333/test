import Component, {Config} from 'metal-jsx';
import dom from 'metal-dom';
import Transitions from 'metal-css-transitions';
import {Align} from 'metal-position';
import {bindAll} from 'lodash';
import {EventHandler} from 'metal-events';

class Tooltip extends Component {
	created() {
		this.getPosition_ = this.getPosition_.bind(this);
	}

	attached() {
		window.addEventListener('resize', this.getPosition_);
		window.addEventListener('scroll', this.getPosition_);
	}

	detached() {
		window.removeEventListener('resize', this.getPosition_);
		window.removeEventListener('scroll', this.getPosition_);
	}

	rendered() {
		this.getPosition_();
	}

	getPosition_() {
		Align.align(this.element, this.props.delegateTarget, Align.Bottom);
	}

	render() {
		return (
			<div class="tooltip-wrapper">
				<div class="tooltip-content">{this.props.message}</div>
			</div>
		);
	}
}

Tooltip.PROPS = {
	delegateTarget: Config.object(),
	message: Config.string()
};

class TooltipBase extends Component {
	created() {
		this._eventHandler = new EventHandler();

		bindAll(
			this,
			'handleShow_',
			'handleHide_'
		);
	}

	attached() {
		this.setTriggers();
	}

	detached() {
		if (this._delay) {
			clearTimeout(this._delay);
		}

		this._eventHandler.removeAllListeners();
	}

	handleHide_({delegateTarget}) {
		const dataTitle = delegateTarget && delegateTarget.getAttribute('data-title');

		if (dataTitle) {
			delegateTarget.removeEventListener('click', this.handleHide_);

			delegateTarget.setAttribute('title', dataTitle);

			delegateTarget.removeAttribute('data-title');

			if (this.state.show_) {
				const parentNode = delegateTarget.parentNode;

				const parentMessage = parentNode && parentNode.getAttribute('data-title');

				if (parentMessage) {
					this.setState(
						{
							delegateTarget_: parentNode,
							message_: parentMessage
						}
					);
				}
				else {
					this.state.show_ = false;
				}
			}
		}
		else if (this._responseMessage) {
			this.setState(
				{
					message_: this._responseMessage,
					show_: true
				}
			);
		}
		else {
			this.state.show_ = false;
		}

		clearTimeout(this._delay);
	}

	handleShow_({delegateTarget}) {
		this._responseMessage = delegateTarget.getAttribute('data-tooltip-response');

		delegateTarget.addEventListener('click', this.handleHide_);

		const message = delegateTarget.getAttribute('title');

		if (message) {
			this.setState(
				{
					delegateTarget_: delegateTarget,
					message_: message
				}
			);

			delegateTarget.setAttribute('data-title', message);
			delegateTarget.removeAttribute('title');

			if (delegateTarget.hasAttribute('data-tooltip') || delegateTarget.hasAttribute('data-tooltip-response')) {
				this._delay = setTimeout(
					() => {
						this.state.show_ = true;
					},
					600
				);
			}
		}
	}

	setTriggers() {
		this._eventHandler.removeAllListeners();

		this._eventHandler.add(
			dom.delegate(document, 'mouseenter', '[title]', this.handleShow_),
			dom.delegate(document, 'mouseleave', '[data-title]', this.handleHide_),
		);
	}

	render() {
		const {delegateTarget_, message_, show_} = this.state;

		return (
			<Transitions elementClasses="tooltip-container" name="transition-fade-in-out">
				{show_ &&
					<Tooltip delegateTarget={delegateTarget_} message={message_} />
				}
			</Transitions>
		);
	}
}

TooltipBase.STATE = {
	delegateTarget_: Config.value({}),
	message_: Config.string().value(''),
	show_: Config.bool().value(false)
};

export default TooltipBase;