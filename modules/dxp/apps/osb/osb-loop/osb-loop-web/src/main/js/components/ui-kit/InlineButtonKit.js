import Component from 'metal-jsx';

import ElementContainer from './ElementContainer';
import InlineButton from '../InlineButton';
import Kit from './Kit';

class InlineButtonsKit extends Component {
	created() {
		this.handleClick_ = this.handleClick_.bind(this);
	}

	handleClick_() {
		alert('Clicked!');
	}

	render() {
		return (
			<Kit header="InlineButton">
				<ElementContainer header="InlineButton">
					<InlineButton onClick={this.handleClick_}>{'InlineButton'}</InlineButton>
				</ElementContainer>
			</Kit>
		);
	}
}

export default InlineButtonsKit;