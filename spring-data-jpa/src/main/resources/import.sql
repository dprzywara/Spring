insert into library (id, name) values (1, 'Pierwsza biblioteka');
insert into library (id, name) values (2, 'Druga biblioteka');
insert into library (id, name) values (3, 'Trzecia biblioteka');

insert into book values (1, 'Pierwsza książka',1);
insert into book values (2, 'Druga książka',1);
insert into book values (3, 'Trzecia książka',2);

insert into author (id, first_name, last_name) values (7, 'Jan', 'Kowalski');
insert into author (id, first_name, last_name) values (8, 'Zbigniew', 'Nowak');
insert into author (id, first_name, last_name) values (9, 'Janusz', 'Jankowski');

insert into book_author(book_id, author_id) values (1, 7);
insert into book_author(book_id, author_id) values (2, 8);
insert into book_author(book_id, author_id) values (3, 9);