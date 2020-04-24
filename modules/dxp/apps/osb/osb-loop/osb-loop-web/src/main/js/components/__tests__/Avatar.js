import Avatar from '../Avatar';
import LoopAssets from '../../tests/loop-assets';
import {getInitials} from '../../lib/util';

const testDivision = LoopAssets.getDivision();
const testPerson = LoopAssets.getPerson();

describe(
	'Avatar',
	() => {
		let component;

		afterEach(
			() => {
				if (component) {
					component.dispose();
				}
			}
		);

		it(
			'renders',
			() => {
				component = new Avatar(
					{
						entity: testPerson
					}
				);

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'renders placeholder if no profile image',
			() => {
				component = new Avatar(
					{
						entity: {
							entityClassPK: 0,
							profileImageURL: null
						}
					}
				);

				const placeholder = component.element.querySelector('.avatar-placeholder');

				expect(placeholder).toBeTruthy();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'renders an icon placeholder for entities other than people',
			() => {
				component = new Avatar(
					{
						entity: {
							...testDivision,
							profileImageURL: null
						}
					}
				);

				const svg = component.element.querySelector('svg');

				expect(svg).toBeTruthy();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'renders initials placeholder for people',
			() => {
				component = new Avatar(
					{
						entity: {
							...testPerson,
							name: 'Test Test',
							profileImageURL: null
						}
					}
				);

				expect(getInitials).toBeCalled();

				expect(component).toMatchSnapshot();
			}
		);
	}
);