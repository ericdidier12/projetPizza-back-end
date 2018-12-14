

------------------------ address; ------------------------------

CREATE TABLE address (
    id SERIAL,
    street character varying(200) NOT NULL,
    number integer NOT NULL,
    zip_code integer NOT NULL,
    city character varying(200) NOT NULL,
    box integer
);
ALTER TABLE public.address OWNER TO postgres;


------------------------------   authorities;-----------------------------------------

CREATE TABLE public.authorities (
    id SERIAL,
    name character varying(100) NOT NULL
);
ALTER TABLE public.authorities OWNER TO postgres;


---------------------------------  authority--------------------------------------

CREATE TABLE public.authority (
    id SERIAL,
    authority character varying(255) NOT NULL
);
ALTER TABLE public.authority OWNER TO postgres;




---------------------------------  bank_account----------------------------------

CREATE TABLE public.bank_account (id SERIAL, iban numeric(20,0) NOT NULL);
ALTER TABLE public.bank_account OWNER TO postgres;


----------------------------------  category; ---------------------------------------

CREATE TABLE public.category (
    id SERIAL,
    name character varying(100) DEFAULT NULL::character varying
);
ALTER TABLE public.category OWNER TO postgres;

---------------------------------  ingredient;---------------------------

CREATE TABLE public.ingredient (
    id SERIAL,
    name character varying(200) DEFAULT NULL::character varying,
    recipe_quantity double precision default 15,
    stock_quantity double precision default 100,
    unit_price double precision default 2
);
ALTER TABLE public.ingredient OWNER TO postgres;

------------------------  order_command  ----------------------------

CREATE TABLE public.order_command (
    id SERIAL,
    date_order timestamp without time zone NOT NULL default CURRENT_TIMESTAMP,
    delivery_price double precision default 5,
    full_price double precision,
    total_price double precision,
    is_paid BOOLEAN DEFAULT false,
    promo_code_id integer,
    statut_id integer not null DEFAULT 1,
    client_id integer NOT NULL
);
ALTER TABLE public.order_command OWNER TO postgres;


--------------------------- order_line   ---------------------------------

CREATE TABLE public.order_line (
    id SERIAL,
	order_id integer,
    pizza_id integer,
    number_of_pizza integer DEFAULT 1
);
ALTER TABLE public.order_line OWNER TO postgres;

---------------------------- pizza  --------------------------

CREATE TABLE public.pizza (
    id SERIAL,
    name character varying(100) DEFAULT 'Custome',
    price float,
    month_promo boolean DEFAULT false,
    cat_id integer default 1,
    fixed boolean NOT NULL default false
);
ALTER TABLE public.pizza OWNER TO postgres;


---------------------------  promo_code  -------------------------

CREATE TABLE public.promo_code (id SERIAL,
name character varying(45) DEFAULT NULL::character varying, reduction double precision);

ALTER TABLE public.promo_code OWNER TO postgres;


--------------------------  recipe  ------------------------------

CREATE TABLE public.recipe (pizza_id integer NOT NULL, ingredient_id integer NOT NULL);

ALTER TABLE public.recipe OWNER TO postgres;


---------------------  status  ----------------------------

CREATE TABLE public.status (
    id SERIAL,
    name character varying(45) DEFAULT NULL::character varying
);
ALTER TABLE public.status OWNER TO postgres;


-------------------  user_client   ----------------------

CREATE TABLE public.user_client (
    id SERIAL,
    email character varying(200) NOT NULL,
    name character varying(200) NOT NULL,
    birth_date timestamp without time zone,
    password character varying(200) NOT NULL,
    authority_id integer DEFAULT 2 NOT NULL,
    address_id integer,
    bank_account_id integer,
    username character varying(255),
	acount_not_expired boolean default true,
    acount_not_locked boolean default true,
    box character varying(255) COLLATE pg_catalog."default",
    country character varying(255) COLLATE pg_catalog."default",
    "number" character varying(255) COLLATE pg_catalog."default",
    street character varying(255) COLLATE pg_catalog."default",
    zip_code character varying(255) COLLATE pg_catalog."default",
    credentials_non_expired boolean default true,
    enabled boolean default true,
    adresse character varying(255) COLLATE pg_catalog."default"
);
ALTER TABLE public.user_client OWNER TO postgres;

------------   user_client_authorities   -------------------

CREATE TABLE public.user_client_authorities (
    user_entity_id bigint NOT NULL,
    authorities_id bigint NOT NULL default 2
);
ALTER TABLE public.user_client_authorities OWNER TO postgres;


----------------------     DATA     ------------------------------

INSERT INTO public.address(street, "number", zip_code, city, box) VALUES ('Avenue Arnaud Fraiteur',	15,	1050,	'Ixelles',	null);



INSERT INTO public.authorities(name) VALUES ('role_admin');
INSERT INTO public.authorities(name) VALUES ('role_user');


INSERT INTO public.authority(authority) VALUES ('ROLE_ADMIN');
INSERT INTO public.authority(authority)	VALUES ('ROLE_USER');


INSERT INTO public.category(name) VALUES ('normal');
INSERT INTO public.category(name) VALUES ('american');
INSERT INTO public.category(name) VALUES ('of the sea');


INSERT INTO public.ingredient(name, recipe_quantity, stock_quantity, unit_price) VALUES ('tomato sauce',	50,	3000, 1.5);
INSERT INTO public.ingredient(name, recipe_quantity, stock_quantity, unit_price) VALUES ('mozzarela', 20, 2000, 1.5);
INSERT INTO public.ingredient(name, recipe_quantity, stock_quantity, unit_price) VALUES ('ham',	15,	2000,	1.5);
INSERT INTO public.ingredient(name, recipe_quantity, stock_quantity, unit_price) VALUES ('pineapple',	10,	1500,	1.5);
INSERT INTO public.ingredient(name, recipe_quantity, stock_quantity, unit_price) VALUES ('mushroom',	10	,1500	,1);
INSERT INTO public.ingredient(name, recipe_quantity, stock_quantity, unit_price) VALUES ('parmesan',	10,	1700,	1.5);
INSERT INTO public.ingredient(name, recipe_quantity, stock_quantity, unit_price) VALUES ('egg'	,20,	2000,	2);
INSERT INTO public.ingredient(name, recipe_quantity, stock_quantity, unit_price) VALUES ('gorgonzola',	15	,1000,	2);
INSERT INTO public.ingredient(name, recipe_quantity, stock_quantity, unit_price) VALUES ('cheddar',	15,	1000,	2);
INSERT INTO public.ingredient(name, recipe_quantity, stock_quantity, unit_price) VALUES ('Cherry tomato',	15	,1200,	2);
INSERT INTO public.ingredient(name, recipe_quantity, stock_quantity, unit_price) VALUES ('salmone',	15,	800,	2.5);
INSERT INTO public.ingredient(name, recipe_quantity, stock_quantity, unit_price) VALUES ('tuna'	,15	,500,	2);
INSERT INTO public.ingredient(name, recipe_quantity, stock_quantity, unit_price) VALUES ('sausage',	25,	250,	2);
INSERT INTO public.ingredient(name, recipe_quantity, stock_quantity, unit_price) VALUES ('hotdog',	20,	220,	2);
INSERT INTO public.ingredient(name, recipe_quantity, stock_quantity, unit_price) VALUES ('hamburger',	20,	180,	2.5);
INSERT INTO public.ingredient(name, recipe_quantity, stock_quantity, unit_price) VALUES ('chips',	15,	500,	1);
INSERT INTO public.ingredient(name, recipe_quantity, stock_quantity, unit_price) VALUES ('scampi',	15,	200,	2);
INSERT INTO public.ingredient(name, recipe_quantity, stock_quantity, unit_price) VALUES ('chicken',	15,	400,	2);
INSERT INTO public.ingredient(name, recipe_quantity, stock_quantity, unit_price) VALUES ('barbecue sauce',	10	,250	,2);
INSERT INTO public.ingredient(name, recipe_quantity, stock_quantity, unit_price) VALUES ('anchovy',	15,	600,	2);
INSERT INTO public.ingredient(name, recipe_quantity, stock_quantity, unit_price) VALUES ('caper',	7,	120,	1);
INSERT INTO public.ingredient(name, recipe_quantity, stock_quantity, unit_price) VALUES ('squid',	20,	180,	2.5);
INSERT INTO public.ingredient(name, recipe_quantity, stock_quantity, unit_price) VALUES ('chorizo',	15,	300	,2);
INSERT INTO public.ingredient(name, recipe_quantity, stock_quantity, unit_price) VALUES ('onion',	10,	200,	1);
INSERT INTO public.ingredient(name, recipe_quantity, stock_quantity, unit_price) VALUES ('bacon',	15,	300,	2);
INSERT INTO public.ingredient(name, recipe_quantity, stock_quantity, unit_price) VALUES ('pickel',	8,	110,	1);
		

INSERT INTO public.pizza(name, price, month_promo, cat_id, fixed) VALUES ('Margherita',	7,	false	,1,	true);
INSERT INTO public.pizza(name, price, month_promo, cat_id, fixed) VALUES ('Ham'	,7,	false,	1,	true);
INSERT INTO public.pizza(name, price, month_promo, cat_id, fixed) VALUES ('Napoli',	8,	false,	1,	true);
INSERT INTO public.pizza(name, price, month_promo, cat_id, fixed) VALUES ('Hawaï',	8	,false,	1,	true);
INSERT INTO public.pizza(name, price, month_promo, cat_id, fixed) VALUES ('cheese',	10,	false,	1,	true);	
INSERT INTO public.pizza(name, price, month_promo, cat_id, fixed) VALUES ('Hotdog'	,11,	false,	2,	true);
INSERT INTO public.pizza(name, price, month_promo, cat_id, fixed) VALUES ('Hamburger',	13,	false,	2,	true);
INSERT INTO public.pizza(name, price, month_promo, cat_id, fixed) VALUES ('Chips',	9,	false,	2,	true);
INSERT INTO public.pizza(name, price, month_promo, cat_id, fixed) VALUES ('Sausage',	12,	false,	2,	true);
INSERT INTO public.pizza(name, price, month_promo, cat_id, fixed) VALUES ('Barbecue',	12	,false,	2,	true);
INSERT INTO public.pizza(name, price, month_promo, cat_id, fixed) VALUES ('Scampi',	12	,false,	3,	true);
INSERT INTO public.pizza(name, price, month_promo, cat_id, fixed) VALUES ('Squid',	12	,true,	3,	true);
INSERT INTO public.pizza(name, price, month_promo, cat_id, fixed) VALUES ('Salmone',	13,	false,	3,	true);
INSERT INTO public.pizza(name, price, month_promo, cat_id, fixed) VALUES ('Tuna',	11,	false	,3	,true);


INSERT INTO public.promo_code(name, reduction) VALUES ('sales',	15);
INSERT INTO public.promo_code(name, reduction) VALUES ('summer',	5);
INSERT INTO public.promo_code(name, reduction) VALUES ('black week',	25);
INSERT INTO public.promo_code(name, reduction) VALUES ('random',	0);


INSERT INTO public.recipe(pizza_id, ingredient_id) VALUES (1,	1);
INSERT INTO public.recipe(pizza_id, ingredient_id) VALUES (1,	2);
INSERT INTO public.recipe(pizza_id, ingredient_id) VALUES (2,	1);
INSERT INTO public.recipe(pizza_id, ingredient_id) VALUES (2,	3);
INSERT INTO public.recipe(pizza_id, ingredient_id) VALUES (3,	1);
INSERT INTO public.recipe(pizza_id, ingredient_id) VALUES (3,	2);
INSERT INTO public.recipe(pizza_id, ingredient_id) VALUES (3,	20);
INSERT INTO public.recipe(pizza_id, ingredient_id) VALUES (3,	21);
INSERT INTO public.recipe(pizza_id, ingredient_id) VALUES (4,	2);
INSERT INTO public.recipe(pizza_id, ingredient_id) VALUES (4,	3);
INSERT INTO public.recipe(pizza_id, ingredient_id) VALUES (4,	4);
INSERT INTO public.recipe(pizza_id, ingredient_id) VALUES (4,	1);
INSERT INTO public.recipe(pizza_id, ingredient_id) VALUES (5,	1);
INSERT INTO public.recipe(pizza_id, ingredient_id) VALUES (5,	2);
INSERT INTO public.recipe(pizza_id, ingredient_id) VALUES (5,	9);
INSERT INTO public.recipe(pizza_id, ingredient_id) VALUES (5,	8);
INSERT INTO public.recipe(pizza_id, ingredient_id) VALUES (5,	6);
INSERT INTO public.recipe(pizza_id, ingredient_id) VALUES (6,	1);
INSERT INTO public.recipe(pizza_id, ingredient_id) VALUES (6,	2);
INSERT INTO public.recipe(pizza_id, ingredient_id) VALUES (6,	14);
INSERT INTO public.recipe(pizza_id, ingredient_id) VALUES (6,	24);
INSERT INTO public.recipe(pizza_id, ingredient_id) VALUES (6,	23);
INSERT INTO public.recipe(pizza_id, ingredient_id) VALUES (7,	1);
INSERT INTO public.recipe(pizza_id, ingredient_id) VALUES (7,	2);
INSERT INTO public.recipe(pizza_id, ingredient_id) VALUES (7,	26);
INSERT INTO public.recipe(pizza_id, ingredient_id) VALUES (7,	15);
INSERT INTO public.recipe(pizza_id, ingredient_id) VALUES (7,	24);
INSERT INTO public.recipe(pizza_id, ingredient_id) VALUES (8,	1);
INSERT INTO public.recipe(pizza_id, ingredient_id) VALUES (8,	2);
INSERT INTO public.recipe(pizza_id, ingredient_id) VALUES (8,	3);
INSERT INTO public.recipe(pizza_id, ingredient_id) VALUES (8,	5);
INSERT INTO public.recipe(pizza_id, ingredient_id) VALUES (8,	16);
INSERT INTO public.recipe(pizza_id, ingredient_id) VALUES (9,	1);
INSERT INTO public.recipe(pizza_id, ingredient_id) VALUES (9,	2);
INSERT INTO public.recipe(pizza_id, ingredient_id) VALUES (9,	13);
INSERT INTO public.recipe(pizza_id, ingredient_id) VALUES (9,	9);
INSERT INTO public.recipe(pizza_id, ingredient_id) VALUES (9	,5);
INSERT INTO public.recipe(pizza_id, ingredient_id) VALUES (10,	1);
INSERT INTO public.recipe(pizza_id, ingredient_id) VALUES (10,	2);
INSERT INTO public.recipe(pizza_id, ingredient_id) VALUES (10,	18);
INSERT INTO public.recipe(pizza_id, ingredient_id) VALUES (10,	19);
INSERT INTO public.recipe(pizza_id, ingredient_id) VALUES (10,	24);
INSERT INTO public.recipe(pizza_id, ingredient_id) VALUES (11,	1);
INSERT INTO public.recipe(pizza_id, ingredient_id) VALUES (11	,2);
INSERT INTO public.recipe(pizza_id, ingredient_id) VALUES (11,	17);
INSERT INTO public.recipe(pizza_id, ingredient_id) VALUES (11,	3);
INSERT INTO public.recipe(pizza_id, ingredient_id) VALUES (12,	1);
INSERT INTO public.recipe(pizza_id, ingredient_id) VALUES (12,	2);
INSERT INTO public.recipe(pizza_id, ingredient_id) VALUES (12,	3);
INSERT INTO public.recipe(pizza_id, ingredient_id) VALUES (12,	22);
INSERT INTO public.recipe(pizza_id, ingredient_id) VALUES (13,	1);
INSERT INTO public.recipe(pizza_id, ingredient_id) VALUES (13,	2);
INSERT INTO public.recipe(pizza_id, ingredient_id) VALUES (13,	11);
INSERT INTO public.recipe(pizza_id, ingredient_id) VALUES (13,	24);
INSERT INTO public.recipe(pizza_id, ingredient_id) VALUES (13,	3);
INSERT INTO public.recipe(pizza_id, ingredient_id) VALUES (14,	1);
INSERT INTO public.recipe(pizza_id, ingredient_id) VALUES (14,	2);
INSERT INTO public.recipe(pizza_id, ingredient_id) VALUES (14,	12);
INSERT INTO public.recipe(pizza_id, ingredient_id) VALUES (14,	10);
INSERT INTO public.recipe(pizza_id, ingredient_id) VALUES (14,	3);


INSERT INTO public.status(name) VALUES ('in progress');
INSERT INTO public.status(name) VALUES ('ready');
INSERT INTO public.status(name) VALUES ('transit');
INSERT INTO public.status(name) VALUES ('delivered');


SELECT pg_catalog.setval('public.authority_id_seq', 1, true);


ALTER TABLE ONLY public.address
    ADD CONSTRAINT address_pkey PRIMARY KEY (id);

	-- Name: order_line orderLine_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
	
ALTER TABLE ONLY public.order_line
    ADD CONSTRAINT orderLine_pkey PRIMARY KEY (id);

-- Name: authority authority_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.authority
    ADD CONSTRAINT authority_pkey PRIMARY KEY (id);

-- Name: bank_account bank_account_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bank_account
    ADD CONSTRAINT bank_account_pkey PRIMARY KEY (id);

-- Name: category category_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id);

-- Name: ingredient ingredient_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ingredient
    ADD CONSTRAINT ingredient_pkey PRIMARY KEY (id);

-- Name: order_command order_command_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.order_command
    ADD CONSTRAINT order_command_pkey PRIMARY KEY (id);

-- Name: pizza pizza_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pizza
    ADD CONSTRAINT pizza_pkey PRIMARY KEY (id);

-- Name: promo_code promo_code_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.promo_code
    ADD CONSTRAINT promo_code_pkey PRIMARY KEY (id);

-- Name: authorities role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.authorities
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);

-- Name: status status_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.status
    ADD CONSTRAINT status_pkey PRIMARY KEY (id);

-- Name: authority uk_nrgoi6sdvipfsloa7ykxwlslf; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.authority
    ADD CONSTRAINT uk_nrgoi6sdvipfsloa7ykxwlslf UNIQUE (authority);

-- Name: user_client user_client_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_client
    ADD CONSTRAINT user_client_pkey PRIMARY KEY (id);

-- Name: user_client_authorities fk5ex4s8pxn92nnwln3s5h0mam0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_client_authorities
    ADD CONSTRAINT fk5ex4s8pxn92nnwln3s5h0mam0 FOREIGN KEY (authorities_id) REFERENCES public.authority(id);

-- Name: order_line fk_order_line_order_command_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.order_line
    ADD CONSTRAINT fk_order_line_order_command_id FOREIGN KEY (order_id) REFERENCES public.order_command(id);

-- Name: order_line fk_order_line_pizza_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.order_line
    ADD CONSTRAINT fk_order_line_pizza_id FOREIGN KEY (pizza_id) REFERENCES public.pizza(id);

-- Name: order_command fk_order_promo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.order_command
    ADD CONSTRAINT fk_order_promo FOREIGN KEY (promo_code_id) REFERENCES public.promo_code(id);

-- Name: order_command fk_order_status; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.order_command
    ADD CONSTRAINT fk_order_status FOREIGN KEY (statut_id) REFERENCES public.status(id);

-- Name: order_command fk_order_user; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.order_command
    ADD CONSTRAINT fk_order_user FOREIGN KEY (client_id) REFERENCES public.user_client(id);

-- Name: pizza fk_pizza_cat; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pizza
    ADD CONSTRAINT fk_pizza_cat FOREIGN KEY (cat_id) REFERENCES public.category(id);

-- Name: recipe fk_recipe_ingredient_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recipe
    ADD CONSTRAINT fk_recipe_ingredient_id FOREIGN KEY (ingredient_id) REFERENCES public.ingredient(id);

-- Name: recipe fk_recipe_pizza_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recipe
    ADD CONSTRAINT fk_recipe_pizza_id FOREIGN KEY (pizza_id) REFERENCES public.pizza(id);

-- Name: user_client fk_user_address; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_client
    ADD CONSTRAINT fk_user_address FOREIGN KEY (address_id) REFERENCES public.address(id);

-- Name: user_client fk_user_bank_account; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_client
    ADD CONSTRAINT fk_user_bank_account FOREIGN KEY (bank_account_id) REFERENCES public.bank_account(id);

-- Name: user_client fk_user_role; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_client
    ADD CONSTRAINT fk_user_role FOREIGN KEY (authority_id) REFERENCES public.authorities(id);

-- Name: user_client_authorities fkighfd9fafj7ych0eflohpb6n4; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_client_authorities
    ADD CONSTRAINT fkighfd9fafj7ych0eflohpb6n4 FOREIGN KEY (user_entity_id) REFERENCES public.user_client(id);


--------------------------  best_pizza  ------------------------------

CREATE TABLE public.best_pizza (user_client_id integer NOT NULL, pizza_id integer NOT NULL);

ALTER TABLE public.best_pizza OWNER TO pizzeriadb;
-------------------------------------------------------------------------
-- Name: best_pizza fk_best_pizza_user_client_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.best_pizza
    ADD CONSTRAINT fk_best_pizza_user_client_id FOREIGN KEY (user_client_id) REFERENCES public.user_client(id);

-- Name: best_pizza fk_best_pizza_pizza_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.best_pizza
    ADD CONSTRAINT fk_best_pizza_pizza_id FOREIGN KEY (pizza_id) REFERENCES public.pizza(id);

