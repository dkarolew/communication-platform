import React from "react";
import styled from "styled-components";
import TwoPanelsContainer from "../components/TwoPanelsContainer";
import ProfilePanel from "../components/ProfilePanel";
import UserDevicesControlPanel from "../components/UserDevicesControlPanel";


const ProfilePage = () => {

    return (
        <StyledDiv>
            <TwoPanelsContainer
                leftPanel={<ProfilePanel />}
                rightPanel={<UserDevicesControlPanel />}
            />
        </StyledDiv>
    )
}

export default ProfilePage


const StyledDiv = styled.div`
    margin: 20px;
`;
