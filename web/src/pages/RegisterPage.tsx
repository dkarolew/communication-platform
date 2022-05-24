import React, {useState} from "react";
import styled from "styled-components";
import {signUp} from "../api/api";


const RegisterPage = () => {

    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [registerError, setRegisterError] = useState('');
    const [registerSuccess, setRegisterSuccess] = useState('');

    const onSubmit = (e: any) => {
        e.preventDefault()

        if (!firstName || !lastName || !email || !password) {
            alert('Please full fill the register form')
            return
        }

        signUp({firstName: firstName, lastName: lastName, email: email, password: password})
            .then(response => {
                if (response.status === 200) {
                    setRegisterSuccess('Account created successfully')
                    setRegisterError('')
                } else if (response.status === 400) {
                    setRegisterError('Email is already taken')
                    setRegisterSuccess('')
                }
            });

        setFirstName('')
        setLastName('')
        setEmail('')
        setPassword('')
    }

    return (
        <StyledDiv>
            <form>
                <div className="form-outline mb-4">
                    <label>First name</label>
                    <input
                        type="text"
                        className="form-control"
                        value={firstName}
                        style={{border: '2px solid black'}}
                        onChange={(e) => setFirstName(e.target.value)}
                    />
                </div>

                <div className="form-outline mb-4">
                    <label>Last name</label>
                    <input
                        type="text"
                        className="form-control"
                        value={lastName}
                        style={{border: '2px solid black'}}
                        onChange={(e) => setLastName(e.target.value)}
                    />
                </div>

                <div className="form-outline mb-4">
                    <label>Email</label>
                    <input
                        type="email"
                        className="form-control"
                        value={email}
                        style={{border: '2px solid black'}}
                        onChange={(e) => setEmail(e.target.value)}
                    />
                </div>

                <div className="form-outline mb-4">
                    <label>Password</label>
                    <input
                        type="password"
                        className="form-control"
                        value={password}
                        style={{border: '2px solid black'}}
                        onChange={(e) => setPassword(e.target.value)}
                    />
                </div>

                <button
                    type="button"
                    className="btn btn-primary btn-block mb-4"
                    onClick={onSubmit}
                >
                    Sign up
                </button>
                {registerError && (
                    <p className="alert alert-danger" role="alert">
                        {registerError}
                    </p>
                )}
                {registerSuccess && (
                    <p className="alert alert-success" role="alert">
                        {registerSuccess}
                    </p>
                )}
            </form>
        </StyledDiv>
    )
}

export default RegisterPage


const StyledDiv = styled.div`
    position: absolute;
    left: 50%;
    top: 50%;
    width: 30%;
    -webkit-transform: translate(-50%, -50%);
    transform: translate(-50%, -50%);
`;