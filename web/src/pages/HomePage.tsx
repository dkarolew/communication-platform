import React from "react";
import Header from "../components/Header";


const HomePage = () => {

    return (
        <div style={{marginLeft: '20px', marginTop: '30px'}}>
            <Header />
            <p style={{marginTop: '30px'}}>
                A universal communication framework for Internet of Things subsystems.
            </p>
            <h2 style={{marginTop: '90px'}}>Contact</h2>
            <p style={{marginTop: '30px'}}>
                In case of problems, do not hesitate and send an&nbsp;
                <a href = "mailto:communication.platformm@gmail.com">
                    e-mail
                </a>
                &nbsp;to our support team.
            </p>
        </div>
    )
}

export default HomePage