SET FOREIGN_KEY_CHECKS = 0;

-- DELETE FROM user;
-- DELETE FROM account;
-- DELETE FROM device;
-- DELETE FROM measurement;
-- DELETE FROM audit_event;

SET FOREIGN_KEY_CHECKS = 1;

----------------------------------------users----------------------------------------

INSERT INTO user (user_id, first_name, last_name, role, email, password)
	VALUES (1, 'John', 'Smith', 'USER', 'john.smith@email.com', 'password');
INSERT INTO user (user_id, first_name, last_name, role, email, password)
	VALUES (2, 'Thomas', 'Robinson', 'USER', 'thomas.robinson@email.com', 'password');

----------------------------------------accounts----------------------------------------

INSERT INTO account (account_id, user_id, state, activation_token)
	VALUES (1, 1, 'NOT_CONFIRMED', '109224c7-f515-4b95-a9f9-43e773bf94fe');
INSERT INTO account (account_id, user_id, state, activation_token)
	VALUES (2, 2, 'CONFIRMED', '32818fc6-f89b-495b-aa0e-dc1be251eaa7');

----------------------------------------devices----------------------------------------

INSERT INTO device (device_id, account_id, name, model, state, measurement_frequency)
	VALUES (1, 1, 'Sensor_1', 'DHT11', 'NOT_ACTIVE', 2000);
INSERT INTO device (device_id, account_id, name, model, state, measurement_frequency)
	VALUES (2, 1, 'Sensor_2', 'DHT22', 'ACTIVE', 5000);
INSERT INTO device (device_id, account_id, name, model, state, measurement_frequency)
	VALUES (3, 1, 'Sensor_3', 'DHT22', 'DISABLED', 3000);

----------------------------------------measurements----------------------------------------

INSERT INTO measurement (measurement_id, device_id, type, value, measurement_time)
	VALUES (1, 1, 'TEMPERATURE', 17.2, CURRENT_TIMESTAMP());
INSERT INTO measurement (measurement_id, device_id, type, value, measurement_time)
	VALUES (2, 1, 'TEMPERATURE', 8.6, CURRENT_TIMESTAMP());

----------------------------------------audit_events----------------------------------------

INSERT INTO audit_event (audit_event_id, type, message, event_time)
	VALUES (1, 'WRONG_PASSWORD', 'User entered wrong password', CURRENT_TIMESTAMP());
INSERT INTO audit_event (audit_event_id, type, message, event_time)
	VALUES (2, 'NEW_ACCOUNT_REGISTERED', 'New user registered', CURRENT_TIMESTAMP());
