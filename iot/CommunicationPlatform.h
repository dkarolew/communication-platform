#ifndef CommunicationPlatform_h
#define CommunicationPlatform_h

#include <string>


class IoTHttpClient {
public:
    IoTHttpClient(int deviceId, std::string name, std::string model, int measurementFrequency);

    void connect(const char* ssid, const char* password);
    void login(const std::string& email, const std::string& password);
    void updateState();
    void sendMessage(const std::string& type, const float value);
    void disconnect();

    int getDeviceId() const { return _deviceId; }
    void setDeviceId(int deviceId) { _deviceId = deviceId; }

    std::string getStatus() const { return _status; }
    void setStatus(std::string status) { _status = std::move(status); }

    int getMeasurementFrequency() const { return _measurementFrequency; }
    void setMeasurementFrequency(int measurementFrequency) { _measurementFrequency = measurementFrequency; }

    std::string getToken() const { return _token; }
    void setToken(std::string token) { _token = std::move(token); }
  
private:
    int _deviceId;
    std::string _name;
    std::string _model;
    std::string _status;
    int _measurementFrequency;
    std::string _token;
};


#endif
