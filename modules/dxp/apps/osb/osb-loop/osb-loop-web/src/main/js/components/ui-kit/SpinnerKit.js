import Component from 'metal-jsx';

import Spinner from '../Spinner';
import ElementContainer from './ElementContainer';
import ElementDisplay from './ElementDisplay';
import Kit from './Kit';

class SpinnerKit extends Component {
	render() {
		return (
			<Kit header="Spinner">
				<ElementContainer>
					<ElementDisplay>
						<Spinner />
					</ElementDisplay>
				</ElementContainer>
			</Kit>
		);
	}
}

export default SpinnerKit;