import Component from 'metal-jsx';

import CreatePageCard from '../CreatePageCard';
import ElementContainer from './ElementContainer';
import Kit from './Kit';

class CreatePageCardKit extends Component {
	created() {
		this.showAlert_ = this.showAlert_.bind(this);
	}

	showAlert_() {
		alert('The button works!');
	}

	render() {
		const styles = {width: '851px'};

		return (
			<Kit card={false} header="CreatePageCard">
				<ElementContainer header="Default" style={styles}>
					<CreatePageCard />
				</ElementContainer>

				<ElementContainer header="Show Button - True" style={styles}>
					<CreatePageCard onClick={this.showAlert_} showButton={true} />
				</ElementContainer>
			</Kit>
		);
	}
}

export default CreatePageCardKit;