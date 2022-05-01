package online.bingulhan.hanbank.backup;

import online.bingulhan.hanbank.BankServer;

public interface IBackupManager {

    public void backup(BankServer server);
    public BankServer getBackup();

}
