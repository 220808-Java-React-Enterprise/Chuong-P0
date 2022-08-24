select * from users;

select * from books;

select * from publishers ;

select * from orders;

select * from order_details;

select * from shopping_cart;

select * from orders where user_id = 'd1cd0983-bfe8-4ff4-b2dd-4b5140782b3e';


SELECT * FROM users WHERE firstname like '%Mar%' or lastName like '%Pu%';


select * from orders order by orderedDate desc;

delete from users where username = 'jeffbezos12';