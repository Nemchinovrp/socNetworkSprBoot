package com.getjavajob.bezfamilnyydg.common.util;

import com.getjavajob.bezfamilnyydg.models.Account;
import com.getjavajob.bezfamilnyydg.models.DTOAccount;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.getjavajob.bezfamilnyydg.common.Constants.AVATAR_AS_PART_REQUEST;
import static com.getjavajob.bezfamilnyydg.common.util.Converter.convertToStringBase64;

public class Initializer {

    public static String getAvatarFromAccount(Account account, String contextPath) {
        StringBuilder avatar = new StringBuilder();

        byte[] avatarBytes = account.getAvatar();
        if (avatarBytes != null && avatarBytes.length != 0) {
            avatar.append("data:image/jpg;base64," + convertToStringBase64(avatarBytes));
        } else {
            avatar.append(contextPath + "/img/defaultAvatar.jpg");
        }

        return avatar.toString();
    }

    public static List<DTOAccount> createListOfDTOAccounts(List<Account> accounts, boolean isLiteVersion, String contextPath) {
        if (accounts != null) {

            List<DTOAccount> dtoAccounts = new ArrayList<>();
            for (Account account : accounts) {
                DTOAccount dtoAccount = createDTOAccount(account, isLiteVersion, contextPath);
                dtoAccounts.add(dtoAccount);
            }
            return dtoAccounts;

        } else {
            return null;
        }
    }

    public static DTOAccount createDTOAccount(Account account, boolean isLiteVersion, String contextPath) {
        DTOAccount dtoAccount = new DTOAccount();
        dtoAccount.setId(account.getId());
        dtoAccount.setName(account.getName());
        dtoAccount.setSurname(account.getSurname());
        if (!isLiteVersion) {
            dtoAccount.setAvatarStringRepresentation(getAvatarFromAccount(account, contextPath));
        }
        return dtoAccount;
    }

    private static byte[] getAvatarFromRequest(HttpServletRequest request) throws IOException, ServletException, SQLException {
        //get parameters
        Part avatarPart = request.getPart(AVATAR_AS_PART_REQUEST);
        InputStream avatarContent = avatarPart.getInputStream();

        return IOUtils.toByteArray(avatarContent);
    }
}
