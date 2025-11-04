-- public.customers definition

-- Drop table

-- DROP TABLE public.customers;

CREATE TABLE public.customers (
	customer_id serial4 NOT NULL,
	first_name varchar(50) NOT NULL,
	last_name varchar(50) NOT NULL,
	email varchar(100) NOT NULL,
	phone_number varchar(20) NULL,
	address varchar(255) NULL,
	city varchar(50) NULL,
	state varchar(50) NULL,
	zip_code varchar(10) NULL,
	age int4 NULL,
	customer_type varchar(50) NULL,
	cust_balance numeric(15, 2) NULL,
	cust_sex varchar(10) NULL,
	join_date date NULL,
	CONSTRAINT customers_email_key UNIQUE (email),
	CONSTRAINT customers_pkey PRIMARY KEY (customer_id)
);


-- public.employees definition

-- Drop table

-- DROP TABLE public.employees;

CREATE TABLE public.employees (
	empid int4 NOT NULL,
	fullname varchar(100) NOT NULL,
	balance numeric(10, 2) NOT NULL,
	emptype varchar(20) NOT NULL,
	sex varchar(1) NULL,
	city varchar(50) NULL,
	joiningdt timestamp DEFAULT now() NULL,
	CONSTRAINT employees_pkey PRIMARY KEY (empid)
);


-- public.products definition

-- Drop table

-- DROP TABLE public.products;

CREATE TABLE public.products (
	product_id serial4 NOT NULL,
	product_name varchar(100) NOT NULL,
	description varchar(255) NULL,
	price numeric(10, 2) NOT NULL,
	stock_quantity int4 NOT NULL,
	created_at timestamptz DEFAULT CURRENT_TIMESTAMP NULL,
	CONSTRAINT products_pkey PRIMARY KEY (product_id),
	CONSTRAINT products_price_check CHECK ((price >= (0)::numeric)),
	CONSTRAINT products_stock_quantity_check CHECK ((stock_quantity >= 0))
);


-- public.orders definition

-- Drop table

-- DROP TABLE public.orders;

CREATE TABLE public.orders (
	order_id serial4 NOT NULL,
	order_date timestamptz DEFAULT CURRENT_TIMESTAMP NULL,
	total_amount numeric(10, 2) NOT NULL,
	description varchar(50) DEFAULT 'Pending'::character varying NULL,
	CONSTRAINT orders_pkey PRIMARY KEY (order_id),
	CONSTRAINT orders_total_amount_check CHECK ((total_amount >= (0)::numeric))
);

-- public.users definition

-- Drop table

-- DROP TABLE public.users;

CREATE TABLE public.users (
	userid int4 NOT NULL,
	username varchar(100) NOT NULL,
	"password" varchar(100) NOT NULL,
	isactive bool NOT NULL,
	email varchar(100) NOT NULL,
	"role" varchar NULL,
	createdby varchar(20) NULL,
	createdat timestamp NULL,
	CONSTRAINT users_pkey PRIMARY KEY (userid),
	CONSTRAINT users_username_key UNIQUE (username)
);


  
