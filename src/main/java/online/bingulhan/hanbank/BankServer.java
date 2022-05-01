package online.bingulhan.hanbank;

import java.util.ArrayList;

public class BankServer {

    public ArrayList<BankAccount> accounts = new ArrayList<>();

    public BankServer() {}
    public BankServer(ArrayList<BankAccount> accounts) { this.accounts=accounts; }
}
