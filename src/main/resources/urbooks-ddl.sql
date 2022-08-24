drop table if exists users;
drop table if exists books;
drop table if exists orders;
drop table if exists order_details;
drop table if exists publishers;
drop table if exists shopping_cart;


create table users (
	id varchar not null,
	username varchar not null,
	password varchar not null,
	role varchar not null,
	email varchar,
	phone varchar,
	firstName varchar,
	lastName varchar,
	
	primary key (id)
);




create table books (
	id varchar not null,
	isbn varchar not null unique,
	title varchar not null,
	publisher_id int,
	price float8 not null,
	quantity integer not null,
	
	primary key(id),
	
	FOREIGN KEY(publisher_id) 
		REFERENCES publishers(id)

);

create table orders (
	id varchar not null,
	subTotal float8 not null,
	tax float8 not null,
	grand_total float8 not null,
	orderedDate date,
	status boolean not null,
	user_id varchar,
	
	primary key(id),
		
	FOREIGN KEY(user_id) 
		REFERENCES users(id)
		
);

create table order_details (
 order_id varchar not null,
 book_id varchar not null,
 quantity integer not null,
 status boolean,
 
primary key(order_id, book_id),
		
FOREIGN KEY(order_id) 
		REFERENCES orders(id),
		
FOREIGN KEY(book_id) 
	REFERENCES books(id)
 
);


create table publishers (
	id SERIAL not null,
	name varchar not null,
	address varchar not null,
	city varchar not null,
	state varchar not null,
	zipcode varchar not null,
	phone varchar not null,
	
	constraint pk_publisher_id
		primary key(id)
);

create table shopping_cart (
	user_id varchar not null,
	book_id varchar not null,
	quatity integer not null,
	isDone boolean,
	
	primary key(user_id, book_id),
	
	FOREIGN KEY(user_id) 
		REFERENCES users(id),
		
	FOREIGN KEY(book_id) 
		REFERENCES books(id)
);




