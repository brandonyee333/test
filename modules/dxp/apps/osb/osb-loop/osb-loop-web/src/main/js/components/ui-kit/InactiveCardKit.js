import Component from 'metal-jsx';

import ElementContainer from './ElementContainer';
import InactiveCard from '../InactiveCard';
import Kit from './Kit';

class InactiveCardKit extends Component {
	render() {
		return (
			<Kit card={false} header="InactiveCard">
				<ElementContainer>
					<InactiveCard name="Test Test" />
				</ElementContainer>
			</Kit>
		);
	}
}

export default InactiveCardKit;