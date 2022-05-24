import React from "react";
import {BASE_API_URL} from "../utils/constans";
import styled from "styled-components";


const AdminPanel = () => {

    return (
        <div>
            <h1>Admin panel</h1>
            <StyledParagraph>
                Here you view some statistics and download a summary report.
            </StyledParagraph>
            <div style={{paddingTop: '30px'}}>
                <a
                    style={{background: 'black', border: 'black', width: '300px', height: '40px'}}
                    className="btn btn-primary"
                    href={BASE_API_URL + "/report/export"}
                    download>
                    Download summary report
                </a>
            </div>
        </div>
    )
}

export default AdminPanel


const StyledParagraph = styled.p`
    padding-top: 30px;
`;