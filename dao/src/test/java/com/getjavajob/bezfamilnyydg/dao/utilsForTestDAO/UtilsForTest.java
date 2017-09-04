package com.getjavajob.bezfamilnyydg.dao.utilsForTestDAO;

import com.getjavajob.bezfamilnyydg.models.Account;
import com.getjavajob.bezfamilnyydg.models.PersonalPhone;
import com.getjavajob.bezfamilnyydg.models.WorkPhone;
import org.h2.tools.RunScript;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class UtilsForTest {
    public static void initializingData(DataSource dataSource) throws SQLException {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            RunScript.execute(connection, new InputStreamReader(ClassLoader.getSystemResourceAsStream("AccountInsertTo.sql")));
            RunScript.execute(connection, new InputStreamReader(ClassLoader.getSystemResourceAsStream("PersonalPhonesInsertTo.sql")));
            RunScript.execute(connection, new InputStreamReader(ClassLoader.getSystemResourceAsStream("WorkPhonesInsertTo.sql")));
            RunScript.execute(connection, new InputStreamReader(ClassLoader.getSystemResourceAsStream("RequestsToFriendsInsert.sql")));
            RunScript.execute(connection, new InputStreamReader(ClassLoader.getSystemResourceAsStream("FriendshipsInsert.sql")));
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public static void deleteAllAfterTest(DataSource dataSource) throws InterruptedException, FileNotFoundException, SQLException {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            RunScript.execute(connection, new InputStreamReader(ClassLoader.getSystemResourceAsStream("AccountDeleteAll.sql")));
            RunScript.execute(connection, new InputStreamReader(ClassLoader.getSystemResourceAsStream("PersonalPhonesDeleteAll.sql")));
            RunScript.execute(connection, new InputStreamReader(ClassLoader.getSystemResourceAsStream("WorkPhonesDeleteAll.sql")));
            RunScript.execute(connection, new InputStreamReader(ClassLoader.getSystemResourceAsStream("RequestsToFriendsDeleteAll.sql")));
            RunScript.execute(connection, new InputStreamReader(ClassLoader.getSystemResourceAsStream("FriendshipsDeleteAll.sql")));
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public static Account initAccount() {
        Account account = new Account("Sergey", "Rabotyaga", "Olegovich", LocalDate.parse("1991-03-12"),
                null, null, "Sergey home address",
                "Sergey work address", "sergey@mail.ru", "124423", "sergey@live.com",
                "like sun", "12345", null);
        Set<PersonalPhone> personalPhones = new HashSet<>(Arrays.asList(new PersonalPhone("+7(000)000-00-00", account),
                new PersonalPhone("+7(111)111-11-11", account)));

        Set<WorkPhone> workPhones = new HashSet<>(Arrays.asList(new WorkPhone("+7(000)000-00-00", account),
                new WorkPhone("+7(111)111-11-11", account)));
        account.setPersonalPhoneNumbers(personalPhones);
        account.setWorkPhoneNumbers(workPhones);
        return account;
    }
}
