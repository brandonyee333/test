import CreatePageCard from '../CreatePageCard';
import LoopAssets from '../../tests/loop-assets';

describe(
	'CreatePageCard',
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
				component = new CreatePageCard();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'renders with button',
			() => {
				component = new CreatePageCard(
					{
						displayURL: LoopAssets.getPerson().displayURL,
						showButton: true
					}
				);

				expect(component).toMatchSnapshot();
			}
		);
	}
);