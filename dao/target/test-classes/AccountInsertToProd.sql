INSERT INTO ACCOUNT_TBL (ACC_ID, NAME, MIDDLE_NAME, SURNAME, DATE_OF_BIRTH, HOME_ADDRESS, WORK_ADDRESS, EMAIL, ICQ,
                         SKYPE, ADD_INFO, PASSWORD, IMAGE)
VALUES
  (1, 'Kira', Null, 'Knightley', '1985-03-26',
      'Great Britain', NULL, 'kira@mail.ru', '124423', 'kira@live.com', 'like sun', '$2a$10$RTlBYene4BN2MwfVZe4QpODcRSMPwLvxq41kqM3WklxNdHFtyk1fa'),
  (2, 'Genna', NULL, 'Pushkin', '1993-06-20',
      NULL, 'Genna work address', 'genna@mail.ru', NULL, 'genna@live.com', NULL, '12345'),
  (3, 'Will', NULL, 'Smith', '1968-09-25',
      NULL, 'New York', 'will@mail.ru', NULL, 'will@live.com', NULL, '$2a$10$1f4piV3cSrGeR4te0R..YeupnK2XbIJeJjf5eQxemUNfHdrEx101S');

INSERT INTO PERSONAL_PHONE_TBL (ACC_ID, PHONE)
VALUES
  (1, '+7(964)762-12-11'), (1, '+7(925)123-12-44'),
  (2, '+7(964)978-73-43'), (2, '+7(924)097-35-83'),
  (3, '+7(964)928-23-43'), (3, '+7(924)094-36-83');


INSERT INTO WORK_PHONE_TBL (ACC_ID, PHONE)
VALUES
  (1, '+7(499)222-11-23'), (1, '+7(495)845-22-45'),
  (2, '+7(499)222-11-00'), (2, '+7(495)000-77-55'),
  (3, '+7(499)233-11-00'), (3, '+7(495)300-77-55');

COMMIT;
