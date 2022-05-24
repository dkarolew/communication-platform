export function setLocalStorage(key: string, value: any) {
    localStorage.setItem(key, JSON.stringify(value));
}

export function removeFromLocalStorage(key: string) {
    localStorage.removeItem(key);
}

export function getLocalStorage(key: string, initialValue: any) {
    try {
        const value = localStorage.getItem(key);
        return value ? JSON.parse(value) : initialValue;
    } catch (e) {
        return initialValue;
    }
}