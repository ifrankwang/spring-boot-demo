package me.frank.demo.util;

import me.frank.demo.exception.ServiceException;

import java.util.function.Supplier;

/**
 * @author 王明哲
 */
public class Checkable {
    private boolean value;

    private Checkable(boolean value) {
        this.value = value;
    }

    public static Checkable of(boolean expression) {
        return new Checkable(expression);
    }

    public static Checkable oppositeOf(boolean expression) {
        return new Checkable(!expression);
    }

    public Checkable ifTrue(Function function) {
        if (value) {
            function.apply();
        }
        return this;
    }

    public <X extends Throwable> Checkable ifTrueThrow(Supplier<? extends X> exceptionSupplier) throws X {
        if (value) {
            throw exceptionSupplier.get();
        }
        return this;
    }

    public Checkable ifTrueThrow(ServiceException e) throws ServiceException {
        if (value) {
            throw e;
        }
        return this;
    }

    public Checkable orElse(Function function) {
        if (!value) {
            function.apply();
        }
        return this;
    }

    public <X extends Throwable> Checkable orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
        if (!value) {
            throw exceptionSupplier.get();
        }
        return this;
    }

    public Checkable orElseThrow(ServiceException e) throws ServiceException {
        if (!value) {
            throw e;
        }
        return this;
    }

    public Checkable toOpposite() {
        return oppositeOf(value);
    }

    public Checkable and(Checkable checkable) {
        return new Checkable(this.value && checkable.value);
    }

    public Checkable or(Checkable checkable) {
        return new Checkable(this.value || checkable.value);
    }

    public boolean toBoolean() {
        return value;
    }
}
