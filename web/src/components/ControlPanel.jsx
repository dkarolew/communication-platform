import React, {useContext, useState} from "react";
import styled from "styled-components";
import {changeDeviceMeasurementFrequency, changeDeviceState} from "../api/api";
import {UserInfoContext} from "../utils/UserInfoContext";


const ControlPanel = () => {

    const [frequency, setFrequency] = useState();
    const [error, setError] = useState('');
    const [success, setSuccess] = useState('');
    const [deviceId, setDeviceId] = useState();

    // @ts-ignore
    const {userInfo} = useContext(UserInfoContext);

    const changeState = (state: string, deviceId: number) => {
        if (deviceId === '') {
            alert("Please select device")
            return
        }

        const deviceDto = {
            deviceId: deviceId,
            userId: userInfo.userId,
            name: '',
            model: '',
            state: state,
            measurementFrequency: -1
        }

        changeDeviceState(deviceDto, userInfo.token)
            .then(response => {
                if (response.status !== 200) {
                    setError('Error during changing device state')
                    setSuccess('')
                } else {
                    setSuccess('Successfully changed device state')
                    setError('')
                }
            });
    }

    const changeFrequency = (frequency: number, deviceId: number) => {
        const deviceDto = {
            deviceId: deviceId,
            userId: userInfo.userId,
            name: '',
            model: '',
            state: 'ACTIVE',
            measurementFrequency: frequency
        }

        changeDeviceMeasurementFrequency(deviceDto, userInfo.token)
            .then(response => {
                if (response.status !== 200) {
                    setError('Error during changing frequency')
                    setSuccess('')
                } else {
                    setSuccess('Successfully changed frequency')
                    setError('')
                }
            });
    }

    return (
        <StyledDiv>
            <h1>Control panel</h1>
            <p>
                You can start/stop and change frequency of measurements by controls below.
            </p>
            <div style={{paddingTop: '20px'}}>
                <StyledLabel>Device ID</StyledLabel>
                <StyledInput
                    type='text'
                    placeholder='ID'
                    value={deviceId}
                    onChange={(e) =>
                        setDeviceId(e.target.value)}
                />
            </div>
            <div style={{paddingTop: '20px'}}>
                <button
                    style={{background: 'darkgreen', border: 'black', width: '350px', height: '40px'}}
                    className="btn btn-primary"
                    onClick={() => changeState('ACTIVE', deviceId)}>
                    Start
                </button>
            </div>
            <div style={{paddingTop: '20px'}}>
                <button
                    style={{background: 'darkred', border: 'black', width: '350px', height: '40px'}}
                    className="btn btn-primary"
                    onClick={() => changeState('NOT_ACTIVE', deviceId)}>
                    Stop
                </button>
            </div>
            <div style={{paddingTop: '40px'}}>
                <label style={{fontSize: '18px'}}>Frequency of the measurement [ms]</label>
                <input
                    type="text"
                    className="form-control"
                    value={frequency}
                    placeholder='Frequency'
                    style={{border: '2px solid black', width: '350px'}}
                    onChange={(e) => setFrequency(e.target.value)}
                />
                <div style={{paddingTop: '20px'}}>
                    <button
                        style={{background: 'darkblue', border: 'black', width: '350px', height: '40px'}}
                        className="btn btn-primary"
                        onClick={() => changeFrequency(frequency, deviceId)}>
                        Change
                    </button>
                </div>
                {userInfo.role === 'ADMIN' && (
                    <div style={{paddingTop: '40px'}}>
                        <label style={{fontSize: '18px'}}>Enable or disable device</label>
                        <div style={{paddingTop: '5px'}}>
                            <button
                                style={{background: '#cccc00', border: 'black', width: '350px', height: '40px'}}
                                className="btn btn-primary"
                                onClick={() => changeState("NOT_ACTIVE", deviceId)}>
                                Enable
                            </button>
                        </div>
                        <div style={{paddingTop: '20px'}}>
                            <button
                                style={{background: 'black', border: 'black', width: '350px', height: '40px'}}
                                className="btn btn-primary"
                                onClick={() => changeState("DISABLED", deviceId)}>
                                Disable
                            </button>
                        </div>
                    </div>
                )}
                {error && (
                    <p
                        className="alert alert-danger"
                        role="alert"
                        style={{width: '350px', marginTop: '20px'}}
                    >
                        {error}
                    </p>
                )}
                {success && (
                    <p
                        className="alert alert-success"
                        role="alert"
                        style={{width: '350px', marginTop: '20px'}}
                    >
                        {success}
                    </p>
                )}
            </div>
        </StyledDiv>
    )
}

export default ControlPanel


const StyledDiv = styled.div`
    margin: 20px;
`;

const StyledLabel = styled.label`
    display: block;
`;

const StyledInput = styled.input`
    width: 350px;  
    height: 40px;
    padding: 3px 7px;
    font-size: 17px;
`;