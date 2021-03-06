CREATE TABLE mysocialnetwork.ROLES_IN_GROUPS_TBL
(
  GROUP_ID INT NOT NULL,
  ACC_ID INT NOT NULL,
  ROLE_ID INT NOT NULL,
  CONSTRAINT roles_in_groups_tbl_pk PRIMARY KEY (GROUP_ID, ACC_ID),
  CONSTRAINT roles_in_groups_group_fk FOREIGN KEY (GROUP_ID) REFERENCES GROUP_TBL (GR_ID) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT roles_in_groups_tbl_acc_id_fk FOREIGN KEY (ACC_ID) REFERENCES ACCOUNT_TBL (ACC_ID) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT roles_in_groups_tbl_role_fk FOREIGN KEY (ROLE_ID) REFERENCES ROLES_FOR_GROUPS_TBL (ID) ON DELETE CASCADE ON UPDATE CASCADE
);