import React, {useContext, useEffect, useState} from "react";
import {Cell, Legend, Pie, PieChart} from "recharts";
import {fetchAllDevices} from "../api/api";
import {UserInfoContext} from "../utils/UserInfoContext";
import styled from "styled-components";


const StatisticsPanel = () => {

    // @ts-ignore
    const {userInfo} = useContext(UserInfoContext);

    const colorStates = ["#007700", "#bb0000", "#000000"];
    const [devices, setDevices] = useState([]);

    useEffect(() => {
        fetchAllDevices(userInfo.token)
            .then(response => {
                if (response.status === 200) {
                    response.json().then(data => setDevices(data))
                }
        });
    }, [devices, userInfo.token])

    function filterActive(v) {
        return v.state === 'ACTIVE'
    }

    function filterNotActive(v) {
        return v.state === 'NOT_ACTIVE'
    }

    function filterDisabled(v) {
        return v.state === 'DISABLED'
    }

    const devicesData = [
        {
            "name": "Active",
            "value": devices.filter(filterActive).length
        },
        {
            "name": "Inactive",
            "value": devices.filter(filterNotActive).length
        },
        {
            "name": "Disabled",
            "value": devices.filter(filterDisabled).length
        },
    ];

    return (
        <>
            <h1>Statistics panel</h1>
            <StyledParagraph>
                Registered IoT devices states:
            </StyledParagraph>
            <div style={{paddingTop: '30px'}}>
                <PieChart width={400} height={400}>
                    <Pie
                        data={devicesData}
                        dataKey="value"
                        nameKey="name"
                        cx="50%"
                        cy="50%"
                        outerRadius={150}
                        innerRadius={50}
                        fill="#bb0000"
                        isAnimationActive={false}
                        label
                    >
                        {devicesData.map((entry, index) =>
                            <Cell fill={colorStates[index % colorStates.length]} />)}
                    </Pie>
                    <Legend verticalAlign="top" height={36}/>
                </PieChart>
            </div>
        </>
    )
}

export default StatisticsPanel


const StyledParagraph = styled.p`
    padding-top: 30px;
`;