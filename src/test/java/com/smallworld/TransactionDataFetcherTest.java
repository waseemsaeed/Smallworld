package com.smallworld;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;


class TransactionDataFetcherTest {

TransactionDataFetcher transactionDataFetcher=new TransactionDataFetcher();

    @Test
    public void getTotalTransactionAmount() {
        double expectedSum = 4371.37;

        // Assert
        assertEquals(expectedSum, transactionDataFetcher.getTotalTransactionAmount());
    }

    @Test
    public void getTop3TransactionsByAmount(){

        double firstAmount=985;
        double secondAmount=666;
        double thidAmount=666;
        assertEquals(firstAmount,transactionDataFetcher.getTop3TransactionsByAmount().get(0).getAmount());
        assertEquals(secondAmount,transactionDataFetcher.getTop3TransactionsByAmount().get(1).getAmount());
        assertEquals(thidAmount,transactionDataFetcher.getTop3TransactionsByAmount().get(2).getAmount());
    }
    @Test
    public void getUnsolvedIssueIds(){
        //[1, 3, 99, 54, 15]
        Set<Integer> expected=new HashSet<>();
        expected.add(1);
        expected.add(3);
        expected.add(99);
        expected.add(54);
        expected.add(15);
        assertNotNull(transactionDataFetcher.getUnsolvedIssueIds());
        assertEquals(expected,transactionDataFetcher.getUnsolvedIssueIds());
    }
    @Test
    public void getAllSolvedIssueMessages(){
        String messageOne="Looks like money laundering";
        String messageTwo="Never gonna give you up";
        String messageThree="Something's fishy";
        String messageFour="Never gonna run around and desert you";
        assertNotNull(transactionDataFetcher.getAllSolvedIssueMessages());
        boolean messageOneCheck=transactionDataFetcher.getAllSolvedIssueMessages().contains(messageOne);
        assertFalse(messageOneCheck);
        boolean messageTwoCheck=transactionDataFetcher.getAllSolvedIssueMessages().contains(messageTwo);
        assertTrue(messageTwoCheck);
        boolean messageThreeCheck=transactionDataFetcher.getAllSolvedIssueMessages().contains(messageThree);
        assertFalse(messageThreeCheck);

        boolean messageFourCheck=transactionDataFetcher.getAllSolvedIssueMessages().contains(messageFour);
        assertTrue(messageFourCheck);
    }
    @Test
    public void countUniqueClients(){

        int totalClients=14;
        assertEquals(totalClients,transactionDataFetcher.countUniqueClients());
    }
    @Test
    public void hasOpenComplianceIssues(){
        String senderBenName="Tom Shelby";
        String senderBenNameSecond="Arthur Shelby";
        String senderBenThird="Oswald Mosley";
        boolean hasOpenCompliance=transactionDataFetcher.hasOpenComplianceIssues(senderBenName);
        assertTrue(hasOpenCompliance);
        boolean issuenNotResolved=transactionDataFetcher.hasOpenComplianceIssues(senderBenNameSecond);
        assertTrue(issuenNotResolved);
        boolean IssueResolved=transactionDataFetcher.hasOpenComplianceIssues(senderBenThird);
        assertFalse(IssueResolved);

    }
    @Test
    public void getTotalTransactionAmountSentBy(){

        double totalAmount=828.26;
        assertEquals(totalAmount,transactionDataFetcher.getTotalTransactionAmountSentBy("Tom Shelby"));
    }
    @Test
    public void getTopSender(){
        String topSender="Grace Burgess";
        assertEquals(topSender,transactionDataFetcher.getTopSender().get());
    }
}