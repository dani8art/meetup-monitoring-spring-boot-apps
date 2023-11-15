--
-- Name: kline; Type: TABLE; Schema: public;
--
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE purchase (
  id uuid NOT NULL DEFAULT uuid_generate_v4() PRIMARY KEY
);

CREATE TABLE ticket (
  id uuid NOT NULL DEFAULT uuid_generate_v4() PRIMARY KEY,
  price character varying(30) NOT NULL,
  location character varying(30) NOT NULL
);

CREATE TABLE purchase_ticket (
  id uuid NOT NULL DEFAULT uuid_generate_v4() PRIMARY KEY,
  purchase_id uuid NOT NULL,
  ticket_id uuid NOT NULL,
  CONSTRAINT fk_purchase FOREIGN KEY(purchase_id) REFERENCES purchase(id),
  CONSTRAINT fk_ticket FOREIGN KEY(ticket_id) REFERENCES ticket(id)
);