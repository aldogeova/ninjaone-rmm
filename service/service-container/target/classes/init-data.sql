--Service  Sentences

INSERT INTO SERVICE.SERVICE (id, name, description, created_date, modified_date, enabled) VALUES
							 ('2c9a80a06d68cf0c016d68cf2b870000','SUBSCRIPTION', 'MONTHLY SUBSCRIPTION PER DEVICE', 1654606612, 1654606612, true);

INSERT INTO SERVICE.SERVICE (id, name, description, created_date, modified_date, enabled) VALUES
							 ('2c9a80a06d68cf0c016d68cf2b870001', 'ANTIVIRUS', 'ANTIVIRUS UPDATE AND MAINTENANCE SERVICE', 1654606612, 1654606612, true);

INSERT INTO SERVICE.SERVICE (id, name, description, created_date, modified_date, enabled) VALUES
							 ('2c9a80a06d68cf0c016d68cf2b870002', 'BACKUP', 'BACKUP OF IMPORTANT INFORMATION FOR THE BUSINES', 1654606612, 1654606612, true);

INSERT INTO SERVICE.SERVICE (id, name, description, created_date, modified_date, enabled) VALUES
							 ('2c9a80a06d68cf0c016d68cf2b870004', 'PSA', 'PROFESSIONAL SERVICES AUTOMATION', 1654606612, 1654606612, true);

INSERT INTO SERVICE.SERVICE (id, name, description, created_date, modified_date, enabled) VALUES
							 ('2c9a80a06d68cf0c016d68cf2b870005', 'SCREEN SHARE', 'REMOTE ASSISTANCE', 1654606612, 1654606612, true);



--Service Type Sentence

	--Service: 2c9a80a06d68cf0c016d68cf2b870000 (Subscription)
	--Type: 2c938088809328990180932c83490003 (Laptop Windows)
	INSERT INTO SERVICE_TYPE (id, service_id , type_device_id, price, created_date, modified_date, enabled) VALUES
							('2c938088809328990180933abcc20602', '2c9a80a06d68cf0c016d68cf2b870000', '2c938088809328990180932c83490003', 4.00,  1654606612, 1654606612, true);

	--Service: 2c9a80a06d68cf0c016d68cf2b870000 (Subscription)
	--Type: 2c938088809328990180932ccc6f0017 (Laptop Linux)
	INSERT INTO SERVICE_TYPE (id, service_id , type_device_id, price, created_date, modified_date, enabled) VALUES
							('2c938088809328990180933b4b850640', '2c9a80a06d68cf0c016d68cf2b870000', '2c938088809328990180932ccc6f0017', 4.00,  1654606612, 1654606612, true);

	--Service: 2c9a80a06d68cf0c016d68cf2b870000 (Subscription)
	--Type: 2c938088809328990180932d8929003b (Laptop Mac)
	INSERT INTO SERVICE_TYPE (id, service_id , type_device_id, price, created_date, modified_date, enabled) VALUES
							('2c938088809328990180933bc2120675', '2c9a80a06d68cf0c016d68cf2b870000', '2c938088809328990180932d8929003b', 4.00,  1654606612, 1654606612, true);

	--Service: 2c9a80a06d68cf0c016d68cf2b870001 (Antivirus)
	--Type: 2c938088809328990180932c83490003 (Laptop Windows)
	INSERT INTO SERVICE_TYPE (id, service_id , type_device_id, price, created_date, modified_date, enabled) VALUES
							('2c938088809328990180933c5a3f06a2', '2c9a80a06d68cf0c016d68cf2b870001', '2c938088809328990180932c83490003', 5.00,  1654606612, 1654606612, true);

	--Service: 2c9a80a06d68cf0c016d68cf2b870001 (Antivirus)
	--Type: 2c938088809328990180932d8929003b (Laptop Mac)
	INSERT INTO SERVICE_TYPE (id, service_id , type_device_id, price, created_date, modified_date, enabled) VALUES
							('2c938088809328990180933ceda406e0', '2c9a80a06d68cf0c016d68cf2b870001', '2c938088809328990180932d8929003b', 7.00,  1654606612, 1654606612, true);

	--Service: 2c9a80a06d68cf0c016d68cf2b870002 (Backup)
	--Type: 2c938088809328990180932c83490003 (Laptop Windows)
	INSERT INTO SERVICE_TYPE (id, service_id , type_device_id, price, created_date, modified_date, enabled) VALUES
							('2c938088809328990180933e4a1f079d', '2c9a80a06d68cf0c016d68cf2b870002', '2c938088809328990180932c83490003', 3.00,  1654606612, 1654606612, true);

	--Service: 2c9a80a06d68cf0c016d68cf2b870002 (Backup)
	--Type: 2c938088809328990180932ccc6f0017 (Laptop Linux)
	INSERT INTO SERVICE_TYPE (id, service_id , type_device_id, price, created_date, modified_date, enabled) VALUES
							('2c938088809328990180933e679307c6', '2c9a80a06d68cf0c016d68cf2b870002', '2c938088809328990180932ccc6f0017', 3.00,  1654606612, 1654606612, true);

	--Service: 2c9a80a06d68cf0c016d68cf2b870002 (Backup)
	--Type: 2c938088809328990180932d8929003b (Laptop Mac)
	INSERT INTO SERVICE_TYPE (id, service_id , type_device_id, price, created_date, modified_date, enabled) VALUES
							('2c938088809328990180933e7c2e07da', '2c9a80a06d68cf0c016d68cf2b870002', '2c938088809328990180932d8929003b', 3.00,  1654606612, 1654606612, true);


	--Service: 2c9a80a06d68cf0c016d68cf2b870004 (PSA)
	--Type: 2c938088809328990180932c83490003 (Laptop Windows)
	INSERT INTO SERVICE_TYPE (id, service_id , type_device_id, price, created_date, modified_date, enabled) VALUES
							('2c93808880932899018093473a020909', '2c9a80a06d68cf0c016d68cf2b870004', '2c938088809328990180932c83490003', 2.00,  1654606612, 1654606612, true);

	--Service: 2c9a80a06d68cf0c016d68cf2b870004 (PSA)
	--Type: 2c938088809328990180932ccc6f0017 (Laptop Linux)
	INSERT INTO SERVICE_TYPE (id, service_id , type_device_id, price, created_date, modified_date, enabled) VALUES
							('2c93808880932899018093473df0091c', '2c9a80a06d68cf0c016d68cf2b870004', '2c938088809328990180932ccc6f0017', 2.00,  1654606612, 1654606612, true);

	--Service: 2c9a80a06d68cf0c016d68cf2b870004 (PSA)
	--Type: 2c938088809328990180932d8929003b (Laptop Mac)
	INSERT INTO SERVICE_TYPE (id, service_id , type_device_id, price, created_date, modified_date, enabled) VALUES
							('2c9380888093289901809347785a092f', '2c9a80a06d68cf0c016d68cf2b870004', '2c938088809328990180932d8929003b', 2.00,  1654606612, 1654606612, true);

	--Service: 2c9a80a06d68cf0c016d68cf2b870005 (Screen Share)
	--Type: 2c938088809328990180932c83490003 (Laptop Windows)
	INSERT INTO SERVICE_TYPE (id, service_id , type_device_id, price, created_date, modified_date, enabled) VALUES
							('2c9380888093289901809351a9f00ab6', '2c9a80a06d68cf0c016d68cf2b870005', '2c938088809328990180932c83490003', 1.00,  1654606612, 1654606612, true);

	--Service: 2c9a80a06d68cf0c016d68cf2b870005 (Screen Share)
	--Type: 2c938088809328990180932ccc6f0017 (Laptop Linux)
	INSERT INTO SERVICE_TYPE (id, service_id , type_device_id, price, created_date, modified_date, enabled) VALUES
							('2c9380888093289901809352348f0ae4', '2c9a80a06d68cf0c016d68cf2b870005', '2c938088809328990180932ccc6f0017', 1.00,  1654606612, 1654606612, true);

	--Service: 2c9a80a06d68cf0c016d68cf2b870005 (Screen Share)
	--Type: 2c938088809328990180932d8929003b (Laptop Mac)
	INSERT INTO SERVICE_TYPE (id, service_id , type_device_id, price, created_date, modified_date, enabled) VALUES
							('4028e48481298a0f01812992a8950003', '2c9a80a06d68cf0c016d68cf2b870005', '2c938088809328990180932d8929003b', 1.00,  1654606612, 1654606612, true);


