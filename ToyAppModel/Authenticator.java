import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.security.Key;
import java.sql.*;
import java.util.ArrayList;


public class Authenticator {

    ArrayList<Account> accounts = new ArrayList<>();
    // SQLite connection string
    String url = "jdbc:sqlite:../Database/seguranca_software.db";
    private static final String ALGO = "AES";
    private static final byte[] keyValue =
            new byte[] { 'F', 'C', 'T', '/', 'U', 'N', 'L', 'r',
            'o', 'c', 'k','s', '!', '!', 'd', 'i' };
    Key key = new SecretKeySpec(keyValue, ALGO);

    static Connection conn;
    Authenticator() {
        conn = connect(url);
    }
    private Connection connect(String url) {
        try {
            Class.forName("org.sqlite.JDBC");
            new File("../Database").mkdirs();
            return DriverManager.getConnection(url);
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
            return null;
        }
    }
    public String encrypt(String Data) throws Exception {
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(Data.getBytes());
        return java.util.Base64.
                getEncoder().encodeToString(encVal);
    }

    public String decrypt(String encrypted) throws Exception {
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedValue = java.util.Base64.getDecoder().decode(encrypted);
        byte[] decValue = c.doFinal(decodedValue);
        return new String(decValue);
    }

    boolean log_in(String name, String pwd1) throws Exception {
        String getUser_Password = "SELECT * FROM Account WHERE username = ? AND password = ?";
        ResultSet rs = null;
        boolean success = false;
        String enc_pwd = encrypt(pwd1);
            try {
                PreparedStatement pstmt = conn.prepareStatement(getUser_Password);
                pstmt.setString(1, name);
                pstmt.setString(2, enc_pwd);
                rs = pstmt.executeQuery();
                success = rs.next();
                if(success){
                    String sql = "UPDATE Account SET is_Logged_In = ? WHERE username= ?";
                    String bool = "true";
                    try{
                        PreparedStatement psmt = conn.prepareStatement(sql);
                        psmt.setString(1, bool);
                        psmt.setString(2, name);
                        psmt.executeUpdate();
                        psmt.close();
                        return true;
                    }catch (SQLException e) {
                        System.out.println(e.getMessage());
                        return false;
                    }
                } else {
                    return false;
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                System.exit(0);
                return false;
            }
    }

    boolean create_account(String name, String pwd1, String pwd2) throws Exception {
        Account n_acc;
        if(pwd1.equals(pwd2)){
            n_acc= new Account(name,pwd1);
            n_acc.logged_in=true;
            String sql = "INSERT INTO Account VALUES(?,?,?)";
            String enc_pwd= encrypt(n_acc.pwd);
            try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,n_acc.name);
                pstmt.setString(2, enc_pwd);
                pstmt.setString(3, String.valueOf(n_acc.logged_in));
                pstmt.executeUpdate();
                pstmt.close();
                return true;
                }catch( SQLException e){
                System.out.println(e.getMessage());
                return false;
            }
        } else {
            return false;
        }
    }
    boolean delete_account(String name, String pwd1){
        String sql = "DELETE FROM Account WHERE username = ?";
        ResultSet rs = null;
        boolean success = false;
        try{
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1,name);
            rs = psmt.executeQuery();
            success = rs.next();
            psmt.close();
            return success;
        }catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    void change_pwd(String name, String pwd1){
        String sql= "UPDATE Account SET password = ? WHERE username= ?";
        try {
            PreparedStatement psmt= conn.prepareStatement(sql);
            psmt.setString(1,pwd1);
            psmt.setString(2,name);
            psmt.executeUpdate();
            psmt.close();
        }catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    boolean logout(String name){
        String setLogInBool= "UPDATE Account SET is_Logged_In = ? WHERE username= ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(setLogInBool);
            pstmt.setString(1, "false");
            pstmt.setString(2, name);
            pstmt.executeUpdate();
            pstmt.close();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}

