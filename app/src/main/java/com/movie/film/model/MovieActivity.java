package com.movie.film.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class MovieActivity extends MovieData {
    private Long transactionId;		// before transaction identifier
    private Long outTransactionId;


    public enum MovieProcessingType {
        EXPENSE_CLAIM("Expense Claim"),
        Human_Resources("Human Resource"),
        DEFAULT("");

        private String processingType;


        private MovieProcessingType (final String MovieProcessingType) {

            this.processingType = MovieProcessingType;
        }



        public static MovieProcessingType getProcessingType (String serviceInfo) {
            MovieProcessingType MovieProcessingType = null;

            switch (serviceInfo) {
                case "Expense Claim":
                    MovieProcessingType = MovieProcessingType.EXPENSE_CLAIM;
                    break;


            }
            return MovieProcessingType;
        }


        }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getOutTransactionId() {
        return outTransactionId;
    }

    public void setOutTransactionId(Long outTransactionId) {
        this.outTransactionId = outTransactionId;
    }
}
