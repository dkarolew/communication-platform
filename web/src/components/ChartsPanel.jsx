import React, {useContext, useEffect, useState} from "react";
import {CartesianGrid, Legend, Line, LineChart, Tooltip, XAxis, YAxis} from "recharts";
import Select from 'react-dropdown-select';
import {UserInfoContext} from "../utils/UserInfoContext";
import {fetchAllDevices, fetchDevicesForUser, fetchMeasurements} from "../api/api";
import styled from "styled-components";


const ChartsPanel = () => {

    const [temperature, setTemperature] = useState([]);
    const [humidity, setHumidity] = useState([]);
    const [deviceId, setDeviceId] = useState(-1);
    const [devices, setDevices] = useState([]);

    // @ts-ignore
    const {userInfo} = useContext(UserInfoContext);

    const prepareDeviceOptions = (data) => {
        let deviceOptions = [];
        for (let i = 0; i < data.length; i++) {
            deviceOptions.push({
                "value": data[i].deviceId,
                "label": data[i].deviceId.toString()
            });
        }
        return deviceOptions;
    }

    const prepareMeasurements = (temperature, humidity) => {
        let measurements = [];
        for (let i = 0; i < temperature.length; i++) {
            measurements.push({
                "date": temperature[i].measurementTime.substring(5, 19).replace('T', ' '),
                "temperature": temperature[i]?.value,
                "humidity": humidity[i]?.value
            });
        }
        return measurements;
    }

    useEffect(() => {
        if (userInfo.role === "ADMIN") {
            fetchAllDevices(userInfo.token)
                .then(response => {
                    if (response.status === 200) {
                        response.json().then(data => {
                            setDevices(data)
                        })
                    }
                });
        } else {
            fetchDevicesForUser(userInfo.token, userInfo.userId)
                .then(response => {
                    if (response.status === 200) {
                        response.json().then(data => {
                            setDevices(data)
                        })
                    }
                });
        }
    }, [devices, userInfo.token, userInfo.userId, userInfo.role]);

    useEffect(() => {
        fetchMeasurements(userInfo.token, deviceId, "TEMPERATURE")
            .then(data => setTemperature(data));
    }, [temperature, userInfo.token, deviceId]);

    useEffect(() => {
        fetchMeasurements(userInfo.token, deviceId, "HUMIDITY")
            .then(data => setHumidity(data));
    }, [humidity, userInfo.token, deviceId]);

    return (
        <StyledDiv>
            <h1>
                Measurements
            </h1>
            <p>
                Here you can monitor measurements made by IoT devices.
            </p>
            <label>Device ID:</label>
            <div style={{paddingTop: '10px'}}>
                <Select
                    style={{width: '20%'}}
                    disabled={false}
                    onChange={(device) => setDeviceId(device[0].value)}
                    options={prepareDeviceOptions(devices)}
                    value={deviceId}
                    values={[]}
                />
            </div>
            <div style={{paddingTop: '20px'}}>
                <LineChart
                    width={1200}
                    height={500}
                    data={prepareMeasurements(temperature, humidity)}
                    margin={{ top: 5, right: 30, left: 20, bottom: 5 }}
                >
                    <CartesianGrid strokeDasharray="3 3" />
                    <XAxis dataKey="date" interval={temperature.length % 5} />
                    <YAxis yAxisId="left" />
                    <YAxis yAxisId="right" orientation="right" />
                    <Tooltip />
                    <Legend />
                    <Line yAxisId="left" type="monotone" dataKey="temperature" stroke="#c70039" activeDot={{ r: 5 }} />
                    <Line yAxisId="right" type="monotone" dataKey="humidity" stroke="#1a1898" activeDot={{ r: 5 }} />
                </LineChart>
            </div>
        </StyledDiv>
    )
}

export default ChartsPanel


const StyledDiv = styled.div`
    margin: 20px;
`;