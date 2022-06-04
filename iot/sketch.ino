#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>
#include <WiFiClient.h>
#include "CommunicationPlatform.h"
#include "DHT.h"

#define DHTPIN 4
#define DHTTYPE DHT22

DHT dht(DHTPIN, DHTTYPE);

const char* ssid = "Rodzinna9.3";
const char* apPassword = "Rodz93(#";

std::string login = "thomas.robinson@email.com";
std::string password = "password";

std::string sensorName = "Sensor";
std::string sensorModel = "DHT22";

IoTHttpClient iotHttpClient(1, sensorName, sensorModel, 3000);


void setup() {
    iotHttpClient.connect(ssid, apPassword);

    iotHttpClient.login(login, password);

    dht.begin();
    Serial.println("DHT22 started.");
}

void loop() {
    iotHttpClient.updateState();

//    Serial.print("Device status: ");
//    Serial.println(iotHttpClient.getStatus().c_str());
  
//    Serial.print("Device measurement frequency: ");
//    Serial.println(String(iotHttpClient.getMeasurementFrequency()));
    
    if (iotHttpClient.getStatus() == "ACTIVE") {
        Serial.println("Device is active");
        delay(iotHttpClient.getMeasurementFrequency());
        Serial.println("Measuring");
        float temperature = dht.readTemperature();
        float humidity = dht.readHumidity();
        iotHttpClient.sendMessage("TEMPERATURE", temperature);
        iotHttpClient.sendMessage("HUMIDITY", humidity);
        Serial.println("Measurements have been send");
    } else {
        Serial.println("Device is inactive");
    }
    
//    iotHttpClient.disconnect();
}
