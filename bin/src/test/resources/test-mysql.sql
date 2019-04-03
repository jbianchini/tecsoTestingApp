BEGIN;
TRUNCATE account;
TRUNCATE move;
 
INSERT INTO `account` (`_account_id`, `_account_number`, `_currency`, `_balance`) VALUES 
(1, 10010, 'USD', 230.34),
(2, 10020, 'USD', 653.11),
(3, 10030, 'USD', 732.10),
(4, 10040, 'EUR', 0.00);

INSERT INTO `move` (`_move_id`, `_account_id`, `_amount`, `_date`, `_description`, `_type`) VALUES
(1, 1, 342.34, '2017-03-01', 'descripcion 1', 'debit'),
(2, 1, 11.77, '2017-03-01', 'descripcion 1', 'credit'),
(3, 2, 456.24, '2017-03-01', 'descripcion 1', 'debit'),
(4, 2, 928.22, '2017-03-01', 'descripcion 1', 'credit'),
(5, 2, 15.99, '2017-03-01', 'descripcion 1', 'debit'),
(6, 3, 53.85, '2017-03-01', 'descripcion 1', 'debit');
 
COMMIT;