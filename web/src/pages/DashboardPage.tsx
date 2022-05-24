import * as React from "react";
import ChartsPanel from "../components/ChartsPanel";
import TwoPanelsContainer from "../components/TwoPanelsContainer";
import ControlPanel from "../components/ControlPanel";


const DashboardPage = () => {

    const humidity = Array.from({length: 100}, () => Math.floor(Math.random() * 100));

    return (
        <TwoPanelsContainer
            leftPanel={<ChartsPanel humidity={humidity} />}
            rightPanel={<ControlPanel />}
        />

    )
}

export default DashboardPage
