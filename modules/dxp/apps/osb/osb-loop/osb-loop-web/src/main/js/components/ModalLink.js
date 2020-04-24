import Component, {Config} from 'metal-jsx';
import {connect} from 'metal-redux';

import {createModalURL} from '../lib/util';
import {showModal} from '../actions/modals';

class ModalLink extends Component {
	created() {
		this.handleClick_ = this.handleClick_.bind(this);
	}

	handleClick_(event) {
		const {ctrlKey, metaKey, shiftKey} = event;

		if (!ctrlKey && !metaKey && !shiftKey) {
			event.preventDefault();

			event.stopImmediatePropagation();

			const {config, showModal} = this.props;

			showModal(config);
		}
	}

	render() {
		const {config, passedChildren} = this.props;

		const href = createModalURL(
			{
				...config,
				hideOnBlur: false,
				redirectURL: window.location.href
			}
		);

		return (
			<a {...this.otherProps()} class="modal-link-container" href={href} onClick={this.handleClick_}>
				{passedChildren}
			</a>
		);
	}
}

const STORE = {
	passedChildren: Config.array(),
	showModal: Config.func()
};

ModalLink.PROPS = {
	...STORE,
	config: Config.object()
};

export default connect(
	(state, {children}) => (
		{
			passedChildren: children
		}
	),
	{
		showModal
	}
)(ModalLink);