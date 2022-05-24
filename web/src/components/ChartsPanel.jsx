import React, {useContext, useEffect, useState} from "react";
import {CartesianGrid, Legend, Line, LineChart, Tooltip, XAxis, YAxis} from "recharts";
import {UserInfoContext} from "../utils/UserInfoContext";
import {fetchMeasurements} from "../api/api";
import styled from "styled-components";


const ChartsPanel = (humidity) => {

    const [measurements, setMeasurements] = useState([]);

    // @ts-ignore
    const {userInfo} = useContext(UserInfoContext);

    const prepareMeasurements = (data) => {
        let measurements = [];
        for (let i = 0; i < data.length; i++) {
            measurements.push({
                "date": data[i].measurementTime.substring(0, 10),
                "temperature": data[i].value,
                "humidity": humidity.humidity[i]
            });
        }
        return measurements;
    }

    useEffect(() => {
        fetchMeasurements(userInfo.token)
            .then(data => setMeasurements(data));
    }, [measurements, userInfo.token]);

    return (
        <StyledDiv>
            <h1>
                Measurements
            </h1>
            <p>
                Here you can monitor measurements made by IoT devices.
            </p>
            <LineChart
                width={1200}
                height={500}
                data={prepareMeasurements(measurements)}
                margin={{ top: 5, right: 30, left: 20, bottom: 5 }}
            >
                <CartesianGrid strokeDasharray="3 3" />
                <XAxis dataKey="date" />
                <YAxis yAxisId="left" />
                <YAxis yAxisId="right" orientation="right" />
                <Tooltip />
                <Legend />
                <Line yAxisId="left" type="monotone" dataKey="temperature" stroke="#c70039" activeDot={{ r: 5 }} />
                <Line yAxisId="right" type="monotone" dataKey="humidity" stroke="#1a1898" activeDot={{ r: 5 }} />
            </LineChart>
        </StyledDiv>
    )
}

export default ChartsPanel


const StyledDiv = styled.div`
    margin: 20px;
`;