package model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
public class Account {
    private String id;
    private String accountName;
    private String moneyAmount;

    public Account(String accountName, String moneyAmount) {
        this.accountName = accountName;
        this.moneyAmount = moneyAmount;
        this.id = UUID.randomUUID().toString();
    }
}