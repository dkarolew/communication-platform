#include "CommunicationPlatform.h"

#include <WiFiClient.h>
#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>
#include <ArduinoJson.h>
#include <string>


IoTHttpClient::IoTHttpClient(int deviceId, std::string name, std::string model, int measurementFrequency) {
    _deviceId = deviceId;
    _name = std::move(name);
    _model = std::move(model);
    _status = "NOT_ACTIVE";
    _measurementFrequency = measurementFrequency;
    _token = "";
}

void IoTHttpClient::connect(const char* ssid, const char* password) {
    Serial.begin(115200);
    WiFi.begin(ssid, password);
    Serial.println("Connecting");

    while(WiFi.status() != WL_CONNECTED) {
        delay(500);
        Serial.print(".");
    }

    Serial.println("");
    Serial.print("Connected to WiFi network with IP Address: ");
    Serial.println(WiFi.localIP());
}

void IoTHttpClient::login(const std::string& login, const std::string& password) {
    if(WiFi.status() == WL_CONNECTED) {
        WiFiClient client;
        HTTPClient http;

        String loginUrl = "http://192.168.0.103:8080/api/v1/auth/login";

        http.begin(client, loginUrl.c_str());
        http.addHeader("Content-Type", "application/json");

        DynamicJsonDocument doc(1024);
        doc["email"] = login;
        doc["password"] = password;
        String payload;
        serializeJson(doc, payload);

        int httpResponseCode = http.POST(payload);

        if (httpResponseCode > 0) {
            Serial.print("HTTP response code: ");
            Serial.println(httpResponseCode);
            String response = http.getString();
            Serial.print("Response: ");
            Serial.println(response);
            
            DynamicJsonDocument doc(1024);
            deserializeJson(doc, response);
            _token = std::string(doc["jwt"]);
        }
        else {
            Serial.print("Error code: ");
            Serial.println(httpResponseCode);
        }
        http.end();
    }
    else {
        Serial.println("WiFi Disconnected");
    }
}

void IoTHttpClient::updateState() {
    if(WiFi.status() == WL_CONNECTED){
        WiFiClient client;
        HTTPClient http;

        String getDeviceStateUrl = "http://192.168.0.103:8080/api/v1/device/" + String(_deviceId);

        http.begin(client, getDeviceStateUrl.c_str());
        http.addHeader("Authorization", String("Bearer ") + _token.c_str());

        int httpResponseCode = http.GET();

        if (httpResponseCode > 0) {
            Serial.print("HTTP response code: ");
            Serial.println(httpResponseCode);
            String response = http.getString();
            Serial.print("Response: ");
            Serial.println(response);
            
            DynamicJsonDocument doc(1024);
            deserializeJson(doc, response);
            _status = std::string(doc["state"]);
            _measurementFrequency = doc["measurementFrequency"];
        }
        else {
            Serial.print("Error code: ");
            Serial.println(httpResponseCode);
        }
        http.end();
    }
    else {
        Serial.println("WiFi Disconnected");
    }
}

void IoTHttpClient::sendMessage(const std::string& type, const float value) {
    if(WiFi.status() == WL_CONNECTED){
        WiFiClient client;
        HTTPClient http;
        
        String sendMeasurementUrl = "http://192.168.0.103:8080/api/v1/measurement";

        http.begin(client, sendMeasurementUrl.c_str());
        http.addHeader("Authorization", String("Bearer ") + _token.c_str());
        http.addHeader("Content-Type", "application/json");

        DynamicJsonDocument doc(1024);
        doc["deviceId"] = _deviceId;
        doc["type"] = type;
        doc["value"] = value;
        String payload;
        serializeJson(doc, payload);

        int httpResponseCode = http.POST(payload);

        if (httpResponseCode > 0) {
            Serial.print("HTTP response code: ");
            Serial.println(httpResponseCode);
            String response = http.getString();
            Serial.print("Response: ");
            Serial.println(response);
        }
        else {
            Serial.print("Error code: ");
            Serial.println(httpResponseCode);
        }
        http.end();
    }
    else {
        Serial.println("WiFi Disconnected");
    }
}

void IoTHttpClient::disconnect() {
    Serial.println("Disconnecting");
    WiFi.disconnect();
    Serial.println("Disconnected");
}
