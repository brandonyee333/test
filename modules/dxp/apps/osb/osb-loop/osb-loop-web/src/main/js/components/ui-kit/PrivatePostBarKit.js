import Component from 'metal-jsx';

import ElementContainer from './ElementContainer';
import Kit from './Kit';
import PrivatePostBar from '../PrivatePostBar';

class PrivatePostBarKit extends Component {
	render() {
		const style = {
			'display': 'block',
			'width': '614px'
		};

		return (
			<Kit card={false} header="Private Post Bar">
				<ElementContainer header="Private Post Bar" style={style}>
					<PrivatePostBar />
				</ElementContainer>
			</Kit>
		);
	}
}

export default PrivatePostBarKit;