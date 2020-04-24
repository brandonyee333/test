import Component from 'metal-jsx';

import Button from '../Button';
import ElementContainer from './ElementContainer';
import Icon from '../Icon';
import Kit from './Kit';
import MenuList from '../menu-list';
import Overlay from '../Overlay';

const styles = {
	maxWidth: '300px'
};

class MenuListKit extends Component {
	render() {
		return (
			<Kit header="MenuList">
				<ElementContainer header="MenuList">
					<MenuList style={styles}>
						<MenuList.Label>{'Label'}</MenuList.Label>

						<MenuList.Item active={true}>{'Menu Item One (Active)'}</MenuList.Item>

						<MenuList.Item href="javascript:;">{'Menu Item Two (Link)'}</MenuList.Item>

						<MenuList.Item>{'Menu Item Three (Really long and should overflow the container.)'}</MenuList.Item>

						<MenuList.Item justifyBetween={true}>
							{'Menu Item Three (Justify Between) '}
							<Icon name="arrow-right-short" size="small" />
						</MenuList.Item>
					</MenuList>
				</ElementContainer>

				<ElementContainer header="Overlay MenuList">
					<Overlay>
						<Button role="secondary">{'Hover Me'}</Button>

						<MenuList style={styles}>
							<MenuList.Label>{'Label'}</MenuList.Label>

							<MenuList.Item>{'Menu Item One'}</MenuList.Item>

							<MenuList.Item hideOnClick={true}>{'Menu Item Two (Hide on click)'}</MenuList.Item>

							<MenuList.Item>{'Menu Item Three'}</MenuList.Item>

							<MenuList.Item>{'Menu Item Three (Really long and should overflow the container.)'}</MenuList.Item>
						</MenuList>
					</Overlay>
				</ElementContainer>
			</Kit>
		);
	}
}

export default MenuListKit;