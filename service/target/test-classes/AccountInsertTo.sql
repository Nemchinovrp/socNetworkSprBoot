INSERT INTO ACCOUNT_TBL (ACC_ID, NAME, MIDDLE_NAME, SURNAME, DATE_OF_BIRTH, HOME_ADDRESS, WORK_ADDRESS, EMAIL, ICQ,
                         SKYPE, ADD_INFO)
VALUES
  (1, 'Sergey', 'Olegovich', 'Rabotyaga', '1991-03-12',
      'Sergey home address', 'Sergey work address', 'sergey@mail.ru', '124423', 'sergey@live.com', 'like sun'),
  (2, 'Genna', NULL, 'Pushkin', '1993-06-20',
      NULL, 'Genna work address', 'genna@mail.ru', NULL, 'genna@live.com', NULL),
  (888, '888', NULL, '888Surname', '1993-06-20',
        NULL, '888 work address', '888@mail.ru', NULL, '888@live.com', NULL);

INSERT INTO PERSONAL_PHONE_TBL (ACC_ID, PHONE)
VALUES
  (1, '+7(964)762-12-11'), (1, '+7(925)123-12-44'),
  (2, '+7(964)978-73-43'), (2, '+7(924)097-35-83');


INSERT INTO WORK_PHONE_TBL (ACC_ID, PHONE)
VALUES
  (1, '+7(499)222-11-23'), (1, '+7(495)845-22-45'),
  (2, '+7(499)222-11-00'), (2, '+7(495)000-77-55');

COMMIT;
