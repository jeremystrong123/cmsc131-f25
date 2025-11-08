package projects.bank;

import java.io.FileWriter;
import java.io.IOException;

public class Audit {
    //Instance Variables
    private FileWriter writer;

    //Constructor
    public Audit(String file) throws IOException{
        if (file == null) {
            throw new IllegalArgumentException("file name must not be null");
        }
        writer = new FileWriter(file);
    }

    //Methods
    public void close() {
        try {
            writer.flush();
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(String s) {
        try {
            writer.write(s + System.lineSeparator());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void accountDoesNotExist(Transaction t) {
        write(
            String.format("%s ERROR account does not exist: %s", Utils.timestamp(), t.toString())
        );
    }

    public void nonSufficientFunds(Transaction t, Account a) {
        write(
            String.format("%s ERROR non-sufficient funds available: %s, current balance: %s", Utils.timestamp(), t.toString(), a.getBalance())
        );
    }

    public void recordTransaction(Transaction t, Account a) {
        write(
            String.format("%s INFO: %s, new balance: %s", Utils.timestamp(), t.toString(), a.getBalance())
        );
    }
}
