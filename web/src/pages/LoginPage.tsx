import React, {useContext, useState} from "react";
import styled from "styled-components";
import {signIn} from "../api/api";
import {UserInfoContext} from "../utils/UserInfoContext";
import {useNavigate} from "react-router-dom";
import {JwtDto} from "../api/model";


const LoginPage = () => {

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [loginError, setLoginError] = useState('');
    const navigate = useNavigate();

    // @ts-ignore
    const {setUserInfo} = useContext(UserInfoContext);

    const onSubmit = (e: any) => {
        e.preventDefault()

        if (!email || !password) {
            alert('Email or password was not provided')
            return
        }

        signIn({email: email, password: password})
            .then(response => {
                if (response.status === 200) {
                    response.json().then((data: JwtDto) => {
                        setUserInfo({
                            userId: data.userId,
                            firstName: data.firstName,
                            lastName: data.lastName,
                            email: data.email,
                            role: data.role,
                            token: 'Bearer ' + data.jwt,
                            isLoggedIn: true
                        })
                        navigate('/')
                    })
                } else if (response.status === 401) {
                    setLoginError('Invalid credentials')
                } else if (response.status === 500) {
                    setLoginError('Account is not activated')
                }
            });

        setEmail('')
        setPassword('')
    }

    return (
        <StyledDiv>
            <StyledImage src="login-icon.jpg" alt='login-icon' />
            <form>
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
                    Sign in
                </button>
                {loginError && (
                    <p className="alert alert-danger" role="alert">
                        {loginError}
                    </p>
                )}
            </form>
        </StyledDiv>
    );
};

export default LoginPage;


const StyledDiv = styled.div`
    position: absolute;
    left: 50%;
    top: 50%;
    width: 30%;
    -webkit-transform: translate(-50%, -50%);
    transform: translate(-50%, -50%);
`;

const StyledImage = styled.img`
    position: absolute;
    left: 50%;
    top: -30%;
    width: 30%;
    -webkit-transform: translate(-50%, -50%);
    transform: translate(-50%, -50%);  
`;