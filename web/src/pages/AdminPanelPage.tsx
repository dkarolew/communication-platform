import React from "react";
import styled from "styled-components";
import StatisticsPanel from "../components/StatisticsPanel";
import TwoPanelsContainer from "../components/TwoPanelsContainer";
import AdminPanel from "../components/AdminPanel";


const AdminPanelPage = () => {

    return (
        <StyledDiv>
            <TwoPanelsContainer
                leftPanel={<AdminPanel />}
                rightPanel={<StatisticsPanel />}
            />
        </StyledDiv>
    )
}

export default AdminPanelPage


const StyledDiv = styled.div`
    margin: 20px;
`;