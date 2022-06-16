package com.ninjaone.inventory.domain.entity;

import com.ninjaone.domain.valueobject.Money;

public class Charge {

    /**
     * FIELDS
     */
    private String message;
    private Money  price;

    /**
     * GETTERS
     */


    public String getMessage() {
        return message;
    }

    public Money getPrice() {
        return price;
    }

    /**
     * BUILDER
     */
    private Charge(Builder builder) {
        message = builder.message;
        price = builder.price;
    }

    public static Builder builder() {
        return new Builder();
    }


    public static final class Builder {
        private String message;
        private Money price;

        private Builder() {
        }

        public Builder message(String val) {
            message = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Charge build() {
            return new Charge(this);
        }
    }
}
