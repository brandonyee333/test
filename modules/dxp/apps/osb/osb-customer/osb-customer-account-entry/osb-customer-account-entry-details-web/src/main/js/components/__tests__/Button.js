import React from 'react';
import {render} from '@testing-library/react';

import Button from '../Button';

describe('Button', () => {
	it('renders correctly', () => {
		const {container} = render(<Button>Button</Button>);

		expect(container).toMatchSnapshot();
	});

	it('renders as disabled correctly', () => {
		const {container} = render(<Button disabled>Disabled Button</Button>);

		expect(container).toMatchSnapshot();
	});

	it('renders as primary style correctly', () => {
		const {container} = render(
			<Button display='primary'>Primary Button</Button>
		);

		expect(container).toMatchSnapshot();
	});

	it('renders as a link correctly', () => {
		const {container} = render(<Button href='/'>Link</Button>);

		expect(container).toMatchSnapshot();
	});

	it('renders as an icon correctly', () => {
		const {container} = render(<Button icon>+</Button>);

		expect(container).toMatchSnapshot();
	});

	it('renders as a certain size correctly', () => {
		const {container} = render(<Button size='lg'>Large Button</Button>);

		expect(container).toMatchSnapshot();
	});

	it('renders as a certain type correctly', () => {
		const {container} = render(
			<Button type='submit'>Submit Button</Button>
		);

		expect(container).toMatchSnapshot();
	});

	it('renders with a value correctly', () => {
		const {container} = render(
			<Button value='1'>Value of 1 Button</Button>
		);

		expect(container).toMatchSnapshot();
	});
});
