CREATE TABLE T_AUTH_NETWORKS(
	name VARCHAR(50) NOT NULL,
	id VARCHAR(50) NOT NULL
);

CREATE TABLE T_NETWORK (
	id_network INT NOT NULL AUTO_INCREMENT,
	type VARCHAR(20),
    generique_type VARCHAR(20),
    last_update DATE,
    PRIMARY KEY (id_network)
);

CREATE TABLE T_LINE (
	id_line INT NOT NULL AUTO_INCREMENT,
	code VARCHAR(10),
	name VARCHAR(200),
	id_network INT,
    opening_time VARCHAR(50),
	closing_time VARCHAR(50),
	color VARCHAR(100),
	transport_type VARCHAR(50),
    last_update DATE,
    PRIMARY KEY (id_line)
);

CREATE TABLE T_LINE_DIRECTION (
	id_direction INT NOT NULL AUTO_INCREMENT,
	code VARCHAR(10),
	libelle VARCHAR(200),
    PRIMARY KEY (id_direction)
);

CREATE TABLE T_ROUTE (
	id_route INT NOT NULL AUTO_INCREMENT,
	id_line INT,
	name VARCHAR(50),
    destination VARCHAR(300),
    opening_time VARCHAR(10),
    closing_time VARCHAR(10),
    last_update DATE,
    PRIMARY KEY (id_route)
);

CREATE TABLE T_STOP_AREA (
	id_stop_area INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(100),
    id_navitia VARCHAR(100),
    longitude VARCHAR(20),
    latitude VARCHAR(20),
    zone INT,
    ignore_itineraire INT,
    last_update DATE,
    calculated INT NOT NULL DEFAULT 0,
    PRIMARY KEY (id_stop_area)
);

CREATE TABLE T_STOP_AREA_LINE (
    id_stop_area INT,
    id_line INT,
    last_update DATE
);

CREATE TABLE T_STOP_AREA_ROUTE (
    id_stop_area INT,
    id_route INT,
    last_update DATE
);

CREATE TABLE T_STOP_POINT (
	id_stop_point INT NOT NULL AUTO_INCREMENT,
	id_stop_point_navitia VARCHAR(100),
	name VARCHAR(300),
    id_stop_area INT,
    id_route INT,
    longitude VARCHAR(30),
    latitude VARCHAR(30),
    last_update DATE,
    PRIMARY KEY (id_stop_point)
);

CREATE TABLE T_ITINERAIRES (
	id_stop_area_from INT NOT NULL,
	id_stop_area_to INT NOT NULL,
	itineraire VARCHAR(500)
);


insert into T_AUTH_NETWORKS values ('METRO', 'network:OIF:439');
-- insert into T_AUTH_NETWORKS values ('Noctilien', 'network:OIF:56');
-- insert into T_AUTH_NETWORKS values ('RATP', 'network:RTP');
insert into T_AUTH_NETWORKS values ('RER', 'network:RER');
-- insert into T_AUTH_NETWORKS values ('SNCF', 'network:sncf');
-- insert into T_AUTH_NETWORKS values ('TER', 'network:TER');
insert into T_AUTH_NETWORKS values ('TRAMWAY', 'network:OIF:440');
-- insert into T_AUTH_NETWORKS values ('Transilien', 'network:TN');
