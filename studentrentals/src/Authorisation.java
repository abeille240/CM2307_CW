import java.io.BufferedReader;
import java.io.FileReader;

public class Authorisation {

    private String username;
    private String password;
    private String filepath;
    //private int accType;

    public Authorisation(String username, String password){
        this.username = username;
        this.password = password;
    }
    
    public static Account authLogin(String username, String password, String filepath, String delimiter) {
        String currentLine;
        String data[];
        try {
            FileReader freader = new FileReader(filepath);
            BufferedReader breader = new BufferedReader(freader);

            while((currentLine = breader.readLine()) != null)
            {
                data = currentLine.split(delimiter);
                String fileUsername = data[0];
                String filePassword = data[1];
                int accType = Integer.parseInt(data[2]);

                if(data[0].equals(username) && data[1].equals(password)){
    
                    Account existing = AccountManager.getAccount(username);
                    if(existing != null){
                        return existing;
                    }
                    
                    // instantiate account
                    Account account;
                    if(accType == 1) {
                        account = new HomeOwner(username, password);
                    }
                    else if(accType == 2){
                        account = new Student(username, password, null);
                    }
                    else{
                        return null;
                    }

                    AccountManager.addAccount(account);
                    return account;
                }

            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public void logout() {
        // logout logic
    }


}
