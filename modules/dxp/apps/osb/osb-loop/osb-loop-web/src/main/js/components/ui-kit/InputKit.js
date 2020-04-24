import Component from 'metal-jsx';

import Button from '../Button';
import ElementContainer from './ElementContainer';
import Input from '../Input';
import Kit from './Kit';

class InputKit extends Component {
	created() {
		this.focusInput_ = this.focusInput_.bind(this);
	}

	focusInput_() {
		this.components.input.focus();
	}

	render() {
		return (
			<Kit header="Input">
				<ElementContainer header="focus on attached">
					<Input
						focusOnAttached={true}
						icon="magnifier"
						placeholder="Search"
					/>
				</ElementContainer>

				<ElementContainer header="focus onclick">
					<Button onClick={this.focusInput_} role="primary">{'Focus Input'}</Button>
				</ElementContainer>

				<ElementContainer>
					<Input
						icon="magnifier"
						placeholder="Search"
						ref="input"
					/>
				</ElementContainer>

				<ElementContainer header="no icon">
					<Input
						placeholder="Full Name"
					/>
				</ElementContainer>
			</Kit>
		);
	}
}

export default InputKit;