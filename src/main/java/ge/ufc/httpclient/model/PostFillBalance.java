package ge.ufc.httpclient.model;

public class PostFillBalance {
    private String agent_transaction_id;
    private  int user_id;
    private double amount;

    public PostFillBalance(String agent_transaction_id, int user_id, double amount) {
        this.agent_transaction_id = agent_transaction_id;
        this.user_id = user_id;
        this.amount = amount;
    }
}
