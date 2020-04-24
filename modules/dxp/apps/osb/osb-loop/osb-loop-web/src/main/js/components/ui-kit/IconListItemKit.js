import Component from 'metal-jsx';

import IconListItem from '../IconListItem';
import ElementContainer from './ElementContainer';
import Kit from './Kit';

class IconListItemKit extends Component {
	render() {
		return (
			<Kit header="IconListItem">
				<ElementContainer header="email">
					<IconListItem icon="mail" label="testtest123@gmail.com" url="mailto:testtest123@gmail.com" />
				</ElementContainer>

				<ElementContainer header="phone">
					<IconListItem icon="phone" label="(123) 456-7890" url="tel:(123) 456-7890" />
				</ElementContainer>

				<ElementContainer header="Skype">
					<IconListItem icon="skype" label="testTest" url="skype:testTest?chat" />
				</ElementContainer>

				<ElementContainer header="twitter">
					<IconListItem icon="twitter" label="testTest" url="#" />
				</ElementContainer>

				<ElementContainer header="facebook">
					<IconListItem icon="facebook" label="testTest" url="#" />
				</ElementContainer>

				<ElementContainer header="github">
					<IconListItem icon="github" label="testTest" url="#" />
				</ElementContainer>
			</Kit>
		);
	}
}

export default IconListItemKit;