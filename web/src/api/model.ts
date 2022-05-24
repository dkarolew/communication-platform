export interface SignUpDto {
    firstName: string,
    lastName: string,
    email: string,
    password: string
}

export interface SignInDto {
    email: string,
    password: string
}

export interface JwtDto {
    userId: number,
    firstName: string,
    lastName: string,
    email: string,
    role: string,
    jwt: string
}

export interface DeviceDto {
    deviceId: number,
    userId: number,
    name: string,
    model: string,
    state: string,
    measurementFrequency: number
}
