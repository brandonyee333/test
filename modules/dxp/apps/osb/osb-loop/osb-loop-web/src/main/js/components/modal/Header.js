import Component, {Config} from 'metal-jsx';
import getCN from 'classnames';

import Icon from '../Icon';

class Header extends Component {
	attached() {
		this.refs.title.focus();
	}

	render() {
		const {children, full, onClose} = this.props;

		return (
			<div class={getCN('modal-header-container', {full})}>
				<h1 class="title" ref="title" tabindex="0">
					{children}
				</h1>

				<Icon
					elementClasses="modal-close"
					name={full ? 'cancel' : 'close-long'}
					onClick={onClose}
					size={full ? 'large' : 'small'}
					tabindex="0"
				/>
			</div>
		);
	}
}

Header.PROPS = {
	full: Config.bool().value(false),
	onClose: Config.func()
};

export default Header;