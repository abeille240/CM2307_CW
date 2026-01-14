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

                if(data[0].equals(username) && data[1].equals(password)){
    
                // retrieve account type
                int accType = Integer.parseInt(data[2]);
                    
                    // instantiate account
                    if(accType == 1) {
                        return new HomeOwner(username, password);
                    }
                    else if(accType == 2){
                        return new Student(username, password, null);
                    }
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
