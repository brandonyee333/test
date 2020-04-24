import Component from 'metal-jsx';

import ElementContainer from './ElementContainer';
import ElementDisplay from './ElementDisplay';
import Kit from './Kit';

class ColorsKit extends Component {
	render() {
		return (
			<Kit header="Colors">
				<ElementContainer header="Brand">
					{
						['primary', 'secondary', 'theme'].map(
							(name, i) => (
								<ElementDisplay description={`$${name}`} key={i}>
									<div class={`color-circle color-${name} element`} />
								</ElementDisplay>
							)
						)
					}
				</ElementContainer>

				<ElementContainer header="Feedback">
					{
						['alert', 'error', 'success'].map(
							(name, i) => (
								<ElementDisplay description={`$${name}`} key={i}>
									<div class={`color-circle color-${name} element`} />
								</ElementDisplay>
							)
						)
					}
				</ElementContainer>

				<ElementContainer header="Other">
					{
						['black', 'light-green', 'light-red', 'onix', 'orange', 'purple', 'royal-blue', 'violet'].map(
							(name, i) => (
								<ElementDisplay description={`$${name}`} key={i}>
									<div class={`color-circle color-${name} element`} />
								</ElementDisplay>
							)
						)
					}
				</ElementContainer>
			</Kit>
		);
	};
}

export default ColorsKit;