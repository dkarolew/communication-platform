import '@testing-library/jest-dom';
import { render, screen } from '@testing-library/react';
import Header from '../Header';
import * as React from 'react';

describe('Header component', () => {

    it("should show title", () => {
        //given
        render(<Header />);

        //when
        const title = screen.getByText("Communication platform");

        //then
        expect(title).toBeInTheDocument();
    });

    it("should match snapshot", () => {
        //when
        const result = render(<Header />);

        //then
        expect(result).toMatchSnapshot();
    });
});