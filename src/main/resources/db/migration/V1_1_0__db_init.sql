CREATE TABLE stations (
    id_station NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR2(50) NOT NULL,
    count_columns NUMBER NOT NULL,
    max_queue NUMBER NOT NULL,
    max_volume_gasoline NUMBER NOT NULL,
    min_volume_gasoline NUMBER NOT NULL,
    min_time_service NUMBER NOT NULL,
    max_time_service NUMBER NOT NULL,
    time_span_type NUMBER NOT NULL,
    default_density NUMBER NOT NULL
)
;

CREATE TABLE brands (
    id_brand NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    brand VARCHAR2(50) NOT NULL,
    cost NUMBER NOT NULL,

    station_id NUMBER NOT NULL
)
;

ALTER TABLE brands
    ADD CONSTRAINT fc_brands_stations
        FOREIGN KEY(station_id) REFERENCES stations(id_station)
;

CREATE TABLE periods (
    id_period NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    density NUMBER NOT NULL,
    start_time NUMBER NOT NULL,
    end_time NUMBER NOT NULL,

    station_id NUMBER NOT NULL
)
;

ALTER TABLE periods
    ADD CONSTRAINT fc_periods_stations
        FOREIGN KEY(station_id) REFERENCES stations(id_station)
;

CREATE TABLE orders (
    id_order NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    queue_id  NUMBER NOT NULL,
    volume  NUMBER NOT NULL,
    order_time  NUMBER NOT NULL,
    duration  NUMBER NOT NULL,
    status  NUMBER NOT NULL,

    brand_id  NUMBER NOT NULL,
    station_id  NUMBER NOT NULL
)
;

ALTER TABLE orders
    ADD CONSTRAINT fc_orders_brands
        FOREIGN KEY(brand_id) REFERENCES brands(id_brand)
;

ALTER TABLE orders
    ADD CONSTRAINT fc_orders_stations
        FOREIGN KEY(station_id) REFERENCES stations(id_station)
;

CREATE TABLE markups (
    id_markup NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    percent NUMBER NOT NULL,
    start_time NUMBER NOT NULL,
    end_time NUMBER NOT NULL,
    brand_id NUMBER NOT NULL,
    station_id NUMBER NOT NULL
)
;

ALTER TABLE markups
    ADD CONSTRAINT fc_markups_brands
        FOREIGN KEY(brand_id) REFERENCES brands(id_brand)
;

ALTER TABLE markups
    ADD CONSTRAINT fc_markups_stations
        FOREIGN KEY(station_id) REFERENCES stations(id_station)
;
