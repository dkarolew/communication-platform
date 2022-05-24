import React, {useContext} from "react";
import styled from "styled-components";
import {UserInfoContext} from "../utils/UserInfoContext";


const ProfilePanel = () => {

    // @ts-ignore
    const {userInfo} = useContext(UserInfoContext);

    return (
        <div>
            <h1>
                My profile
            </h1>
            <p>
                Here you can view information about your profile and add/remove IoT device.
            </p>
            <StyledTable>
                <thead>
                <tr>
                    <th>First name</th>
                    <th>Last name</th>
                    <th>Email</th>
                    <th>Role</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>{userInfo.firstName}</td>
                    <td>{userInfo.lastName}</td>
                    <td>{userInfo.email}</td>
                    <td>{userInfo.role}</td>
                </tr>
                </tbody>
            </StyledTable>
        </div>
    )
}

export default ProfilePanel


const StyledTable = styled.table`
    border-collapse: collapse;
    margin: 25px 0;
    font-size: 0.9em;
    font-family: sans-serif;
    min-width: 800px;
    box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);  
`;