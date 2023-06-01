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
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TransactionDataFetcher {
    private static List<Transaction> transactions=null;
    static {
        try (FileReader fileReader = new FileReader("transactions.json")) {
            Gson gson = new Gson();
            Type transactionListType = new TypeToken<List<Transaction>>() {}.getType();
            transactions = gson.fromJson(fileReader, transactionListType);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     * Returns the sum of the amounts of all transactions
     */
    public static double getTotalTransactionAmount() {

        try {
            double totalAmount = transactions.stream()
                    .mapToDouble(Transaction::getAmount)
                    .sum();
            return totalAmount;
        } catch (Exception e) {
            e.printStackTrace();
        }


        throw new UnsupportedOperationException();
    }

    /**
     * Returns the sum of the amounts of all transactions sent by the specified client
     */
    public static double getTotalTransactionAmountSentBy(String senderFullName) {

        try{
            double totalAmount  = transactions.stream()
                    .filter(transaction -> transaction.getSenderFullName().equals(senderFullName))
                    .mapToDouble(Transaction::getAmount)
                    .sum();

        return totalAmount;
        } catch (Exception e) {
            e.printStackTrace();
        }

        throw new UnsupportedOperationException();
    }

    /**
     * Returns the highest transaction amount
     */
    public static  double getMaxTransactionAmount() {

        double highestAmount=0;
        try {
            highestAmount = transactions.stream()
                    .map(Transaction::getAmount)
                    .max(Comparator.naturalOrder()).get();
            return highestAmount;
        }catch (Exception e){

        };
        throw new UnsupportedOperationException();

}




    /**
     * Counts the number of unique clients that sent or received a transaction
     */
    public static long countUniqueClients() {

        try {

            long uniqueClientsCount = transactions.stream()
                    .flatMap(transaction -> Stream.of(transaction.getBeneficiaryFullName(),transaction.getSenderFullName()))
                    .distinct()
                    .count();

            return uniqueClientsCount;
        } catch (Exception e) {
            e.printStackTrace();
        }


        throw new UnsupportedOperationException();
    }

    /**
     * Returns whether a client (sender or beneficiary) has at least one transaction with a compliance
     * issue that has not been solved
     */
    public static boolean hasOpenComplianceIssues(String clientFullName) {
        try {

            boolean hasCompliance  = transactions.stream()
                    .anyMatch(transaction -> (transaction.getSenderFullName().equals(clientFullName) || transaction.getBeneficiaryFullName().equals(clientFullName)) && transaction.isIssueSolved()==false)
                    ;
            return hasCompliance;
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new UnsupportedOperationException();
    }

    /**
     * Returns all transactions indexed by beneficiary name
     */
    public static Map<String, List<Transaction>> getTransactionsByBeneficiaryName() {
        try {

            Map<String, List<Transaction>> transactionsByBeneficiary = transactions.stream()
                    .collect(Collectors.groupingBy(Transaction::getBeneficiaryFullName));

            return transactionsByBeneficiary;
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the identifiers of all open compliance issues
     */
    public static Set<Integer> getUnsolvedIssueIds() {
        try {
            Set<Integer> sortedTransactions = transactions.stream().filter(transaction ->transaction.isIssueSolved()==false ).map(Transaction::getIssueId).collect(Collectors.toSet());

           return sortedTransactions;

        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new UnsupportedOperationException();
    }

    /**
     * Returns a list of all solved issue messages
     */
    public static List<String> getAllSolvedIssueMessages() {
        try {
            List<String> sortedTransactions = transactions.stream().map(Transaction::getIssueMessage).collect(Collectors.toList());

            return sortedTransactions;
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the 3 transactions with the highest amount sorted by amount descending
     */
    public static List<Transaction> getTop3TransactionsByAmount() {
        try {
        List<Transaction> sortedTransactions = transactions.stream()
                .sorted(Comparator.comparing(Transaction::getAmount, Comparator.reverseOrder())).limit(3)
                .collect(Collectors.toList());
            return sortedTransactions;
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the senderFullName of the sender with the most total sent amount
     */
    public Optional<String> getTopSender() {
        try (FileReader fileReader = new FileReader("transactions.json")) {
            Gson gson = new Gson();
            Type transactionListType = new TypeToken<List<Transaction>>() {}.getType();
            List<Transaction> transactions = gson.fromJson(fileReader, transactionListType);

           // Optional<String> totalAmountBySender = transactions.stream().collect(transactions.)


         //   return totalAmountBySender;
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new UnsupportedOperationException();
    }

     public static void main(String[] args) {

        System.out.println("Max.. "+getMaxTransactionAmount());

        System.out.println("Total:  "+countUniqueClients());

        System.out.println(hasOpenComplianceIssues("Tom Shelby"));

        Map<String,List<Transaction>> map=getTransactionsByBeneficiaryName();
         for (Map.Entry<String, List<Transaction>> entry : map.entrySet()) {
             String key = entry.getKey();
             List<Transaction> value = entry.getValue();
             System.out.println("Key: " + key );
            for(Transaction transaction:value){
                System.out.println(transaction);
            }

         }
        Set<Integer> list1=getUnsolvedIssueIds();
        System.out.println(list1.toString());
        List<String> list=getAllSolvedIssueMessages();

        System.out.println(list.toString());
         List<Transaction> tr=  getTop3TransactionsByAmount();
         for (Transaction transaction : tr) {
             System.out.println(transaction.getAmount());
         }
        System.out.println(getTotalTransactionAmount());
         System.out.println(getTotalTransactionAmountSentBy("Tom Shelby"));
    }
}
