import React, {useContext, useEffect, useState} from "react";
import styled from "styled-components";
import {addDevice, deleteDevice, fetchAllDevices, fetchDevicesForUser} from "../api/api";
import {UserInfoContext} from "../utils/UserInfoContext";


const UserDevicesControlPanel = () => {

    const initialDeviceState = {deviceId: null, name: '', model: '', measurementFrequency: ''};
    const [showAddDeviceForm, setShowAddDeviceForm] = useState(false);
    const [showRemoveDeviceForm, setShowRemoveDeviceForm] = useState(false);
    const [device, setDevice] = useState(initialDeviceState);
    const [devices, setDevices] = useState([]);

    // @ts-ignore
    const {userInfo} = useContext(UserInfoContext);

    useEffect(() => {
        if (userInfo.role === 'ADMIN') {
            fetchAllDevices(userInfo.token, userInfo.userId)
                .then(response => {
                    if (response.status === 200) {
                        response.json().then(data => setDevices(data))
                    }
                });
        } else {
            fetchDevicesForUser(userInfo.token, userInfo.userId)
                .then(response => {
                    if (response.status === 200) {
                        response.json().then(data => setDevices(data))
                    }
                });
        }
    }, [devices, userInfo.token, userInfo.userId])

    const addNewDevice = (e: any) => {
        e.preventDefault()

        if (!device.name || !device.model || !device.measurementFrequency) {
            alert('Please full fill information about device')
            return
        }

        const deviceDto = {
            deviceId: null,
            userId: userInfo.userId,
            name: device.name,
            model: device.model,
            state: 'NOT_ACTIVE',
            measurementFrequency: device.measurementFrequency
        }
        addDevice(deviceDto, userInfo.token)
            .then(response => console.log(response))

        setDevice(initialDeviceState)
    }

    const removeDevice = (e: any) => {
        e.preventDefault()

        if (!device.deviceId) {
            alert('Please enter device ID')
            return
        }

        deleteDevice(device.deviceId, userInfo.token)
            .then(response => console.log(response))

        setDevice(initialDeviceState)
    }

    return (
        <div>
            <h1>
                Device management panel
            </h1>
            <p>
                Your IoT registered devices:
            </p>
            <StyledTable>
                <thead>
                <tr>
                    <th>Device ID</th>
                    <th>Name</th>
                    <th>Model</th>
                    <th>State</th>
                    <th>Measurement frequency [ms]</th>
                </tr>
                </thead>
                <tbody>
                {devices.map((device: any, index: number) => {
                    return (
                        <tr key={index}>
                            <td>{device.deviceId}</td>
                            <td>{device.name}</td>
                            <td>{device.model}</td>
                            <td>{device.state}</td>
                            <td>{device.measurementFrequency}</td>
                        </tr>
                    )
                })}
                </tbody>
            </StyledTable>
            {showAddDeviceForm ? (
                <div style={{width: '80%', paddingLeft: '40px'}}>
                    <StyledForm className='add-form' onSubmit={addNewDevice} style={{paddingTop: '20px'}}>
                        <StyledDiv className='form-control'>
                            <StyledLabel>Device name</StyledLabel>
                            <StyledInput
                                type='text'
                                placeholder='Name'
                                value={device.name}
                                onChange={(e) =>
                                    setDevice((prev) => ({ ...prev, name: e.target.value }))}
                            />
                        </StyledDiv>
                        <StyledDiv className='form-control'>
                            <StyledLabel>Device model</StyledLabel>
                            <StyledInput
                                type='text'
                                placeholder='Model'
                                value={device.model}
                                onChange={(e) =>
                                    setDevice((prev) => ({ ...prev, model: e.target.value }))}
                            />
                        </StyledDiv>
                        <StyledDiv className='form-control'>
                            <StyledLabel>Device measurement frequency [ms]</StyledLabel>
                            <StyledInput
                                type='text'
                                placeholder='Frequency'
                                value={device.measurementFrequency}
                                onChange={(e) =>
                                    setDevice((prev) => ({ ...prev, measurementFrequency: e.target.value }))}
                            />
                        </StyledDiv>
                        <div style={{width: '70%', paddingLeft: '100px'}}>
                            <input
                                type='submit'
                                value='Save'
                                className='btn btn-primary'
                                style={{width: '350px', background: 'darkgreen', border: 'black'}}
                            />
                        </div>
                        <div style={{width: '70%', paddingLeft: '100px', paddingTop: '10px'}}>
                            <input
                                value='Hide'
                                onClick={() => setShowAddDeviceForm(false)}
                                className='btn btn-primary'
                                style={{width: '350px', background: 'darkblue', border: 'black'}}
                            />
                        </div>
                    </StyledForm>
                </div>
            ) : (
                <div style={{paddingTop: '10px'}}>
                    <button
                        style={{width: '350px', background: 'darkgreen', border: 'black'}}
                        className="btn btn-primary"
                        onClick={() => setShowAddDeviceForm(true)}>
                        Add device
                    </button>
                </div>
            )}
            {showRemoveDeviceForm ? (
                <div style={{width: '80%', paddingLeft: '40px'}}>
                    <StyledForm className='add-form' style={{paddingTop: '30px'}}>
                        <StyledDiv className='form-control'>
                            <StyledLabel>Device ID</StyledLabel>
                            <StyledInput
                                type='text'
                                placeholder='ID'
                                value={device.deviceId}
                                onChange={(e) =>
                                    setDevice((prev) => ({ ...prev, deviceId: e.target.value }))}
                            />
                        </StyledDiv>
                        <div style={{paddingLeft: '100px'}}>
                            <input
                                value='Save'
                                className='btn btn-primary'
                                onClick={removeDevice}
                                style={{width: '350px', background: 'darkgreen', border: 'black'}}
                            />
                            <div style={{paddingTop: '10px'}}>
                                <input
                                    value='Hide'
                                    className='btn btn-primary'
                                    style={{width: '350px', background: 'darkblue', border: 'black'}}
                                    onClick={() => setShowRemoveDeviceForm(false)}
                                />
                            </div>
                        </div>
                    </StyledForm>
                </div>
            ) : (
                <div style={{paddingTop: '20px'}}>
                    <button
                        style={{background: 'darkred', border: 'black', width: '350px', height: '40px'}}
                        className="btn btn-primary"
                        onClick={() => setShowRemoveDeviceForm(true)}>
                        Remove device
                    </button>
                </div>
            )}
        </div>
    )
}

export default UserDevicesControlPanel


const StyledTable = styled.table`
    border-collapse: collapse;
    margin: 25px 0;
    font-size: 0.9em;
    font-family: sans-serif;
    min-width: 800px;
    box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);  
`;

const StyledForm = styled.form`
    margin-bottom: 40px;
`;

const StyledDiv = styled.div`
    margin: 20px -40px;
`;

const StyledLabel = styled.label`
    display: block;
`;

const StyledInput = styled.input`
    width: 100%;  
    height: 40px;
    margin: 5px;
    padding: 3px 7px;
    font-size: 17px;
`;
