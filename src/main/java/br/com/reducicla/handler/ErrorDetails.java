package br.com.reducicla.handler;

import lombok.Getter;

/**
 * @author User on 19/05/2021
 */

@Getter
public class ErrorDetails {

    private String title;
    private int status;
    private String message;
    private long timestamp;
    private String locale;

    public static final class Builder {
        private String title;
        private int status;
        private String message;
        private long timestamp;
        private String locale;

        private Builder() {
        }

        public static Builder create() {
            return new Builder();
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder status(int status) {
            this.status = status;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder timestamp(long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder locale(String locale) {
            this.locale = locale;
            return this;
        }

        public ErrorDetails build() {
            ErrorDetails errorDetails = new ErrorDetails();
            errorDetails.timestamp = this.timestamp;
            errorDetails.locale = this.locale;
            errorDetails.status = this.status;
            errorDetails.title = this.title;
            errorDetails.message = this.message;
            return errorDetails;
        }
    }
}