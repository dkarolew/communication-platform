import {BASE_API_URL} from "../utils/constans";
import {DeviceDto, SignInDto, SignUpDto} from "./model";


export async function signUp(signUpDto: SignUpDto) : Promise<any> {
    return await fetch(BASE_API_URL + "/auth/register", {
        method: 'POST',
        headers: {
            'Content-type': 'application/json'
        },
        body: JSON.stringify(signUpDto)
    })
}

export async function signIn(signInDto: SignInDto) : Promise<any> {
    return await fetch(BASE_API_URL + "/auth/login", {
        method: 'POST',
        headers: {
            'Content-type': 'application/json'
        },
        body: JSON.stringify(signInDto)
    })
}

export async function fetchMeasurements(token: string) : Promise<any> {
    const response = await fetch(BASE_API_URL + "/measurement", {
        method: 'GET',
        headers: {
            'Authorization': token
        }
    });

    return response.json();
}

export async function changeDeviceState(deviceDto: DeviceDto, token: string) : Promise<any> {
    return await fetch(BASE_API_URL + "/device/change-state", {
        method: 'POST',
        headers: {
            'Authorization': token,
            'Content-type': 'application/json'
        },
        body: JSON.stringify(deviceDto)
    });
}

export async function changeDeviceMeasurementFrequency(deviceDto: DeviceDto, token: string) : Promise<any> {
    return await fetch(BASE_API_URL + "/device/change-frequency", {
        method: 'POST',
        headers: {
            'Authorization': token,
            'Content-type': 'application/json'
        },
        body: JSON.stringify(deviceDto)
    });
}

export async function fetchAllDevices(token: string) : Promise<any> {
    return await fetch(BASE_API_URL + "/device", {
        method: 'GET',
        headers: {
            'Authorization': token
        }
    })
}

export async function fetchDevicesForUser(token: string, userId: number) : Promise<any> {
    return await fetch(BASE_API_URL + `/device/byUser?userId=${userId}`, {
        method: 'GET',
        headers: {
            'Authorization': token
        }
    })
}

export async function addDevice(deviceDto: DeviceDto, token: string) : Promise<any> {
    return await fetch(BASE_API_URL + "/device", {
        method: 'POST',
        headers: {
            'Authorization': token,
            'Content-type': 'application/json'
        },
        body: JSON.stringify(deviceDto)
    })
}

export async function deleteDevice(deviceId: number, token: string) : Promise<any> {
    return await fetch(BASE_API_URL + "/device/" + deviceId, {
        method: 'DELETE',
        headers: {
            'Authorization': token
        }
    })
}
