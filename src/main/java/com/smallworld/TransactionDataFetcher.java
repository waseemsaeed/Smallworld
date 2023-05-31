package com.smallworld;

import com.smallworld.data.Transaction;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Optional;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
public class TransactionDataFetcher {

    /**
     * Returns the sum of the amounts of all transactions
     */
    public static double getTotalTransactionAmount() {

        try (FileReader fileReader = new FileReader("transactions.json")) {
            Gson gson = new Gson();
            Type transactionListType = new TypeToken<List<Transaction>>() {}.getType();
            List<Transaction> transactions = gson.fromJson(fileReader, transactionListType);

            // Now you have the list of transactions to work with
            // You can iterate over the list and perform operations on each transaction
            double totalAmount = transactions.stream()
                    .mapToDouble(Transaction::getAmount)
                    .sum();
            return totalAmount;
        } catch (IOException e) {
            e.printStackTrace();
        }


        throw new UnsupportedOperationException();
    }

    /**
     * Returns the sum of the amounts of all transactions sent by the specified client
     */
    public static double getTotalTransactionAmountSentBy(String senderFullName) {

        try (FileReader fileReader = new FileReader("transactions.json")) {
            Gson gson = new Gson();
            Type transactionListType = new TypeToken<List<Transaction>>() {}.getType();
            List<Transaction> transactions = gson.fromJson(fileReader, transactionListType);

            // Now you have the list of transactions to work with
            // You can iterate over the list and perform operations on each transaction
//            List<Transaction> sortedTransactions = transactions.stream()
//                    .sorted(Comparator.comparing(transaction -> {
//                        if (transaction.getSenderFullName().equals(senderFullName)) {
//                            return 0;
//                        } else if (transaction.getWhere().equals("B")) {
//                            return 1;
//                        } else {
//                            return 2;
//                        }
//                    }))
//                    .collect(Collectors.toList());


            double totalAmount  = transactions.stream()
                    .filter(transaction -> transaction.getSenderFullName().equals(senderFullName))
                    .mapToDouble(Transaction::getAmount)
                    .sum();

return totalAmount;
        } catch (IOException e) {
            e.printStackTrace();
        }

        throw new UnsupportedOperationException();
    }

    /**
     * Returns the highest transaction amount
     */
    public double getMaxTransactionAmount() {
        throw new UnsupportedOperationException();
    }

    /**
     * Counts the number of unique clients that sent or received a transaction
     */
    public long countUniqueClients() {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns whether a client (sender or beneficiary) has at least one transaction with a compliance
     * issue that has not been solved
     */
    public boolean hasOpenComplianceIssues(String clientFullName) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns all transactions indexed by beneficiary name
     */
    public Map<String, Transaction> getTransactionsByBeneficiaryName() {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the identifiers of all open compliance issues
     */
    public Set<Integer> getUnsolvedIssueIds() {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns a list of all solved issue messages
     */
    public List<String> getAllSolvedIssueMessages() {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the 3 transactions with the highest amount sorted by amount descending
     */
    public static List<Transaction> getTop3TransactionsByAmount() {
        try (FileReader fileReader = new FileReader("transactions.json")) {
            Gson gson = new Gson();
            Type transactionListType = new TypeToken<List<Transaction>>() {}.getType();
            List<Transaction> transactions = gson.fromJson(fileReader, transactionListType);

        List<Transaction> sortedTransactions = transactions.stream()
                .sorted(Comparator.comparing(Transaction::getAmount, Comparator.reverseOrder())).limit(3)
                .collect(Collectors.toList());
            return sortedTransactions;
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the senderFullName of the sender with the most total sent amount
     */
    public Optional<String> getTopSender() {
        throw new UnsupportedOperationException();
    }

     public static void main(String[] args) {
         List<Transaction> tr=  getTop3TransactionsByAmount();
         for (Transaction transaction : tr) {
             System.out.println(transaction.getAmount());
         }
        System.out.println(getTotalTransactionAmount());
         System.out.println(getTotalTransactionAmountSentBy("Tom Shelby"));
    }
}
