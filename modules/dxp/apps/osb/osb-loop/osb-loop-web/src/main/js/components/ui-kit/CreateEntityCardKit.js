import Component from 'metal-jsx';

import CreateEntityCard from '../CreateEntityCard';
import ElementContainer from './ElementContainer';
import Kit from './Kit';

class CreateEntityCardKit extends Component {
	created() {
		this.handleClick_ = this.handleClick_.bind(this);
	}

	handleClick_() {
		alert('you clicked the button');
	}

	render() {
		return (
			<Kit card={false} header="CreateEntityCard">
				<ElementContainer>
					<CreateEntityCard label="create something!" onClick={this.handleClick_} />
				</ElementContainer>
			</Kit>
		);
	}
}

export default CreateEntityCardKit;