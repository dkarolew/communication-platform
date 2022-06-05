import * as React from "react";
import ChartsPanel from "../components/ChartsPanel";
import TwoPanelsContainer from "../components/TwoPanelsContainer";
import ControlPanel from "../components/ControlPanel";


const DashboardPage = () => {

    return (
        <TwoPanelsContainer
            leftPanel={<ChartsPanel />}
            rightPanel={<ControlPanel />}
        />

    )
}

export default DashboardPage
