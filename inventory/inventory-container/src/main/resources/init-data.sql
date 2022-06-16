--Operative System Sentences

INSERT INTO INVENTORY.OPERATIVE_SYSTEM (id, name, description, created_date, modified_date, enabled) VALUES
							 ('4028ab42813e3a0901813e3faf720001','WINDOWS', 'WINDOWS OPERATIVE SYSTEM', 1654606612, 1654606612, true);

INSERT INTO INVENTORY.OPERATIVE_SYSTEM (id, name, description, created_date, modified_date, enabled) VALUES
							 ('4028ab42813e3a0901813e3f6a9e0000', 'LINUX', 'LINUX OPERATIVE SYSTEM', 1654606612, 1654606612, true);

INSERT INTO INVENTORY.OPERATIVE_SYSTEM (id, name, description, created_date, modified_date, enabled) VALUES
							 ('4028ab42813e3a0901813e3fe9f40002', 'MACOS', 'MAC OPERATIVE SYSTEM', 1654606612, 1654606612, true);


--Device Classification Sentences

INSERT INTO INVENTORY.DEVICE_CLASSIFICATION (id, name, description, created_date, modified_date, enabled) VALUES
							 ('2c938088809328990180932c85560007','LAPTOP', 'COMPUTER THAT IS PORTABLE AND SUITABLE FOR USE WHILE TRAVELING', 1654606612, 1654606612, true);

INSERT INTO INVENTORY.DEVICE_CLASSIFICATION (id, name, description, created_date, modified_date, enabled) VALUES
							 ('2c938088809328990180932c85a70009', 'DESKTOP', 'A COMPUTER SUITABLE FOR USE AT AN ORDINARY DESK', 1654606612, 1654606612, true);

INSERT INTO INVENTORY.DEVICE_CLASSIFICATION (id, name, description, created_date, modified_date, enabled) VALUES
							 ('2c938088809328990180932ccec5001b', 'WORKSTATION', 'A DESKTOP COMPUTER TERMINAL, TYPICALLY NETWORKED AND MORE POWERFULL THAN A PERSONAL COMPUTER', 1654606612, 1654606612, true);



--TypeDevice Sentence

	-- DeviceClassification: 2c938088809328990180932c85560007 (Laptop)
	-- Operative System: 4028ab42813e3a0901813e3faf720001 (Windows)

	INSERT INTO TYPE_DEVICE (id, device_classification_id , operative_system_id, description, created_date, modified_date, enabled) VALUES
							('2c938088809328990180932c83490003', '2c938088809328990180932c85560007', '4028ab42813e3a0901813e3faf720001',  'Laptop Windows', 1654606612, 1654606612, true);

	-- DeviceClassification: 2c938088809328990180932c85560007 (Laptop)
	-- Operative System: 4028ab42813e3a0901813e3f6a9e0000 (Linux)

	INSERT INTO TYPE_DEVICE (id, device_classification_id , operative_system_id, description, created_date, modified_date, enabled) VALUES
							('2c938088809328990180932ccc6f0017', '2c938088809328990180932c85560007', '4028ab42813e3a0901813e3f6a9e0000',  'Laptop Linux',  1654606612, 1654606612, true);

	-- DeviceClassification: 2c938088809328990180932c85560007 (Laptop)
	-- Operative System: 4028ab42813e3a0901813e3fe9f40002 (Mac)

	INSERT INTO TYPE_DEVICE (id, device_classification_id , operative_system_id, description, created_date, modified_date, enabled) VALUES
							('2c938088809328990180932d8929003b', '2c938088809328990180932c85560007', '4028ab42813e3a0901813e3fe9f40002',  'Laptop Mac', 1654606612, 1654606612, true);

	-- DeviceClassification: 2c938088809328990180932c85a70009 (Desktop)
	-- Operative System: 4028ab42813e3a0901813e3faf720001 (Windows)

	INSERT INTO TYPE_DEVICE (id, device_classification_id , operative_system_id, description, created_date, modified_date, enabled) VALUES
							('2c938088809328990180932f5946012b', '2c938088809328990180932c85a70009', '4028ab42813e3a0901813e3faf720001',  'Desktop Windows', 1654606612, 1654606612, true);

	-- DeviceClassification: 2c938088809328990180932c85a70009 (Desktop)
	-- Operative System: 4028ab42813e3a0901813e3f6a9e0000 (Linux)

	INSERT INTO TYPE_DEVICE (id, device_classification_id , operative_system_id, description, created_date, modified_date, enabled) VALUES
							('2c938088809328990180932fe4ed0166', '2c938088809328990180932c85a70009', '4028ab42813e3a0901813e3f6a9e0000',  'Desktop Linux',  1654606612, 1654606612, true);

	-- DeviceClassification: 2c938088809328990180932c85a70009 (Desktop)
	-- Operative System: 4028ab42813e3a0901813e3fe9f40002 (Mac)

	INSERT INTO TYPE_DEVICE (id, device_classification_id , operative_system_id, description, created_date, modified_date, enabled) VALUES
							('2c938088809328990180933092a20195', '2c938088809328990180932c85a70009', '4028ab42813e3a0901813e3fe9f40002',  'Desktop Mac', 1654606612, 1654606612, true);

	-- DeviceClassification: 2c938088809328990180932ccec5001b (Workstation)
	-- Operative System: 4028ab42813e3a0901813e3faf720001 (Windows)

	INSERT INTO TYPE_DEVICE (id, device_classification_id , operative_system_id, description, created_date, modified_date, enabled) VALUES
							('2c9380888093289901809331ee0e022a', '2c938088809328990180932ccec5001b', '4028ab42813e3a0901813e3faf720001',  'WorkstationWindows', 1654606612, 1654606612, true);


	-- DeviceClassification: 2c938088809328990180932ccec5001b (Workstation)
	-- Operative System: 4028ab42813e3a0901813e3f6a9e0000 (Linux)

	INSERT INTO TYPE_DEVICE (id, device_classification_id , operative_system_id, description, created_date, modified_date, enabled) VALUES
							('2c9380888093289901809332403d023e', '2c938088809328990180932ccec5001b', '4028ab42813e3a0901813e3f6a9e0000',  'Workstation Linux',  1654606612, 1654606612, true);


	-- DeviceClassification: 2c938088809328990180932ccec5001b (Workstation)
	-- Operative System: 4028ab42813e3a0901813e3fe9f40002 (Mac)

	INSERT INTO TYPE_DEVICE (id, device_classification_id , operative_system_id, description, created_date, modified_date, enabled) VALUES
							('2c93808880932899018093327a0d0254', '2c938088809328990180932ccec5001b', '4028ab42813e3a0901813e3fe9f40002',  'Workstation Mac', 1654606612, 1654606612, true);


