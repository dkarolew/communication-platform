import React, {useContext, useState} from "react";
import styled from "styled-components";
import {changeDeviceMeasurementFrequency, changeDeviceState} from "../api/api";
import {UserInfoContext} from "../utils/UserInfoContext";


const ControlPanel = () => {

    const [frequency, setFrequency] = useState(1000);
    const [error, setError] = useState('');
    const [success, setSuccess] = useState('');

    // @ts-ignore
    const {userInfo} = useContext(UserInfoContext);

    const changeState = (state: string) => {
        const deviceDto = {
            deviceId: 1,
            userId: 1,
            name: '',
            model: '',
            state: state,
            measurementFrequency: 5000
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

    const changeFrequency = (frequency: number) => {
        const deviceDto = {
            deviceId: 1,
            userId: 1,
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
                <button
                    style={{background: 'darkgreen', border: 'black', width: '350px', height: '40px'}}
                    className="btn btn-primary"
                    onClick={() => changeState('ACTIVE')}>
                    Start
                </button>
            </div>
            <div style={{paddingTop: '20px'}}>
                <button
                    style={{background: 'darkred', border: 'black', width: '350px', height: '40px'}}
                    className="btn btn-primary"
                    onClick={() => changeState('NOT_ACTIVE')}>
                    Stop
                </button>
            </div>
            <div style={{paddingTop: '40px'}}>
                <label style={{fontSize: '18px'}}>Frequency of the measurement [ms]</label>
                <input
                    type="text"
                    className="form-control"
                    value={frequency}
                    style={{border: '2px solid black', width: '350px'}}
                    onChange={(e) => setFrequency(e.target.value)}
                />
                <div style={{paddingTop: '20px'}}>
                    <button
                        style={{background: 'darkblue', border: 'black', width: '350px', height: '40px'}}
                        className="btn btn-primary"
                        onClick={() => changeFrequency(frequency)}>
                        Change
                    </button>
                </div>
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