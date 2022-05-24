import React from 'react';
import styled from "styled-components";


const TwoPanelsContainer = ({leftPanel, rightPanel}) => {

    return (
        <MainDiv className='split-screen'>
            <LeftDiv>
                {leftPanel}
            </LeftDiv>
            <RightDiv>
                {rightPanel}
            </RightDiv>
        </MainDiv>
    )
}

export default TwoPanelsContainer


const MainDiv = styled.div`
    display: flex;
    flexDirection: 'row';
`;

const LeftDiv = styled.div`
    width: 60%;
`;

const RightDiv = styled.div`
    width: 40%;
`;



