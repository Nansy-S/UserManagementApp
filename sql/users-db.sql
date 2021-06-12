CREATE DATABASE "users-db";

CREATE TABLE user_accounts
(
    id SERIAL PRIMARY KEY,
    username CHARACTER VARYING(16) UNIQUE,
    password CHARACTER VARYING NOT NULL,
    first_name CHARACTER VARYING(16) NOT NULL,
    last_name CHARACTER VARYING(16) NOT NULL,
    role CHARACTER VARYING(10) NOT NULL,
    status CHARACTER VARYING(10) NOT NULL,
	created_at TIMESTAMP NOT NULL,
	CHECK((username !='') 
		  AND (length(username) >= 3) 
		  AND (username~'^[a-zA-Z]+$')
		  AND (first_name !='')
		  AND (first_name~'^[a-zA-Z]+$')
		  AND (last_name !='')
		  AND (last_name~'^[a-zA-Z]+$'))
	)

INSERT INTO public.user_accounts (id, username, password, first_name, last_name, role, status, created_at) VALUES (1, 'user', '$2a$13$kNudZ1Gs6mdcETgdUuTcI.boHenjo0.mV5WHSqpKoMZClpOanjOSO', 'Chuck', 'Palahniuk', 'USER', 'ACTIVE', '2021-06-12 21:08:28.582');
INSERT INTO public.user_accounts (id, username, password, first_name, last_name, role, status, created_at) VALUES (2, 'admin', '$2a$13$s3Des45fGSetR4mdAcJ1DeaAhmouxZxpsR01CnEeslhGEYeJutIc2', 'Stephen', 'King', 'ADMIN', 'ACTIVE', '2021-06-12 22:35:43.893');
