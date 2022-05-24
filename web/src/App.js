import {useEffect, useState} from "react";
import Header from "./components/Header";
import fetchMeasurements from "./api/api";


function App() {

  const [measurements, setMeasurements] = useState([]);

  useEffect(() => {
    const token = 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2huLnNtaXRoQGVtYWlsLmNvbSIsImlhdCI6MTY1MjY0NDU5NCwiZXhwIjoxNjUyNzMwOTk0fQ.c6MMmblUopL_8Iq60uOMUz0CYONqVqwH7taTcoxnVFiYmjY5wu4lw37nc-PmEiE_Ky8TFj6JEdQDh4gMkBIYxw'
    fetchMeasurements(token).then(data => setMeasurements(data))
  }, [measurements]);

  return (
    <div>
      <Header/>
        <p style={{margin: '20px'}}>
          Measurements: <br/>
          {JSON.stringify(measurements)}
        </p>
    </div>
  );
}

export default App;
