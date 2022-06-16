package com.ninjaone.domain.valueobject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Money {
    private final BigDecimal cost;

    public static final Money ZERO = new Money(BigDecimal.ZERO);

    public Money(BigDecimal cost) {
        this.cost = cost;
    }

    public boolean isGreaterThanZero(){
        return  this.cost != null && this.cost.compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean isLowerThanZero(){
        return  this.cost != null && this.cost.compareTo(BigDecimal.ZERO) < 0;
    }

    public boolean isGreaterThan(Money money){
        return this.cost.compareTo(money.getCost()) > 0;
    }

    public Money add(Money money){
        return  new Money(setScale(this.cost.add(money.getCost())));
    }

    public Money subtract(Money money){
        return new Money(setScale(this.cost.subtract(money.getCost())));
    }

    public Money multiply(Money money){
        return new Money(setScale(this.cost.multiply(money.getCost())));
    }

    public BigDecimal getCost() {
        return cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return cost.equals(money.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cost);
    }

    private BigDecimal setScale(BigDecimal input){
        return  input.setScale(2, RoundingMode.HALF_EVEN);
    }

}
