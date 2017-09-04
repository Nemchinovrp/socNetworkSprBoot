INSERT INTO ACCOUNT_TBL (ACC_ID, NAME, SURNAME, MIDDLE_NAME, DATE_OF_BIRTH, HOME_ADDRESS, WORK_ADDRESS, EMAIL, ICQ,
                         SKYPE, ADD_INFO, PASSWORD, IMAGE)
VALUES
  (1, 'Sergey', 'Rabotyaga', 'Olegovich', '1991-03-12',
      'Sergey home address', 'Sergey work address', 'sergey@mail.ru', '124423', 'sergey@live.com', 'like sun', '12345',
   NULL),
  (2, 'Genna', 'Pushkin', NULL, '1993-06-20',
      NULL, 'Genna work address', 'genna@mail.ru', NULL, 'genna@live.com', NULL, '12345', NULL),
  (3, 'Number3', 'Rabotyaga', 'Olegovich', '1991-03-12',
      'Sergey home address', 'Sergey work address', 'number3@mail.ru', '124423', 'number3@live.com', 'like sun',
   '12345', NULL),
  (4, 'Number4', 'Rabotyaga', 'Olegovich', '1991-03-12',
      'Sergey home address', 'Sergey work address', 'number4@mail.ru', '124423', 'number4@live.com', 'like sun',
   '12345', NULL);
COMMIT;
