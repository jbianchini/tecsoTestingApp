BEGIN;
TRUNCATE `account`;
TRUNCATE `move`;
 
INSERT INTO `account` (`_account_id`, `_account_number`, `_currency`, `_balance`) VALUES 
(1, 10010, 'ARS', 230.34),
(2, 10020, 'USD', 653.11),
(3, 10030, 'USD', 732.10),
(4, 10040, 'EUR', 0.00);

INSERT INTO `move` (`_move_id`, `_account_id`, `_account_number`, `_amount`, `_date`, `_description`, `_type`) VALUES
(1, 1, 10010, 342.34, '2017-03-01', 'descripcion 1', 'debit'),
(2, 1, 10010, 11.77, '2017-03-01', 'descripcion 1', 'credit'),
(3, 2, 10020, 456.24, '2017-03-01', 'descripcion 1', 'debit'),
(4, 2, 10020, 928.22, '2017-03-01', 'descripcion 1', 'credit'),
(5, 2, 10020, 15.99, '2017-03-01', 'descripcion 1', 'debit'),
(6, 3, 10030, 53.85, '2017-03-01', 'descripcion 1', 'debit');
 
UPDATE `account_sequence` set next_val = 5;
UPDATE `move_sequence` set next_val = 7;

COMMIT;